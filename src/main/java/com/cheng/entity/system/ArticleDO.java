package com.cheng.entity.system;

import com.cheng.entity.BaseEntity;
import com.cheng.util.ArticleStatus;

import javax.persistence.*;

/**
 * @author MiaoCheng
 * @date: Create in 2018/6/3
 * @description: 文章实体
 * @modifide By:
 */
@Table(name = "article")
@Entity
public class ArticleDO extends BaseEntity {
    /**
     * 标题
     */
    @Column(name = "title", nullable = false, length = 50)
    private String title;
    /**
     * 作者
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserDO author;
    /**
     * 文章存储路径
     */
    @Column(name = "article_src", nullable = false)
    private String articleSrc;
    /**
     * 文章状态
     */
    @Column(name = "status", nullable = false)
    private ArticleStatus status;
    /**
     * 文章类别
     */
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private ArticleCategoryDO category;
    /**
     * 文章tag
     */
    @Column(name = "tag")
    private String tag;
    /**
     * 封面图片
     */
    @ManyToOne
    @JoinColumn(name = "cover_img")
    private FileDO coverImg;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserDO getAuthor() {
        return author;
    }

    public void setAuthor(UserDO author) {
        this.author = author;
    }

    public String getArticleSrc() {
        return articleSrc;
    }

    public void setArticleSrc(String articleSrc) {
        this.articleSrc = articleSrc;
    }

    public ArticleStatus getStatus() {
        return status;
    }

    public void setStatus(ArticleStatus status) {
        this.status = status;
    }

    public ArticleCategoryDO getCategory() {
        return category;
    }

    public void setCategory(ArticleCategoryDO category) {
        this.category = category;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public FileDO getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(FileDO coverImg) {
        this.coverImg = coverImg;
    }

    @Override
    public String toString() {
        return "ArticleDO{" +
                "title='" + title + '\'' +
                ", author=" + author +
                ", articleSrc='" + articleSrc + '\'' +
                ", status=" + status +
                ", category=" + category +
                ", tag='" + tag + '\'' +
                ", coverImg=" + coverImg +
                '}';
    }
}
