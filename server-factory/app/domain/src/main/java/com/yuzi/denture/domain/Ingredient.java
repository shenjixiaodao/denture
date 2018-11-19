package com.yuzi.denture.domain;

import java.util.List;

public class Ingredient {

    private Long id;
    private String name;
    private Double balance;
    private Long factoryId;
    String type;
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
