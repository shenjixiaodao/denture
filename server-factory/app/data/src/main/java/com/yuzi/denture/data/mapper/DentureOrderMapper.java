package com.yuzi.denture.data.mapper;

import com.yuzi.denture.domain.DentureOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DentureOrderMapper {
    void save(DentureOrder order);
    List<DentureOrder> findOrdersByFactoryId(Long factoryId);
    DentureOrder findOrderByDentureId(String dentureId);
}
