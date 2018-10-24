package com.yuzi.denture.data.repository;

import com.yuzi.denture.data.mapper.ClinicMapper;
import com.yuzi.denture.domain.Clinic;
import com.yuzi.denture.domain.repository.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InfoRepositoryImpl implements InfoRepository {

    @Autowired
    private ClinicMapper clinicMapper;

    @Override
    public List<Clinic> findCustomerClinics(Long factoryId, Long uid) {
        Map<String, Object> paras = new HashMap<>();
        paras.put("factoryId", factoryId);
        paras.put("uid", uid);
        List<Clinic> clinics = clinicMapper.findByFactoryId(paras);
        return clinics;
    }
}
