package com.cheng.dao;

import com.cheng.dao.parameter.Operator;
import com.cheng.dao.parameter.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author MiaoCheng
 * @date: Create in 2018/6/5
 * @description: 扩展JPA接口
 * @modifide By:
 */
@NoRepositoryBean
public interface ExpandJpaRepository<T, ID extends Serializable> extends JpaRepository<T,ID> {
    /**
     * 返回一个对象
     * @param condition sql
     * @param objects
     * @return
     */
    T findOne(String condition, Object... objects);

    /**
     * 返回查询对象列表
     * @param condition
     * @param objects
     * @return
     */
    List<T> findAll(String condition, Object... objects);

    /**
     * 查询所有
     * @param predicates
     * @param operator
     * @return
     */
    List<T> findAll(Iterable<Predicate> predicates, Operator operator);

    /**
     *  查询所有
     * @param predicates
     * @param operator
     * @param sort
     * @return
     */
    List<T> findAll(Iterable<Predicate> predicates, Operator operator, Sort sort);

    /**
     *  查询所有
     * @param predicates
     * @param operator
     * @param pageable
     * @return
     */
    Page<T> findAll(Iterable<Predicate> predicates, Operator operator, Pageable pageable);

    /**
     *  查询数量
     * @param predicates
     * @param operator
     * @return
     */
    long count(Iterable<Predicate> predicates, Operator operator);

    /**
     *  查询所有
     * @param condition
     * @param sort
     * @param objects
     * @return
     */
    List<T> findAll(String condition, Sort sort, Object... objects);

    /**
     * 查询分页
     * @param condition
     * @param pageable
     * @param objects
     * @return
     */
    Page<T> findAll(String condition, Pageable pageable, Object... objects);

    /**
     * 统计总数
     * @param condition
     * @param objects
     * @return
     */
    long count(String condition, Object... objects);

    /**
     * 根据id删除
     * @param ids
     */
    void deleteByIds(Iterable<ID> ids);

    /**
     * 获取实体类型
     * @return
     */
    Class<T> getEntityClass();

    /**
     * nativeQuery4Map
     * @param sql
     * @return
     */
    List<Map<String,Object>> nativeQuery4Map(String sql);

    /**
     * nativeQuery4Map
     * @param sql
     * @param pageable
     * @return
     */
    Page<Map> nativeQuery4Map(String sql, Pageable pageable);

    /**
     * nativeQuery4Map
     * @param sql
     * @return
     */
    Object nativeQuery4Object(String sql);
}
