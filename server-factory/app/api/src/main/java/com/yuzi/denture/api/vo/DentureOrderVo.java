package com.yuzi.denture.api.vo;

import java.math.BigDecimal;
import java.util.Date;

public class DentureOrderVo {
    //订单号
    private String id;
    //产品编号(dentureId):
    private String dentureId;
    //定制方(clinic):
    private ClinicVo clinic;
    //生产方(factory):
    private FactoryVo factory;
    //下单时间(createdDate):
    private Date createdDate;
    //支付定金(payAmount):
    private BigDecimal payAmount;
    //订单状态(status):
    private String status;
    //牙模物流信息(modelDeliveryInfo):
    private DeliveryInfoVo modelDeliveryInfo;
    //义齿物流信息(dentureDeliveryInfo):
    private DeliveryInfoVo dentureDeliveryInfo;
    //接收日期(receivedDate):
    private Date receivedDate;
    //安装日期(InstalledDate): Date
    private Date installedDate;
    //医生(dentist):
    private ClinicUserVo dentist;
    //患者(patient):
    private PatientVo patient;
    //记录人(recorder):
    private ClinicUserVo recorder;
    //备注(comment):
    private String comment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDentureId() {
        return dentureId;
    }

    public void setDentureId(String dentureId) {
        this.dentureId = dentureId;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DeliveryInfoVo getModelDeliveryInfo() {
        return modelDeliveryInfo;
    }

    public void setModelDeliveryInfo(DeliveryInfoVo modelDeliveryInfo) {
        this.modelDeliveryInfo = modelDeliveryInfo;
    }

    public DeliveryInfoVo getDentureDeliveryInfo() {
        return dentureDeliveryInfo;
    }

    public void setDentureDeliveryInfo(DeliveryInfoVo dentureDeliveryInfo) {
        this.dentureDeliveryInfo = dentureDeliveryInfo;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public Date getInstalledDate() {
        return installedDate;
    }

    public void setInstalledDate(Date installedDate) {
        this.installedDate = installedDate;
    }

    public ClinicUserVo getDentist() {
        return dentist;
    }

    public void setDentist(ClinicUserVo dentist) {
        this.dentist = dentist;
    }

    public PatientVo getPatient() {
        return patient;
    }

    public void setPatient(PatientVo patient) {
        this.patient = patient;
    }

    public ClinicUserVo getRecorder() {
        return recorder;
    }

    public void setRecorder(ClinicUserVo recorder) {
        this.recorder = recorder;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
