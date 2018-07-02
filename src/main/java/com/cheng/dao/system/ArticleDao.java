package com.cheng.dao.system;

import com.cheng.dao.ExpandJpaRepository;
import com.cheng.entity.system.ArticleDO;
import org.springframework.stereotype.Repository;

/**
 * @author MiaoCheng
 * @date: Create in 2018/6/7
 * @description:
 * @modifide By:
 */
@Repository
public interface ArticleDao extends ExpandJpaRepository<ArticleDO, Integer> {
}
