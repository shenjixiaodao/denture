package com.yuzi.denture.data.hj;

import com.yuzi.denture.domain.hj.Order;
import com.yuzi.denture.domain.hj.OrderDetail;
import com.yuzi.denture.domain.hj.criteria.OrderCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {
    void save(Order order);
    void saveDetail(OrderDetail detail);
    void update(Order order);
    void updateDetail(OrderDetail detail);
    Long countOrders(OrderCriteria criteria);
    List<Order> findOrders(OrderCriteria criteria);
    Order findDetail(Long id);
    Order findOrderByNameDate(Map para);
}
