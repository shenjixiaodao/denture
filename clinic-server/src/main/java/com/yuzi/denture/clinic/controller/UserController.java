package com.yuzi.denture.clinic.controller;

import com.yuzi.denture.clinic.controller.assembler.ClinicUserAssembler;
import com.yuzi.denture.clinic.controller.assembler.FactoryAssembler;
import com.yuzi.denture.clinic.controller.base.WebResult;
import com.yuzi.denture.clinic.controller.vo.ClinicUserVo;
import com.yuzi.denture.clinic.controller.vo.FactoryVo;
import com.yuzi.denture.clinic.domain.ClinicUser;
import com.yuzi.denture.clinic.domain.Factory;
import com.yuzi.denture.clinic.repository.InfoRepository;
import com.yuzi.denture.clinic.service.ClinicService;
import com.yuzi.denture.clinic.session.SessionManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/denture/clinic/user")
@Api(value = "user", description = "用户管理相关接口")
public class UserController {

    @Autowired
    private InfoRepository repository;
    @Autowired
    private ClinicService service;
    @Value("${avatar.location}")
    private String AvatarLocation;

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @ApiOperation(value = "登录", response = ClinicUserVo.class, httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "phone", dataType = "string",
                    required = true, value = "手机"),
            @ApiImplicitParam(paramType = "form", name = "password", dataType = "string",
                    required = true, value = "密码")
    })
    @ResponseBody
    @RequestMapping(value = "/login", method = POST)
    public WebResult<ClinicUserVo> login(String phone, String password) {
        logger.info("登录:phone={}, password={}",phone, password);
        WebResult<ClinicUserVo> result = WebResult.execute(res -> {
            ClinicUser user = service.login(phone, password);
            ClinicUserVo vo = ClinicUserAssembler.toVo(user);
            String token = user.token();
            vo.setToken(token);
            SessionManager.Instance().cacheUser(token, user);
            res.setData(vo);
            logger.info("登录成功");
        }, "登录错误", logger);
        return result;
    }

    @ApiOperation(value = "回应合作申请", response = WebResult.class, httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "factoryId", dataType = "string",
                    required = true, value = "工厂ID"),
            @ApiImplicitParam(paramType = "form", name = "isValid", dataType = "boolean", value = "true:同意 ， false:拒绝")
    })
    @ResponseBody
    @RequestMapping(value = "/replyCo", method = POST)
    public WebResult replyCo(Long factoryId, Boolean isValid, HttpServletRequest request) {
        //todo UnionID从db中获取
        Long clinicId = 1L;
        logger.info("回应合作申请:factoryId={}, isValid={}",factoryId, isValid);
        WebResult result = WebResult.execute(res -> {
            service.coRequest(clinicId, factoryId, new Byte(isValid? "1":"-1"));
            logger.info("回应合作申请返回");
        }, "回应合作申请错误", logger);
        return result;
    }

    @ApiOperation(value = "合作请求者", response = FactoryVo.class, httpMethod = "GET")
    @ResponseBody
    @RequestMapping(value = "/applicants", method = GET)
    public WebResult<FactoryVo> applicants() {
        //logger.info("查询合作工厂:id={}", id);
        //TODO UnionID
        Long clinicId = 1L;
        WebResult<FactoryVo> result = WebResult.execute(res -> {
            List<Factory> factories = repository.findApplicants(clinicId);
            List<FactoryVo> vos = FactoryAssembler.toVos(factories);
            res.setData(vos);
        }, "查询订单错误", logger);
        return result;
    }

    @ApiOperation(value = "添加员工", response = WebResult.class, httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "phone", dataType = "string",
                    required = true, value = "手机"),
            @ApiImplicitParam(paramType = "form", name = "name", dataType = "string",
                    required = true, value = "姓名"),
            @ApiImplicitParam(paramType = "form", name = "password", dataType = "string",
                    required = true, value = "密码"),
            @ApiImplicitParam(paramType = "form", name = "clinicId", dataType = "long",
                    required = true, value = "诊所编号"),
            @ApiImplicitParam(paramType = "form", name = "clinicName", dataType = "string",
                    required = true, value = "诊所名"),
            @ApiImplicitParam(paramType = "form", name = "clinicAddress", dataType = "string",
                    required = true, value = "诊所地址"),
            @ApiImplicitParam(paramType = "form", name = "role", dataType = "string",
                    required = true, value = "职称"),
            @ApiImplicitParam(paramType = "form", name = "code", dataType = "string",
                    required = true, value = "验证码")
    })
    @ResponseBody
    @RequestMapping(value = "/register", method = POST)
    public WebResult register(String phone, String name, String password, String role, String code,
                              Long clinicId, String clinicName, String clinicAddress) {
        logger.info("审核义齿:phone={}, password={}, code={}",phone, password, code);
        WebResult<FactoryVo> result = WebResult.execute(res -> {
            ClinicUser user = service.register(phone, name, password, ClinicUser.ClinicRole.typeOf(role),
                    clinicId, clinicName, clinicAddress);
            ClinicUserVo vo = ClinicUserAssembler.toVo(user);
            String token = user.token();
            vo.setToken(token);
            SessionManager.Instance().cacheUser(token, user);
            res.setData(vo);
        }, "查询订单错误", logger);
        return result;
    }

    @ApiOperation(value = "修改头像", response = String.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/changeAvatar", method = POST)
    public WebResult<String> changeAvatar(@RequestPart MultipartFile avatar, HttpServletRequest request) {
        logger.info("修改头像");
        ClinicUser user = SessionManager.Instance().user(request);
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
