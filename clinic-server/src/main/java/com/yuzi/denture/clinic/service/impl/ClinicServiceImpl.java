package com.yuzi.denture.clinic.service.impl;

import com.yuzi.denture.clinic.domain.ClinicUser;
import com.yuzi.denture.clinic.domain.Denture;
import com.yuzi.denture.clinic.domain.DentureOrder;
import com.yuzi.denture.clinic.domain.ProcedureGroup;
import com.yuzi.denture.clinic.domain.type.*;
import com.yuzi.denture.clinic.repository.ClinicRepository;
import com.yuzi.denture.clinic.service.ClinicService;
import com.yuzi.denture.clinic.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClinicServiceImpl implements ClinicService {

    @Autowired
    private ClinicRepository repository;

    @Override
    public ClinicUser login(String contact, String encryptPWD) {
        ClinicUser user = repository.findUser(contact);
        if(user == null)
            throw new IllegalArgumentException("不存在用户");
        String hashPwd = user.decryptAndHashPwd(encryptPWD);
        if(!user.checkPWD(hashPwd))
            throw new IllegalArgumentException("密码错误");
        return user;
    }

    @Override
    public DentureOrder createOrderAndDenture(Long clinicId, Long dentistId, Long factoryId, String comment,
                                              String positions, DentureType type, SpecType specification,
                                              String colorNo, FieldType fieldType, BiteLevel biteLevel,
                                              BorderType borderType, NeckType neckType, InnerCrownType innerCrowType,
                                              PaddingType paddingType, OuterCrownType outerCrowType) {
        //1, create denture
        Denture denture = new Denture(type, specification, clinicId, comment,
                factoryId, positions, colorNo);
        denture.setId(IdGenerator.generate(factoryId));
        denture.setFieldType(fieldType);
        denture.setBiteLevel(biteLevel);
        denture.setBorderType(borderType);
        denture.setNeckType(neckType);
        denture.setInnerCrownType(innerCrowType);
        denture.setPaddingType(paddingType);
        denture.setOuterCrownType(outerCrowType);
        //初始创建denture时，生成加工所需要的所有工序
        List<ProcedureGroup> groups = denture.generateProcedureGroups();
        repository.batchAddProcedureGroups(groups);
        repository.add(denture);
        //2, create order
        DentureOrder order = new DentureOrder(denture.getId(), clinicId, factoryId, dentistId, comment);
        order.setId(IdGenerator.generate(clinicId));
        repository.add(order);
        return order;
    }

    @Override
    public void coRequest(Long clinicId, Long factoryId, Byte isValid) {
        repository.updateCoRequest(clinicId, factoryId, isValid);
    }

    @Override
    public ClinicUser register(String phone, String encryptPwd, ClinicUser.ClinicRole role) {
        ClinicUser user = new ClinicUser(role, phone, encryptPwd);
        repository.addUser(user);
        return user;
    }
}
