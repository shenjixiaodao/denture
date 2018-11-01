package com.yuzi.denture.clinic.mapper;

import com.yuzi.denture.clinic.domain.Factory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FactoryMapper {
    List<Factory> findFactories(Map paras);
    void updateCoRequest(Map paras);
    Integer countFactory(Map paras);
}
