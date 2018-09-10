package com.yuzi.denture.domain.factory.service;


import com.yuzi.denture.domain.factory.Denture;

public interface FactoryService {

    Denture createDenture(String orderId, Long factorId);

}
