package com.yuzi.denture.clinic.controller.vo;

import io.swagger.annotations.ApiModelProperty;


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
}
