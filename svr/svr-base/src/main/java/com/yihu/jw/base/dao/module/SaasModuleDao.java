package com.yihu.jw.base.dao.module;

import com.yihu.jw.entity.base.module.SaasModuleDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Entity - 租户模块
 * Created by yeshijie on 2018/10/11.
 */
public interface SaasModuleDao extends PagingAndSortingRepository<SaasModuleDO, String>, JpaSpecificationExecutor<SaasModuleDO> {

    @Query("select count(*) from SaasModuleDO a where a.moduleId = ?1 ")
    int isExistModule(String moduleId);

    @Modifying
    @Query("delete from SaasModuleDO p where p.moduleId=?1 ")
    void deleteByModuleId(String moduleId);

    List<SaasModuleDO> findByModuleId(String moduleId);
}