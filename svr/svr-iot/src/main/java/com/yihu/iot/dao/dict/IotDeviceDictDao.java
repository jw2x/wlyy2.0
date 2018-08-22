package com.yihu.iot.dao.dict;

import com.yihu.jw.entity.iot.dict.IotDeviceDictDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author yeshijie on 2017/12/1.
 */
public interface IotDeviceDictDao extends PagingAndSortingRepository<IotDeviceDictDO,String>,
        JpaSpecificationExecutor<IotDeviceDictDO> {

    @Query("from IotDeviceDictDO w where w.id =?1")
    IotDeviceDictDO findById(String id);
}
