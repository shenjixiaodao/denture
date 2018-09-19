package com.yuzi.denture.data.mapper;

import com.yuzi.denture.domain.DentureOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DentureOrderMapper {
    List<DentureOrder> findOrdersByFactoryId(Long factoryId);
    DentureOrder findOrderById(String orderId);
}
