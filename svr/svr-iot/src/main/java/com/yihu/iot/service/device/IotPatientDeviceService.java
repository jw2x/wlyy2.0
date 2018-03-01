package com.yihu.iot.service.device;

import com.alibaba.fastjson.JSONObject;
import com.yihu.base.es.config.ElastricSearchHelper;
import com.yihu.base.es.config.model.SaveModel;
import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.iot.dao.device.IotPatientDeviceDao;
import com.yihu.iot.datainput.util.ConstantUtils;
import com.yihu.iot.service.common.ElasticSearchQueryGenerator;
import com.yihu.jw.iot.device.IotPatientDeviceDO;
import com.yihu.jw.iot.device.LocationDataDO;
import com.yihu.jw.restmodel.iot.device.IotPatientDeviceVO;
import com.yihu.jw.restmodel.iot.device.LocationDataVO;
import com.yihu.jw.util.common.LatitudeUtils;
import com.yihu.jw.util.date.DateUtil;
import io.searchbox.client.JestResult;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author yeshijie on 2018/1/16.
 */
@Service
public class IotPatientDeviceService extends BaseJpaService<IotPatientDeviceDO,IotPatientDeviceDao> {

    private Logger logger = LoggerFactory.getLogger(IotPatientDeviceService.class);
    @Autowired
    private IotPatientDeviceDao iotPatientDeviceDao;
    @Autowired
    private ElastricSearchHelper elastricSearchHelper;
    @Autowired
    private ElasticSearchQueryGenerator elasticSearchQueryGenerator;

    /**
     * 新增
     * @param patientDevice
     * @return
     */
    public IotPatientDeviceDO create(IotPatientDeviceDO patientDevice) {

        patientDevice.setSaasId(getCode());
        patientDevice.setDel(1);
        return iotPatientDeviceDao.save(patientDevice);
    }

