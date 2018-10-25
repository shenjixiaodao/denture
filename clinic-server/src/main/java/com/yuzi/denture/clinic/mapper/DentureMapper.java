package com.yuzi.denture.clinic.mapper;


import com.yuzi.denture.clinic.domain.Denture;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DentureMapper {
    void save(Denture denture);
}
