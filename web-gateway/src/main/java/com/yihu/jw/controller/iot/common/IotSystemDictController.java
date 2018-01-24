package com.yihu.jw.controller.iot.common;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yihu.jw.commnon.iot.IotCommonContants;
import com.yihu.jw.feign.iot.common.IotSystemDictFeign;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.iot.dict.IotSystemDictVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yeshijie on 2018/1/16.
 */
@RestController
@RequestMapping(IotCommonContants.Common.system_dict)
@Api(tags = "系统字典相关操作", description = "系统字典相关操作")
public class IotSystemDictController {

    @Autowired
    private IotSystemDictFeign iotSystemDictFeign;

    @GetMapping(value = IotRequestMapping.System.findDictByCode)
    @ApiOperation(value = "获取字典列表(不分页)")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop<IotSystemDictVO> getList(
            @ApiParam(name = "dictName", value = "字典名称", defaultValue = "COMPANY_TYPE")
            @RequestParam(value = "dictName", required = true) String dictName) throws Exception {
        return iotSystemDictFeign.getList(dictName);
    }

}
