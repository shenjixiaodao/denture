package com.yuzi.denture.clinic.repository.impl;

import com.yuzi.denture.clinic.domain.ClinicUser;
import com.yuzi.denture.clinic.domain.Denture;
import com.yuzi.denture.clinic.domain.DentureOrder;
import com.yuzi.denture.clinic.domain.ProcedureGroup;
import com.yuzi.denture.clinic.mapper.*;
import com.yuzi.denture.clinic.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ClinicRepositoryImpl implements ClinicRepository {

    @Autowired
    private DentureOrderMapper orderMapper;
    @Autowired
    private DentureMapper dentureMapper;
    @Autowired
    private ProcedureGroupMapper procedureGroupMapper;
    @Autowired
    private FactoryMapper factoryMapper;
    @Autowired
    private ClinicUserMapper userMapper;

    @Override
    public void add(DentureOrder order) {
        orderMapper.save(order);
    }

    @Override
    public void add(Denture denture) {
        dentureMapper.save(denture);
    }

    @Override
    public void batchAddProcedureGroups(List<ProcedureGroup> groups) {
        procedureGroupMapper.batchSave(groups);
    }

    @Override
    public List<DentureOrder> orders(Long clinicId) {
        List<DentureOrder> orders = orderMapper.findOrdersByClinicId(clinicId);
        return orders;
    }

    @Override
    public DentureOrder order(Long id) {
        return orderMapper.findOrderById(id);
    }

    @Override
    public void updateCoRequest(Long clinicId, Long factoryId, Byte isValid) {
        Map<String, Object> paras = new HashMap<>();
        paras.put("clinicId", clinicId);
        paras.put("factoryId", factoryId);
        paras.put("isValid", isValid);
        factoryMapper.updateCoRequest(paras);
    }

    @Override
    public void addUser(ClinicUser user) {

    }

    @Override
    public ClinicUser findUser(String contact) {
        return userMapper.findUserByContact(contact);
    }
}
