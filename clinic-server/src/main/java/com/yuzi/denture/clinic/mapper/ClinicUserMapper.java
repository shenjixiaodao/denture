package com.yuzi.denture.clinic.mapper;

import com.yuzi.denture.clinic.domain.ClinicUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by liyou on 2018/9/21.
 */
@Mapper
public interface ClinicUserMapper {



    ClinicUser findUserByContact(String contact);
}
