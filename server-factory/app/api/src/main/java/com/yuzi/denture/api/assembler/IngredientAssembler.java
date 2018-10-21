package com.yuzi.denture.api.assembler;

import com.yuzi.denture.api.vo.IngredientPurchaseRecordVo;
import com.yuzi.denture.api.vo.IngredientVo;
import com.yuzi.denture.domain.Ingredient;
import com.yuzi.denture.domain.IngredientPurchaseRecord;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by liyou on 2018/9/19.
 */
public class IngredientAssembler {

    public static List<IngredientVo> toVos(List<Ingredient> ingredients) {
        if(ingredients == null) {
            return Collections.emptyList();
        }
        List<IngredientVo> vos = new ArrayList<>(ingredients.size());
        for(Ingredient ingredient:ingredients) {
            IngredientVo vo = IngredientAssembler.toVo(ingredient);
            vos.add(vo);
        }
        return vos;
    }

    public static IngredientVo toVo(Ingredient ingredient) {
        if(ingredient == null)
            return null;
        IngredientVo vo = new IngredientVo();
        BeanUtils.copyProperties(ingredient, vo);
        vo.setRecords(toRecordVos(ingredient.getPurchaseRecords()));
        return vo;
    }

    public static List<IngredientPurchaseRecordVo> toRecordVos(List<IngredientPurchaseRecord> records) {
        if(records == null) {
            return Collections.emptyList();
        }
        List<IngredientPurchaseRecordVo> vos = new ArrayList<>(records.size());
        for(IngredientPurchaseRecord record : records) {
            IngredientPurchaseRecordVo vo = toRecordVo(record);
            vos.add(vo);
        }
        return vos;
    }

    public static IngredientPurchaseRecordVo toRecordVo(IngredientPurchaseRecord record) {
        if(record == null)
            return null;
        IngredientPurchaseRecordVo vo = new IngredientPurchaseRecordVo();
        BeanUtils.copyProperties(record, vo);
        return vo;
    }
}
