package com.cheng.util;
/**
 * @author MiaoCheng
 * @date: Create in 2018/6/6
 * @description: 文件分类
 * @modifide By:
 */
public enum FileCategory {
    /**
     *分面图片
     */
    COVERIMG("封面图片"),
    /**
     *用户头像
     */
    PHOTO("头像");

    private String value;
    private FileCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
