package com.cheng.web.user;

import com.cheng.dto.ArticleDTO;
import com.cheng.entity.system.ArticleCategoryDO;
import com.cheng.entity.system.ArticleDO;
import com.cheng.entity.system.UserDO;
import com.cheng.service.ArticleService;
import com.cheng.service.CategoryService;
import com.cheng.util.Constant;
import com.cheng.web.BaseController;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author MiaoCheng
 * @date: Create in 2018/6/1
 * @description:
 * @modifide By:
 */
@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;
    /**
     * 跳转到文章编辑页面
     * @return
     */
    @RequestMapping("/toAdd")
    public ModelAndView toEditArticle(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user/addArticle");
        List<ArticleCategoryDO> catoryList =categoryService.getCategoryListByUserId(getLoginUser(request));
        mav.addObject("catoryList", catoryList);
        return mav;
    }

    /**
     * 保存文章
     * @return
     */
    @RequestMapping("addArticle")
    public ModelAndView addArticle(@RequestParam(required = false) MultipartFile coverImg, ArticleDTO article, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        UserDO user = getLoginUser(request);
        String msg = Constant.SUCCESS;
        if(user == null) {
            // session中用户不存在
            msg = Constant.NOTEXIST;
        }else{
            article.setAuthor(user);
            try{
                articleService.addArticle(article, coverImg, request);

            }catch (Exception e){
                // 保存出错
                msg = Constant.ERROR;
                e.printStackTrace();
            }
        }
        mav.addObject("msg",msg);
        mav.setViewName("redirect:/article/toAdd");
        return mav;
    }

    /**
     * 阅读文章
     * @param request
     * @return
     */
    @RequestMapping("/readArticle")
    public ModelAndView readArticle(HttpServletRequest request, Integer id) {
        ModelAndView mav = new ModelAndView();

        ArticleDTO articleDTO = articleService.readArticleById(request, id);
        // 如果没有找到这篇文章
        if(articleDTO == null){
            mav.setViewName("user/articleNotFound");
        }else{
            mav.setViewName("user/articleContent");
        }
        mav.addObject("article", articleDTO);
        return mav;
    }

    /**
     * 获取分页文章
     * @param articleDTO
     * @return
     */
    @RequestMapping("/getPageArticle")
    public Page<ArticleDO> getPageArticle(ArticleDTO articleDTO){

        return articleService.findPage(articleDTO);
    }

    /**
     * 修改分类
     * @return
     */
    @RequestMapping("/changeCategory")
    public String changeCategory(ArticleCategoryDO categoryDO){

        return "修改成功！";
    }
}
