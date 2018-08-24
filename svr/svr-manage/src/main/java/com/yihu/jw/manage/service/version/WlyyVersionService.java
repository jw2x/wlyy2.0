package com.yihu.jw.manage.service.version;

import com.yihu.jw.manage.model.system.ManageUser;
import com.yihu.jw.manage.model.version.WlyyVersion;
import com.yihu.jw.manage.service.system.UserService;
import com.yihu.jw.manage.util.RestTemplateUtil;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.rm.base.BaseVersionRequestMapping;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by chenweida on 2017/6/20.
 */
@Service
public class WlyyVersionService {

    @Value("${spring.gateway}"+ BaseVersionRequestMapping.api_common)
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

        Envelop forObject = template.getForObject(url + BaseVersionRequestMapping.WlyyVersion.api_getList+"?size={size}&page={page}&sorts={sorts}&filters={filters}",
                Envelop.class, req);
        return forObject;
    }

    public Envelop saveOrUpdate(WlyyVersion version,String userCode) {
        ManageUser user = userService.findByCode(userCode);
        String userName = user.getName();

        //设置值
        if(version.getId()==null){
            version.setCreateUser(userCode);
            version.setStatus(1);
            version.setCreateUserName(userName);
            version.setCode(UUID.randomUUID().toString().replaceAll("-",""));
        }
        version.setUpdateUserName(userName);
        version.setUpdateUser(userCode);

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        JSONObject jsonObj = JSONObject.fromObject(version);
        HttpEntity<String> formEntity = new HttpEntity<String>(jsonObj.toString(), headers);
        Envelop envelop =null;
        if(version.getId()==null){//说明是保存
            ResponseEntity<Envelop> responseEntity = template.postForEntity(url + BaseVersionRequestMapping.WlyyVersion.api_create, formEntity, Envelop.class);
            envelop = responseEntity.getBody();
            return envelop;
        }
        ResponseEntity<Envelop> resp = template.exchange(url + BaseVersionRequestMapping.WlyyVersion.api_update, HttpMethod.PUT,formEntity,Envelop.class);
        envelop = resp.getBody();
        return envelop;
    }

    public Envelop deleteByCode(String codes,String userCode) {
        ManageUser user = userService.findByCode(userCode);
        String userName = user.getName();

        Map<String, String> map = new HashMap<>();
        map.put("userCode", userCode);
        map.put("userName", userName);
        String urlRequest = url +"/wlyyVersion/"+codes+"?userCode={userCode}&userName={userName}";
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        RestTemplateUtil restTemplateUtil = new RestTemplateUtil(urlRequest,multiValueMap);
        Envelop envelop = restTemplateUtil.exchange(urlRequest, HttpMethod.DELETE, Envelop.class,map);
        return envelop;
    }

    public Envelop findByCode(String code) {
        Envelop envelop = template.getForObject(url +"/wlyyVersion/"+code, Envelop.class);
        return envelop;
    }
}
