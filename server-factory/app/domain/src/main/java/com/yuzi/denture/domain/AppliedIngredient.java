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

}
