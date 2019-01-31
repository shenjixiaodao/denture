package com.yuzi.denture.domain.response;


public enum ResponseCode {

    SUCCESS("0","SUCCESS") ,FAILURE("1","内部系统错误") ,TOKEN_EXPIRED("2","登录过期")
    ,Duplicate_Code("3","重复代码"), REQ_PARAM_ERROR("9999","请求参数错误");
    private final String code;
    private final String msg;

    ResponseCode(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public String code(){
        return this.code;
    }

    public String msg(){
        return this.msg;
    }
}
