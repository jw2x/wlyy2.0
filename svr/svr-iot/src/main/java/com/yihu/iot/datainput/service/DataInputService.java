package com.yihu.iot.datainput.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
//import com.yihu.base.hbase.HBaseAdmin;
//import com.yihu.base.hbase.HBaseHelper;
import com.yihu.elasticsearch.ElasticSearchHelper;
import com.yihu.iot.datainput.enums.DataOperationTypeEnum;
import com.yihu.iot.datainput.util.ConstantUtils;
import com.yihu.iot.datainput.util.RowKeyUtils;
import com.yihu.iot.service.device.IotDeviceService;
import com.yihu.jw.datainput.DataBodySignsDO;
import com.yihu.jw.datainput.WeRunDataDO;
import com.yihu.jw.entity.iot.device.IotDeviceDO;
import com.yihu.jw.util.date.DateUtil;
import org.apache.commons.lang.StringUtils;
//import org.apache.hadoop.hbase.util.CollectionUtils;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;

/**
 * 1、设备注册及绑定
 * 2、不含居民身份的数据上传协议
 * 3、含居民身份的数据上传协议
 * 4、数据查询返回的协议格式
 */
@Component
public class DataInputService {
    private Logger logger = LoggerFactory.getLogger(DataInputService.class);

    @Autowired
    private IotDeviceService iotDeviceService;

    @Autowired
    private DataProcessLogService dataProcessLogService;

    @Autowired
    private ElasticSearchHelper elasticSearchHelper;

//    @Autowired
//    private HBaseHelper hBaseHelper;
//    @Autowired
//    private HBaseAdmin hBaseAdmin;

    /**
     * 居民设备注册及绑定
     */
    public String bindUser(String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        String data_source = jsonObject.getString("data_source");
        List<IotDeviceDO> deviceDOList = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        try {
            if(null != jsonArray){
                for(Object array:jsonArray){
                    JSONObject dataJson = (JSONObject)JSONObject.toJSON(array);
                    IotDeviceDO iotDeviceDO = new IotDeviceDO();
                    String sn = dataJson.getString("sn");
                    int count = iotDeviceService.countByDeviceSn(sn);
                    if(count > 0){
                        continue; //表示设备已经绑定过
                    }
                    iotDeviceDO.setDeviceSn(sn);
//                    iotDeviceDO.setCode(dataJson.getString("ext_code"));
                    iotDeviceDO.setName(dataJson.getString("device_name"));
//                    iotDeviceDO.setDeviceModel(dataJson.getString("device_model"));
                    iotDeviceDO.setDeviceSource("2"); //设备来源为居民绑定

                    iotDeviceDO.setCreateUser(dataJson.getString("idcard")); //居民绑定的，暂定创建人和修改人均为居民
                    iotDeviceDO.setCreateUserName(dataJson.getString("username"));
                    iotDeviceDO.setUpdateUser(dataJson.getString("idcard"));
                    iotDeviceDO.setUpdateUserName(dataJson.getString("username"));

//                    iotDeviceDO.setManufacturerCode(dataJson.getString("manufacture_code"));
                    iotDeviceDO.setManufacturerName(dataJson.getString("manufacture_name"));
//                    iotDeviceDO.setManufactureTel(dataJson.getString("manufacture_tel"));
//                    iotDeviceDO.setSupplierCode(dataJson.getString("owner_org_code"));
                    iotDeviceDO.setSupplierName(dataJson.getString("owner_org_name"));
                    iotDeviceDO.setName(dataJson.getString("sale_org_code"));
                    iotDeviceDO.setName(dataJson.getString("sale_org_name"));
                    deviceDOList.add(iotDeviceDO);
                }
                iotDeviceService.bindUser(deviceDOList);
                //保存日志
                dataProcessLogService.saveLog("","",data_source,"", DateUtils.formatDate(new Date(), DateUtil.yyyy_MM_dd_HH_mm_ss),"1","4","com.yihu.iot.datainput.service.DataInputService.bindUser",DataOperationTypeEnum.bindUser.getName(),0);
            }
        }catch (Exception e){
            logger.error("注册绑定失败");
            //保存日志
            dataProcessLogService.saveLog("","",data_source,"", DateUtils.formatDate(new Date(), DateUtil.yyyy_MM_dd_HH_mm_ss),"1","3","com.yihu.iot.datainput.service.DataInputService.bindUser",DataOperationTypeEnum.bindUser.getName(),1);
            return "fail";
        }
        return "success";
    }

