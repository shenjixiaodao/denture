package com.yuzi.denture.clinic.controller.vo;


import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.util.List;

/**
 * Created by liyou on 2018/9/19.
 */
public class DentureVo {
    //序号
    private int index;
    //产品编号(id): String
    @ApiModelProperty(value="义齿ID")
    private String id;
    //产品名称(type):
    @ApiModelProperty(value="义齿类型")
    private String type;
    //规格(specification):
    @ApiModelProperty(value="义齿规格\n" +
            "       [GuGe(\"钴铬合金\"),\n" +
            "        GuiJinShuDanGuan(\"贵金属单冠\"),\n" +
            "        LianGuan(\"连冠（桥、嵌体、贴面）\"),\n" +
            "        ErYangHuaGao(\"二氧化锆\"),\n" +
            "        YangHuaGao(\"氧化锆\"),\n" +
            "        ErYangHuaGuiGuan(\"二氧化硅冠（桥、嵌体、贴面)\"),\n" +
            "        NieGeHeJinGuan(\"镍铬合金冠\"),\n" +
            "        NieGeHeJinQiao(\"镍铬合金桥\"),\n" +
            "        WanZhiZhiJiaKeZhai(\"弯制支架可摘局部义齿\"),\n" +
            "        ShuZhiJiTuoQuanKou(\"树脂基托全口义齿\"),\n" +
            "        Other(\"其他\")]")
    private String specification;
    //定制方(clinic):
    @ApiModelProperty(value="定制方", dataType = "com.yuzi.denture.api.vo.ClinicVo")
    private ClinicVo clinic;
    @ApiModelProperty(value="医生备注")
    private String comment;
    //生产方(factory):
    @ApiModelProperty(value="生产方", dataType = "com.yuzi.denture.api.vo.FactoryVo")
    private FactoryVo factory;
    //数量(number):
    @ApiModelProperty(value="定制数量")
    private Integer number;
    @ApiModelProperty(value="牙位号: 使用\",\"分隔各个牙位号;牙位号格式: 半位[a|b|c|d]-编号[1-8], eg: a-2（表示左上半第2号）")
    private String positions;
    //义齿色号(colorNo)
    @ApiModelProperty(value="义齿色号")
    private String colorNo;
    //创建日期(createdDate):
    @ApiModelProperty(value="创建日期")
    private Date createdDate;
    @ApiModelProperty(value="precedureGroups", dataType = "com.yuzi.denture.api.vo.ProcedureGroupVo")
    private List<ProcedureGroupVo> procedureGroups;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public ClinicVo getClinic() {
        return clinic;
    }

    public void setClinic(ClinicVo clinic) {
        this.clinic = clinic;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public FactoryVo getFactory() {
        return factory;
    }

    public void setFactory(FactoryVo factory) {
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

    public List<ProcedureGroupVo> getProcedureGroups() {
        return procedureGroups;
    }

    public void setProcedureGroups(List<ProcedureGroupVo> procedureGroups) {
        this.procedureGroups = procedureGroups;
    }
}
