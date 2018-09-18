package com.yuzi.denture.domain.factory.repository;

import com.yuzi.denture.domain.factory.DeliveryInfo;
import com.yuzi.denture.domain.factory.Denture;
import com.yuzi.denture.domain.factory.DentureOrder;

import java.util.List;

public interface FactoryRepository {
    void add(Denture denture);

    /**
     * 牙摸初始入厂，工厂根据物流单号查询 Denture信息
     */
    Denture findByDenture(String deliveryId, DeliveryInfo.Company company);
    /**
     * 牙模进入工厂后，Denture都是通过{@param dentureId}来获取
     */
    Denture findByDenture(String dentureId);

    /**
     * 综合管理员根据工厂ID，查询所有Denture信息
     */
    List<Denture> findDentures(Long factoryId);

    List<DentureOrder> findOrders(Long factoryId);
    DentureOrder findOrder(String orderId);
}
