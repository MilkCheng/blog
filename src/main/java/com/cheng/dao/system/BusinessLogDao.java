package com.cheng.dao.system;

import com.cheng.dao.ExpandJpaRepository;
import com.cheng.entity.system.BusinessLogDO;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
/**
 * @author miaocheng
 * @date 2018/05/29
 *
 */
@Repository
public interface BusinessLogDao extends ExpandJpaRepository<BusinessLogDO, BigInteger> {
}
