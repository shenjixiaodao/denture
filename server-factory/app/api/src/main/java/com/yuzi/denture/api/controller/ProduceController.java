package com.yuzi.denture.api.controller;

import com.yuzi.denture.api.vo.base.DentureVo;
import com.yuzi.denture.api.vo.base.WebResult;
import com.yuzi.denture.domain.Denture;
import com.yuzi.denture.domain.ReviewResult;
import com.yuzi.denture.domain.service.FactoryService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by liyou on 2018/9/18.
 */
@RestController
@RequestMapping(value = "/denture/factory/produce")
@Api(value = "produce", description = "生产相关接口")
public class ProduceController {

    private static Logger logger = LoggerFactory.getLogger(ProduceController.class);

    private FactoryService service;

    @ApiOperation(value = "上传附件", response = DentureVo.class, httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "dentureId", dataType = "string",
                    required = true, value = "义齿ID"),
            @ApiImplicitParam(paramType = "form", name = "reviewResult", dataType = "string",
                    required = true, value = "审核结果[Accept, Reject]")
    })
    @ResponseBody
    @RequestMapping(value = "/review", method = POST)
    public WebResult<DentureVo> review(String dentureId, String reviewResult) {
        //todo 从session中获取用户ID
        Long operatorId = 1L;
        if(logger.isInfoEnabled()) {
            logger.info("审核义齿:operator={}, dentureId={}, dentureId",operatorId, dentureId,
                    reviewResult);
        }
        WebResult<DentureVo> result = new WebResult<>();
        try {
            Denture denture = service.inspectReviewAndStart(dentureId, operatorId,
                    ReviewResult.typeOf(reviewResult));

        } catch (Exception ex) {
            logger.warn("注册用户异常: {}", ex);
            return WebResult.failureResult(ex.getMessage());
        }
        return result;
    }

}
