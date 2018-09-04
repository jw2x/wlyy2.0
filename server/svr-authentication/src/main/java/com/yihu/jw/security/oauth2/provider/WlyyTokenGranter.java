package com.yihu.jw.security.oauth2.provider;

import com.yihu.jw.security.oauth2.core.redis.WlyyRedisVerifyCodeService;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.security.oauth2.common.exceptions.RedirectMismatchException;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.implicit.ImplicitGrantService;
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenRequest;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.util.Assert;

import java.util.*;

/**
 * Token授权器。
 * @author Progr1mmer
 * @created 2018.01.09
 */
public class WlyyTokenGranter implements TokenGranter {
    private final Map<String, TokenGranter> tokenGranters = new HashMap<>();

    public WlyyTokenGranter(AuthenticationManager authenticationManager,
                            AuthorizationServerTokenServices tokenServices,
                            AuthorizationCodeServices authorizationCodeServices,
                            ClientDetailsService clientDetailsService,
                            OAuth2RequestFactory requestFactory,
                            WlyyRedisVerifyCodeService wlyyRedisVerifyCodeService) {

        tokenGranters.put(WlyyAuthorizationCodeGranter.GRANT_TYPE,
                new WlyyAuthorizationCodeGranter(
                        tokenServices,
                        authorizationCodeServices,
                        clientDetailsService,
                        requestFactory
                ));

        tokenGranters.put(WlyyResourceOwnerPasswordTokenGranter.GRANT_TYPE,
                new WlyyResourceOwnerPasswordTokenGranter(
                        authenticationManager,
                        tokenServices,
                        clientDetailsService,
                        requestFactory
                ));

        tokenGranters.put(WlyyRefreshTokenGranter.GRANT_TYPE,
                new WlyyRefreshTokenGranter(
                        tokenServices,
                        clientDetailsService,
                        requestFactory
                ));

        tokenGranters.put(WlyyImplicitTokenGranter.GRANT_TYPE,
                new WlyyImplicitTokenGranter(
                        tokenServices,
                        clientDetailsService,
                        requestFactory
                ));

        tokenGranters.put(WlyyCaptchaTokenGranter.GRANT_TYPE,
                new WlyyCaptchaTokenGranter(
                        authenticationManager,
                        tokenServices,
                        clientDetailsService,
                        requestFactory,
                        wlyyRedisVerifyCodeService
                ));
    }

    public OAuth2AccessToken grant(String grantType, TokenRequest tokenRequest) {
        if (tokenGranters.get(grantType) != null) {
            OAuth2AccessToken grant = tokenGranters.get(grantType).grant(grantType, tokenRequest);
            return grant;
        }
        return null;
    }

    /**
     * authorization_code模式Token授权器。
     */
    public static class WlyyAuthorizationCodeGranter extends AbstractTokenGranter {
        public static final String GRANT_TYPE = "authorization_code";

        private final AuthorizationCodeServices authorizationCodeServices;

        public WlyyAuthorizationCodeGranter(AuthorizationServerTokenServices tokenServices,
                                           AuthorizationCodeServices authorizationCodeServices,
                                           ClientDetailsService clientDetailsService,
                                           OAuth2RequestFactory requestFactory) {
            this(tokenServices, authorizationCodeServices, clientDetailsService, requestFactory, GRANT_TYPE);
        }

        protected WlyyAuthorizationCodeGranter(AuthorizationServerTokenServices tokenServices,
                                              AuthorizationCodeServices authorizationCodeServices,
                                              ClientDetailsService clientDetailsService,
                                              OAuth2RequestFactory requestFactory,
                                              String grantType) {
            super(tokenServices, clientDetailsService, requestFactory, grantType);
            this.authorizationCodeServices = authorizationCodeServices;
        }

