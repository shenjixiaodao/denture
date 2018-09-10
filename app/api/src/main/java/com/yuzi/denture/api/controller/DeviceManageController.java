package com.yuzi.denture.api.controller;


import com.yuzi.denture.api.vo.ControlVo;
import com.yuzi.denture.api.vo.base.ResponseCode;
import com.yuzi.denture.api.vo.base.WebResult;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/sh/manage")
@Api(value = "DeviceManage", description = "语言包相关接口")
public class DeviceManageController {

    private static Logger logger = LoggerFactory.getLogger(DeviceManageController.class);

    @ApiOperation(value = "控制设备", response = WebResult.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/control", method = POST)
    public WebResult control(@ApiParam(value = "发送设备指令", required = true) @RequestBody ControlVo controlVo) {
        if(logger.isInfoEnabled()) {
            logger.info("发送设备控制指令: {}",controlVo.toString());
        }
        WebResult result = null;
        try {
            //
        } catch (Exception e) {
            logger.warn("调用管理语言包异常: {}",e);
            result = new WebResult<>(ResponseCode.SMInnerError, null);
        }
        return result;
    }

    @ApiOperation(value = "绑定设备", response = WebResult.class, httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "mac", dataType = "String", required = true, value = "网关mac地址")
    })
    @ResponseBody
    @RequestMapping(value = "/bindDevice", method = POST)
    public WebResult bindDevice(String mac) {
        if(logger.isInfoEnabled()) {
            logger.info("绑定mac=[{}]设备", mac);
        }
        WebResult result = null;
        try {
            //
        } catch (Exception e) {
            logger.warn("调用管理语言包异常: {}",e);
            result = new WebResult<>(ResponseCode.SMInnerError, null);
        }
        return result;
    }
}
