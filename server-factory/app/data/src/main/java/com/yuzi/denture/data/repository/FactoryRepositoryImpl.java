package com.yuzi.denture.data.repository;



import com.yuzi.denture.data.mapper.DentureMapper;
import com.yuzi.denture.data.mapper.DentureOrderMapper;
import com.yuzi.denture.data.mapper.FactoryUserMapper;
import com.yuzi.denture.data.mapper.ProcedureMapper;
import com.yuzi.denture.domain.*;
import com.yuzi.denture.domain.repository.FactoryRepository;
import org.apache.tomcat.jni.Proc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FactoryRepositoryImpl implements FactoryRepository {

    @Autowired
    private DentureMapper dentureMapper;
    @Autowired
    private DentureOrderMapper dentureOrderMapper;
    @Autowired
    private ProcedureMapper procedureMapper;
    @Autowired
    private FactoryUserMapper userMapper;
    /*@Override
    public void add(Denture denture) {
        dentureMapper.save(denture);
    }*/

    @Override
    public void update(Denture denture) {
        dentureMapper.update(denture);
    }

    @Override
    public void add(Procedure procedure) {
        procedureMapper.save(procedure);
    }

    @Override
    public void add(FactoryUser user) {
        userMapper.save(user);
    }

    @Override
    public FactoryUser findUser(String contact) {
        return userMapper.findUserByContact(contact);
    }

    @Override
    public List<FactoryUser> findUsers(Long factoryId) {
        return userMapper.findUsersByFactoryId(factoryId);
    }

    @Override
    public Denture findDenture(String deliveryId, DeliveryInfo.Company company) {
        Map<String, Object> paras = new HashMap<>();
        paras.put("deliveryId", deliveryId);
        paras.put("company", company);
        return dentureMapper.findByDeliveryInfo(paras);
    }

    @Override
    public Denture findDenture(String dentureId) {
        return dentureMapper.findByDentureId(dentureId);
    }

    @Override
    public List<Denture> findDentures(Long factoryId) {
        return dentureMapper.findDenturesByFactoryId(factoryId);
    }

    @Override
    public List<DentureOrder> findOrders(Long factoryId) {
        return dentureOrderMapper.findOrdersByFactoryId(factoryId);
    }

    @Override
    public DentureOrder findOrder(String orderId) {
        return dentureOrderMapper.findOrderById(orderId);
    }
}