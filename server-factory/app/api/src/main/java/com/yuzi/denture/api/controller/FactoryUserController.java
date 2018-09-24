package com.yuzi.denture.api.controller;

import com.yuzi.denture.api.vo.base.WebResult;
import com.yuzi.denture.domain.Factory;
import com.yuzi.denture.domain.FactoryUser;
import com.yuzi.denture.domain.GroupType;
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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
            @ApiImplicitParam(paramType = "form", name = "groupType", dataType = "string",
                    required = true, value = "审核结果\n" +
                    "   [ShiGao(\"石膏组\"),\n" +
                    "    LaXing(\"蜡型组\"),\n" +
                    "    CheJin(\"车金组\"),\n" +
                    "    ChongJiao(\"充胶组\"),\n" +
                    "    ShangCi(\"上瓷组\"),\n" +
                    "    CheCi(\"车瓷组\"),\n" +
                    "    Comprehensive(\"综合管理组\"),\n" +
                    "    Market(\"销售组\"),\n" +
                    "    Management(\"管理组\"),\n" +
                    "    Other(\"其他\")]"),
            @ApiImplicitParam(paramType = "form", name = "joinDate", dataType = "string",
                    value = "加入公司时间，格式=[YYYY-mm-dd]")
    })
    @ResponseBody
    @RequestMapping(value = "/add", method = POST)
    public WebResult add(String name, String contact, String groupType, String joinDate) {
        //todo 从session中获取用户factoryId
        Long factoryId = 1L;
        logger.info("审核义齿:name={}, contact={}, groupType={}, joinDate={}",name, contact,
                groupType, joinDate);

        WebResult result = WebResult.successResult();
        try {
            FactoryUser user = new FactoryUser(factoryId, name, contact, GroupType.typeOf(groupType));
            if(StringUtils.isEmpty(joinDate)) {
                DateFormatter formatter = new DateFormatter("yyyy-mm-dd");
                user.setJoinDate(formatter.parse(joinDate, Locale.CHINA));
            }
            service.addFactoryUser(user);
        } catch (ParseException ex) {
          logger.warn("日期格式错误：{}", ex);
            return WebResult.failureResult(ex.getMessage());
        } catch (Exception ex) {
            logger.warn("审核义齿异常: {}", ex);
            return WebResult.failureResult(ex.getMessage());
        }
        return result;
    }


}
