package com.yuzi.denture.api.controller;

import com.yuzi.denture.api.assembler.ClinicUserAssembler;
import com.yuzi.denture.api.session.SessionManager;
import com.yuzi.denture.api.vo.*;
import com.yuzi.denture.api.vo.base.WebPageResult;
import com.yuzi.denture.api.vo.base.WebResult;
import com.yuzi.denture.domain.*;
import com.yuzi.denture.domain.criteria.CustomerCriteria;
import com.yuzi.denture.domain.repository.FactoryRepository;
import com.yuzi.denture.domain.service.FactoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by liyou on 2018/9/18.
 */
@RestController
@RequestMapping(value = "/denture/factory/customer")
@Api(value = "customer", description = "客戶相关接口")
public class CustomerController {

    private static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private FactoryService service;
    @Autowired
    private FactoryRepository repository;

    @ApiOperation(value = "业务人员录入客户", response = WebResult.class, httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "name", dataType = "long",
                    required = true, value = "诊所名"),
            @ApiImplicitParam(paramType = "form", name = "contact", dataType = "long",
                    required = true, value = "联系方式"),
            @ApiImplicitParam(paramType = "form", name = "region", dataType = "long",
                    required = true, value = "所在区域"),
            @ApiImplicitParam(paramType = "form", name = "address", dataType = "long",
                    required = true, value = "地址"),
            @ApiImplicitParam(paramType = "form", name = "dentistName", dataType = "long",
                    required = true, value = "医生")
    })
    @ResponseBody
    @RequestMapping(value = "/recordCustomer", method = POST)
    public WebResult recordCustomer(String name, String contact, String region, String address, String dentistName,
                                    HttpServletRequest request) {
        FactoryUser user = SessionManager.Instance().user(request);
        Long factoryId = user.getFactoryId();
        Long uid = user.getId();
        logger.info("录入诊所客户:name={},contact={},address={},dentistName={}",name, contact, address, dentistName);
        WebResult result = WebResult.execute(res -> {
            service.addCustomer(factoryId, uid, name, contact, region, address, dentistName);
            logger.info("添加客户成功");
        }, "录入诊所客户", logger);
        return result;
    }

    @ApiOperation(value = "录入诊所员工", response = ClinicUserVo.class, httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "name", dataType = "string",
                    required = true, value = "诊所名"),
            @ApiImplicitParam(paramType = "form", name = "clinicId", dataType = "Long",
                    required = true, value = "门诊ID"),
            @ApiImplicitParam(paramType = "form", name = "role", dataType = "string",
                    required = true, value = "职称"),
            @ApiImplicitParam(paramType = "form", name = "contact", dataType = "string",
                    required = true, value = "联系电话")
    })
    @ResponseBody
    @RequestMapping(value = "/addClinicUser", method = POST)
    public WebResult<ClinicUserVo> addClinicUser(String name, Long clinicId, String role, String contact) {
        logger.info("录入诊所员工");
        WebResult<ClinicUserVo> result = WebResult.execute(res -> {
            ClinicUser user = new ClinicUser(clinicId, role, contact, name);
            service.addClinicUser(user);
            ClinicUserVo vo = ClinicUserAssembler.toVo(user);
            res.setData(vo);
        }, "录入诊所员工", logger);
        return result;
    }

    @ApiOperation(value = "查询工厂所有客户列表", response = FactoryCustomerVo.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/customers", method = POST)
    public WebPageResult<List<FactoryCustomer>> customers(@RequestBody CustomerCriteria criteria, HttpServletRequest request) {
        FactoryUser user = SessionManager.Instance().user(request);
        Long factoryId = user.getFactoryId();
        criteria.setFactoryId(factoryId);
        WebPageResult<List<FactoryCustomer>> res = new WebPageResult<>();
        List<FactoryCustomer> customers = repository.findCustomers(criteria);
        Integer total = repository.countCustomers(criteria);
        res.setData(customers);
        res.setTotalSize(total);
        return res;
    }

    @ApiOperation(value = "修改客户信息", response = FactoryCustomer.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/modifyCustomer", method = POST)
    public WebResult modifyCustomer(@RequestBody FactoryCustomer customer, HttpServletRequest request) {
        // FactoryUser user = SessionManager.Instance().user(request);
        // Long factoryId = user.getFactoryId();
        WebResult result = WebResult.execute(res -> {
            service.modifyCustomer(customer);
            res.setData(customer);
        }, "修改客户信息异常", logger);
        return result;
    }
}