    /**
     * 修改设备绑定居民信息
     */
    public void updateBindUser(String data_source,String deviveSn,String idcard,String username){
        IotDeviceDO iotDeviceDO = iotDeviceService.findByDeviceSn(deviveSn);
        if(null != iotDeviceDO){
            iotDeviceDO.setUpdateUser(idcard);
            iotDeviceDO.setUpdateUserName(username);
        }else{
            iotDeviceDO = new IotDeviceDO();
            iotDeviceDO.setDeviceSource(data_source);
            iotDeviceDO.setDeviceSn(deviveSn);
            iotDeviceDO.setCreateUser(idcard);
            iotDeviceDO.setCreateUserName(username);
        }
        iotDeviceService.save(iotDeviceDO);
        //保存日志
        dataProcessLogService.saveLog("","",data_source,"", DateUtils.formatDate(new Date(), DateUtil.yyyy_MM_dd_HH_mm_ss),"1","3","com.yihu.iot.datainput.service.DataInputService.bindUser","",1);

    }

    /**
     * 上传数据
     * @param json
     * @return
     */
    public String uploadData(String json) throws IOException {
        String fileName = "";
        String fileAbsPath = "";
        String rowkey = "";
        //提取json某些项值
        DataBodySignsDO dataBodySignsDO = JSONObject.parseObject(json,DataBodySignsDO.class);
        JSONObject jsonObject = JSONObject.parseObject(json);
        String accessToken= dataBodySignsDO.getAccess_token();
        String dataSource = dataBodySignsDO.getData_source();
        String deviceSn = dataBodySignsDO.getSn();
        String extCode = dataBodySignsDO.getExt_code();

        //包含居民身份的数据，对设备数据进行校验绑定，此处包含的信息只有身份证号和用户名以及设备序列号，如果设备库中存在该序号的设备，则对绑定居民进行修改，改为当前居民，如果没有则跳过
        if(jsonObject.containsKey("idcard") && jsonObject.containsKey("username")){
            String idcard = jsonObject.getString("idcard");
            String username = jsonObject.getString("username");
            updateBindUser(dataSource,deviceSn,idcard,username);
        }

        JSONArray jsonArray = jsonObject.getJSONArray("data");
        if(null == jsonArray || jsonArray.size() == 0){
            return "json no data";
        }

        List<String> rowkeyList = new ArrayList<>();
        List<Map<String,Map<String,String>>> familyList = new ArrayList<>();

        //循环数据，一组数据存一行，生成一个rowkey，并将该rowkey存到es中
        for(Object obj:jsonArray){
            JSONObject data = (JSONObject)obj;
            data.put("del","1"); //添加删除标记
            try {
                String measuretime = jsonObject.getString("measure_time");
                if(null == measuretime){
                    measuretime = DateUtils.formatDate(new Date(), DateUtil.yyyy_MM_dd_HH_mm_ss);
                }
                //生成一份json数据的rowkey
                rowkey = RowKeyUtils.makeRowKey(accessToken,dataSource, extCode, DateUtil.dateTimeParse(measuretime).getTime());
                data.put("rid",rowkey);//hbase的rowkey
                rowkeyList.add(rowkey);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //组装B列
            Map<String, Map<String, String>> family = new HashMap<>();
            Map<String, String> columnsB = new HashMap<>();
            for(String key:data.keySet()){
                if(StringUtils.equalsIgnoreCase("rid",key)){ //存到hbase里的数据不需要rid
                    continue;
                }
                columnsB.put(key,data.getString(key));
            }
            if(data.containsKey("ecg")){
                fileName = data.getString("fileName");
                fileAbsPath = data.getString("filepath");
            }
            family.put(ConstantUtils.familyB,columnsB);
            familyList.add(family);
        }

        //将数据存入es
        elasticSearchHelper.save(ConstantUtils.esIndex, ConstantUtils.esType, jsonObject.toJSONString());

        try {
//            boolean tableExists = hBaseAdmin.isTableExists(ConstantUtils.tableName);
//            if (!tableExists) {
//                hBaseAdmin.createTable(ConstantUtils.tableName,ConstantUtils.familyB);
//            }
//            hBaseHelper.addBulk(ConstantUtils.tableName, rowkeyList, familyList);
        } catch (Exception e) {
            e.printStackTrace();
            //保存日志
            dataProcessLogService.saveLog(fileName, fileAbsPath, dataSource, "", DateUtils.formatDate(new Date(), DateUtil.yyyy_MM_dd_HH_mm_ss), "1", "3", "com.yihu.iot.datainput.service.DataInputService.uploadData", DataOperationTypeEnum.upload1.getName(), 1);
            return "fail";
        }
        //保存日志
        dataProcessLogService.saveLog(fileName, fileAbsPath, dataSource, "", DateUtils.formatDate(new Date(), DateUtil.yyyy_MM_dd_HH_mm_ss), "1", "4", "com.yihu.iot.datainput.service.DataInputService.uploadData", DataOperationTypeEnum.upload1.getName(), 0);

        JSONObject result = new JSONObject();
        JSONArray rids = new JSONArray();
        rids.addAll(rowkeyList);
        result.put("rid",rids);
        result.put("upload_time",DateUtils.formatDate(new Date(), DateUtil.yyyy_MM_dd_HH_mm_ss));
        return result.toJSONString();
    }

    /**
     * 上传数据
     * @param json
     * @return
     */
    public String inputBodySignsData(String json) throws IOException {
        JSONObject result = new JSONObject();
        result.put("upload_time",DateUtils.formatDate(new Date(), DateUtil.yyyy_MM_dd_HH_mm_ss));
        if(StringUtils.isEmpty(json)){
            result.put("response",ConstantUtils.FAIL);
            result.put("msg","parameter json is null");
            return result.toString();
        }
        String fileName = "";
        String fileAbsPath = "";
        String rowkey = "";
        //提取json某些项值
        DataBodySignsDO dataBodySignsDO = null;
        try {
            dataBodySignsDO  = JSONObject.parseObject(json,DataBodySignsDO.class);
        }catch (Exception e){
            logger.error("json parse error,invalid json string");
            result.put("msg","json parse error,invalid json string");
            result.put("response",ConstantUtils.FAIL);
            return result.toString();
        }

        JSONObject jsonObject = JSONObject.parseObject(json);
        String accessToken= dataBodySignsDO.getAccess_token();
        String dataSource = dataBodySignsDO.getData_source();
        String deviceSn = dataBodySignsDO.getSn();
        String extCode = dataBodySignsDO.getExt_code();

        //包含居民身份的数据，对设备数据进行校验绑定，此处包含的信息只有身份证号和用户名以及设备序列号，如果设备库中存在该序号的设备，则对绑定居民进行修改，改为当前居民，如果没有则跳过
        /*if(jsonObject.containsKey("idcard") && jsonObject.containsKey("username")){
            String idcard = jsonObject.getString("idcard");
            String username = jsonObject.getString("username");
            updateBindUser(dataSource,deviceSn,idcard,username);
        }*/

        JSONArray jsonArray = jsonObject.getJSONArray("data");
        if(null == jsonArray || jsonArray.size() == 0){
            result.put("response",ConstantUtils.FAIL);
            result.put("msg","parameter 'data' of json no exist");
            return result.toString();
        }

        List<String> rowkeyList = new ArrayList<>();
        List<Map<String,Map<String,String>>> familyList = new ArrayList<>();

        //循环数据，一组数据存一行，生成一个rowkey，并将该rowkey存到es中
        for(Object obj:jsonArray){
            JSONObject data = (JSONObject)obj;
            data.put("del","1"); //添加删除标记
            try {
                String measuretime = jsonObject.getString("measure_time");
                if(null == measuretime){
                    measuretime = DateUtils.formatDate(new Date(), DateUtil.yyyy_MM_dd_HH_mm_ss);
                }
                //生成一份json数据的rowkey
                rowkey = RowKeyUtils.makeRowKey(accessToken,dataSource, extCode, DateUtil.dateTimeParse(measuretime).getTime());
                data.put("rid",rowkey);//hbase的rowkey
                rowkeyList.add(rowkey);
            } catch (Exception e) {
                logger.error("make rowkey error");
                result.put("msg","make rowkey error");
                result.put("response",ConstantUtils.FAIL);
                return result.toString();
            }
            //组装B列
            Map<String, Map<String, String>> family = new HashMap<>();
            Map<String, String> columnsB = new HashMap<>();
            for(String key:data.keySet()){
                if(StringUtils.equalsIgnoreCase("rid",key)){ //存到hbase里的数据不需要rid
                    continue;
                }
                columnsB.put(key,data.getString(key));
            }
            if(data.containsKey("ecg")){
                fileName = data.getString("fileName");
                fileAbsPath = data.getString("filepath");
            }
            family.put(ConstantUtils.familyB,columnsB);
            familyList.add(family);
        }
        List<String> saveList = new ArrayList<>();
        saveList.add(jsonObject.toJSONString());
        //将数据存入es
        boolean success = false;
        try {
            success = elasticSearchHelper.save(ConstantUtils.esIndex, ConstantUtils.esType, saveList);
        }catch (Exception e){
            logger.error("upload signBodyData to elasticsearch failed," + e.getMessage());
            result.put("msg","upload signBodyData to elasticsearch failed," + e.getMessage());
        }
        if(success){
            dataProcessLogService.saveLog(fileName, fileAbsPath, dataSource, "", DateUtils.formatDate(new Date(), DateUtil.yyyy_MM_dd_HH_mm_ss), "1", "4", "com.yihu.iot.datainput.service.DataInputService.uploadData", DataOperationTypeEnum.upload1.getName(), 0);
            JSONArray rids = new JSONArray();
            rids.addAll(rowkeyList);
            result.put("rid",rids);
            result.put("response",ConstantUtils.SUCCESS);
        }else{
            result.put("response",ConstantUtils.FAIL);
        }

        /*try {
            boolean tableExists = hBaseAdmin.isTableExists(ConstantUtils.tableName);
            if (!tableExists) {
                hBaseAdmin.createTable(ConstantUtils.tableName,ConstantUtils.familyB);
            }
            hBaseHelper.addBulk(ConstantUtils.tableName, rowkeyList, familyList);
        } catch (Exception e) {
            e.printStackTrace();
            //保存日志
            dataProcessLogService.saveLog(fileName, fileAbsPath, dataSource, "", DateUtils.formatDate(new Date(), DateUtil.yyyy_MM_dd_HH_mm_ss), "1", "3", "com.yihu.iot.datainput.service.DataInputService.uploadData", DataOperationTypeEnum.upload1.getName(), 1);
            return "fail";
        }*/
        //保存日志

        return result.toJSONString();
    }


    /**
     * 上传微信运动数据
     * 目前只上传到es，hbase没有可用服务器
     * @param json
     * @return
     */
    public String inputWeRunData(String json){
        JSONObject result = new JSONObject();
        result.put("upload_time",DateUtils.formatDate(new Date(), DateUtil.yyyy_MM_dd_HH_mm_ss));
        if(StringUtils.isEmpty(json)){
            result.put("response",ConstantUtils.FAIL);
            result.put("msg","parameter json is null");
            return result.toString();
        }
        WeRunDataDO weRunData = JSONObject.parseObject(json,WeRunDataDO.class);
        boolean bool = false;
        //用户code不能为空
        if(StringUtils.isEmpty(weRunData.getUsercode())){
            result.put("response",ConstantUtils.FAIL);
            result.put("msg","invalid usercode");
            return result.toString();
        }
        //步数数据不能为空
        if(CollectionUtils.isEmpty(weRunData.getStepInfoList())){
            result.put("response",ConstantUtils.FAIL);
            result.put("msg","invalid stepinfolist");
            return result.toString();
        }
        try{
            bool = elasticSearchHelper.save(ConstantUtils.weRunDataIndex,ConstantUtils.weRunDataType,json);
        }catch (Exception e){
            logger.error("upload weRunData to elasticsearch failed");
            result.put("msg","upload weRunData to elasticsearch failed");
        }
        if(bool){
                result.put("response",ConstantUtils.SUCCESS);
            }else{
                result.put("response",ConstantUtils.FAIL);
        }
       return result.toString();
    }
}
