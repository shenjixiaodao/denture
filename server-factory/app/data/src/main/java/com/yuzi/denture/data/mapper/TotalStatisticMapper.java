package com.yuzi.denture.data.mapper;

import com.yuzi.denture.domain.aggregate.TotalIngredientStatistic;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by liyou on 2018/11/18.
 */
@Mapper
public interface TotalStatisticMapper {
    TotalIngredientStatistic statIngredient(Long factoryId);
}
