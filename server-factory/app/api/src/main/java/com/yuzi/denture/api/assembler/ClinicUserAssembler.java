package com.yuzi.denture.api.assembler;

import com.yuzi.denture.api.vo.ClinicUserVo;
import com.yuzi.denture.domain.ClinicUser;
import org.springframework.beans.BeanUtils;

public class ClinicUserAssembler {
    public static ClinicUserVo toVo(ClinicUser user){
        if(user == null)
            return null;
        ClinicUserVo vo = new ClinicUserVo();
        BeanUtils.copyProperties(user, vo);
        if(user.getRole() != null) {
            vo.setRole(user.getRole().name());
        }
        return vo;
    }
}
