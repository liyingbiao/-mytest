package com.itheima.domain;

import java.util.List;

public class PageBean<T> {
    // 当前页
    private int pageNum;
    // 页大小
    private int pageSize=5;
    // 总记录数
    private long totalCount;
    // 总页数
    private int totalPage;
    private List<T> list;

    /**
     * 计算总页数
     * @return
     */
    public int getTotalPage() {
        return (int) (totalCount%pageSize==0?totalCount/pageSize:(totalCount/pageSize)+1);
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }



    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
