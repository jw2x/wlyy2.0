package com.yihu.jw.manage.service.wechat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yihu.jw.manage.model.system.ManageUser;
import com.yihu.jw.manage.model.wechat.WechatConfig;
import com.yihu.jw.manage.service.system.UserService;
import com.yihu.jw.manage.util.RestTemplateUtil;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.wx.WechatContants;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/13 0013.
 */
@Service
public class WechatConfigService {

    @Value("${spring.gateway}"+"/"+ WechatContants.api_common+"/")
    private String url;

    @Autowired
    private RestTemplate template;

    @Autowired
    private UserService userService;

   public Envelop list(String name, String sorts ,Integer size, Integer page) {
       Map<String, Object> map = new HashMap<>();
       Map<String, Object> filters = new HashMap<>();
       map.put("size",size);
       map.put("page",page);
       map.put("sorts",sorts);
       map.put("filters","");
       if(StringUtils.isNotBlank(name)){
           filters.put("name",name);
           map.put("filters",filters);
       }
       return template.getForObject(url +WechatContants.WxConfig.api_getWechats+"?size={size}&page={page}&sorts={sorts}&filters={filters}", Envelop.class,map);

    }

    public Envelop deleteByCode(String codes,String userCode) {
        //delete 没有返回值....
        //template.delete(url + "/wechatConfig/delete?codes={codes}", codes);
        ManageUser user = userService.findByCode(userCode);
        String userName = user.getName();
        Map<String, String> map = new HashMap<>();
        map.put("userCode", userCode);
        map.put("userName", userName);
        String urlRequest = url +"wechatConfig/"+codes+"?userCode={userCode}&userName={userName}";
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        RestTemplateUtil restTemplateUtil = new RestTemplateUtil(urlRequest,multiValueMap);
        Envelop envelop = restTemplateUtil.exchange(urlRequest, HttpMethod.DELETE, Envelop.class,map);
        return envelop;
    }

    public Envelop findByCode(String code) {
        Envelop envelop = template.getForObject(url +"wechatConfig/"+code, Envelop.class);
        return envelop;
    }

    public Envelop saveOrUpdate(WechatConfig wechatConfig,String userCode) throws JsonProcessingException {
        ManageUser user = userService.findByCode(userCode);
        String userName = user.getName();

        //设置值
        if(wechatConfig.getId()==null){
            wechatConfig.setCreateUser(userCode);
            wechatConfig.setCreateUserName(userName);
        }
        wechatConfig.setUpdateUserName(userName);
        wechatConfig.setUpdateUser(userCode);

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        JSONObject jsonObj = JSONObject.fromObject(wechatConfig);
        HttpEntity<String> formEntity = new HttpEntity<String>(jsonObj.toString(), headers);
        Envelop envelop =null;
        if(wechatConfig.getId()==null){//说明是保存
            ResponseEntity<Envelop> responseEntity = template.postForEntity(url + WechatContants.WxConfig.api_create, formEntity, Envelop.class);
            envelop = responseEntity.getBody();
            return envelop;
        }
        ResponseEntity<Envelop> resp = template.exchange(url + WechatContants.WxConfig.api_update,HttpMethod.PUT,formEntity,Envelop.class);
        envelop = resp.getBody();
        return envelop;
    }

    public Envelop getListNoPage(String fields, Map<String, Object> filter, String sorts) {
        Map<String, Object> map = new HashMap<>();
        map.put("fields",fields);
        map.put("sorts",sorts);
        map.put("filter",filter);
        Envelop forObject = template.getForObject(url + WechatContants.WxConfig.api_getWechatNoPage+"?fields={fields}&filter={filter}&sorts={sorts}", Envelop.class,map);
        return forObject;
    }
}
