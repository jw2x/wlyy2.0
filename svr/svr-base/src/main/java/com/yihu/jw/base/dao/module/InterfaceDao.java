package com.yihu.jw.base.dao.module;

import com.yihu.jw.entity.base.module.InterfaceDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 接口
 * @author yeshijie on 2018/9/28.
 */

public interface InterfaceDao extends PagingAndSortingRepository<InterfaceDO, String>, JpaSpecificationExecutor<InterfaceDO> {

    @Modifying
    @Query("update InterfaceDO p set p.status=?2 where p.id=?1")
    void updateStatus(String id,Integer status);
}
