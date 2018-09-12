package com.yuzi.denture.domain.factory;

import java.util.Date;
import java.util.List;

public class Denture {
    //产品编号(id): String
    private String id;
    //产品名称(type):
    private DentureType type;
    //规格(specification):
    private SpecType specification;
    //定制方(clinic):
    private Clinic clinic;
    //生产方(factory):
    private Factory factory;
    //数量(number):
    private Integer number;
    //义齿色号(colorNo): Enum
    private ColorNo colorNo;
    //牙位号(position): Enum
    private Position position;
    //接收日期(createdDate):
    private Date createdDate;
    //查验人(modelInspector):
    private FactoryUser modelInspector;
    //查验日期(modelInspectionDate):
    private Date modelInspectionDate;
    //生产开始日期(startDate):
    private Date startDate;
    //生产截止日期(endDate):
    private Date endDate;
    //生产部负责人审核(proReview): Review
    private ReviewResult proReview;
    //生产部负责人审核日期(proReviewDate):
    private Date proReviewDate;
    //质量部负责人审核(quaReview): Review
    private ReviewResult quaReview;
    //质量部负责人审核日期(quaReviewDate):
    private Date quaReviewDate;
    //辅料申请记录(appliedIngredients): List
    private List<AppliedIngredient> appliedIngredients;
    //工序组操作记录(precedureGroups): List



    public enum ColorNo {

    }

    public enum Position {

    }
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
    }
    public enum  DentureType {
        Fixed("定制式固定义齿"), Mobilizable("定制式活动义齿");
        private String text;
        DentureType(String text) {
            this.text = text;
        }
        public String text() {
            return this.text;
        }
    }
    public Denture() {
    }
}
