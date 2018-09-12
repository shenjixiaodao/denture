package com.yuzi.denture.data.factory.mapper;

import com.yuzi.denture.domain.factory.Denture;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DentureMapper {
    void save(Denture denture);
}
