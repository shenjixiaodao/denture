package com.yuzi.denture.api.vo.base;


import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


public class WebResult<T> implements Serializable{

    @ApiModelProperty(value="网络状态码")
    int status = 200;

    @ApiModelProperty(value="错误码")
    int errorCode;

    String errorMessage;

    T data;

    public WebResult() {}

    public WebResult(T data){
        this(ResponseCode.SUCCESS,data);
    }

    public WebResult(ResponseCode responseCode,T data){
        this.errorCode = responseCode.getCode();
        this.errorMessage = responseCode.getMsg();
        this.data = data;
    }

    public static WebResult commonRes(Object data){
        return new WebResult(ResponseCode.SUCCESS,data);
    }

    public static WebResult commonRes(ResponseCode responseCode,Object data){
        return new WebResult(responseCode,data);
    }

    public static WebResult success() {
        WebResult result = new WebResult(ResponseCode.SUCCESS, null);
        return result;
    }

    public boolean isSuccess() {
        return this.errorCode == ResponseCode.SUCCESS.getCode();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }
}
