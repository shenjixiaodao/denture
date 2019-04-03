package com.yuzi.denture.api.vo.base;


import com.yuzi.denture.api.vo.*;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by liyou on 2018/9/19.
 */
public class DentureVo {
    //序号
    private int index;
    //产品编号(id): String
    @ApiModelProperty(value="义齿ID")
    private String id;
    //产品名称(type):
    @ApiModelProperty(value="义齿类型")
    private String type;
    //规格(specification):
    @ApiModelProperty(value="义齿规格")
    private String specification;
    //定制方(clinic):
    @ApiModelProperty(value="定制方", dataType = "com.yuzi.denture.api.vo.ClinicVo")
    private ClinicVo clinic;
    @ApiModelProperty(value="医生备注")
    private String comment;
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
    String biteLevel;
    String borderType;
    String fieldType;
    String innerCrownType;
    String neckType;
    String outerCrownType;
    String paddingType;
    String requirement;
    BigDecimal basePrice;
    BigDecimal factoryPrice;
    Date estimatedDuration;
    String patientName;
    String deliveryId;
    String deliveryDate;
    String deliveryPerson;
    Long salesmanId;
    String salesman;
    String stage;
    Long dentistId;
    String dentist;
    String status;
    Date receivedDate;
    Long creatorId;
    String creator;
    InspectionReportVo inspection;

    public InspectionReportVo getInspection() {
        return inspection;
    }

    public void setInspection(InspectionReportVo inspection) {
        this.inspection = inspection;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getDentistId() {
        return dentistId;
    }

    public void setDentistId(Long dentistId) {
        this.dentistId = dentistId;
    }

    public String getDentist() {
        return dentist;
    }

    public void setDentist(String dentist) {
        this.dentist = dentist;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryPerson() {
        return deliveryPerson;
    }

    public void setDeliveryPerson(String deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
    }

    public Long getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(Long salesmanId) {
        this.salesmanId = salesmanId;
    }

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public Date getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(Date estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public BigDecimal getFactoryPrice() {
        return factoryPrice;
    }

    public void setFactoryPrice(BigDecimal factoryPrice) {
        this.factoryPrice = factoryPrice;
    }

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public List<AppliedIngredientVo> getAppliedIngredients() {
        return appliedIngredients;
    }

    public void setAppliedIngredients(List<AppliedIngredientVo> appliedIngredients) {
        this.appliedIngredients = appliedIngredients;
    }

    public List<ProcedureGroupVo> getProcedureGroups() {
        return procedureGroups;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setProcedureGroups(List<ProcedureGroupVo> procedureGroups) {
        this.procedureGroups = procedureGroups;
    }

    public String getBiteLevel() {
        return biteLevel;
    }

    public void setBiteLevel(String biteLevel) {
        this.biteLevel = biteLevel;
    }

    public String getBorderType() {
        return borderType;
    }

    public void setBorderType(String borderType) {
        this.borderType = borderType;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getInnerCrownType() {
        return innerCrownType;
    }

    public void setInnerCrownType(String innerCrownType) {
        this.innerCrownType = innerCrownType;
    }

    public String getNeckType() {
        return neckType;
    }

    public void setNeckType(String neckType) {
        this.neckType = neckType;
    }

    public String getOuterCrownType() {
        return outerCrownType;
    }

    public void setOuterCrownType(String outerCrownType) {
        this.outerCrownType = outerCrownType;
    }

    public String getPaddingType() {
        return paddingType;
    }

    public void setPaddingType(String paddingType) {
        this.paddingType = paddingType;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }
}
