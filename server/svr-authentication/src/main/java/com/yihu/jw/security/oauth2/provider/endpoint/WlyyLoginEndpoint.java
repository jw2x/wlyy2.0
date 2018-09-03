package com.yihu.jw.security.oauth2.provider.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yihu.jw.security.oauth2.core.redis.WlyyRedisVerifyCodeService;
import com.yihu.jw.security.oauth2.provider.error.WlyyOAuth2ExceptionTranslator;
import com.yihu.jw.security.oauth2.provider.WlyyTokenGranter;
import com.yihu.jw.security.core.userdetails.jdbc.WlyyUserDetailsService;
import com.yihu.jw.security.model.Oauth2Envelop;
import com.yihu.jw.security.model.WlyyUserSimple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

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
    private WlyyRedisVerifyCodeService wlyyRedisVerifyCodeService;
    @Autowired
    private ObjectMapper objectMapper;


    @PostConstruct
    private void init() {
        super.setTokenGranter(tokenGranter);
    }

    @RequestMapping(value = "/oauth/login", method = RequestMethod.POST)
    public ResponseEntity<Oauth2Envelop<WlyyUserSimple>> login(@RequestParam Map<String, String> parameters) {
        String client_id = parameters.get("client_id");
        if (StringUtils.isEmpty(client_id)) {
            throw new InvalidRequestException("client_id");
        }
        if (StringUtils.isEmpty(parameters.get("verify_code"))) {
            parameters.put("grant_type", "password");
        } else {
            parameters.put("grant_type", "verify_code");
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

    /*@RequestMapping(value = ServiceApi.Authentication.VerifyCode, method = RequestMethod.POST)
    public ResponseEntity<Envelop> verifyCode(@RequestParam Map<String, String> parameters) throws  Exception{
        Envelop envelop = new Envelop();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "no-store");
        headers.set("Pragma", "no-cache");
        String client_id = parameters.get("client_id");
        String username = parameters.get("username");
        if (StringUtils.isEmpty(username)){
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg("手机号码【"+username+"】不能为空！");
            return new ResponseEntity<>(envelop, headers, HttpStatus.OK);
        }
        VerifyCode verifyCode = new VerifyCode();
        //手机短信验证码
        RandomUtil randomUtil = new RandomUtil();
        String random = randomUtil.getRandomString(6);
        //发送短信
        String api = "MsgGW.Sms.send";
        String content = "尊敬的用户：欢迎使用健康上饶，您的验证码为:【" + random + "】,有效期10分钟，请尽快完成注册。若非本人操作，请忽略。";
        Map<String, String> apiParamMap = new HashMap<>();
        //手机号码
        apiParamMap.put("mobile", username);
        //业务标签
        apiParamMap.put("handlerId", fzHandlerId);
        //短信内容
        apiParamMap.put("content", content);
        //渠道号
        apiParamMap.put("clientId", fzClientId);
        String result = null;
        Envelop resultEnvelop = fzApiClient.fzInnerApi(api, objectMapper.writeValueAsString(apiParamMap), 1);
        if (resultEnvelop.isSuccessFlg()) {
            result = resultEnvelop.getObj().toString();
        }
        if (!StringUtils.isEmpty(result)) {
            Map<String, Object> resultMap = objectMapper.readValue(result, Map.class);
            Integer resultCode = 0;
            if (null != resultMap.get("Code") && !"".equals(resultMap.get("Code"))) {
                resultCode = Integer.valueOf(resultMap.get("Code").toString());
            }
            if (resultCode == 10000) {
                verifyCode.setExpiresIn(600);
                verifyCode.setNextRequestTime(60);
                //验证码有效期
                ehrRedisVerifyCodeService.store(client_id, username, random, 600000);
                envelop.setSuccessFlg(true);
                envelop.setObj(verifyCode);
            } else if(resultCode == -201){
                envelop.setSuccessFlg(false);
                envelop.setErrorCode(resultCode);
                envelop.setErrorMsg("短信已达每天限制的次数（10次）！");
            } else if(resultCode == -200){
                envelop.setSuccessFlg(false);
                envelop.setErrorCode(resultCode);
                envelop.setErrorMsg("短信发送频率太快（不能低于60s）！");
            } else {
                envelop.setSuccessFlg(false);
                envelop.setErrorCode(resultCode);
                envelop.setErrorMsg("短信验证码发送失败！");
            }
        } else {
            envelop.setSuccessFlg(false);
            envelop.setErrorCode(ErrorCode.REQUEST_NOT_COMPLETED.value());
            envelop.setErrorMsg("短信验证码发送失败！");
        }
        return new ResponseEntity<>(envelop, headers, HttpStatus.OK);
    }

    @RequestMapping(value = ServiceApi.Authentication.VerifyCodeExpire, method = RequestMethod.POST)
    public ResponseEntity<VerifyCode> verifyCodeExpire(@RequestParam Map<String, String> parameters) {
        String client_id = parameters.get("client_id");
        String username = parameters.get("username");
        VerifyCode verifyCode = new VerifyCode();
        int expiresIn = ehrRedisVerifyCodeService.getExpireTime(client_id, username);
        int nextRequestTime = 60 + (expiresIn - 600 ) > 0 ? 60 + (expiresIn - 600 ) : 0;
        verifyCode.setNextRequestTime(nextRequestTime);
        verifyCode.setExpiresIn(expiresIn);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "no-store");
        headers.set("Pragma", "no-cache");
        return new ResponseEntity<>(verifyCode, headers, HttpStatus.OK);
    }

    @RequestMapping(value = ServiceApi.Authentication.VerifyCodeValidate, method = RequestMethod.POST)
    public ResponseEntity<Envelop> verifyCodeValidate(@RequestParam Map<String, String> parameters) throws  Exception{
        Envelop envelop = new Envelop();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "no-store");
        headers.set("Pragma", "no-cache");
        String client_id = parameters.get("client_id");
        String username = parameters.get("username");
        String verifyCode = parameters.get("verify_code");
        if (StringUtils.isEmpty(verifyCode)){
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg("验证码不能为空！");
            return new ResponseEntity<>(envelop, headers, HttpStatus.OK);
        }
        boolean _verify = ehrRedisVerifyCodeService.verification(client_id, username, verifyCode);
        if (_verify){
            envelop.setSuccessFlg(true);
        } else {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg("请输入正确的验证码！");
        }
        return new ResponseEntity<>(envelop, headers, HttpStatus.OK);
    }*/

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
        LOG.info(e.getMessage(), e);
        if (e instanceof UsernameNotFoundException) {
            return handleOAuth2Exception(new Oauth2Envelop("用户未注册!", HttpStatus.UNAUTHORIZED.value()));
        } else if (e instanceof NoSuchClientException) {
            return handleOAuth2Exception(new Oauth2Envelop("应用未注册!", HttpStatus.UNAUTHORIZED.value()));
        } else if (e instanceof InvalidGrantException) {
            if (e.getMessage().equals("verify_code")) {
                return handleOAuth2Exception(new Oauth2Envelop("验证码有误!", HttpStatus.UNAUTHORIZED.value()));
            }
            return handleOAuth2Exception(new Oauth2Envelop("密码有误!", HttpStatus.UNAUTHORIZED.value()));
        } else if (e instanceof InvalidTokenException) {
            return handleOAuth2Exception(new Oauth2Envelop("Token有误!", HttpStatus.UNAUTHORIZED.value()));
        } else if (e instanceof InvalidRequestException) {
            return handleOAuth2Exception(new Oauth2Envelop("参数" + e.getMessage() + "缺失!", HttpStatus.UNAUTHORIZED.value()));
        }
        return handleOAuth2Exception(new Oauth2Envelop(e.getMessage(), -1));
    }

    private ResponseEntity<Oauth2Envelop> handleOAuth2Exception(Oauth2Envelop authenticationFailed) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "no-store");
        headers.set("Pragma", "no-cache");
        headers.set("WWW-Authenticate", String.format("%s %s", OAuth2AccessToken.BEARER_TYPE, authenticationFailed.getMessage()));
        ResponseEntity<Oauth2Envelop> response = new ResponseEntity<>(authenticationFailed, headers, HttpStatus.OK);
        return response;
    }

}
