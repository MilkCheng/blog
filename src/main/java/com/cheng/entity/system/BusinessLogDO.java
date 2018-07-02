package com.cheng.entity.system;

import com.cheng.entity.BaseEntity;

import javax.persistence.*;
import java.util.Calendar;
/**
 * @author miaocheng
 * @date 2018/05/29
 *
 */
@Entity
@Table(name = "business_log")
public class BusinessLogDO extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    /**
     *操作人
     */
    private UserDO userDO;
    @Column(name = "op_time")
    @Temporal(TemporalType.TIMESTAMP)
    /**
     *操作时间
     */
    private Calendar opTime;
    @Column(name = "message", length = 255)
    /**
     * 操作描述
     */
    private String message;

    public BusinessLogDO(UserDO userDO, Calendar opTime, String message) {
        super();
        this.userDO = userDO;
        this.opTime = opTime;
        this.message = message;
    }
    public UserDO getUserDO() {
        return userDO;
    }

    public void setUserDO(UserDO userDO) {
        this.userDO = userDO;
    }

    public Calendar getOpTime() {
        return opTime;
    }

    public void setOpTime(Calendar opTime) {
        this.opTime = opTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BusinessLogDO{" +
                "user=" + userDO +
                ", opTime=" + opTime +
                ", message='" + message + '\'' +
                '}';
    }
}
