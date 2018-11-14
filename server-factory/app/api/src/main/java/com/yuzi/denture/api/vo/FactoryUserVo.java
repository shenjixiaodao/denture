package com.yuzi.denture.api.vo;


import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class FactoryUserVo implements Serializable{

    //id:
    @ApiModelProperty(value="用户ID")
    private Long id;
    //factoryId
    @ApiModelProperty(value="所在工厂ID")
    Long factoryId;
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
    private List<String> roles;
    @ApiModelProperty(value="登录成功后的token")
    private String token;
    String avatar;
    Boolean marital;
    Integer age;
    String cardId;
    String address;
    String educational;

    public Boolean getMarital() {
        return marital;
    }

    public void setMarital(Boolean marital) {
        this.marital = marital;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEducational() {
        return educational;
    }

    public void setEducational(String educational) {
        this.educational = educational;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
