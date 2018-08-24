package com.yihu.jw.fegin.wlyy.agreement;

import com.yihu.jw.fegin.fallbackfactory.wlyy.agreement.WlyyAgreementFeignFallbackFactory;
import com.yihu.jw.restmodel.CommonContants;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.rm.wlyy.WlyyRequestMapping;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@FeignClient(
        name = CommonContants.svr_wlyy // name值是eurika的实例名字
        ,fallbackFactory  = WlyyAgreementFeignFallbackFactory.class
)
@RequestMapping(WlyyRequestMapping.api_wlyy_common)
public interface WlyyAgreementFeign {

    @PostMapping(value = WlyyRequestMapping.Agreement.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    MixEnvelop create(@RequestBody String jsonData);

    @PutMapping(value = WlyyRequestMapping.Agreement.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    MixEnvelop update(@RequestBody String jsonData);

    @DeleteMapping(value = WlyyRequestMapping.Agreement.api_delete)
    MixEnvelop delete(@RequestParam(value = "id") String id);

    @RequestMapping(value= WlyyRequestMapping.Agreement.api_getById,method = RequestMethod.GET)
    MixEnvelop findByCode(@RequestParam(value = "id" ) String id);

    @RequestMapping(value = WlyyRequestMapping.Agreement.api_queryPage, method = RequestMethod.GET)
    MixEnvelop queryPage(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts,
            @RequestParam(value = "size", required = false) int size,
            @RequestParam(value = "page", required = false) int page);

    @GetMapping(value = WlyyRequestMapping.Agreement.api_getList)
    MixEnvelop getList(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts);
}
