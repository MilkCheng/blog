package com.cheng.dao.system;

import com.cheng.dao.ExpandJpaRepository;
import com.cheng.entity.system.ArticleTagDO;
import org.springframework.stereotype.Repository;

/**
 * @author MiaoCheng
 * @date: Create in 2018/6/8
 * @description:
 * @modifide By:
 */
@Repository
public interface ArticleTagDao extends ExpandJpaRepository<ArticleTagDO, Integer> {
    /**
     * 根据tag名字获取tag
     * @param tagName
     * @return
     */
    public ArticleTagDO getArticleTagDOByTagName(String tagName);
}
