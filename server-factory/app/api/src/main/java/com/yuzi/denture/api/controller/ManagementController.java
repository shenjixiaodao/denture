package com.yuzi.denture.api.controller;

import com.yuzi.denture.api.assembler.*;
import com.yuzi.denture.api.session.SessionManager;
import com.yuzi.denture.api.vo.*;
import com.yuzi.denture.api.vo.base.DentureVo;
import com.yuzi.denture.api.vo.base.WebPageResult;
import com.yuzi.denture.api.vo.base.WebResult;
import com.yuzi.denture.domain.GroupType;
import com.yuzi.denture.domain.*;
import com.yuzi.denture.domain.criteria.CustomerCriteria;
import com.yuzi.denture.domain.criteria.DentureCriteria;
import com.yuzi.denture.domain.repository.FactoryRepository;
import com.yuzi.denture.domain.service.FactoryService;
import com.yuzi.denture.domain.type.*;
import com.yuzi.denture.domain.util.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by liyou on 2018/9/18.
 */
@RestController
@RequestMapping(value = "/denture/factory/management")
@Api(value = "management", description = "管理相关接口")
public class ManagementController {

    private static Logger logger = LoggerFactory.getLogger(ManagementController.class);

    @Autowired
    private FactoryRepository repository;



    @ApiOperation(value = "添加结算方式", response = WebResult.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/addSettlement", method = POST)
    public WebResult addSettlement(@RequestBody SettlementType settlementType, HttpServletRequest request) {
        WebResult result = WebResult.execute(res -> {
            FactoryUser user = SessionManager.Instance().user(request);
            Long factoryId = user.getFactoryId();
            settlementType.setFactoryId(factoryId);
            repository.add(settlementType);
        }, "添加结算方式错误", logger);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/findSettlementTypes", method = GET)
    public WebResult<List<SettlementType>> findSettlementTypes(HttpServletRequest request) {
        FactoryUser user = SessionManager.Instance().user(request);
        Long factoryId = user.getFactoryId();
        WebResult<List<SettlementType>> result = WebResult.execute(res -> {
            List<SettlementType> types = repository.findSettlementTypes(factoryId);
            res.setData(types);
        }, "查询结算方式", logger);
        return result;
    }

    @ApiOperation(value = "添加注册证号", response = WebResult.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/addCertification", method = POST)
    public WebResult addCertification(@RequestBody Certification certification, HttpServletRequest request) {
        WebResult result = WebResult.execute(res -> {
            FactoryUser user = SessionManager.Instance().user(request);
            Long factoryId = user.getFactoryId();
            certification.setFactoryId(factoryId);
            repository.add(certification);
        }, "添加注册证号错误", logger);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/findCertifications", method = GET)
    public WebResult<List<Certification>> findCertifications(HttpServletRequest request) {
        FactoryUser user = SessionManager.Instance().user(request);
        Long factoryId = user.getFactoryId();
        WebResult<List<Certification>> result = WebResult.execute(res -> {
            List<Certification> types = repository.findCertifications(factoryId);
            res.setData(types);
        }, "查询注册证号", logger);
        return result;
    }

}
