package com.yuzi.denture.api.vo;


import java.util.List;

public class IngredientVo {
    private Long id;
    private String name;
    private Double balance;
    private Long factoryId;
    String type;
    String code;
    String unit;
    String spec;
    String subSpec;
    private List<IngredientPurchaseRecordVo> records;
    List<AppliedIngredientVo> appliedIngredients;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
