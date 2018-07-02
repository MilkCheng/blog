package com.cheng.util;
/**
 * @author MiaoCheng
 * @date: Create in 2018/6/3
 * @description: 文章状态
 * @modifide By:
 */
public enum ArticleStatus {
    /**
     *隐藏，仅自己可见
     */
    HIDE("隐藏"),
    /**
     *草稿，暂不发布
     */
    DRAFT("草稿"),
    /*
    *展示
    */
    SHOW("展示");
    private String value;
    private ArticleStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
