package com.yuzi.denture.domain.criteria;

import com.yuzi.denture.domain.common.PageCriteria;

public class DentureCriteria extends PageCriteria {
    Long factoryId;
    Long clinicId;
    String dentureId;
    String patientName;
    String createdDate;
    String region;
    String clinicName;
    // All, Invalid, Created, Producing, Delivered, Remaking
    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
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
}
