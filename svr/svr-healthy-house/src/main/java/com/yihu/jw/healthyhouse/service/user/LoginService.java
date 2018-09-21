package com.yihu.jw.healthyhouse.service.user;

import com.yihu.jw.exception.business.ManageException;
import com.yihu.jw.healthyhouse.cache.WlyyRedisVerifyCodeService;
import com.yihu.jw.healthyhouse.constant.LoginInfo;
import com.yihu.jw.healthyhouse.model.user.User;
import com.yihu.jw.restmodel.wlyy.HouseUserContant;
import com.yihu.jw.util.security.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HZY
 * @created 2018/9/18 20:02
 */
@Service
public class LoginService {

    @Autowired
    private UserService userService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WlyyRedisVerifyCodeService wlyyRedisVerifyCodeService;
    @Value("${jw.smsUrl}")
    private String smsUrl;

    /**
     * 手机验证码方式登录并自动注册
     *
     * @param loginCode
     * @return
     * @throws Exception
     */
    @Transactional(noRollbackForClassName = "ManageException")
    public User phoneLogin(HttpServletRequest request, String loginCode) throws ManageException {
        //判断用户信息是否存在
        User user = userService.findByCode(loginCode);
        if (user == null) {
            //未注册用户进行账户注册
            user = new User();
            user.setLoginCode(loginCode);
            user.setName(loginCode);
            user.setTelephone(loginCode);
            user.setPassword(LoginInfo.DEFAULT_PASSWORD);
        } else {
            //已注册用户更改用户状态
            user.setActivated(HouseUserContant.activated_active);
        }
        request.getSession().setAttribute(LoginInfo.IS_LOGIN, true);
        request.getSession().setAttribute(LoginInfo.TOKEN, ""); //TODO token是否添加
        request.getSession().setAttribute(LoginInfo.LOGIN_NAME, user.getName());
        request.getSession().setAttribute(LoginInfo.LOGIN_CODE, user.getLoginCode());
        request.getSession().setAttribute(LoginInfo.USER_ID, user.getId());
        user.setLastLoginTime(new Date());
        userService.saveOrUpdate(user, LoginInfo.SAVE_TYPE_PHONE);
        return user;
    }


    /**
     * i健康账户登录&注册
     *
     * @param loginCode
     * @param password
     * @return
     * @throws Exception
     */
    @Transactional(noRollbackForClassName = "ManageException")
    public User iJklogin(HttpServletRequest request, String clientId, String loginCode, String password) throws ManageException {
        //判断登陆信息是否正确
        User user = userService.findByCode(loginCode);
        if (user == null) {
            //i健康登录认证
            Map<String, Object> data = oauthIjkLogin(clientId, loginCode, password);
            if (data != null) {
                user = new User();
                user.setPassword(password);
                user.setLoginCode((String) data.get("user"));
                user.setName((String) data.get("name"));
                user.setGender((String) data.get("gender"));
                user.setIdCardNo((String) data.get("idcard"));
                user.setTelephone((String) data.get("mobile"));

            } else {
                String message = "账号不存在";
                throw new ManageException(message);
            }
        }
        if (!user.getPassword().equals(MD5.GetMD5Code(password + user.getSalt()))) {
            String message = "密码错误";
            throw new ManageException(message);
        }
        request.getSession().setAttribute(LoginInfo.IS_LOGIN, true);
        request.getSession().setAttribute(LoginInfo.TOKEN, ""); //TODO token是否添加
        request.getSession().setAttribute(LoginInfo.LOGIN_NAME, user.getName());
        request.getSession().setAttribute(LoginInfo.USER_ID, user.getId());
        user.setActivated(HouseUserContant.activated_active);
        user.setLastLoginTime(new Date());
        userService.saveOrUpdate(user, LoginInfo.SAVE_TYPE_IJK);
        return user;
    }


