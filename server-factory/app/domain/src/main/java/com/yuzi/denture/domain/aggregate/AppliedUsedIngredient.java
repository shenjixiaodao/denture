package com.yuzi.denture.domain.aggregate;


public class AppliedUsedIngredient {
    Long ingredientId;
    String ingredientName;
    String ingredientType;
    String equalityRateRange;
    Double appliedNumber;
    Double usedNumber = 0.0;
    Double wastedNumber = 0.0;
    Double balance = 0.0;

    public void addAppliedNumber(Double number) {
        appliedNumber += number;
        balance += number;
    }

    public void addUsedNumber(Double number) {
        usedNumber += number;
        balance -= number;
    }

    public void addWastedNumber(Double number) {
        wastedNumber += number;
    }

    public String getEqualityRateRange() {
        return equalityRateRange;
    }

    public void setEqualityRateRange(String equalityRateRange) {
        this.equalityRateRange = equalityRateRange;
    }

    public Double getWastedNumber() {
        return wastedNumber;
    }

    public void setWastedNumber(Double wastedNumber) {
        this.wastedNumber = wastedNumber;
    }

    public String getIngredientType() {
        return ingredientType;
    }

    public void setIngredientType(String ingredientType) {
        this.ingredientType = ingredientType;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public Double getAppliedNumber() {
        return appliedNumber;
    }

    public void setAppliedNumber(Double appliedNumber) {
        this.appliedNumber = appliedNumber;
        this.balance += appliedNumber;
    }

    public Double getUsedNumber() {
        return usedNumber;
    }

    public void setUsedNumber(Double usedNumber) {
        this.usedNumber = usedNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
