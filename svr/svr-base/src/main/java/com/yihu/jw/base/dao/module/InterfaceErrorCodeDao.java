package com.yihu.jw.base.dao.module;

import com.yihu.jw.entity.base.module.InterfaceErrorCodeDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 接口错误说明
 * @author yeshijie on 2018/9/28.
 */
public interface InterfaceErrorCodeDao extends PagingAndSortingRepository<InterfaceErrorCodeDO, String>, JpaSpecificationExecutor<InterfaceErrorCodeDO> {

    @Query("from InterfaceErrorCodeDO w where w.interfaceId =?1 and w.del=1")
    List<InterfaceErrorCodeDO> findByInterfaceId(String interfaceId);

    @Modifying
    @Query("delete from InterfaceErrorCodeDO p where p.interfaceId=?1")
    void deleteByInterfaceId(String interfaceId);
}
