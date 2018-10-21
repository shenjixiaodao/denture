package com.yuzi.denture.domain;

import java.util.Date;

/**
 * Created by liyou on 2018/10/21.
 */
public class IngredientPurchaseRecord {
    private Long id;
    private Long ingredientId;
    private Long supplierId;
    private Double number;
    private Date gmtCreated;
    private Date gmtModified;

    public IngredientPurchaseRecord(Long ingredientId, Long supplierId, Double number) {
        this.ingredientId = ingredientId;
        this.supplierId = supplierId;
        this.number = number;
        this.gmtCreated = new Date();
        this.gmtModified = new Date();
    }

    public IngredientPurchaseRecord() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
