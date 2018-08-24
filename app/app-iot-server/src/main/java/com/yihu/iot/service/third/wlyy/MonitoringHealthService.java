package com.yihu.iot.service.third.wlyy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yihu.ehr.util.datetime.DateUtil;
import com.yihu.iot.constant.ServiceApi;
import com.yihu.iot.service.common.BaseService;
import com.yihu.iot.util.http.HttpHelper;
import com.yihu.iot.util.http.HttpResponse;
import com.yihu.jw.restmodel.web.MixEnvelop;
import io.swagger.annotations.ApiOperation;
import iot.device.LocationDataVO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yeshijie on 2018/2/11.
 */
@Service
public class MonitoringHealthService extends BaseService {

    private Logger logger = LoggerFactory.getLogger(MonitoringHealthService.class);
    @Value("${third.wlyy.url}")
    private String wlyyUrl;
    @Value("${third.wlyy.appid}")
    private String appid;
    @Value("${third.wlyy.appsecret}")
    private String appSecret;
    public static Map<String,String> tokenMap = new HashMap<>();

    /**
     * 获取位置信息
     * @param diseaseCondition
     * @return
     */
    public MixEnvelop<LocationDataVO, LocationDataVO> findDeviceLocations(Integer diseaseCondition, Integer page, Integer size, String type) throws IOException {
        MixEnvelop<LocationDataVO, LocationDataVO> envelop = new MixEnvelop<>();
        MixEnvelop<LocationDataVO, LocationDataVO> envelopTmp = null;
        JSONArray jsonArray = new JSONArray();
        Integer total = 0;
        if(StringUtils.isNotBlank(type)){
            String re = searchpatientdevicesn(type, page, size);
            JSONObject json = JSON.parseObject(re);
            if(json.getInteger("status")==200){
                JSONObject data = json.getJSONObject("data");
                total = data.getInteger("total");
                JSONArray list = data.getJSONArray("list");
                for(int i=0;i<list.size();i++){
                    JSONArray jsonArrayTemp = new JSONArray();
                    JSONObject deviceSn = new JSONObject();
                    deviceSn.put("andOr","or");
                    deviceSn.put("field","deviceSn");
                    deviceSn.put("condition","=");
                    deviceSn.put("value",list.getString(i));
                    jsonArrayTemp.add(deviceSn);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("filter",jsonArray);
                    jsonObject.put("page",page);
                    jsonObject.put("size",size);
                    Map<String, Object> params = new HashMap<>();
                    params.put("jsonData", jsonObject.toString());
                    HttpResponse response = HttpHelper.get(iotUrl + ServiceApi.PatientDevice.findLocationByIdCard, params);
                    envelopTmp = objectMapper.readValue(response.getBody(),MixEnvelop.class);
                    envelop.getDetailModelList().addAll(envelopTmp.getDetailModelList());
                }
            }
            envelop.setTotalCount(total);
            return envelop;
        }else {
            //查找全部
            if(diseaseCondition!=null){
                JSONObject json = new JSONObject();
                json.put("andOr","and");
                json.put("field","diseaseCondition");
                json.put("condition","=");
                json.put("value",diseaseCondition);
                jsonArray.add(json);
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("filter",jsonArray);
            jsonObject.put("page",page);
            jsonObject.put("size",size);
            Map<String, Object> params = new HashMap<>();
            params.put("jsonData", jsonObject.toString());
            HttpResponse response = HttpHelper.get(iotUrl + ServiceApi.PatientDevice.findLocationByIdCard, params);
            envelop = objectMapper.readValue(response.getBody(),MixEnvelop.class);
            return envelop;
        }
    }

    /**
     * 根据病种类型，搜索已绑定设备的居民设备SN码
     * 类型(1高血压 2糖尿病)
     */
    public String searchpatientdevicesn(String type,Integer page,Integer size){
        String url = "/wlyygc/iot_monitoring/searchpatientdevicesn";
        Map<String, Object> params = new HashMap<>();
        params.put("type",type);
        params.put("page",page);
        params.put("pageSize",size);
        return sendGet(url,params);
    }


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
    private synchronized String getAccessToken(){
        String token = "";
        if(tokenMap.get("token")!=null){
            token = tokenMap.get("token");
            String time = tokenMap.get("time");
            Date start = DateUtil.strToDate(time);
            long m = (System.currentTimeMillis()-start.getTime())/1000;
            long overTime = 15*6*60;//1.5小时
            if(m<overTime){
                return token;
            }
        }
        Map<String, Object> params = new HashMap<>();
        params.put("appid", appid);
        params.put("appSecret", appSecret);
        String url = "/gc/accesstoken";
        HttpResponse response = HttpHelper.post(wlyyUrl + url, params);
        JSONObject jsonObject = JSON.parseObject(response.getBody());
        if(jsonObject.getInteger("status")==10000){
            String accesstoken = jsonObject.getJSONObject("result").getString("accesstoken");
            tokenMap.put("token",accesstoken);
            tokenMap.put("time", DateUtil.toString(new Date(), DateUtil.DEFAULT_YMDHMSDATE_FORMAT));
            return accesstoken;
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
    public String searchPatient(String name,Integer page,Integer pageSize,String idcards){
        String url = "/wlyygc/iot_monitoring/searchPatient";
        Map<String, Object> params = new HashMap<>();
        params.put("name",name);
        params.put("page",page);
        params.put("pageSize",pageSize);
        params.put("idcards",idcards);
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
    public String getHealthIndexChartByPatient(String patient, Integer type, Integer gi_type, String begin, String end,String time) {
        String url = "/wlyygc/iot_monitoring/chart";
        Map<String, Object> params = new HashMap<>();
        params.put("patient",patient);
        params.put("type",type);
        params.put("time",time);
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
