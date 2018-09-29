package com.yihu.jw.base.dao.module;

import com.yihu.jw.entity.base.module.InterfaceParamDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 接口入参出参
 * @author yeshijie on 2018/9/28.
 */
public interface InterfaceParamDao extends PagingAndSortingRepository<InterfaceParamDO, String>, JpaSpecificationExecutor<InterfaceParamDO> {

    @Query("from InterfaceParamDO w where w.interfaceId =?1 and w.del=1")
    List<InterfaceParamDO> findByInterfaceId(String interfaceId);

    @Modifying
    @Query("delete from InterfaceParamDO p where p.interfaceId=?1")
    void deleteByInterfaceId(String interfaceId);
}
