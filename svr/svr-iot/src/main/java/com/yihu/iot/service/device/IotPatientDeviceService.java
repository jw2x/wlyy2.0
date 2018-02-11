package com.yihu.iot.service.device;

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.iot.dao.device.IotPatientDeviceDao;
import com.yihu.jw.iot.device.IotPatientDeviceDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yeshijie on 2018/1/16.
 */
@Service
public class IotPatientDeviceService extends BaseJpaService<IotPatientDeviceDO,IotPatientDeviceDao> {

    @Autowired
    private IotPatientDeviceDao iotPatientDeviceDao;

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

}
