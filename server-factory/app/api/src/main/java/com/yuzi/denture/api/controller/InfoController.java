package com.yuzi.denture.api.controller;

import com.yuzi.denture.api.assembler.ClinicAssembler;
import com.yuzi.denture.api.session.SessionManager;
import com.yuzi.denture.api.vo.ClinicVo;
import com.yuzi.denture.api.vo.base.WebResult;
import com.yuzi.denture.domain.Clinic;
import com.yuzi.denture.domain.FactoryUser;
import com.yuzi.denture.domain.repository.InfoRepository;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/denture/factory/info")
@Api(value = "info", description = "页面展示信息查询相关接口")
public class InfoController {

    private static Logger logger = LoggerFactory.getLogger(InfoController.class);

    @Value("${upload.location}")
    private String UploadLocation;
    @Autowired
    private InfoRepository infoRepository;

    @ApiOperation(value = "查询诊所列表", response = ClinicVo.class, httpMethod = "GET")
    @ResponseBody
    @RequestMapping(value = "/queryClinics", method = GET)
    public WebResult<List<ClinicVo>> clinics() {
        // todo session get factoryId and uid
        Long factoryId = 1L;
        Long uid = 1L;
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

    @RequestMapping("/avatar/{suffix}")
    @ApiOperation(value = "工厂Logo", response = String.class, httpMethod = "GET")
    public void avatar(@PathVariable String suffix, HttpServletRequest request, HttpServletResponse resp) {
        //FactoryUser user = SessionManager.Instance().user(request);
        Long factoryId = 1L;//user.getFactoryId();
        // 禁止图像缓存。
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);
        resp.setContentType("image/"+suffix);
        try {
            //检查图片是否存在
            File imageFile = new File(UploadLocation + File.separator + factoryId+"."+suffix);
            if(!imageFile.exists()) {
                throw new RuntimeException(factoryId+" : 图片不存在");
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
