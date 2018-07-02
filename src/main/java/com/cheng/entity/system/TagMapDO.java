package com.cheng.entity.system;

import com.cheng.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author MiaoCheng
 * @date: Create in 2018/6/4
 * @description:文章和tag映射
 * @modifide By:
 */
@Table(name = "tag_article_map")
@Entity
public class TagMapDO extends BaseEntity {
    @Column(name = "article_id")
    private Integer articleId;
    @Column(name = "tag_id")
    private Integer tagId;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    @Override
    public String toString() {
        return "TagMapDO{" +
                "articleId=" + articleId +
                ", tagId=" + tagId +
                '}';
    }
}
