//package com.yihu.jw.fegin.base.base;
//
//import com.yihu.jw.exception.business.JiWeiException;
//import com.yihu.jw.fegin.fallbackfactory.base.base.SystemDictListFeignFallbackFactory;
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
//        ,fallbackFactory  = SystemDictListFeignFallbackFactory.class
//)
//@RequestMapping(value = BaseRequestMapping.api_base_common)
//public interface SystemDictListFeign {
//
//    @RequestMapping(value = BaseRequestMapping.SystemDictList.api_create,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
//    MixEnvelop create(@RequestBody String jsonData) throws JiWeiException;
//
//    @RequestMapping(value =  BaseRequestMapping.SystemDictList.api_update,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.PUT)
//    MixEnvelop update(@RequestBody String jsonData) throws JiWeiException;
//
//    @RequestMapping(value =  BaseRequestMapping.SystemDictList.api_delete,method = RequestMethod.DELETE)
//    MixEnvelop delete(@PathVariable(value = "ids", required = true) String ids, @RequestParam(value = "userId") String userId, @RequestParam(value = "userName") String userName) throws JiWeiException;
//
//    @GetMapping(value = BaseRequestMapping.SystemDictList.api_getById)
//    MixEnvelop findById(@PathVariable(value = "id", required = true) String id) throws JiWeiException;
//
//    @RequestMapping(value = BaseRequestMapping.SystemDictList.api_getList, method = RequestMethod.GET)
//    MixEnvelop getList(
//            @RequestParam(value = "fields", required = false) String fields,
//            @RequestParam(value = "filters", required = false) String filters,
//            @RequestParam(value = "sorts", required = false) String sorts,
//            @RequestParam(value = "size", required = false) int size,
//            @RequestParam(value = "page", required = false) int page) throws JiWeiException;
//
//    @GetMapping(value = BaseRequestMapping.SystemDictList.api_getListNoPage)
//    MixEnvelop getListNoPage(
//            @RequestParam(value = "fields", required = false) String fields,
//            @RequestParam(value = "filters", required = false) String filters,
//            @RequestParam(value = "sorts", required = false) String sorts) throws JiWeiException;
//
//    @GetMapping(value = BaseRequestMapping.SystemDictList.api_getChildren)
//    MixEnvelop getChildren(@PathVariable(value = "id") String id) throws JiWeiException;
//}
