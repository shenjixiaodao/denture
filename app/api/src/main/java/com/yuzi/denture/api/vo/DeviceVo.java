package com.yuzi.denture.api.vo;

import io.swagger.annotations.ApiModelProperty;

public class DeviceVo {

    @ApiModelProperty(value="服务器返回的设备ID")
    private String id;
    @ApiModelProperty(value="设备名")
    private String name;
    @ApiModelProperty(value="设备类型")
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DeviceVo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
