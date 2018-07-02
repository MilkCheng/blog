package com.cheng.service.impl;

import com.cheng.dao.system.FileDao;
import com.cheng.entity.system.FileDO;
import com.cheng.entity.system.UserDO;
import com.cheng.service.BaseService;
import com.cheng.service.FileService;
import com.cheng.util.Constant;
import com.cheng.util.FileCategory;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;
import java.util.UUID;

/**
 * @author MiaoCheng
 * @date: Create in 2018/6/6
 * @description: 文件服务实现类
 * @modifide By:
 */
@Service
public class FileServiceImpl extends BaseService implements FileService {
    @Autowired
    private FileDao fileDao;
    /**
     * 文件存储路径前缀,这么写是为了能够在切分的时候避免 / 的错误识别
     * 因为要在数据库中保存相对地址，所以要从中间切断
     */
    private final String fixFilePath1 = "file";
    private final String fixFilePath2 = "upload";
    private final String fixFilePath = File.separator + fixFilePath1+ File.separator + fixFilePath2+ File.separator;
    @Override
    public FileDO addFile(MultipartFile file, HttpServletRequest request, FileCategory fileCategory
                             , Integer status) {
        if(file == null || request == null || fileCategory == null || status == null){
            return null;
        }
        // 创建一个DiskFileItemfactory工厂类
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 创建一个ServletFileUpload核心对象
        ServletFileUpload sfu = new ServletFileUpload(factory);
        // 解决上传表单项乱码问题
        sfu.setHeaderEncoding("UTF-8");
        // 指定临时文件的存储目录
        String tmpPath = request.getServletContext().getRealPath(" /temp");
        File repo = new File(tmpPath);
        if (!repo.exists()) {
            repo.mkdirs();
        }
        factory.setRepository(repo);
        if (file == null) {
            return null;
        }
        // 执行文件上传的操作
        // 判断表单是否支持文件上传。即：enctype="multipart/form-data"
        boolean isMultipartContent = ServletFileUpload
                .isMultipartContent(request);
        if (!isMultipartContent) {
            throw new RuntimeException("your form is not multipart/form-data");
        }
        // 获取文件名
        String filename = file.getOriginalFilename();
        filename = FilenameUtils.getName(filename);
        // 设置文件名,数据库中文件名长度设置为64，如果文件名长度大于55，后面的长度就用***代替
        if (filename.length() >= 55) {
            String temp = FilenameUtils.getBaseName(filename);
            temp = temp.substring(0, 55) + "***" + FilenameUtils.getExtension(filename);
            filename = temp;
        }
        // 创建一个用于插入数据库中的File对象
        FileDO fup = new FileDO();
        fup.setFileName(filename);
        // 获取经过目录打散之后真实的存储路径并添加
        String realPath = getRealSavePath(filename, request);
        // 数据库中保存相对路径
        fup.setFileSrc(fixFilePath + realPath.split(fixFilePath2)[1]);
        fup.setCategory(fileCategory);
        fup.setStatus(status);
        fup.setUser((UserDO) request.getSession().getAttribute(Constant.loginUser));
        fup.setGmtCreate(new Date());
        fup.setGmtModifide(new Date());
        fup.setSize(file.getSize());
        // 将文件添加到数据库
        fileDao.save(fup);
        //文件保存到硬盘
        FileOutputStream fos = null;
        InputStream is = null;
        try {
            fos = new FileOutputStream(new File(realPath));
            is = file.getInputStream();
            byte[] b = new byte[8 * 1024];
            int len;
            while ((len = is.read(b)) != -1) {
                fos.write(b, 0, len);
            }
        }catch (FileNotFoundException e1){
            e1.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(is != null ){
                    is.close();
                }
                if (fos != null){
                    fos.close();
                }
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
        return fup;
    }

    @Override
    public String getRealSavePath(String fileName, HttpServletRequest request) {
        if(fileName == null || "".equals(fileName)){
            return null;
        }
        // 创建一个文件存盘的目录
        String directoryRealPath = request.getServletContext().getRealPath(fixFilePath);
        //即代表文件又代表目录
        System.out.println(directoryRealPath);
        File storeDirectory = new File(directoryRealPath);
        if (!storeDirectory.exists()) {
            // 创建一个指定的目录
            storeDirectory.mkdirs();
        }
        // 返回字符转换的32位hashcode码
        int hashcode = fileName.hashCode();
        // 把hashcode转换为16进制的字符
        String code = Integer.toHexString(hashcode);
        String childDirectory = code.charAt(0) + File.separator
                + code.charAt(1) + File.separator + code.charAt(3) + File.separator + code.charAt(4);
        // 创建指定目录
        File file = new File(storeDirectory, childDirectory);
        if (!file.exists()) {
            file.mkdirs();
        }
        //防止重名
		/*SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		String date = sdf.format(new Date());*/
        String base = UUID.randomUUID().toString().replaceAll("-", "");
        String path = file.getAbsolutePath() + File.separator + base + "."
                + (FilenameUtils.getExtension(fileName)==""?"txt":FilenameUtils.getExtension(fileName));
        return path;
    }

    @Override
    public void saveArticle(String content, File file) throws FileNotFoundException {
        if(file.exists())
        {
            file.delete();
        }
        FileOutputStream fos = new FileOutputStream(file);
        PrintWriter pw = new PrintWriter(fos);
        pw.print(content);
        if(pw != null) {
            pw.close();
        }
    }

    @Override
    public String getContentBySrc(String src) {
        File file = new File(src);
        if(!file.exists()) {
            return null;
        }
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuffer stb = new StringBuffer();
        byte b[] = new byte[8*1024];
        int i = 0;
        try {
            while((i = fis.read(b)) != -1)
            {
                stb.append(new String(b,0,i));
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if(fis != null){
                    fis.close();
                }
            }catch (IOException e){

            }
        }
        return stb.toString();

    }
}
