package com.yuzi.denture.domain;

import java.util.Date;

public class AppliedIngredient {

    //ingredientId: Long
    private Ingredient ingredient;
    //产品编号(DentureId):
    private String dentureId;
    //序号(no):
    private Integer no;
    //领取量(appliedNumber):
    private Double appliedNumber;
    //领用人(applicant): User
    private FactoryUser applicant;
    //领用日期(appliedDate):
    private Date appliedDate;
    //备注(comment):
    private String comment;
    Long factoryId;

    public AppliedIngredient(Long ingredientId, String dentureId, Double appliedNumber,
                             Long applicantId, String comment) {
        this.ingredient = new Ingredient(ingredientId);
        this.dentureId = dentureId;
        //this.no = no;
        this.appliedNumber = appliedNumber;
        this.applicant = new FactoryUser(applicantId);
        this.appliedDate = new Date();
        this.comment = comment;
    }

    public AppliedIngredient() {
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public String getDentureId() {
        return dentureId;
    }

    public void setDentureId(String dentureId) {
        this.dentureId = dentureId;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Double getAppliedNumber() {
        return appliedNumber;
    }

    public void setAppliedNumber(Double appliedNumber) {
        this.appliedNumber = appliedNumber;
    }

    public FactoryUser getApplicant() {
        return applicant;
    }

    public void setApplicant(FactoryUser applicant) {
        this.applicant = applicant;
    }

    public Date getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(Date appliedDate) {
        this.appliedDate = appliedDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
