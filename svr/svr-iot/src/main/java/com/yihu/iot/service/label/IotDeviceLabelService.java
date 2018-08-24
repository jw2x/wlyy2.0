package com.yihu.iot.service.label;

import com.yihu.iot.dao.label.IotDeviceLabelDao;
import com.yihu.jw.entity.iot.label.IotDeviceLabelDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yeshijie on 2018/1/4.
 */
@Service
public class IotDeviceLabelService extends BaseJpaService<IotDeviceLabelDO,IotDeviceLabelDao> {

    @Autowired
    private IotDeviceLabelDao iotDeviceLabelDao;

    public IotDeviceLabelDO findById(String id) {
        return iotDeviceLabelDao.findById(id);
    }

    /**
     * 新增
     * @param iotDeviceLabel
     * @return
     */
    public IotDeviceLabelDO create(IotDeviceLabelDO iotDeviceLabel) {

        iotDeviceLabel.setSaasId(getCode());
        return iotDeviceLabelDao.save(iotDeviceLabel);
    }

}
