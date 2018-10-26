package com.yuzi.denture.clinic.domain.type;

import org.springframework.util.StringUtils;

public enum  DentureType {
    Fixed("定制式固定义齿"), Mobilizable("定制式活动义齿");
    private String text;
    DentureType(String text) {
        this.text = text;
    }
    public String text() {
        return this.text;
    }
    public static DentureType typeOf(String type) {
        if(StringUtils.isEmpty(type))
            return null;
        for(DentureType result : DentureType.values()){
            if(result.name().toLowerCase().equals(type.toLowerCase()))
                return result;
        }
        throw new IllegalArgumentException("未知义齿类型");
    }
}
