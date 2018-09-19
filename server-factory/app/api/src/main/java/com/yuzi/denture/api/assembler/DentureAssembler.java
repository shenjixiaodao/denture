package com.yuzi.denture.api.assembler;

import com.yuzi.denture.api.vo.base.DentureVo;
import com.yuzi.denture.domain.Denture;
import org.springframework.beans.BeanUtils;

/**
 * Created by liyou on 2018/9/19.
 */
public class DentureAssembler {

    public static DentureVo toVo(Denture denture) {
        if(denture == null)
            return null;
        DentureVo vo = new DentureVo();
        BeanUtils.copyProperties(denture, vo);
        vo.setType(denture.getType().name());
        vo.setSpecification(denture.getSpecification().name());
        vo.setClinic(ClinicAssembler.toVo(denture.getClinic()));
        vo.setFactory(FactoryAssembler.toVo(denture.getFactory()));
        vo.setModelInspector(FactoryUserAssembler
                .toVo(denture.getModelInspector()));

        return vo;
    }
}
