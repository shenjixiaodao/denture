package com.yuzi.denture.domain.service;


import com.yuzi.denture.domain.*;

public interface FactoryService {

    /**
     * 业务人员通过传统线下方式录入义齿对象；
     * 在此之前，需保证该诊所已加入
     */
    Denture createDenture();
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

    //user
    void addFactoryUser(FactoryUser user);
    FactoryUser login(String contact, String encryptPWD);
    void modifyPwd(Long uid, String srcPwd, String dstPwd);
    void addCustomer(Long factoryId, Long clinicId, Long salesmanId);
    void modifyCustomer(Long customerId, Long clinicId, Long salesmanId);
}
