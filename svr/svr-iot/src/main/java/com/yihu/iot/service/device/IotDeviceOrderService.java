package com.yihu.iot.service.device;

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.iot.dao.device.IotDeviceOrderDao;
import com.yihu.jw.iot.device.IotDeviceOrderDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yeshijie on 2017/12/8.
 */
@Service
public class IotDeviceOrderService extends BaseJpaService<IotDeviceOrderDO,IotDeviceOrderDao> {

    @Autowired
    private IotDeviceOrderDao iotDeviceOrderDao;

    public IotDeviceOrderDO findById(String id) {
        return iotDeviceOrderDao.findById(id);
    }
}
