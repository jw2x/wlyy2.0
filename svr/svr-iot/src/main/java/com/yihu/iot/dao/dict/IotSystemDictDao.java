package com.yihu.iot.dao.dict;

import com.yihu.jw.entity.iot.dict.IotSystemDictDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author yeshijie on 2018/1/16.
 */
public interface IotSystemDictDao extends PagingAndSortingRepository<IotSystemDictDO,String>,
        JpaSpecificationExecutor<IotSystemDictDO> {

    @Query("from IotSystemDictDO w where w.dictName =?1 and w.del = 1 ORDER BY sort")
    List<IotSystemDictDO> findByDictName(String dictName);
    
}
