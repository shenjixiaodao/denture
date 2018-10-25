package com.yuzi.denture.clinic.domain.type;

public enum OuterCrowType {
    SheCe("舌侧"),
    JinTop("全金属边"),
    ThreeQuarter("松"),
    SC_TQ("舌侧金属3/4颌侧"),
    JinBelow("金属颌侧"),
    AllCi("舌侧全瓷边");

    private String text;
    OuterCrowType(String text) {
        this.text = text;
    }
    public String text() {
        return this.text;
    }
    public static OuterCrowType typeOf(String type) {
        for(OuterCrowType result : OuterCrowType.values()){
            if(result.name().toLowerCase().equals(type.toLowerCase()))
                return result;
        }
        throw new IllegalArgumentException("未知类型");
    }
}
