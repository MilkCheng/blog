package com.cheng.entity.system;



import com.cheng.entity.BaseEntity;
import com.cheng.util.Gender;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigInteger;
import java.util.Date;
/**
 * @author miaocheng
 * @date 2018/05/29
 *
 */
@Entity
@Table(name = "user")
public class UserDO extends BaseEntity{
    @Column(name = "email", length = 255)
    private String email;
    @Column(name = "gender")
    private Gender gender;
    @Column(name = "mobile", length = 64)
    private String mobile;
    @Column(name = "name", length = 64)
    private String name;
    @Column(name = "username", length = 64)
    private String username;
    @Column(name = "password", length = 64)
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", gender=" + gender +
                ", mobile='" + mobile + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
