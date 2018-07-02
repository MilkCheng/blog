package com.cheng.entity.system;

import com.cheng.entity.BaseEntity;
import com.cheng.util.FileCategory;

import javax.persistence.*;

/**
 * @author MiaoCheng
 * @date: Create in 2018/6/6
 * @description: 文件资源
 * @modifide By:
 */
@Table(name = "file")
@Entity
public class FileDO extends BaseEntity {
    /**
     * 文件名
     */
    @Column(name = "file_name", length = 64)
    private String fileName;
    /**
     * 文件存放路径
     */
    @Column(name = "file_src", length = 300)
    private String fileSrc;
    /**
     * 所属用户
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDO user;
    /**
     * 状态
     */
    @Column(name = "status")
    private Integer status;
    /**
     * 文件类别
     */
    @Column(name = "category")
    private FileCategory category;
    /**
     * 文件大小
     */
    @Column(name = "size")
    private Long size;
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSrc() {
        return fileSrc;
    }

    public void setFileSrc(String fileSrc) {
        this.fileSrc = fileSrc;
    }

    public UserDO getUser() {
        return user;
    }

    public void setUser(UserDO user) {
        this.user = user;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public FileCategory getCategory() {
        return category;
    }

    public void setCategory(FileCategory category) {
        this.category = category;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "FileDO{" +
                "fileName='" + fileName + '\'' +
                ", fileSrc1='" + fileSrc + '\'' +
                ", user=" + user +
                ", status=" + status +
                ", category=" + category +
                ", size=" + size +
                '}';
    }
}
