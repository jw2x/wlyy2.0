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
}
