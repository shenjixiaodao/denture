package com.yuzi.denture.api.controller;

import com.yuzi.denture.api.assembler.ClinicAssembler;
import com.yuzi.denture.api.assembler.DentureAssembler;
import com.yuzi.denture.api.session.SessionManager;
import com.yuzi.denture.api.vo.ClinicVo;
import com.yuzi.denture.api.vo.base.DentureVo;
import com.yuzi.denture.api.vo.base.WebPageResult;
import com.yuzi.denture.api.vo.base.WebResult;
import com.yuzi.denture.domain.*;
import com.yuzi.denture.domain.aggregate.AppliedUsedIngredient;
import com.yuzi.denture.domain.aggregate.IngredientStatistic;
import com.yuzi.denture.domain.aggregate.ProductTypeStatistic;
import com.yuzi.denture.domain.aggregate.TotalIngredientStatistic;
import com.yuzi.denture.domain.criteria.DentureCriteria;
import com.yuzi.denture.domain.criteria.ProductTypeStatCriteria;
import com.yuzi.denture.domain.repository.FactoryRepository;
import com.yuzi.denture.domain.repository.InfoRepository;
import com.yuzi.denture.domain.util.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/denture/factory/info")
@Api(value = "info", description = "页面展示信息查询相关接口")
public class InfoController {

    private static Logger logger = LoggerFactory.getLogger(InfoController.class);

    @Value("${avatar.location}")
    private String AvatarLocation;
    @Autowired
    private InfoRepository infoRepository;
    @Autowired
    private FactoryRepository repository;

    @ApiOperation(value = "统计总物料信息", response = TotalIngredientStatistic.class, httpMethod = "GET")
    @ResponseBody
    @RequestMapping(value = "/statTotalIngredient", method = GET)
    public WebResult<TotalIngredientStatistic> statTotalIngredient(HttpServletRequest request) {
        FactoryUser user = SessionManager.Instance().user(request);
        Long factoryId = user.getFactoryId();
        logger.info("统计总物料信息:factoryId={}",factoryId);
        WebResult<TotalIngredientStatistic> result = WebResult.execute(res -> {
            TotalIngredientStatistic statistic = infoRepository.statTotalIngredient(factoryId);
            res.setData(statistic);
        }, "查询统计总物料信息", logger);
        return result;
    }

