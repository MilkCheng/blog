package com.cheng.service.impl;

import com.cheng.dao.system.ArticleTagDao;
import com.cheng.dao.system.TagMapDao;
import com.cheng.entity.system.ArticleTagDO;
import com.cheng.entity.system.TagMapDO;
import com.cheng.service.ArticleTagService;
import com.cheng.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author MiaoCheng
 * @date: Create in 2018/6/8
 * @description:
 * @modifide By:
 */
@Service
public class ArticleTagServiceImpl extends BaseService implements ArticleTagService {
    @Autowired
    private ArticleTagDao articleTagDao;
    @Autowired
    private TagMapDao tagMapDao;
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveTagFormString(String tags, Integer articleId) {
        if(tags == null || "".equals(tags) || articleId == null){
            return;
        }
        String[] tagName = tags.split(",");
        for (String tag : tagName ) {
            ArticleTagDO aTag = articleTagDao.getArticleTagDOByTagName(tag);
            TagMapDO tmd = new TagMapDO();
            tmd.setArticleId(articleId);
            tmd.setGmtCreate(new Date());
            tmd.setGmtModifide(new Date());
            if(aTag != null) {
                // 如果tag已经存在，就把引用数量加1，并且记录被引用的文章id
                aTag.setAdoptedNumber(aTag.getAdoptedNumber()+1);
                aTag.setGmtModifide(new Date());
                articleTagDao.save(aTag);
            }else {
                // 如果tag不存在，就新建一个
                aTag = new ArticleTagDO();
                aTag.setAdoptedNumber(1);
                aTag.setTagName(tag);
                aTag.setGmtCreate(new Date());
                aTag.setGmtModifide(new Date());
                articleTagDao.save(aTag);
            }
            tmd.setTagId(aTag.getId());
            tagMapDao.save(tmd);
        }
    }
}
