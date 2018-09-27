package com.yuzi.denture.api.assembler;

import com.yuzi.denture.api.vo.DentureOrderVo;
import com.yuzi.denture.domain.DentureOrder;
import org.springframework.beans.BeanUtils;

public class DentureOrderAssembler {
    public static DentureOrderVo toVo(DentureOrder order) {
        if(order == null)
            return null;
        DentureOrderVo vo = new DentureOrderVo();
        BeanUtils.copyProperties(order, vo);
        vo.setClinic(ClinicAssembler.toVo(order.getClinic()));
        vo.setFactory(FactoryAssembler.toVo(order.getFactory()));
        vo.setModelDeliveryInfo(DeliveryInfoAssembler.toVo(order.getModelDeliveryInfo()));
        vo.setDentureDeliveryInfo(DeliveryInfoAssembler.toVo(order.getDentureDeliveryInfo()));
        vo.setDentist(ClinicUserAssembler.toVo(order.getDentist()));
        vo.setPatient(PatientAssembler.toVo(order.getPatient()));
        vo.setRecorder(ClinicUserAssembler.toVo(order.getRecorder()));
        return vo;
    }
}
