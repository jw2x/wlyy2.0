package com.yihu.jw.fegin.wlyy.agreement;

import com.yihu.jw.fegin.fallbackfactory.wlyy.agreement.WlyySignFamilyFeignFallbackFactory;
import com.yihu.jw.restmodel.CommonContants;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.rm.wlyy.WlyyRequestMapping;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@FeignClient(
        name = CommonContants.svr_wlyy // name值是eurika的实例名字
        ,fallbackFactory  = WlyySignFamilyFeignFallbackFactory.class
)
@RequestMapping(WlyyRequestMapping.api_wlyy_common)
public interface WlyySignFamilyFeign {

    @PostMapping(value = WlyyRequestMapping.SignFamily.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    MixEnvelop create(@RequestBody String jsonData);

    @PutMapping(value = WlyyRequestMapping.SignFamily.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    MixEnvelop update(@RequestBody String jsonData);

    @RequestMapping(value= WlyyRequestMapping.SignFamily.api_getById,method = RequestMethod.GET)
    MixEnvelop findByCode(@RequestParam(value = "id") String id);

    @RequestMapping(value = WlyyRequestMapping.SignFamily.api_queryPage, method = RequestMethod.GET)
    MixEnvelop queryPage(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts,
            @RequestParam(value = "size", required = false) int size,
            @RequestParam(value = "page", required = false) int page);

    @GetMapping(value = WlyyRequestMapping.SignFamily.api_getList)
    MixEnvelop getList(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts);
}
