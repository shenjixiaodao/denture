package com.yuzi.denture.data.repository;

import com.yuzi.denture.data.mapper.ClinicMapper;
import com.yuzi.denture.domain.Clinic;
import com.yuzi.denture.domain.ClinicUser;
import com.yuzi.denture.domain.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by liyou on 2018/10/25.
 */
@Repository
public class ClinicRepositoryImpl implements ClinicRepository {

    @Autowired
    private ClinicMapper clinicMapper;

    @Override
    public void add(Clinic clinic) {
        clinicMapper.save(clinic);
    }

    @Override
    public void addUser(ClinicUser user) {
        clinicMapper.saveClinicUser(user);
    }
}
