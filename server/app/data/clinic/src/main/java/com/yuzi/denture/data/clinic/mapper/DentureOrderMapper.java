package com.yuzi.denture.data.clinic.mapper;

import com.yuzi.denture.domain.clinic.DentureOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DentureOrderMapper {
    void save(DentureOrder order);
}
