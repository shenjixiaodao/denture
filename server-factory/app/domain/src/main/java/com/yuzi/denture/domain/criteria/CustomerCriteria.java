package com.yuzi.denture.domain.criteria;

import com.yuzi.denture.domain.common.PageCriteria;

public class CustomerCriteria extends PageCriteria {
    Long salesmanId;
    Long factoryId;

    public Long getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(Long salesmanId) {
        this.salesmanId = salesmanId;
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }
}
