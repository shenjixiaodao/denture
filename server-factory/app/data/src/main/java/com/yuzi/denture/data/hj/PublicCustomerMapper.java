package com.yuzi.denture.data.hj;

import com.yuzi.denture.domain.hj.PublicCustomer;
import com.yuzi.denture.domain.hj.criteria.PublicCustomerCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PublicCustomerMapper {
    void save(PublicCustomer customer);
    List<PublicCustomer> findPublicCustomers(PublicCustomerCriteria criteria);
    Long countPublicCustomers(PublicCustomerCriteria criteria);
}
