package com.yihu.iot.service.device;

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.iot.dao.device.IotDeviceQualityInspectionPlanDao;
import com.yihu.jw.iot.device.IotDeviceQualityInspectionPlanDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yeshijie on 2017/12/8.
 */
@Service
public class IotDeviceQualityInspectionPlanService extends BaseJpaService<IotDeviceQualityInspectionPlanDO,IotDeviceQualityInspectionPlanDao> {

    @Autowired
    private IotDeviceQualityInspectionPlanDao iotDeviceQualityInspectionPlanDao;

    public IotDeviceQualityInspectionPlanDO findById(String id) {
        return iotDeviceQualityInspectionPlanDao.findById(id);
    }
}
