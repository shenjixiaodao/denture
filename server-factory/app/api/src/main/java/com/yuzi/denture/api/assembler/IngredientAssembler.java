package com.yuzi.denture.api.assembler;

import com.yuzi.denture.api.vo.IngredientVo;
import com.yuzi.denture.domain.Ingredient;
import org.springframework.beans.BeanUtils;

/**
 * Created by liyou on 2018/9/19.
 */
public class IngredientAssembler {
    public static IngredientVo toVo(Ingredient ingredient) {
        if(ingredient == null)
            return null;
        IngredientVo vo = new IngredientVo();
        BeanUtils.copyProperties(ingredient, vo);
        return vo;
    }
}
