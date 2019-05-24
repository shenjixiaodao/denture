package com.yuzi.denture.api.controller;

import com.yuzi.denture.api.assembler.DentureAssembler;
import com.yuzi.denture.api.assembler.DentureOrderAssembler;
import com.yuzi.denture.api.assembler.ProcedureAssembler;
import com.yuzi.denture.api.session.SessionManager;
import com.yuzi.denture.api.vo.DentureCriteriaVo;
import com.yuzi.denture.api.vo.DentureOrderVo;
import com.yuzi.denture.api.vo.ProcedureGroupVo;
import com.yuzi.denture.api.vo.ProcedureVo;
import com.yuzi.denture.api.vo.base.DentureVo;
import com.yuzi.denture.api.vo.base.WebPageResult;
import com.yuzi.denture.api.vo.base.WebResult;
import com.yuzi.denture.domain.*;
import com.yuzi.denture.domain.criteria.DentureCriteria;
import com.yuzi.denture.domain.repository.FactoryRepository;
import com.yuzi.denture.domain.service.FactoryService;
import com.yuzi.denture.domain.type.*;
import com.yuzi.denture.domain.util.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by liyou on 2018/9/18.
 */
@RestController
@RequestMapping(value = "/denture/factory/denture")
@Api(value = "denture", description = "义齿相关接口")
public class DentureController {

    private static Logger logger = LoggerFactory.getLogger(DentureController.class);

    @Autowired
    private FactoryService service;
    @Autowired
    private FactoryRepository repository;

