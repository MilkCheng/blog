package com.cheng.entity.system;

import com.cheng.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author MiaoCheng
 * @date: Create in 2018/5/29
 * @description: 用户角色表
 * @modifide By:
 */
@Entity
@Table(name = "role")
public class RoleDO extends BaseEntity {
    /**
     * 角色名
     */
    @Column(name = "name", length = 64, nullable = false)
    private String name;
    /**
     * 角色描述
     */
    @Column(name = "description", length = 255)
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RoleDO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
