package com.yihu.iot.service.dict;

import com.yihu.iot.dao.dict.IotDeviceDictDao;
import com.yihu.jw.entity.iot.dict.IotDeviceDictDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yeshijie on 2017/12/8.
 */
@Service
public class IotDeviceDictService extends BaseJpaService<IotDeviceDictDO,IotDeviceDictDao> {

    @Autowired
    private IotDeviceDictDao iotDeviceDictDao;

    public IotDeviceDictDO findById(String id) {
        return iotDeviceDictDao.findById(id);
    }

}
