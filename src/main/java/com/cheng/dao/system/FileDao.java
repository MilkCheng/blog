package com.cheng.dao.system;

import com.cheng.dao.ExpandJpaRepository;
import com.cheng.entity.system.FileDO;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * @author MiaoCheng
 * @date: Create in 2018/6/7
 * @description:
 * @modifide By:
 */
@Repository
public interface FileDao extends ExpandJpaRepository<FileDO, BigInteger> {
}
