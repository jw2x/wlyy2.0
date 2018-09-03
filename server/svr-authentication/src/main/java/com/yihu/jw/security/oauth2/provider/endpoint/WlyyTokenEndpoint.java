/*
 * Copyright (c) 2015 MONKEYK Information Technology Co. Ltd
 * www.monkeyk.com
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * MONKEYK Information Technology Co. Ltd ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with MONKEYK Information Technology Co. Ltd.
 */
package com.yihu.jw.security.oauth2.provider.endpoint;

import com.yihu.jw.security.model.Oauth2Envelop;
import com.yihu.jw.security.oauth2.common.WlyyOAuth2AccessToken;
import com.yihu.jw.security.oauth2.provider.WlyyTokenGranter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.*;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.endpoint.AbstractEndpoint;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestValidator;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

/**
 * <p>
 * Endpoint for token requests as described in the OAuth2 spec. Clients post requests with a <code>grant_type</code>
 * parameter (e.g. "authorization_code") and other parameters as determined by the grant type. Supported grant types are
 * handled by the provided {@link com.yihu.jw.security.oauth2.provider.WlyyTokenGranter token
 * granter}.
 * </p>
 *
 * <p>
 * Clients must be authenticated using a Spring Security {@link Authentication} to access this endpoint, and the client
 * id is extracted from the authentication token. The best way to arrange this (as per the OAuth2 spec) is to use HTTP
 * basic authentication for this endpoint with standard Spring Security support.
 * </p>
 *
 * @author Progr1mmer
 * @created on 2018/8/29.
 */
@RestController
public class WlyyTokenEndpoint extends AbstractEndpoint {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private WlyyTokenGranter tokenGranter;
    @Autowired
    private ClientDetailsService clientDetailsService;

    private OAuth2RequestValidator oAuth2RequestValidator = new DefaultOAuth2RequestValidator();

    private Set<HttpMethod> allowedRequestMethods = new HashSet<>(Arrays.asList(HttpMethod.POST));

    @PostConstruct
    private void init() {
        super.setTokenGranter(tokenGranter);
        super.setClientDetailsService(clientDetailsService);
    }

    @RequestMapping(value = "/oauth/token", method=RequestMethod.GET)
    public ResponseEntity<WlyyOAuth2AccessToken> getAccessToken(@RequestParam
            Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        if (!allowedRequestMethods.contains(HttpMethod.GET)) {
            throw new HttpRequestMethodNotSupportedException("GET");
        }
        return postAccessToken(parameters);
    }

