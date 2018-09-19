package com.yuzi.denture.domain.service;


import com.yuzi.denture.domain.Denture;
import com.yuzi.denture.domain.Procedure;
import com.yuzi.denture.domain.ReviewResult;

public interface FactoryService {

    /**
     * 牙模查验, {@param inspector}
     * 生产部和质量部负负责人review，后台自动根据根据工厂信息指派负责人
     */
    Denture inspectReviewAndStart(String dentureId, Long inspector, ReviewResult reviewResult);

    /**
     * 后台自动完成，时间等信息补充
     * @param pgId 完成工序所属的工序组ID
     */
    Procedure completeProcedure(Long pgId, Long operatorId, String procedureName, String comment);
}
