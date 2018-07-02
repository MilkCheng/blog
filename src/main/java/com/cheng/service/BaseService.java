package com.cheng.service;

import com.cheng.dao.system.BusinessLogDao;
import com.cheng.entity.system.BusinessLogDO;
import com.cheng.entity.system.UserDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Calendar;
import java.util.Date;

/**
 * @author miaocheng
 * @date 2018/05/29
 *
 */
@Service("BaseService")
public class BaseService {
    @Autowired
    private BusinessLogDao businessLogDao;
    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected void addBusinessLog(UserDO userDO, String message) {
        Assert.notNull(userDO,"BaseService user is null line:19");
        Assert.notNull(message,"BaseService message is null line:20");
        BusinessLogDO log = new BusinessLogDO(userDO, Calendar.getInstance(), message);
        log.setGmtCreate(new Date());
        log.setGmtModifide(new Date());
        businessLogDao.save(log);
    }
}
