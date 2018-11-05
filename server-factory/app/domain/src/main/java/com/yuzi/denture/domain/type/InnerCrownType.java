package com.yuzi.denture.domain.type;

import org.springframework.util.StringUtils;

public enum InnerCrownType {
    Normal("正常"),
    Tight("紧"),
    Loose("松");

    private String text;
    InnerCrownType(String text) {
        this.text = text;
    }
    public String text() {
        return this.text;
    }
    public static InnerCrownType typeOf(String type) {
        if(StringUtils.isEmpty(type))
            return null;
        for(InnerCrownType result : InnerCrownType.values()){
            if(result.name().toLowerCase().equals(type.toLowerCase()))
                return result;
        }
        throw new IllegalArgumentException("未知类型");
    }
}
