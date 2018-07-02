package com.cheng.web.user;


import com.cheng.dto.ArticleDTO;
import com.cheng.entity.system.ArticleCategoryDO;
import com.cheng.entity.system.ArticleDO;
import com.cheng.entity.system.UserDO;
import com.cheng.service.ArticleService;
import com.cheng.service.CategoryService;
import com.cheng.service.UserService;
import com.cheng.util.Constant;
import com.cheng.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * @author miaocheng
 * @date 2018/05/29
 *
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;
    /**
     * 跳转到首页
     * @return
     */
    @RequestMapping("/toIndex")
    public ModelAndView toIndex(ArticleDTO articleDTO){
        ModelAndView mav = new ModelAndView("common/index");
        String category = articleDTO.getCategory();
        if(category != null){
            try {
                articleDTO.setCategory(URLDecoder.decode(category,"UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        Page<ArticleDO> page = articleService.findPage(articleDTO);
        mav.addObject("category",articleDTO.getCategory());
        mav.addObject("page", page);
        return mav;
    }
    @RequestMapping("/toLogin")
    public ModelAndView toLogion()
    {
        return new ModelAndView("user/login");
    }
    @RequestMapping("/toRegist")
    public ModelAndView toRegist()
    {
        return new ModelAndView("user/regist");
    }
    @RequestMapping("/regist")
    public ModelAndView regist(String email, String password1, String password2, HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        String msg = userService.registUser(email, password1, password2, request);
        if(msg.equals(Constant.SUCCESS)) {
            mav.setViewName("user/login");
            mav.addObject("msg", "注册成功，请您登陆！");
        }
        if(msg.equals(Constant.ERROR)) {
            mav.setViewName("user/regist");
            mav.addObject("msg", "输入格式有误！");
        }
        if(msg.equals(Constant.EXIST)) {
            mav.setViewName("user/login");
            mav.addObject("msg", "此邮箱已存在，请直接登陆！");
        }
        return mav;
    }
    @RequestMapping("/login")
    public String login(UserDO userDO, HttpServletRequest request){
        return userService.loginUser(userDO, request);
    }
    @RequestMapping("/toCategoryMange")
    public ModelAndView toCategoryMange(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        List<ArticleCategoryDO> catoryList =categoryService.getCategoryListByUserId(getLoginUser(request));
        mav.addObject("catoryList", catoryList);
        mav.setViewName("user/categoryMange");
        return mav;
    }
}
