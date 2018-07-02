package com.cheng.entity.system;

import com.cheng.entity.BaseEntity;

import javax.persistence.*;

/**
 * @author MiaoCheng
 * @date: Create in 2018/6/4
 * @description: 文章类型
 * @modifide By:
 */
@Table(name = "article_category")
@Entity
public class ArticleCategoryDO extends BaseEntity {
    /**
     *类型名称
     */
    @Column(name = "category_name", length = 20)
    private String categoryName;
    /**
     * 所属用户
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDO userDO;



    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public UserDO getUserDO() {
        return userDO;
    }

    public void setUserDO(UserDO userDO) {
        this.userDO = userDO;
    }
    @Override
    public String toString() {
        super.toString();
        return "ArticleCategoryDO{" +
                "categoryName='" + categoryName + '\'' +
                ", user=" + userDO +
                '}';
    }
}
