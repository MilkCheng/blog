package com.cheng.service;

import com.cheng.entity.system.ArticleCategoryDO;
import com.cheng.entity.system.UserDO;

import java.util.List;

/**
 * @author MiaoCheng
 * @date: Create in 2018/6/12
 * @description:
 * @modifide By:
 */
public interface CategoryService {
    /**
     * 添加分类
     * @param categoryName 分类名称
     * @param user 所属用户
     */
    void addCatrgory(String categoryName, UserDO user);

    /**
     * 获取用户的分类列表
     * @param user 用户
     * @return
     */
    List<ArticleCategoryDO> getCategoryListByUserId(UserDO user);
}
