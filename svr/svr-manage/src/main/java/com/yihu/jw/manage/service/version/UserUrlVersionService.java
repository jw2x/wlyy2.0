package com.yihu.jw.manage.service.version;

import com.yihu.jw.manage.model.system.ManageUser;
import com.yihu.jw.manage.service.system.UserService;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.rm.base.BaseVersionRequestMapping;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/11 0011.
 */
@Service
public class UserUrlVersionService {
    @Value("${spring.gateway}"+ BaseVersionRequestMapping.api_common)
    private String url;
    @Autowired
    private RestTemplate template;
    @Autowired
    private UserService userService;

    /**
     * 更改userCode的后台版本
     * @param serverCode
     * @param userCodes
     * @param userCode
     * @param saasId
     * @return
     */
    public Envelop changeUserVersion(String serverCode, String userCodes,String userCode,String saasId) {
        ManageUser user = userService.findByCode(userCode);
        String userName = user.getName();

        Map<String, Object> req = new HashMap<>();
        req.put("serverCode",serverCode);
        req.put("userCodes",userCodes);
        req.put("userCode",userCode);
        req.put("userName",userName);
        req.put("saasId",saasId);
        return template.getForObject(url+ BaseVersionRequestMapping.UserUrlVersion.api_changeUserVersion+"?serverCode={serverCode}&userCodes={userCodes}&userCode={userCode}&userName={userName}&saasId={saasId}",Envelop.class,req);

    }

    public Envelop getListNoPage(Map<String, String> map) {
        Map<String, Object> req = new HashMap<>();
        Map<String, Object> filters = new HashMap<>();
        String saasId = map.get("saasId");
        if (StringUtils.isNotBlank(saasId)) {
            filters.put("saasId", saasId);
        }
        String bsvCode = map.get("bsvCode");
        if (StringUtils.isNotBlank(bsvCode)) {
            filters.put("bsvCode", bsvCode);
        }
        req.put("filters", filters);
        return template.getForObject(url+ BaseVersionRequestMapping.UserUrlVersion.api_getListNoPage+"?filters={filters}",Envelop.class,req);
    }
}
