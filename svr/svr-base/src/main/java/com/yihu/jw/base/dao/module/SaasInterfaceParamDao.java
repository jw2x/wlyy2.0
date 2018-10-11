package com.yihu.jw.base.dao.module;

import com.yihu.jw.entity.base.module.InterfaceParamDO;
import com.yihu.jw.entity.base.module.SaasInterfaceParamDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 接口入参出参
 * @author yeshijie on 2018/10/11.
 */
public interface SaasInterfaceParamDao extends PagingAndSortingRepository<SaasInterfaceParamDO, String>,
        JpaSpecificationExecutor<SaasInterfaceParamDO> {

    @Query("from SaasInterfaceParamDO w where w.saasInterfaceId =?1 and w.del=1")
    List<SaasInterfaceParamDO> findBySaasInterfaceId(String saasInterfaceId);

    @Modifying
    @Query("delete from SaasInterfaceParamDO p where p.saasInterfaceId=?1")
    void deleteBySaasInterfaceId(String saasInterfaceId);
}
