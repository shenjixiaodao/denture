package com.yuzi.denture.domain.repository;

import com.yuzi.denture.domain.Clinic;
import com.yuzi.denture.domain.ClinicUser;


/**
 * Created by liyou on 2018/10/25.
 */
public interface ClinicRepository {
    void add(Clinic clinic);
    void addUser(ClinicUser user);
}
