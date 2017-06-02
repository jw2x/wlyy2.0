package com.yihu.jw.fegin.wlyy.agreement;

import com.yihu.jw.fegin.fallbackfactory.wlyy.agreement.WlyySignFamilyFeginFallbackFactory;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.wlyy.WlyyContants;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@FeignClient(
        name = CommonContants.svr_wlyy // name值是eurika的实例名字
        ,fallbackFactory  = WlyySignFamilyFeginFallbackFactory.class
)
@RequestMapping(WlyyContants.SignFamily.api_common)
public interface WlyySignFamilyFegin {

    @PostMapping(value = WlyyContants.SignFamily.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop create(@RequestBody String jsonData);

    @PutMapping(value = WlyyContants.SignFamily.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop update(@RequestBody String jsonData);

    @DeleteMapping(value =WlyyContants.SignFamily.api_delete)
    Envelop delete(@RequestParam(value = "code") String code);

    @RequestMapping(value=WlyyContants.SignFamily.api_getByCode,method = RequestMethod.GET)
    Envelop findByCode(@RequestParam(value = "code") String code);

    @RequestMapping(value =WlyyContants.SignFamily.api_queryPage, method = RequestMethod.GET)
    Envelop queryPage(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts,
            @RequestParam(value = "size", required = false) int size,
            @RequestParam(value = "page", required = false) int page,
            HttpServletRequest request,
            HttpServletResponse response);

    @GetMapping(value =WlyyContants.SignFamily.api_getList)
    Envelop getList(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts);
}
