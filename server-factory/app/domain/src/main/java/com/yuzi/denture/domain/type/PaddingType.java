package com.yuzi.denture.domain.type;

import org.springframework.util.StringUtils;

public enum PaddingType {
    PiDuiYao("批对咬"),
    PiJiYa("批基牙"),
    ZuoYaoJin("做咬金"),
    LouDianJin("露点金"),
    Unknown("待问");

    private String text;
    PaddingType(String text) {
        this.text = text;
    }
    public String text() {
        return this.text;
    }
    public static PaddingType typeOf(String type) {
        if(StringUtils.isEmpty(type))
            return null;
        for(PaddingType result : PaddingType.values()){
            if(result.name().toLowerCase().equals(type.toLowerCase()))
                return result;
        }
        throw new IllegalArgumentException("未知类型");
    }
}
