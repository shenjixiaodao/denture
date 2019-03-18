package com.yuzi.denture.data.mapper;

import com.yuzi.denture.domain.PriceSheet;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PriceSheetMapper {
    void save(PriceSheet ps);
    List<PriceSheet> findPricesByCustomerId(Long customerId);
}
