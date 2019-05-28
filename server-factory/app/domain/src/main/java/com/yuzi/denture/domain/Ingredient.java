package com.yuzi.denture.domain;

import java.util.List;

public class Ingredient {

    private Long id;
    private String name;
    private Double balance;
    private Long factoryId;
    String type;
    String code;
    String unit;
    String spec;
    String subSpec;
    String equalityRateRange;
    private List<IngredientPurchaseRecord> purchaseRecords;
    List<AppliedIngredient> appliedIngredients;

    public Ingredient(String name,Long factoryId) {
        this.name = name;
        this.balance = 0.0;
        this.factoryId = factoryId;
    }

    public Ingredient(Long id) {
        this.id = id;
    }

    public Ingredient() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getSubSpec() {
        return subSpec;
    }

    public void setSubSpec(String subSpec) {
        this.subSpec = subSpec;
    }

    public String getEqualityRateRange() {
        return equalityRateRange;
    }

    public void setEqualityRateRange(String equalityRateRange) {
        this.equalityRateRange = equalityRateRange;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void addBalance(Double number) {
        this.balance += number;
    }

    public void subBalance(Double number) {
        if(balance < number)
            throw new IllegalArgumentException("物料【"+name+"】库存不足");
        this.balance -= number;
    }

    public List<AppliedIngredient> getAppliedIngredients() {
        return appliedIngredients;
    }

    public void setAppliedIngredients(List<AppliedIngredient> appliedIngredients) {
        this.appliedIngredients = appliedIngredients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public List<IngredientPurchaseRecord> getPurchaseRecords() {
        return purchaseRecords;
    }

    public void setPurchaseRecords(List<IngredientPurchaseRecord> purchaseRecords) {
        this.purchaseRecords = purchaseRecords;
    }
}
