package com.yihu.base.security.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by chenweida on 2017/12/5.
 */
@Component
public class AccessTokenPorperties {
    @Value("${security.oauth2.token.accessTokenValidityHours}")
    private Integer accessTokenValidityHours = 2; //accesstoken超时时间

    @Value("${security.oauth2.token.refreshTokenValidityHours}")
    private Integer refreshTokenValidityHours = 2;//刷新token过期时间


    public Integer getAccessTokenValidityHours() {
        return accessTokenValidityHours;
    }

    public void setAccessTokenValidityHours(Integer accessTokenValidityHours) {
        this.accessTokenValidityHours = accessTokenValidityHours;
    }

    public Integer getRefreshTokenValidityHours() {
        return refreshTokenValidityHours;
    }

    public void setRefreshTokenValidityHours(Integer refreshTokenValidityHours) {
        this.refreshTokenValidityHours = refreshTokenValidityHours;
    }
}
