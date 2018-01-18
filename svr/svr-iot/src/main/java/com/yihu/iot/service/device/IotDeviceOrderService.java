package com.yihu.iot.service.device;

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.iot.dao.device.IotDeviceOrderDao;
import com.yihu.jw.iot.device.IotDeviceOrderDO;
import com.yihu.jw.util.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

        String time = DateUtil.dateToStr(new Date(),DateUtil.YYYYMMDD);
        List<IotDeviceOrderDO> doList = iotDeviceOrderDao.findByYmd(time);
        iotDeviceOrder.setOrderNo(String.format("",doList.size()));
        iotDeviceOrder.setSaasId(getCode());
        iotDeviceOrder.setDel(1);
        iotDeviceOrder.setYmd(time);
        return iotDeviceOrderDao.save(iotDeviceOrder);
    }

    public IotDeviceOrderDO findById(String id) {
        return iotDeviceOrderDao.findById(id);
    }

    public static void main(String[] args) {
        System.out.println(String.format("%05d", 5));
    }
}
