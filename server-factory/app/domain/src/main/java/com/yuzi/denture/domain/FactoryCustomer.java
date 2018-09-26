package com.yuzi.denture.domain;


import java.util.Date;

public class FactoryCustomer {
    
    //id: Long
    Long id;
    //factoryId: Long
    Long factoryId;
    //clinic
    Clinic clinic;
    //salesmanId: Long
    Long salesmanId;
    //createdDate: Date
    Date createdDate;
    //isValid: Boolean
    Boolean isValid;

    public FactoryCustomer(Long factoryId, Long clinicId, Long salesmanId) {
        this.factoryId = factoryId;
        this.clinic = new Clinic(clinicId);
        this.salesmanId = salesmanId;
        this.createdDate = new Date();
        this.isValid = Boolean.FALSE;
    }

    public FactoryCustomer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public Long getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(Long salesmanId) {
        this.salesmanId = salesmanId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }
}
