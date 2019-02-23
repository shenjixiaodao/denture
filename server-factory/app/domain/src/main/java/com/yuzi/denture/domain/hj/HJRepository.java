package com.yuzi.denture.domain.hj;

import com.yuzi.denture.domain.hj.criteria.OrderCriteria;

import java.util.List;

public interface HJRepository {
    void store(Customer customer);
    void update(Customer customer);
    List<Customer> findCustomers();
    Customer findCustomer(Long id);

    void store(Order order);
    void update(Order order);
    void update(OrderDetail detail);
    void store(OrderDetail detail);
    Long countOrders(OrderCriteria criteria);
    List<Order> findOrders(OrderCriteria criteria);
    Order findDetail(Long id);

}
