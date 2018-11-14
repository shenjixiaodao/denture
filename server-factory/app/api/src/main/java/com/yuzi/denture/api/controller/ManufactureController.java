package com.yuzi.denture.api.controller;

import com.yuzi.denture.api.assembler.*;
import com.yuzi.denture.api.session.SessionManager;
import com.yuzi.denture.api.vo.*;
import com.yuzi.denture.api.vo.base.DentureVo;
import com.yuzi.denture.api.vo.base.WebResult;
import com.yuzi.denture.domain.*;
import com.yuzi.denture.domain.GroupType;
import com.yuzi.denture.domain.criteria.DentureCriteria;
import com.yuzi.denture.domain.repository.FactoryRepository;
import com.yuzi.denture.domain.service.FactoryService;
import com.yuzi.denture.domain.type.*;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
    public WebResult<DentureVo> queryByDentureId(String dentureId, HttpServletRequest request) {
        logger.info("查询义齿信息息:dentureId={}", dentureId);
        FactoryUser user = SessionManager.Instance().user(request);
        GroupType group = user.getGroupType();
        WebResult<DentureVo> result = new WebResult<>();
        try {
            Denture denture = repository.findDenture(dentureId);
            //filter procedure by group
            if(group!=GroupType.Comprehensive) {
                denture.filterGroup(group);
            }
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
                    "        Other(\"其他\")]"),
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
                    required = true, value = "制作要求")
    })
    @ResponseBody
    @RequestMapping(value = "/recordOrder", method = POST)
    public WebResult<DentureVo> recordOrder(Long clinicId, Long dentistId, String comment,
                                            String positions, String type, String specification,
                                            String colorNo, String fieldType, String biteLevel, String borderType,
                                            String neckType, String innerCrownType, String paddingType,
                                            String outerCrownType, String requirement, HttpServletRequest request) {
        FactoryUser user = SessionManager.Instance().user(request);
        Long factoryId = user.getFactoryId();
        logger.info("录入订单:clinicId={}, dentistId={}, comment={}, positions={}, " +
                        "type={}, specification={}, colorNo={} ",clinicId, dentistId, comment,
                positions, type, specification, colorNo);
        WebResult<DentureVo> result = WebResult.execute(res -> {
            Denture denture = service.createOrderAndDenture(clinicId, dentistId, factoryId, comment, positions,
                    Denture.DentureType.typeOf(type), specification, colorNo,
                    FieldType.typeOf(fieldType), BiteLevel.typeOf(biteLevel), BorderType.typeOf(borderType),
                    NeckType.typeOf(neckType), InnerCrownType.typeOf(innerCrownType), PaddingType.typeOf(paddingType),
                    OuterCrownType.typeOf(outerCrownType), requirement);
            DentureVo vo = DentureAssembler.toVo(denture);
            res.setData(vo);
            logger.info("录入订单成功");
        }, "录入订单错误", logger);
        return result;
    }

    @ApiOperation(value = "业务人员录入客户", response = WebResult.class, httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "name", dataType = "long",
                    required = true, value = "诊所名"),
            @ApiImplicitParam(paramType = "form", name = "contact", dataType = "long",
                    required = true, value = "联系方式"),
            @ApiImplicitParam(paramType = "form", name = "region", dataType = "long",
                    required = true, value = "所在区域"),
            @ApiImplicitParam(paramType = "form", name = "address", dataType = "long",
                    required = true, value = "地址"),
            @ApiImplicitParam(paramType = "form", name = "dentistName", dataType = "long",
                    required = true, value = "医生")
    })
    @ResponseBody
    @RequestMapping(value = "/recordCustomer", method = POST)
    public WebResult recordCustomer(String name, String contact, String region, String address, String dentistName,
                                    HttpServletRequest request) {
        FactoryUser user = SessionManager.Instance().user(request);
        Long factoryId = user.getFactoryId();
        Long uid = user.getId();
        logger.info("录入诊所客户:name={},contact={},address={},dentistName={}",name, contact, address, dentistName);
        WebResult result = WebResult.execute(res -> {
            service.addCustomer(factoryId, uid, name, contact, region, address, dentistName);
            logger.info("添加客户成功");
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

    @ApiOperation(value = "业务员查询名下跟踪订单", response = DentureOrderVo.class, httpMethod = "GET")
    @ResponseBody
    @RequestMapping(value = "/salesmanQueryOrders", method = GET)
    public WebResult<List<DentureOrderVo>> salesmanQueryOrders(HttpServletRequest request) {
        FactoryUser user = SessionManager.Instance().user(request);
        Long factorId = user.getFactoryId(); //对应工厂的订单
        Long uid = 1L;
        logger.info("查询订单:uid={}", uid);
        WebResult<List<DentureOrderVo>> result = WebResult.execute(res -> {
            List<DentureOrder> orders = repository.findOrdersByUid(uid);
            List<DentureOrderVo> vos = DentureOrderAssembler.toVos(orders);
            res.setData(vos);
        }, "查询订单错误", logger);
        return result;
    }

    //comprehensive user api
    @ApiOperation(value = "义齿综合管理查询", response = DentureVo.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/queryDenturesByCriteria", method = POST)
    public WebResult<List<DentureVo>> queryDenturesByCriteria(@RequestBody DentureCriteriaVo criteriaVo,
                                                            HttpServletRequest request) {
        FactoryUser user = SessionManager.Instance().user(request);
        Long factoryId = user.getFactoryId();
        logger.info("查询订单:criteriaVo={}", criteriaVo);
        Denture.ComprehensiveStatus s = Denture.ComprehensiveStatus.typeOf(criteriaVo.getStatus());
        WebResult<List<DentureVo>> result = WebResult.execute(res -> {
            List<Denture> dentures;
            DentureCriteria criteria = new DentureCriteria();
            BeanUtils.copyProperties(criteriaVo, criteria);
            criteria.setFactoryId(factoryId);
            if(s==Denture.ComprehensiveStatus.Waiting) {
                dentures = repository.findWaitingDentures(criteria);
            } else if(s==Denture.ComprehensiveStatus.Doing) {
                dentures = repository.findDoingDentures(criteria);
            } else {
                dentures = repository.findDoneDentures(criteria);
            }
            List<DentureVo> vos = DentureAssembler.toVos(dentures);
            res.setData(vos);
        }, "查询订单错误", logger);
        return result;
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
            @ApiImplicitParam(paramType = "form", name = "estimatedDuration", dataType = "double",
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
    public WebResult<DentureVo> review(String id, Double estimatedDuration, String basePrice,
                                       String factoryPrice, String requirement, String reviewResult,
                                       HttpServletRequest request) {
        FactoryUser user = SessionManager.Instance().user(request);
        Long operatorId = user.getId();
        logger.info("审核义齿:operator={}, dentureId={}, reviewResult={}",operatorId, id,
                    reviewResult);

        WebResult<DentureVo> result = new WebResult<>();
        try {
            Denture denture = service.inspectReviewAndStart(id, estimatedDuration, basePrice,
                    factoryPrice, requirement, operatorId, ReviewResult.typeOf(reviewResult));
            DentureVo vo = DentureAssembler.toVo(denture);
            result.setData(vo);
        } catch (Exception ex) {
            logger.warn("审核义齿异常: {}", ex);
            return WebResult.failure(ex.getMessage());
        }
        return result;
    }

    @ApiOperation(value = "新增物料", response = WebResult.class, httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "name", dataType = "string",
                    required = true, value = "物料名称")
    })
    @ResponseBody
    @RequestMapping(value = "/newIngredient", method = POST)
    public WebResult newIngredient(String name, HttpServletRequest request) {
        FactoryUser user = SessionManager.Instance().user(request);
        Long factoryId = user.getFactoryId();
        logger.info("新增物料:name={}", name);
        WebResult result = new WebResult<>();
        try {
            service.newIngredient(name, factoryId);
        } catch (Exception ex) {
            logger.warn("审核义齿异常: {}", ex);
            return WebResult.failure(ex.getMessage());
        }
        return result;
    }

    @ApiOperation(value = "记录新购入物料", response = WebResult.class, httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "ingredientId", dataType = "long",
                    required = true, value = "物料Id"),
            @ApiImplicitParam(paramType = "form", name = "supplierId", dataType = "long",
                    required = true, value = "供应商ID"),
            @ApiImplicitParam(paramType = "form", name = "number", dataType = "double",
                    required = true, value = "购买量"),
            @ApiImplicitParam(paramType = "form", name = "billNo", dataType = "String",
                    required = true, value = "采购单据"),
            @ApiImplicitParam(paramType = "form", name = "price", dataType = "String",
                    required = true, value = "购买单价")
    })
    @ResponseBody
    @RequestMapping(value = "/recordIngredientPurchase", method = POST)
    public WebResult recordIngredientPurchase(String billNo, String price, Long ingredientId,
                                              Long supplierId, Double number) {
        logger.info("记录新购入物料:ingredientId={}, supplierId={}, number={}", ingredientId, supplierId, number);
        WebResult result = new WebResult<>();
        try {
            service.newIngredientPurchaseRecord(billNo, price, ingredientId,supplierId, number);
        } catch (Exception ex) {
            logger.warn("记录新购入物料异常: {}", ex);
            return WebResult.failure(ex.getMessage());
        }
        return result;
    }

    @ApiOperation(value = "申请物料", response = WebResult.class, httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "dentureId", dataType = "string",
                    required = true, value = "义齿ID"),
            @ApiImplicitParam(paramType = "form", name = "ingredientId", dataType = "long",
                    required = true, value = "物料Id"),
            @ApiImplicitParam(paramType = "form", name = "number", dataType = "double",
                    required = true, value = "申请量"),
            @ApiImplicitParam(paramType = "form", name = "comment", dataType = "string",
                    required = true, value = "备注")
    })
    @ResponseBody
    @RequestMapping(value = "/applyIngredient", method = POST)
    public WebResult applyIngredient(String dentureId, Long ingredientId, Double number, String comment,
                                     HttpServletRequest request) {
        FactoryUser user = SessionManager.Instance().user(request);
        Long applicantId = user.getId();
        logger.info("申请物料:ingredientId={}, comment={}, number={}", ingredientId, comment, number);
        WebResult result = new WebResult<>();
        try {
            service.applyIngredient(applicantId, dentureId, ingredientId, number, comment);
        } catch (Exception ex) {
            logger.warn("申请物料异常: {}", ex);
            return WebResult.failure(ex.getMessage());
        }
        return result;
    }

    @ApiOperation(value = "查询物料列表", response = IngredientVo.class, httpMethod = "GET")
    @ResponseBody
    @RequestMapping(value = "/queryIngredients", method = GET)
    public WebResult<List<IngredientVo>> queryIngredients(HttpServletRequest request) {
        FactoryUser user = SessionManager.Instance().user(request);
        Long factoryId = user.getFactoryId();
        logger.info("查询物料列表:factoryId={}",factoryId);

        WebResult<List<IngredientVo>> result = WebResult.execute(res -> {
            List<Ingredient> ingredients = repository.findIngredients(factoryId);
            List<IngredientVo> vos = IngredientAssembler.toVos(ingredients);
            res.setData(vos);
        }, "查询工序列表错误", logger);
        return result;
    }

    @ApiOperation(value = "查询物料详情", response = IngredientVo.class, httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", dataType = "Long", required = true, value = "物料ID")
    })
    @ResponseBody
    @RequestMapping(value = "/queryIngredientById", method = GET)
    public WebResult<IngredientVo> queryIngredientById(Long id) {
        logger.info("查询物料详情:id={}",id);

        WebResult<IngredientVo> result = WebResult.execute(res -> {
            Ingredient ingredient = repository.findIngredient(id);
            IngredientVo vo = IngredientAssembler.toVo(ingredient);
            res.setData(vo);
        }, "查询物料详情错误", logger);
        return result;
    }

    @ApiOperation(value = "新增供应商", response = WebResult.class, httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "name", dataType = "String",
                    required = true, value = "名称"),
            @ApiImplicitParam(paramType = "form", name = "address", dataType = "String",
                    required = true, value = "地址"),
            @ApiImplicitParam(paramType = "form", name = "contact", dataType = "String",
                    required = true, value = "联系方式")
    })
    @ResponseBody
    @RequestMapping(value = "/newSupplier", method = POST)
    public WebResult newSupplier(String name, String address, String contact, HttpServletRequest request) {
        logger.info("新增供应商:name={}, name={}, contact={}", name, name, contact);
        WebResult result = new WebResult<>();
        try {
            FactoryUser user = SessionManager.Instance().user(request);
            Long factoryId = user.getFactoryId();
            Supplier supplier = new Supplier(factoryId, name, address, contact);
            repository.newSupplier(supplier);
        } catch (Exception ex) {
            logger.warn("新增供应商异常: {}", ex);
            return WebResult.failure(ex.getMessage());
        }
        return result;
    }

    @ApiOperation(value = "查询供应商列表", response = SupplierVo.class, httpMethod = "GET")
    @ResponseBody
    @RequestMapping(value = "/querySuppliers", method = GET)
    public WebResult<List<SupplierVo>> querySuppliers(HttpServletRequest request) {
        FactoryUser user = SessionManager.Instance().user(request);
        Long factoryId = user.getFactoryId();
        logger.info("查询供应商列表:factoryId={}",factoryId);

        WebResult<List<SupplierVo>> result = WebResult.execute(res -> {
            List<Supplier> suppliers = repository.findSuppliers(factoryId);
            List<SupplierVo> vos = SupplierAssembler.toVos(suppliers);
            res.setData(vos);
        }, "查询供应商列表错误", logger);
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
            Procedure procedure = service.completeProcedure(pgId, operatorId, name, comment);
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
}
