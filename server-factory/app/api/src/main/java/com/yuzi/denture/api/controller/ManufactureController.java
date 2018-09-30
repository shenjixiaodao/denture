package com.yuzi.denture.api.controller;

import com.yuzi.denture.api.assembler.DentureAssembler;
import com.yuzi.denture.api.assembler.DentureOrderAssembler;
import com.yuzi.denture.api.assembler.ProcedureAssembler;
import com.yuzi.denture.api.vo.DentureOrderVo;
import com.yuzi.denture.api.vo.ProcedureVo;
import com.yuzi.denture.api.vo.base.DentureVo;
import com.yuzi.denture.api.vo.base.WebResult;
import com.yuzi.denture.domain.*;
import com.yuzi.denture.domain.repository.FactoryRepository;
import com.yuzi.denture.domain.service.FactoryService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    //salesman user api
    @ApiOperation(value = "业务人员录入订单", response = DentureVo.class, httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "clinicId", dataType = "long",
                    required = true, value = "诊所ID"),
            @ApiImplicitParam(paramType = "form", name = "dentistId", dataType = "long",
                    required = true, value = "医生ID"),
            @ApiImplicitParam(paramType = "form", name = "comment", dataType = "string",
                    required = true, value = "医生备注"),
            @ApiImplicitParam(paramType = "form", name = "positions", dataType = "string",
                    required = true, value = "牙位[牙位号格式: 半位[a|b|c|d]-编号[1-8], eg: a-2（表示左上半第2号）;多个使用\",\"分隔各个牙位号]"),
            @ApiImplicitParam(paramType = "form", name = "colorNo", dataType = "string",
                    required = true, value = "色号"),
            @ApiImplicitParam(paramType = "form", name = "type", dataType = "string",
                    required = true, value = "义齿类型[Fixed(\"定制式固定义齿\"), Mobilizable(\"定制式活动义齿\")]"),
            @ApiImplicitParam(paramType = "form", name = "specification", dataType = "string",
                    required = true, value = "规格" +
                    "       [GuGe(\"钴铬合金\"),\n" +
                    "        GuiJinShuDanGuan(\"贵金属单冠\"),\n" +
                    "        LianGuan(\"连冠（桥、嵌体、贴面）\"),\n" +
                    "        ErYangHuaGao(\"二氧化锆\"),\n" +
                    "        YangHuaGao(\"氧化锆\"),\n" +
                    "        ErYangHuaGuiGuan(\"二氧化硅冠（桥、嵌体、贴面)\"),\n" +
                    "        NieGeHeJinGuan(\"镍铬合金冠\"),\n" +
                    "        NieGeHeJinQiao(\"镍铬合金桥\"),\n" +
                    "        WanZhiZhiJiaKeZhai(\"弯制支架可摘局部义齿\"),\n" +
                    "        ShuZhiJiTuoQuanKou(\"树脂基托全口义齿\"),\n" +
                    "        Other(\"其他\")]")
    })
    @ResponseBody
    @RequestMapping(value = "/recordOrder", method = POST)
    public WebResult<DentureVo> recordOrder(Long clinicId, Long dentistId, String comment,
                                            String positions, String type,
                                            String specification, String colorNo) {
        //todo 从session中获取factoryId
        Long factoryId = 1L;
        logger.info("录入订单:clinicId={}, dentistId={}, comment={}, positions={}, " +
                        "type={}, specification={}, colorNo={} ",clinicId, dentistId, comment,
                positions, type, specification, colorNo);
        WebResult<DentureVo> result = WebResult.execute(res -> {
            Denture denture = service.createOrderAndDenture(clinicId, dentistId, factoryId, comment, positions,
                    Denture.DentureType.typeOf(type), Denture.SpecType.typeOf(specification), colorNo);
            DentureVo vo = DentureAssembler.toVo(denture);
            res.setData(vo);
            logger.info("录入订单成功");
        }, "录入订单错误", logger);
        return result;
    }

    @ApiOperation(value = "业务人员添加工厂客户", response = WebResult.class, httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "clinicId", dataType = "long",
                    required = true, value = "诊所ID")
    })
    @ResponseBody
    @RequestMapping(value = "/addCustomer", method = POST)
    public WebResult addCustomer(Long clinicId) {
        //todo 从session中获取factoryId and uid
        Long factoryId = 1L;
        Long uid = 1L;
        logger.info("添加客户:clinicId={},factoryId={},uid={}  ",clinicId, factoryId, uid);
        WebResult<DentureVo> result = WebResult.execute(res -> {
            service.addCustomer(factoryId, clinicId, uid);
            logger.info("添加客户成功");
        }, "添加客户错误", logger);
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
        }, "录入订单错误", logger);
        return result;
    }

    @ApiOperation(value = "业务员查询名下跟踪订单", response = DentureOrderVo.class, httpMethod = "GET")
    @ResponseBody
    @RequestMapping(value = "/salesmanQueryOrders", method = GET)
    public WebResult<DentureOrderVo> salesmanQueryOrders() {
        //todo 从session中获取用户ID
        Long uid = 1L;
        logger.info("查询订单:uid={}", uid);
        WebResult<DentureOrderVo> result = WebResult.execute(res -> {
            List<DentureOrder> orders = repository.findOrdersByUid(uid);
            List<DentureOrderVo> vos = DentureOrderAssembler.toVos(orders);
            res.setData(vos);
        }, "查询订单错误", logger);
        return result;
    }

    //comprehensive user api
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

    //worker user api
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
