package com.yihu.ehr.iot.service.third.wlyy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yihu.ehr.iot.service.common.BaseService;
import com.yihu.ehr.iot.util.http.HttpHelper;
import com.yihu.ehr.iot.util.http.HttpResponse;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yeshijie on 2018/2/11.
 */
@Service
public class MonitoringHealthService extends BaseService{

    private Logger logger = LoggerFactory.getLogger(MonitoringHealthService.class);
    @Value("${third.wlyy.url}")
    private String wlyyUrl;
    @Value("${third.wlyy.appid}")
    private String appid;
    @Value("${third.wlyy.appsecret}")
    private String appSecret;


    /**
     * 访问i健康接口，自带登录信息
     * @param url
     * @return
     */
    private String sendGet(String url,Map<String, Object> params){
        params.put("accesstoken",getAccessToken());
        HttpResponse response = HttpHelper.get(wlyyUrl + url, params);
        return response.getBody();
    }

    /**
     * 返回accessToken
     * @return
     * @throws IOException
     */
    private String getAccessToken(){
        Map<String, Object> params = new HashMap<>();
        params.put("appid", appid);
        params.put("appSecret", appSecret);
        String url = "/gc/accesstoken";
        HttpResponse response = HttpHelper.post(wlyyUrl + url, params);
        JSONObject jsonObject = JSON.parseObject(response.getBody());
        if(jsonObject.getInteger("status")==10000){
            return jsonObject.getJSONObject("result").getString("accesstoken");
        }
        return null;
    }


    /**
     * 设备发放情况
     */
    public String equipmentDistribution(){
        String url = "/wlyygc/iot_monitoring/equipmentDistribution";
        Map<String, Object> params = new HashMap<>();
        return sendGet(url,params);
    }


    /**
     * 慢病患者情况-统计
     * 类型(2糖尿病，1高血压)
     */
    public String chronicDiseaseCount(String type){
        String url = "/wlyygc/iot_monitoring/chronicDiseaseCount";
        Map<String, Object> params = new HashMap<>();
        params.put("type",type);
        return sendGet(url,params);
    }

    /**
     * 预警信息警报
     * @param page
     * @param pageSize
     * @return
     */
    public String warningInformationAlarm(Integer page,Integer pageSize){
        String url = "/wlyygc/iot_monitoring/warningInformationAlarm";
        Map<String, Object> params = new HashMap<>();
        params.put("page",page);
        params.put("pageSize",pageSize);
        return sendGet(url,params);
    }

    /**
     * 居民 医生搜索
     * @param page
     * @param pageSize
     * @return
     */
    public String searchPatient(String name,Integer page,Integer pageSize){
        String url = "/wlyygc/iot_monitoring/searchPatient";
        Map<String, Object> params = new HashMap<>();
        params.put("name",name);
        params.put("page",page);
        params.put("pageSize",pageSize);
        return sendGet(url,params);
    }

    /**
     * 设备绑定情况
     * @param type 设备类型(1血糖仪，2血压计
     * @return
     */
    public String deviceBinding(String type){
        String url = "/wlyygc/iot_monitoring/deviceBinding";
        Map<String, Object> params = new HashMap<>();
        params.put("type",type);
        return sendGet(url,params);
    }


    /**
     * 个人信息
     * @param patient
     * @return
     */
    @RequestMapping(value = "/persionalInfo",method = RequestMethod.GET)
    @ApiOperation("个人信息")
    public String persionalInfo(String patient){
        String url = "/wlyygc/iot_monitoring/persionalInfo";
        Map<String, Object> params = new HashMap<>();
        params.put("patient",patient);
        return sendGet(url,params);
    }

    /**
     * 家人信息
     * @param patient
     * @return
     */
    public String familyMember(String patient){
        String url = "/wlyygc/iot_monitoring/familyMember";
        Map<String, Object> params = new HashMap<>();
        params.put("patient",patient);
        return sendGet(url,params);
    }

    /**
     * 健康设备
     * @param patient
     * @return
     */
    @RequestMapping(value = "/healthDevice",method = RequestMethod.GET)
    @ApiOperation("健康设备")
    public String healthDevice(String patient){
        String url = "/wlyygc/iot_monitoring/healthDevice";
        Map<String, Object> params = new HashMap<>();
        params.put("patient",patient);
        return sendGet(url,params);
    }

    /**
     * 体征数据
     * @param patient
     * @param type
     * @param gi_type
     * @param begin
     * @param end
     * @return
     */
    public String getHealthIndexChartByPatient(String patient,Integer type, Integer gi_type,String begin,String end) {
        String url = "/wlyygc/iot_monitoring/chart";
        Map<String, Object> params = new HashMap<>();
        params.put("patient",patient);
        params.put("type",type);
        params.put("begin",begin);
        params.put("end",end);
        params.put("gi_type",gi_type);
        return sendGet(url,params);
    }

    /**
     * 获取门诊记录/住院记录(基卫+APP)
     * @param patient
     * @param type 类型(1血糖，2血压)
     * @param page
     * @param pageSize
     * @return
     */
    public String getAllEvent(String patient,String type,String page,String pageSize) {
        String url = "/wlyygc/iot_monitoring/event";
        Map<String, Object> params = new HashMap<>();
        params.put("patient",patient);
        params.put("type",type);
        params.put("page",page);
        params.put("pageSize",pageSize);
        return sendGet(url,params);
    }

}
