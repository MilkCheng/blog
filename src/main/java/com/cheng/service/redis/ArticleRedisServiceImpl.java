package com.cheng.service.redis;

import com.cheng.entity.system.ArticleDO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author MiaoCheng
 * @date: Create in 2018/6/19
 * @description:
 * @modifide By:
 */
@Repository
public class ArticleRedisServiceImpl {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 保存文章对象
     * @param key
     * @param time
     * @param articleDO
     */
    public void add(String key, Long time, ArticleDO articleDO){
        Gson gson = new Gson();
        redisTemplate.opsForValue().set(key, gson.toJson(articleDO), time, TimeUnit.MINUTES);
    }

    /**
     * 保存文章列表
     * @param key
     * @param time
     * @param articleDOS
     */
    public void add(String key, Long time, List<ArticleDO> articleDOS){
        Gson gson = new Gson();
        redisTemplate.opsForValue().set(key, gson.toJson(articleDOS), time, TimeUnit.MINUTES);
    }

    /**
     * 保存文章内容
     * @param key
     * @param time
     * @param content
     */
    public void add(String key, Long time, String content){
        redisTemplate.opsForValue().set(key, content, time, TimeUnit.MINUTES);
    }
    /**
     * 查找文章
     * @param key
     * @return
     */
    public ArticleDO get(String key){
        Gson gson = new Gson();
        ArticleDO articleDO = null;
        String articleJson = redisTemplate.opsForValue().get(key);
        if(articleJson != null){
            articleDO = gson.fromJson(articleJson, ArticleDO.class);
        }
        return articleDO;
    }

    /**
     * 查找文章内容
     * @param key
     * @return
     */
    public String getContent(String key){
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 查找列表
     * @param key
     * @return
     */
    public List<ArticleDO> getList(String key){
        Gson gson = new Gson();
        List<ArticleDO> list = null;
        String listJson = redisTemplate.opsForValue().get(key);
        if(listJson != null){
            list = gson.fromJson(listJson, new TypeToken<List<ArticleDO>>(){}.getType());
        }
        return list;
    }

    public void delete(String key){
        redisTemplate.opsForValue().getOperations().delete(key);
    }

}
