package com.yihu.iot.dao.device;

import com.yihu.jw.iot.device.IotDeviceQualityInspectionPlanDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author yeshijie on 2017/12/1.
 */
public interface IotDeviceQualityInspectionPlanDao extends PagingAndSortingRepository<IotDeviceQualityInspectionPlanDO,String>,
        JpaSpecificationExecutor<IotDeviceQualityInspectionPlanDO> {

    @Query("from IotDeviceQualityInspectionPlanDO w where w.id =?1")
    IotDeviceQualityInspectionPlanDO findById(String id);

    @Query(value = "SELECT a.* from iot_device_quality_inspection_plan a WHERE a.purchase_id = ?1 and a.del=1 ORDER BY a.status asc,a.plan_time desc limit 1",nativeQuery = true)
    IotDeviceQualityInspectionPlanDO findLastByPurchaseId(String purchaseId);
}
