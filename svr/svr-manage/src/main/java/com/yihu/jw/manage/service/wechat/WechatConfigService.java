package com.yihu.jw.manage.service.wechat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yihu.jw.manage.model.wechat.WechatConfig;
import com.yihu.jw.manage.util.RestTemplateUtil;
import com.yihu.jw.restmodel.common.Envelop;
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

    @Value("${spring.gateway}")
    private String url;

    @Autowired
    private RestTemplate template;

   public Envelop list(String name, String sorts ,Integer size, Integer page) {
       Map<String, Object> map = new HashMap<>();
       Map<String, Object> filters = new HashMap<>();
       map.put("size",size);
       map.put("page",page);
       map.put("sorts",sorts);
       map.put("filters","");
       if(StringUtils.isNotBlank(name)){
           name = name.replaceAll(" ","");
           filters.put("name",name);
           map.put("filters",filters);
       }

       Envelop forObject = template.getForObject(url + "/wechatConfig/getWechats?size={size}&page={page}&sorts={sorts}&filters={filters}",
               Envelop.class,map);
       return forObject;

    }

    public Envelop deleteByCode(String codes) {
        //delete 没有返回值....
        //template.delete(url + "/wechatConfig/delete?codes={codes}", codes);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("codes", codes);
        String urlRequest = url + "/wechatConfig/delete?codes="+codes;
        RestTemplateUtil restTemplateUtil = new RestTemplateUtil(urlRequest,map);
        Envelop envelop = restTemplateUtil.exchange(urlRequest, HttpMethod.DELETE, Envelop.class);
        return envelop;
    }

    public Envelop findByCode(String code) {
        Envelop envelop = template.getForObject(url + "/wechatConfig/getByCode?code={code}", Envelop.class,code);
        return envelop;
    }

    public Envelop save(WechatConfig wechatConfig) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        JSONObject jsonObj = JSONObject.fromObject(wechatConfig);
        HttpEntity<String> formEntity = new HttpEntity<String>(jsonObj.toString(), headers);
        ResponseEntity<Envelop> responseEntity = template.postForEntity(url + "/wechatConfig/create", formEntity, Envelop.class);
        Envelop envelop = responseEntity.getBody();
        return envelop;
    }
    public Envelop update(WechatConfig wechatConfig) {
       /* ResponseEntity<Envelop> responseEntity = template.PUT(url + "/wechatConfig/create?jsonData={jsonData}", wechatConfig, Envelop.class);
        Envelop envelop = responseEntity.getBody();
        return envelop;*/
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        String urlRequest = url + "/wechatConfig/update?jsonData={jsonData}";
        RestTemplateUtil restTemplateUtil = new RestTemplateUtil(urlRequest,map);
        Envelop envelop = restTemplateUtil.exchange(urlRequest, HttpMethod.PUT, Envelop.class);
        return envelop;
    }
}
