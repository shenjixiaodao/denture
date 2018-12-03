package com.yuzi.denture.domain;

public class UsedIngredient {

    Long appliedId;
    //工序组编号(pgId):
    String dentureId;
    Long factoryId;
    String equipment;
    private Long pgId;
    //使用辅料编号(ingredientId):
    private Long ingredientId;
    //使用量(usedNumber):
    private Double usedNumber;
    //备注(comment):
    private String comment;

    public Long getAppliedId() {
        return appliedId;
    }

    public void setAppliedId(Long appliedId) {
        this.appliedId = appliedId;
    }

    public String getDentureId() {
        return dentureId;
    }

    public void setDentureId(String dentureId) {
        this.dentureId = dentureId;
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public Long getPgId() {
        return pgId;
    }

    public void setPgId(Long pgId) {
        this.pgId = pgId;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public Double getUsedNumber() {
        return usedNumber;
    }

    public void setUsedNumber(Double usedNumber) {
        this.usedNumber = usedNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
