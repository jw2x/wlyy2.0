package com.yihu.iot.datainput.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yihu.base.es.config.ElastricSearchHelper;
import com.yihu.base.es.config.model.SaveModel;
import com.yihu.base.hbase.HBaseAdmin;
import com.yihu.base.hbase.HBaseHelper;
import com.yihu.iot.datainput.enums.DataOperationTypeEnum;
import com.yihu.iot.datainput.util.ConstantUtils;
import com.yihu.iot.datainput.util.RowKeyUtils;
import com.yihu.iot.service.device.IotDeviceService;
import com.yihu.jw.iot.device.IotDeviceDO;
import com.yihu.jw.util.date.DateUtil;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    private ElastricSearchHelper elastricSearchHelper;

    @Autowired
    private HBaseHelper hBaseHelper;

    @Autowired
    private HBaseAdmin hBaseAdmin;




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
                    iotDeviceDO.setDeviceModel(dataJson.getString("device_model"));
                    iotDeviceDO.setDeviceSource("2"); //设备来源为居民绑定

                    iotDeviceDO.setCreateUser(dataJson.getString("idcard")); //居民绑定的，暂定创建人和修改人均为居民
                    iotDeviceDO.setCreateUserName(dataJson.getString("username"));
                    iotDeviceDO.setUpdateUser(dataJson.getString("idcard"));
                    iotDeviceDO.setUpdateUserName(dataJson.getString("username"));

                    iotDeviceDO.setManufacturerCode(dataJson.getString("manufacture_code"));
                    iotDeviceDO.setManufacturerName(dataJson.getString("manufacture_name"));
                    iotDeviceDO.setManufactureTel(dataJson.getString("manufacture_tel"));
                    iotDeviceDO.setSupplierCode(dataJson.getString("owner_org_code"));
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
    public String uploadData(String json){
        String fileName = "";
        String fileAbsPath = "";
        String rowkey = "";
        //提取json各项值
        JSONObject jsonObject = JSONObject.parseObject(json);
        String accessToken= jsonObject.getString("access_token");
        String dataSource = jsonObject.getString("data_source");
        String deviceSn = jsonObject.getString("sn");
        String extCode = jsonObject.getString("ext_code");
        String measuretime = jsonObject.getString("measure_time");
        if(null == measuretime){
            measuretime = DateUtils.formatDate(new Date(), DateUtil.yyyy_MM_dd_HH_mm_ss);
        }
        //包含居民身份的数据，对设备数据进行校验绑定，此处包含的信息只有身份证号和用户名以及设备序列号，如果设备库中存在该序号的设备，则对绑定居民进行修改，改为当前居民，如果没有则跳过
        if(jsonObject.containsKey("idcard") && jsonObject.containsKey("username")){
            String idcard = jsonObject.getString("idcard");
            String username = jsonObject.getString("username");
            updateBindUser(dataSource,deviceSn,idcard,username);
        }

        try {
            rowkey = RowKeyUtils.makeRowKey(accessToken, deviceSn, extCode, DateUtil.dateTimeParse(measuretime).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //将数据存入es
        jsonObject.put("_id", new SaveModel().getId());//es的id继承至jestId
        jsonObject.put("id", rowkey);//hbase的rowkey
        elastricSearchHelper.save(ConstantUtils.esIndex, ConstantUtils.esType, jsonObject.toJSONString());


        Map<String, Map<String, String>> family = new HashMap<>();
//        List<Map<String, String>> columnsA = new ArrayList<>();
        Map<String, String> columnsA = new HashMap<>();
        Map<String, String> columnsB = new HashMap<>();
        //组装A列
        columnsA.put("access_token",accessToken);
        columnsA.put("data_source",dataSource);
        columnsA.put("sn",deviceSn);
        columnsA.put("ext_code",extCode);
        columnsA.put("device_name",jsonObject.getString("device_name"));
        columnsA.put("device_model",jsonObject.getString("device_model"));
        family.put(ConstantUtils.tableName,columnsA);

        JSONArray jsonArray = jsonObject.getJSONArray("data");
        if(null == jsonArray || jsonArray.size() == 0){
            return "json no data";
        }
        //组装B列
        for(Object obj:jsonArray){
            JSONObject data = (JSONObject)obj;
           for(String key:data.keySet()){
               columnsB.put(key,data.getString(key));
           }
           if(data.containsKey("ecg")){
               fileName = data.getString("fileName");
               fileAbsPath = data.getString("filepath");
           }
//            columnsA.add(columnsB);
        }
        family.put(ConstantUtils.familyB, columnsB);
        try {
            boolean tableExists = hBaseAdmin.isTableExists(ConstantUtils.tableName);
            if (!tableExists) {
                hBaseAdmin.createTable(ConstantUtils.tableName,ConstantUtils.familyA,ConstantUtils.familyB);
            }
            hBaseHelper.add(ConstantUtils.tableName, rowkey, family);
        } catch (Exception e) {
            e.printStackTrace();
            //保存日志
            dataProcessLogService.saveLog(fileName, fileAbsPath, dataSource, "", DateUtils.formatDate(new Date(), DateUtil.yyyy_MM_dd_HH_mm_ss), "1", "3", "com.yihu.iot.datainput.service.DataInputService.uploadData", DataOperationTypeEnum.upload1.getName(), 1);
        }
        //保存日志
        dataProcessLogService.saveLog(fileName, fileAbsPath, dataSource, "", DateUtils.formatDate(new Date(), DateUtil.yyyy_MM_dd_HH_mm_ss), "1", "4", "com.yihu.iot.datainput.service.DataInputService.uploadData", DataOperationTypeEnum.upload1.getName(), 0);

        return "success";
    }
}
