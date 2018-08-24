package com.yihu.iot.datainput.service;

import com.alibaba.fastjson.JSONObject;
import com.yihu.iot.datainput.enums.DataOperationTypeEnum;
import com.yihu.iot.datainput.enums.DataTypeEnum;
import com.yihu.jw.datainput.DataBodySignsDO;
import com.yihu.jw.entity.iot.datainput.DataStandardDO;
import com.yihu.jw.util.date.DateUtil;
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
        //将传过来的数据转换为对象
        DataBodySignsDO dataBodySignsDO = JSONObject.parseObject(oldJson,DataBodySignsDO.class);
        JSONObject jsonObject = JSONObject.parseObject(oldJson);
        //如果没有授权或者数据来源，则表示数据异常
        if(null == dataBodySignsDO.getAccess_token() || (null != dataBodySignsDO.getAccess_token() && null == dataBodySignsDO.getData_source())){
            logger.warn("传过来的数据无有效access_token或data_source",oldJson);
            return "";
        }
        //保存日志
        dataProcessLogService.saveLog("","",dataBodySignsDO.getData_source(),"", DateUtils.formatDate(new Date(),DateUtil.yyyy_MM_dd_HH_mm_ss),"1","4","com.yihu.iot.datainput.service.DataStandardConvertService.iconvert", DataOperationTypeEnum.convert.getName(),0);
        return String.valueOf(dataBodySignsDO);
    }

}

