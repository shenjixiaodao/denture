package com.yuzi.denture.data.hj;

import com.yuzi.denture.domain.hj.Visit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VisitMapper {
    void save(Visit visit);
    List<Visit> findVisits();
}
