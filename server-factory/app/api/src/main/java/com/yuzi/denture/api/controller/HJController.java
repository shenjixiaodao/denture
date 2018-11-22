package com.yuzi.denture.api.controller;

import com.yuzi.denture.api.vo.base.WebResult;
import com.yuzi.denture.domain.hj.Customer;
import com.yuzi.denture.domain.hj.HJRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/denture/factory/hj")
@Api(value = "hj", description = "hj相关接口")
public class HJController {

    private static Logger logger = LoggerFactory.getLogger(HJController.class);

    @Autowired
    private HJRepository service;

    @ApiOperation(value = "记录客户信息", response = Customer.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/store", method = POST)
    public WebResult<Customer> store(@RequestBody Customer customer) {
        logger.info("记录客户信息:customer={}",customer);
        WebResult<Customer> result = WebResult.execute(res -> {
            service.store(customer);
            res.setData(customer);
        }, "记录客户信息错误", logger);
        return result;
    }

    @ApiOperation(value = "查询客户信息列表", response = Customer.class, httpMethod = "GET")
    @ResponseBody
    @RequestMapping(value = "/findCustomers", method = GET)
    public WebResult<List<Customer>> findCustomers() {
        logger.info("查询客户信息列表");
        WebResult<List<Customer>> result = WebResult.execute(res -> {
            List<Customer> customers = service.findCustomers();
            res.setData(customers);
        }, "查询客户信息列表错误", logger);
        return result;
    }

    @ApiOperation(value = "查询客户信息列表", response = Customer.class, httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", dataType = "long",
                    required = true, value = "客户ID")
    })
    @ResponseBody
    @RequestMapping(value = "/findCustomer", method = GET)
    public WebResult<Customer> findCustomers(Long id) {
        logger.info("查询客户信息:");
        WebResult<Customer> result = WebResult.execute(res -> {
            Customer customer = service.findCustomer(id);
            res.setData(customer);
        }, "查询客户信息列表错误", logger);
        return result;
    }
}
