package com.yuzi.denture.clinic.controller.assembler;


import com.yuzi.denture.clinic.controller.vo.FactoryVo;
import com.yuzi.denture.clinic.domain.Factory;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by liyou on 2018/9/19.
 */
public class FactoryAssembler {

    public static List<FactoryVo> toVos(List<Factory> factories) {
        if(factories == null)
            return Collections.emptyList();
        List<FactoryVo> vos = new ArrayList<>(factories.size());
        for(Factory factory:factories) {
            FactoryVo vo = toVo(factory);
            vos.add(vo);
        }
        return vos;
    }

    public static FactoryVo toVo(Factory factory) {
        if(factory == null)
            return null;
        FactoryVo vo = new FactoryVo();
        BeanUtils.copyProperties(factory, vo);
        return vo;
    }
}
