package com.yuzi.denture.domain.type;

import org.springframework.util.StringUtils;

public enum BorderType {
    Plan("面接"),
    Point("点接"),
    Normal("正常");
    private String text;
    BorderType(String text) {
        this.text = text;
    }
    public String text() {
        return this.text;
    }
    public static BorderType typeOf(String type) {
        if(StringUtils.isEmpty(type))
            return null;
        for(BorderType result : BorderType.values()){
            if(result.name().toLowerCase().equals(type.toLowerCase()))
                return result;
        }
        throw new IllegalArgumentException("未知类型");
    }
}
