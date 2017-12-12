package com.yihu.base.security.rbas.provider;

import com.yihu.base.security.rbas.IRbasService;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by chenweida on 2017/12/5.
 * 角色认证
 */
@Component
@Order(Integer.MAX_VALUE)
public class RbasAuthorizeConfigProvider implements AuthorizeConfigProvider {
    @Resource(name="rbasService")
    private IRbasService rbasService;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry expressionInterceptUrlRegistry) {
        expressionInterceptUrlRegistry.anyRequest().access("@rbasService.hasPerssion(request,authentication)");
    }
}
