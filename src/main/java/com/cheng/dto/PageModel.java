package com.cheng.dto;

/**
 * @author MiaoCheng
 * @date: Create in 2018/6/5
 * @description:分页数据
 * @modifide By:
 */
public class PageModel {
    /**
     * 当前页
     */
    private Integer page = 0;
    /**
     * 页面大小
     */
    private Integer size = 10;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
