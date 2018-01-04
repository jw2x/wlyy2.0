package com.yihu.iot.service.device;

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.iot.dao.device.IotDeviceDao;
import com.yihu.jw.iot.device.IotDeviceDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yeshijie on 2017/12/8.
 */
@Service
public class IotDeviceService extends BaseJpaService<IotDeviceDO,IotDeviceDao> {

    @Autowired
    private IotDeviceDao iotDeviceDao;

    public IotDeviceDO findById(String id) {
        return iotDeviceDao.findById(id);
    }

    /**
     * 设备注册及绑定
     * @param iotDeviceDO
     */
    public void bindUser(List<IotDeviceDO> iotDeviceDO){
        this.batchInsert(iotDeviceDO);
    }

    /**
     * 根据设备序列号判断设备是否存在
     * @param deviceSn
     * @return
     */
    public int countByDeviceSn(String deviceSn) {
        return iotDeviceDao.countByDeviceSn(deviceSn);
    }

    /**
     * 根据设备序列号查找设备
     * @param deviceSn
     * @return
     */
    public IotDeviceDO findByDeviceSn(String deviceSn) {
        return iotDeviceDao.findByDeviceSn(deviceSn);
    }
}
