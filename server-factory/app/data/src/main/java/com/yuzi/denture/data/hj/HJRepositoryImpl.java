package com.yuzi.denture.data.hj;

import com.yuzi.denture.domain.hj.Customer;
import com.yuzi.denture.domain.hj.HJRepository;
import com.yuzi.denture.domain.hj.Order;
import com.yuzi.denture.domain.hj.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HJRepositoryImpl implements HJRepository {

    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    OrderMapper orderMapper;

    @Override
    public void store(Customer customer) {
        if(customer.getId() != null) {

        } else {
            customerMapper.save(customer);
        }
    }

    @Override
    public void update(Customer customer) {
        customerMapper.update(customer);
    }

    @Override
    public List<Customer> findCustomers() {
        return customerMapper.findCustomers();
    }

    @Override
    public Customer findCustomer(Long id) {
        return customerMapper.findCustomer(id);
    }

    @Override
    public void store(Order order) {
        Customer customer = customerMapper.findCustomer(order.getCustomer_id());
        order.setCustomer_name(customer.getName());
        orderMapper.save(order);
    }

    @Override
    public void store(OrderDetail detail) {
        orderMapper.saveDetail(detail);
    }

    @Override
    public List<Order> findOrders() {
        return orderMapper.findOrders();
    }

    @Override
    public Order findDetail(Long id) {
        return orderMapper.findDetail(id);
    }
}
