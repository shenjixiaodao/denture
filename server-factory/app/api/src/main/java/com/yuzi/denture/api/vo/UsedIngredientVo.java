package com.yuzi.denture.api.vo;

public class UsedIngredientVo {
    
    //工序组编号(pgId):
    private Long pgId;
    //使用辅料编号(ingredientId):
    private Long ingredientId;
    //使用量(usedNumber):
    private Double usedNumber;
    //备注(comment):
    private String comment;

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
