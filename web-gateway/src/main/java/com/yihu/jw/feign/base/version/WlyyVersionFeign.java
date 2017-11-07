package com.yihu.jw.feign.base.version;

import com.yihu.jw.feign.fallbackfactory.base.version.WlyyVersionFeignFallbackFactory;
import com.yihu.jw.restmodel.CommonContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.rm.base.BaseVersionRequestMapping;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@FeignClient(
        name = CommonContants.svr_base // name值是eurika的实例名字
        ,fallbackFactory  = WlyyVersionFeignFallbackFactory.class
)
@RequestMapping(value = BaseVersionRequestMapping.api_common)
public interface WlyyVersionFeign {

    @PostMapping(value = BaseVersionRequestMapping.WlyyVersion.api_create,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop create(@RequestBody String jsonData);

    @PutMapping(value =  BaseVersionRequestMapping.WlyyVersion.api_update,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop update(@RequestBody String jsonData);

    @DeleteMapping(value =BaseVersionRequestMapping.WlyyVersion.api_delete)
    Envelop delete(@PathVariable(value ="codes" ,required = true)  String codes, @RequestParam(value="userCode") String userCode, @RequestParam(value="userName") String userName);

    @GetMapping(value = BaseVersionRequestMapping.WlyyVersion.api_getByCode)
    Envelop findByCode(@PathVariable(value ="code",required = true)  String code);

    @RequestMapping(value = BaseVersionRequestMapping.WlyyVersion.api_getList, method = RequestMethod.GET)
    Envelop getList(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts,
            @RequestParam(value = "size", required = false) int size,
            @RequestParam(value = "page", required = false) int page);

    @GetMapping(value = BaseVersionRequestMapping.WlyyVersion.api_getListNoPage)
    Envelop getListNoPage(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts);
}
