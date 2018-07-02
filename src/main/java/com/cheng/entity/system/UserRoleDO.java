package com.cheng.entity.system;

import com.cheng.entity.BaseEntity;

import javax.persistence.*;

/**
 * @author MiaoCheng
 * @date: Create in 2018/5/29
 * @description: 用户角色关联
 * @modifide By:
 */
@Entity
@Table(name = "user_role")
public class UserRoleDO extends BaseEntity {
    /**
     * 用户
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserDO userDO;
    /**
     * 角色
     */
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleDO roleDO;

    @Override
    public String toString() {
        return "UserRoleDO{" +
                "user=" + userDO +
                ", roleDO=" + roleDO +
                '}';
    }
}
