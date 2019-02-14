package com.yuzi.denture.domain.exception;


import com.yuzi.denture.domain.response.ResponseCode;

public class CodeException extends RuntimeException{

    private final ResponseCode code;

    public CodeException(ResponseCode code) {
        this.code = code;
    }

    public ResponseCode getCode() {
        return code;
    }
}
