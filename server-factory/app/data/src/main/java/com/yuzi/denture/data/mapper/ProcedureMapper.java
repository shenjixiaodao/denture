package com.yuzi.denture.data.mapper;

import com.yuzi.denture.domain.Procedure;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ProcedureMapper {
    void save(Procedure procedure);
}