        @Override
        protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {

            Map<String, String> parameters = tokenRequest.getRequestParameters();
            String authorizationCode = parameters.get("code");
            String redirectUri = parameters.get(OAuth2Utils.REDIRECT_URI);

            if (authorizationCode == null) {
                throw new InvalidRequestException("An authorization code must be supplied.");
            }

            OAuth2Authentication storedAuth = authorizationCodeServices.consumeAuthorizationCode(authorizationCode);
            if (storedAuth == null) {
                throw new InvalidGrantException("Invalid authorization code: " + authorizationCode);
            }

            OAuth2Request pendingOAuth2Request = storedAuth.getOAuth2Request();
            String oauthUri;
            if (pendingOAuth2Request.getRedirectUri().indexOf("?") != -1) {
                oauthUri = pendingOAuth2Request.getRedirectUri().substring(0, pendingOAuth2Request.getRedirectUri().indexOf("?"));
            } else {
                oauthUri = pendingOAuth2Request.getRedirectUri();
            }
            // https://jira.springsource.org/browse/SECOAUTH-333
            // This might be null, if the authorization was done without the redirect_uri parameter
            String redirectUriApprovalParameter = pendingOAuth2Request.getRequestParameters().get(
                    OAuth2Utils.REDIRECT_URI);
            if ((redirectUri != null || redirectUriApprovalParameter != null)
                    && !oauthUri.equals(redirectUri)) {
                throw new RedirectMismatchException("Redirect URI mismatch.");
            }

            String pendingClientId = pendingOAuth2Request.getClientId();
            String clientId = tokenRequest.getClientId();
            if (clientId != null && !clientId.equals(pendingClientId)) {
                // just a sanity check.
                throw new InvalidClientException("Client ID mismatch");
            }

            // Secret is not required in the authorization request, so it won't be available
            // in the pendingAuthorizationRequest. We do want to check that a secret is provided
            // in the token request, but that happens elsewhere.

            Map<String, String> combinedParameters = new HashMap<String, String>(pendingOAuth2Request
                    .getRequestParameters());
            // Combine the parameters adding the new ones last so they override if there are any clashes
            combinedParameters.putAll(parameters);

            // Make a new stored request with the combined parameters
            OAuth2Request finalStoredOAuth2Request = pendingOAuth2Request.createOAuth2Request(combinedParameters);

            Authentication userAuth = storedAuth.getUserAuthentication();

            return new OAuth2Authentication(finalStoredOAuth2Request, userAuth);
        }
    }

    /**
     * password模式Token授权器。
     */
    public static class WlyyResourceOwnerPasswordTokenGranter extends AbstractTokenGranter {
        private static final String GRANT_TYPE = "password";

        private final AuthenticationManager authenticationManager;

        public WlyyResourceOwnerPasswordTokenGranter(AuthenticationManager authenticationManager,
                                                 AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
            this(authenticationManager, tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        }

        protected WlyyResourceOwnerPasswordTokenGranter(AuthenticationManager authenticationManager, AuthorizationServerTokenServices tokenServices,
                                                    ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType) {
            super(tokenServices, clientDetailsService, requestFactory, grantType);
            this.authenticationManager = authenticationManager;
        }

        @Override
        protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {

            Map<String, String> parameters = new LinkedHashMap<String, String>(tokenRequest.getRequestParameters());
            String username = parameters.get("username");
            String password = parameters.get("password");
            // Protect from downstream leaks of password
            parameters.remove("password");

            Authentication userAuth = new UsernamePasswordAuthenticationToken(username, password);
            ((AbstractAuthenticationToken) userAuth).setDetails(parameters);
            try {
                userAuth = authenticationManager.authenticate(userAuth);
            }
            catch (AccountStatusException ase) {
                //covers expired, locked, disabled cases (mentioned in section 5.2, draft 31)
                throw new InvalidGrantException(ase.getMessage());
            }
            catch (BadCredentialsException e) {
                // If the username/password are wrong the spec says we should send 400/invalid grant
                throw new InvalidGrantException(e.getMessage());
            }
            if (userAuth == null || !userAuth.isAuthenticated()) {
                throw new InvalidGrantException("Could not authenticate user: " + username);
            }

            OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
            return new OAuth2Authentication(storedOAuth2Request, userAuth);
        }
    }

    /**
     * refresh模式Token授权器。
     */
    public static class WlyyRefreshTokenGranter extends AbstractTokenGranter {
        static final String GRANT_TYPE = "refresh_token";

