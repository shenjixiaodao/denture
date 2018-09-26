package com.yuzi.denture.domain;

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
    /**
     * 牙位号(position),
     * 使用","分隔各个牙位号，个数等于 {@param number}；
     * 牙位号格式: 半位[a|b|c|d]-编号[1-8], eg: a-2（表示左上半第2号）
     */
    private String positions;
    //义齿色号(colorNo): Enum
    private String colorNo;
    //创建日期(createdDate):
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
    //工序组操作记录(precedureGroups):
    private List<ProcedureGroup> procedureGroups;


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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DentureType getType() {
        return type;
    }

    public void setType(DentureType type) {
        this.type = type;
    }

    public SpecType getSpecification() {
        return specification;
    }

    public void setSpecification(SpecType specification) {
        this.specification = specification;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getPositions() {
        return positions;
    }

    public void setPositions(String positions) {
        this.positions = positions;
    }

    public String getColorNo() {
        return colorNo;
    }

    public void setColorNo(String colorNo) {
        this.colorNo = colorNo;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public FactoryUser getModelInspector() {
        return modelInspector;
    }

    public void setModelInspector(FactoryUser modelInspector) {
        this.modelInspector = modelInspector;
    }

    public Date getModelInspectionDate() {
        return modelInspectionDate;
    }

    public void setModelInspectionDate(Date modelInspectionDate) {
        this.modelInspectionDate = modelInspectionDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public ReviewResult getProReview() {
        return proReview;
    }

    public void setProReview(ReviewResult proReview) {
        this.proReview = proReview;
    }

    public Date getProReviewDate() {
        return proReviewDate;
    }

    public void setProReviewDate(Date proReviewDate) {
        this.proReviewDate = proReviewDate;
    }

    public ReviewResult getQuaReview() {
        return quaReview;
    }

    public void setQuaReview(ReviewResult quaReview) {
        this.quaReview = quaReview;
    }

    public Date getQuaReviewDate() {
        return quaReviewDate;
    }

    public void setQuaReviewDate(Date quaReviewDate) {
        this.quaReviewDate = quaReviewDate;
    }

    public List<AppliedIngredient> getAppliedIngredients() {
        return appliedIngredients;
    }

    public void setAppliedIngredients(List<AppliedIngredient> appliedIngredients) {
        this.appliedIngredients = appliedIngredients;
    }

    public List<ProcedureGroup> getProcedureGroups() {
        return procedureGroups;
    }

    public void setProcedureGroups(List<ProcedureGroup> procedureGroups) {
        this.procedureGroups = procedureGroups;
    }

    @Override
    public String toString() {
        return "Denture{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", specification=" + specification +
                ", clinic=" + clinic +
                ", factory=" + factory +
                ", number=" + number +
                ", positions='" + positions + '\'' +
                ", colorNo='" + colorNo + '\'' +
                ", createdDate=" + createdDate +
                ", modelInspector=" + modelInspector +
                ", modelInspectionDate=" + modelInspectionDate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", proReview=" + proReview +
                ", proReviewDate=" + proReviewDate +
                ", quaReview=" + quaReview +
                ", quaReviewDate=" + quaReviewDate +
                ", appliedIngredients=" + appliedIngredients +
                ", procedureGroups=" + procedureGroups +
                '}';
    }
}
