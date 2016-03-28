package com.ants.project.core.utils;

import java.io.Serializable;
import java.util.List;

/**
 * 默认修改com.github.pagehelper.Page
 * Created by shunblog on 2016/3/22.
 */
public class Page<T> implements Serializable{

    //当前第几页
    private int current;
    //每页大小
    private int size;
    //数据对象
    private List<T> data;
    //总记录数
    private long total;

    //总页数
    private int pages;

    public Page(int current, int size){
        this.current = current;
        this.size = size;
    }

    public Page(List<T> list) {
        com.github.pagehelper.Page page = (com.github.pagehelper.Page) list;
        this.current = page.getPageNum();
        this.size = page.getPageSize();
        this.data = page.getResult();
        this.total = page.getTotal();
        this.pages = page.getPages();
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
