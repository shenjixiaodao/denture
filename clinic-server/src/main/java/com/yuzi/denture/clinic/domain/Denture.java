package com.yuzi.denture.clinic.domain;


import com.yuzi.denture.clinic.domain.type.*;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Denture {
    private final static String PositionSeparator = ",";
    //产品编号(id): String
    private String id;
    //产品名称(type):
    private DentureType type;
    //规格(specification):
    private SpecType specification;
    //定制方(clinic):
    private Clinic clinic;
    //医生备注
    private String comment;
    //生产方(factory):
    private Factory factory;
    //数量(number):
    private Integer number;
    private List<ProcedureGroup> procedureGroups;
    /**
     * 牙位号(position),
     * 使用","分隔各个牙位号，个数等于 {@param number}；
     * 牙位号格式: 半位[a|b|c|d]-编号[1-8], eg: a-2（表示左上半第2号）
     */
    private String positions;
    //义齿色号(colorNo): Enum
    private String colorNo;
    //创建日期(createdDate):
    private Date createdDate;
    private BiteLevel biteLevel;
    BorderType borderType;
    FieldType fieldType;
    InnerCrowType innerCrowType;
    NeckType neckType;
    OuterCrowType outerCrowType;
    PaddingType paddingType;

    public Denture(DentureType type, SpecType specification, Long clinicId, String comment,
                   Long factoryId, String positions, String colorNo) {
        this.type = type;
        this.specification = specification;
        this.clinic = new Clinic(clinicId);
        this.comment = comment;
        this.factory = new Factory(factoryId);
        this.positions = positions;
        for(String pos : positions.split(PositionSeparator)) {
            this.number = 0;
            if(!StringUtils.isEmpty(pos))
                this.number++;
        }
        this.colorNo = colorNo;
        this.createdDate = new Date();
    }

    public List<ProcedureGroup> generateProcedureGroups() {
        if(StringUtils.isEmpty(id)) {
            throw new IllegalArgumentException("dentureId为空");
        }
        this.procedureGroups = new ArrayList<>(GroupType.values().length);
        for(GroupType gt : GroupType.values()) {
            ProcedureGroup group = new ProcedureGroup(gt, this.id);
            procedureGroups.add(group);
        }
        return this.procedureGroups;
    }

    public Denture() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DentureType getType() {
        return type;
    }

    public void setType(DentureType type) {
        this.type = type;
    }

    public SpecType getSpecification() {
        return specification;
    }

    public void setSpecification(SpecType specification) {
        this.specification = specification;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getPositions() {
        return positions;
    }

    public void setPositions(String positions) {
        this.positions = positions;
    }

    public String getColorNo() {
        return colorNo;
    }

    public void setColorNo(String colorNo) {
        this.colorNo = colorNo;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public static String getPositionSeparator() {
        return PositionSeparator;
    }

    public BiteLevel getBiteLevel() {
        return biteLevel;
    }

    public void setBiteLevel(BiteLevel biteLevel) {
        this.biteLevel = biteLevel;
    }

    public BorderType getBorderType() {
        return borderType;
    }

    public void setBorderType(BorderType borderType) {
        this.borderType = borderType;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public InnerCrowType getInnerCrowType() {
        return innerCrowType;
    }

    public void setInnerCrowType(InnerCrowType innerCrowType) {
        this.innerCrowType = innerCrowType;
    }

    public NeckType getNeckType() {
        return neckType;
    }

    public void setNeckType(NeckType neckType) {
        this.neckType = neckType;
    }

    public List<ProcedureGroup> getProcedureGroups() {
        return procedureGroups;
    }

    public void setProcedureGroups(List<ProcedureGroup> procedureGroups) {
        this.procedureGroups = procedureGroups;
    }

    public OuterCrowType getOuterCrowType() {
        return outerCrowType;
    }

    public void setOuterCrowType(OuterCrowType outerCrowType) {
        this.outerCrowType = outerCrowType;
    }

    public PaddingType getPaddingType() {
        return paddingType;
    }

    public void setPaddingType(PaddingType paddingType) {
        this.paddingType = paddingType;
    }
}
