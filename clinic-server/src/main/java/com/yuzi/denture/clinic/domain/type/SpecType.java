package com.yuzi.denture.clinic.domain.type;

public enum SpecType {
    GuGe("钴铬合金"),
    GuiJinShuDanGuan("贵金属单冠"),
    LianGuan("连冠（桥、嵌体、贴面）"),
    ErYangHuaGao("二氧化锆"),
    YangHuaGao("氧化锆"),
    ErYangHuaGuiGuan("二氧化硅冠（桥、嵌体、贴面)"),
    NieGeHeJinGuan("镍铬合金冠"),
    NieGeHeJinQiao("镍铬合金桥"),
    WanZhiZhiJiaKeZhai("弯制支架可摘局部义齿"),
    ShuZhiJiTuoQuanKou("树脂基托全口义齿"),
    Other("其他");
    private String text;
    SpecType(String text) {
        this.text = text;
    }
    public String text() {
        return this.text;
    }
    public static SpecType typeOf(String type) {
        for(SpecType result : SpecType.values()){
            if(result.name().toLowerCase().equals(type.toLowerCase()))
                return result;
        }
        throw new IllegalArgumentException("未知义齿规格类型");
    }
}
