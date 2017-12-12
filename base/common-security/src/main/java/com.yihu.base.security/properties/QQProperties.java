package com.yihu.base.security.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;
import org.springframework.stereotype.Component;

/**
 * Created by chenweida on 2017/12/9.
 */
@Component
public class QQProperties extends SocialProperties {
    private String providerId = "qq";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

}
