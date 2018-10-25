package com.yuzi.denture.clinic.domain.type;

public enum  InnerCrowType {
    Normal("正常"),
    Tight("紧"),
    Loose("松");

    private String text;
    InnerCrowType(String text) {
        this.text = text;
    }
    public String text() {
        return this.text;
    }
    public static InnerCrowType typeOf(String type) {
        for(InnerCrowType result : InnerCrowType.values()){
            if(result.name().toLowerCase().equals(type.toLowerCase()))
                return result;
        }
        throw new IllegalArgumentException("未知类型");
    }
}
