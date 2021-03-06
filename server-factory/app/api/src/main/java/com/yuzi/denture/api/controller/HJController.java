package com.yuzi.denture.api.controller;

import com.yuzi.denture.api.vo.base.WebPageResult;
import com.yuzi.denture.api.vo.base.WebResult;
import com.yuzi.denture.domain.hj.*;
import com.yuzi.denture.domain.hj.criteria.OrderCriteria;
import com.yuzi.denture.domain.hj.criteria.PublicCustomerCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value = "/storeCustomer", method = POST)
    public WebResult<Customer> storeCustomer(@RequestBody Customer customer) {
        WebResult<Customer> result = WebResult.execute(res -> {
            service.store(customer);
            res.setData(customer);
        }, "记录客户信息错误", logger);
        return result;
    }

    @ApiOperation(value = "检查客户信息", response = Customer.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/checkNameCustomer", method = POST)
    public WebResult<Customer> checkNameCustomer(@RequestBody Customer customer) {
        WebResult<Customer> result = WebResult.execute(res -> {
            Customer localCustomer = service.findCustomerByName(customer.getName());
            if(localCustomer == null) {
                localCustomer = new Customer(customer.getName());
                localCustomer.setIs_customer(0);
                service.store(localCustomer);
            }
            res.setData(localCustomer);
        }, "记录客户信息错误", logger);
        return result;
    }

    @ApiOperation(value = "修改客户信息", response = WebResult.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/modifyCustomer", method = POST)
    public WebResult modifyCustomer(@RequestBody Customer customer) {
        WebResult result = WebResult.execute(res -> {
            service.update(customer);
        }, "修改客户信息错误", logger);
        return result;
    }

    @ApiOperation(value = "查询客户信息列表", response = Customer.class, httpMethod = "GET")
    @ResponseBody
    @RequestMapping(value = "/findCustomers", method = GET)
    public WebResult<List<Customer>> findCustomers() {
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
        WebResult<Customer> result = WebResult.execute(res -> {
            Customer customer = service.findCustomer(id);
            res.setData(customer);
        }, "查询客户信息列表错误", logger);
        return result;
    }

    //======================
    @ApiOperation(value = "新建订单信息", response = Order.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/newOrder", method = POST)
    public WebResult<Order> newOrder(@RequestBody Order order) {
        WebResult<Order> result = WebResult.execute(res -> {
            service.store(order);
            res.setData(order);
        }, "新建订单信息错误", logger);
        return result;
    }

    @ApiOperation(value = "修改订单信息", response = WebResult.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/modifyOrder", method = POST)
    public WebResult modifyOrder(@RequestBody Order order) {
        WebResult result = WebResult.execute(res -> {
            service.update(order);
        }, "修改订单信息错误", logger);
        return result;
    }

    @ApiOperation(value = "添加订单详情", response = WebResult.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/addOrderDetail", method = POST)
    public WebResult addOrderDetail(@RequestBody OrderDetail detail) {
        WebResult result = WebResult.execute(res -> {
            service.store(detail);
        }, "添加订单详情错误", logger);
        return result;
    }

    @ApiOperation(value = "修改订单详情信息", response = Order.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/modifyOrderDetail", method = POST)
    public WebResult<Order> modifyOrderDetail(@RequestBody OrderDetail detail) {
        WebResult<Order> result = WebResult.execute(res -> {
            service.update(detail);
            Order order = service.findDetail(detail.getOrder_id());
            res.setData(order);
        }, "修改订单详情错误", logger);
        return result;
    }

    @ApiOperation(value = "查询订单列表", response = Order.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/findOrders", method = POST)
    public WebPageResult<List<Order>> findOrders(@RequestBody OrderCriteria criteria) {
        WebPageResult<List<Order>> result = new WebPageResult();
        Long total = service.countOrders(criteria);
        List<Order> orders = service.findOrders(criteria);
        result.setTotalSize(total);
        result.setData(orders);
        return result;
    }

    @ApiOperation(value = "查询订单详情", response = Order.class, httpMethod = "GET")
    @ResponseBody
    @RequestMapping(value = "/findOrderDetail", method = GET)
    public WebResult<Order> findOrderDetail(@RequestParam("id") Long id) {
        WebResult<Order> result = WebResult.execute(res -> {
            Order order = service.findDetail(id);
            res.setData(order);
        }, "查询订单详情错误", logger);
        return result;
    }

    @ApiOperation(value = "查询公海客户列表", response = PublicCustomer.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/findPublicCustomers", method = POST)
    public WebPageResult<List<PublicCustomer>> findPublicCustomers(@RequestBody PublicCustomerCriteria criteria) {
        WebPageResult<List<PublicCustomer>> result = new WebPageResult();
        Long total = service.countPublicCustomers(criteria);
        List<PublicCustomer> orders = service.findPublicCustomers(criteria);
        result.setTotalSize(total);
        result.setData(orders);
        return result;
    }


    @ApiOperation(value = "记录回访信息", response = Visit.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/storeVisit", method = POST)
    public WebResult<Visit> storeVisit(@RequestBody Visit visit) {
        WebResult<Visit> result = WebResult.execute(res -> {
            service.store(visit);
            res.setData(visit);
        }, "记录回访信息", logger);
        return result;
    }
}
