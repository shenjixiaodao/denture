package com.yuzi.denture.api.assembler;

import com.yuzi.denture.api.vo.AppliedIngredientVo;
import com.yuzi.denture.domain.AppliedIngredient;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by liyou on 2018/9/19.
 */
public class AppliedIngredientAssembler {

    public static AppliedIngredientVo toVo(AppliedIngredient ingredient) {
        if(ingredient == null)
            return null;
        AppliedIngredientVo vo = new AppliedIngredientVo();
        BeanUtils.copyProperties(ingredient, vo);
        vo.setIngredient(IngredientAssembler.toVo(ingredient.getIngredient()));
        vo.setApplicant(FactoryUserAssembler.toVo(ingredient.getApplicant()));
        return vo;
    }

    public static List<AppliedIngredientVo> toVos(List<AppliedIngredient> ingredients) {
        if(ingredients == null)
            return Collections.emptyList();
        List<AppliedIngredientVo> vos = new ArrayList<>(ingredients.size());
        for(AppliedIngredient ingredient : ingredients) {
            AppliedIngredientVo vo = toVo(ingredient);
            vos.add(vo);
        }
        return vos;
    }
}
