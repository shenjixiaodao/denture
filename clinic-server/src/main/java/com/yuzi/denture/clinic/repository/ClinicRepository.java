package com.yuzi.denture.clinic.repository;

import com.yuzi.denture.clinic.domain.Denture;
import com.yuzi.denture.clinic.domain.DentureOrder;
import com.yuzi.denture.clinic.domain.ProcedureGroup;

import java.util.List;
import java.util.Map;

public interface ClinicRepository {
    void add(DentureOrder order);
    void add(Denture denture);
    void batchAddProcedureGroups(List<ProcedureGroup> groups);

    List<DentureOrder> orders(Long clinicId);
    DentureOrder order(Long id);
    void updateCoRequest(Long clinicId, Long factoryId, Byte isValid);
}
