package com.yuzi.denture.api.vo;


import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class FactoryUserVo {

    //id:
    @ApiModelProperty(value="用户ID")
    private Long id;
    //name:
    @ApiModelProperty(value="用户姓名")
    private String name;
    //contact:
    @ApiModelProperty(value="用户联系方式")
    private String contact;
    //groupType:
    @ApiModelProperty(value="用户所属组\n" +
            "   [ShiGao(\"石膏组\"),\n" +
            "    LaXing(\"蜡型组\"),\n" +
            "    CheJin(\"车金组\"),\n" +
            "    ChongJiao(\"充胶组\"),\n" +
            "    ShangCi(\"上瓷组\"),\n" +
            "    CheCi(\"车瓷组\"),\n" +
            "    Comprehensive(\"综合管理组\"),\n" +
            "    Market(\"销售组\"),\n" +
            "    Management(\"管理组\")]")
    private String groupType;
    //joinDate:
    @ApiModelProperty(value="加入公司时间")
    private Date joinDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }
}