package com.yuzi.denture.data.mapper;

import com.yuzi.denture.domain.aggregate.AggregateOrder;
import com.yuzi.denture.domain.criteria.AggregateOrderCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by liyou on 2018/12/19.
 */
@Mapper
public interface AggregateMapper {
    List<AggregateOrder> findAggregateOrders(AggregateOrderCriteria criteria);
}
