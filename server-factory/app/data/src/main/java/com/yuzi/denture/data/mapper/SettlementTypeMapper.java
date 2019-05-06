package com.yuzi.denture.data.mapper;

import com.yuzi.denture.domain.SettlementType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface SettlementTypeMapper {
    void save(SettlementType st);
    List<SettlementType> findByFactoryId(Long factory);
}
