package com.yuzi.denture.domain.factory.repository;

import com.yuzi.denture.domain.factory.Denture;
import com.yuzi.denture.domain.factory.DentureOrder;

import java.util.List;

public interface FactoryRepository {
    void add(Denture denture);

    List<DentureOrder> findOrders(Long factoryId);
    DentureOrder findOrder(String orderId);
}
