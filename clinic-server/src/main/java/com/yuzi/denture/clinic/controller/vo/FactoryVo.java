package com.yuzi.denture.clinic.controller.vo;

import io.swagger.annotations.ApiModelProperty;

public class FactoryVo {
    
    //编号(id):
    @ApiModelProperty(value="工厂ID")
    private Long id;
    //名称(name):
    @ApiModelProperty(value="工厂名")
    private String name;
    //联系方式(contact):
    @ApiModelProperty(value="工厂联系方式")
    private String contact;
    //地址(address):
    @ApiModelProperty(value="工厂地址")
    private String address;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
