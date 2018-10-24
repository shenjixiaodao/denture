package com.yuzi.denture.data.mapper;

import com.yuzi.denture.domain.Clinic;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface ClinicMapper {

    Clinic findById(Long id);
    List<Clinic> findByFactoryId(Map paras);
}
