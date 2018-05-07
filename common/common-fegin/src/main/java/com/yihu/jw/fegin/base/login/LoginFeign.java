package com.yihu.jw.fegin.base.login;

import com.yihu.jw.fegin.fallbackfactory.base.base.FunctionFeignFallbackFactory;
import com.yihu.jw.restmodel.CommonContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.base.BaseEnvelop;
import com.yihu.jw.rm.base.BaseLoginRequestMapping;
import com.yihu.jw.rm.base.BaseRequestMapping;
import com.yihu.jw.rm.base.BaseUserRequestMapping;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 刘文彬 on 2018/4/20.
 */
@FeignClient(
        name = CommonContants.svr_base // name值是eurika的实例名字
//        ,fallbackFactory  = FunctionFeignFallbackFactory.class
)
@RequestMapping(value = BaseLoginRequestMapping.api_common)
public interface LoginFeign {

    @PostMapping(value = BaseLoginRequestMapping.BaseLoginAccount.api_checkoutInfo, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop checkoutInfo();

    @PostMapping(value = BaseLoginRequestMapping.BaseLoginAccount.api_accountSub, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop register(
            @RequestParam(value = "mobilePhone", required = true) String mobilePhone,
            @RequestParam(value = "saasId", required = true) String saasId,
            @RequestParam(value = "type", required = true) int type,
            @RequestParam(value = "captcha", required = true) String captcha,
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "idcard", required = true) String idcard,
            @RequestParam(value = "ssc", required = true) String ssc);


    @PostMapping(value = BaseLoginRequestMapping.BaseLoginAccount.api_login, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop login(@RequestParam(value = "mobilePhone", required = false) String mobilePhone,
                           @RequestParam(value = "password", required = false) String password,
                           @RequestParam(value = "saasId", required = true) String saasId,
                           @RequestParam(value = "captcha", required = false) String captcha);
}