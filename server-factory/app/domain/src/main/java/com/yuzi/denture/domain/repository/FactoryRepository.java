package com.yuzi.denture.domain.repository;

import com.yuzi.denture.domain.DeliveryInfo;
import com.yuzi.denture.domain.Denture;
import com.yuzi.denture.domain.DentureOrder;
import com.yuzi.denture.domain.Procedure;

import java.util.List;

public interface FactoryRepository {
    //void add(Denture denture);
    void update(Denture denture);

    //procedure
    void add(Procedure procedure);
    /**
     * 牙摸初始入厂，工厂根据物流单号查询 Denture信息
     */
    Denture findDenture(String deliveryId, DeliveryInfo.Company company);
    /**
     * 牙模进入工厂后，Denture都是通过{@param dentureId}来获取
     */
    Denture findDenture(String dentureId);
    /**
     * 综合管理员根据工厂ID，查询所有Denture信息
     */
    List<Denture> findDentures(Long factoryId);

    /**
     * 综合管理人员可以通过{@param factoryId} 查询所有工厂的订单
     */
    List<DentureOrder> findOrders(Long factoryId);
    DentureOrder findOrder(String orderId);
}
