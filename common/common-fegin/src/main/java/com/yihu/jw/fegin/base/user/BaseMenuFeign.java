package com.yihu.jw.fegin.base.user;

import com.yihu.jw.exception.business.JiWeiException;
import com.yihu.jw.fegin.fallbackfactory.base.user.BaseMenuFeignFallbackFactory;
import com.yihu.jw.restmodel.CommonContants;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.rm.base.BaseUserRequestMapping;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by LiTaohong on 2017/12/05.
 */
@FeignClient(
        name = CommonContants.svr_base // name值是eurika的实例名字
        ,fallbackFactory  = BaseMenuFeignFallbackFactory.class
)
public interface BaseMenuFeign {
    
    @RequestMapping(value = BaseUserRequestMapping.BaseMenu.api_create,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    MixEnvelop create(@RequestBody String jsonData) throws JiWeiException;

    @RequestMapping(value =  BaseUserRequestMapping.BaseMenu.api_update,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.PUT)
    MixEnvelop update(@RequestBody String jsonData) throws JiWeiException;

    @RequestMapping(value =  BaseUserRequestMapping.BaseMenu.api_delete,method = RequestMethod.DELETE)
    MixEnvelop delete(@PathVariable(value = "id") String id) throws JiWeiException;

    @GetMapping(value = BaseUserRequestMapping.BaseMenu.api_getOne)
    MixEnvelop findById(@PathVariable(value = "id", required = true) String id) throws JiWeiException;

    @RequestMapping(value = BaseUserRequestMapping.BaseMenu.api_getList, method = RequestMethod.GET)
    MixEnvelop getList(@RequestParam(value = "fields", required = false) String fields,
                       @RequestParam(value = "filters", required = false) String filters,
                       @RequestParam(value = "sorts", required = false) String sorts,
                       @RequestParam(value = "size", required = false) int size,
                       @RequestParam(value = "page", required = false) int page) throws JiWeiException;

    @GetMapping(value = BaseUserRequestMapping.BaseMenu.api_getListNoPage)
    MixEnvelop getListNoPage(@RequestParam(value = "fields", required = false) String fields,
                             @RequestParam(value = "filters", required = false) String filters,
                             @RequestParam(value = "sorts", required = false) String sorts) throws JiWeiException;

    @GetMapping(value = BaseUserRequestMapping.BaseMenu.api_getchildren)
    MixEnvelop getChildren(@RequestParam(value = "saasId", required = true) String saasId, @RequestParam(value = "parentId", required = true) String parentId) throws JiWeiException;


















}
