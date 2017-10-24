package com.yihu.jw.fegin.base.version;

import com.yihu.jw.exception.business.JiWeiException;
import com.yihu.jw.fegin.fallbackfactory.base.version.UserUrlVersionFeginFallbackFactory;
import com.yihu.jw.restmodel.CommonContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.rm.base.BaseVersionRequestMapping;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@FeignClient(
        name = CommonContants.svr_base // name值是eurika的实例名字
        ,fallbackFactory  = UserUrlVersionFeginFallbackFactory.class
)
@RequestMapping(value = BaseVersionRequestMapping.api_common)
public interface UserUrlVersionFegin {

    @PostMapping(value = BaseVersionRequestMapping.UserUrlVersion.api_create,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop create(@RequestBody String jsonData) throws JiWeiException;

    @PutMapping(value =  BaseVersionRequestMapping.UserUrlVersion.api_update,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop update(@RequestBody String jsonData) throws JiWeiException;

    @DeleteMapping(value =BaseVersionRequestMapping.UserUrlVersion.api_delete)
    Envelop delete(@PathVariable(required = true, value = "codes") String codes, @RequestParam(value = "userCode") String userCode, @RequestParam(value = "userName") String userName) throws JiWeiException;

    @GetMapping(value = BaseVersionRequestMapping.UserUrlVersion.api_getByCode)
    Envelop findByCode(@PathVariable(required = true, value = "code") String code) throws JiWeiException;

    @RequestMapping(value = BaseVersionRequestMapping.UserUrlVersion.api_getList, method = RequestMethod.GET)
    Envelop getList(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts,
            @RequestParam(value = "size", required = false) int size,
            @RequestParam(value = "page", required = false) int page) throws JiWeiException;

    @GetMapping(value = BaseVersionRequestMapping.UserUrlVersion.api_getListNoPage)
    Envelop getListNoPage(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts) throws JiWeiException;

    @GetMapping(value = BaseVersionRequestMapping.UserUrlVersion.api_changeUserVersion)
    Envelop changeUserVersion(@RequestParam(value="serverCode")String serverCode,@RequestParam(value="userCodes") String userCodes,@RequestParam(value="userCode") String userCode, @RequestParam(value="userName")String userName, @RequestParam(value="saasId")String saasId) throws JiWeiException;

}
