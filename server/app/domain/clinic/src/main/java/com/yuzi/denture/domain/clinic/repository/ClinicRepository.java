package com.yuzi.denture.domain.clinic.repository;

import com.yuzi.denture.domain.clinic.DentureOrder;

public interface ClinicRepository {

    /**
     * 保存一个义齿订单
     * @param order
     */
    void add(DentureOrder order);
}