        public WlyyRefreshTokenGranter(AuthorizationServerTokenServices tokenServices,
                                      ClientDetailsService clientDetailsService,
                                      OAuth2RequestFactory requestFactory) {
            super(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        }

        @Override
        protected OAuth2AccessToken getAccessToken(ClientDetails client, TokenRequest tokenRequest) {
            String refreshToken = tokenRequest.getRequestParameters().get(GRANT_TYPE);

            return getTokenServices().refreshAccessToken(refreshToken, tokenRequest);
        }
    }

    /**
     * Implicit模式Token授权器。
     */
    public static class WlyyImplicitTokenGranter extends AbstractTokenGranter {
        private static final String GRANT_TYPE = "implicit";

        public WlyyImplicitTokenGranter(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
            this(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        }

        protected WlyyImplicitTokenGranter(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService,
                                       OAuth2RequestFactory requestFactory, String grantType) {
            super(tokenServices, clientDetailsService, requestFactory, grantType);
        }

        @Override
        protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest clientToken) {

            Authentication userAuth = SecurityContextHolder.getContext().getAuthentication();
            if (userAuth == null || !userAuth.isAuthenticated()) {
                throw new InsufficientAuthenticationException("There is no currently logged in user");
            }
            Assert.state(clientToken instanceof ImplicitTokenRequest, "An ImplicitTokenRequest is required here. Caller needs to wrap the TokenRequest.");

            OAuth2Request requestForStorage = ((ImplicitTokenRequest)clientToken).getOAuth2Request();

            return new OAuth2Authentication(requestForStorage, userAuth);

        }

        @SuppressWarnings("deprecation")
        public void setImplicitGrantService(ImplicitGrantService service) {
        }
    }

    /**
     * verify_code模式Token授权器。
     */
    public static class WlyyCaptchaTokenGranter extends AbstractTokenGranter {
        private static final String GRANT_TYPE = "captcha";

        private final AuthenticationManager authenticationManager;
        // Ehr Properties
        private final WlyyRedisVerifyCodeService wlyyRedisVerifyCodeService;

        public WlyyCaptchaTokenGranter(AuthenticationManager authenticationManager,
                                         AuthorizationServerTokenServices tokenServices,
                                         ClientDetailsService clientDetailsService,
                                         OAuth2RequestFactory requestFactory,
                                         WlyyRedisVerifyCodeService wlyyRedisVerifyCodeService) {
            this(authenticationManager, tokenServices, clientDetailsService, requestFactory, GRANT_TYPE, wlyyRedisVerifyCodeService);
        }

        protected WlyyCaptchaTokenGranter(AuthenticationManager authenticationManager,
                                            AuthorizationServerTokenServices tokenServices,
                                            ClientDetailsService clientDetailsService,
                                            OAuth2RequestFactory requestFactory,
                                            String grantType,
                                            WlyyRedisVerifyCodeService wlyyRedisVerifyCodeService) {
            super(tokenServices, clientDetailsService, requestFactory, grantType);
            this.authenticationManager = authenticationManager;
            this.wlyyRedisVerifyCodeService = wlyyRedisVerifyCodeService;
        }

        @Override
        protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {

            Map<String, String> parameters = new LinkedHashMap<String, String>(tokenRequest.getRequestParameters());
            String client_id = parameters.get("client_id");
            String username = parameters.get("username");
            String verify_code = parameters.get("captcha");

            if (!wlyyRedisVerifyCodeService.verification(client_id, username, verify_code)){
                throw new InvalidGrantException("Invalid captcha");
            }
            Authentication userAuth = new UsernamePasswordAuthenticationToken(username, verify_code, getGrantedAuthorities(username));
            ((AbstractAuthenticationToken) userAuth).setDetails(parameters);
            OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
            return new OAuth2Authentication(storedOAuth2Request, userAuth);
        }

        private Collection<? extends GrantedAuthority> getGrantedAuthorities(String username) {
            Collection<GrantedAuthority> authorities = new ArrayList<>(1);
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            return authorities;
        }
    }
}

