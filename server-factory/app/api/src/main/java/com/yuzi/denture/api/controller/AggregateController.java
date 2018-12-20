package com.yuzi.denture.api.controller;


import com.yuzi.denture.api.session.SessionManager;
import com.yuzi.denture.api.vo.base.WebResult;
import com.yuzi.denture.domain.FactoryUser;
import com.yuzi.denture.domain.aggregate.AggregateOrder;
import com.yuzi.denture.domain.aggregate.TotalIngredientStatistic;
import com.yuzi.denture.domain.criteria.AggregateOrderCriteria;
import com.yuzi.denture.domain.repository.InfoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
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
            List<AggregateOrder> statistic = infoRepository.findAggregateOrders(criteria);
            res.setData(statistic);
        }, "查询聚合订单信息", logger);
        return result;
    }


}
