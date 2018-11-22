package com.yuzi.denture.domain.hj;

import java.util.List;

public interface HJRepository {
    void store(Customer customer);
    List<Customer> findCustomers();
    Customer findCustomer(Long id);
}
