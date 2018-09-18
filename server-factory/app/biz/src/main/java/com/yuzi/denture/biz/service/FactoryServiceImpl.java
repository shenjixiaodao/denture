package com.yuzi.denture.biz.service;

import com.yuzi.denture.domain.factory.Denture;
import com.yuzi.denture.domain.factory.ReviewResult;
import com.yuzi.denture.domain.factory.service.FactoryService;
import org.springframework.stereotype.Service;

@Service
public class FactoryServiceImpl implements FactoryService {


    @Override
    public Denture inspectReviewAndStart(String dentureId, Long inspector, ReviewResult reviewResult) {
        return null;
    }

    @Override
    public Denture completeProcedure(Long pgId, Long operatorId, String procedureName) {
        return null;
    }
}
