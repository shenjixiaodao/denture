package com.yuzi.denture.biz.service;

import com.yuzi.denture.domain.*;
import com.yuzi.denture.domain.repository.FactoryRepository;
import com.yuzi.denture.domain.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class FactoryServiceImpl implements FactoryService {

    @Autowired
    private FactoryRepository repository;

    @Override
    public Denture inspectReviewAndStart(String dentureId, Long inspectorId, ReviewResult reviewResult) {
        Denture denture = repository.findDenture(dentureId);
        denture.setStartDate(new Date());
        denture.setModelInspectionDate(new Date());
        denture.setModelInspector(new FactoryUser(inspectorId));
        //审核合并执行
        denture.setProReview(reviewResult);
        denture.setProReviewDate(new Date());
        denture.setQuaReview(reviewResult);
        denture.setQuaReviewDate(new Date());
        repository.update(denture);
        return denture;
    }

    @Override
    public Procedure completeProcedure(Long pgId, Long operatorId, String procedureName, String comment) {
        Procedure procedure = new Procedure(pgId, procedureName, operatorId);
        repository.add(procedure);
        return procedure;
    }

    @Transactional
    @Override
    public void addFactoryUser(FactoryUser user) {
        repository.add(user);
        FactoryRole role = user.getRoles().get(0);
        role.setUid(user.getId());
        repository.addRole(role);
    }

    @Override
    public FactoryUser login(String contact, String encryptPWD) {
        FactoryUser user = repository.findUser(contact);
        if(user == null)
            throw new IllegalArgumentException("不存在用户");
        if(!user.checkPWD(encryptPWD))
            throw new IllegalArgumentException("密码错误");
        return user;
    }

    @Override
    public void modifyPwd(Long uid, String srcPwd, String dstPwd) {
        FactoryUser user = repository.findUser(uid);
        if(user == null)
            throw new IllegalArgumentException("用户为空");
        if(user.checkPWD(srcPwd))
            throw new IllegalArgumentException("旧密码验证错误");
        user.setPassword(dstPwd);
        repository.update(user);
    }

    @Override
    public void addCustomer(Long factoryId, Long clinicId, Long salesmanId) {
        FactoryCustomer customer = new FactoryCustomer(factoryId, clinicId, salesmanId);
        repository.addCustomer(customer);
    }

    @Override
    public void modifyCustomer(Long customerId, Long clinicId, Long salesmanId) {
        FactoryCustomer customer = repository.findCustomer(customerId);
        if(clinicId!=null) {
            customer.setClinic(new Clinic(clinicId));
        }
        if(salesmanId != null) {
            customer.setSalesmanId(salesmanId);
        }
        repository.updateCustomer(customer);
    }
}
