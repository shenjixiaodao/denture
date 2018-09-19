package com.yuzi.denture.api.vo;


import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class AppliedIngredientVo {
    
    //ingredientId: Long
    @ApiModelProperty(value="原料", dataType = "com.yuzi.denture.api.vo.IngredientVo")
    private IngredientVo ingredient;
    //产品编号(DentureId):
    @ApiModelProperty(value="义齿ID")
    private String dentureId;
    //序号(no):
    @ApiModelProperty(value="序号")
    private Integer no;
    //领取量(appliedNumber):
    @ApiModelProperty(value="原料用量")
    private Double appliedNumber;
    //领用人(applicant): User
    @ApiModelProperty(value="领用人", dataType = "com.yuzi.denture.api.vo.FactoryUserVo")
    private FactoryUserVo applicant;
    //领用日期(appliedDate):
    @ApiModelProperty(value="领用日期")
    private Date appliedDate;
    //备注(comment):
    @ApiModelProperty(value="备注")
    private String comment;

    public IngredientVo getIngredient() {
        return ingredient;
    }

    public void setIngredient(IngredientVo ingredient) {
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

    public FactoryUserVo getApplicant() {
        return applicant;
    }

    public void setApplicant(FactoryUserVo applicant) {
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
