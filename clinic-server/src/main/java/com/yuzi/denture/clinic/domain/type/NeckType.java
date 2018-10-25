package com.yuzi.denture.clinic.domain.type;

public enum NeckType {
    AnJianTai("按肩台"),
    Top("龈上边缘"),
    Below("龈下边缘");
    private String text;
    NeckType(String text) {
        this.text = text;
    }
    public String text() {
        return this.text;
    }
    public static NeckType typeOf(String type) {
        for(NeckType result : NeckType.values()){
            if(result.name().toLowerCase().equals(type.toLowerCase()))
                return result;
        }
        throw new IllegalArgumentException("未知类型");
    }
}
