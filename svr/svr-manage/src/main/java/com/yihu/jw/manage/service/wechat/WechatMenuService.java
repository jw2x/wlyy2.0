package com.yihu.jw.manage.service.wechat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yihu.jw.manage.model.system.ManageUser;
import com.yihu.jw.manage.service.system.UserService;
import com.yihu.jw.manage.util.RestTemplateUtil;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.base.wx.MWxMenu;
import com.yihu.jw.rm.wx.WechatRequestMapping;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/13 0013.
 */
@Service
public class WechatMenuService {

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
        req.put("sorts","");
        String name = map.get("name");
        if(StringUtils.isNotBlank(name)){
            filters.put("name",name);
        }
        String saasId = map.get("saasId");
        if(StringUtils.isNotBlank(saasId)){
            filters.put("saasId",saasId);
        }
        req.put("filters", JSONObject.fromObject(filters).toString());
        String requestUrl = url +WechatRequestMapping.WxMenu.api_getWxMenus + "?size={size}&page={page}&sorts={sorts}&filters={filters}";
        Envelop forObject = template.getForObject(requestUrl,
                Envelop.class,req);
        return forObject;

    }

    public Envelop deleteByCode(String codes,String userCode) {
        //delete 没有返回值....
        //template.delete(url + "/graphicMessage/delete?codes={codes}", codes);
        ManageUser user = userService.findByCode(userCode);
        String userName = user.getName();
        Map<String, String> map = new HashMap<>();
        map.put("userCode", userCode);
        map.put("userName", userName);
        String urlRequest = url + "/menu/"+codes+"/"+userCode+"?userCode={userCode}&userName={userName}";
        RestTemplateUtil restTemplateUtil = new RestTemplateUtil(urlRequest,null);
        Envelop envelop = restTemplateUtil.exchange(urlRequest, HttpMethod.DELETE, Envelop.class,map);
        return envelop;
    }

    public Envelop findByCode(String code) {
        Envelop envelop = template.getForObject(url + "/menu/"+code, Envelop.class);
        return envelop;
    }

    public Envelop saveOrUpdate(MWxMenu menu,String userCode) throws JsonProcessingException {
        ManageUser user = userService.findByCode(userCode);
        String userName = user.getName();

        //设置值
        if(menu.getId()==null){
            menu.setCreateUser(userCode);
            menu.setCreateUserName(userName);
        }
        menu.setUpdateUserName(userName);
        menu.setUpdateUser(userCode);
        menu.setStatus(1);

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        JSONObject jsonObj = JSONObject.fromObject(menu);
        HttpEntity<String> formEntity = new HttpEntity<String>(jsonObj.toString(), headers);
        Envelop envelop =null;
        if(menu.getId()==null){//说明是保存
            ResponseEntity<Envelop> responseEntity = template.postForEntity(url + WechatRequestMapping.WxMenu.api_create, formEntity, Envelop.class);
            envelop = responseEntity.getBody();
            return envelop;
        }
        ResponseEntity<Envelop> resp = template.exchange(url +  WechatRequestMapping.WxMenu.api_update,HttpMethod.PUT,formEntity,Envelop.class);
        envelop = resp.getBody();
        return envelop;
    }

    public Envelop getParentMenu(String wechatCode) {
        Envelop envelop = template.getForObject(url + "/parentMenu/"+wechatCode, Envelop.class);
        return envelop;
    }

    public Envelop getChildMenus(String parentCode) {
        Envelop envelop = template.getForObject(url + "/childMenu/list/"+parentCode, Envelop.class);
        return envelop;
    }
}
