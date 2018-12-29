package com.yuzi.denture.data.repository;

import com.yuzi.denture.data.mapper.AggregateMapper;
import com.yuzi.denture.data.mapper.ClinicMapper;
import com.yuzi.denture.data.mapper.IngredientMapper;
import com.yuzi.denture.data.mapper.TotalStatisticMapper;
import com.yuzi.denture.domain.AppliedIngredient;
import com.yuzi.denture.domain.Clinic;
import com.yuzi.denture.domain.UsedIngredient;
import com.yuzi.denture.domain.aggregate.AggregateOrder;
import com.yuzi.denture.domain.aggregate.AppliedUsedIngredient;
import com.yuzi.denture.domain.aggregate.IngredientStatistic;
import com.yuzi.denture.domain.aggregate.TotalIngredientStatistic;
import com.yuzi.denture.domain.criteria.AggregateOrderCriteria;
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

    @Override
    public List<AppliedUsedIngredient> findAppliedUsedIngredient(String dentureId) {
        List<AppliedIngredient> applied = ingredientMapper.findAppliedIngredient(dentureId);
        List<UsedIngredient> used = ingredientMapper.findUsedIngredient(dentureId);
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
    public List<AggregateOrder> findAggregateOrders(AggregateOrderCriteria criteria) {
        return aggregateMapper.findAggregateOrders(criteria);
    }
}
