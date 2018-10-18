package com.yuzi.denture.api.controller;

import com.yuzi.denture.api.assembler.FactoryUserAssembler;
import com.yuzi.denture.api.session.Cst;
import com.yuzi.denture.api.vo.FactoryUserVo;
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
import org.springframework.format.datetime.DateFormatter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
    public WebResult add(String name, String contact, String role, String joinDate) {
        //todo 从session中获取用户factoryId
        Long factoryId = 1L;
        logger.info("审核义齿:name={}, contact={}, role={}, joinDate={}",name, contact,
                role, joinDate);
        WebResult result = WebResult.success();
        try {
            FactoryUser user = new FactoryUser(factoryId, name, contact, FactoryRole.Role.typeOf(role));
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

    @ApiOperation(value = "登录", response = FactoryUserVo.class, httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "phone", dataType = "string",
                    required = true, value = "手机"),
            @ApiImplicitParam(paramType = "form", name = "password", dataType = "string",
                    required = true, value = "密码")
    })
    @ResponseBody
    @RequestMapping(value = "/login", method = POST)
    public WebResult<FactoryUserVo> login(String phone, String password, HttpServletRequest request) {
        logger.info("登录:phone={}, password={}",phone, password);
        WebResult<FactoryUserVo> result = WebResult.execute(res -> {
            FactoryUser user = service.login(phone, password);
            FactoryUserVo vo = FactoryUserAssembler.toVo(user);
            vo.setToken(user.token());
            request.getSession().setAttribute(Cst.UserKey, user);
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
    public WebResult<FactoryUserVo> modifyPwd(String srcPwd, String dstPwd) {
        logger.info("修改密码:srcPwd={}, dstPwd={}",srcPwd, dstPwd);
        //todo 从session获取用户ID
        Long uid = 1L;
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
    public WebResult<FactoryUserVo> addCustomer(Long clinicId) {
        logger.info("添加客户:clinicId={}",clinicId);
        //todo 从session获取uid和factoryId
        Long uid = 1L;
        Long factoryId = 1L;
        WebResult result = WebResult.execute(res -> {
            service.addCustomer(factoryId, clinicId, uid);
            logger.info("添加客户成功");
        }, "添加客户错误", logger);
        return result;
    }

    @ApiOperation(value = "查询用户列表", response = FactoryUserVo.class, httpMethod = "GET")
    @ResponseBody
    @RequestMapping(value = "/users", method = GET)
    public WebResult<FactoryUserVo> users() {
        //todo 从session中获取 factoryId
        Long factoryId = 1L;
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
}
