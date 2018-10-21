package com.yuzi.denture.data.mapper;

import com.yuzi.denture.domain.Ingredient;
import com.yuzi.denture.domain.IngredientPurchaseRecord;
import com.yuzi.denture.domain.Supplier;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    void saveSupplier(Supplier supplier);
    List<Supplier> findSuppliers(Long factoryId);
}
