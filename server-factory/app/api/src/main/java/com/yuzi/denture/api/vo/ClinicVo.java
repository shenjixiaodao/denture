package com.yuzi.denture.api.vo;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;

/**
 * Created by liyou on 2018/9/19.
 */
public class ClinicVo {
    //id:
    @ApiModelProperty(value="诊所ID")
    private Long id;
    //名称(name):
    @ApiModelProperty(value="诊所名")
    private String name;
    //地址(address):
    @ApiModelProperty(value="诊所地址")
    private String address;
    //联系方式(contact):
    @ApiModelProperty(value="诊所联系方式")
    private String contact;
    String region;
    List<ClinicUserVo> users;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public List<ClinicUserVo> getUsers() {
        return users;
    }

    public void setUsers(List<ClinicUserVo> users) {
        this.users = users;
    }
}
