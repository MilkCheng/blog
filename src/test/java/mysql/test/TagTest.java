package mysql.test;

import com.cheng.dao.system.ArticleTagDao;
import com.cheng.entity.system.ArticleTagDO;
import com.cheng.service.ArticleTagService;
import com.cheng.service.impl.ArticleTagServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author MiaoCheng
 * @date: Create in 2018/6/8
 * @description:
 * @modifide By:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfig.class})
@EntityScan(basePackages = "com.**.**")
public class TagTest {
    @Autowired
    ArticleTagDao ats;
    ArticleTagService articleTagService = new ArticleTagServiceImpl();
    @Test
    public void tagAddTest(){
       /* ArticleTagDO atd = new ArticleTagDO();
        atd.setTagName("测试");
        atd.setAdoptedNumber(1);
        ats.save(atd);
        List<ArticleTagDO> atList = ats.findAll();
        int a = 1;*/
    }

}
