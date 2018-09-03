package com.yihu.jw.security.oauth2.provider.client;

import com.yihu.utils.network.IPInfoUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by progr1mmer on 2017/9/19.
 */
public class WlyyJdbcClientRedirectUriService {

    private JdbcTemplate jdbcTemplate;

    public WlyyJdbcClientRedirectUriService(JdbcTemplate jdbcTemplate) {
        Assert.notNull(jdbcTemplate, "jdbcTemplate required");
        this.jdbcTemplate = jdbcTemplate;
    }

    public String loadValidUri (String clientId, String redirectUriParameter) {
        if (null == redirectUriParameter) {
            return redirectUriParameter;
        }
        if (redirectUriParameter.indexOf("?") != -1) {
            redirectUriParameter = redirectUriParameter.substring(0, redirectUriParameter.indexOf("?"));
        }
        Map<String, Object> redirects = loadRedirectUriByClientId(clientId);
        if (redirectUriParameter.equals(redirects.get("redirect_uri")) || redirectUriParameter.equals(redirects.get("redirect_out_uri"))) {
            return redirects.get("redirect_uri").toString();
        } else {
            return redirectUriParameter;
        }
    }

    public String loadAccessUri (String clientId, String redirectUriParameter) {
        if (redirectUriParameter != null) {
            return redirectUriParameter;
        }
        Map<String, Object> redirects = loadRedirectUriByClientId(clientId);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = IPInfoUtils.getIPAddress(request);
        boolean isInnerIp = IPInfoUtils.isInnerIP(ip);
        if (isInnerIp) {
            return (String) redirects.get("redirect_uri");
        } else {
            return redirects.get("redirect_out_uri") != null ? (String) redirects.get("redirect_out_uri") : (String) redirects.get("redirect_uri");
        }
    }

    private Map<String, Object> loadRedirectUriByClientId(String clientId) {
        String selectQuery = "select web_server_redirect_uri as redirect_uri, web_server_redirect_out_uri as redirect_out_uri from oauth_client_details where client_id = ?";
        Map<String, Object> resultMap = jdbcTemplate.queryForMap(selectQuery, clientId);
        return resultMap;
    }
}
