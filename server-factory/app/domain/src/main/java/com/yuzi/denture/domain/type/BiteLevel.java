package com.yuzi.denture.domain.type;

import org.springframework.util.StringUtils;

public enum BiteLevel {
    YaoMi("咬密"),
    QingYao("轻咬"),
    BuYao("不咬");

    private String text;
    BiteLevel(String text) {
        this.text = text;
    }
    public String text() {
        return this.text;
    }
    public static BiteLevel typeOf(String type) {
        if(StringUtils.isEmpty(type))
            return null;
        for(BiteLevel result : BiteLevel.values()){
            if(result.name().toLowerCase().equals(type.toLowerCase()))
                return result;
        }
        throw new IllegalArgumentException("未知类型");
    }
}
