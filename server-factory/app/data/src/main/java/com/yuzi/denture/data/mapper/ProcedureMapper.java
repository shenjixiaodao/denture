package com.yuzi.denture.data.mapper;

import com.yuzi.denture.domain.Procedure;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ProcedureMapper {
    void save(Procedure procedure);

    List<Procedure> findProceduresByPgId(Long pgId);
}
