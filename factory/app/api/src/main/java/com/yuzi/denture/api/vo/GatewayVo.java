package com.yuzi.denture.api.vo;

import io.swagger.annotations.ApiModelProperty;

public class GatewayVo {
    //zigbee, wifi
    @ApiModelProperty(value="网关类型[WIFI , ZigBee, BLE]", required = true)
    private String type;
    @ApiModelProperty(value="网关名")
    private String name;
    @ApiModelProperty(value="网关协议版本号")
    private String version;
    @ApiModelProperty(value="ZigBee协议网关字段")
    private String ieeeAddr;
    @ApiModelProperty(value="ZigBee协议网关字段")
    private String ePanId;
    @ApiModelProperty(value="ZigBee协议网关字段")
    private String channel;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getIeeeAddr() {
        return ieeeAddr;
    }

    public void setIeeeAddr(String ieeeAddr) {
        this.ieeeAddr = ieeeAddr;
    }

    public String getePanId() {
        return ePanId;
    }

    public void setePanId(String ePanId) {
        this.ePanId = ePanId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "GatewayVo{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", ieeeAddr='" + ieeeAddr + '\'' +
                ", ePanId='" + ePanId + '\'' +
                ", channel='" + channel + '\'' +
                '}';
    }
}
