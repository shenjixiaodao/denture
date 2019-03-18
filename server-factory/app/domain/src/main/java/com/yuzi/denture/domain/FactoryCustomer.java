package com.yuzi.denture.domain;


import java.util.Date;
import java.util.List;

public class FactoryCustomer {
    
    //id: Long
    Long id;
    //factoryId: Long
    Long factoryId;
    //clinic
    Clinic clinic;
    //price sheet
    List<PriceSheet> priceSheet;
    //salesmanId: Long
    Long salesmanId;
    //createdDate: Date
    Date createdDate;
    Integer fixedGuarantee;
    Integer mobilizableGuarantee;
    // 0:发起请求 1:同意  -1:拒绝  -2:工厂自己添加:
    Byte isValid;

    public FactoryCustomer(Long factoryId, Long clinicId, Long salesmanId) {
        this.factoryId = factoryId;
        this.clinic = new Clinic(clinicId);
        this.salesmanId = salesmanId;
        this.createdDate = new Date();
        this.isValid = 0;
    }

    public FactoryCustomer(Long factoryId, Long salesmanId) {
        this.factoryId = factoryId;
        this.salesmanId = salesmanId;
        this.createdDate = new Date();
        this.isValid = -2;
    }

    public FactoryCustomer() {
    }

    public List<PriceSheet> getPriceSheet() {
        return priceSheet;
    }

    public void setPriceSheet(List<PriceSheet> priceSheet) {
        this.priceSheet = priceSheet;
    }

    public Integer getFixedGuarantee() {
        return fixedGuarantee;
    }

    public void setFixedGuarantee(Integer fixedGuarantee) {
        this.fixedGuarantee = fixedGuarantee;
    }

    public Integer getMobilizableGuarantee() {
        return mobilizableGuarantee;
    }

    public void setMobilizableGuarantee(Integer mobilizableGuarantee) {
        this.mobilizableGuarantee = mobilizableGuarantee;
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

    public Byte getValid() {
        return isValid;
    }

    public void setValid(Byte valid) {
        isValid = valid;
    }
}
