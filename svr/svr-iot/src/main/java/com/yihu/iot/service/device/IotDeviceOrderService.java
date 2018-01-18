package com.yihu.iot.service.device;

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.iot.dao.device.IotDeviceOrderDao;
import com.yihu.jw.iot.device.IotDeviceOrderDO;
import com.yihu.jw.util.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yeshijie on 2017/12/8.
 */
@Service
public class IotDeviceOrderService extends BaseJpaService<IotDeviceOrderDO,IotDeviceOrderDao> {

    @Autowired
    private IotDeviceOrderDao iotDeviceOrderDao;

    /**
     * 新增
     * @param iotDeviceOrder
     * @return
     */
    public IotDeviceOrderDO create(IotDeviceOrderDO iotDeviceOrder) {

        String time = DateUtil.yyyy_MM_dd_HH_mm_ss;
        iotDeviceOrder.setSaasId(getCode());
        iotDeviceOrder.setDel(1);
        return iotDeviceOrderDao.save(iotDeviceOrder);
    }

    public IotDeviceOrderDO findById(String id) {
        return iotDeviceOrderDao.findById(id);
    }
}
