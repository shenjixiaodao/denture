package com.yuzi.denture.domain;

import com.yuzi.denture.domain.type.ProcedureType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProcedureGroup {
    
    //工序组操作编号(id):
    private Long id;
    //工序组:
    private ProcedureType type;
    //产品编号(DentureId):
    private String dentureId;
    //辅料使用记录(usedIngredients):
    private List<UsedIngredient> usedIngredients;
    //工序开始时间(startDate):
    private Date startDate;
    //工序完成时间(endDate):
    private Date endDate;
    //操作工序(procedures):
    private List<Procedure> procedures;
    //操作员(operator):
    private FactoryUser operator;
    //检查内容(inspectionContent):
    private String inspectionContent;
    //检查员(inspector):
    private FactoryUser inspector;
    //检查结果(inspectionResult):
    private InspectionResult inspectionResult;

    public ProcedureGroup(ProcedureType type, String dentureId) {
        this.type = type;
        this.dentureId = dentureId;
    }

    public void addUsedIngredient(UsedIngredient usedIngredient) {
        if(this.usedIngredients == null)
            usedIngredients = new ArrayList<>();
        this.usedIngredients.add(usedIngredient);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProcedureType getType() {
        return type;
    }

    public void setType(ProcedureType type) {
        this.type = type;
    }

    public String getDentureId() {
        return dentureId;
    }

    public void setDentureId(String dentureId) {
        this.dentureId = dentureId;
    }

    public List<UsedIngredient> getUsedIngredients() {
        return usedIngredients;
    }

    public void setUsedIngredients(List<UsedIngredient> usedIngredients) {
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

    public List<Procedure> getProcedures() {
        return procedures;
    }

    public void setProcedures(List<Procedure> procedures) {
        this.procedures = procedures;
    }

    public FactoryUser getOperator() {
        return operator;
    }

    public void setOperator(FactoryUser operator) {
        this.operator = operator;
    }

    public String getInspectionContent() {
        return inspectionContent;
    }

    public void setInspectionContent(String inspectionContent) {
        this.inspectionContent = inspectionContent;
    }

    public FactoryUser getInspector() {
        return inspector;
    }

    public void setInspector(FactoryUser inspector) {
        this.inspector = inspector;
    }

    public InspectionResult getInspectionResult() {
        return inspectionResult;
    }

    public void setInspectionResult(InspectionResult inspectionResult) {
        this.inspectionResult = inspectionResult;
    }

    public ProcedureGroup() {
    }
}
