package com.yuzi.denture.data.mapper;

import com.yuzi.denture.domain.ProcedureGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by liyou on 2018/10/18.
 */
@Mapper
public interface ProcedureGroupMapper {
    void batchSave(List<ProcedureGroup> groups);
}
