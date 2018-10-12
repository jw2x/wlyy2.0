package com.yihu.jw.base.dao.module;

import com.yihu.jw.entity.base.module.SaasInterfaceDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 租户接口
 * @author yeshijie on 2018/10/11.
 */
public interface SaasInterfaceDao extends PagingAndSortingRepository<SaasInterfaceDO, String>, JpaSpecificationExecutor<SaasInterfaceDO> {

    @Modifying
    @Query("update SaasInterfaceDO p set p.status=?2 where p.id=?1")
    void updateStatus(String id,Integer status);

    @Modifying
    @Query("update SaasInterfaceDO p set p.status=?2 where p.interfaceId=?1")
    void updateStatusByInterfaceId(String interfaceId,Integer status);
}
