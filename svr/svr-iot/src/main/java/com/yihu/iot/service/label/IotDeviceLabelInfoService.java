package com.yihu.iot.service.label;

import com.yihu.iot.dao.label.IotDeviceLabelInfoDao;
import com.yihu.jw.iot.label.IotDeviceLabelInfoDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yeshijie on 2018/1/4.
 */
@Service
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