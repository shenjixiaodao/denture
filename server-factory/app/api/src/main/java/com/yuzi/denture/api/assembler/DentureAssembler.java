package com.yuzi.denture.api.assembler;

import com.yuzi.denture.api.vo.base.DentureVo;
import com.yuzi.denture.domain.Denture;
import com.yuzi.denture.domain.ReviewResult;
import com.yuzi.denture.domain.type.*;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

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
        vo.setType(denture.getType().text());
        if(denture.getProReview() != null)
            vo.setProReview(denture.getProReview().text());
        if(denture.getQuaReview() != null)
            vo.setQuaReview(denture.getQuaReview().text());
        if(denture.getBiteLevel() != null)
            vo.setBiteLevel(denture.getBiteLevel().text());
        if(denture.getBorderType() != null)
            vo.setBorderType(denture.getBorderType().text());
        if(denture.getFieldType() != null)
            vo.setFieldType(denture.getFieldType().text());
        if(denture.getInnerCrownType() != null)
            vo.setInnerCrownType(denture.getInnerCrownType().text());
        if(denture.getNeckType() != null)
            vo.setNeckType(denture.getNeckType().text());
        if(denture.getOuterCrownType() != null)
            vo.setOuterCrownType(denture.getOuterCrownType().text());
        if(denture.getPaddingType() != null)
            vo.setPaddingType(denture.getPaddingType().text());
        vo.setClinic(ClinicAssembler.toVo(denture.getClinic()));
        vo.setFactory(FactoryAssembler.toVo(denture.getFactory()));
        vo.setModelInspector(FactoryUserAssembler.toVo(denture.getModelInspector()));
        vo.setAppliedIngredients(AppliedIngredientAssembler.toVos(denture.getAppliedIngredients()));
        vo.setProcedureGroups(ProcedureGroupAssembler.toVos(denture.getProcedureGroups()));
        return vo;
    }

    public static Denture to(DentureVo vo) {
        Denture denture = new Denture();
        BeanUtils.copyProperties(vo, denture);
        //denture.setType(Denture.DentureType.typeOf(vo.getType()));
        if(!StringUtils.isEmpty(vo.getProReview()))
            denture.setProReview(ReviewResult.typeOf(vo.getProReview()));
        if(!StringUtils.isEmpty(vo.getQuaReview()))
            denture.setQuaReview(ReviewResult.typeOf(vo.getQuaReview()));
        /*if(!StringUtils.isEmpty(denture.getBiteLevel()))
            denture.setBiteLevel(BiteLevel.typeOf(vo.getBiteLevel()));
        if(!StringUtils.isEmpty(denture.getBorderType()))
            denture.setBorderType(BorderType.typeOf(vo.getBorderType()));
        if(!StringUtils.isEmpty(denture.getFieldType()))
            denture.setFieldType(FieldType.typeOf(vo.getFieldType()));
        if(!StringUtils.isEmpty(denture.getInnerCrownType()))
            denture.setInnerCrownType(InnerCrownType.typeOf(vo.getInnerCrownType()));
        if(!StringUtils.isEmpty(denture.getNeckType()))
            denture.setNeckType(NeckType.typeOf(vo.getNeckType()));
        if(!StringUtils.isEmpty(denture.getOuterCrownType()))
            denture.setOuterCrownType(OuterCrownType.typeOf(vo.getOuterCrownType()));
        if(!StringUtils.isEmpty(denture.getPaddingType()))
            denture.setPaddingType(PaddingType.typeOf(vo.getPaddingType()));*/
        return denture;
    }
}
