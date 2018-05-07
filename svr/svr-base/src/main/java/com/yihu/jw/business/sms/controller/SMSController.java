package com.yihu.jw.business.sms.controller;

import com.yihu.jw.base.sms.BaseSmsDO;
import com.yihu.jw.business.sms.service.SmsService;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.restmodel.base.sms.SmsVO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.common.base.BaseEnvelop;
import com.yihu.jw.rm.base.BaseSmsRequestMapping;
import com.yihu.jw.util.common.MobileUtils;
import com.yihu.jw.util.common.NetworkUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenweida on 2017/5/19.
 * 注册发送短信验证码
 */
@RestController
@RequestMapping(BaseSmsRequestMapping.api_common)
@Api(value = "短信模块", description = "短信模块接口管理")
public class SMSController extends EnvelopRestController {
    @Autowired
    private SmsService smsService;


    @ApiOperation(value = "（注册、找回密码）发送校验码")
    @PostMapping(value = BaseSmsRequestMapping.Sms.api_sms_send, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseEnvelop send(@ApiParam(name = "mobile", value = "手机号码", required = true) @RequestParam(value = "mobile", required = true) String mobile,
                            @ApiParam(name = "type", value = "短信验证码类型", required = true) @RequestParam(value = "type", required = true) int type,
                            @ApiParam(name = "saasId", value = "saasId", required = true) @RequestParam(value = "saasId", required = true) String saasId,
                            HttpServletRequest request) throws IOException {
        try {
            if (type > 10 || type < 1) {
                return BaseEnvelop.getError("无效的请求！");
            }
            if (StringUtils.isEmpty(mobile)) {
                return BaseEnvelop.getError("手机号码不允许为空！");
            }
            if(!MobileUtils.checkCellphone(mobile)){
                return BaseEnvelop.getError("手机号码格式不正确！");
            }
            // 获取ip地址
            String ip = NetworkUtil.getIpAddress(request);
            if (StringUtils.isEmpty(ip)) {
                return  BaseEnvelop.getError("无效的ip请求！");
            }
            BaseEnvelop envelop1 = smsService.send(mobile,ip,type,saasId);
            return envelop1;
        } catch (Exception e) {
            error(e);
            return  BaseEnvelop.getError("手机号有误，无法发送验证！");
        }
    }

}
