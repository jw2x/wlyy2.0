package com.yihu.iot.service.device;

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.iot.dao.device.IotDeviceDao;
import com.yihu.jw.iot.device.IotDeviceDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yeshijie on 2017/12/8.
 */
@Service
public class IotDeviceService extends BaseJpaService<IotDeviceDO,IotDeviceDao> {

    @Autowired
    private IotDeviceDao iotDeviceDao;

    public IotDeviceDO findById(String id) {
        return iotDeviceDao.findById(id);
    }
}
