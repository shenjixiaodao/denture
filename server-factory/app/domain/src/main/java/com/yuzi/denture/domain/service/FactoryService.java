package com.yuzi.denture.domain.service;


import com.yuzi.denture.domain.*;
import com.yuzi.denture.domain.type.*;

import java.util.Date;

public interface FactoryService {

    /**
     * 业务人员通过传统线下方式录入义齿订单；
     * 在此之前，需保证该诊所已加入，并且该诊所被录入业务员添加到工厂的客户记录表中
     * @param clinicId {@link DentureOrder#clinic}
     * @param dentistId {@link DentureOrder#dentist}
     * @param factoryId {@link Denture#factory}
     * @param comment {@link Denture#comment} and {@link DentureOrder#comment}
     * @param positions {@link Denture#positions}
     * @param type {@link Denture#type}
     * @param specification {@link Denture#specification}
     * @param colorNo {@link Denture#colorNo}
     * @return denture
     */
    Denture createOrderAndDenture(Long clinicId, Long dentistId, Long factoryId, String comment,
                                  String positions, Denture.DentureType type, String specification, Integer number,
                                  String colorNo, FieldType fieldType, BiteLevel biteLevel,
                                  BorderType borderType, NeckType neckType, InnerCrownType innerCrowType,
                                  PaddingType paddingType, OuterCrownType outerCrowType, String requirement,
                                  String patientName, Long salesmanId, String salesman,String stage,
                                  Date receivedDate, Long creatorId, String creator, String dentist);
    /**
     * 牙模查验, {@param inspector}
     * 生产部和质量部负负责人review，后台自动根据根据工厂信息指派负责人
     */
    Denture inspectReviewAndStart(String dentureId, Date estimatedDuration, String basePrice,
                                  String factoryPrice, String requirement, Long inspector, ReviewResult reviewResult);

    /**
     * 后台自动完成，时间等信息补充
     * @param pgId 完成工序所属的工序组ID
     */
    Procedure completeProcedure(Long pgId, Long operatorId, String procedureName, String comment);

    //user
    void addFactoryUser(FactoryUser user);
    void modifyUser(FactoryUser user);
    FactoryUser login(String contact, String encryptPWD);
    void modifyPwd(Long uid, String srcPwd, String dstPwd);
    void inviteCustomer(Long factoryId, Long clinicId, Long salesmanId);
    void addCustomer(Long factoryId, Long salesmanId, String name, String contact, String region, String address,
                        String dentistName);
    void modifyCustomer(Long customerId, Long clinicId, Long salesmanId);

    //clinic
    void addClinicUser(ClinicUser user);

    //ingredient
    void newIngredient(String name, Long factoryId, String type);
    void newIngredientPurchaseRecord(String billNo, String price, Long ingredientId, Long supplierId, Double number);
    void applyIngredient(Long uid, String dentureId, Long ingredientId, Double number,
                         String comment, Long factoryId);

    void addProductType(ProductType type);

    void addPriceSheet(PriceSheet price);

    void addDeliveryInfo(String dentureId, String deliveryDate, String deliveryPerson);
}
