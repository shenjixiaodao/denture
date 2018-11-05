package com.yuzi.denture.domain.type;

import org.springframework.util.StringUtils;

public enum OuterCrownType {
    SheCe("舌侧"),
    JinTop("全金属边"),
    ThreeQuarter("松"),
    SC_TQ("舌侧金属3/4颌侧"),
    JinBelow("金属颌侧"),
    AllCi("舌侧全瓷边");

    private String text;
    OuterCrownType(String text) {
        this.text = text;
    }
    public String text() {
        return this.text;
    }
    public static OuterCrownType typeOf(String type) {
        if(StringUtils.isEmpty(type))
            return null;
        for(OuterCrownType result : OuterCrownType.values()){
            if(result.name().toLowerCase().equals(type.toLowerCase()))
                return result;
        }
        throw new IllegalArgumentException("未知类型");
    }
}
