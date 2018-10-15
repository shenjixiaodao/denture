package com.yuzi.denture.api.assembler;

import com.yuzi.denture.api.vo.UsedIngredientVo;
import com.yuzi.denture.domain.UsedIngredient;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by liyou on 2018/9/19.
 */
public class UsedIngredientAssembler {
    public static UsedIngredientVo toVo(UsedIngredient ingredient) {
        if(ingredient == null)
            return null;
        UsedIngredientVo vo = new UsedIngredientVo();
        BeanUtils.copyProperties(ingredient, vo);
        return vo;
    }
    public static List<UsedIngredientVo> toVos(List<UsedIngredient> ingredients) {
        if(ingredients == null)
            return Collections.emptyList();
        List<UsedIngredientVo> vos = new ArrayList<>(ingredients.size());
        for(UsedIngredient ingredient:ingredients) {
            UsedIngredientVo vo = toVo(ingredient);
            vos.add(vo);
        }
        return vos;
    }
}
