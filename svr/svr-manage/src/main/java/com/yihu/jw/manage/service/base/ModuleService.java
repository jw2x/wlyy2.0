package com.yihu.jw.manage.service.base;

import com.yihu.jw.manage.model.system.ManageUser;
import com.yihu.jw.manage.service.system.UserService;
import com.yihu.jw.manage.util.RestTemplateUtil;
import com.yihu.jw.restmodel.base.base.BaseContants;
import com.yihu.jw.restmodel.base.base.MModule;
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

import java.util.*;

/**
 * Created by chenweida on 2017/6/20.
 */
@Service
public class ModuleService {

    @Value("${spring.gateway}"+ BaseContants.api_common)
    private String url;
    @Autowired
    private RestTemplate template;
    @Autowired
    private UserService userService;

    public Envelop list(Integer size, Integer page,Map<String,String> map) {
        Map<String, Object> req = new HashMap<>();
        Map<String, Object> filters = new HashMap<>();
        req.put("size", size);
        req.put("page", page);
        req.put("sorts", map.get("sorts"));
        String name = map.get("name");
        String saasId = map.get("saasId");
        if (StringUtils.isNotBlank(name)) {
            filters.put("name", name);
        }
        if (StringUtils.isNotBlank(saasId)) {
            filters.put("saasId", saasId);
        }
        String filterStr = JSONObject.fromObject(filters).toString();
        req.put("filters", filterStr);

        Envelop forObject = template.getForObject(url + BaseContants.Module.api_getList+"?size={size}&page={page}&sorts={sorts}&filters={filters}",
                Envelop.class, req);
        return forObject;
    }

    public Envelop getListNoPage(Map<String,String> map){
        Map<String, Object> req = new HashMap<>();
        Map<String, Object> filters = new HashMap<>();
        String saasId = map.get("saasId");
        if (StringUtils.isNotBlank(saasId)) {
            filters.put("saasId", saasId);
        }
        String filterStr = JSONObject.fromObject(filters).toString();
        req.put("filters", filterStr);
        return template.getForObject(url+BaseContants.Module.api_getListNoPage+"?filters={filters}",Envelop.class,req);
    }


    public Envelop deleteByCode(String codes,String userCode) {
        ManageUser user = userService.findByCode(userCode);
        String userName = user.getName();

        Map<String, String> map = new HashMap<>();
        map.put("userCode", userCode);
        map.put("userName", userName);
        String urlRequest = url +"/module/"+codes+"?userCode={userCode}&userName={userName}";
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        RestTemplateUtil restTemplateUtil = new RestTemplateUtil(urlRequest,multiValueMap);
        Envelop envelop = restTemplateUtil.exchange(urlRequest, HttpMethod.DELETE, Envelop.class,map);
        return envelop;
    }

    public Envelop findByCode(String code) {
        Envelop envelop = template.getForObject(url +"/module/"+code, Envelop.class);
        return envelop;
    }

    public Envelop getChildren(String code) {
        return template.getForObject(url +"/module/children/"+code, Envelop.class);
    }

    public Envelop saveOrUpdate(MModule module,String userCode) {
        ManageUser user = userService.findByCode(userCode);
        String userName = user.getName();

        //设置值
        if(module.getId()==null){
            module.setCreateUser(userCode);
            module.setStatus(1);
            module.setCreateUserName(userName);
            module.setCode(UUID.randomUUID().toString().replaceAll("-",""));
        }
        module.setUpdateUserName(userName);
        module.setUpdateUser(userCode);

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        JSONObject jsonObj = JSONObject.fromObject(module);
        HttpEntity<String> formEntity = new HttpEntity<String>(jsonObj.toString(), headers);
        Envelop envelop =null;
        if(module.getId()==null){//说明是保存
            ResponseEntity<Envelop> responseEntity = template.postForEntity(url + BaseContants.Module.api_create, formEntity, Envelop.class);
            envelop = responseEntity.getBody();
            return envelop;
        }
        ResponseEntity<Envelop> resp = template.exchange(url + BaseContants.Module.api_update, HttpMethod.PUT,formEntity,Envelop.class);
        envelop = resp.getBody();
        return envelop;
    }

    public List<String> getExistFun(String code) {
        Envelop envelop = template.getForObject(url + "/moduleFun/existFunc/" + code, Envelop.class);
        Object obj = envelop.getObj();
        if(obj==null){
            return new ArrayList<>();
        }
        return (List)obj;
    }

    public Envelop changeFun(String moduleCode, String funCodes) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        String json = "{\"moduleCode\" :\""+moduleCode+"\",\"funCodes\":\""+funCodes+"\"}";

        HttpEntity<String> formEntity = new HttpEntity<String>(json, headers);

        ResponseEntity<Envelop> resp = template.exchange(url + BaseContants.ModuleFun.api_changeFun, HttpMethod.PUT,formEntity,Envelop.class);
        return resp.getBody();
    }
}
