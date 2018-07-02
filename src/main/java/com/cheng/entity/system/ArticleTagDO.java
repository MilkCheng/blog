package com.cheng.entity.system;

import com.cheng.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author MiaoCheng
 * @date: Create in 2018/6/4
 * @description: 文章TAG
 * @modifide By:
 */
@Table(name = "article_tag")
@Entity
public class ArticleTagDO extends BaseEntity {
    /**
     * tag名称
     */
    @Column(name = "tag_name", length = 10)
    private String tagName;
    /**
     * 被引用数量
     */
    @Column(name = "adopted_number")
    private Integer adoptedNumber;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getAdoptedNumber() {
        return adoptedNumber;
    }

    public void setAdoptedNumber(Integer adoptedNumber) {
        this.adoptedNumber = adoptedNumber;
    }

    @Override
    public String toString() {
        return "ArticleTagDO{" +
                "tagName='" + tagName + '\'' +
                ", adoptedNumber=" + adoptedNumber +
                '}';
    }
}
