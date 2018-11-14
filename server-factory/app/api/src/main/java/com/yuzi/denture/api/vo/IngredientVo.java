package com.yuzi.denture.api.vo;


import java.util.List;

public class IngredientVo {
    private Long id;
    private String name;
    private Double balance;
    private Long factoryId;
    private List<IngredientPurchaseRecordVo> records;
    List<AppliedIngredientVo> appliedIngredients;

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

    public List<IngredientPurchaseRecordVo> getRecords() {
        return records;
    }

    public void setRecords(List<IngredientPurchaseRecordVo> records) {
        this.records = records;
    }

    public List<AppliedIngredientVo> getAppliedIngredients() {
        return appliedIngredients;
    }

    public void setAppliedIngredients(List<AppliedIngredientVo> appliedIngredients) {
        this.appliedIngredients = appliedIngredients;
    }
}
