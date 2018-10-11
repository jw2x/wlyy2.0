package com.yihu.jw.base.dao.module;

import com.yihu.jw.entity.base.module.InterfaceErrorCodeDO;
import com.yihu.jw.entity.base.module.SaasInterfaceErrorCodeDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 接口错误说明
 * @author yeshijie on 2018/10/11.
 */
public interface SaasInterfaceErrorCodeDao extends PagingAndSortingRepository<SaasInterfaceErrorCodeDO, String>,
        JpaSpecificationExecutor<SaasInterfaceErrorCodeDO> {

    @Query("from SaasInterfaceErrorCodeDO w where w.saasInterfaceId =?1 and w.del=1")
    List<SaasInterfaceErrorCodeDO> findBySaasInterfaceId(String saasInterfaceId);

    @Modifying
    @Query("delete from SaasInterfaceErrorCodeDO p where p.saasInterfaceId=?1")
    void deleteBySaasInterfaceId(String saasInterfaceId);
}
