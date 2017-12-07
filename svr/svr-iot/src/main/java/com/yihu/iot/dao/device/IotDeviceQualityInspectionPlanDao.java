package com.yihu.iot.dao.device;

import com.yihu.jw.iot.device.IotDeviceQualityInspectionPlanDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author yeshijie on 2017/12/1.
 */
public interface IotDeviceQualityInspectionPlanDao extends PagingAndSortingRepository<IotDeviceQualityInspectionPlanDO
        ,Long>,JpaSpecificationExecutor<IotDeviceQualityInspectionPlanDO> {


}
