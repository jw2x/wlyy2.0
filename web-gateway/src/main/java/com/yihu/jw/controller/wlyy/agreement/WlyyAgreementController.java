package com.yihu.jw.controller.wlyy.agreement;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yihu.jw.commnon.wlyy.AgreementContants;
import com.yihu.jw.fegin.wlyy.agreement.WlyyAgreementFegin;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AgreementContants.Agreement.api_common)
@Api(value = "协议相关操作", description = "协议相关操作")
public class WlyyAgreementController extends EnvelopRestController {

    private Logger logger = LoggerFactory.getLogger(WlyyAgreementController.class);

    @Autowired
    private WlyyAgreementFegin wlyyAgreementFegin;

    @Autowired
    private Tracer tracer;

    @PostMapping(value = AgreementContants.Agreement.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建协议", notes = "创建协议")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop create(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        return wlyyAgreementFegin.create(jsonData);
    }

    @PutMapping(value = AgreementContants.Agreement.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改协议", notes = "修改协议")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop update(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        return wlyyAgreementFegin.update(jsonData);
    }

    @DeleteMapping(value =AgreementContants.Agreement.api_delete)
    @ApiOperation(value = "删除协议", notes = "删除协议")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop delete(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code) {
        return wlyyAgreementFegin.delete(code);
    }

    @GetMapping(value =AgreementContants.Agreement.api_getByCode)
    @ApiOperation(value = "根据code查找协议", notes = "根据code查找协议")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code
    ) {
        return wlyyAgreementFegin.findByCode(code);
    }

    //@RequestMapping(value =AgreementContants.Agreement.api_queryPage, method = RequestMethod.GET)
    //@ApiOperation(value = "分页获取协议")
    //@HystrixCommand(commandProperties = {
    //        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
    //        @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    //public Envelop queryPage(
    //        @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,parentCode,saasId,name,price,posterPic,remark,type,status")
    //        @RequestParam(value = "fields", required = false) String fields,
    //        @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
    //        @RequestParam(value = "filters", required = false) String filters,
    //        @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
    //        @RequestParam(value = "sorts", required = false) String sorts,
    //        @ApiParam(name = "size", value = "分页大小", defaultValue = "15")
    //        @RequestParam(value = "size", required = false) int size,
    //        @ApiParam(name = "page", value = "页码", defaultValue = "1")
    //        @RequestParam(value = "page", required = false) int page,
    //        HttpServletRequest request,
    //        HttpServletResponse response) throws Exception {
    //    return wlyyAgreementFegin.queryPage(fields,filters,sorts,size,page,request,response);
    //}


    @GetMapping(value =AgreementContants.Agreement.api_getList)
    @ApiOperation(value = "获取协议列表(不分页)")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop getList(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,parentCode,saasId,name,price,posterPic,remark,type,status")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        return wlyyAgreementFegin.getList(fields,filters,sorts);
    }
}
