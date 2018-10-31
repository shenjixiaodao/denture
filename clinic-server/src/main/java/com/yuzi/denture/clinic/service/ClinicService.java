package com.yuzi.denture.clinic.service;


import com.yuzi.denture.clinic.domain.ClinicUser;
import com.yuzi.denture.clinic.domain.DentureOrder;
import com.yuzi.denture.clinic.domain.type.*;

public interface ClinicService {

    ClinicUser login(String contact, String encryptPWD);

    DentureOrder createOrderAndDenture(Long clinicId, Long dentistId, Long factoryId, String comment,
                                       String positions, DentureType type, SpecType specification, String colorNo,
                                       FieldType fieldType, BiteLevel biteLevel, BorderType borderType, NeckType neckType,
                                       InnerCrownType innerCrowType, PaddingType paddingType, OuterCrownType outerCrowType);

    /**
     * @param clinicId
     * @param factoryId
     * @param isValid  0:发起请求 1:  同意 -1：拒绝
     */
    void coRequest(Long clinicId, Long factoryId, Byte isValid);

    ClinicUser register(String phone, String encryptPwd, ClinicUser.ClinicRole role);
}
