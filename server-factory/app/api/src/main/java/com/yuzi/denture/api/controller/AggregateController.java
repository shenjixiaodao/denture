package com.yuzi.denture.api.controller;


import com.yuzi.denture.api.assembler.DentureAssembler;
import com.yuzi.denture.api.session.SessionManager;
import com.yuzi.denture.api.vo.base.DentureVo;
import com.yuzi.denture.api.vo.base.WebResult;
import com.yuzi.denture.domain.Denture;
import com.yuzi.denture.domain.FactoryUser;
import com.yuzi.denture.domain.aggregate.AggregateOrder;
import com.yuzi.denture.domain.aggregate.AppliedUsedIngredient;
import com.yuzi.denture.domain.aggregate.Salary;
import com.yuzi.denture.domain.criteria.AggregateOrderCriteria;
import com.yuzi.denture.domain.criteria.DentureCriteria;
import com.yuzi.denture.domain.criteria.IngredientCriteria;
import com.yuzi.denture.domain.criteria.SalaryCriteria;
import com.yuzi.denture.domain.repository.InfoRepository;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/denture/factory/aggregate")
@Api(value = "info", description = "页面展示信息查询相关接口")
public class AggregateController {

    private static Logger logger = LoggerFactory.getLogger(AggregateController.class);


    @Autowired
    private InfoRepository infoRepository;

    @ResponseBody
    @RequestMapping(value = "/aggregateOrders", method = POST)
    public WebResult<List<AggregateOrder>> aggregateOrders(@RequestBody AggregateOrderCriteria criteria,
                                                           HttpServletRequest request) {
        FactoryUser user = SessionManager.Instance().user(request);
        Long factoryId = user.getFactoryId();
        WebResult<List<AggregateOrder>> result = WebResult.execute(res -> {
            criteria.setFactoryId(factoryId);
            List<AggregateOrder> statistic = infoRepository.aggregateOrders(criteria);
            Map<String, Map<String, Object>> data = new TreeMap<>();
            for(AggregateOrder order: statistic) {
                String month = order.getDeliveryDate();
                Map<String, Object> item = data.get(month);
                if(item == null) {
                    item = new HashMap<>();
                    item.put("month", month);
                    item.put("size", 0);
                    item.put("amount", BigDecimal.ZERO);
                    item.put("list", new ArrayList<>());
                    data.put(month, item);
                }
                item.put("size", (int)item.get("size") + order.getNumber());
                item.put("amount", ((BigDecimal)item.get("amount")).add(order.getBasePrice().multiply(new BigDecimal(order.getNumber()))));
                ((List)item.get("list")).add(order);
            }
            res.setData(data.values());
        }, "查询聚合订单信息", logger);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/aggregateAppliedUsedIngredient", method = POST)
    public WebResult<List<AppliedUsedIngredient>> findAppliedUsedIngredient(@RequestBody IngredientCriteria criteria,
                                                                            HttpServletRequest request) {
        FactoryUser user = SessionManager.Instance().user(request);
        Long factoryId = user.getFactoryId();
        WebResult<List<AppliedUsedIngredient>> result = WebResult.execute(res -> {
            criteria.setFactoryId(factoryId);
            List<AppliedUsedIngredient> appliedUsedIngredients = infoRepository.aggregateAppliedUsedIngredient(criteria);
            res.setData(appliedUsedIngredients);
        }, "查询聚合物料领用信息", logger);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/salaryList", method = POST)
    public WebResult<List<Salary>> salaryList(@RequestBody SalaryCriteria criteria,
                                              HttpServletRequest request) {
        FactoryUser user = SessionManager.Instance().user(request);
        Long factoryId = user.getFactoryId();
        WebResult<List<Salary>> result = WebResult.execute(res -> {
            criteria.setFactoryId(factoryId);
            List<Salary> salaries = infoRepository.salaryList(criteria);
            res.setData(salaries);
        }, "查询工资单", logger);
        return result;
    }

    private static String month(String date) {
        String[] strs = date.split("-");
        return strs[0]+"-"+strs[1];
    }

}
