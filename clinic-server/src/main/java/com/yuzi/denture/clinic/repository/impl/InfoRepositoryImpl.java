package com.yuzi.denture.clinic.repository.impl;

import com.yuzi.denture.clinic.domain.Factory;
import com.yuzi.denture.clinic.mapper.FactoryMapper;
import com.yuzi.denture.clinic.repository.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InfoRepositoryImpl implements InfoRepository {

    @Autowired
    private FactoryMapper factoryMapper;

    @Override
    public List<Factory> findFactories(Long clinicId, Byte isValid) {
        Map<String, Object> paras = new HashMap<>();
        paras.put("clinicId", clinicId);
        paras.put("isValid", isValid);
        List<Factory> factories = factoryMapper.findFactories(paras);
        return factories;
    }

    @Override
    public Integer countFactory(Long clinicId, Byte isValid) {
        Map<String, Object> paras = new HashMap<>();
        paras.put("clinicId", clinicId);
        paras.put("isValid", isValid);
        return factoryMapper.countFactory(paras);
    }
}
