package com.yihu.iot.service.label;

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.iot.dao.label.IotDeviceLabelInfoDao;
import com.yihu.jw.iot.label.IotDeviceLabelInfoDO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yeshijie on 2018/1/4.
 */
public class IotDeviceLabelInfoService extends BaseJpaService<IotDeviceLabelInfoDO,IotDeviceLabelInfoDao> {

    @Autowired
    private IotDeviceLabelInfoDao iotDeviceLabelInfoDao;

    public IotDeviceLabelInfoDO findById(String id) {
        return iotDeviceLabelInfoDao.findById(id);
    }

    /**
     * 新增
     * @param iotDeviceLabelInfo
     * @return
     */
    public IotDeviceLabelInfoDO create(IotDeviceLabelInfoDO iotDeviceLabelInfo) {

        iotDeviceLabelInfo.setSaasId(getCode());
        return iotDeviceLabelInfoDao.save(iotDeviceLabelInfo);
    }


}