package com.yuzi.denture.clinic.controller.assembler;


import com.yuzi.denture.clinic.controller.vo.ClinicUserVo;
import com.yuzi.denture.clinic.domain.ClinicUser;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClinicUserAssembler {

    public static List<ClinicUserVo> toVos(List<ClinicUser> users) {
        if(users == null) {
            return Collections.emptyList();
        }
        List<ClinicUserVo> vos = new ArrayList<>(users.size());
        for(ClinicUser user:users) {
            ClinicUserVo vo = toVo(user);
            vos.add(vo);
        }
        return vos;
    }

    public static ClinicUserVo toVo(ClinicUser user){
        if(user == null)
            return null;
        ClinicUserVo vo = new ClinicUserVo();
        BeanUtils.copyProperties(user, vo);
        if(user.getRole() != null) {
            vo.setRole(user.getRole().name());
        }
        vo.setClinic(ClinicAssembler.toVo(user.getClinic()));
        return vo;
    }
}
