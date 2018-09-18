package com.yuzi.denture.domain.factory;

public enum  GroupType {
    ShiGao("石膏组"),
    LaXing("蜡型组"),
    CheJin("车金组"),
    ChongJiao("充胶组"),
    ShangCi("上瓷组"),
    CheCi("车瓷组"),
    Comprehensive("综合管理组"),
    Market("销售组"),
    Management("管理组");
    private String text;
    GroupType(String text) {
        this.text = text;
    }
    public String text() {
        return this.text;
    }
}
