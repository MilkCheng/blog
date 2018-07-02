package com.cheng.dao.system;

import com.cheng.dao.ExpandJpaRepository;
import com.cheng.entity.system.TagMapDO;
import org.springframework.stereotype.Repository;

/**
 * @author MiaoCheng
 * @date: Create in 2018/6/8
 * @description:
 * @modifide By:
 */
@Repository
public interface TagMapDao extends ExpandJpaRepository<TagMapDO, Integer> {
}
