package com.yuzi.denture.domain;

import java.util.Date;

public class Procedure {
    
    //编号(id):
    private Long id;
    //工序组编号(pgId):
    private Long pgId;
    //工序名(name):
    private String name;
    //工艺要求(specification):
    private String specification;
    //操作记录(operationRecord):
    private String operationRecord;
    //备注(comment):
    private String comment;
    //操作员(operator):
    private FactoryUser operator;
    private Date completedDate;
    private Date modifiedDate;

    public Procedure(Long pgId, String name, Long operatorId) {
        this.pgId = pgId;
        this.name = name;
        this.operator = new FactoryUser(operatorId);
        this.completedDate = new Date();
        this.modifiedDate = this.completedDate;
    }

    public Procedure() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPgId() {
        return pgId;
    }

    public void setPgId(Long pgId) {
        this.pgId = pgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getOperationRecord() {
        return operationRecord;
    }

    public void setOperationRecord(String operationRecord) {
        this.operationRecord = operationRecord;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public FactoryUser getOperator() {
        return operator;
    }

    public Date getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public void setOperator(FactoryUser operator) {
        this.operator = operator;
    }
}
