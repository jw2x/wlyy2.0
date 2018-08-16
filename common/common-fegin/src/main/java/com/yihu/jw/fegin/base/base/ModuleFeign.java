//package com.yihu.jw.fegin.base.base;
//
//import com.yihu.jw.exception.business.JiWeiException;
//import com.yihu.jw.fegin.fallbackfactory.base.base.ModuleFeignFallbackFactory;
//import com.yihu.jw.restmodel.CommonContants;
//import com.yihu.jw.restmodel.web.MixEnvelop;
//import com.yihu.jw.rm.base.BaseRequestMapping;
//import org.springframework.cloud.netflix.feign.FeignClient;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//
//
//@FeignClient(
//        name = CommonContants.svr_base // name值是eurika的实例名字
//        ,fallbackFactory  = ModuleFeignFallbackFactory.class
//)
//@RequestMapping(value = BaseRequestMapping.api_base_common)
//public interface ModuleFeign {
//
//    @PostMapping(value = BaseRequestMapping.Module.api_create,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    MixEnvelop create(@RequestBody String jsonData) throws JiWeiException;
//
//    @PutMapping(value =  BaseRequestMapping.Module.api_update,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    MixEnvelop update(@RequestBody String jsonData) throws JiWeiException;
//
//    @DeleteMapping(value =BaseRequestMapping.Module.api_delete)
//    MixEnvelop delete(@PathVariable(value = "ids", required = true) String ids, @RequestParam(value = "userId") String userId, @RequestParam(value = "userName") String userName) throws JiWeiException;
//
//    @GetMapping(value = BaseRequestMapping.Module.api_getById)
//    MixEnvelop findById(@PathVariable(value = "id", required = true) String id) throws JiWeiException;
//
//    @RequestMapping(value = BaseRequestMapping.Module.api_getList, method = RequestMethod.GET)
//    MixEnvelop getList(
//            @RequestParam(value = "fields", required = false) String fields,
//            @RequestParam(value = "filters", required = false) String filters,
//            @RequestParam(value = "sorts", required = false) String sorts,
//            @RequestParam(value = "size", required = false) int size,
//            @RequestParam(value = "page", required = false) int page) throws JiWeiException;
//
//    @GetMapping(value = BaseRequestMapping.Module.api_getListNoPage)
//    MixEnvelop getListNoPage(
//            @RequestParam(value = "fields", required = false) String fields,
//            @RequestParam(value = "filters", required = false) String filters,
//            @RequestParam(value = "sorts", required = false) String sorts) throws JiWeiException;
//
//    @GetMapping(value = BaseRequestMapping.Module.api_getChildren)
//    MixEnvelop getChildren(@PathVariable(value = "id") String id) throws JiWeiException;
//
//    @GetMapping(value = BaseRequestMapping.ModuleFun.api_getExistFun)
//    MixEnvelop getExistFunc(@PathVariable(value = "id") String id) throws JiWeiException;
//
//    @PutMapping(value =  BaseRequestMapping.ModuleFun.api_changeFun,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    MixEnvelop changeFun(@RequestBody String jsonData) throws JiWeiException;
//}
