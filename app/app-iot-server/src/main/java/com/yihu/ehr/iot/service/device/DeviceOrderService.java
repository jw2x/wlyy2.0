package com.yihu.ehr.iot.service.device;

import com.yihu.ehr.iot.constant.ServiceApi;
import com.yihu.ehr.iot.service.common.BaseService;
import com.yihu.ehr.iot.util.http.HttpHelper;
import com.yihu.ehr.iot.util.http.HttpResponse;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.iot.device.IotDeviceOrderVO;
import com.yihu.jw.restmodel.iot.device.IotOrderPurchaseVO;
import com.yihu.jw.restmodel.iot.device.IotOrderVO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yeshijie on 2017/12/8.
 */
@Service
public class DeviceOrderService extends BaseService{

    /**
     * 创建设备订单
     * @param jsonData
     * @return
     * @throws IOException
     */
    public Envelop<IotOrderVO> create(String jsonData) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("jsonData", jsonData);
        HttpResponse response = HttpHelper.post(iotUrl + ServiceApi.DeviceOrder.CreateOrder, params);
        Envelop<IotOrderVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }

    /**
     * 根据id查找设备订单
     * @param id
     * @return
     * @throws IOException
     */
    public Envelop<IotDeviceOrderVO>  findByCode(String id) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        HttpResponse response = HttpHelper.get(iotUrl + ServiceApi.DeviceOrder.FindById, params);
        Envelop<IotDeviceOrderVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }

    /**
     * 分页查找设备订单
     * @param name
     * @param type
     * @param page
     * @param size
     * @return
     * @throws IOException
     */
    public Envelop<IotDeviceOrderVO> findPage(String name,String type,Integer page,Integer size) throws IOException{
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("type", type);
        params.put("page", page);
        params.put("size", size);
        HttpResponse response = HttpHelper.get(iotUrl + ServiceApi.DeviceOrder.FindPage, params);
        Envelop<IotDeviceOrderVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }

    /**
     * 删除订单
     * @param id
     * @return
     * @throws IOException
     */
    public Envelop<IotDeviceOrderVO> delOrder(String id) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        HttpResponse response = HttpHelper.post(iotUrl + ServiceApi.DeviceOrder.DelOrder, params);
        Envelop<IotDeviceOrderVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }

    /**
     * 修改订单
     * @param jsonData
     * @return
     * @throws IOException
     */
    public Envelop<IotOrderVO> updOrder(String jsonData) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("jsonData", jsonData);
        HttpResponse response = HttpHelper.post(iotUrl + ServiceApi.DeviceOrder.UpdOrder, params);
        Envelop<IotOrderVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }

    /**
     * 分页查找采购清单
     * @param orderId
     * @param page
     * @param size
     * @return
     * @throws IOException
     */
    public Envelop<IotOrderPurchaseVO> findPurcharsePage(String orderId,Integer page,Integer size) throws IOException{
        Map<String, Object> params = new HashMap<>();
        params.put("orderId", orderId);
        params.put("page", page);
        params.put("size", size);
        HttpResponse response = HttpHelper.get(iotUrl + ServiceApi.DeviceOrder.FindPurcharsePage, params);
        Envelop<IotOrderPurchaseVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }

    /**
     * 根据id查找采购订单
     * @param id
     * @return
     */
    public Envelop<IotOrderPurchaseVO> findPurcharseById(String id) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        HttpResponse response = HttpHelper.get(iotUrl + ServiceApi.DeviceOrder.FindPurcharseById, params);
        Envelop<IotOrderPurchaseVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }

    /**
     * 质检管理
     * @param qualityStatus
     * @param orderNo
     * @param startTime
     * @param endTime
     * @param page
     * @param size
     * @return
     */
    public Envelop<IotOrderPurchaseVO> findQualityPage(String qualityStatus,
            String orderNo,String startTime,String endTime,Integer page,Integer size) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("qualityStatus", qualityStatus);
        params.put("orderNo", orderNo);
        params.put("startTime", startTime);
        params.put("endTime", endTime);
        params.put("page", page);
        params.put("size", size);
        HttpResponse response = HttpHelper.get(iotUrl + ServiceApi.DeviceOrder.FindQualityPage, params);
        Envelop<IotOrderPurchaseVO> envelop = objectMapper.readValue(response.getBody(), Envelop.class);
        return envelop;
    }

}
