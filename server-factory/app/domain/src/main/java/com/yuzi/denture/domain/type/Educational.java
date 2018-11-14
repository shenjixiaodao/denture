package com.yuzi.denture.domain.type;

import org.springframework.util.StringUtils;

/**
 * Created by liyou on 2018/11/14.
 */
public enum Educational {

    Senior("高中/中专"), Junior("大专"), Bachelor("本科"), Master("硕士");

    private String text;
    Educational(String text) {
        this.text = text;
    }
    public String text() {
        return this.text;
    }
    public static Educational typeOf(String type) {
        if(StringUtils.isEmpty(type))
            return null;
        for(Educational result : Educational.values()){
            if(result.name().toLowerCase().equals(type.toLowerCase()))
                return result;
        }
        throw new IllegalArgumentException("未知学历");
    }
}
