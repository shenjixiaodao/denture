package com.yuzi.denture.api.controller;

import com.yuzi.denture.api.assembler.*;
import com.yuzi.denture.api.session.SessionManager;
import com.yuzi.denture.api.vo.*;
import com.yuzi.denture.api.vo.base.WebResult;
import com.yuzi.denture.domain.*;
import com.yuzi.denture.domain.repository.FactoryRepository;
import com.yuzi.denture.domain.service.FactoryService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by liyou on 2018/9/18.
 */
@RestController
@RequestMapping(value = "/denture/factory/manufacture")
@Api(value = "manufacture", description = "生产相关接口")
public class ManufactureController {

    private static Logger logger = LoggerFactory.getLogger(ManufactureController.class);

    @Autowired
    private FactoryService service;
    @Autowired
    private FactoryRepository repository;

    @ApiOperation(value = "业务员查询名下跟踪订单", response = DentureOrderVo.class, httpMethod = "GET")
    @ResponseBody
    @RequestMapping(value = "/salesmanQueryOrders", method = GET)
    public WebResult<List<DentureOrderVo>> salesmanQueryOrders(HttpServletRequest request) {
        FactoryUser user = SessionManager.Instance().user(request);
        Long factorId = user.getFactoryId(); //对应工厂的订单
        Long uid = 1L;
        logger.info("查询订单:uid={}", uid);
        WebResult<List<DentureOrderVo>> result = WebResult.execute(res -> {
            List<DentureOrder> orders = repository.findOrdersByUid(uid);
            List<DentureOrderVo> vos = DentureOrderAssembler.toVos(orders);
            res.setData(vos);
        }, "查询订单错误", logger);
        return result;
    }

}
