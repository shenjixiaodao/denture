package com.yuzi.denture.clinic.repository;

import com.yuzi.denture.clinic.domain.Factory;

import java.util.List;

public interface InfoRepository {
    List<Factory> findFactories(Long clinicId, Byte isValid);
    Integer countFactory(Long clinicId, Byte isValid);

    List<Factory> findApplicants(Long clinicId);
}
