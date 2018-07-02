package com.cheng.service;

/**
 * @author MiaoCheng
 * @date: Create in 2018/6/8
 * @description:
 * @modifide By:
 */
public interface ArticleTagService {
    /**
     * 保存文章及其对应tag
     * @param tags
     * @param articleId
     */
    public void saveTagFormString(String tags, Integer articleId);
}
