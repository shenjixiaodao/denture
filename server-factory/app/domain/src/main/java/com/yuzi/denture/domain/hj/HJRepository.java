package com.yuzi.denture.domain.hj;

import java.util.List;

public interface HJRepository {
    void store(Customer customer);
    void update(Customer customer);
    List<Customer> findCustomers();
    Customer findCustomer(Long id);

    void store(Order order);
    void store(OrderDetail detail);
    List<Order> findOrders();
    Order findDetail(Long id);

}
