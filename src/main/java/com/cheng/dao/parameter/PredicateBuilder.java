package com.cheng.dao.parameter;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author MiaoCheng
 * @date: Create in 2018/6/5
 * @description: 连接符
 * @modifide By:
 */
public class PredicateBuilder {
    private List<Predicate> predicates = new ArrayList<Predicate>();

    public static PredicateBuilder create(){
        return new PredicateBuilder();
    }

    public PredicateBuilder add(String key,Object value,LinkEnum link){
        predicates.add(new Predicate(key,value,link));
        return this;
    }

    public PredicateBuilder add(String key,Object value){
        predicates.add(new Predicate(key,value));
        return this;
    }
    public List<Predicate> build(){
        return predicates;
    }
}
