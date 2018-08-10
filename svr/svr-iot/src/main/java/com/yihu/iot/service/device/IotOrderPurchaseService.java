package com.yihu.iot.service.device;

import com.yihu.iot.dao.device.IotOrderPurchaseDao;
import com.yihu.jw.iot.device.IotOrderPurchaseDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yeshijie on 2017/12/8.
 */
@Service
public class IotOrderPurchaseService extends BaseJpaService<IotOrderPurchaseDO,IotOrderPurchaseDao> {
    @Autowired
    private IotOrderPurchaseDao iotDevicePurchaseDao;

    /**
     * 新增
     * @param iotDevicePurchase
     * @return
     */
    public IotOrderPurchaseDO create(IotOrderPurchaseDO iotDevicePurchase) {

        iotDevicePurchase.setSaasId(getCode());
        iotDevicePurchase.setDel(1);
        return iotDevicePurchaseDao.save(iotDevicePurchase);
    }

    public IotOrderPurchaseDO findById(String id) {
        return iotDevicePurchaseDao.findById(id);
    }
}
