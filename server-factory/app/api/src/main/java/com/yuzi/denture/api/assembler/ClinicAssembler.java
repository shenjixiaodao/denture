package com.yuzi.denture.api.assembler;

import com.yuzi.denture.api.vo.ClinicVo;
import com.yuzi.denture.domain.Clinic;
import org.springframework.beans.BeanUtils;

/**
 * Created by liyou on 2018/9/19.
 */
public class ClinicAssembler {
    public static ClinicVo toVo(Clinic clinic) {
        if(clinic == null)
            return null;
        ClinicVo vo = new ClinicVo();
        BeanUtils.copyProperties(clinic, vo);
        return vo;
    }
}
