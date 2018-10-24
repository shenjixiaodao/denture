package com.yuzi.denture.api.controller;

import com.yuzi.denture.api.assembler.ClinicAssembler;
import com.yuzi.denture.api.vo.ClinicVo;
import com.yuzi.denture.api.vo.base.WebResult;
import com.yuzi.denture.domain.Clinic;
import com.yuzi.denture.domain.repository.InfoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/denture/factory/info")
@Api(value = "info", description = "页面展示信息查询相关接口")
public class InfoController {

    private static Logger logger = LoggerFactory.getLogger(InfoController.class);

    @Autowired
    private InfoRepository infoRepository;

    @ApiOperation(value = "查询诊所列表", response = ClinicVo.class, httpMethod = "GET")
    @ResponseBody
    @RequestMapping(value = "/queryClinics", method = GET)
    public WebResult<List<ClinicVo>> clinics() {
        // todo session get factoryId and uid
        Long factoryId = 1L;
        Long uid = 1L;
        logger.info("查询诊所列表:factoryId={}",factoryId);
        WebResult<List<ClinicVo>> result = WebResult.execute(res -> {
            List<Clinic> clinics = infoRepository.findCustomerClinics(factoryId, uid);
            List<ClinicVo> vos = ClinicAssembler.toVos(clinics);
            res.setData(vos);
        }, "查询诊所列表错误", logger);
        return result;
    }

}
