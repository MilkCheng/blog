package mysql.test;

import com.cheng.dao.system.UserDao;
import com.cheng.entity.system.UserDO;
import com.cheng.util.Gender;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfig.class})
public class MysqlTest {
    private static Logger logger = LoggerFactory.getLogger(MysqlTest.class);
    @Autowired
    private UserDao userDao;
    @Before
    @Transactional
    public void initDao()
    {
        UserDO u = new UserDO();
        u.setName("李明");
        u.setPassword("123131");
        u.setUsername("LIMING");
        u.setGender(Gender.MALE);
        userDao.save(u);
    }

    @Test
    public void findByPage()
    {
        Pageable pageable = new PageRequest(0, 10, new Sort(Sort.Direction.ASC, "id"));
        Page<UserDO> page = userDao.findAll(pageable);
        Assert.notNull(page,"查找人员失败！");
       /* for(UserDO userDO : page.getContent()) {
            logger.info("====user==== user name:{}, Username:{}, Gender name:{}",
                    userDO.getName(), userDO.getUsername(), userDO.getGender().getValue());
        }*/
    }
}
