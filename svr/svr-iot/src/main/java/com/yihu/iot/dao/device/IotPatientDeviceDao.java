package com.yihu.iot.dao.device;

import com.yihu.jw.iot.device.IotQualityRecordDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author yeshijie on 2018/1/16.
 */
public interface IotPatientDeviceDao extends PagingAndSortingRepository<IotQualityRecordDO, String>,
        JpaSpecificationExecutor<IotQualityRecordDO> {


}
