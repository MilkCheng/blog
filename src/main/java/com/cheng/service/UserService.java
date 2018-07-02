package com.cheng.service;

import com.cheng.entity.system.UserDO;

import javax.servlet.http.HttpServletRequest;

/**
 * @author miaocheng
 * @date 2018/05/29
 *
 */
public interface UserService{
    /**
     * 对注册用户进行验证并添加注册
     * @param email
     * @param password1
     * @param password2
     * @param request
     * @return error(输入数据错误), success（注册成功）, exsit（邮箱已存在）
     */

    public String registUser(String email, String password1, String password2, HttpServletRequest request);

    /**
     * 用户登录，并返回相应实体类
     * @param userDO  包含邮箱密码的user对象
     * @param request request请求
     * @return
     */
    public String loginUser(UserDO userDO, HttpServletRequest request);
}
