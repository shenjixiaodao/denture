package com.yuzi.denture.biz.clinic;

import com.yuzi.denture.domain.clinic.DentureOrder;
import com.yuzi.denture.domain.clinic.repository.ClinicRepository;
import com.yuzi.denture.domain.clinic.service.ClinicService;
import com.yuzi.denture.domain.factory.Denture;
import com.yuzi.denture.domain.factory.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class ClinicServiceImpl implements ClinicService {

    @Autowired
    private FactoryService factoryService;
    @Autowired
    private ClinicRepository repository;

    @Override
    @Transactional
    public void create(DentureOrder order) {
        //todo orderId
        String orderId = new Date().getTime()+"";
        order.setId(orderId);
        Denture denture = factoryService.createDenture(orderId,order.getFactoryId());
        order.setDentureId(denture.getId());
        repository.add(order);
    }
}
