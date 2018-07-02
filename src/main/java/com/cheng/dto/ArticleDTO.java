package com.cheng.dto;

import com.cheng.entity.system.ArticleCategoryDO;
import com.cheng.entity.system.ArticleTagDO;
import com.cheng.entity.system.UserDO;
import com.cheng.util.ArticleStatus;

import java.util.Date;

/**
 * @author MiaoCheng
 * @date: Create in 2018/6/3
 * @description: 添加文章DTO
 * @modifide By:
 */
public class ArticleDTO extends PageModel{
    /**
     * id
     */
    private Integer id;
    /**
     * 标题
     */
    private String title;
    /**
     * tag
     */
    private String tag;
    /**
     * 分类
     */
    private String category;
    /**
     * 状态
     */
    private String status;
    /**
     * 内容
     */
    private String content;
    /**
     * 作者
     */
    private UserDO author;
    /**
     * 发布时间
     */
    private Date time;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserDO getAuthor() {
        return author;
    }

    public void setAuthor(UserDO author) {
        this.author = author;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ArticleDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", tag='" + tag + '\'' +
                ", category='" + category + '\'' +
                ", status='" + status + '\'' +
                ", content='" + content + '\'' +
                ", author=" + author +
                ", time=" + time +
                '}';
    }
}
