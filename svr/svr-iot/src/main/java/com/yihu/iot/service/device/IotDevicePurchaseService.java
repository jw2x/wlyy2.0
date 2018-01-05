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

    /**
     * 新增
     * @param iotDevicePurchase
     * @return
     */
    public IotDevicePurchaseDO create(IotDevicePurchaseDO iotDevicePurchase) {

        iotDevicePurchase.setSaasId(getCode());
        iotDevicePurchase.setDel(1);
        return iotDevicePurchaseDao.save(iotDevicePurchase);
    }

    public IotDevicePurchaseDO findById(String id) {
        return iotDevicePurchaseDao.findById(id);
    }
}
