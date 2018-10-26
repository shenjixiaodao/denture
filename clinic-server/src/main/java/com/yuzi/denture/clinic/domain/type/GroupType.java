package com.yuzi.denture.clinic.domain.type;

import org.springframework.util.StringUtils;

public enum  GroupType {
    ShiGao("石膏组"),
    LaXing("蜡型组"),
    CheJin("车金组"),
    ChongJiao("充胶组"),
    ShangCi("上瓷组"),
    CheCi("车瓷组"),
    Comprehensive("综合管理组"),
    Market("销售组"),
    Management("管理组"),
    Other("其他");
    private String text;
    GroupType(String text) {
        this.text = text;
    }
    public String text() {
        return this.text;
    }
    public static GroupType typeOf(String type) {
        if(StringUtils.isEmpty(type))
            return null;
        for(GroupType result : GroupType.values()){
            if(result.name().toLowerCase().equals(type.toLowerCase()))
                return result;
        }
        throw new IllegalArgumentException("未知组类型");
    }
}
