package com.yuzi.denture.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UsedIngredient {

    Long id;
    //工序组编号(pgId):
    String dentureId;
    Long factoryId;
    String equipment;
    private Long pgId;
    //使用辅料编号(ingredient):
    private Ingredient ingredient;
    //使用量(usedNumber):
    private Double usedNumber;
    Double wastedNumber;
    //备注(comment):
    private String comment;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date usedTime;

    public Double getWastedNumber() {
        return wastedNumber;
    }

    public void setWastedNumber(Double wastedNumber) {
        this.wastedNumber = wastedNumber;
    }

    public Date getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(Date usedTime) {
        this.usedTime = usedTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
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
