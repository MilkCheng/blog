package com.cheng.dao.system;

import com.cheng.dao.ExpandJpaRepository;
import com.cheng.entity.system.UserDO;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
/**
 * @author miaocheng
 * @date 2018/05/29
 *
 */
@Repository
public interface UserDao extends ExpandJpaRepository<UserDO, Integer> {
    /**
     * 根据email查找并返回用户实体类
     * @param email
     * @return
     */
    public UserDO findByEmail(String email);

}
