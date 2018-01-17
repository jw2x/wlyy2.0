package com.yihu.iot.dao.dict;

import com.yihu.jw.iot.dict.IotSystemDictDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author yeshijie on 2018/1/16.
 */
public interface IotSystemDictDao extends PagingAndSortingRepository<IotSystemDictDO,String>,
        JpaSpecificationExecutor<IotSystemDictDO> {

    
}
