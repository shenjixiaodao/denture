package com.yuzi.denture.clinic.repository.impl;

import com.yuzi.denture.clinic.domain.Denture;
import com.yuzi.denture.clinic.domain.DentureOrder;
import com.yuzi.denture.clinic.domain.ProcedureGroup;
import com.yuzi.denture.clinic.repository.ClinicRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClinicRepositoryImpl implements ClinicRepository {
    @Override
    public void add(DentureOrder order) {

    }

    @Override
    public void add(Denture denture) {

    }

    @Override
    public void batchAddProcedureGroups(List<ProcedureGroup> groups) {

    }
}
