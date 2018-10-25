package com.yuzi.denture.clinic.domain.type;

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
        for(BiteLevel result : BiteLevel.values()){
            if(result.name().toLowerCase().equals(type.toLowerCase()))
                return result;
        }
        throw new IllegalArgumentException("未知类型");
    }
}
