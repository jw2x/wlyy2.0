package com.yihu.jw.fegin.base.user;

import com.yihu.jw.exception.business.JiWeiException;
import com.yihu.jw.fegin.fallbackfactory.base.user.BaseRoleFeignFallbackFactory;
import com.yihu.jw.common.CommonContants;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.rm.base.BaseUserRequestMapping;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by LiTaohong on 2017/11/28.
 */
@FeignClient(
        name = CommonContants.svr_base // name值是eurika的实例名字
        ,fallbackFactory  = BaseRoleFeignFallbackFactory.class
)
@RequestMapping(BaseUserRequestMapping.api_user_common)
public interface BaseRoleFeign {

    @RequestMapping(value = BaseUserRequestMapping.BaseRole.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    MixEnvelop create(@RequestBody String jsonData) throws JiWeiException;

    @RequestMapping(value = BaseUserRequestMapping.BaseRole.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.PUT)
    MixEnvelop update(@RequestBody String jsonData) throws JiWeiException;

    @RequestMapping(value = BaseUserRequestMapping.BaseRole.api_delete, method = RequestMethod.DELETE)
    MixEnvelop delete(@PathVariable(value = "id") String id) throws JiWeiException;

    @GetMapping(value = BaseUserRequestMapping.BaseRole.api_getById)
    MixEnvelop findById(@PathVariable(value = "id", required = true) String id) throws JiWeiException;

    @RequestMapping(value = BaseUserRequestMapping.BaseRole.api_getListPage, method = RequestMethod.GET)
    MixEnvelop getList(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts,
            @RequestParam(value = "size", required = false) int size,
            @RequestParam(value = "page", required = false) int page) throws JiWeiException;

    @GetMapping(value = BaseUserRequestMapping.BaseRole.api_getlistNoPage)
    MixEnvelop getListNoPage(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts) throws JiWeiException;

    @RequestMapping(value = BaseUserRequestMapping.BaseRoleMenu.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    MixEnvelop createRoleMenus(@RequestBody String jsonData) throws JiWeiException;

    @RequestMapping(value = BaseUserRequestMapping.BaseRoleMenu.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    MixEnvelop updateRoleMenus(@RequestBody String jsonData) throws JiWeiException;

    @RequestMapping(value = BaseUserRequestMapping.BaseRoleMenu.api_delete, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    MixEnvelop deleteRoleMenus(@RequestBody String jsonData) throws JiWeiException;

    @RequestMapping(value = BaseUserRequestMapping.BaseRoleMenu.api_getListNoPage, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    MixEnvelop getMenuList(@RequestBody String jsonData) throws JiWeiException;

    @PostMapping(value = BaseUserRequestMapping.BaseRole.api_getRoleByPhoneAndSaasId, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    MixEnvelop getPhoneAndSaasId(@RequestParam(value = "phone", required = true) String phone, @RequestParam(value = "saasId", required = true) String saasId);

}