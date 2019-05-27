package com.yuzi.denture.data.mapper;

import com.yuzi.denture.domain.Factory;
import com.yuzi.denture.domain.FactoryCustomer;
import com.yuzi.denture.domain.FactoryRole;
import com.yuzi.denture.domain.FactoryUser;
import com.yuzi.denture.domain.criteria.CustomerCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by liyou on 2018/9/21.
 */
@Mapper
public interface FactoryUserMapper {

    void save(FactoryUser user);
    void addRole(List<FactoryRole> roles);
    void deleteRole(Long uid);
    void update(FactoryUser user);
    void addCustomer(FactoryCustomer customer);
    void updateCustomer(FactoryCustomer customer);
    FactoryCustomer findCustomerById(Long id);
    FactoryCustomer findCustomerDetailById(Long id);
    List<FactoryCustomer> findCustomersByUid(Long uid);
    Integer countCustomers(CustomerCriteria criteria);
    List<FactoryCustomer> findCustomers(CustomerCriteria criteria);
    FactoryUser findUserByContact(String contact);
    FactoryUser findUserById(Long id);

    List<FactoryUser> findUsersByFactoryId(Long factoryId);

    //factory
    void saveFactory(Factory factory);
    void updateFactory(Factory factory);
    Factory findFactoryById(Long factoryId);

    //function
    void addFunction(Map<String, Object> function);
    void deleteFunctions(Long uid);
    List<Integer> findFunctionsByUid(Long uid);
}
