package com.yuzi.denture.api.vo.base;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by shenjixiaodao on 2017/6/1.
 */
public class WebPageResult extends WebResult{

    @ApiModelProperty(value="当前页码")
    private int currentPage;
    @ApiModelProperty( value="每页记录数")
    private int pageSize;
    @ApiModelProperty( value="总记录数")
    private int totalSize;
    @ApiModelProperty(value="总页数")
    private int totalPage;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }
}
