package com.yuzi.denture.data.mapper;

import com.yuzi.denture.domain.*;
import com.yuzi.denture.domain.criteria.IngredientCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by liyou on 2018/10/21.
 */
@Mapper
public interface IngredientMapper {
    void save(Ingredient ingredient);
    void update(Ingredient ingredient);
    void saveIngredientPurchaseRecord(IngredientPurchaseRecord record);
    List<Ingredient> findIngredients(Long factoryId);
    Ingredient findIngredientById(Long id);
    void saveAppliedIngredient(AppliedIngredient ingredient);
    List<AppliedIngredient> findAppliedIngredient(String dentureId);

    void saveSupplier(Supplier supplier);
    List<Supplier> findSuppliers(Long factoryId);

    //statistic
    Long yearApplied(Map paras);
    Long monthApplied(Map paras);
    Long dayApplied(Map paras);

    //used ingredient
    void saveUsedIngredient(UsedIngredient usedIngredient);
    List<UsedIngredient> findUsedIngredient(String dentureId);

    List<AppliedIngredient> findAppliedIngredientByFactoryId(IngredientCriteria criteria);
    List<UsedIngredient> findUsedIngredientByFactoryId(IngredientCriteria criteria);
}
