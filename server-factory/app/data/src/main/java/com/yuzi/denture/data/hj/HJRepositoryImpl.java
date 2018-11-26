package com.yuzi.denture.data.hj;

import com.yuzi.denture.domain.hj.Customer;
import com.yuzi.denture.domain.hj.HJRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HJRepositoryImpl implements HJRepository {

    @Autowired
    CustomerMapper customerMapper;

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
}
