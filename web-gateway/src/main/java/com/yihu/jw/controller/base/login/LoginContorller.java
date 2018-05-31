//package com.yihu.jw.controller.base.login;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.yihu.base.security.properties.SecurityProperties;
//import com.yihu.base.security.sms.mobile.MobileCheck;
//import com.yihu.base.security.sms.process.SmsValidateCodeProcessor;
//import com.yihu.jw.base.user.BaseEmployDO;
//import com.yihu.jw.common.base.base.BaseContants;
//import com.yihu.jw.fegin.base.base.FunctionFeign;
//import com.yihu.jw.fegin.base.login.LoginFeign;
//import com.yihu.jw.fegin.base.user.EmployFeign;
//import com.yihu.jw.restmodel.common.Envelop;
//import com.yihu.jw.restmodel.common.base.BaseEnvelop;
//import com.yihu.jw.restmodel.common.base.BaseEnvelopStatus;
//import com.yihu.jw.rm.base.BaseLoginRequestMapping;
//import com.yihu.jw.rm.base.BaseUserRequestMapping;
//import com.yihu.jw.util.common.ConvertToSpellUtils;
//import com.yihu.jw.util.date.DateUtil;
//import com.yihu.jw.util.security.MD5;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import org.bouncycastle.util.encoders.Base64;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.context.request.ServletWebRequest;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.text.SimpleDateFormat;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
///**
// * Created by 刘文彬 on 2018/4/20.
// */
//@RestController
//@RequestMapping(BaseLoginRequestMapping.api_gateway_common)
//@Api(value = "登录模块", description = "登录模块")
//public class LoginContorller {
//
//    @Autowired
//    private LoginFeign loginFeign;
//    @Autowired
//    private MobileCheck mobileCheck;
//    @Autowired
//    private SmsValidateCodeProcessor smsValidateCodeProcessor;
//    @Autowired
//    private EmployFeign employFeign;
//    @Value("${server.web-gateway-port}")
//    private String port;
//    @Autowired
//    private RestTemplate restTemplate ;
//    @Value("${spring.application.name}")
//    private String appName;
//
//    @PostMapping(value = BaseLoginRequestMapping.BaseLoginAccount.api_checkoutInfo, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @ApiOperation(value = "注册校验信息", notes = "注册校验姓名、身份证、医保卡号信息")
//    public BaseEnvelop checkoutInfo(@ApiParam(name = "ssc", value = "医保卡号", required = true) @RequestParam(value = "ssc", required = true) String ssc,
//                                @ApiParam(name = "idcard", value = "身份证", required = true) @RequestParam(value = "idcard", required = true) String idcard){
//
////        return loginFeign.checkoutInfo(ssc,idcard);
//        return null;
//    }
//
//    @PostMapping(value = BaseLoginRequestMapping.BaseLoginAccount.api_accountSub, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @ApiOperation(value = "注册账号", notes = "注册校验姓名、身份证、医保卡号信息")
//    public Envelop register(
//            @ApiParam(name = "mobilePhone", value = "电话号码（账号）", required = true) @RequestParam(value = "mobilePhone", required = true) String mobilePhone,
//            @ApiParam(name = "saasId", value = "saasID", required = true) @RequestParam(value = "saasId", required = true) String saasId,
//            @ApiParam(name = "type", value = "1微信端注册，2微信端找回密码，3医生端找回密码，4患者登录，5医生登录", required = true) @RequestParam(value = "type", required = true) int type,
//            @ApiParam(name = "captcha", value = "短信验证码", required = true) @RequestParam(value = "captcha", required = true) String captcha,
//            @ApiParam(name = "name", value = "姓名", required = true) @RequestParam(value = "name", required = true) String name,
//            @ApiParam(name = "password", value = "账户密码", required = true) @RequestParam(value = "password", required = true) String password,
//            @ApiParam(name = "idcard", value = "身份证", required = true) @RequestParam(value = "idcard", required = true) String idcard,
//            @ApiParam(name = "ssc", value = "医保卡号", required = true) @RequestParam(value = "ssc", required = true) String ssc) {
//
//        //判断账号是否重复
//        Envelop baseEmployDO = employFeign.getEmployeeByPhoneAndSaasId(mobilePhone,saasId);
//        if(baseEmployDO.getStatus()!=10100&&baseEmployDO.getObj()!=null){
//            return Envelop.getError(BaseEnvelopStatus.status_10106.getName(),BaseEnvelopStatus.status_10106.getCode());
//        }
//
//        //保存账户基础信息
//        BaseEmployDO employeeDO = new BaseEmployDO();
//        employeeDO.setId(UUID.randomUUID().toString().replaceAll("-", ""));
//        employeeDO.setSaasId(saasId);
//        employeeDO.setName(name);
//        employeeDO.setPyCode(ConvertToSpellUtils.changeToInitialPinYin(name));
//        employeeDO.setIdcard(idcard);
//        employeeDO.setSsc(ssc);
//        employeeDO.setPhone(mobilePhone);
//        String salt= UUID.randomUUID().toString().replace("-","");
//        employeeDO.setSalt(salt);
//        employeeDO.setPassword(MD5.GetMD5Code(password + salt));
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setDateFormat(new SimpleDateFormat(DateUtil.yyyy_MM_dd_HH_mm_ss));
//        try {
//            String entity = objectMapper.writeValueAsString(employeeDO);
//            employFeign.create(entity);
//        } catch (JsonProcessingException e) {
//            return Envelop.getError(BaseEnvelopStatus.system_error.getName(),BaseEnvelopStatus.system_error.getCode());
//        }
//
//        return login(employeeDO.getPhone(),employeeDO.getPassword(),saasId,"");
//    }
//
//    @PostMapping(BaseLoginRequestMapping.BaseLoginAccount.mobileSendSms)
//    @ApiOperation(value = "发送短信登录的验证码", notes = "不走校验框架")
//    public BaseEnvelop createCode(@ApiParam(name = "mobile", value = "手机号", required = true) @RequestParam(value = "mobile", required = true) String mobile,
//                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
//        //验证手机号是否正确
//        String[] mobileSaas = mobile.split(",");
//        if (!mobileCheck.checkMobile(mobileSaas[0])) {
//            return BaseEnvelop.getError("手机格式错误！");
//        } else {
//            //发送短信验证码并且保存到redis中
//            smsValidateCodeProcessor.create(new ServletWebRequest(request, response));
//            return BaseEnvelop.getSuccess("发送成功！");
//        }
//    }
//
//    @PostMapping(BaseLoginRequestMapping.BaseLoginAccount.api_login)
//    public Envelop login(@ApiParam(name = "mobilePhone", value = "电话号码（账号）", required = false) @RequestParam(value = "mobilePhone", required = false) String mobilePhone,
//                       @ApiParam(name = "password", value = "password", required = false) @RequestParam(value = "password", required = false) String password,
//                       @ApiParam(name = "saasId", value = "saasID", required = true) @RequestParam(value = "saasId", required = true) String saasId,
//                       @ApiParam(name = "captcha", value = "短信验证码", required = false) @RequestParam(value = "captcha", required = false) String captcha){
//        Envelop baseEmployDO = employFeign.getEmployeeByPhoneAndSaasId(mobilePhone,saasId);
//        if(baseEmployDO==null||((Map)baseEmployDO.getObj()).isEmpty()){
//            return Envelop.getError(BaseEnvelopStatus.status_10100.getName(),BaseEnvelopStatus.status_10100.getCode());
//        }
//        Map employMap = (Map)baseEmployDO.getObj();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Accept", "*/*");
//        headers.add("Cache-Control", "no-cache");
//        //client_id:client_securt
//        byte[] a = Base64.encode((saasId+":").getBytes());
//        String client_id = new String(a);
//        headers.add("Authorization","Basic "+client_id);//MTox
//        String token = "";
//        //传参数JSON格式
//        //  封装参数，千万不要替换为Map与HashMap，否则参数无法传递
//        MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
//        //判断是短信登录还是账号密码登录
//        if(StringUtils.isEmpty(captcha)){
//
//            //  也支持中文
//            params.add("username", mobilePhone+","+saasId);
//            params.add("password", MD5.GetMD5Code(password+employMap.get("salt")));
//            //设置http请求实体
//            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
//            token = restTemplate.postForObject("http://"+appName.toUpperCase()+"/authentication/form", requestEntity, String.class);
//        }else{
//            params.add("mobile", mobilePhone+","+saasId);
//            params.add("sms", captcha);
//            //设置http请求实体
//            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
//            token = restTemplate.postForObject("http://"+appName.toUpperCase()+"/authentication/mobile", requestEntity, String.class);
//        }
//
//        if(!StringUtils.isEmpty(token)){
//            Map<String,Object> map = new HashMap<>();
//            map.put("token",token);
//            map.put("employ",employMap);
//            return Envelop.getSuccess("登录成功！",map);
//        }else{
//            return Envelop.getError("登录失败！");
//        }
//
////        return loginFeign.login(mobilePhone,password,saasId,captcha);
//    }
//}
