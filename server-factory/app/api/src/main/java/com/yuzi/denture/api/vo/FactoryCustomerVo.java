package com.yuzi.denture.api.vo;



import java.util.Date;

public class FactoryCustomerVo {

    //id: Long
    Long id;
    //factoryId: Long
    Long factoryId;
    //clinic
    ClinicVo clinic;
    //salesmanId: Long
    Long salesmanId;
    //createdDate: Date
    Date createdDate;
    //isValid: Boolean
    Byte isValid;
    Integer mobilizableGuarantee;
    Integer fixedGuarantee;

    public FactoryCustomerVo() {
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

    public ClinicVo getClinic() {
        return clinic;
    }

    public void setClinic(ClinicVo clinic) {
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

    public Byte getValid() {
        return isValid;
    }

    public void setValid(Byte valid) {
        isValid = valid;
    }

    public Integer getMobilizableGuarantee() {
        return mobilizableGuarantee;
    }

    public void setMobilizableGuarantee(Integer mobilizableGuarantee) {
        this.mobilizableGuarantee = mobilizableGuarantee;
    }

    public Integer getFixedGuarantee() {
        return fixedGuarantee;
    }

    public void setFixedGuarantee(Integer fixedGuarantee) {
        this.fixedGuarantee = fixedGuarantee;
    }
}
