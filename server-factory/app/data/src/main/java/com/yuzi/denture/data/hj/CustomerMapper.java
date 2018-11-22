package com.yuzi.denture.data.hj;

import com.yuzi.denture.domain.hj.Customer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper {
    void save(Customer customer);
    List<Customer> findCustomers();
    Customer findCustomer(Long id);
}
