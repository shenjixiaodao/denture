package com.yuzi.denture.data.clinic.repository;

import com.yuzi.denture.data.clinic.mapper.DentureOrderMapper;
import com.yuzi.denture.domain.clinic.DentureOrder;
import com.yuzi.denture.domain.clinic.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClinicRepositoryImpl implements ClinicRepository {

    @Autowired
    private DentureOrderMapper dentureOrderMapper;

    @Override
    public void add(DentureOrder order) {
        dentureOrderMapper.save(order);
    }
}
