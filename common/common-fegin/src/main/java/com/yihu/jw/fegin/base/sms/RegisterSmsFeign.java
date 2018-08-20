package com.yihu.jw.fegin.base.sms;

import com.yihu.jw.restmodel.CommonContants;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.rm.base.BaseSmsRequestMapping;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

/**
 * Created by 刘文彬 on 2018/4/23.
 */
@FeignClient(
        name = CommonContants.svr_base // name值是eurika的实例名字
)
@RequestMapping(value = BaseSmsRequestMapping.api_common)
public interface RegisterSmsFeign {
    @PostMapping(value = BaseSmsRequestMapping.Sms.api_sms_send, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop send(@ApiParam(name = "mobile", value = "手机号码", required = true) @RequestParam(value = "mobile", required = true) String mobile,
                 @ApiParam(name = "type", value = "短信验证码类型", required = true) @RequestParam(value = "type", required = true) int type,
                 @ApiParam(name = "saasId", value = "saasId", required = true) @RequestParam(value = "saasId", required = true) String saasId) throws IOException ;

    }
