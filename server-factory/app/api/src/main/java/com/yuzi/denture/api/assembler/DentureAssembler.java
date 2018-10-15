package com.yuzi.denture.api.assembler;

import com.yuzi.denture.api.vo.base.DentureVo;
import com.yuzi.denture.domain.Denture;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by liyou on 2018/9/19.
 */
public class DentureAssembler {

    public static List<DentureVo> toVos(List<Denture> dentures) {
        if(dentures == null)
            return Collections.emptyList();
        List<DentureVo> vos = new ArrayList<>(dentures.size());
        for(int index=0; index<dentures.size(); index++) {
            DentureVo vo = toVo(dentures.get(index));
            vo.setIndex(index);
            vos.add(vo);
        }
        return vos;
    }

    public static DentureVo toVo(Denture denture) {
        if(denture == null)
            return null;
        DentureVo vo = new DentureVo();
        BeanUtils.copyProperties(denture, vo);
        vo.setType(denture.getType().name());
        vo.setSpecification(denture.getSpecification().name());
        vo.setClinic(ClinicAssembler.toVo(denture.getClinic()));
        vo.setFactory(FactoryAssembler.toVo(denture.getFactory()));
        vo.setModelInspector(FactoryUserAssembler.toVo(denture.getModelInspector()));
        vo.setAppliedIngredients(AppliedIngredientAssembler.toVos(denture.getAppliedIngredients()));
        vo.setProcedureGroups(ProcedureGroupAssembler.toVos(denture.getProcedureGroups()));
        return vo;
    }
}
