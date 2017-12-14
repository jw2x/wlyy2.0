package com.yihu.iot.service.device;

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.iot.dao.device.IotDevicePurchaseDao;
import com.yihu.jw.iot.device.IotDevicePurchaseDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yeshijie on 2017/12/8.
 */
@Service
public class IotDevicePurchaseService extends BaseJpaService<IotDevicePurchaseDO,IotDevicePurchaseDao> {
    @Autowired
    private IotDevicePurchaseDao iotDevicePurchaseDao;

    public IotDevicePurchaseDO findById(String id) {
        return iotDevicePurchaseDao.findById(id);
    }
}
