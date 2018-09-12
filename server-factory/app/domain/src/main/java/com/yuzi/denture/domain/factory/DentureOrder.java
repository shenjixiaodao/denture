package com.yuzi.denture.domain.factory;

import java.math.BigDecimal;
import java.util.Date;

public class DentureOrder {

    //订单号
    private String id;
    //产品编号(dentureId):
    private String dentureId;
    //定制方(clinic):
    private Clinic clinic;
    //生产方(factory):
    private Factory factory;
    //下单时间(createdDate):
    private Date createdDate;
    //支付定金(payAmount):
    private BigDecimal payAmount;
    //订单状态(status): Status
    //牙模物流信息(modelDeliveryInfo):
    private DeliveryInfo modelDeliveryInfo;
    //义齿物流信息(dentureDeliveryInfo):
    private DeliveryInfo dentureDeliveryInfo;
    //接收日期(receivedDate):
    private Date receivedDate;
    //安装日期(InstalledDate): Date
    private Date installedDate;
    //医生(dentist):
    private ClinicUser clinicUser;
    //患者(patient):
    private Patient patient;
    //记录人(recorder):
    private ClinicUser recorder;
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

    public DeliveryInfo getModelDeliveryInfo() {
        return modelDeliveryInfo;
    }

    public void setModelDeliveryInfo(DeliveryInfo modelDeliveryInfo) {
        this.modelDeliveryInfo = modelDeliveryInfo;
    }

    public DeliveryInfo getDentureDeliveryInfo() {
        return dentureDeliveryInfo;
    }

    public void setDentureDeliveryInfo(DeliveryInfo dentureDeliveryInfo) {
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

    public ClinicUser getClinicUser() {
        return clinicUser;
    }

    public void setClinicUser(ClinicUser clinicUser) {
        this.clinicUser = clinicUser;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public ClinicUser getRecorder() {
        return recorder;
    }

    public void setRecorder(ClinicUser recorder) {
        this.recorder = recorder;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
