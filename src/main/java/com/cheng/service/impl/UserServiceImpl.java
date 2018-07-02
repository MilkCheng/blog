package com.cheng.service.impl;

import com.cheng.dao.system.UserDao;
import com.cheng.entity.system.UserDO;
import com.cheng.service.BaseService;
import com.cheng.service.CategoryService;
import com.cheng.service.UserService;
import com.cheng.util.Constant;
import com.cheng.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author miaocheng
 * @date 2018/05/29
 *
 */
@Service
public class UserServiceImpl extends BaseService implements UserService{
    @Autowired
    private UserDao userDao;
    @Autowired
    private CategoryService categoryService;
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public String registUser(String email, String password1, String password2, HttpServletRequest request) {
        if(null==password1 || !password1.equals(password2) || "".equals(email) || email==null) {
            return Constant.ERROR;
        }else {
            //获取真实ip
            String ip = getRealIpAddress(request);
            UserDO userDO = userDao.findByEmail(email);
            //获取管理员用户
            UserDO admin = userDao.findOne(1);
            if(userDO != null) {
                addBusinessLog(admin,"ip："+ip+" 试图以用户名："+email+"注册，注册失败，用户已存在");
                return Constant.EXIST;
            }else{
                UserDO addUserDO = new UserDO();
                addUserDO.setUsername(email.split("@")[0]);
                addUserDO.setEmail(email);
                addUserDO.setPassword(Md5Util.md5Password(password1));
                addUserDO.setGmtCreate(new Date());
                addUserDO.setGmtModifide(new Date());
                userDao.save(addUserDO);
                categoryService.addCatrgory("默认", addUserDO);
                addBusinessLog(addUserDO, "用户："+ addUserDO.getEmail()+"首次注册！ip:"+ip);
                return Constant.SUCCESS;
            }
        }
    }

    @Override
    public String loginUser(UserDO userDO, HttpServletRequest request) {
        //获取管理员
        UserDO admin = userDao.findOne(1);
        //获取真实ip
        String ip = getRealIpAddress(request);
        if (userDO.getEmail() == null || userDO.getPassword() == null
                || "".equals(userDO.getEmail()) || "".equals(userDO.getPassword())) {
            return Constant.ERROR;
        }else{
            UserDO login = userDao.findByEmail(userDO.getEmail());
            if (login == null) {
                addBusinessLog(admin, "ip:"+ip+" 试图以用户名"+userDO.getEmail()+"登陆，登陆失败！用户不存在");
                return Constant.NOTEXIST;
            }else{
                if(!login.getPassword().equals(Md5Util.md5Password(userDO.getPassword()))){
                    addBusinessLog(admin, "ip:"+ip+
                            " 试图以用户名"+userDO.getEmail()+"登陆，登陆失败！密码错误："+userDO.getPassword());
                    return Constant.ERROR;
                }else {
                    saveLoginUser(request, login);
                    addBusinessLog(login, "用户："+login.getEmail()+" 登录！ip地址："+ip);
                    return Constant.SUCCESS;
                }
            }
        }
    }
    /**
     * 保存用户信息到Session中
     */
    private void saveLoginUser(HttpServletRequest request, UserDO userDO){
        HttpSession session = request.getSession();
        session.setAttribute(Constant.loginUser, userDO);
        // 登陆信息保存 5 小时
        session.setMaxInactiveInterval(1000*60*12);
    }

    /**
     * 删除用户登陆信息
     * @param request
     */
    private void deleteLoginInfo(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute(Constant.loginUser,null);
        session.setMaxInactiveInterval(0);
    }

    /**
     *获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     *
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     *
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     *用户真实IP为： 192.168.1.110
     *
     * @param request
     * @return
     */
    private String getRealIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        String status = "unknown";
        if (ip == null || ip.length() == 0 || status.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || status.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || status.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || status.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || status.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
