package com.yuzi.denture.api.assembler;

import com.yuzi.denture.api.vo.DentureOrderVo;
import com.yuzi.denture.domain.DentureOrder;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DentureOrderAssembler {
    public static DentureOrderVo toVo(DentureOrder order) {
        if(order == null)
            return null;
        DentureOrderVo vo = new DentureOrderVo();
        BeanUtils.copyProperties(order, vo);
        vo.setStatus(order.getStatus().name());
        vo.setClinic(ClinicAssembler.toVo(order.getClinic()));
        vo.setFactory(FactoryAssembler.toVo(order.getFactory()));
        vo.setModelDeliveryInfo(DeliveryInfoAssembler.toVo(order.getModelDeliveryInfo()));
        vo.setDentureDeliveryInfo(DeliveryInfoAssembler.toVo(order.getDentureDeliveryInfo()));
        vo.setDentist(ClinicUserAssembler.toVo(order.getDentist()));
        vo.setPatient(PatientAssembler.toVo(order.getPatient()));
        vo.setRecorder(ClinicUserAssembler.toVo(order.getRecorder()));
        return vo;
    }

    public static List<DentureOrderVo> toVos(List<DentureOrder> orders) {
        if(orders == null)
            return Collections.emptyList();
        List<DentureOrderVo> vos = new ArrayList<>(orders.size());
        for(DentureOrder order: orders) {
            DentureOrderVo vo = toVo(order);
            vos.add(vo);
        }
        return vos;
    }
}
