package com.yuzi.denture.api.assembler;

import com.yuzi.denture.api.vo.ProcedureGroupVo;
import com.yuzi.denture.domain.FactoryUser;
import com.yuzi.denture.domain.ProcedureGroup;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by liyou on 2018/9/19.
 */
public class ProcedureGroupAssembler {
    public static ProcedureGroupVo toVo(ProcedureGroup group) {
        if(group == null)
            return null;
        ProcedureGroupVo vo = new ProcedureGroupVo();
        BeanUtils.copyProperties(group, vo);
        if(group.getType() != null)
            vo.setType(group.getType().name());
        vo.setUsedIngredients(UsedIngredientAssembler.toVos(group.getUsedIngredients()));
        vo.setProcedures(ProcedureAssembler.toVos(group.getProcedures()));
        vo.setOperator(FactoryUserAssembler.toVo(group.getOperator()));
        vo.setInspector(FactoryUserAssembler.toVo(group.getInspector()));
        return vo;
    }
    public static List<ProcedureGroupVo> toVos(List<ProcedureGroup> groups) {
        if(groups == null)
            return Collections.emptyList();
        List<ProcedureGroupVo> vos = new ArrayList<>(groups.size());
        for(ProcedureGroup group : groups) {
            ProcedureGroupVo vo = toVo(group);
            vos.add(vo);
        }
        return vos;
    }
}
