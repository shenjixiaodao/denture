package com.yuzi.denture.biz.service;

import com.yuzi.denture.domain.Denture;
import com.yuzi.denture.domain.FactoryUser;
import com.yuzi.denture.domain.Procedure;
import com.yuzi.denture.domain.ReviewResult;
import com.yuzi.denture.domain.repository.FactoryRepository;
import com.yuzi.denture.domain.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public void addFactoryUser(FactoryUser user) {
        repository.add(user);
    }

    @Override
    public FactoryUser login(String contact, String encryptPWD) {
        FactoryUser user = repository.findUser(contact);
        if(user == null)
            throw new IllegalArgumentException("不存在用户");
        user.checkPWD(encryptPWD);
        return user;
    }
}
