package com.yuzi.denture.api.vo;

import io.swagger.annotations.ApiModelProperty;

public class DentureCriteriaVo {
    @ApiModelProperty(value="status=[Waiting(\"待审核\"), Doing(\"处理中\"), Done(\"已结束\")]", required = true)
    String status;
    Long factoryId;
    String dentureId;
    String patientName;
    String createdDate;
    String region;
    String clinicName;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public String getDentureId() {
        return dentureId;
    }

    public void setDentureId(String dentureId) {
        this.dentureId = dentureId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    @Override
    public String toString() {
        return "DentureCriteriaVo{" +
                "status='" + status + '\'' +
                ", factoryId=" + factoryId +
                ", dentureId='" + dentureId + '\'' +
                ", patientName='" + patientName + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", region='" + region + '\'' +
                ", clinicName='" + clinicName + '\'' +
                '}';
    }
}
