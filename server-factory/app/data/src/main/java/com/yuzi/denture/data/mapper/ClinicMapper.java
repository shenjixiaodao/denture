package com.yuzi.denture.data.mapper;

import com.yuzi.denture.domain.Clinic;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ClinicMapper {

    Clinic findById(Long id);

}
