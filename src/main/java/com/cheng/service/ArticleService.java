package com.cheng.service;

import com.cheng.dto.ArticleDTO;
import com.cheng.entity.system.ArticleDO;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author MiaoCheng
 * @date: Create in 2018/6/5
 * @description:
 * @modifide By:
 */

public interface ArticleService{
    /**
     * 添加文章
     * @param article 文章VO
     * @param coverImg 封面图片
     * @return 返回添加结果信息
     */
    String addArticle(ArticleDTO article, MultipartFile coverImg, HttpServletRequest request);

    /**
     * 查询分页
     * @param articleQuery 分页模型
     * @return
     */
    Page<ArticleDO> findPage(ArticleDTO articleQuery);

    /**
     * 根据id阅读文章
     * @param request
     * @param id
     * @return
     */
    ArticleDTO readArticleById(HttpServletRequest request, Integer id);

}