    @ApiOperation(value = "统计物料信息", response = IngredientStatistic.class, httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", dataType = "long",
                    required = true, value = "物料ID")
    })
    @ResponseBody
    @RequestMapping(value = "/statIngredient", method = GET)
    public WebResult<IngredientStatistic> statIngredient(Long id, HttpServletRequest request) {
        FactoryUser user = SessionManager.Instance().user(request);
        Long factoryId = user.getFactoryId();
        logger.info("统计物料信息:factoryId={}",factoryId);
        WebResult<IngredientStatistic> result = WebResult.execute(res -> {
            IngredientStatistic statistic = infoRepository.statIngredient(id, factoryId);
            res.setData(statistic);
        }, "查询统计物料信息", logger);
        return result;
    }

    @ApiOperation(value = "查询诊所列表", response = ClinicVo.class, httpMethod = "GET")
    @ResponseBody
    @RequestMapping(value = "/queryClinics", method = GET)
    public WebResult<List<ClinicVo>> clinics(HttpServletRequest request) {
        FactoryUser user = SessionManager.Instance().user(request);
        Long uid = user.getId();
        Long factoryId = user.getFactoryId();
        logger.info("查询诊所列表:factoryId={}",factoryId);
        WebResult<List<ClinicVo>> result = WebResult.execute(res -> {
            List<Clinic> clinics = infoRepository.findCustomerClinics(factoryId, uid);
            List<ClinicVo> vos = ClinicAssembler.toVos(clinics);
            res.setData(vos);
        }, "查询诊所列表错误", logger);
        return result;
    }

    @ApiOperation(value = "查询诊所详情", response = ClinicVo.class, httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "id", dataType = "long",
                    required = true, value = "诊所ID")
    })
    @ResponseBody
    @RequestMapping(value = "/queryClinic", method = GET)
    public WebResult<ClinicVo> clinic(Long id) {
        logger.info("查询诊所详情:id={}",id);
        WebResult<ClinicVo> result = WebResult.execute(res -> {
            Clinic clinic = infoRepository.findCustomerClinic(id);
            ClinicVo vos = ClinicAssembler.toVo(clinic);
            res.setData(vos);
        }, "查询诊所列表错误", logger);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/findAppliedUsedIngredient", method = GET)
    public WebResult<List<AppliedUsedIngredient>> findAppliedUsedIngredient(@RequestParam String dentureId) {
        WebResult<List<AppliedUsedIngredient>> result = WebResult.execute(res -> {
            List<AppliedUsedIngredient> appliedUsedIngredients = infoRepository.findAppliedUsedIngredient(dentureId);
            res.setData(appliedUsedIngredients);
        }, "查询申请使用物料信息", logger);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/findDenturesByCustomer", method = POST)
    public WebResult<List<DentureVo>> findDenturesByCustomer(@RequestBody DentureCriteria criteria,
                                                             HttpServletRequest request) {
        FactoryUser user = SessionManager.Instance().user(request);
        Long factoryId = user.getFactoryId();

        WebPageResult<List<DentureVo>> res = new WebPageResult<>();
        criteria.setFactoryId(factoryId);
        List<Denture> dentures = infoRepository.findDenturesByCriteria(criteria);
        Integer total = repository.countDentures(criteria);
        List<DentureVo> vos = DentureAssembler.toVos(dentures);
        res.setData(vos);
        res.setTotalSize(total);
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/findProductTypes", method = GET)
    public WebResult<List<ProductType>> findProductTypes(HttpServletRequest request) {
        FactoryUser user = SessionManager.Instance().user(request);
        Long factoryId = user.getFactoryId();
        WebResult<List<ProductType>> result = WebResult.execute(res -> {
            List<ProductType> types = infoRepository.findProductTypesByFactoryId(factoryId);
            res.setData(types);
        }, "查询产品类型", logger);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/productTypeStatistic", method = POST)
    public WebResult productTypeStatistic(@RequestBody ProductTypeStatCriteria criteria,
                                                                      HttpServletRequest request) {
        WebResult result = WebResult.execute(res -> {
            FactoryUser user = SessionManager.Instance().user(request);
            Long factoryId = user.getFactoryId();
            criteria.setFactoryId(factoryId);
            List<ProductTypeStatistic> statistics = infoRepository.productTypeStatistic(criteria);
            List<ProductType> types = infoRepository.findProductTypesByFactoryId(factoryId);
            int days = days(CommonUtil.parseMonthDate(criteria.getMonth()));
            Map<String, Map<String, Object>> data = new HashMap();
            for(ProductType type:types) {
                Map<String, Object> item = new HashMap<>();
                for(int day=0;day<days;day++) {
                    item.put((day+1)+"", 0);
                }
                item.put("days", days);
                item.put("code", type.getCode());
                item.put("name", type.getName());
                item.put("type", type.getType());
                data.put(type.getCode(), item);
            }
            for(ProductTypeStatistic statistic:statistics) {
                Map<String, Object> item = data.get(statistic.getProductCode());
                int day = day(statistic.getDentureCreatedDate());
                int size = (int)item.get(day+"");
                item.put(day+"", size + 1);
            }
            res.setData(data.values());
        }, "按产品类型统计订单", logger);
        return result;
    }

    @RequestMapping("/avatar/{uid}/{suffix}")
    @ApiOperation(value = "用户头像", response = String.class, httpMethod = "GET")
    public void avatar(@PathVariable Long uid, @PathVariable String suffix, HttpServletResponse resp) {
        // 禁止图像缓存。
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);
        resp.setContentType("image/"+suffix);
        try {
            //检查图片是否存在
            File imageFile = new File(AvatarLocation + File.separator + uid+"."+suffix);
            if(!imageFile.exists()) {
                throw new RuntimeException(uid+" : 图片不存在");
            }
            // 将图像输出到Servlet输出流中
            ServletOutputStream sos = resp.getOutputStream();
            ImageIO.write(ImageIO.read(imageFile), suffix, sos);
            sos.close();
        } catch (IOException e) {
            logger.warn("获取图片异常:{}", e);
        }
    }

    @ApiOperation(value = "登录过期", response = ClinicVo.class)
    @ResponseBody
    @RequestMapping(value = "/expired", method = { POST, GET })
    public WebResult expired() {
        return WebResult.expired();
    }

    private static int days(Date monthDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(monthDate);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    private static int day(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /*public static void main(String[] strs) {
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTime());
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
    }*/
}
