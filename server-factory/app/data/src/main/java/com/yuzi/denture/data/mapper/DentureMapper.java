package com.yuzi.denture.data.mapper;

import com.yuzi.denture.domain.Denture;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DentureMapper {
    //void save(Denture denture);
    void update(Denture denture);
    Denture findByDeliveryInfo(Map paras);
    Denture findByDentureId(String dentureId);
    List<Denture> findDenturesByFactoryId(Long factoryId);
}
