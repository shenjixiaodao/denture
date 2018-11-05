package com.yuzi.denture.domain.type;

import org.springframework.util.StringUtils;

public enum FieldType {
    DaMaAn("大马鞍"),
    PianCe("偏侧型"),
    ZiDan("子弹型"),
    XiaoMaAn("小马鞍"),
    WeiSheng("卫生型"),
    ShenRu("深入型");
    private String text;
    FieldType(String text) {
        this.text = text;
    }
    public String text() {
        return this.text;
    }
    public static FieldType typeOf(String type) {
        if(StringUtils.isEmpty(type))
            return null;
        for(FieldType result : FieldType.values()){
            if(result.name().toLowerCase().equals(type.toLowerCase()))
                return result;
        }
        throw new IllegalArgumentException("未知类型");
    }
}
