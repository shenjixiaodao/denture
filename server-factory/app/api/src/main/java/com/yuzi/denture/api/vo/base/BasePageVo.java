package com.yuzi.denture.api.vo.base;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by shenjixiaodao on 2017/6/1.
 */
public abstract class BasePageVo {
    @ApiModelProperty(value="当前页码",  required = true , dataType = "int")
    private int currentPage;
    @ApiModelProperty( value="每页记录数", required = true, dataType = "int")
    private int pageSize;

    public BasePageVo() {
        pageSize = 10;
        currentPage = 0;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getOffset(){
        return this.pageSize * this.currentPage;
    }

    @Override
    public String toString() {
        return "BasePageVo{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                '}';
    }
}
