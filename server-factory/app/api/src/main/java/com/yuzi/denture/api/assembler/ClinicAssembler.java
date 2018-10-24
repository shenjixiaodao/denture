package com.yuzi.denture.api.assembler;

import com.yuzi.denture.api.vo.ClinicVo;
import com.yuzi.denture.domain.Clinic;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by liyou on 2018/9/19.
 */
public class ClinicAssembler {

    public static List<ClinicVo> toVos(List<Clinic> clinics) {
        if(clinics == null)
            return Collections.emptyList();
        List<ClinicVo> vos = new ArrayList<>(clinics.size());
        for(Clinic clinic:clinics) {
            ClinicVo vo = toVo(clinic);
            vos.add(vo);
        }
        return vos;
    }

    public static ClinicVo toVo(Clinic clinic) {
        if(clinic == null)
            return null;
        ClinicVo vo = new ClinicVo();
        BeanUtils.copyProperties(clinic, vo);
        vo.setUsers(ClinicUserAssembler.toVos(clinic.getUsers()));
        return vo;
    }
}
