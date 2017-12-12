package com.yihu.iot.service.supplier;

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.iot.dao.supplier.IotDeviceSupplierDao;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.exception.code.ExceptionCode;
import com.yihu.jw.iot.supplier.IotDeviceSupplierDO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.Transient;
import java.util.Date;

/**
 * @author yeshijie on 2017/12/5.
 */
@Service
public class IotDeviceSupplierService extends BaseJpaService<IotDeviceSupplierDO,IotDeviceSupplierDao>{

    @Autowired
    private IotDeviceSupplierDao iotDeviceSupplierDao;

    @Transient
    public IotDeviceSupplierDO create(IotDeviceSupplierDO iotDeviceSupplier) {
        String saasId = iotDeviceSupplier.getSaasId();
        if (StringUtils.isEmpty(saasId)) {
            throw new ApiException(IotRequestMapping.DeviceSupplier.message_fail_saasId_is_null, ExceptionCode.common_error_params_code);
        }
        //Saas saas = saasService.findByCode(saasId);
        //if(saas==null){
        //    throw new ApiException(BaseContants.Saas.message_fail_code_no_exist, ExceptionCode.common_error_params_code);
        //}
        //设置创建时间
        Date date = new Date();
        iotDeviceSupplier.setCreateTime(date);
        return iotDeviceSupplierDao.save(iotDeviceSupplier);
    }

    public IotDeviceSupplierDO findById(String id) {
        return iotDeviceSupplierDao.findById(id);
    }
}
