package com.yuzi.denture.api.assembler;

import com.yuzi.denture.api.vo.DeliveryInfoVo;
import com.yuzi.denture.domain.DeliveryInfo;
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
