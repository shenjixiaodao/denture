package com.yuzi.denture.data.factory.repository;



import com.yuzi.denture.data.factory.mapper.DentureMapper;
import com.yuzi.denture.domain.factory.DeliveryInfo;
import com.yuzi.denture.domain.factory.Denture;
import com.yuzi.denture.domain.factory.DentureOrder;
import com.yuzi.denture.domain.factory.repository.FactoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FactoryRepositoryImpl implements FactoryRepository {

    @Autowired
    private DentureMapper dentureMapper;

    /*@Override
    public void add(Denture denture) {
        dentureMapper.save(denture);
    }*/

    @Override
    public Denture findByDenture(String deliveryId, DeliveryInfo.Company company) {
        Map<String, Object> paras = new HashMap<>();
        paras.put("deliveryId", deliveryId);
        paras.put("company", company);
        return dentureMapper.findByDeliveryInfo(paras);
    }

    @Override
    public Denture findByDenture(String dentureId) {
        return dentureMapper.findByDentureId(dentureId);
    }

    @Override
    public List<Denture> findDentures(Long factoryId) {
        return null;
    }

    @Override
    public List<DentureOrder> findOrders(Long factoryId) {
        return null;
    }

    @Override
    public DentureOrder findOrder(String orderId) {
        return null;
    }
}
