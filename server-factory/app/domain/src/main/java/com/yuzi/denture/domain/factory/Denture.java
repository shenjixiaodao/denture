package com.yuzi.denture.domain.factory;

public class Denture {
    private String id;
    private String orderId;
    private Long factoryId;

    public Denture(String orderId, Long factoryId) {
        this.orderId = orderId;
        this.factoryId = factoryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public Denture() {
    }
}
