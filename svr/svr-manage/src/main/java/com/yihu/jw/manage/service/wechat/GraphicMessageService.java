package com.yihu.jw.manage.service.wechat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yihu.jw.base.wx.WxGraphicMessageDO;
import com.yihu.jw.manage.model.system.ManageUser;
import com.yihu.jw.manage.service.system.UserService;
import com.yihu.jw.manage.util.RestTemplateUtil;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.rm.base.WechatRequestMapping;
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

    @Value("${spring.gateway}"+ WechatRequestMapping.api_common)
    private String url;

    @Autowired
    private RestTemplate template;

    @Autowired
    private UserService userService;

    public Envelop list(Integer size, Integer page,Map<String,String> map) {
        Map<String, Object> req = new HashMap<>();
        Map<String, Object> filters = new HashMap<>();
        req.put("size",size);
        req.put("page",page);
        req.put("sorts",map.get("sorts"));

        String title = map.get("title");
        if(StringUtils.isNotBlank(title)){
            filters.put("title",title);
        }
        String saasId = map.get("saasId");
        if(StringUtils.isNotBlank(saasId)){
            filters.put("saasId",saasId);
        }
        String filterStr = JSONObject.fromObject(filters).toString();
        req.put("filters",filterStr);
        Envelop forObject = template.getForObject(url +WechatRequestMapping.WxGraphicMessage.api_getWxGraphicMessages+ "?size={size}&page={page}&sorts={sorts}&filters={filters}", Envelop.class,req);
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
        String urlRequest = url + "/graphicMessage/"+codes+"?userCode={userCode}&userName={userName}";
        RestTemplateUtil restTemplateUtil = new RestTemplateUtil(urlRequest,map);
        Envelop envelop = restTemplateUtil.exchange(urlRequest, HttpMethod.DELETE, Envelop.class,par);
        return envelop;
    }

    public Envelop findByCode(String code) {
        Envelop envelop = template.getForObject(url + "/graphicMessage/"+code, Envelop.class);
        return envelop;
    }

    public Envelop saveOrUpdate(WxGraphicMessageDO graphicMessage, String userCode) throws JsonProcessingException {
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
            ResponseEntity<Envelop> responseEntity = template.postForEntity(url +WechatRequestMapping.WxGraphicMessage.api_create, formEntity, Envelop.class);
            envelop = responseEntity.getBody();
            return envelop;
        }
        ResponseEntity<Envelop> resp = template.exchange(url + WechatRequestMapping.WxGraphicMessage.api_update,HttpMethod.PUT,formEntity,Envelop.class);
        envelop = resp.getBody();

        return envelop;
    }
}
