package com.yuzi.denture.clinic.mapper;


import com.yuzi.denture.clinic.domain.DentureOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DentureOrderMapper {
    void save(DentureOrder order);
    List<DentureOrder> findOrdersByClinicId(Long clinicId);
}
