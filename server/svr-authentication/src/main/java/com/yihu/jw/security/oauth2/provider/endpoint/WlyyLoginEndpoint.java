package com.yihu.jw.security.oauth2.provider.endpoint;

import com.yihu.jw.security.model.Captcha;
import com.yihu.jw.security.model.PublicKey;
import com.yihu.jw.security.oauth2.core.redis.WlyyRedisVerifyCodeService;
import com.yihu.jw.security.oauth2.provider.error.WlyyOAuth2ExceptionTranslator;
import com.yihu.jw.security.oauth2.provider.WlyyTokenGranter;
import com.yihu.jw.security.core.userdetails.jdbc.WlyyUserDetailsService;
import com.yihu.jw.security.model.Oauth2Envelop;
import com.yihu.jw.security.model.WlyyUserSimple;
import com.yihu.utils.security.RSAUtils;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.*;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.endpoint.AbstractEndpoint;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestValidator;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * Endpoint for default login verify and sso login token verify
 * will return the user's basic information and token certificate
 * </p>
 *
 * @author Progr1mmer
 * @created on 2018/8/29.
 */
@RestController
public class WlyyLoginEndpoint extends AbstractEndpoint {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private OAuth2RequestFactory oAuth2RequestFactory;
    private OAuth2RequestValidator oAuth2RequestValidator = new DefaultOAuth2RequestValidator();

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ClientDetailsService clientDetailsService;
    @Autowired
    private WlyyTokenGranter tokenGranter;
    @Autowired
    private WlyyOAuth2ExceptionTranslator wlyyOAuth2ExceptionTranslator;
    @Autowired
    private RedisTokenStore tokenStore;
    @Autowired
    private WlyyUserDetailsService userDetailsService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WlyyRedisVerifyCodeService wlyyRedisVerifyCodeService;

    @PostConstruct
    private void init() {
        super.setTokenGranter(tokenGranter);
    }

