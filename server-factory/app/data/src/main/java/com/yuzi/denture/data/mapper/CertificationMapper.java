package com.yuzi.denture.data.mapper;

import com.yuzi.denture.domain.Certification;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface CertificationMapper {
    void save(Certification certification);
    List<Certification> findByFactoryId(Long factory);
}
