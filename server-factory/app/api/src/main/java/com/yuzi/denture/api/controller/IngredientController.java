package com.yuzi.denture.api.controller;

import com.yuzi.denture.api.assembler.*;
import com.yuzi.denture.api.session.SessionManager;
import com.yuzi.denture.api.vo.*;
import com.yuzi.denture.api.vo.base.WebResult;
import com.yuzi.denture.domain.*;
import com.yuzi.denture.domain.repository.FactoryRepository;
import com.yuzi.denture.domain.service.FactoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by liyou on 2018/9/18.
 */
@RestController
@RequestMapping(value = "/denture/factory/ingredient")
@Api(value = "ingredient", description = "生产相关接口")
public class IngredientController {

    private static Logger logger = LoggerFactory.getLogger(IngredientController.class);

    @Autowired
    private FactoryService service;
    @Autowired
    private FactoryRepository repository;

    @ApiOperation(value = "新增物料", response = WebResult.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/newIngredient", method = POST)
    public WebResult newIngredient(@RequestBody Ingredient ingredient, HttpServletRequest request) {
        FactoryUser user = SessionManager.Instance().user(request);
        Long factoryId = user.getFactoryId();
        logger.info("新增物料:{}", ingredient);
        WebResult result = new WebResult<>();
        try {
            ingredient.setFactoryId(factoryId);
            service.newIngredient(ingredient);
        } catch (Exception ex) {
            logger.warn("新增物料异常: {}", ex);
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
        Long factoryId = user.getFactoryId();
        logger.info("申请物料:ingredientId={}, comment={}, number={}", ingredientId, comment, number);
        WebResult result = new WebResult<>();
        try {
            service.applyIngredient(applicantId, dentureId, ingredientId, number, comment, factoryId);
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
                    required = true, value = "联系方式"),
            @ApiImplicitParam(paramType = "form", name = "contacter", dataType = "String",
                    required = true, value = "联系人"),
            @ApiImplicitParam(paramType = "form", name = "phone", dataType = "String",
                    required = true, value = "手机")
    })
    @ResponseBody
    @RequestMapping(value = "/newSupplier", method = POST)
    public WebResult newSupplier(String name, String address, String contact, String contacter, String phone,
                                 HttpServletRequest request) {
        logger.info("新增供应商:name={}, name={}, contact={}", name, name, contact);
        WebResult result = new WebResult<>();
        try {
            FactoryUser user = SessionManager.Instance().user(request);
            Long factoryId = user.getFactoryId();
            Supplier supplier = new Supplier(factoryId, name, address, contact);
            supplier.setContacter(contacter);
            supplier.setPhone(phone);
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

    @ApiOperation(value = "记录物料使用记录", response = WebResult.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/useIngredient", method = POST)
    public WebResult useIngredient(@RequestBody UsedIngredient ingredient) {
        WebResult result = WebResult.execute(res -> {
            repository.useIngredient(ingredient);
        }, "记录物料使用记录", logger);
        return result;
    }
}
