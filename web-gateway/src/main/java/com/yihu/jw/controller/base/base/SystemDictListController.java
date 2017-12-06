package com.yihu.jw.controller.base.base;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yihu.jw.commnon.base.base.BaseContants;
import com.yihu.jw.exception.business.JiWeiException;
import com.yihu.jw.feign.base.base.SystemDictListFeign;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LiTaohong on 2017/12/01.
 */
@RestController
@RequestMapping(BaseContants.api_systemDictList)
@Api(description = "SystemDictList相关")
public class SystemDictListController extends EnvelopRestController {

    private Logger logger= LoggerFactory.getLogger(SystemDictListController.class);
    @Autowired
    private SystemDictListFeign SystemDictListFegin;

    @Autowired
    private Tracer tracer;


    @GetMapping(value = BaseContants.SystemDictList.api_getSystemDictListNoPage)
    @ApiOperation(value = "获取SystemDictList，不分页")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop getSystemDictListNoPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,name,SystemDictListId,appId,appSecret,baseUrl,remark")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws JiWeiException {
        return SystemDictListFegin.getListNoPage(fields, filters, sorts);
    }

}
