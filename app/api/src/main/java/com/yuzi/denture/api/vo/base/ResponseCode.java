package com.yuzi.denture.api.vo.base;

/**
 * @Auther: LYZH
 * @Date: 2018/6/22 10:58
 * @Description:
 *
 * 以模块名开头 如USER_**
 * 错误码为四位，前两位为模块编号(99-common 10-resource 20-storage 30-user 40-web 50-smart_home)
 *
 */
public enum ResponseCode {

    SUCCESS(0,"SUCCESS")

    //========================== Web Module
    ,SMInnerError(5001,"内部系统错误")
    ,SMOuterError(5002,"外部系统未知错误")
    ,SMTimeout(5003,"网络超时")

    ,REQ_PARAM_ERROR(9901,"请求参数错误")
    ;

    private final int code;
    private final String msg;

    ResponseCode(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode(){
        return this.code;
    }

    public String getMsg(){
        return this.msg;
    }
}
