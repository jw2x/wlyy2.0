package com.yihu.ehr.health.service.dictionary;

import com.yihu.ehr.health.constant.ServiceApi;
import com.yihu.ehr.health.service.common.BaseService;
import com.yihu.ehr.health.util.http.HttpHelper;
import com.yihu.ehr.health.util.http.HttpResponse;
import com.yihu.jw.restmodel.archives.dict.SystemDictVO;
import com.yihu.jw.restmodel.common.Envelop;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yeshijie on 2018/1/16.
 */
@Service
public class SystemDictService extends BaseService{


    /**
     * 获取字典列表(不分页)
     * @return
     * @throws Exception
     */
    public List<SystemDictVO> getList(String filters) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("filters", "id="+filters);
        HttpResponse response = HttpHelper.get(profileInnerUrl+ ServiceApi.SystemDict.dictionariesWithEntry, params);
        Envelop<SystemDictVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop.getDetailModelList();
    }

    /**
     * 获取组织机构信息
     * @return
     */
//    private Envelop<IotSystemDictVO> organizations(){
//        String url = "/organizations";
//        Envelop<IotSystemDictVO> envelop = new Envelop<IotSystemDictVO>();
//        Map<String, Object> params = new HashMap<>();
//        params.put("fields","");
//        params.put("sorts","");
//        params.put("filters", "orgCode?3502");
//        params.put("page", 1);
//        params.put("size", 100);
//        try {
//            HttpResponse response = HttpHelper.get(profileInnerUrl + url, params);
//            ObjectResult result =  objectMapper.readValue(response.getBody(),ObjectResult.class);
//            if(result.isSuccessFlg()){
//                envelop = objectMapper.readValue(response.getBody(),Envelop.class);
//                envelop.setStatus(200);
//            }else {
//                envelop.setStatus(-1);
//                envelop.setErrorMsg(result.getErrorMsg());
//            }
//            return envelop;
//        } catch (Exception e) {
//            e.printStackTrace();
//            envelop.setStatus(-1);
//            envelop.setErrorMsg(ErrorCode.SystemError.toString());
//            return envelop;
//        }
//    }

}
