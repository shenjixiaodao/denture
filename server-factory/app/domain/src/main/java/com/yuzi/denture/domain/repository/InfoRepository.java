package com.yuzi.denture.domain.repository;

import com.yuzi.denture.domain.Clinic;
import com.yuzi.denture.domain.Denture;
import com.yuzi.denture.domain.ProductType;
import com.yuzi.denture.domain.aggregate.*;
import com.yuzi.denture.domain.criteria.AggregateOrderCriteria;
import com.yuzi.denture.domain.criteria.DentureCriteria;
import com.yuzi.denture.domain.criteria.IngredientCriteria;
import com.yuzi.denture.domain.criteria.SalaryCriteria;

import java.util.List;

public interface InfoRepository {

    List<Denture> findDenturesByCriteria(DentureCriteria criteria);

    List<AppliedUsedIngredient> findAppliedUsedIngredient(String dentureId);

    List<Clinic> findCustomerClinics(Long factoryId, Long uid);

    Clinic findCustomerClinic(Long id);

    TotalIngredientStatistic statTotalIngredient(Long factoryId);
    IngredientStatistic statIngredient(Long ingredientId, Long factoryId);

    List<AggregateOrder> aggregateOrders(AggregateOrderCriteria criteria);
    List<AppliedUsedIngredient> aggregateAppliedUsedIngredient(IngredientCriteria criteria);

    //Financial stat


    List<ProductType> findProductTypesByFactoryId(Long factoryId);

    List<Salary> salaryList(SalaryCriteria criteria);
}
