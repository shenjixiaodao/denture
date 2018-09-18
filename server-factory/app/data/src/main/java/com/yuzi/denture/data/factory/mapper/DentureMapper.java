package com.yuzi.denture.data.factory.mapper;

import com.yuzi.denture.domain.factory.Denture;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface DentureMapper {
    //void save(Denture denture);

    Denture findByDeliveryInfo(Map paras);
    Denture findByDentureId(String dentureId);
}
