package com.yuzi.denture.api.controller;

import com.yuzi.denture.api.assembler.FactoryCustomerAssembler;
import com.yuzi.denture.api.assembler.FactoryUserAssembler;
import com.yuzi.denture.api.session.SessionManager;
import com.yuzi.denture.api.vo.FactoryCustomerVo;
import com.yuzi.denture.api.vo.FactoryUserVo;
import com.yuzi.denture.api.vo.base.WebResult;
import com.yuzi.denture.domain.*;
import com.yuzi.denture.domain.repository.FactoryRepository;
import com.yuzi.denture.domain.service.FactoryService;
import com.yuzi.denture.domain.type.Educational;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by liyou on 2018/9/21.
 */
@RestController
@RequestMapping(value = "/denture/factory/factoryUser")
@Api(value = "FactoryUser", description = "工厂用户相关接口")
public class FactoryUserController {

    private static Logger logger = LoggerFactory.getLogger(FactoryUserController.class);

    @Value("${avatar.location}")
    private String AvatarLocation;
    @Autowired
    private FactoryService service;
    @Autowired
    private FactoryRepository repository;

    @ApiOperation(value = "添加员工", response = WebResult.class, httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "name", dataType = "string",
                    required = true, value = "姓名"),
            @ApiImplicitParam(paramType = "form", name = "contact", dataType = "string",
                    required = true, value = "手机"),
            @ApiImplicitParam(paramType = "form", name = "cardId", dataType = "String",
                    required = true, value = "身份证号"),
            @ApiImplicitParam(paramType = "form", name = "position", dataType = "String",
                    required = true, value = "职位"),
            @ApiImplicitParam(paramType = "form", name = "address", dataType = "String",
                    required = true, value = "住址"),
            @ApiImplicitParam(paramType = "form", name = "role", dataType = "string",
                    required = true, value = "角色\n" +
                    "       [ShiGao(\"石膏技师\"),\n" +
                    "        ShiGaoLeader(\"石膏组长\"),\n" +
                    "        LaXing(\"蜡型技师\"),\n" +
                    "        LaXingLeader(\"蜡型组长\"),\n" +
                    "        CheJin(\"车金技师\"),\n" +
                    "        ChejinLeader(\"车金组长\"),\n" +
                    "        ChongJiao(\"充胶技师\"),\n" +
                    "        ChongjiaoLeader(\"充胶组长\"),\n" +
                    "        ShangCi(\"上瓷技师\"),\n" +
                    "        ShangCiLeader(\"上瓷组长\"),\n" +
                    "        CheCi(\"车瓷\"),\n" +
                    "        CheCiLeader(\"车瓷组长\"),\n" +
                    "        Comprehensive(\"综合管理人员\"),\n" +
                    "        ComprehensiveLeader(\"综合部主管\"),\n" +
                    "        Market(\"市场人员\"),\n" +
                    "        MarketLeader(\"市场主管\"),\n" +
                    "        Management(\"管理层\")]"),
            @ApiImplicitParam(paramType = "form", name = "joinDate", dataType = "string",
                    value = "加入公司时间，格式=[YYYY-mm-dd]")
    })
    @ResponseBody
    @RequestMapping(value = "/add", method = POST)
    public WebResult add(String name, String contact, String cardId, String role,
                         String joinDate, String position, String address, HttpServletRequest request) {
        FactoryUser user = SessionManager.Instance().user(request);
        Long factoryId = user.getFactoryId();
        logger.info("审核义齿:name={}, contact={}, role={}, joinDate={}",name, contact,
                role, joinDate);
        WebResult result = WebResult.success();
        try {
            user = new FactoryUser(factoryId, name, contact, cardId, FactoryRole.Role.typeOf(role));
            user.setAddress(address);
            user.setPosition(position);
            if(!StringUtils.isEmpty(joinDate)) {
                DateFormatter formatter = new DateFormatter("yyyy-mm-dd");
                user.setJoinDate(formatter.parse(joinDate, Locale.CHINA));
            }
            service.addFactoryUser(user);
        } catch (ParseException ex) {
          logger.warn("日期格式错误：{}", ex);
            return WebResult.failure(ex.getMessage());
        } catch (Exception ex) {
            logger.warn("审核义齿异常: {}", ex);
            return WebResult.failure(ex.getMessage());
        }
        return result;
    }

    @ApiOperation(value = "修改用户", response = WebResult.class, httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "uid", dataType = "Long",
                    required = true, value = "用户ID"),
            @ApiImplicitParam(paramType = "form", name = "roles", dataType = "String",
                    required = true, value = "用户角色,多个角色用','分隔"),
            @ApiImplicitParam(paramType = "form", name = "marital", dataType = "Boolean",
                    required = true, value = "婚姻状况"),
            @ApiImplicitParam(paramType = "form", name = "address", dataType = "String",
                    required = true, value = "地址"),
            @ApiImplicitParam(paramType = "form", name = "educational", dataType = "String",
                    required = true, value = "教育状况"),
            @ApiImplicitParam(paramType = "form", name = "cardId", dataType = "String",
                    required = true, value = "身份证号"),
            @ApiImplicitParam(paramType = "form", name = "joinDate", dataType = "String",
                    required = true, value = "入职时间"),
            @ApiImplicitParam(paramType = "form", name = "baseSalary", dataType = "String", value = "基本工资"),
            @ApiImplicitParam(paramType = "form", name = "mealSubsidy", dataType = "String", value = "餐补"),
            @ApiImplicitParam(paramType = "form", name = "trafficSubsidy", dataType = "String", value = "交通补"),
            @ApiImplicitParam(paramType = "form", name = "accommodationSubsidy", dataType = "String", value = "住宿补"),
            @ApiImplicitParam(paramType = "form", name = "commissionRate", dataType = "Double", value = "提成率"),
    })
    @ResponseBody
    @RequestMapping(value = "/modify", method = POST)
    public WebResult<FactoryUserVo> modify(Long uid, String roles, Boolean martial, String address, String educational,
                                           String cardId, String joinDate, String baseSalary, String mealSubsidy,
                                           String trafficSubsidy, String accommodationSubsidy, Double commissionRate) {
        logger.info("修改用户:roles={}",roles);
        WebResult<FactoryUserVo> result = WebResult.execute(res -> {
            FactoryUser user = repository.findUser(uid);
            user.setRoles(roles);
            user.setMarital(martial);
            user.setAddress(address);
            user.setEducational(Educational.typeOf(educational));
            user.setCardId(cardId);
            user.setJoinDate(joinDate);
            user.setBaseSalary(new BigDecimal(baseSalary));
            user.setMealSubsidy(new BigDecimal(mealSubsidy));
            user.setTrafficSubsidy(new BigDecimal(trafficSubsidy));
            user.setAccommodationSubsidy(new BigDecimal(accommodationSubsidy));
            user.setCommissionRate(commissionRate);
            service.modifyUser(user);
            FactoryUserVo vo = FactoryUserAssembler.toVo(user);
            res.setData(vo);
        }, "修改用户错误", logger);
        return result;
    }

    @ApiOperation(value = "登录", response = FactoryUserVo.class, httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "phone", dataType = "string",
                    required = true, value = "手机"),
            @ApiImplicitParam(paramType = "form", name = "password", dataType = "string",
                    required = true, value = "密码")
    })
    @ResponseBody
    @RequestMapping(value = "/login", method = POST)
    public WebResult<FactoryUserVo> login(String phone, String password) {
        logger.info("登录:phone={}, password={}",phone, password);
        WebResult<FactoryUserVo> result = WebResult.execute(res -> {
            FactoryUser user = service.login(phone, password);
            FactoryUserVo vo = FactoryUserAssembler.toVo(user);
            String token = user.token();
            vo.setToken(token);
            SessionManager.Instance().cacheUser(token, user);
            res.setData(vo);
            logger.info("登录成功");
        }, "登录错误", logger);
        return result;
    }

    @ApiOperation(value = "修改密码", response = WebResult.class, httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "srcPwd", dataType = "string",
                    required = true, value = "原始密码"),
            @ApiImplicitParam(paramType = "form", name = "dstPwd", dataType = "string",
                    required = true, value = "修改密码")
    })
    @ResponseBody
    @RequestMapping(value = "/modifyPwd", method = POST)
    public WebResult<FactoryUserVo> modifyPwd(String srcPwd, String dstPwd, HttpServletRequest request) {
        logger.info("修改密码:srcPwd={}, dstPwd={}",srcPwd, dstPwd);
        FactoryUser user = SessionManager.Instance().user(request);
        Long uid = user.getId();
        WebResult result = WebResult.execute(res -> {
            service.modifyPwd(uid, srcPwd, dstPwd);
            logger.info("修改密码成功");
        }, "修改密码错误", logger);
        return result;
    }

    @ApiOperation(value = "添加客户", response = WebResult.class, httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "clinicId", dataType = "long",
                    required = true, value = "诊所ID")
    })
    @ResponseBody
    @RequestMapping(value = "/addCustomer", method = POST)
    public WebResult<FactoryUserVo> addCustomer(Long clinicId, HttpServletRequest request) {
        logger.info("添加客户:clinicId={}",clinicId);
        FactoryUser user = SessionManager.Instance().user(request);
        Long uid = user.getId();
        Long factoryId = user.getFactoryId();
        WebResult result = WebResult.execute(res -> {
            service.inviteCustomer(factoryId, clinicId, uid);
            logger.info("添加客户成功");
        }, "添加客户错误", logger);
        return result;
    }

    @ApiOperation(value = "查询客户列表", response = FactoryCustomerVo.class, httpMethod = "GET")
    @ResponseBody
    @RequestMapping(value = "/customers", method = GET)
    public WebResult<List<FactoryCustomer>> customers(HttpServletRequest request) {
        FactoryUser user = SessionManager.Instance().user(request);
        Long uid = user.getId();
        WebResult<List<FactoryCustomer>> result = WebResult.execute(res -> {
            List<FactoryCustomer> customers = repository.findCustomersByUid(uid);
            List<FactoryCustomerVo> vos = FactoryCustomerAssembler.toVos(customers);
            res.setData(vos);
            logger.info("查询客户列表成功");
        }, "查询客户列表错误", logger);
        return result;
    }

    @ApiOperation(value = "查询客户详情", response = FactoryCustomerVo.class, httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", dataType = "Long", required = true, value = "客户ID")
    })
    @ResponseBody
    @RequestMapping(value = "/customer", method = GET)
    public WebResult<FactoryCustomerVo> customer(Long id) {
        WebResult<FactoryCustomerVo> result = WebResult.execute(res -> {
            FactoryCustomer customer = repository.findCustomerDetail(id);
            FactoryCustomerVo vo = FactoryCustomerAssembler.toVo(customer);
            res.setData(vo);
            logger.info("查询客户列表成功");
        }, "查询客户列表错误", logger);
        return result;
    }

    @ApiOperation(value = "查询用户列表", response = FactoryUserVo.class, httpMethod = "GET")
    @ResponseBody
    @RequestMapping(value = "/users", method = GET)
    public WebResult<FactoryUserVo> users(HttpServletRequest request) {
        FactoryUser user = SessionManager.Instance().user(request);
        Long factoryId = user.getFactoryId();
        WebResult<FactoryUserVo> result = WebResult.execute(res -> {
            List<FactoryUser> users = repository.findUsers(factoryId);
            List<FactoryUserVo> vos = FactoryUserAssembler.toVos(users);
            res.setData(vos);
            logger.info("查询用户列表成功");
        }, "查询用户列表错误", logger);
        return result;
    }

    @ApiOperation(value = "查询用户信息", response = FactoryUserVo.class, httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "uid", dataType = "Long", required = true, value = "用户ID")
    })
    @ResponseBody
    @RequestMapping(value = "/user", method = GET)
    public WebResult<FactoryUserVo> user(Long uid) {
        WebResult<FactoryUserVo> result = WebResult.execute(res -> {
            FactoryUser user = repository.findUser(uid);
            FactoryUserVo vo = FactoryUserAssembler.toVo(user);
            res.setData(vo);
            logger.info("查询用户信息成功");
        }, "查询用户信息错误", logger);
        return result;
    }

    @ApiOperation(value = "修改头像", response = String.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/changeAvatar", method = POST)
    public WebResult<String> changeAvatar(@RequestPart MultipartFile avatar, HttpServletRequest request) {
        logger.info("修改头像");
        FactoryUser user = SessionManager.Instance().user(request);
        Long uid = user.getId();
        WebResult<String> result = WebResult.execute(res -> {
            String filename = avatar.getOriginalFilename();
            filename = uid + filename.substring(filename.lastIndexOf("."));
            File destFile = new File(AvatarLocation + File.separator + filename);
            try {
                avatar.transferTo(destFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(!destFile.exists()) {
                throw new RuntimeException("文件上传失败");
            }
            user.setAvatar(filename);
            service.modifyUser(user);
            res.setData(filename);
            logger.info("修改头像成功");
        }, "修改头像错误", logger);
        return result;
    }
}
