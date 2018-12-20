package com.yuzi.denture.data.repository;

import com.yuzi.denture.data.mapper.ClinicMapper;
import com.yuzi.denture.data.mapper.IngredientMapper;
import com.yuzi.denture.data.mapper.TotalStatisticMapper;
import com.yuzi.denture.domain.Clinic;
import com.yuzi.denture.domain.aggregate.AggregateOrder;
import com.yuzi.denture.domain.aggregate.IngredientStatistic;
import com.yuzi.denture.domain.aggregate.TotalIngredientStatistic;
import com.yuzi.denture.domain.criteria.AggregateOrderCriteria;
import com.yuzi.denture.domain.repository.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        return null;
    }
}
