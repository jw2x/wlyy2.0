package com.yihu.jw.base.dao;

import com.yihu.jw.base.model.Saas;
import com.yihu.jw.base.model.SaasModule;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chenweida on 2017/5/22.
 */
public interface SaasModuleDao extends PagingAndSortingRepository<SaasModule, Long>, JpaSpecificationExecutor<SaasModule> {
    @Modifying
    @Query("delete from SaasModule es where es.saasId = ?1")
    int deleteBySaasCode(String saasCode);
}
