package com.yihu.iot.dao.label;

import com.yihu.jw.iot.label.IotDeviceLabelInfoDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author yeshijie on 2018/1/4.
 */
public interface IotDeviceLabelInfoDao extends PagingAndSortingRepository<IotDeviceLabelInfoDO,String>,JpaSpecificationExecutor<IotDeviceLabelInfoDO> {

    @Query("from IotDeviceLabelInfoDO w where w.id =?1")
    IotDeviceLabelInfoDO findById(String id);
}
