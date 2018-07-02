package com.cheng.dao.system;

import com.cheng.dao.ExpandJpaRepository;
import com.cheng.entity.system.ArticleCategoryDO;
import com.cheng.entity.system.UserDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author MiaoCheng
 * @date: Create in 2018/6/7
 * @description:
 * @modifide By:
 */
@Repository
public interface ArticleCategoryDao extends ExpandJpaRepository<ArticleCategoryDO, Integer> {
    /**
     * 查找用户所有的分类
     * @param user
     * @return
     */
    List<ArticleCategoryDO> findArticleCategoryDOSByUserDO(UserDO user);
}
