package redis;

import com.cheng.entity.system.ArticleDO;
import com.cheng.service.redis.ArticleRedisServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author MiaoCheng
 * @date: Create in 2018/6/19
 * @description:
 * @modifide By:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RedisConfig.class, ArticleRedisServiceImpl.class})
public class RedisServiceTest {
    private static Logger logger = LoggerFactory.getLogger(RedisServiceTest.class);
    @Autowired
    private ArticleRedisServiceImpl articleRedis;
    @Before
    public void setup(){
        ArticleDO articleDO = new ArticleDO();
        articleDO.setTitle("测试redis缓存");
        articleDO.setTag("asdasdads");
        articleDO.setId(1);
        articleRedis.delete(this.getClass().getName()+":articleid:"+articleDO.getId());
        articleRedis.add(this.getClass().getName()+":articleid:"+articleDO.getId(), 10L, articleDO);
    }
    @Test
    public void get(){
        ArticleDO articleDO = articleRedis.get(this.getClass().getName()+":articleid:1");
        Assert.assertNotNull(articleDO);
        logger.info("=====article==== title:{},id:{},tag:{}",
                articleDO.getTitle(),articleDO.getTag(),articleDO.getId());
        System.out.println(articleDO.getTitle()+"..."+articleDO.getTag()+"..."+articleDO.getId());
    }
}