    /**
     * 设备绑定时 把坐标信息存入es
     * @param deviceVO
     */
    public void deviceData2Es(IotPatientDeviceVO deviceVO) {
        try {
            if (StringUtils.isEmpty(deviceVO.getAddress())) {
                return;
            }
//            List<LocationDataDO> dataDTOs = new ArrayList<>();
            LocationDataDO dataDTO = new LocationDataDO();
            dataDTO.setCreateTime(DateUtil.dateToStrLong(new Date()));
            dataDTO.setDeviceTime(dataDTO.getCreateTime());
            dataDTO.setCategoryCode(deviceVO.getCategoryCode());
            dataDTO.setDeviceSn(deviceVO.getDeviceSn());
            dataDTO.setIdCard(deviceVO.getIdcard());

            Map<String, String> json = LatitudeUtils.getGeocoderLatitude(deviceVO.getAddress().replace("G.", "").replace("（糖友网）", "").replace("（高友网）", ""));
            if (json == null) {
                return;
            }
            logger.info("地址:," + deviceVO.getAddress() + "坐标" + json.toString());
            dataDTO.setLocation(Double.valueOf(json.get("lat")), Double.valueOf(json.get("lng")));
//            dataDTOs.add(dataDTO);
            elastricSearchHelper.save(ConstantUtils.deviceLocationIndex, ConstantUtils.deviceLocationType,JSONObject.toJSONString(dataDTO));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 按id查找
     * @param id
     * @return
     */
    public IotPatientDeviceDO findById(String id) {
        return iotPatientDeviceDao.findOne(id);
    }

    /**
     * 按sn码和按键号查找
     * @param deviceSn
     * @param userType
     * @return
     */
    public IotPatientDeviceDO findByDeviceSnAndUserType(String deviceSn,String userType){
        return iotPatientDeviceDao.findByDeviceSnAndUserType(deviceSn, userType);
    }

    /**
     * 按居民code查找
     * @param patient
     * @return
     */
    public List<IotPatientDeviceDO> findByPatient(String patient){
        return iotPatientDeviceDao.findByPatient(patient);
    }

    /**
     * 按居民和sn码查找
     * @param patient
     * @param deviceSn
     * @return
     */
    public List<IotPatientDeviceDO> findByPatientAndDeviceSn(String patient,String deviceSn){
        return iotPatientDeviceDao.findByPatientAndDeviceSn(patient,deviceSn);
    }

    /**
     * 按居民分页查找
     * @param patient
     * @param pageRequest
     * @return
     */
    public List<IotPatientDeviceDO> findByPatient(String patient, Pageable pageRequest){
        return iotPatientDeviceDao.findByPatient(patient,pageRequest);
    }

    /**
     * 按sn码和设备类型查找
     * @param deviceSn
     * @param categoryCode
     * @return
     */
    public List<IotPatientDeviceDO> findByDeviceSnAndCategoryCode(String deviceSn, String categoryCode){
        return iotPatientDeviceDao.findByDeviceSnAndCategoryCode(deviceSn, categoryCode);
    }

    /**
     * 按sn码，设备类型及按键号查找
     * @param deviceSn
     * @param categoryCode
     * @param userType
     * @return
     */
    public IotPatientDeviceDO findByDeviceSnAndCategoryCodeAndUserType(String deviceSn, String categoryCode, String userType){
        return iotPatientDeviceDao.findByDeviceSnAndCategoryCodeAndUserType(deviceSn, categoryCode, userType);
    }

    /**
     * 更换患者绑定的血糖仪
     * @param patient
     * @param deviceSN
     * @param newDeviceSN
     * @param userType
     * @param sim
     * @return
     */
    public int updatePatientDevice(String patient, String deviceSN, String newDeviceSN,String userType,String sim){
        return iotPatientDeviceDao.updatePatientDevice(patient, deviceSN, newDeviceSN, userType, sim);
    }


    /**
     * 查询所有设备地址
     * @return
     */
    public List<LocationDataVO> findAllDeviceLocations(){
        SearchSourceBuilder queryStr = elasticSearchQueryGenerator.getQueryAllBuilder();
        JestResult esResult = elastricSearchHelper.search(ConstantUtils.deviceLocationIndex,ConstantUtils.deviceLocationType,queryStr.toString());
        return getESResultBeanList(esResult);
    }

    /**
     * 根据居民绑定的idCard查询设备地址
     * @return
     */
    public List<LocationDataVO> findDeviceLocationsByIdCard(String jsonData){
        SearchSourceBuilder queryStr = elasticSearchQueryGenerator.getQueryBuilder("",jsonData);
        JestResult esResult = elastricSearchHelper.search(ConstantUtils.deviceLocationIndex,ConstantUtils.deviceLocationType,queryStr.toString());
        return getESResultBeanList(esResult);
    }

    /**
     * 根据设备SN码查询设备地址
     * @return
     */
    public List<LocationDataVO> findDeviceLocationsBySn(String jsonData){
        SearchSourceBuilder queryStr = elasticSearchQueryGenerator.getQueryBuilder("",jsonData);
        JestResult esResult = elastricSearchHelper.search(ConstantUtils.deviceLocationIndex,ConstantUtils.deviceLocationType,queryStr.toString());
        return getESResultBeanList(esResult);
    }

    /**
     * 返回对象集合
     * @param esResult
     * @return
     */
    private List<LocationDataVO> getESResultBeanList(JestResult esResult){
        List<LocationDataVO> resultList = new ArrayList<>();
        if(!esResult.isSucceeded()){
            return resultList;
        }
        return esResult.getSourceAsObjectList(LocationDataVO.class);
    }

    /**
     * 设备解绑，根据Sn或idCard删除地址
     * @return
     */
    public boolean deleteLocationsByIdcardOrSn(String jsonData){
        List<SaveModel> saveModelList = new ArrayList<>();
        SearchSourceBuilder queryStr = elasticSearchQueryGenerator.getQueryBuilder("",jsonData);
        JestResult esResult = elastricSearchHelper.search(ConstantUtils.deviceLocationIndex,ConstantUtils.deviceLocationType,queryStr.toString());
        List<LocationDataVO> resultList = getESResultBeanList(esResult);
        for(LocationDataVO locationDataVO : resultList){
            SaveModel saveModel = new SaveModel();
            saveModel.setId(locationDataVO.getId());
            saveModelList.add(saveModel);
        }
        boolean bool = true;
        try {
            elastricSearchHelper.deleteData(ConstantUtils.deviceLocationIndex,ConstantUtils.deviceLocationType,saveModelList);
        }catch (Exception e){
            bool = false;
        }
//        JestResult deleteResult = elastricSearchHelper.deleteData(ConstantUtils.deviceLocationIndex,ConstantUtils.deviceLocationType,saveModelList);
//        return deleteResult.isSucceeded();
        return bool;
    }

}
