package com.yuzi.denture.clinic.repository.impl;

import com.yuzi.denture.clinic.domain.Denture;
import com.yuzi.denture.clinic.domain.DentureOrder;
import com.yuzi.denture.clinic.domain.ProcedureGroup;
import com.yuzi.denture.clinic.mapper.DentureMapper;
import com.yuzi.denture.clinic.mapper.DentureOrderMapper;
import com.yuzi.denture.clinic.mapper.ProcedureGroupMapper;
import com.yuzi.denture.clinic.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClinicRepositoryImpl implements ClinicRepository {

    @Autowired
    private DentureOrderMapper orderMapper;
    @Autowired
    private DentureMapper dentureMapper;
    @Autowired
    private ProcedureGroupMapper procedureGroupMapper;

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
}
