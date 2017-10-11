package com.yihu.jw.fegin.base.version;

import com.yihu.jw.fegin.fallbackfactory.base.version.ServerUrlVersionFeginFallbackFactory;
import com.yihu.jw.restmodel.base.version.BaseVersionContants;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.exception.business.JiWeiException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@FeignClient(
        name = CommonContants.svr_base // name值是eurika的实例名字
        ,fallbackFactory  = ServerUrlVersionFeginFallbackFactory.class
)
@RequestMapping(value = BaseVersionContants.api_common)
public interface ServerUrlVersionFegin {

    @PostMapping(value = BaseVersionContants.BaseServerUrlVersion.api_create,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop create(@RequestBody String jsonData ) throws JiWeiException;

    @PutMapping(value =  BaseVersionContants.BaseServerUrlVersion.api_update,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop update( @RequestBody String jsonData) throws JiWeiException;

    @DeleteMapping(value =BaseVersionContants.BaseServerUrlVersion.api_delete)
    Envelop delete( @PathVariable(required = true,value="codes") String codes,@RequestParam(value="userCode") String userCode,@RequestParam(value="userName") String userName) throws JiWeiException;

    @GetMapping(value = BaseVersionContants.BaseServerUrlVersion.api_getByCode)
    Envelop findByCode(@PathVariable(required = true,value="code")  String code) throws JiWeiException;

    @RequestMapping(value = BaseVersionContants.BaseServerUrlVersion.api_getList, method = RequestMethod.GET)
    Envelop getList(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts,
            @RequestParam(value = "size", required = false) int size,
            @RequestParam(value = "page", required = false) int page) throws JiWeiException;

    @GetMapping(value = BaseVersionContants.BaseServerUrlVersion.api_getListNoPage)
    Envelop getListNoPage(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts) throws JiWeiException;
}
