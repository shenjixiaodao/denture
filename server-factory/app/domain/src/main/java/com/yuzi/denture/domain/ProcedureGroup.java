package com.yuzi.denture.domain;

import java.util.Date;
import java.util.List;

public class ProcedureGroup {
    
    //工序组操作编号(id):
    private Long id;
    //工序组:
    private GroupType type;
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


}
