package com.yuzi.denture.domain;

import com.yuzi.denture.domain.type.*;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
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
    private String specification;
    //定制方(clinic):
    private Clinic clinic;
    //医生备注
    private String comment;
    //生产方(factory):
    private Factory factory;
    //数量(number):
    private Integer number;
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
    //查验人(modelInspector):
    private FactoryUser modelInspector;
    //查验日期(modelInspectionDate):
    private Date modelInspectionDate;
    //生产开始日期(startDate):
    private Date startDate;
    //生产截止日期(endDate):
    private Date endDate;
    //生产部负责人审核(proReview): Review
    private ReviewResult proReview;
    //生产部负责人审核日期(proReviewDate):
    private Date proReviewDate;
    //质量部负责人审核(quaReview): Review
    private ReviewResult quaReview;
    //质量部负责人审核日期(quaReviewDate):
    private Date quaReviewDate;
    //辅料申请记录(appliedIngredients): List
    private List<AppliedIngredient> appliedIngredients;
    //工序组操作记录(precedureGroups):
    private List<ProcedureGroup> procedureGroups;
    BiteLevel biteLevel;
    BorderType borderType;
    FieldType fieldType;
    InnerCrownType innerCrownType;
    NeckType neckType;
    OuterCrownType outerCrownType;
    PaddingType paddingType;
    String requirement;
    BigDecimal basePrice;
    BigDecimal factoryPrice;
    Date estimatedDuration;
    String patientName;
    String deliveryId;
    String deliveryDate;
    String deliveryPerson;
    Long salesmanId;
    String salesman;
    String stage;
    Long dentistId;
    String dentist;
    String status;
    Date receivedDate;
    Long creatorId;
    String creator;

    public Denture(DentureType type, String specification, Long clinicId, String comment,
                   Long factoryId, String positions, Integer number, String colorNo) {
        this.type = type;
        this.specification = specification;
        this.clinic = new Clinic(clinicId);
        this.comment = comment;
        this.factory = new Factory(factoryId);
        this.positions = positions;
        this.number = number;
        this.colorNo = colorNo;
        this.createdDate = new Date();
    }

    /*public enum SpecType {
        GuGe("钴铬合金"),
        GuiJinShuDanGuan("贵金属单冠"),
        LianGuan("连冠（桥、嵌体、贴面）"),
        ErYangHuaGao("二氧化锆"),
        YangHuaGao("氧化锆"),
        ErYangHuaGuiGuan("二氧化硅冠（桥、嵌体、贴面)"),
        NieGeHeJinGuan("镍铬合金冠"),
        NieGeHeJinQiao("镍铬合金桥"),
        WanZhiZhiJiaKeZhai("弯制支架可摘局部义齿"),
        ShuZhiJiTuoQuanKou("树脂基托全口义齿"),
        Other("其他");
        private String text;
        SpecType(String text) {
            this.text = text;
        }
        public String text() {
            return this.text;
        }
        public static SpecType typeOf(String type) {
            for(SpecType result : SpecType.values()){
                if(result.name().toLowerCase().equals(type.toLowerCase()))
                    return result;
            }
            throw new IllegalArgumentException("未知义齿规格类型");
        }
    }*/
    public enum  DentureType {
        Fixed("定制式固定义齿"), Mobilizable("定制式活动义齿");
        private String text;
        DentureType(String text) {
            this.text = text;
        }
        public String text() {
            return this.text;
        }
        public static DentureType typeOf(String type) {
            for(DentureType result : DentureType.values()){
                if(result.name().toLowerCase().equals(type.toLowerCase()))
                    return result;
            }
            throw new IllegalArgumentException("未知义齿类型");
        }
    }

    public enum Status {
        Invalid("无效"), Created("新入检"), Producing("待出货"), Delivered("已出货"), Remaking("返厂");
        private String text;
        Status(String text) {
            this.text = text;
        }
        public String text() {
            return this.text;
        }
        public static Status typeOf(String type) {
            for(Status result : Status.values()){
                if(result.name().toLowerCase().equals(type.toLowerCase()))
                    return result;
            }
            throw new IllegalArgumentException("未知管理状态");
        }
    }

    public List<ProcedureGroup> generateProcedureGroups() {
        if(StringUtils.isEmpty(id)) {
            throw new IllegalArgumentException("dentureId为空");
        }
        this.procedureGroups = new ArrayList<>(ProcedureType.values().length);
        for(ProcedureType gt : ProcedureType.values()) {
            ProcedureGroup group = new ProcedureGroup(gt, this.id);
            procedureGroups.add(group);
        }
        return this.procedureGroups;
    }

    public void filterGroup(GroupType type) {
        if(procedureGroups == null)
            return;
        // todo 是否需要针对不同的工序组过滤工序记录
        /*for(Iterator<ProcedureGroup> iterator = procedureGroups.iterator(); iterator.hasNext();) {
            ProcedureGroup group = iterator.next();
            if(group.getType() != type)
                iterator.remove();
        }*/
    }

    public Denture() {
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public Long getDentistId() {
        return dentistId;
    }

    public void setDentistId(Long dentistId) {
        this.dentistId = dentistId;
    }

    public String getDentist() {
        return dentist;
    }

    public void setDentist(String dentist) {
        this.dentist = dentist;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public Long getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(Long salesmanId) {
        this.salesmanId = salesmanId;
    }

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryPerson() {
        return deliveryPerson;
    }

    public void setDeliveryPerson(String deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }
    public void setBasePrice(String basePrice) {
        if (!StringUtils.isEmpty(basePrice))
            this.basePrice = new BigDecimal(basePrice);
    }

    public BigDecimal getFactoryPrice() {
        return factoryPrice;
    }

    public void setFactoryPrice(BigDecimal factoryPrice) {
        this.factoryPrice = factoryPrice;
    }

    public void setFactoryPrice(String factoryPrice) {
        if (!StringUtils.isEmpty(factoryPrice))
            this.basePrice = new BigDecimal(factoryPrice);
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Date getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(Date estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
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

    public InnerCrownType getInnerCrownType() {
        return innerCrownType;
    }

    public void setInnerCrownType(InnerCrownType innerCrownType) {
        this.innerCrownType = innerCrownType;
    }

    public NeckType getNeckType() {
        return neckType;
    }

    public void setNeckType(NeckType neckType) {
        this.neckType = neckType;
    }

    public OuterCrownType getOuterCrownType() {
        return outerCrownType;
    }

    public void setOuterCrownType(OuterCrownType outerCrownType) {
        this.outerCrownType = outerCrownType;
    }

    public PaddingType getPaddingType() {
        return paddingType;
    }

    public void setPaddingType(PaddingType paddingType) {
        this.paddingType = paddingType;
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

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
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

    public FactoryUser getModelInspector() {
        return modelInspector;
    }

    public void setModelInspector(FactoryUser modelInspector) {
        this.modelInspector = modelInspector;
    }

    public Date getModelInspectionDate() {
        return modelInspectionDate;
    }

    public void setModelInspectionDate(Date modelInspectionDate) {
        this.modelInspectionDate = modelInspectionDate;
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

    public ReviewResult getProReview() {
        return proReview;
    }

    public void setProReview(ReviewResult proReview) {
        this.proReview = proReview;
    }

    public Date getProReviewDate() {
        return proReviewDate;
    }

    public void setProReviewDate(Date proReviewDate) {
        this.proReviewDate = proReviewDate;
    }

    public ReviewResult getQuaReview() {
        return quaReview;
    }

    public void setQuaReview(ReviewResult quaReview) {
        this.quaReview = quaReview;
    }

    public Date getQuaReviewDate() {
        return quaReviewDate;
    }

    public void setQuaReviewDate(Date quaReviewDate) {
        this.quaReviewDate = quaReviewDate;
    }

    public List<AppliedIngredient> getAppliedIngredients() {
        return appliedIngredients;
    }

    public void setAppliedIngredients(List<AppliedIngredient> appliedIngredients) {
        this.appliedIngredients = appliedIngredients;
    }

    public List<ProcedureGroup> getProcedureGroups() {
        return procedureGroups;
    }

    public void setProcedureGroups(List<ProcedureGroup> procedureGroups) {
        this.procedureGroups = procedureGroups;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    @Override
    public String toString() {
        return "Denture{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", specification=" + specification +
                ", clinic=" + clinic +
                ", factory=" + factory +
                ", number=" + number +
                ", positions='" + positions + '\'' +
                ", colorNo='" + colorNo + '\'' +
                ", createdDate=" + createdDate +
                ", modelInspector=" + modelInspector +
                ", modelInspectionDate=" + modelInspectionDate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", proReview=" + proReview +
                ", proReviewDate=" + proReviewDate +
                ", quaReview=" + quaReview +
                ", quaReviewDate=" + quaReviewDate +
                ", appliedIngredients=" + appliedIngredients +
                ", procedureGroups=" + procedureGroups +
                '}';
    }
}
