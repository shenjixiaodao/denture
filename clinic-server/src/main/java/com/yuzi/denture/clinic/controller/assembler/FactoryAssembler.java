package com.yuzi.denture.clinic.controller.assembler;


import com.yuzi.denture.clinic.controller.vo.FactoryVo;
import com.yuzi.denture.clinic.domain.Factory;
import org.springframework.beans.BeanUtils;

/**
 * Created by liyou on 2018/9/19.
 */
public class FactoryAssembler {
    public static FactoryVo toVo(Factory factory) {
        if(factory == null)
            return null;
        FactoryVo vo = new FactoryVo();
        BeanUtils.copyProperties(factory, vo);
        return vo;
    }
}
