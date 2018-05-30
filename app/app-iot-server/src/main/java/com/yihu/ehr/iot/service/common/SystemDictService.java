package com.yihu.ehr.iot.service.common;

import com.yihu.ehr.constants.ErrorCode;
import com.yihu.ehr.iot.constant.ServiceApi;
import com.yihu.ehr.iot.model.ObjectResult;
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
        if("HOSPITAL".equals(dictName)){
            return organizations();
        }
        Map<String, Object> params = new HashMap<>();
        params.put("dictName", dictName);
        HttpResponse response = HttpHelper.get(iotUrl + ServiceApi.System.FindDictByCode, params);
        Envelop<IotSystemDictVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }

    /**
     * 获取组织机构信息
     * @return
     */
    private Envelop<IotSystemDictVO> organizations(){
        String url = "/organizations";
        Envelop<IotSystemDictVO> envelop = new Envelop<IotSystemDictVO>();
        Map<String, Object> params = new HashMap<>();
        params.put("fields","");
        params.put("sorts","");
        params.put("filters", "orgCode?3502");
        params.put("page", 1);
        params.put("size", 100);
        try {
            HttpResponse response = HttpHelper.get(profileInnerUrl + url, params);
            ObjectResult result =  objectMapper.readValue(response.getBody(),ObjectResult.class);
            if(result.isSuccessFlg()){
                envelop = objectMapper.readValue(response.getBody(),Envelop.class);
                envelop.setStatus(200);
            }else {
                envelop.setStatus(-1);
                envelop.setMessage(result.getErrorMsg());
            }
            return envelop;
        } catch (Exception e) {
            e.printStackTrace();
            envelop.setStatus(-1);
            envelop.setMessage(ErrorCode.SystemError.toString());
            return envelop;
        }
    }

}
