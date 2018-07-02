package com.cheng.util;
/**
 * @author miaocheng
 * @date 2018/05/29
 *
 */
public enum Gender  {
    /**
     *男
     */
    MALE("男"),
    /**
     *女
     */
    FEMALE("女");
    private String value;
    private Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
