package com.cheng.entity;



import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
@MappedSuperclass
/**
 * @author miaocheng
 * @date 2018/05/29
 *
 */
public class BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * id
     */
    private Integer id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "gmt_create")
    /**
     * 创建时间
     */
    private Date gmtCreate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "gmt_modifide")
    /**
     * 修改时间
     */
    private Date gmtModifide;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModifide() {
        return gmtModifide;
    }

    public void setGmtModifide(Date gmtModifide) {
        this.gmtModifide = gmtModifide;
    }
}