    /**
     * i健康用户信息认证
     *
     * @param username
     * @param password
     * @return
     * @throws ManageException
     */
    public Map<String, Object> oauthIjkLogin(String clientId, String username, String password) throws ManageException {
        HashMap<String, Object> userDetail = null;
        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", clientId);
        params.add("username", username);
        params.add("password", password);
        params.add("login_type", "1");
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, reqHeaders);
        HashMap<String, Object> result = restTemplate.postForObject("http://svr-authentication:10260/oauth/login", httpEntity, HashMap.class);
        if (200 == (Integer) result.get("status")) {
            userDetail = (HashMap) result.get("obj");
            return userDetail;
        } else {
            throw new ManageException("i健康用户认证失败");
        }
    }


    /**
     * 登出
     *
     * @param loginCode
     * @param password
     * @return
     * @throws Exception
     */
    @Transactional(noRollbackForClassName = "ManageException")
    public User logout(HttpServletRequest request, String loginCode, String password) throws ManageException {
        //判断登陆信息是否正确
        User user = userService.findByCode(loginCode);
        if (user == null) {
            //保存登陆信息
            String message = "账号不存在";
            throw new ManageException(message);
        }
        if (!user.getPassword().equals(MD5.GetMD5Code(password + user.getSalt()))) {
            //保存登陆信息
            String message = "密码错误";
            throw new ManageException(message);
        }
        request.getSession().removeAttribute(LoginInfo.IS_LOGIN);
        request.getSession().removeAttribute(LoginInfo.TOKEN);
        request.getSession().removeAttribute(LoginInfo.LOGIN_NAME);
        request.getSession().removeAttribute(LoginInfo.USER_ID);
        user.setActivated(HouseUserContant.activated_offline);
        userService.saveOrUpdate(user, "systemLogin");
        return user;
    }


    /**
     * 发送短信
     *
     * @param clientId 应用id
     * @param type     短信类型
     * @param phone    接收手机号码
     * @return
     */
    public ResponseEntity<HashMap> sendSms(String clientId, String type, String phone) throws ParseException, ManageException {
        //发送短信获取验证码
        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("clientId", clientId);
        params.add("type", type);
        params.add("to", phone);
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, reqHeaders);
        HashMap<String, Object> result = restTemplate.postForObject(smsUrl, httpEntity, HashMap.class);
        if (200 == (Integer) result.get("status")) {
            Map<String, Object> sms = (Map) result.get("obj");
            String captcha = (String) sms.get("captcha");
            Date deadline = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String) sms.get("deadline"));
            Long expire = (deadline.getTime() - System.currentTimeMillis()) / 1000;
            wlyyRedisVerifyCodeService.store(clientId, phone, captcha, expire.intValue());
            HttpHeaders headers = new HttpHeaders();
            headers.set("Cache-Control", "no-store");
            headers.set("Pragma", "no-cache");
            return new ResponseEntity<>(result, headers, HttpStatus.OK);
        } else {
            throw new ManageException("验证码获取失败!");
        }
    }


    /**
     * 管理员手机登录
     *
     * @param request
     * @param loginCode 登录名
     * @return
     * @throws ManageException
     */
    @Transactional(noRollbackForClassName = "ManageException")
    public User managerPhoneLogin(HttpServletRequest request, String loginCode) throws ManageException {
        //判断管理员用户信息是否存在
        User user = userService.findByLoginCodeAAndUserType(loginCode, LoginInfo.USER_TYPE_AdminManager);
        if (user == null) {
            throw new ManageException("该管理员账号不存在!");
        } else {
            //已注册用户更改用户状态
            user.setActivated(HouseUserContant.activated_active);
            request.getSession().setAttribute(LoginInfo.IS_LOGIN, true);
            request.getSession().setAttribute(LoginInfo.TOKEN, ""); //TODO token是否添加
            request.getSession().setAttribute(LoginInfo.LOGIN_NAME, user.getName());
            request.getSession().setAttribute(LoginInfo.LOGIN_CODE, user.getLoginCode());
            request.getSession().setAttribute(LoginInfo.USER_ID, user.getId());
            user.setLastLoginTime(new Date());
            userService.saveOrUpdate(user, LoginInfo.SAVE_TYPE_PHONE);
        }
        return user;
    }

    /**
     * 管理员-账号密码登录
     *
     * @param request
     * @param clientId  应用id
     * @param loginCode 登录账号
     * @param password  密码
     * @return
     * @throws ManageException
     */
    @Transactional(noRollbackForClassName = "ManageException")
    public User managerLogin(HttpServletRequest request, String clientId, String loginCode, String password) throws ManageException {
        //判断登陆信息是否正确
        User user = userService.findByCode(loginCode);
        if (user == null) {
            String message = "该管理员账号不存在！";
            throw new ManageException(message);
        } else {
            if (!user.getPassword().equals(MD5.GetMD5Code(password + user.getSalt()))) {
                String message = "密码错误";
                throw new ManageException(message);
            }
            request.getSession().setAttribute(LoginInfo.IS_LOGIN, true);
            request.getSession().setAttribute(LoginInfo.TOKEN, ""); //TODO token是否添加
            request.getSession().setAttribute(LoginInfo.LOGIN_NAME, user.getName());
            request.getSession().setAttribute(LoginInfo.USER_ID, user.getId());
            user.setActivated(HouseUserContant.activated_active);
            user.setLastLoginTime(new Date());
            userService.saveOrUpdate(user, LoginInfo.SAVE_TYPE_IJK);
            return user;
        }
    }


}
