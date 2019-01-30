package com.yuzi.denture.data.repository;

import com.yuzi.denture.data.mapper.*;
import com.yuzi.denture.domain.*;
import com.yuzi.denture.domain.aggregate.AggregateOrder;
import com.yuzi.denture.domain.aggregate.AppliedUsedIngredient;
import com.yuzi.denture.domain.aggregate.IngredientStatistic;
import com.yuzi.denture.domain.aggregate.TotalIngredientStatistic;
import com.yuzi.denture.domain.criteria.AggregateOrderCriteria;
import com.yuzi.denture.domain.criteria.DentureCriteria;
import com.yuzi.denture.domain.criteria.IngredientCriteria;
import com.yuzi.denture.domain.repository.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InfoRepositoryImpl implements InfoRepository {

    @Autowired
    private ClinicMapper clinicMapper;
    @Autowired
    private TotalStatisticMapper totalStatisticMapper;
    @Autowired
    private IngredientMapper ingredientMapper;
    @Autowired
    private AggregateMapper aggregateMapper;
    @Autowired
    private DentureMapper dentureMapper;
    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Override
    public List<Denture> findDenturesByCriteria(DentureCriteria criteria) {
        return dentureMapper.findDenturesByCriteria(criteria);
    }

    @Override
    public List<AppliedUsedIngredient> findAppliedUsedIngredient(String dentureId) {
        List<AppliedIngredient> applied = ingredientMapper.findAppliedIngredient(dentureId);
        List<UsedIngredient> used = ingredientMapper.findUsedIngredient(dentureId);
        return aggregateAppliedUsedIngredient(applied, used);
    }

    @Override
    public List<Clinic> findCustomerClinics(Long factoryId, Long uid) {
        Map<String, Object> paras = new HashMap<>();
        paras.put("factoryId", factoryId);
        paras.put("uid", uid);
        List<Clinic> clinics = clinicMapper.findByFactoryId(paras);
        return clinics;
    }

    @Override
    public Clinic findCustomerClinic(Long id) {
        return clinicMapper.findById(id);
    }

    @Override
    public TotalIngredientStatistic statTotalIngredient(Long factoryId) {
        return totalStatisticMapper.statIngredient(factoryId);
    }

    @Override
    public IngredientStatistic statIngredient(Long ingredientId, Long factoryId) {
        Map<String, Object> paras = new HashMap<>();
        paras.put("ingredientId", ingredientId);
        paras.put("factoryId", factoryId);
        IngredientStatistic statistic = new IngredientStatistic();
        statistic.setYear(ingredientMapper.yearApplied(paras));
        statistic.setMonth(ingredientMapper.monthApplied(paras));
        statistic.setDay(ingredientMapper.dayApplied(paras));
        return statistic;
    }

    @Override
    public List<AggregateOrder> aggregateOrders(AggregateOrderCriteria criteria) {
        return aggregateMapper.findAggregateOrders(criteria);
    }

    @Override
    public List<AppliedUsedIngredient> aggregateAppliedUsedIngredient(IngredientCriteria criteria) {
        List<AppliedIngredient> applied = ingredientMapper.findAppliedIngredientByFactoryId(criteria);
        List<UsedIngredient> used = ingredientMapper.findUsedIngredientByFactoryId(criteria);
        return aggregateAppliedUsedIngredient(applied, used);
    }

    @Override
    public List<ProductType> findProductTypesByFactoryId(Long factoryId) {
        return productTypeMapper.findTypesByFactoryId(factoryId);
    }

    private List<AppliedUsedIngredient> aggregateAppliedUsedIngredient(List<AppliedIngredient> applied,
                                                                       List<UsedIngredient> used) {
        Map<Long, AppliedUsedIngredient> appliedUsedMap = new HashMap<>();
        for(AppliedIngredient ai: applied) {
            AppliedUsedIngredient appliedUsed = appliedUsedMap.get(ai.getIngredient().getId());
            if( appliedUsed == null) {
                appliedUsed = new AppliedUsedIngredient();
                appliedUsed.setAppliedNumber(ai.getAppliedNumber());
                appliedUsed.setIngredientId(ai.getIngredient().getId());
                appliedUsed.setIngredientName(ai.getIngredient().getName());
                appliedUsedMap.put(ai.getIngredient().getId(), appliedUsed);
            } else {
                appliedUsed.addAppliedNumber(ai.getAppliedNumber());
            }
        }
        for(UsedIngredient ui: used) {
            AppliedUsedIngredient appliedUsed = appliedUsedMap.get(ui.getIngredient().getId());
            appliedUsed.addUsedNumber(ui.getUsedNumber());
        }
        return new ArrayList<>(appliedUsedMap.values());
    }
}
