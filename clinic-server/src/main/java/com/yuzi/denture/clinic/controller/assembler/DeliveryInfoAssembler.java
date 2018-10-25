package com.yuzi.denture.clinic.controller.assembler;

import com.yuzi.denture.clinic.controller.vo.DeliveryInfoVo;
import com.yuzi.denture.clinic.domain.DeliveryInfo;
import org.springframework.beans.BeanUtils;

public class DeliveryInfoAssembler {
    public static DeliveryInfoVo toVo(DeliveryInfo info) {
        if(info == null)
            return null;
        DeliveryInfoVo vo = new DeliveryInfoVo();
        BeanUtils.copyProperties(info, vo);
        return vo;
    }
}
