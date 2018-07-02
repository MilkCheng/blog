package com.cheng.service;

import com.cheng.entity.system.FileDO;
import com.cheng.util.FileCategory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author MiaoCheng
 * @date: Create in 2018/6/6
 * @description:文件服务
 * @modifide By:
 */
public interface FileService {
    /**
     * 保存文件记录到数据库并且把文件保存到硬盘
     * @param file 文件流
     * @param request http请求
     * @param fileCategory 文件类别，没有可以传入null
     * @param status 文件状态，默认为0
     * @return 返回保存的文件
     */
    FileDO addFile(MultipartFile file, HttpServletRequest request, FileCategory fileCategory
            , Integer status);

    /**
     * 传入文件名，返回经过目录打散之后的真实存储路径
     * @param fileName 文件名
     * @param request http请求
     * @return
     */
    String getRealSavePath(String fileName,HttpServletRequest request);

    /**
     * 存储文章内容，如果文件存在，会被覆盖
     * @param content 文本内容
     * @param file 完全存储路径
     * @throws FileNotFoundException
     */
    void saveArticle(String content, File file) throws FileNotFoundException;

    /**
     * 根据Src读取文件内容
     * @param src 文件地址
     * @return
     */
    String getContentBySrc(String src);
}
