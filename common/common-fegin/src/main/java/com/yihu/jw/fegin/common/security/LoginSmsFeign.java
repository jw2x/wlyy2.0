package com.yihu.jw.fegin.common.security;

import com.yihu.jw.fegin.fallbackfactory.base.base.SaasFeignFallbackFactory;
import com.yihu.jw.restmodel.CommonContants;
import com.yihu.jw.rm.base.BaseLoginRequestMapping;
import com.yihu.jw.rm.base.BaseRequestMapping;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 刘文彬 on 2018/4/23.
 */
@FeignClient(
        name = CommonContants.svr_base // name值是eurika的实例名字
)
@RequestMapping(value = BaseRequestMapping.api_base_common)
public interface LoginSmsFeign {
    @GetMapping(BaseLoginRequestMapping.BaseLoginAccount.mobileSendSms)
    void createCode(HttpServletRequest request,HttpServletResponse response)throws Exception;
}