    /**
     * 登陆
     * @param parameters
     * 不定入参：
     * client_id 应用标识
     * captcha 验证码
     * password 密码
     * username 用户名/手机/身份证号
     * login_type 用户类型 1或默认为user，2：医生登录，3：患者登录
     * @param httpSession
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/oauth/login", method = RequestMethod.POST)
    public ResponseEntity<Oauth2Envelop<WlyyUserSimple>> login(@RequestParam Map<String, String> parameters, HttpSession httpSession) throws Exception {
        String username = parameters.get("username");
        if (StringUtils.isEmpty(username)) {
            throw new InvalidRequestException("username");
        }
        String client_id = parameters.get("client_id");
        if (StringUtils.isEmpty(client_id)) {
            throw new InvalidRequestException("client_id");
        }
        if (StringUtils.isEmpty(parameters.get("captcha"))) {
            parameters.put("grant_type", "password");
//            if (parameters.get("password") != null) {
//                RSAPrivateKey rsaPrivateKey = (RSAPrivateKey)httpSession.getAttribute("privateKey");
//                parameters.put("password", RSAUtils.decryptByPrivateKey(new String(Base64.decodeBase64(parameters.get("password"))), rsaPrivateKey));
//            }
        } else {
            parameters.put("grant_type", "captcha");
        }

        ClientDetails authenticatedClient = clientDetailsService.loadClientByClientId(client_id);

        TokenRequest tokenRequest = oAuth2RequestFactory.createTokenRequest(parameters, authenticatedClient);
        if (authenticatedClient != null) {
            oAuth2RequestValidator.validateScope(tokenRequest, authenticatedClient);
        }
        OAuth2AccessToken token = getTokenGranter().grant(tokenRequest.getGrantType(), tokenRequest);
        if (token == null) {
            throw new UnsupportedGrantTypeException("Unsupported grant type: " + tokenRequest.getGrantType());
        }
        /*如果是移动端登陆则移除之前的token，
        在网关处通过HTTP状态码告知前端是过期（402）还是账号在别处登陆（403），
        实现同一账号只能在一处登陆*/
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (request.getHeader("login-device") != null && request.getHeader("login-device").equals("mobile")) {
            tokenStore.removeAccessToken(token.getValue());
            tokenStore.removeRefreshToken(token.getRefreshToken().getValue());
            token = getTokenGranter().grant(tokenRequest.getGrantType(), tokenRequest);
        }
        if (token == null) {
            throw new UnsupportedGrantTypeException("Unsupported grant type: " + tokenRequest.getGrantType());
        }
        WlyyUserSimple wlyyUserSimple = userDetailsService.authSuccess(parameters.get("username"));
        wlyyUserSimple.setAccessToken(token.getValue());
        wlyyUserSimple.setTokenType(token.getTokenType());
        wlyyUserSimple.setExpiresIn(token.getExpiresIn());
        wlyyUserSimple.setRefreshToken(token.getRefreshToken().getValue());
        wlyyUserSimple.setUser(parameters.get("username"));
        wlyyUserSimple.setState(parameters.get("state"));
        return getResponse(wlyyUserSimple);
    }

    /**
     * 单点登陆第二步 - token验证
     * @param parameters
     * @return
     */
    @RequestMapping(value = "/oauth/sso", method = RequestMethod.POST)
    public ResponseEntity<Oauth2Envelop<WlyyUserSimple>> sso(@RequestParam Map<String, String> parameters) {
        String clientId = parameters.get("client_id");
        String accessToken = parameters.get("access_token");
        if (StringUtils.isEmpty(clientId)) {
            throw new InvalidRequestException("client_id");
        }
        if (StringUtils.isEmpty(accessToken)) {
            throw new InvalidRequestException("access_token");
        }
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(accessToken);
        if (null == oAuth2AccessToken) {
            throw new InvalidTokenException("Invalid access_token");
        }
        if (oAuth2AccessToken.isExpired()) {
            throw new InvalidTokenException("Expired accessToken");
        }

        OAuth2Authentication authentication = tokenStore.readAuthentication(accessToken);
        if (null == authentication) {
            throw new InvalidTokenException("Cant not load authentication");
        }
        String _clientId = authentication.getOAuth2Request().getClientId();
        if (!clientId.equals(_clientId)) {
            throw new InvalidTokenException("Provided token does not belong to the request client");
        }
        WlyyUserSimple wlyyUserSimple = userDetailsService.authSuccess(authentication.getName());
        wlyyUserSimple.setAccessToken(oAuth2AccessToken.getValue());
        wlyyUserSimple.setTokenType(oAuth2AccessToken.getTokenType());
        wlyyUserSimple.setExpiresIn(oAuth2AccessToken.getExpiresIn());
        wlyyUserSimple.setRefreshToken(oAuth2AccessToken.getRefreshToken().getValue());
        wlyyUserSimple.setUser(authentication.getName());
        wlyyUserSimple.setState(parameters.get("state"));
        return getResponse(wlyyUserSimple);
    }

    /**
     * 登出
     * @param parameters
     * @param request
     * @return
     */
    @RequestMapping(value = "/oauth/logout", method = RequestMethod.POST)
    public ResponseEntity<Oauth2Envelop> logout(@RequestParam Map<String, String> parameters, HttpServletRequest request) {
        String token = request.getHeader("token");
        if (null == token) {
            token = parameters.get("token");
        }
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        if (oAuth2AccessToken != null) {
            tokenStore.removeAccessToken(oAuth2AccessToken.getValue());
            tokenStore.removeRefreshToken(oAuth2AccessToken.getRefreshToken().getValue());
        }
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "no-store");
        headers.set("Pragma", "no-cache");
        Oauth2Envelop oauth2Envelop = new Oauth2Envelop("logout", 200);
        return new ResponseEntity<>(oauth2Envelop, headers, HttpStatus.OK);
    }

    /**
     * 获取公钥
     * @param httpSession
     * @param httpServletResponse
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/oauth/public_key", method = RequestMethod.GET)
    public ResponseEntity<Oauth2Envelop<PublicKey>> publicKey (
            HttpSession httpSession,
            HttpServletResponse httpServletResponse) throws Exception {
        //生成公钥和私钥
        HashMap<String, Object> map = RSAUtils.generateKeys();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) map.get("public");
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) map.get("private");
        PublicKey publicKey = new PublicKey();
        publicKey.setModulus(Base64.encodeBase64String(rsaPublicKey.getModulus().toByteArray()));
        publicKey.setExponent(Base64.encodeBase64String(rsaPublicKey.getPublicExponent().toByteArray()));
        httpSession.setAttribute("privateKey", rsaPrivateKey);
        //生成Cookie
        Cookie cookie = new Cookie("oauth2", UUID.randomUUID().toString());
        cookie.setMaxAge(60);
        cookie.setPath("/oauth");
        httpServletResponse.addCookie(cookie);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "no-store");
        headers.set("Pragma", "no-cache");
        Oauth2Envelop<PublicKey> oauth2Envelop = new Oauth2Envelop<>("public_key", 200, publicKey);
        return new ResponseEntity<>(oauth2Envelop, headers, HttpStatus.OK);
    }

    /**
     * 获取验证码
     * @param parameters
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/oauth/captcha", method = RequestMethod.GET)
    public ResponseEntity<Oauth2Envelop<Captcha>> captcha(@RequestParam Map<String, String> parameters) throws  Exception{
        String client_id = parameters.get("client_id");
        String username = parameters.get("username");
        if (StringUtils.isEmpty(client_id)) {
            throw new InvalidRequestException("client_id");
        }
        if (StringUtils.isEmpty(username)){
            throw new InvalidRequestException("username");
        }
        //验证请求间隔超时，防止频繁获取验证码
        if (!wlyyRedisVerifyCodeService.isIntervalTimeout(client_id, username)) {
            throw new IllegalAccessException("SMS request frequency is too fast");
        }
        //发送短信获取验证码
        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("clientId", client_id);
        params.add("type", "login");
        params.add("to", username);
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, reqHeaders);
        HashMap<String, Object> result = restTemplate.postForObject("http://svr-base:10020/sms_gateway/send", httpEntity, HashMap.class);
        if (200 == (Integer) result.get("status")){
            Map<String, Object> sms =  (Map)result.get("obj");
            String captcha = (String) sms.get("captcha");
            Date deadline = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String) sms.get("deadline"));
            Long expire = (deadline.getTime() - new Date().getTime()) / 1000;
            Captcha _captcha = new Captcha();
            _captcha.setCode(captcha);
            _captcha.setExpiresIn(expire.intValue());
            wlyyRedisVerifyCodeService.store(client_id, username, captcha, expire.intValue());
            Oauth2Envelop<Captcha> oauth2Envelop = new Oauth2Envelop<>("captcha", 200, _captcha);
            HttpHeaders headers = new HttpHeaders();
            headers.set("Cache-Control", "no-store");
            headers.set("Pragma", "no-cache");
            return new ResponseEntity<>(oauth2Envelop, headers, HttpStatus.OK);
        }
        throw new IllegalStateException((String) result.get("message"));
        /*Captcha _captcha = new Captcha();
        _captcha.setCode("12345");
        _captcha.setExpiresIn(10000);
        _captcha.setInterval(60);
        wlyyRedisVerifyCodeService.store(client_id, username, "12345", 100);
        Oauth2Envelop<Captcha> oauth2Envelop = new Oauth2Envelop("captcha", 200, _captcha);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "no-store");
        headers.set("Pragma", "no-cache");
        return new ResponseEntity<>(oauth2Envelop, headers, HttpStatus.OK);*/
    }

    /**
     * 验证验证码
     * @param parameters
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/oauth/captcha", method = RequestMethod.POST)
    public ResponseEntity<Oauth2Envelop> captchaCheck  (@RequestParam Map<String, String> parameters) throws  Exception{
        String client_id = parameters.get("client_id");
        String username = parameters.get("username");
        String captcha = parameters.get("captcha");
        if (StringUtils.isEmpty(client_id)) {
            throw new InvalidRequestException("client_id");
        }
        if (StringUtils.isEmpty(username)){
            throw new InvalidRequestException("username");
        }
        if (StringUtils.isEmpty(captcha)){
            throw new InvalidRequestException("captcha");
        }
        Oauth2Envelop<Boolean> oauth2Envelop;
        if (wlyyRedisVerifyCodeService.verification(client_id, username, captcha)) {
            oauth2Envelop = new Oauth2Envelop<>("验证码正确", 200, true);
        } else {
            oauth2Envelop = new Oauth2Envelop<>("验证码错误", 200, false);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "no-store");
        headers.set("Pragma", "no-cache");
        return new ResponseEntity<>(oauth2Envelop, headers, HttpStatus.OK);
    }

    @Override
    protected TokenGranter getTokenGranter() {
        return this.tokenGranter;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.state(clientDetailsService != null, "ClientDetailsService must be provided");
        Assert.state(authenticationManager != null, "AuthenticationManager must be provided");
        oAuth2RequestFactory = new DefaultOAuth2RequestFactory(clientDetailsService);
    }

    private ResponseEntity<Oauth2Envelop<WlyyUserSimple>> getResponse(WlyyUserSimple ehrUserSimple) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "no-store");
        headers.set("Pragma", "no-cache");
        Oauth2Envelop<WlyyUserSimple> oauth2Envelop = new Oauth2Envelop<>("login", 200, ehrUserSimple);
        return new ResponseEntity<>(oauth2Envelop, headers, HttpStatus.OK);
    }

    @Override
    protected WebResponseExceptionTranslator getExceptionTranslator() {
        return wlyyOAuth2ExceptionTranslator;
    }

   @ExceptionHandler(Exception.class)
    public ResponseEntity<Oauth2Envelop> handleException(Exception e) throws Exception {
        LOG.debug(e.getMessage(), e);
        if (e instanceof UsernameNotFoundException) {
            return handleOAuth2Exception(new Oauth2Envelop("用户不存在!", HttpStatus.UNAUTHORIZED.value()), e);
        } else if (e instanceof NoSuchClientException) {
            return handleOAuth2Exception(new Oauth2Envelop("应用未注册!", HttpStatus.UNAUTHORIZED.value()), e);
        } else if (e instanceof InvalidGrantException) {
            return handleOAuth2Exception(new Oauth2Envelop(invalidGrantMessage((InvalidGrantException)e), HttpStatus.UNAUTHORIZED.value()), e);
        } else if (e instanceof InvalidTokenException) {
            return handleOAuth2Exception(new Oauth2Envelop("Token有误!", HttpStatus.UNAUTHORIZED.value()), e);
        } else if (e instanceof InvalidRequestException) {
            return handleOAuth2Exception(new Oauth2Envelop("参数" + e.getMessage() + "缺失!", HttpStatus.UNAUTHORIZED.value()), e);
        } else if (e instanceof IllegalAccessException) {
            return handleOAuth2Exception(new Oauth2Envelop("短信请求频率过快,请稍后再试!", -1), e);
        } else if (e instanceof IllegalStateException) {
            return handleOAuth2Exception(new Oauth2Envelop("短信网关请求失败!", -1), e);
        }
        return handleOAuth2Exception(new Oauth2Envelop(e.getMessage(), -1), e);
    }

    private String invalidGrantMessage(InvalidGrantException e) {
        if (e.getMessage().equals("User is disabled")) {
            return "账号不可用!";
        } else if (e.getMessage().equals("User account is locked")) {
            return "账号已被锁定,请稍后重试!";
        } else if (e.getMessage().equals("Bad credentials")) {
            return userDetailsService.authFailure();
        } else if (e.getMessage().equals("Invalid captcha")) {
            return "验证码错误!";
        }
        return e.getMessage();
    }

    private ResponseEntity<Oauth2Envelop> handleOAuth2Exception(Oauth2Envelop authenticationFailed, Exception e) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "no-store");
        headers.set("Pragma", "no-cache");
        headers.set("WWW-Authenticate", String.format("%s %s", OAuth2AccessToken.BEARER_TYPE, e.getMessage()));
        ResponseEntity<Oauth2Envelop> response = new ResponseEntity<>(authenticationFailed, headers, HttpStatus.OK);
        return response;
    }

}
