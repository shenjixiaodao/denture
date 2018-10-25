package com.yuzi.denture.clinic.domain;

import com.yuzi.denture.clinic.domain.type.GroupType;

public class ProcedureGroup {
    
    //工序组操作编号(id):
    private Long id;
    //工序组:
    private GroupType type;
    //产品编号(DentureId):
    private String dentureId;

    public ProcedureGroup(GroupType type, String dentureId) {
        this.type = type;
        this.dentureId = dentureId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GroupType getType() {
        return type;
    }

    public void setType(GroupType type) {
        this.type = type;
    }

    public String getDentureId() {
        return dentureId;
    }

    public void setDentureId(String dentureId) {
        this.dentureId = dentureId;
    }

    public ProcedureGroup() {
    }
}
