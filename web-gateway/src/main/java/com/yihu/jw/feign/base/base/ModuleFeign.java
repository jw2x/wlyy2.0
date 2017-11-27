package com.yihu.jw.feign.base.base;

import com.yihu.jw.exception.business.JiWeiException;
import com.yihu.jw.feign.fallbackfactory.base.base.ModuleFeignFallbackFactory;
import com.yihu.jw.restmodel.CommonContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.rm.base.BaseRequestMapping;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@FeignClient(
        name = CommonContants.svr_base // name值是eurika的实例名字
        ,fallbackFactory  = ModuleFeignFallbackFactory.class
)
@RequestMapping(value = BaseRequestMapping.api_base_common)
public interface ModuleFeign {

    @PostMapping(value = BaseRequestMapping.Module.api_create,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop create(@RequestBody String jsonData) throws JiWeiException;

    @PutMapping(value =  BaseRequestMapping.Module.api_update,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop update(@RequestBody String jsonData) throws JiWeiException;

    @DeleteMapping(value =BaseRequestMapping.Module.api_delete)
    Envelop delete(@PathVariable(value = "ids", required = true) String ids, @RequestParam(value = "userId") String userId, @RequestParam(value = "userName") String userName) throws JiWeiException;

    @GetMapping(value = BaseRequestMapping.Module.api_getById)
    Envelop findById(@PathVariable(value = "id", required = true) String id) throws JiWeiException;

    @RequestMapping(value = BaseRequestMapping.Module.api_getList, method = RequestMethod.GET)
    Envelop getList(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts,
            @RequestParam(value = "size", required = false) int size,
            @RequestParam(value = "page", required = false) int page) throws JiWeiException;

    @GetMapping(value = BaseRequestMapping.Module.api_getListNoPage)
    Envelop getListNoPage(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts) throws JiWeiException;

    @GetMapping(value = BaseRequestMapping.Module.api_getChildren)
    Envelop getChildren(@PathVariable(value = "id") String id) throws JiWeiException;

    @GetMapping(value = BaseRequestMapping.ModuleFun.api_getExistFun)
    Envelop getExistFunc(@PathVariable(value = "id") String id) throws JiWeiException;

    @PutMapping(value =  BaseRequestMapping.ModuleFun.api_changeFun,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop changeFun(@RequestBody String jsonData) throws JiWeiException;
}