    @RequestMapping(value = "/oauth/token", method=RequestMethod.POST)
    public ResponseEntity<WlyyOAuth2AccessToken> postAccessToken(@RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {

        String clientId = getClientId(parameters);
        if (StringUtils.isEmpty(clientId)) {
            throw new InvalidClientException("Missing clientId");
        }

        ClientDetails authenticatedClient = getClientDetailsService().loadClientByClientId(clientId);

        TokenRequest tokenRequest = getOAuth2RequestFactory().createTokenRequest(parameters, authenticatedClient);

        /*
         * skip this step
         */
        /*if (clientId != null && !clientId.equals("")) {
            // Only validate the client details if a client authenticated during this
            // request.
            if (!clientId.equals(tokenRequest.getClientId())) {
                // double check to make sure that the client ID in the token request is the same as that in the
                // authenticated client
                throw new InvalidClientException("Given client ID does not match authenticated client");
            }
        }*/
        if (authenticatedClient != null) {
            oAuth2RequestValidator.validateScope(tokenRequest, authenticatedClient);
        }
        if (!StringUtils.hasText(tokenRequest.getGrantType())) {
            throw new InvalidRequestException("Missing grant type");
        }
        if (tokenRequest.getGrantType().equals("implicit")) {
            throw new InvalidGrantException("Implicit grant type not supported from token endpoint");
        }

        if (isAuthCodeRequest(parameters)) {
            // The scope was requested or determined during the authorization step
            if (!tokenRequest.getScope().isEmpty()) {
                logger.debug("Clearing scope of incoming token request");
                tokenRequest.setScope(Collections.<String> emptySet());
            }
        }

        if (isRefreshTokenRequest(parameters)) {
            // A refresh token has its own default scopes, so we should ignore any added by the factory here.
            tokenRequest.setScope(OAuth2Utils.parseParameterList(parameters.get(OAuth2Utils.SCOPE)));
        }

        OAuth2AccessToken token = getTokenGranter().grant(tokenRequest.getGrantType(), tokenRequest);
        if (token == null) {
            throw new UnsupportedGrantTypeException("Unsupported grant type: " + tokenRequest.getGrantType());
        }
        // ----------------- Simple Result ----------------
        WlyyOAuth2AccessToken wlyyOAuth2AccessToken = new WlyyOAuth2AccessToken();
        wlyyOAuth2AccessToken.setAccessToken(token.getValue());
        wlyyOAuth2AccessToken.setTokenType(token.getTokenType());
        wlyyOAuth2AccessToken.setExpiresIn(token.getExpiresIn());
        wlyyOAuth2AccessToken.setRefreshToken(token.getRefreshToken().getValue());
        wlyyOAuth2AccessToken.setScope(org.apache.commons.lang.StringUtils.join(token.getScope(), " "));
        wlyyOAuth2AccessToken.setState(parameters.get("state"));
        // ----------------- Simple Result ----------------

        return getResponse(wlyyOAuth2AccessToken);

    }

    /**
     * @param principal the currently authentication principal
     * @return a client id if there is one in the principal
     */
    @Deprecated
    protected String getClientId(Principal principal) {
        Authentication client = (Authentication) principal;
        if (!client.isAuthenticated()) {
            throw new InsufficientAuthenticationException("The client is not authenticated.");
        }
        String clientId = client.getName();
        if (client instanceof OAuth2Authentication) {
            // Might be a client and user combined authentication
            clientId = ((OAuth2Authentication) client).getOAuth2Request().getClientId();
        }
        return clientId;
    }

    /**
     * @param parameters the request parameters (omit the basic certification)
     * @return a client id if there is one in the principal
     */
    protected String getClientId(Map<String, String> parameters) {
        return parameters.get("client_id");
    }

    /*@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<OAuth2Exception> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) throws Exception {
        logger.info("Handling error: " + e.getClass().getSimpleName() + ", " + e.getMessage());
        return getExceptionTranslator().translate(e);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<OAuth2Exception> handleException(Exception e) throws Exception {
        logger.info("Handling error: " + e.getClass().getSimpleName() + ", " + e.getMessage());
        return getExceptionTranslator().translate(e);
    }

    @ExceptionHandler(ClientRegistrationException.class)
    public ResponseEntity<OAuth2Exception> handleClientRegistrationException(Exception e) throws Exception {
        logger.info("Handling error: " + e.getClass().getSimpleName() + ", " + e.getMessage());
        return getExceptionTranslator().translate(new BadClientCredentialsException());
    }

    @ExceptionHandler(OAuth2Exception.class)
    public ResponseEntity<OAuth2Exception> handleException(OAuth2Exception e) throws Exception {
        logger.info("Handling error: " + e.getClass().getSimpleName() + ", " + e.getMessage());
        return getExceptionTranslator().translate(e);
    }*/

    /**
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Oauth2Envelop> handleException(Exception e) throws Exception {
        LOG.info(e.getMessage(), e);
        return handleOAuth2Exception(new Oauth2Envelop(e.getMessage(), -1), e);
    }

    private ResponseEntity<WlyyOAuth2AccessToken> getResponse(WlyyOAuth2AccessToken accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "no-store");
        headers.set("Pragma", "no-cache");
        return new ResponseEntity<>(accessToken, headers, HttpStatus.OK);
    }

    /**
     * return results directly
     * @param authenticationFailed
     * @return
     * @throws IOException
     */
    private ResponseEntity<Oauth2Envelop> handleOAuth2Exception(Oauth2Envelop authenticationFailed, Exception e) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "no-store");
        headers.set("Pragma", "no-cache");
        headers.set("WWW-Authenticate", String.format("%s %s", OAuth2AccessToken.BEARER_TYPE, e.getMessage()));
        ResponseEntity<Oauth2Envelop> response = new ResponseEntity<>(authenticationFailed, headers, HttpStatus.OK);
        return response;
    }

    private boolean isRefreshTokenRequest(Map<String, String> parameters) {
        return "refresh_token".equals(parameters.get("grant_type")) && parameters.get("refresh_token") != null;
    }

    private boolean isAuthCodeRequest(Map<String, String> parameters) {
        return "authorization_code".equals(parameters.get("grant_type")) && parameters.get("code") != null;
    }

    public void setOAuth2RequestValidator(OAuth2RequestValidator oAuth2RequestValidator) {
        this.oAuth2RequestValidator = oAuth2RequestValidator;
    }

    public void setAllowedRequestMethods(Set<HttpMethod> allowedRequestMethods) {
        this.allowedRequestMethods = allowedRequestMethods;
    }

}
