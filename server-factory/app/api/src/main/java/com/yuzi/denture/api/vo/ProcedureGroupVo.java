package com.yuzi.denture.api.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

public class ProcedureGroupVo {
    
    //工序组操作编号(id):
    @ApiModelProperty(value="工序组操作编号")
    private Long id;
    //工序组:
    @ApiModelProperty(value="工序组类型")
    private String type;
    //产品编号(DentureId):
    @ApiModelProperty(value="义齿ID")
    private String dentureId;
    //辅料使用记录(usedIngredients):
    @ApiModelProperty(value="原料使用记录", dataType = "com.yuzi.denture.api.vo.UsedIngredientVo")
    private List<UsedIngredientVo> usedIngredients;
    //工序开始时间(startDate):
    @ApiModelProperty(value="工序组开始时间")
    private Date startDate;
    //工序完成时间(endDate):
    @ApiModelProperty(value="工序组完成时间")
    private Date endDate;
    //操作工序(procedures):
    @ApiModelProperty(value="操作工序集",dataType = "com.yuzi.denture.api.vo.ProcedureVo")
    private List<ProcedureVo> procedures;
    //操作员(operator):
    @ApiModelProperty(value="操作人员",dataType = "com.yuzi.denture.api.vo.FactoryUserVo")
    private FactoryUserVo operator;
    //检查内容(inspectionContent):
    @ApiModelProperty(value="检查内容")
    private String inspectionContent;
    //检查员(inspector):
    @ApiModelProperty(value="检查人员",dataType = "com.yuzi.denture.api.vo.FactoryUserVo")
    private FactoryUserVo inspector;
    //检查结果(inspectionResult):
    @ApiModelProperty(value="@ApiModelProperty(value=\"检查内容\")")
    private String inspectionResult;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDentureId() {
        return dentureId;
    }

    public void setDentureId(String dentureId) {
        this.dentureId = dentureId;
    }

    public List<UsedIngredientVo> getUsedIngredients() {
        return usedIngredients;
    }

    public void setUsedIngredients(List<UsedIngredientVo> usedIngredients) {
        this.usedIngredients = usedIngredients;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<ProcedureVo> getProcedures() {
        return procedures;
    }

    public void setProcedures(List<ProcedureVo> procedures) {
        this.procedures = procedures;
    }

    public FactoryUserVo getOperator() {
        return operator;
    }

    public void setOperator(FactoryUserVo operator) {
        this.operator = operator;
    }

    public String getInspectionContent() {
        return inspectionContent;
    }

    public void setInspectionContent(String inspectionContent) {
        this.inspectionContent = inspectionContent;
    }

    public FactoryUserVo getInspector() {
        return inspector;
    }

    public void setInspector(FactoryUserVo inspector) {
        this.inspector = inspector;
    }

    public String getInspectionResult() {
        return inspectionResult;
    }

    public void setInspectionResult(String inspectionResult) {
        this.inspectionResult = inspectionResult;
    }
}
