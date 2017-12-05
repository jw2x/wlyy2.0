package com.yihu.base.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by chenweida on 2017/12/5.
 */
@ConfigurationProperties(prefix = "security.oauth2.token")
public class AccessTokenPorperties {

    private Integer accessTokenValiditySeconds = 2; //accesstoken超时时间

    private Integer refreshTokenValiditySeconds = 2;//刷新token过期时间


    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }
}
