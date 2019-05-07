package com.yuzi.denture.domain.aggregate;

import java.util.Date;

public class ProductTypeStatistic {
    String productName;
    String productCode;
    Long factoryId;
    String productType;
    Date dentureCreatedDate;
    String dentureStatus;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Date getDentureCreatedDate() {
        return dentureCreatedDate;
    }

    public void setDentureCreatedDate(Date dentureCreatedDate) {
        this.dentureCreatedDate = dentureCreatedDate;
    }

    public String getDentureStatus() {
        return dentureStatus;
    }

    public void setDentureStatus(String dentureStatus) {
        this.dentureStatus = dentureStatus;
    }
}
