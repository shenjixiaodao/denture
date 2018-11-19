package com.yuzi.denture.clinic.controller;


import com.yuzi.denture.clinic.controller.assembler.FactoryAssembler;
import com.yuzi.denture.clinic.controller.base.WebResult;
import com.yuzi.denture.clinic.controller.vo.DentureOrderVo;
import com.yuzi.denture.clinic.controller.vo.FactoryVo;
import com.yuzi.denture.clinic.controller.vo.StatisticVo;
import com.yuzi.denture.clinic.domain.Factory;
import com.yuzi.denture.clinic.repository.InfoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by liyou on 2018/9/18.
 */
@RestController
@RequestMapping(value = "/denture/clinic/info")
@Api(value = "info", description = "信息相关接口")
public class InfoController {

    private static Logger logger = LoggerFactory.getLogger(InfoController.class);

    @Value("${avatar.location}")
    private String AvatarLocation;

    @Autowired
    private InfoRepository repository;

    @ApiOperation(value = "查询合作工厂数量", response = StatisticVo.class, httpMethod = "GET")
    @ResponseBody
    @RequestMapping(value = "/statistic", method = GET)
    public WebResult<StatisticVo> statistic() {
        //logger.info("查询合作工厂:id={}", id);
        //TODO UnionID
        Long clinicId = 1L;
        WebResult<StatisticVo> result = WebResult.execute(res -> {
            StatisticVo vo = new StatisticVo();
            Integer partnerNumber = repository.countFactory(clinicId, new Byte("1"));
            vo.setPartnerNumber(partnerNumber);
            Integer applicantsNumber = repository.countFactory(clinicId, new Byte("0"));
            vo.setApplicantNumber(applicantsNumber);
            res.setData(vo);
        }, "查询订单错误", logger);
        return result;
    }

    @ApiOperation(value = "查询工厂信息", response = FactoryVo.class, httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "type", dataType = "String", required = true, value = "类型[-1,0,1]")
    })
    @ResponseBody
    @RequestMapping(value = "/queryFactories", method = GET)
    public WebResult<FactoryVo> factories(String type) {
        //logger.info("查询合作工厂:id={}", id);
        //TODO UnionID
        Long clinicId = 1L;
        WebResult<FactoryVo> result = WebResult.execute(res -> {
            List<Factory> factories = repository.findFactories(clinicId, new Byte(type));
            List<FactoryVo> vos = FactoryAssembler.toVos(factories);
            res.setData(vos);
        }, "查询订单错误", logger);
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

}
