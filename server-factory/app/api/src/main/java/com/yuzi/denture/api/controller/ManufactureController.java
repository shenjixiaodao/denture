package com.yuzi.denture.api.controller;

import com.yuzi.denture.api.assembler.DentureAssembler;
import com.yuzi.denture.api.assembler.ProcedureAssembler;
import com.yuzi.denture.api.vo.ProcedureVo;
import com.yuzi.denture.api.vo.base.DentureVo;
import com.yuzi.denture.api.vo.base.WebResult;
import com.yuzi.denture.domain.DeliveryInfo;
import com.yuzi.denture.domain.Denture;
import com.yuzi.denture.domain.Procedure;
import com.yuzi.denture.domain.ReviewResult;
import com.yuzi.denture.domain.repository.FactoryRepository;
import com.yuzi.denture.domain.service.FactoryService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by liyou on 2018/9/18.
 */
@RestController
@RequestMapping(value = "/denture/factory/manufacture")
@Api(value = "manufacture", description = "生产相关接口")
public class ManufactureController {

    private static Logger logger = LoggerFactory.getLogger(ManufactureController.class);

    @Autowired
    private FactoryService service;
    @Autowired
    private FactoryRepository repository;

    @ApiOperation(value = "根据快递单号查询义齿信息", response = DentureVo.class, httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "deliveryId", dataType = "String", required = true, value = "快递单号"),
            @ApiImplicitParam(paramType = "query", name = "company", dataType = "String", required = true,
                    value = "快递公司[SF(\"顺丰\"), EMS(\"邮政特快\"), YT(\"圆通\"), ST(\"申通\"), ZT(\"中通\"), TT(\"天天\"), YD(\"韵达\")]")
    })
    @ResponseBody
    @RequestMapping(value = "/queryByDeliveryId", method = GET)
    public WebResult<DentureVo> queryByDeliveryId(String deliveryId, String company) {
        logger.info("根据快递单号查询义齿信息:deliveryId={}, company={}",deliveryId, company);

        WebResult<DentureVo> result = new WebResult<>();
        try {
            Denture denture = repository.findDenture(deliveryId, DeliveryInfo.Company.typeOf(company));
            DentureVo vo = DentureAssembler.toVo(denture);
            result.setData(vo);
        } catch (Exception ex) {
            logger.warn("查询义齿信息异常: {}", ex);
            return WebResult.failure(ex.getMessage());
        }
        return result;
    }

    @ApiOperation(value = "牙模入厂生产审核", response = DentureVo.class, httpMethod = "POST")
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
        logger.info("审核义齿:operator={}, dentureId={}, reviewResult={}",operatorId, dentureId,
                    reviewResult);

        WebResult<DentureVo> result = new WebResult<>();
        try {
            Denture denture = service.inspectReviewAndStart(dentureId, operatorId,
                    ReviewResult.typeOf(reviewResult));
            DentureVo vo = DentureAssembler.toVo(denture);
            result.setData(vo);
        } catch (Exception ex) {
            logger.warn("审核义齿异常: {}", ex);
            return WebResult.failure(ex.getMessage());
        }
        return result;
    }

    @ApiOperation(value = "查询义齿信息", response = DentureVo.class, httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "dentureId", dataType = "String", required = true, value = "义齿ID")
    })
    @ResponseBody
    @RequestMapping(value = "/queryByDentureId", method = GET)
    public WebResult<DentureVo> queryByDentureId(String dentureId) {
        logger.info("查询义齿信息息:dentureId={}", dentureId);

        WebResult<DentureVo> result = new WebResult<>();
        try {
            Denture denture = repository.findDenture(dentureId);
            DentureVo vo = DentureAssembler.toVo(denture);
            result.setData(vo);
        } catch (Exception ex) {
            logger.warn("查询义齿信息异常: {}", ex);
            return WebResult.failure(ex.getMessage());
        }
        return result;
    }

    @ApiOperation(value = "完成一个工序", response = ProcedureVo.class, httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "pgId", dataType = "long",
                    required = true, value = "工序所在操作组ID"),
            @ApiImplicitParam(paramType = "form", name = "procedureName", dataType = "string",
                    required = true, value = "工序名"),
            @ApiImplicitParam(paramType = "form", name = "comment", dataType = "string", value = "备注")
    })
    @ResponseBody
    @RequestMapping(value = "/completeProcedure", method = POST)
    public WebResult<ProcedureVo> completeProcedure(Long pgId, String procedureName, String comment) {
        //todo 从session中获取用户ID
        Long operatorId = 1L;
        logger.info("完成一个工序:operatorId={}, pgId={}, procedureName={}, comment={}",operatorId, pgId,
                procedureName, comment);
        WebResult<ProcedureVo> result = new WebResult<>();
        try {
            Procedure procedure = service.completeProcedure(pgId, operatorId, procedureName, comment);
            ProcedureVo vo = ProcedureAssembler.toVo(procedure);
            result.setData(vo);
        } catch (Exception ex) {
            logger.warn("提交一个工序异常: {}", ex);
            return WebResult.failure(ex.getMessage());
        }
        return result;
    }
}
