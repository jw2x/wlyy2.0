package com.yihu.iot.service.supplier;

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.iot.dao.supplier.IotSupplyDeviceDataTypeDao;
import com.yihu.jw.iot.supplier.IotSupplyDeviceDataTypeDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yeshijie on 2017/12/8.
 */
@Service
public class IotSupplyDeviceDataTypeService extends BaseJpaService<IotSupplyDeviceDataTypeDO,IotSupplyDeviceDataTypeDao> {
    @Autowired
    private IotSupplyDeviceDataTypeDao iotSupplyDeviceDataTypeDao;

    public IotSupplyDeviceDataTypeDO findById(String id) {
        return iotSupplyDeviceDataTypeDao.findById(id);
    }
}
