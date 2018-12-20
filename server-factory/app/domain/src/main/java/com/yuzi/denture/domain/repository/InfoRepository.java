package com.yuzi.denture.domain.repository;

import com.yuzi.denture.domain.Clinic;
import com.yuzi.denture.domain.aggregate.AggregateOrder;
import com.yuzi.denture.domain.aggregate.IngredientStatistic;
import com.yuzi.denture.domain.aggregate.TotalIngredientStatistic;
import com.yuzi.denture.domain.criteria.AggregateOrderCriteria;

import java.util.List;

public interface InfoRepository {
    List<Clinic> findCustomerClinics(Long factoryId, Long uid);

    Clinic findCustomerClinic(Long id);

    TotalIngredientStatistic statTotalIngredient(Long factoryId);
    IngredientStatistic statIngredient(Long ingredientId, Long factoryId);

    List<AggregateOrder> findAggregateOrders(AggregateOrderCriteria criteria);
}
