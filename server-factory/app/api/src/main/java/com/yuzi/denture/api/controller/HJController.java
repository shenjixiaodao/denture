package com.yuzi.denture.api.controller;

import com.yuzi.denture.api.vo.base.WebResult;
import com.yuzi.denture.domain.hj.Customer;
import com.yuzi.denture.domain.hj.HJRepository;
import com.yuzi.denture.domain.hj.Order;
import com.yuzi.denture.domain.hj.OrderDetail;
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

    //======================
    @ApiOperation(value = "新建订单信息", response = Order.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/newOrder", method = POST)
    public WebResult<Order> newOrder(@RequestBody Order order) {
        logger.info("新建订单信息:order={}",order);
        WebResult<Order> result = WebResult.execute(res -> {
            service.store(order);
            res.setData(order);
        }, "新建订单信息错误", logger);
        return result;
    }

    @ApiOperation(value = "添加订单详情", response = WebResult.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/addOrderDetail", method = POST)
    public WebResult newOrder(@RequestBody OrderDetail detail) {
        logger.info("添加订单详情:detail={}",detail);
        WebResult result = WebResult.execute(res -> {
            service.store(detail);
        }, "添加订单详情错误", logger);
        return result;
    }

    @ApiOperation(value = "查询订单列表", response = Order.class, httpMethod = "GET")
    @ResponseBody
    @RequestMapping(value = "/findOrders", method = GET)
    public WebResult<List<Order>> findOrders() {
        logger.info("查询订单列表");
        WebResult<List<Order>> result = WebResult.execute(res -> {
            List<Order> orders = service.findOrders();
            res.setData(orders);
        }, "查询订单列表错误", logger);
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
}
