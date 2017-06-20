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
           filters.put("name",name);
           map.put("filters",filters);
       }

       Envelop forObject = template.getForObject(url + "/wechat/wechatConfig/list?size={size}&page={page}&sorts={sorts}&filters={filters}",
               Envelop.class,map);
       return forObject;

    }

    public Envelop deleteByCode(String codes) {
        //delete 没有返回值....
        //template.delete(url + "/wechatConfig/delete?codes={codes}", codes);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("codes", codes);
        String urlRequest = url + "/wechat/wechatConfig/"+codes;
        RestTemplateUtil restTemplateUtil = new RestTemplateUtil(urlRequest,map);
        Envelop envelop = restTemplateUtil.exchange(urlRequest, HttpMethod.DELETE, Envelop.class);
        return envelop;
    }

    public Envelop findByCode(String code) {
        Envelop envelop = template.getForObject(url + "/wechat/wechatConfig/"+code, Envelop.class);
        return envelop;
    }

    public Envelop saveOrUpdate(WechatConfig wechatConfig) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        JSONObject jsonObj = JSONObject.fromObject(wechatConfig);
        HttpEntity<String> formEntity = new HttpEntity<String>(jsonObj.toString(), headers);
        Envelop envelop =null;
        if(wechatConfig.getId()==null){//说明是保存
            ResponseEntity<Envelop> responseEntity = template.postForEntity(url + "/wechat/wechatConfig", formEntity, Envelop.class);
            envelop = responseEntity.getBody();
            return envelop;
        }
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("jsonData",jsonObj.toString());
        String urlRequest = url + "/wechat/wechatConfig";
        RestTemplateUtil restTemplateUtil = new RestTemplateUtil(urlRequest,map);
        envelop = restTemplateUtil.exchange(urlRequest, HttpMethod.PUT, Envelop.class);

        return envelop;
    }
}
