package com.yihu.ehr.iot.service.company;

import com.yihu.ehr.iot.service.common.BaseService;
import com.yihu.ehr.iot.util.http.HttpHelper;
import com.yihu.ehr.iot.util.http.HttpResponse;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.iot.company.IotCompanyVO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yeshijie on 2018/1/22.
 */
@Service
public class CompanyService extends BaseService {

    /**
     * 分页查找企业
     * @param name
     * @param status
     * @param type
     * @param page
     * @param size
     * @return
     * @throws IOException
     */
    public Envelop<IotCompanyVO> findCompanyPage(String name,String status,String type,Integer page,Integer size) throws IOException{
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("status", status);
        params.put("type", type);
        params.put("page", page);
        params.put("size", size);
        HttpResponse response = HttpHelper.get(iotUrl + "/oauth/validToken", params);
        Envelop<IotCompanyVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);

        return envelop;
    }
}
