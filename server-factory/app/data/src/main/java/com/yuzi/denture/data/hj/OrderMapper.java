package com.yuzi.denture.data.hj;

import com.yuzi.denture.domain.hj.Order;
import com.yuzi.denture.domain.hj.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    void save(Order order);
    void saveDetail(OrderDetail detail);
    // void update(Customer customer);
    List<Order> findOrders();
    Order findDetail(Long id);
}
