package com.yihu.iot.dao.label;

import com.yihu.jw.entity.iot.label.IotDeviceLabelDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author yeshijie on 2018/1/4.
 */
public interface IotDeviceLabelDao extends PagingAndSortingRepository<IotDeviceLabelDO,String>,JpaSpecificationExecutor<IotDeviceLabelDO> {

    @Query("from IotDeviceLabelDO w where w.id =?1")
    IotDeviceLabelDO findById(String id);
}
