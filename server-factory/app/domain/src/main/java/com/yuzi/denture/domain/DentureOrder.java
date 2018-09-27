package com.yuzi.denture.domain;

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
    //订单状态(status):
    private Status status;
    //牙模物流信息(modelDeliveryInfo):
    private DeliveryInfo modelDeliveryInfo;
    //义齿物流信息(dentureDeliveryInfo):
    private DeliveryInfo dentureDeliveryInfo;
    //接收日期(receivedDate):
    private Date receivedDate;
    //安装日期(InstalledDate): Date
    private Date installedDate;
    //医生(dentist):
    private ClinicUser dentist;
    //患者(patient):
    private Patient patient;
    //记录人(recorder):
    private ClinicUser recorder;
    //备注(comment):
    private String comment;

    public DentureOrder(String dentureId, Long clinicId, Long factoryId,
                        Long dentistId, String comment) {
        this.dentureId = dentureId;
        this.clinic = new Clinic(clinicId);
        this.factory = new Factory(factoryId);
        this.dentist = new ClinicUser(dentistId);
        this.comment = comment;
        this.status = Status.Created;
        this.createdDate = new Date();
    }

    public DentureOrder() {
    }

    public enum Status {
        Created("创建"),
        Paid("支付定金"),
        Accepted("已接单"),
        Rejected("已拒单"),
        Making("制作中"),
        Inspecting("检测中"),
        PackAndClr("包装消毒"),
        Released("出厂"),
        Return("返厂"),
        Remaking("重制"),
        Installed("安装完成");
        private String text;
        Status(String text) {
            this.text = text;
        }
        public String text() {
            return this.text;
        }

    }

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public ClinicUser getDentist() {
        return dentist;
    }

    public void setDentist(ClinicUser dentist) {
        this.dentist = dentist;
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

    @Override
    public String toString() {
        return "DentureOrder{" +
                "id='" + id + '\'' +
                ", dentureId='" + dentureId + '\'' +
                ", clinic=" + clinic +
                ", factory=" + factory +
                ", createdDate=" + createdDate +
                ", payAmount=" + payAmount +
                ", status=" + status +
                ", modelDeliveryInfo=" + modelDeliveryInfo +
                ", dentureDeliveryInfo=" + dentureDeliveryInfo +
                ", receivedDate=" + receivedDate +
                ", installedDate=" + installedDate +
                ", dentist=" + dentist +
                ", patient=" + patient +
                ", recorder=" + recorder +
                ", comment='" + comment + '\'' +
                '}';
    }
}
