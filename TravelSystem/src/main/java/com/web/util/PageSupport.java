package com.web.util;

public class PageSupport {
	//当前页码
    private int currentPageNo = 1;

    //总数量(表)
    private int totalCount = 0;

    //页面容量
    private int pageSize = 0;

    //总页数
    private int totalPageCount = 1;

    public int getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo) {
        if (currentPageNo>0){
            this.currentPageNo = currentPageNo;
        }
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        if(totalCount > 0){
            this.totalCount = totalCount;
            this.setTotalPageCountByRs();
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if(pageSize>0){
            this.pageSize = pageSize;
        }
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public void setTotalPageCountByRs(){
        if(totalCount % pageSize == 0){
            this.totalPageCount = totalCount / pageSize;
        }else if(totalCount % pageSize > 0){
            this.totalPageCount = totalCount / pageSize + 1;
        }else {
            this.totalPageCount = 0;
        }
    }
}
