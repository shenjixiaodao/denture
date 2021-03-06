package com.yuzi.denture.api.controller;

import com.yuzi.denture.api.session.SessionManager;
import com.yuzi.denture.api.vo.base.WebResult;
import com.yuzi.denture.domain.*;
import com.yuzi.denture.domain.repository.FactoryRepository;
import com.yuzi.denture.domain.service.FactoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private FactoryService service;

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

    @ApiOperation(value = "修改工厂信息", response = WebResult.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/modifyFactory", method = POST)
    public WebResult modifyFactory(@RequestBody Factory factory, HttpServletRequest request) {
        WebResult result = WebResult.execute(res -> {
            FactoryUser user = SessionManager.Instance().user(request);
            Long factoryId = user.getFactoryId();
            factory.setId(factoryId);
            repository.modifyFactory(factory);
        }, "修改工厂信息错误", logger);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/findFactory", method = GET)
    public WebResult<Factory> findFactory(HttpServletRequest request) {
        FactoryUser user = SessionManager.Instance().user(request);
        Long factoryId = user.getFactoryId();
        WebResult<Factory> result = WebResult.execute(res -> {
            Factory factory = repository.findFactoryById(factoryId);
            res.setData(factory);
        }, "查询工程信息", logger);
        return result;
    }

    @ApiOperation(value = "添加产品类别", response = WebResult.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/addProductType", method = POST)
    public WebResult addProductType(@RequestBody ProductType type, HttpServletRequest request) {
        FactoryUser user = SessionManager.Instance().user(request);
        Long factoryId = user.getFactoryId();
        WebResult result = WebResult.execute(res -> {
            type.setFactoryId(factoryId);
            type.setGmtCreated(new Date());
            service.addProductType(type);
        }, "添加产品类别异常", logger);
        return result;
    }

    @ApiOperation(value = "添加价目", response = WebResult.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/addPriceSheet", method = POST)
    public WebResult addPriceSheet(@RequestBody PriceSheet price, HttpServletRequest request) {
        FactoryUser user = SessionManager.Instance().user(request);
        Long factoryId = user.getFactoryId();
        WebResult result = WebResult.execute(res -> {
            price.setFactory(new Factory(factoryId));
            service.addPriceSheet(price);
        }, "添加产品类别异常", logger);
        return result;
    }

    @ApiOperation(value = "删除产品类别", response = WebResult.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/deleteProductType", method = POST)
    public WebResult deleteProductType(@RequestParam("id") Long id, HttpServletRequest request) {
        FactoryUser user = SessionManager.Instance().user(request);
        Long factoryId = user.getFactoryId();
        WebResult result = WebResult.execute(res -> {
            repository.deleteProductType(id);
        }, "添加产品类别异常", logger);
        return result;
    }

}
