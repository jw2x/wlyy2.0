package com.yihu.jw.fegin.base.version;

import com.yihu.jw.fegin.fallbackfactory.base.version.WlyyVersionFeginFallbackFactory;
import com.yihu.jw.restmodel.base.version.BaseVersionContants;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.common.Envelop;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@FeignClient(
        name = CommonContants.svr_base // name值是eurika的实例名字
        ,fallbackFactory  = WlyyVersionFeginFallbackFactory.class
)
@RequestMapping(value = BaseVersionContants.api_common)
public interface WlyyVersionFegin {

    @PostMapping(value = BaseVersionContants.WlyyVersion.api_create,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop create(@RequestBody String jsonData);

    @PutMapping(value =  BaseVersionContants.WlyyVersion.api_update,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop update(@RequestBody String jsonData);

    @DeleteMapping(value =BaseVersionContants.WlyyVersion.api_delete)
    Envelop delete(@PathVariable(value ="codes" ,required = true)  String codes, @RequestParam(value="userCode") String userCode, @RequestParam(value="userName") String userName);

    @GetMapping(value = BaseVersionContants.WlyyVersion.api_getByCode)
    Envelop findByCode(@PathVariable(value ="code",required = true)  String code);

    @RequestMapping(value = BaseVersionContants.WlyyVersion.api_getList, method = RequestMethod.GET)
    Envelop getList(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts,
            @RequestParam(value = "size", required = false) int size,
            @RequestParam(value = "page", required = false) int page);

    @GetMapping(value = BaseVersionContants.WlyyVersion.api_getListNoPage)
    Envelop getListNoPage(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts);
}
