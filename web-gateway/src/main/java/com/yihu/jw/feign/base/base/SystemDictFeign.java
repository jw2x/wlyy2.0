package com.yihu.jw.feign.base.base;

import com.yihu.jw.exception.business.JiWeiException;
import com.yihu.jw.feign.fallbackfactory.base.base.SystemDictFeignFallbackFactory;
import com.yihu.jw.feign.fallbackfactory.base.base.SystemDictFeignFallbackFactory;
import com.yihu.jw.restmodel.CommonContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.rm.base.BaseRequestMapping;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@FeignClient(
        name = CommonContants.svr_base // name值是eurika的实例名字
        ,fallbackFactory  = SystemDictFeignFallbackFactory.class
)
@RequestMapping(value = BaseRequestMapping.api_base_common)
public interface SystemDictFeign {

    @RequestMapping(value = BaseRequestMapping.SystemDict.api_create,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    Envelop create(@RequestBody String jsonData) throws JiWeiException;

    @RequestMapping(value =  BaseRequestMapping.SystemDict.api_update,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.PUT)
    Envelop update(@RequestBody String jsonData) throws JiWeiException;

    @RequestMapping(value =  BaseRequestMapping.SystemDict.api_delete,method = RequestMethod.DELETE)
    Envelop delete(@PathVariable(value = "ids", required = true) String ids, @RequestParam(value = "userId") String userId, @RequestParam(value = "userName") String userName) throws JiWeiException;

    @GetMapping(value = BaseRequestMapping.SystemDict.api_getById)
    Envelop findById(@PathVariable(value = "id", required = true) String id) throws JiWeiException;

    @RequestMapping(value = BaseRequestMapping.SystemDict.api_getList, method = RequestMethod.GET)
    Envelop getList(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts,
            @RequestParam(value = "size", required = false) int size,
            @RequestParam(value = "page", required = false) int page) throws JiWeiException;

    @GetMapping(value = BaseRequestMapping.SystemDict.api_getListNoPage)
    Envelop getListNoPage(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts) throws JiWeiException;
}
