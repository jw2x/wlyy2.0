package com.yihu.jw.fegin.base.user;

import com.yihu.jw.exception.business.JiWeiException;
import com.yihu.jw.fegin.fallbackfactory.base.user.EmployeeFeignFallbackFactory;
import com.yihu.jw.restmodel.CommonContants;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.rm.base.BaseUserRequestMapping;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by LiTaohong on 2017/11/30.
 */
@FeignClient(
        name = CommonContants.svr_base // name值是eurika的实例名字
        ,fallbackFactory  = EmployeeFeignFallbackFactory.class
)
@RequestMapping(value = BaseUserRequestMapping.api_user_common)
public interface EmployFeign {

    @RequestMapping(value = BaseUserRequestMapping.BaseEmploy.api_create,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    MixEnvelop create(@RequestBody String jsonData);

    @RequestMapping(value =  BaseUserRequestMapping.BaseEmploy.api_update,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.PUT)
    MixEnvelop update(@RequestBody String jsonData) throws JiWeiException;

    @RequestMapping(value =  BaseUserRequestMapping.BaseEmploy.api_delete,method = RequestMethod.DELETE)
    MixEnvelop delete(@PathVariable (value = "id") String id) throws JiWeiException;

    @GetMapping(value = BaseUserRequestMapping.BaseEmploy.api_getById)
    MixEnvelop findById(@PathVariable(value = "id", required = true) String id) throws JiWeiException;

    @RequestMapping(value = BaseUserRequestMapping.BaseEmploy.api_getListBySaasId, method = RequestMethod.GET)
    MixEnvelop getList(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts,
            @RequestParam(value = "size", required = false) int size,
            @RequestParam(value = "page", required = false) int page) throws JiWeiException;

    @GetMapping(value = BaseUserRequestMapping.BaseEmploy.api_getListNoPage)
    MixEnvelop getListNoPage(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts) throws JiWeiException;

    @RequestMapping(value = BaseUserRequestMapping.BaseEmployRole.api_create,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    MixEnvelop createEmployRoles(@RequestBody String jsonData) throws JiWeiException;

    @RequestMapping(value = BaseUserRequestMapping.BaseEmployRole.api_update,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    MixEnvelop updateEmployRoles(@RequestBody String jsonData) throws JiWeiException;

    @RequestMapping(value = BaseUserRequestMapping.BaseEmployRole.api_delete,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    MixEnvelop deleteEmployRoles(@RequestBody String jsonData) throws JiWeiException;

    @RequestMapping(value = BaseUserRequestMapping.BaseEmployRole.api_getListNoPage,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    MixEnvelop getRoleList(@RequestBody String jsonData) throws JiWeiException;

    @GetMapping(value = BaseUserRequestMapping.BaseEmploy.api_getByPhone)
    MixEnvelop getEmployeeByPhoneAndSaasId(@RequestParam(value = "phone", required = true) String phone, @RequestParam(value = "saasId", required = true) String saasId);
}

