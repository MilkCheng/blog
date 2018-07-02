package com.cheng.service.impl;

import com.cheng.dao.parameter.LinkEnum;
import com.cheng.dao.parameter.Operator;
import com.cheng.dao.parameter.PredicateBuilder;
import com.cheng.dao.system.ArticleCategoryDao;
import com.cheng.dao.system.ArticleDao;
import com.cheng.dto.ArticleDTO;
import com.cheng.entity.system.ArticleCategoryDO;
import com.cheng.entity.system.ArticleDO;
import com.cheng.entity.system.FileDO;
import com.cheng.service.ArticleService;
import com.cheng.service.ArticleTagService;
import com.cheng.service.BaseService;
import com.cheng.service.FileService;
import com.cheng.service.redis.ArticleRedisServiceImpl;
import com.cheng.util.ArticleStatus;
import com.cheng.util.Constant;
import com.cheng.util.FileCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;

/**
 * @author MiaoCheng
 * @date: Create in 2018/6/5
 * @description:
 * @modifide By:
 */
@Service
public class ArticleServiceImpl extends BaseService implements ArticleService {
    @Autowired
    private FileService fileService;
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ArticleCategoryDao articleCategoryDao;
    @Autowired
    private ArticleTagService articleTagService;
    @Autowired
    private ArticleRedisServiceImpl articleRedisService;
    /**
     * 用来在redis中生成唯一键。文章内容 mysql:artrcle:content
     */
    private static final String keyHead = "mysql:artrcle:";
    @Override
    public String addArticle(ArticleDTO article, MultipartFile coverImg, HttpServletRequest request) {
        // 获取文件保存路径并且保存到硬盘
        String fileSrc = fileService.getRealSavePath(article.getTitle(), request);
        try {
            fileService.saveArticle(article.getContent(), new File(fileSrc));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return Constant.ERROR;
        }

        //添加封面图片
        FileDO img = fileService.addFile(coverImg, request, FileCategory.COVERIMG, 0);
        // 文章添加记录到数据库
        ArticleDO ad = new ArticleDO();
        ad.setTitle(article.getTitle());
        ad.setArticleSrc(fileSrc);
        ArticleCategoryDO acd = articleCategoryDao.findOne(new Integer(article.getCategory()));
        ad.setCategory(acd);
        ad.setStatus(article.getStatus()=="on"?ArticleStatus.HIDE:ArticleStatus.SHOW);
        ad.setTag(article.getTag());
        ad.setAuthor(article.getAuthor());
        ad.setGmtCreate(new Date());
        ad.setGmtModifide(new Date());
        ad.setCoverImg(img);
        articleDao.save(ad);
        // 数据存储到缓存中
        articleRedisService.add(keyHead+ ad.getId(), 12L, ad);
        articleRedisService.add(keyHead + "content:" + ad.getId(), 10L, article.getContent());
        //记录文章添加日志
        addBusinessLog(article.getAuthor(),"添加文章：《"+article.getTitle()+"》");
        // 保存tag
        articleTagService.saveTagFormString(article.getTag(),ad.getId());
        //记录tag添加日志
        addBusinessLog(article.getAuthor(),"添加文章tag："+article.getTag());
        return Constant.SUCCESS;
    }

    @Override
    public Page<ArticleDO> findPage(ArticleDTO articleQuery) {
        Pageable pageable = new PageRequest(articleQuery.getPage(),
                                    articleQuery.getSize(), new Sort(Sort.Direction.DESC, "id"));
        PredicateBuilder pb  = new PredicateBuilder();
        if(articleQuery.getAuthor() != null){
            pb.add("author", articleQuery.getAuthor(), LinkEnum.EQ);
        }
        if(articleQuery.getCategory() != null && !"".equals(articleQuery.getCategory())) {
            pb.add("category", articleQuery.getCategory(), LinkEnum.EQ);
        }
        return articleDao.findAll(pb.build(), Operator.AND, pageable);
    }

    @Override
    public ArticleDTO readArticleById(HttpServletRequest request, Integer id) {
        if(id == null){
            return null;
        }
        // 先在缓存中寻找
        ArticleDO articleDO = articleRedisService.get(keyHead+id);
        if(articleDO == null) {
            // 如果缓存中不存在，到关系库中查询
            articleDO = articleDao.findOne(id);
            if(articleDO != null ){
                // 把查询出来的数据添加到缓存中
                articleRedisService.add(keyHead+id, 12L, articleDO);
            }else{
                return null;
            }
        }
        String content = articleRedisService.getContent(keyHead+"content:"+id);
        if(content == null){
            content = fileService.getContentBySrc(articleDO.getArticleSrc());
            if(content != null){
                articleRedisService.add(keyHead+"content:"+id, 30L, content);
            }
        }
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setTitle(articleDO.getTitle());
        articleDTO.setAuthor(articleDO.getAuthor());
        articleDTO.setCategory(articleDO.getCategory().getCategoryName());
        articleDTO.setTime(articleDO.getGmtCreate());
        articleDTO.setTag(articleDO.getTag());
        articleDTO.setContent(content);
        articleDTO.setId(articleDO.getId());
        return articleDTO;
    }
}
