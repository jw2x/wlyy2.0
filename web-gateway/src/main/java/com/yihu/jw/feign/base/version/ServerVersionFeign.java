package com.yihu.jw.feign.base.version;

import com.yihu.jw.exception.business.JiWeiException;
import com.yihu.jw.feign.fallbackfactory.base.version.ServerVersionFeignFallbackFactory;
import com.yihu.jw.restmodel.CommonContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.rm.base.BaseVersionRequestMapping;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@FeignClient(
        name = CommonContants.svr_base // name值是eurika的实例名字
        ,fallbackFactory  = ServerVersionFeignFallbackFactory.class
)
@RequestMapping(value = BaseVersionRequestMapping.api_common)
public interface ServerVersionFeign {

    @PostMapping(value = BaseVersionRequestMapping.BaseServerVersion.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop create(@RequestBody String jsonData) throws JiWeiException;

    @PutMapping(value = BaseVersionRequestMapping.BaseServerVersion.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop update(@RequestBody String jsonData) throws JiWeiException;

    @DeleteMapping(value = BaseVersionRequestMapping.BaseServerVersion.api_delete, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop delete( @PathVariable(value = "codes")String codes,@RequestParam(value="userCode") String userCode,@RequestParam(value="userName") String userName) throws JiWeiException;

    @GetMapping(value = BaseVersionRequestMapping.BaseServerVersion.api_getByCode, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop findByCode(@PathVariable(value = "code") String code) throws JiWeiException;

    @RequestMapping(value = BaseVersionRequestMapping.BaseServerVersion.api_getList, method = RequestMethod.GET)
    Envelop getList(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts,
            @RequestParam(value = "size", required = false) int size,
            @RequestParam(value = "page", required = false) int page) throws JiWeiException;

    @GetMapping(value = BaseVersionRequestMapping.BaseServerVersion.api_getListNoPage)
    Envelop getListNoPage(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts) throws JiWeiException;

}
