package com.yuzi.denture.data.mapper;

import com.yuzi.denture.domain.FactoryCustomer;
import com.yuzi.denture.domain.FactoryRole;
import com.yuzi.denture.domain.FactoryUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by liyou on 2018/9/21.
 */
@Mapper
public interface FactoryUserMapper {

    void save(FactoryUser user);
    void addRole(FactoryRole role);
    void update(FactoryUser user);
    void addCustomer(FactoryCustomer customer);
    void updateCustomer(FactoryCustomer customer);
    FactoryCustomer findCustomerById(Long id);
    List<FactoryCustomer> findCustomersByUid(Map paras);
    FactoryUser findUserByContact(String contact);
    FactoryUser findUserById(Long id);

    //factory
    List<FactoryUser> findUsersByFactoryId(Long factoryId);
}
