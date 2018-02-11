package com.yihu.iot.dao.device;

import com.yihu.jw.iot.device.IotPatientDeviceDO;
import com.yihu.jw.iot.device.IotQualityRecordDO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author yeshijie on 2018/1/16.
 */
public interface IotPatientDeviceDao extends PagingAndSortingRepository<IotPatientDeviceDO, String>,
        JpaSpecificationExecutor<IotPatientDeviceDO> {


    @Query("from IotPatientDeviceDO a where a.patient = ?1 and a.del=1 ")
    List<IotPatientDeviceDO> findByPatient(String patient);

    @Query("from IotPatientDeviceDO a where a.patient = ?1 and a.deviceSn=?2 and a.del=1")
    List<IotPatientDeviceDO> findByPatientAndDeviceSn(String patient,String deviceSn);

    @Query("from IotPatientDeviceDO a where a.patient=?1 and a.del=1 ")
    List<IotPatientDeviceDO> findByPatient(String patient, Pageable pageRequest);

    @Query("from IotPatientDeviceDO a where a.categoryCode = ?2 and a.deviceSn=?1 and a.del=1 ")
    List<IotPatientDeviceDO> findByDeviceSnAndCategoryCode(String deviceSn, String categoryCode);

    @Query("from IotPatientDeviceDO a where a.patient = ?1 and a.deviceSn=?2 and a.del=1 ")
    IotPatientDeviceDO findByDeviceSnAndCategoryCodeAndUserType(String deviceSn, String categoryCode, String userType);

    @Query("from IotPatientDeviceDO a where a.userType = ?2 and a.deviceSn=?1 and a.del=1 ")
    IotPatientDeviceDO findByDeviceSnAndUserType(String deviceSn, String userType);

    //更换患者绑定的血糖仪
    @Modifying
    @Query("update IotPatientDeviceDO t set t.deviceSn = ?3 , t.userType = ?4,t.sim=?5 where t.patient = ?1 and t.deviceSn = ?2 and t.del=1 ")
    int updatePatientDevice(String patient, String deviceSN, String newDeviceSN,String userType,String sim);
}
