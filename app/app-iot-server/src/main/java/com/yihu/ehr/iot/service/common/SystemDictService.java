package com.yihu.ehr.iot.service.common;

import com.yihu.ehr.iot.constant.ServiceApi;
import com.yihu.ehr.iot.util.http.HttpHelper;
import com.yihu.ehr.iot.util.http.HttpResponse;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.iot.dict.IotSystemDictVO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yeshijie on 2018/1/16.
 */
@Service
public class SystemDictService extends BaseService{


    /**
     * 获取字典列表(不分页)
     * @param dictName
     * @return
     * @throws Exception
     */
    public Envelop<IotSystemDictVO> getList(String dictName) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("dictName", dictName);
        HttpResponse response = HttpHelper.get(iotUrl + ServiceApi.System.FindDictByCode, params);
        Envelop<IotSystemDictVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }

}
