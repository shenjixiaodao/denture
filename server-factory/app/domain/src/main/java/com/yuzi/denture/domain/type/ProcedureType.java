package com.yuzi.denture.domain.type;

public enum ProcedureType {
    ShiGao("石膏组"),
    LaXing("蜡型组"),
    CheJin("车金组"),
    ChongJiao("充胶组"),
    ShangCi("上瓷组"),
    CheCi("车瓷组");
    private String text;
    ProcedureType(String text) {
        this.text = text;
    }
    public String text() {
        return this.text;
    }
    public static ProcedureType typeOf(String type) {
        for(ProcedureType result : ProcedureType.values()){
            if(result.name().toLowerCase().equals(type.toLowerCase()))
                return result;
        }
        throw new IllegalArgumentException("未知组类型");
    }
}
