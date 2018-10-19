package com.yuzi.denture.data.repository;



import com.yuzi.denture.data.mapper.*;
import com.yuzi.denture.domain.*;
import com.yuzi.denture.domain.repository.FactoryRepository;
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
    private ProcedureGroupMapper procedureGroupMapper;
    @Autowired
    private FactoryUserMapper userMapper;

    @Override
    public void add(DentureOrder order) {
        dentureOrderMapper.save(order);
    }

    @Override
    public void add(Denture denture) {
        dentureMapper.save(denture);
    }

    @Override
    public void update(Denture denture) {
        dentureMapper.update(denture);
    }

    @Override
    public void batchAddProcedureGroups(List<ProcedureGroup> groups) {
        procedureGroupMapper.batchSave(groups);
    }

    @Override
    public void add(Procedure procedure) {
        procedureMapper.save(procedure);
    }

    @Override
    public List<Procedure> findProcedures(Long pgId) {
        List<Procedure> procedures = procedureMapper.findProceduresByPgId(pgId);
        return procedures;
    }

    @Override
    public void add(FactoryUser user) {
        userMapper.save(user);
    }

    @Override
    public void addRole(FactoryRole role) {
        userMapper.addRole(role);
    }

    @Override
    public void update(FactoryUser user) {
        userMapper.update(user);
    }

    @Override
    public void addCustomer(FactoryCustomer customer) {
        userMapper.addCustomer(customer);
    }

    @Override
    public void updateCustomer(FactoryCustomer customer) {
        userMapper.updateCustomer(customer);
    }

    @Override
    public FactoryCustomer findCustomer(Long id) {
        return userMapper.findCustomerById(id);
    }

    @Override
    public List<FactoryCustomer> findCustomersByUid(Long factoryId, Long uid) {
        Map<String, Object> paras = new HashMap<>();
        paras.put("uid", uid);
        paras.put("factoryId", factoryId);
        return userMapper.findCustomersByUid(paras);
    }

    @Override
    public List<FactoryCustomer> findCustomersByFactoryId(Long factoryId) {
        //todo
        return null;
    }

    @Override
    public FactoryUser findUser(String contact) {
        return userMapper.findUserByContact(contact);
    }

    @Override
    public FactoryUser findUser(Long uid) {
        return userMapper.findUserById(uid);
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
        Denture denture = dentureMapper.findByDentureId(dentureId);
        return denture;
    }

    @Override
    public List<Denture> findDentures(Long factoryId) {
        return dentureMapper.findDenturesByFactoryId(factoryId);
    }

    @Override
    public List<Denture> findWaitingDentures(Long factoryId) {
        List<Denture> dentures = dentureMapper.findWaitingDentures(factoryId);
        return dentures;
    }

    @Override
    public List<Denture> findDoingDentures(Long factoryId) {
        List<Denture> dentures = dentureMapper.findDoingDentures(factoryId);
        return dentures;
    }

    @Override
    public List<Denture> findDoneDentures(Long factoryId) {
        List<Denture> dentures = dentureMapper.findDoneDentures(factoryId);
        return dentures;
    }

    @Override
    public List<DentureOrder> findOrders(Long factoryId) {
        return dentureOrderMapper.findOrdersByFactoryId(factoryId);
    }

    @Override
    public List<DentureOrder> findOrdersByUid(Long uid) {
        return dentureOrderMapper.findOrdersByUid(uid);
    }

    @Override
    public DentureOrder findOrder(String dentureId) {
        return dentureOrderMapper.findOrderByDentureId(dentureId);
    }
}
