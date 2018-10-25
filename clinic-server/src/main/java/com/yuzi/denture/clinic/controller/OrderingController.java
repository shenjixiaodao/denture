package com.yuzi.denture.clinic.controller;


import com.yuzi.denture.clinic.controller.assembler.DentureOrderAssembler;
import com.yuzi.denture.clinic.controller.base.WebResult;
import com.yuzi.denture.clinic.domain.DentureOrder;
import com.yuzi.denture.clinic.domain.type.*;
import com.yuzi.denture.clinic.repository.ClinicRepository;
import com.yuzi.denture.clinic.service.ClinicService;
import com.yuzi.denture.clinic.controller.vo.DentureOrderVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping(value = "/denture/clinic/ordering")
@Api(value = "clinic", description = "下单相关接口")
public class OrderingController {

    private static Logger logger = LoggerFactory.getLogger(OrderingController.class);

    @Autowired
    private ClinicService service;
    @Autowired
    private ClinicRepository repository;


    @ApiOperation(value = "下单", response = WebResult.class, httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "factoryId", dataType = "long",
                    required = true, value = "工厂ID"),
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
                    required = true, value = "缺牙区[DaMaAn(大马鞍)\n" +
                    "PianCe(偏侧型)\n" +
                    "ZiDan(子弹型)\n" +
                    "XiaoMaAn(小马鞍)\n" +
                    "WeiSheng(卫生型)\n" +
                    "ShenRu(深入型)]"),
            @ApiImplicitParam(paramType = "form", name = "biteLevel", dataType = "string",
                    required = true, value = "咬合度[YaoMi(咬密)\n" +
                    "QingYao(轻咬)\n" +
                    "BuYao(不咬)]"),
            @ApiImplicitParam(paramType = "form", name = "borderType", dataType = "string",
                    required = true, value = "邻接(BorderType)[Plan(面接)\n" +
                    "Point(点接)\n" +
                    "Normal(正常)]"),
            @ApiImplicitParam(paramType = "form", name = "neckType", dataType = "string",
                    required = true, value = "颈缘(NeckType)[AnJianTai(按肩台)\n" +
                    "Top(龈上边缘)\n" +
                    "Below(龈下边缘)]"),
            @ApiImplicitParam(paramType = "form", name = "innerCrowType", dataType = "string",
                    required = true, value = "InnerCrowType(内冠)[Normal(正常)\n" +
                    "Tight(紧)\n" +
                    "Loose(松)]"),
            @ApiImplicitParam(paramType = "form", name = "paddingType", dataType = "string",
                    required = true, value = "PaddingType[PiDuiYao(批对咬)\n" +
                    "PiJiYa(批基牙)\n" +
                    "ZuoYaoJin(做咬金)\n" +
                    "LouDianJin(露点金)\n" +
                    "Unknown(待问)]"),
            @ApiImplicitParam(paramType = "form", name = "outerCrowType", dataType = "string",
                    required = true, value = "OuterCrowType(牙冠)[SheCe(舌侧)\n" +
                    "JinTop(全金属边)\n" +
                    "ThreeQuarter(松)\n" +
                    "SC_TQ(舌侧金属3/4颌侧)\n" +
                    "JinBelow(金属颌侧)\n" +
                    "AllCi(舌侧全瓷边)\n"),
    })
    @ResponseBody
    @RequestMapping(value = "/order", method = POST)
    public WebResult order(Long factoryId, String comment,
                           String positions, String type, String specification, String colorNo,
                           String fieldType, String biteLevel, String borderType, String neckType,
                           String innerCrowType, String paddingType, String outerCrowType) {
        //todo UnionID从db中获取
        Long clinicId = 1L;
        Long dentistId = 1L;
        logger.info("录入订单:clinicId={}, dentistId={}, comment={}, positions={}, " +
                        "type={}, specification={}, colorNo={} ",clinicId, dentistId, comment,
                positions, type, specification, colorNo);
        WebResult result = WebResult.execute(res -> {
            DentureOrder order = service.createOrderAndDenture(clinicId, dentistId, factoryId, comment, positions,
                    DentureType.typeOf(type), SpecType.typeOf(specification), colorNo, FieldType.typeOf(fieldType),
                    BiteLevel.typeOf(biteLevel), BorderType.typeOf(borderType), NeckType.typeOf(neckType),
                    InnerCrowType.typeOf(innerCrowType), PaddingType.typeOf(paddingType), OuterCrowType.typeOf(outerCrowType));
            DentureOrderVo vo = DentureOrderAssembler.toVo(order);
            res.setData(vo);
            logger.info("录入订单成功");
        }, "录入订单错误", logger);
        return result;
    }

    @ApiOperation(value = "查询义齿订单信息", response = DentureOrderVo.class, httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", dataType = "String", required = true, value = "订单编号")
    })
    @ResponseBody
    @RequestMapping(value = "/queryOrderById", method = GET)
    public WebResult<DentureOrderVo> queryOrderById(String id) {
        logger.info("查询订单:id={}", id);
        WebResult<DentureOrderVo> result = WebResult.execute(res -> {
            // todo
        }, "查询订单错误", logger);
        return result;
    }

    @ApiOperation(value = "查询订单列表", response = DentureOrderVo.class, httpMethod = "GET")
    @ResponseBody
    @RequestMapping(value = "/queryOrders", method = GET)
    public WebResult<List<DentureOrderVo>> queryOrders() {
        //todo 从session中获取clinicId
        Long clinicId = 1L;
        logger.info("查询订单:clinicId={}", clinicId);
        WebResult<List<DentureOrderVo>> result = WebResult.execute(res -> {
            List<DentureOrder> orders = repository.orders(clinicId);
            List<DentureOrderVo> vos = DentureOrderAssembler.toVos(orders);
            res.setData(vos);
        }, "查询订单错误", logger);
        return result;
    }
}
