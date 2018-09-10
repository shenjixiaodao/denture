package com.yuzi.denture.domain.clinic.service;

import com.yuzi.denture.domain.clinic.DentureOrder;

public interface ClinicService {
    /**
     * 诊所创建一个订单
     * @param order
     */
    void create(DentureOrder order);
}
