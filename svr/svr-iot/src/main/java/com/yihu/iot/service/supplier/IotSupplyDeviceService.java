package com.yihu.iot.service.supplier;

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.iot.dao.supplier.IotSupplyDeviceDao;
import com.yihu.jw.iot.supplier.IotSupplyDeviceDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;

/**
 * @author yeshijie on 2017/12/8.
 */
@Service
public class IotSupplyDeviceService extends BaseJpaService<IotSupplyDeviceDO,IotSupplyDeviceDao> {

    @Autowired
    private IotSupplyDeviceDao iotSupplyDeviceDao;

    public IotSupplyDeviceDO findById(String id) {
        return iotSupplyDeviceDao.findById(id);
    }

    @Transient
    public IotSupplyDeviceDO create(IotSupplyDeviceDO iotSupplyDeviceDO) {


        iotSupplyDeviceDO.setSaasId(getCode());
        iotSupplyDeviceDO.setDel(1);
        return iotSupplyDeviceDao.save(iotSupplyDeviceDO);
    }
}
