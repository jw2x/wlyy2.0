package com.yihu.jw.controller.base.login;

import com.yihu.jw.common.base.base.BaseContants;
import com.yihu.jw.fegin.base.sms.RegisterSmsFeign;
import com.yihu.jw.fegin.common.security.LoginSmsFeign;
import com.yihu.jw.restmodel.common.base.BaseEnvelop;
import com.yihu.jw.rm.base.BaseLoginRequestMapping;
import com.yihu.jw.rm.base.BaseSmsRequestMapping;
import com.yihu.jw.rm.base.BaseUserRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by 刘文彬 on 2018/4/24.
 */
@RestController
@RequestMapping(BaseLoginRequestMapping.api_common)
@Api(value = "注册发送短信验证码", description = "注册发送短信验证码")
public class RegisterSmsContorller {

    @Autowired
    private RegisterSmsFeign registerSmsFeign;

    @PostMapping(value = BaseSmsRequestMapping.Sms.api_sms_send, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "发送短信验证码", notes = "发送短信验证码")
    BaseEnvelop send(@ApiParam(name = "mobile", value = "手机号码", required = true) @RequestParam(value = "mobile", required = true) String mobile,
                     @ApiParam(name = "type", value = "短信验证码类型", required = true) @RequestParam(value = "type", required = true) int type,
                     @ApiParam(name = "saasId", value = "saasId", required = true) @RequestParam(value = "saasId", required = true) String saasId) throws IOException{
        return registerSmsFeign.send(mobile,type,saasId);
    }
}
