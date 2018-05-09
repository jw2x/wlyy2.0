package com.yihu.jw.business.login.service;

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.jw.base.login.BaseLoginAccountDO;
import com.yihu.jw.base.user.BaseEmployDO;
import com.yihu.jw.business.user.dao.EmployDao;
import com.yihu.jw.business.user.service.EmployService;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.base.BaseEnvelop;
import com.yihu.jw.restmodel.common.base.BaseEnvelopStatus;
import com.yihu.jw.rm.base.BaseLoginRequestMapping;
import com.yihu.jw.util.common.ConvertToSpellUtils;
import com.yihu.jw.util.security.MD5;
import io.swagger.annotations.ApiParam;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.beans.Transient;
import java.util.UUID;

/**
 * Created by 刘文彬 on 2018/4/26.
 */
@Service
public class LoginService  extends BaseJpaService<BaseEmployDO,EmployDao> {

    @Autowired
    private EmployDao employDao;
    @Autowired
    private EmployService employService;

    @Value("${server.web-gateway-port}")
    private String port;
    public BaseEnvelop checkoutInfo(){

        //校验姓名、身份证以及医保卡号信息是否正确

        return null;
    }

    @Transactional
    public Envelop register(String mobilePhone,String password,String saasId,String name,String idcard,String ssc) throws Exception {

        //判断账号是否重复
        BaseEmployDO baseEmployDO = employDao.findByPhoneAndSaasId(mobilePhone,saasId);
        if(baseEmployDO!=null){
            return Envelop.getError("该手机号已注册！");
        }

        //保存账户基础信息
        BaseEmployDO employeeDO = new BaseEmployDO();
        employeeDO.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        employeeDO.setSaasId(saasId);
        employeeDO.setName(name);
        employeeDO.setPyCode(ConvertToSpellUtils.changeToInitialPinYin(name));
        employeeDO.setIdcard(idcard);
        employeeDO.setSsc(ssc);
        employeeDO.setPhone(mobilePhone);
        String salt= UUID.randomUUID().toString().replace("-","");
        employeeDO.setSalt(salt);
        employeeDO.setPassword(MD5.GetMD5Code(password + salt));
        employService.createBaseEmployDO(employeeDO);

        return login(employeeDO.getPhone(),employeeDO.getPassword(),saasId,"");
    }


    public Envelop login(String mobilePhone,String password,String saasId,String captcha) throws Exception{
        BaseEmployDO baseEmployDO = employService.findByPhoneAndSaasId(mobilePhone,saasId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "*/*");
        headers.add("Cache-Control", "no-cache");
        //client_id:client_securt
        byte[] a = Base64.encode((saasId+":").getBytes());
        String client_id = new String(a);
        headers.add("Authorization","Basic "+client_id);//MTox
        String token = "";
        //传参数JSON格式
        //  封装参数，千万不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
        //判断是短信登录还是账号密码登录
        if(StringUtils.isEmpty(captcha)){

            //  也支持中文
            params.add("username", mobilePhone+","+saasId);
            params.add("password", MD5.GetMD5Code(password+baseEmployDO.getSalt()));
            //设置http请求实体
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
            RestTemplate restTemplate = new RestTemplate();
            token = restTemplate.postForObject("http://localhost:"+port+"/authentication/form", requestEntity, String.class);
        }else{
            params.add("mobile", mobilePhone+","+saasId);
            params.add("sms", captcha);
            //设置http请求实体
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
            RestTemplate restTemplate = new RestTemplate();
            token = restTemplate.postForObject("http://localhost:"+port+"/authentication/mobile", requestEntity, String.class);
        }

        if(!StringUtils.isEmpty(token)){
            return Envelop.getSuccess("登录成功！",token);
        }else{
            return Envelop.getError("登录失败！");
        }
    }

//    public Envelop logout(){
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Accept", "*/*");
//        headers.add("Cache-Control", "no-cache");
//        RestTemplate restTemplate = new RestTemplate();
//        MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
//        //设置http请求实体
//        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
//        restTemplate.postForObject("http://localhost:"+port+"/logout", requestEntity, String.class);
//    }
}
