package com.yuzi.denture.domain.repository;

import com.yuzi.denture.domain.Clinic;

import java.util.List;

public interface InfoRepository {
    List<Clinic> findCustomerClinics(Long factoryId, Long uid);
}