    @ApiOperation(value = "查询义齿信息", response = DentureVo.class, httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "dentureId", dataType = "String", required = true, value = "义齿ID")
    })
    @ResponseBody
    @RequestMapping(value = "/queryByDentureId", method = GET)
    public WebResult<DentureVo> queryByDentureId(String dentureId, HttpServletRequest request) {
        logger.info("查询义齿信息息:dentureId={}", dentureId);
        FactoryUser user = SessionManager.Instance().user(request);
        GroupType group = user.getGroupType();
        WebResult<DentureVo> result = new WebResult<>();
        try {
            Denture denture = repository.findDenture(dentureId);
            // todo 是否需要针对不同的工序组过滤工序记录
            //filter procedure by group
            /*if(group!=GroupType.Comprehensive) {
                denture.filterGroup(group);
            }*/
            DentureVo vo = DentureAssembler.toVo(denture);
            result.setData(vo);
        } catch (Exception ex) {
            logger.warn("查询义齿信息异常: {}", ex);
            return WebResult.failure(ex.getMessage());
        }
        return result;
    }

    //salesman user api
    @ApiOperation(value = "业务人员录入订单", response = DentureVo.class, httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "clinicId", dataType = "long",
                    required = true, value = "诊所ID"),
            @ApiImplicitParam(paramType = "form", name = "dentistId", dataType = "long",
                    required = true, value = "医生ID"),
            @ApiImplicitParam(paramType = "form", name = "comment", dataType = "string",
                    required = true, value = "医生备注"),
            @ApiImplicitParam(paramType = "form", name = "patientName", dataType = "string",
                    required = true, value = "患者姓名"),
            @ApiImplicitParam(paramType = "form", name = "positions", dataType = "string",
                    required = true, value = "牙位[牙位号格式: 半位[a|b|c|d]-编号[1-8], eg: a-2（表示左上半第2号）;多个使用\",\"分隔各个牙位号]"),
            @ApiImplicitParam(paramType = "form", name = "number", dataType = "Integer",
                    required = true, value = "数量"),
            @ApiImplicitParam(paramType = "form", name = "colorNo", dataType = "string",
                    required = true, value = "色号"),
            @ApiImplicitParam(paramType = "form", name = "type", dataType = "string",
                    required = true, value = "义齿规格[Fixed(\"定制式固定义齿\"), Mobilizable(\"定制式活动义齿\")]"),
            @ApiImplicitParam(paramType = "form", name = "specification", dataType = "string",
                    required = true, value = "具体类型"),
            @ApiImplicitParam(paramType = "form", name = "fieldType", dataType = "string",
                    value = "缺牙区[DaMaAn(大马鞍)\n" +
                            "PianCe(偏侧型)\n" +
                            "ZiDan(子弹型)\n" +
                            "XiaoMaAn(小马鞍)\n" +
                            "WeiSheng(卫生型)\n" +
                            "ShenRu(深入型)]"),
            @ApiImplicitParam(paramType = "form", name = "biteLevel", dataType = "string",
                    value = "咬合度[YaoMi(咬密)\n" +
                            "QingYao(轻咬)\n" +
                            "BuYao(不咬)]"),
            @ApiImplicitParam(paramType = "form", name = "borderType", dataType = "string",
                    value = "邻接(BorderType)[Plan(面接)\n" +
                            "Point(点接)\n" +
                            "Normal(正常)]"),
            @ApiImplicitParam(paramType = "form", name = "neckType", dataType = "string",
                    value = "颈缘(NeckType)[AnJianTai(按肩台)\n" +
                            "Top(龈上边缘)\n" +
                            "Below(龈下边缘)]"),
            @ApiImplicitParam(paramType = "form", name = "innerCrownType", dataType = "string",
                    value = "InnerCrownType(内冠)[Normal(正常)\n" +
                            "Tight(紧)\n" +
                            "Loose(松)]"),
            @ApiImplicitParam(paramType = "form", name = "paddingType", dataType = "string",
                    value = "PaddingType[PiDuiYao(批对咬)\n" +
                            "PiJiYa(批基牙)\n" +
                            "ZuoYaoJin(做咬金)\n" +
                            "LouDianJin(露点金)\n" +
                            "Unknown(待问)]"),
            @ApiImplicitParam(paramType = "form", name = "outerCrownType", dataType = "string",
                    value = "OuterCrownType(牙冠)[SheCe(舌侧)\n" +
                            "JinTop(全金属边)\n" +
                            "ThreeQuarter(松)\n" +
                            "SC_TQ(舌侧金属3/4颌侧)\n" +
                            "JinBelow(金属颌侧)\n" +
                            "AllCi(舌侧全瓷边)\n"),
            @ApiImplicitParam(paramType = "form", name = "requirement", dataType = "string",
                    required = true, value = "制作要求"),
            @ApiImplicitParam(paramType = "form", name = "salesmanId", dataType = "long",
                    required = true, value = "业务员ID"),
            @ApiImplicitParam(paramType = "form", name = "salesman", dataType = "string",
                    required = true, value = "业务员名"),
            @ApiImplicitParam(paramType = "form", name = "stage", dataType = "string",
                    required = true, value = "阶段"),
            @ApiImplicitParam(paramType = "form", name = "receivedDate", dataType = "string",
                    required = true, value = "收件时间"),
            @ApiImplicitParam(paramType = "form", name = "dentist", dataType = "string",
                    required = true, value = "医生"),
            @ApiImplicitParam(paramType = "form", name = "boxNo", dataType = "string",
                    required = true, value = "牙盒编号")
    })
    @ResponseBody
    @RequestMapping(value = "/recordOrder", method = POST)
    public WebResult<DentureVo> recordOrder(Long clinicId, Long dentistId, String comment, String patientName,
                                            String positions, String type, String specification, Integer number,
                                            String colorNo, String fieldType, String biteLevel, String borderType,
                                            String neckType, String innerCrownType, String paddingType,
                                            String outerCrownType, String requirement, Long salesmanId, String salesman,
                                            String stage, String receivedDate, String dentist, String boxNo,
                                            HttpServletRequest request) {
        FactoryUser user = SessionManager.Instance().user(request);
        Long factoryId = user.getFactoryId();
        logger.info("录入订单:clinicId={}, dentistId={}, comment={}, positions={}, " +
                        "type={}, specification={}, colorNo={} ",clinicId, dentistId, comment,
                positions, type, specification, colorNo);
        WebResult<DentureVo> result = WebResult.execute(res -> {
            Denture denture = service.createOrderAndDenture(clinicId, dentistId, factoryId, comment, positions,
                    Denture.DentureType.typeOf(type), specification, number, colorNo,
                    FieldType.typeOf(fieldType), BiteLevel.typeOf(biteLevel), BorderType.typeOf(borderType),
                    NeckType.typeOf(neckType), InnerCrownType.typeOf(innerCrownType), PaddingType.typeOf(paddingType),
                    OuterCrownType.typeOf(outerCrownType), requirement, patientName, salesmanId, salesman, stage,
                    CommonUtil.parseDate(receivedDate), user.getId(), user.getName(), dentist, boxNo);
            DentureVo vo = DentureAssembler.toVo(denture);
            res.setData(vo);
            logger.info("录入订单成功");
        }, "录入订单错误", logger);
        return result;
    }

    @ApiOperation(value = "出货", response = WebResult.class, httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "dentureId", dataType = "String",
                    required = true),
            @ApiImplicitParam(paramType = "form", name = "deliveryDate", dataType = "String",
                    required = true),
            @ApiImplicitParam(paramType = "form", name = "deliveryPerson", dataType = "String",
                    required = true)
    })
    @ResponseBody
    @RequestMapping(value = "/delivery", method = POST)
    public WebResult delivery(String dentureId, String deliveryDate, String deliveryPerson,
                                    HttpServletRequest request) {
        FactoryUser user = SessionManager.Instance().user(request);
        Long factoryId = user.getFactoryId();
        Long uid = user.getId();
        WebResult result = WebResult.execute(res -> {
            service.addDeliveryInfo(dentureId, deliveryDate, deliveryPerson);
        }, "录入诊所客户", logger);
        return result;
    }

    @ApiOperation(value = "查询义齿订单信息", response = DentureOrderVo.class, httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "dentureId", dataType = "String", required = true, value = "义齿编号")
    })
    @ResponseBody
    @RequestMapping(value = "/queryOrderByDentureId", method = GET)
    public WebResult<DentureOrderVo> queryOrderByDentureId(String dentureId) {
        logger.info("查询订单:dentureId={}", dentureId);
        WebResult<DentureOrderVo> result = WebResult.execute(res -> {
            DentureOrder order = repository.findOrder(dentureId);
            DentureOrderVo vo = DentureOrderAssembler.toVo(order);
            res.setData(vo);
        }, "查询订单错误", logger);
        return result;
    }

    @ApiOperation(value = "义齿综合管理查询", response = DentureVo.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/queryDenturesByCriteria", method = POST)
    public WebResult<List<DentureVo>> queryDenturesByCriteria(@RequestBody DentureCriteriaVo criteriaVo,
                                                            HttpServletRequest request) {
        FactoryUser user = SessionManager.Instance().user(request);
        Long factoryId = user.getFactoryId();
        logger.info("查询订单:criteriaVo={}", criteriaVo);
        Denture.Status s = Denture.Status.typeOf(criteriaVo.getStatus());
        WebResult<List<DentureVo>> result = WebResult.execute(res -> {
            List<Denture> dentures;
            DentureCriteria criteria = new DentureCriteria();
            BeanUtils.copyProperties(criteriaVo, criteria);
            criteria.setFactoryId(factoryId);
            // todo
            /*if(s==Denture.Status.Waiting) {
                dentures = repository.findWaitingDentures(criteria);
            } else if(s==Denture.Status.Doing) {
                dentures = repository.findDoingDentures(criteria);
            } else {
                dentures = repository.findDoneDentures(criteria);
            }*/
            // List<DentureVo> vos = DentureAssembler.toVos(dentures);
            // res.setData(vos);
        }, "查询订单错误", logger);
        return result;
    }

    @ApiOperation(value = "义齿查询", response = DentureVo.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/queryDentures", method = POST)
    public WebPageResult<List<DentureVo>> queryDentures(@RequestBody DentureCriteria criteria,
                                                        HttpServletRequest request) {
        FactoryUser user = SessionManager.Instance().user(request);
        Long factoryId = user.getFactoryId();
        logger.info("查询订单:criteria={}", criteria);
        WebPageResult<List<DentureVo>> res = new WebPageResult<>();
        if(!StringUtils.isEmpty(criteria.getDentureId())) {
            criteria.setDentureId(CommonUtil.getFullId(factoryId, criteria.getDentureId()));
        }
        criteria.setFactoryId(factoryId);
        Integer total = repository.countDentures(criteria);
        List<Denture> dentures = repository.findDentures(criteria);
        List<DentureVo> vos = DentureAssembler.toVos(dentures);
        res.setData(vos);
        res.setTotalSize(total);
        return res;
    }

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
            @ApiImplicitParam(paramType = "form", name = "id", dataType = "string",
                    required = true, value = "义齿ID"),
            @ApiImplicitParam(paramType = "form", name = "estimatedDuration", dataType = "string",
                    required = true, value = "预计加工时长"),
            @ApiImplicitParam(paramType = "form", name = "basePrice", dataType = "string",
                    required = true, value = "应收单价"),
            @ApiImplicitParam(paramType = "form", name = "factoryPrice", dataType = "string",
                    required = true, value = "出厂单价"),
            @ApiImplicitParam(paramType = "form", name = "requirement", dataType = "string",
                    required = true, value = "制作要求"),
            @ApiImplicitParam(paramType = "form", name = "reviewResult", dataType = "string",
                    required = true, value = "审核结果[Accept, Reject]")
    })
    @ResponseBody
    @RequestMapping(value = "/review", method = POST)
    public WebResult<DentureVo> review(String id, String estimatedDuration, String basePrice,
                                       String factoryPrice, String requirement, String reviewResult,
                                       HttpServletRequest request) {
        FactoryUser user = SessionManager.Instance().user(request);
        Long operatorId = user.getId();
        logger.info("审核义齿:operator={}, dentureId={}, reviewResult={}",operatorId, id,
                    reviewResult);

        WebResult<DentureVo> result = new WebResult<>();
        try {
            Denture denture = service.inspectReviewAndStart(id, CommonUtil.parseDate(estimatedDuration), basePrice,
                    factoryPrice, requirement, operatorId, ReviewResult.typeOf(reviewResult));
            DentureVo vo = DentureAssembler.toVo(denture);
            result.setData(vo);
        } catch (Exception ex) {
            logger.warn("审核义齿异常: {}", ex);
            return WebResult.failure(ex.getMessage());
        }
        return result;
    }

    //worker user api
    @ApiOperation(value = "完成一个工序", response = ProcedureVo.class, httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "pgId", dataType = "long",
                    required = true, value = "工序所在操作组ID"),
            @ApiImplicitParam(paramType = "form", name = "name", dataType = "string",
                    required = true, value = "工序名"),
            @ApiImplicitParam(paramType = "form", name = "comment", dataType = "string", value = "备注")
    })
    @ResponseBody
    @RequestMapping(value = "/completeProcedure", method = POST)
    public WebResult<ProcedureVo> completeProcedure(Long pgId, String name, String comment, HttpServletRequest request) {
        FactoryUser user = SessionManager.Instance().user(request);
        Long operatorId = user.getId();
        logger.info("完成一个工序:operatorId={}, pgId={}, name={}, comment={}",operatorId, pgId,
                name, comment);
        WebResult<ProcedureVo> result = new WebResult<>();
        try {
            Procedure procedure = new Procedure(pgId, name, operatorId);
            procedure.setComment(comment);
            service.completeProcedure(procedure);
            ProcedureVo vo = ProcedureAssembler.toVo(procedure);
            result.setData(vo);
        } catch (Exception ex) {
            logger.warn("提交一个工序异常: {}", ex);
            return WebResult.failure(ex.getMessage());
        }
        return result;
    }

    @ApiOperation(value = "查询工序列表", response = ProcedureVo.class, httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pgId", dataType = "Long", required = true, value = "工序组编号")
    })
    @ResponseBody
    @RequestMapping(value = "/queryProcedures", method = GET)
    public WebResult<List<ProcedureVo>> queryProcedures(Long pgId) {
        logger.info("查询工序列表:pgId={}",pgId);

        WebResult<List<ProcedureVo>> result = WebResult.execute(res -> {
            List<Procedure> procedures = repository.findProcedures(pgId);
            List<ProcedureVo> vos = ProcedureAssembler.toVos(procedures);
            res.setData(vos);
        }, "查询工序列表错误", logger);
        return result;
    }

    @ApiOperation(value = "查询义齿工序列表", response = ProcedureGroupVo.class, httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "dentureId", dataType = "String", required = true, value = "义齿ID")
    })
    @ResponseBody
    @RequestMapping(value = "/queryProcedureGroups", method = GET)
    public WebResult<List<ProcedureGroupVo>> queryProcedureGroups(Long dentureId) {
        logger.info("查询义齿工序列表:dentureId={}",dentureId);

        WebResult<List<ProcedureGroupVo>> result = WebResult.execute(res -> {
            /*List<Procedure> procedures = repository.findProcedures(pgId);
            List<ProcedureVo> vos = ProcedureAssembler.toVos(procedures);
            res.setData(vos);*/
        }, "查询义齿工序列表错误", logger);
        return result;
    }

    @ApiOperation(value = "新建检验报告", response = InspectionReport.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/newInspectionReport", method = POST)
    public WebResult newInspectionReport(@RequestBody InspectionReport report, HttpServletRequest request) {
        // FactoryUser user = SessionManager.Instance().user(request);
        // Long factoryId = user.getFactoryId();
        WebResult result = WebResult.execute(res -> {
            service.addInspectionReport(report);
            res.setData(report);
        }, "新建检验报告异常", logger);
        return result;
    }

    @ApiOperation(value = "添加检验项", response = InspectionItem.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/newInspectionItem", method = POST)
    public WebResult newInspectionItem(@RequestBody InspectionItem item, HttpServletRequest request) {
        // FactoryUser user = SessionManager.Instance().user(request);
        // Long factoryId = user.getFactoryId();
        WebResult result = WebResult.execute(res -> {
            service.addInspectionItem(item);
            res.setData(item);
        }, "新建检验报告异常", logger);
        return result;
    }
}
