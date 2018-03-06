package com.yihu.jw.controller.wlyy.patient;

import com.yihu.jw.common.wlyy.PatientContants;
import com.yihu.jw.fegin.wlyy.patient.AdvertisementFeign;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.util.CusAccessObjectUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/6/6 0006.
 */
@RestController
@RequestMapping(PatientContants.Advertisement.api_common)
@Api(value = "签约相关操作", description = "签约相关操作")
public class AdvertisementControlelr extends EnvelopRestController {

    private Logger logger = LoggerFactory.getLogger(AdvertisementControlelr.class);

    @Autowired
    private AdvertisementFeign advertisementFegin;
    @Autowired
    private Tracer tracer;

    @PostMapping(value = PatientContants.Advertisement.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建广告", notes = "创建广告")
    public Envelop create(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        return advertisementFegin.create(jsonData);
    }

    @PutMapping(value = PatientContants.Advertisement.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改广告", notes = "修改广告")
    public Envelop update(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        return advertisementFegin.update(jsonData);
    }

    @DeleteMapping(value =PatientContants.Advertisement.api_delete)
    @ApiOperation(value = "删除广告", notes = "删除广告")
    public Envelop delete(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code) {
        return advertisementFegin.delete(code);
    }

    @GetMapping(value =PatientContants.Advertisement.api_getByCode)
    @ApiOperation(value = "根据code查找协议", notes = "根据code查找协议")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code
    ) {
        return advertisementFegin.findByCode(code);
    }

    @RequestMapping(value =PatientContants.Advertisement.api_queryPage, method = RequestMethod.GET)
    @ApiOperation(value = "分页获取协议")
    public Envelop queryPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,parentCode,saasId,name,price,posterPic,remark,type,status")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts,
            @ApiParam(name = "size", value = "分页大小", defaultValue = "15")
            @RequestParam(value = "size", required = false) int size,
            @ApiParam(name = "page", value = "页码", defaultValue = "1")
            @RequestParam(value = "page", required = false) int page,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return advertisementFegin.queryPage(fields,filters,sorts,size,page);
    }


    @GetMapping(value =PatientContants.Advertisement.api_getList)
    @ApiOperation(value = "获取协议列表(不分页)")
    public Envelop getList(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,parentCode,saasId,name,price,posterPic,remark,type,status")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        return advertisementFegin.getList(fields, filters, sorts);
    }

    @GetMapping(value =PatientContants.Advertisement.api_getListByPatientCode)
    @ApiOperation(value = "根据患者code获取广告")
    public Envelop getListByPatientCode(
            @ApiParam(name="patientCode")
            @RequestParam(value="patientCode") String patientCode,
            HttpServletRequest request
    ){
        return advertisementFegin.getListByPatientCode(patientCode);
    }

    @GetMapping(value =PatientContants.Advertisement.api_getListByHttp)
    @ApiOperation(value = "根据患者http获取广告")
    public Envelop getListByHttp(
            HttpServletRequest request
    ){
        String ipAddress = CusAccessObjectUtil.getIpAddress(request);
        return advertisementFegin.getListByIp(ipAddress);
    }
}
