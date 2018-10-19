package com.yuzi.denture.api.assembler;

import com.yuzi.denture.api.vo.FactoryCustomerVo;
import com.yuzi.denture.domain.FactoryCustomer;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FactoryCustomerAssembler {

    public static List<FactoryCustomerVo> toVos(List<FactoryCustomer> customers) {
        if(customers == null)
            return Collections.emptyList();
        List<FactoryCustomerVo> vos = new ArrayList<>(customers.size());
        for(FactoryCustomer customer:customers) {
            FactoryCustomerVo vo = toVo(customer);
            vos.add(vo);
        }
        return vos;
    }

    public static FactoryCustomerVo toVo(FactoryCustomer customer) {
        if(customer == null)
            return null;
        FactoryCustomerVo vo = new FactoryCustomerVo();
        BeanUtils.copyProperties(customer, vo);
        vo.setClinic(ClinicAssembler.toVo(customer.getClinic()));
        return vo;
    }
}
