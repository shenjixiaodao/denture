package com.yuzi.denture.api.vo.base;


import com.yuzi.denture.api.vo.*;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.util.List;

/**
 * Created by liyou on 2018/9/19.
 */
public class DentureVo {
    //产品编号(id): String
    @ApiModelProperty(value="义齿ID")
    private String id;
    //产品名称(type):
    @ApiModelProperty(value="义齿类型")
    private String type;
    //规格(specification):
    @ApiModelProperty(value="义齿规格\n" +
            "       [GuGe(\"钴铬合金\"),\n" +
            "        GuiJinShuDanGuan(\"贵金属单冠\"),\n" +
            "        LianGuan(\"连冠（桥、嵌体、贴面）\"),\n" +
            "        ErYangHuaGao(\"二氧化锆\"),\n" +
            "        YangHuaGao(\"氧化锆\"),\n" +
            "        ErYangHuaGuiGuan(\"二氧化硅冠（桥、嵌体、贴面)\"),\n" +
            "        NieGeHeJinGuan(\"镍铬合金冠\"),\n" +
            "        NieGeHeJinQiao(\"镍铬合金桥\"),\n" +
            "        WanZhiZhiJiaKeZhai(\"弯制支架可摘局部义齿\"),\n" +
            "        ShuZhiJiTuoQuanKou(\"树脂基托全口义齿\"),\n" +
            "        Other(\"其他\")]")
    private String specification;
    //定制方(clinic):
    @ApiModelProperty(value="定制方", dataType = "com.yuzi.denture.api.vo.ClinicVo")
    private ClinicVo clinic;
    //生产方(factory):
    @ApiModelProperty(value="生产方", dataType = "com.yuzi.denture.api.vo.FactoryVo")
    private FactoryVo factory;
    //数量(number):
    @ApiModelProperty(value="定制数量")
    private Integer number;
    @ApiModelProperty(value="牙位号: 使用\",\"分隔各个牙位号;牙位号格式: 半位[a|b|c|d]-编号[1-8], eg: a-2（表示左上半第2号）")
    private String positions;
    //义齿色号(colorNo)
    @ApiModelProperty(value="义齿色号")
    private String colorNo;
    //创建日期(createdDate):
    @ApiModelProperty(value="创建日期")
    private Date createdDate;
    //查验人(modelInspector):
    @ApiModelProperty(value="查验人", dataType = "com.yuzi.denture.api.vo.FactoryUserVo")
    private FactoryUserVo modelInspector;
    //查验日期(modelInspectionDate):
    @ApiModelProperty(value="查验日期")
    private Date modelInspectionDate;
    //生产开始日期(startDate):
    @ApiModelProperty(value="生产开始日期")
    private Date startDate;
    //生产截止日期(endDate):
    @ApiModelProperty(value="生产截止日期")
    private Date endDate;
    //生产部负责人审核(proReview): Review
    @ApiModelProperty(value="生产部负责人审核")
    private String proReview;
    //生产部负责人审核日期(proReviewDate):
    @ApiModelProperty(value="生产部负责人审核日期")
    private Date proReviewDate;
    //质量部负责人审核(quaReview): Review
    @ApiModelProperty(value="质量部负责人审核")
    private String quaReview;
    //质量部负责人审核日期(quaReviewDate):
    @ApiModelProperty(value="质量部负责人审核日期")
    private Date quaReviewDate;
    //辅料申请记录(appliedIngredients): List
    @ApiModelProperty(value="辅料申请记录", dataType = "com.yuzi.denture.api.vo.AppliedIngredientVo")
    private List<AppliedIngredientVo> appliedIngredients;
    //工序组操作记录(precedureGroups):
    @ApiModelProperty(value="precedureGroups", dataType = "com.yuzi.denture.api.vo.ProcedureGroupVo")
    private List<ProcedureGroupVo> procedureGroups;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public ClinicVo getClinic() {
        return clinic;
    }

    public void setClinic(ClinicVo clinic) {
        this.clinic = clinic;
    }

    public FactoryVo getFactory() {
        return factory;
    }

    public void setFactory(FactoryVo factory) {
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

    public FactoryUserVo getModelInspector() {
        return modelInspector;
    }

    public void setModelInspector(FactoryUserVo modelInspector) {
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

    public String getProReview() {
        return proReview;
    }

    public void setProReview(String proReview) {
        this.proReview = proReview;
    }

    public Date getProReviewDate() {
        return proReviewDate;
    }

    public void setProReviewDate(Date proReviewDate) {
        this.proReviewDate = proReviewDate;
    }

    public String getQuaReview() {
        return quaReview;
    }

    public void setQuaReview(String quaReview) {
        this.quaReview = quaReview;
    }

    public Date getQuaReviewDate() {
        return quaReviewDate;
    }

    public void setQuaReviewDate(Date quaReviewDate) {
        this.quaReviewDate = quaReviewDate;
    }
}
