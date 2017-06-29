package com.yihu.jw.manage.service.wechat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yihu.jw.manage.model.system.ManageUser;
import com.yihu.jw.manage.model.wechat.GraphicMessage;
import com.yihu.jw.manage.service.system.UserService;
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
public class GraphicMessageService {

    @Value("${spring.gateway}")
    private String url;

    @Autowired
    private RestTemplate template;

    @Autowired
    private UserService userService;

    public Envelop list(String title, String sorts ,Integer size, Integer page) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> filters = new HashMap<>();
        map.put("size",size);
        map.put("page",page);
        map.put("sorts",sorts);
        map.put("filters","");
        if(StringUtils.isNotBlank(title)){
            filters.put("title",title);
            map.put("filters",filters);
        }

        Envelop forObject = template.getForObject(url + "/wechat/graphicMessage/list?size={size}&page={page}&sorts={sorts}&filters={filters}",
                Envelop.class,map);
        return forObject;

    }

    public Envelop deleteByCode(String codes,String userCode) {
        //delete 没有返回值....
        //template.delete(url + "/graphicMessage/delete?codes={codes}", codes);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        ManageUser user = userService.findByCode(userCode);
        String userName = user.getName();
        Map<String, String> par = new HashMap<>();
        par.put("userCode", userCode);
        par.put("userName", userName);
        String urlRequest = url + "/wechat/graphicMessage/"+codes+"?userCode={userCode}&userName={userName}";
        RestTemplateUtil restTemplateUtil = new RestTemplateUtil(urlRequest,map);
        Envelop envelop = restTemplateUtil.exchange(urlRequest, HttpMethod.DELETE, Envelop.class,par);
        return envelop;
    }

    public Envelop findByCode(String code) {
        Envelop envelop = template.getForObject(url + "/wechat/graphicMessage/"+code, Envelop.class);
        return envelop;
    }

    public Envelop saveOrUpdate(GraphicMessage graphicMessage, String userCode) throws JsonProcessingException {
        ManageUser user = userService.findByCode(userCode);
        String userName = user.getName();

        //设置值
        if(graphicMessage.getId()==null){
            graphicMessage.setCreateUser(userCode);
            graphicMessage.setCreateUserName(userName);
        }
        graphicMessage.setUpdateUserName(userName);
        graphicMessage.setUpdateUser(userCode);

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        JSONObject jsonObj = JSONObject.fromObject(graphicMessage);
        HttpEntity<String> formEntity = new HttpEntity<String>(jsonObj.toString(), headers);
        Envelop envelop =null;
        if(graphicMessage.getId()==null){//说明是保存
            ResponseEntity<Envelop> responseEntity = template.postForEntity(url + "/wechat/graphicMessage", formEntity, Envelop.class);
            envelop = responseEntity.getBody();
            return envelop;
        }
        ResponseEntity<Envelop> resp = template.exchange(url + "/wechat/graphicMessage",HttpMethod.PUT,formEntity,Envelop.class);
        envelop = resp.getBody();

        return envelop;
    }
}
