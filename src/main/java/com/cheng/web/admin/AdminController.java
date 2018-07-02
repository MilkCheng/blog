package com.cheng.web.admin;


import com.cheng.entity.system.UserDO;
import com.cheng.service.UserService;
import com.cheng.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author miaocheng
 * @date 2018/05/29
 *
 */

@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController {
    @Autowired
    private UserService userService;
    @RequestMapping("/toIndex")
    public ModelAndView toIndex(){
        return new ModelAndView("admin/mange");
    }
    @RequestMapping("/toLogin")
    public ModelAndView toLogion()
    {
        return new ModelAndView("admin/login");
    }
    @RequestMapping("/login")
    public String login(UserDO userDO, HttpServletRequest request){
        return userService.loginUser(userDO, request);
    }
}
