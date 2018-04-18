package com.yihu.iot.dao.device;

import com.yihu.jw.iot.device.IotDeviceQualityInspectionPlanDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author yeshijie on 2017/12/1.
 */
public interface IotDeviceQualityInspectionPlanDao extends PagingAndSortingRepository<IotDeviceQualityInspectionPlanDO,String>,
        JpaSpecificationExecutor<IotDeviceQualityInspectionPlanDO> {

    @Query("from IotDeviceQualityInspectionPlanDO w where w.id =?1")
    IotDeviceQualityInspectionPlanDO findById(String id);

    @Query("from IotDeviceQualityInspectionPlanDO w where w.purchaseId =?1 and w.status=?2 and w.del=1 ORDER BY w.planTime desc ")
    List<IotDeviceQualityInspectionPlanDO> findListByPurchaseId(String purchaseId,String status);

    @Query("from IotDeviceQualityInspectionPlanDO w where w.deviceId =?1")
    IotDeviceQualityInspectionPlanDO findByDeviceId(String deviceId);

    @Query(value = "SELECT a.* from iot_device_quality_inspection_plan a WHERE a.purchase_id = ?1 and a.del=1 ORDER BY a.status asc,a.plan_time desc limit 1",nativeQuery = true)
    IotDeviceQualityInspectionPlanDO findLastByPurchaseId(String purchaseId);

    @Query(value = "SELECT a.* from iot_device_quality_inspection_plan a WHERE a.purchase_id = ?1 and a.status=?2 and a.del=1 ORDER BY a.plan_time desc limit 1",nativeQuery = true)
    IotDeviceQualityInspectionPlanDO findLastByPurchaseId(String purchaseId,String status);

    @Query(value = "SELECT a.* from iot_device_quality_inspection_plan a WHERE a.purchase_id = ?1 and a.status=?2 and a.del=1 ORDER BY a.plan_time asc limit 1",nativeQuery = true)
    IotDeviceQualityInspectionPlanDO findFirstByPurchaseId(String purchaseId,String status);
}
