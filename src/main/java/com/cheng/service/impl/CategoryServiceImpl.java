package com.cheng.service.impl;

import com.cheng.dao.system.ArticleCategoryDao;
import com.cheng.entity.system.ArticleCategoryDO;
import com.cheng.entity.system.UserDO;
import com.cheng.service.BaseService;
import com.cheng.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author MiaoCheng
 * @date: Create in 2018/6/12
 * @description:
 * @modifide By:
 */
@Service
public class CategoryServiceImpl extends BaseService implements CategoryService {
    @Autowired
    private ArticleCategoryDao articleCategoryDao;
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void addCatrgory(String categoryName, UserDO user) {
        if(categoryName == null || "".equals(categoryName) || user == null) {
            return;
        }
        ArticleCategoryDO acd = new ArticleCategoryDO();
        acd.setCategoryName(categoryName);
        acd.setUserDO(user);
        acd.setGmtCreate(new Date());
        acd.setGmtModifide(new Date());
        articleCategoryDao.save(acd);
    }

    @Override
    public List<ArticleCategoryDO> getCategoryListByUserId(UserDO user) {
        return articleCategoryDao.findArticleCategoryDOSByUserDO(user);
    }
}
