package com.yuzi.denture.clinic.service;


import com.yuzi.denture.clinic.domain.DentureOrder;
import com.yuzi.denture.clinic.domain.type.*;

public interface ClinicService {

    DentureOrder createOrderAndDenture(Long clinicId, Long dentistId, Long factoryId, String comment,
                                       String positions, DentureType type, SpecType specification, String colorNo,
                                       FieldType fieldType, BiteLevel biteLevel, BorderType borderType, NeckType neckType,
                                       InnerCrowType innerCrowType, PaddingType paddingType, OuterCrowType outerCrowType);
}
