package com.yihu.jw.base.dao.module;

import com.yihu.jw.entity.base.module.SaasTypeModuleDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

/**
 * Entity - 租户类型模块
 * Created by yeshijie on 2018/10/11.
 */
public interface SaasTypeModuleDao extends PagingAndSortingRepository<SaasTypeModuleDO, String>, JpaSpecificationExecutor<SaasTypeModuleDO> {

    @Query("select count(*) from SaasTypeModuleDO a where a.moduleId = ?1 ")
    int isExistModule(String moduleId);

    @Modifying
    @Query("delete from SaasTypeModuleDO p where p.moduleId=?1 ")
    void deleteByModuleId(String moduleId);

    @Modifying
    @Transactional
    @Query("delete from SaasTypeModuleDO p where p.saasTypeId=?1 ")
    void deleteBySaasTypeId(String saasTypeId);
}