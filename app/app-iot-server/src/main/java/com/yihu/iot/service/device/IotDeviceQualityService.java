package com.yihu.iot.service.device;

import com.yihu.iot.constant.ServiceApi;
import com.yihu.iot.service.common.BaseService;
import com.yihu.iot.util.http.HttpHelper;
import com.yihu.iot.util.http.HttpResponse;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.iot.device.IotDeviceQualityInspectionPlanVO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yeshijie on 2018/01/24.
 */
@Service
public class IotDeviceQualityService extends BaseService {

    /**
     * 创建设备质检
     * @param jsonData
     * @return
     */
    public MixEnvelop<IotDeviceQualityInspectionPlanVO, IotDeviceQualityInspectionPlanVO> create(String jsonData) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("jsonData", jsonData);
        HttpResponse response = HttpHelper.post(iotUrl + ServiceApi.Quality.AddQualityPlan, params);
        MixEnvelop<IotDeviceQualityInspectionPlanVO, IotDeviceQualityInspectionPlanVO> envelop = objectMapper.readValue(response.getBody(),MixEnvelop.class);
        return envelop;
    }

    /**
     * 根据code查找设备质检
     * @param id
     * @return
     */
    public MixEnvelop<IotDeviceQualityInspectionPlanVO, IotDeviceQualityInspectionPlanVO> findByCode(String id) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        HttpResponse response = HttpHelper.get(iotUrl + ServiceApi.Quality.FindById, params);
        MixEnvelop<IotDeviceQualityInspectionPlanVO, IotDeviceQualityInspectionPlanVO> envelop = objectMapper.readValue(response.getBody(),MixEnvelop.class);
        return envelop;
    }

    /**
     * 分页获取设备质检计划
     * @param purcharseId
     * @param page
     * @param size
     * @return
     * @throws IOException
     */
    public MixEnvelop<IotDeviceQualityInspectionPlanVO, IotDeviceQualityInspectionPlanVO> queryQualityPlanPage(String purcharseId, String orderNo,
                                                                             String startTime, String endTime, Integer page, Integer size) throws IOException{
        Map<String, Object> params = new HashMap<>();
        params.put("purcharseId", purcharseId);
        params.put("orderNo", orderNo);
        params.put("startTime", startTime);
        params.put("endTime", endTime);
        params.put("page", page);
        params.put("size", size);
        HttpResponse response = HttpHelper.get(iotUrl + ServiceApi.Quality.QueryQualityPlanPage, params);
        MixEnvelop<IotDeviceQualityInspectionPlanVO, IotDeviceQualityInspectionPlanVO> envelop = objectMapper.readValue(response.getBody(),MixEnvelop.class);
        return envelop;
    }

    /**
     * 删除质检计划
     * @param id
     * @return
     * @throws IOException
     */
    public MixEnvelop<IotDeviceQualityInspectionPlanVO, IotDeviceQualityInspectionPlanVO> delQualityPlan(String id) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        HttpResponse response = HttpHelper.post(iotUrl + ServiceApi.Quality.DelQualityPlan, params);
        MixEnvelop<IotDeviceQualityInspectionPlanVO, IotDeviceQualityInspectionPlanVO> envelop = objectMapper.readValue(response.getBody(),MixEnvelop.class);
        return envelop;
    }

    /**
     * 完成质检计划
     * @param actualTime
     * @param id
     * @return
     * @throws IOException
     */
    public MixEnvelop<IotDeviceQualityInspectionPlanVO, IotDeviceQualityInspectionPlanVO> completeQualityPlan(String actualTime, String id) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("actualTime", actualTime);
        params.put("id", id);
        HttpResponse response = HttpHelper.post(iotUrl + ServiceApi.Quality.CompleteQualityPlan, params);
        MixEnvelop<IotDeviceQualityInspectionPlanVO, IotDeviceQualityInspectionPlanVO> envelop = objectMapper.readValue(response.getBody(),MixEnvelop.class);
        return envelop;
    }

    /**
     * 完成质检计划(按采购id)
     * @param actualTime
     * @param purchaseId
     * @return
     * @throws IOException
     */
    public MixEnvelop<IotDeviceQualityInspectionPlanVO, IotDeviceQualityInspectionPlanVO> completePlanByPurchaseId(String actualTime, String purchaseId) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("actualTime", actualTime);
        params.put("purchaseId", purchaseId);
        HttpResponse response = HttpHelper.post(iotUrl + ServiceApi.Quality.CompletePlanByPurchaseId, params);
        MixEnvelop<IotDeviceQualityInspectionPlanVO, IotDeviceQualityInspectionPlanVO> envelop = objectMapper.readValue(response.getBody(),MixEnvelop.class);
        return envelop;
    }

}
