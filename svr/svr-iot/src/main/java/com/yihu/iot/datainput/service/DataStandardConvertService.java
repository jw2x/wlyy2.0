package com.yihu.iot.datainput.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yihu.iot.datainput.enums.DataDeviceTypeEnum;
import com.yihu.iot.datainput.enums.DataOperationTypeEnum;
import com.yihu.iot.datainput.enums.DataTypeEnum;
import com.yihu.jw.iot.data_input.DataStandardDO;
import com.yihu.jw.util.date.DateUtil;
import com.yihu.jw.util.spring.SpringContextHolder;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 数据标准转换
 */
@Component
public class DataStandardConvertService {

    private Logger logger = LoggerFactory.getLogger(DataStandardConvertService.class);
    public  static Map<String,List<DataStandardDO>> dataMap = new HashMap<>();

    @Autowired
    private DataProcessLogService dataProcessLogService;

    @Autowired
    private DataStandardService dataStandardService;


    /**
     * 从数据库读取标准,并放到map，key为协议大类名称，目前只有体征数据协议
     */
    @PostConstruct
    public void init(){
        Set<String> baseNames = DataTypeEnum.getNames();
        for(String baseNmae:baseNames){
            List<DataStandardDO>  resultList = dataStandardService.getList(baseNmae);
            dataMap.put(baseNmae, resultList);
        }
    }

    /**
     * 数据标准转换，i健康数据-->物联网数据标准
     * i健康模板数据
     * 物联网模板数据
     * @param oldJson 要转换的json
     */
    public String iconvert(String oldJson){
        JSONObject jsonObject = JSONObject.parseObject(oldJson);
        //如果没有授权或者数据来源，则表示数据异常
        if(!jsonObject.containsKey("access_token") || !jsonObject.containsKey("data_source")){
            logger.warn("传过来的数据无有效access_token或data_source",oldJson);
            return "";
        }

        //拿到i健康json数据里的各项值
        Object id = jsonObject.get("id");
        if(null == id){
            id = UUID.randomUUID();
        }
        String access_token = (String)jsonObject.get("access_token");
        String data_source = (String)jsonObject.get("data_source");
        String sn = (String)jsonObject.get("deviceSn");
        String deviceType = jsonObject.getString("deviceType");
        String ext_code = (String)jsonObject.get("userType");
        String data = (String)jsonObject.get("userType") + (String)jsonObject.get("unit");
        String device_name = (String)jsonObject.get("device_name");
        String device_model = (String)jsonObject.get("device_model");
        String measure_time = (String)jsonObject.get("sendTime");

        JSONObject newJsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject dataJsonObject = new JSONObject();

        newJsonObject.put("id",id);
        newJsonObject.put("access_token",access_token);
        newJsonObject.put("data_source",data_source);
        newJsonObject.put("sn",sn);
        newJsonObject.put("ext_code",ext_code);
        newJsonObject.put("device_name",device_name);
        newJsonObject.put("device_model",device_model);
        newJsonObject.put("data",jsonArray);

        dataJsonObject.put("measure_time",measure_time);
        String deviceDataName = DataDeviceTypeEnum.getNameByType(deviceType);
        dataJsonObject.put(deviceDataName,data);
        jsonArray.add(dataJsonObject);
        //保存日志
        dataProcessLogService.saveLog("","",data_source,measure_time, DateUtils.formatDate(new Date(),DateUtil.yyyy_MM_dd_HH_mm_ss),"1","4","com.yihu.iot.datainput.service.DataStandardConvertService.iconvert", DataOperationTypeEnum.convert.getName(),0);
        //转换后的标准json数据
        return newJsonObject.toJSONString();
    }

}

