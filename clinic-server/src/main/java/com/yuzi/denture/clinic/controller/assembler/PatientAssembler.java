package com.yuzi.denture.clinic.controller.assembler;

import com.yuzi.denture.clinic.controller.vo.PatientVo;
import com.yuzi.denture.clinic.domain.Patient;
import org.springframework.beans.BeanUtils;

public class PatientAssembler {

    public static PatientVo toVo(Patient patient) {
        if(patient == null)
            return null;
        PatientVo vo = new PatientVo();
        BeanUtils.copyProperties(patient, vo);
        return vo;
    }

}
