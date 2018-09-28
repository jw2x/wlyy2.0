package com.yihu.jw.base.dao.module;

import com.yihu.jw.entity.base.module.ModuleDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Dao - 模块
 * Created by chenweida on 2017/5/19.
 */
public interface ModuleDao extends PagingAndSortingRepository<ModuleDO, String>, JpaSpecificationExecutor<ModuleDO> {

    @Modifying
    @Query("update ModuleDO p set p.status=?2 where p.id=?1")
    void updateStatus(String id,Integer status);

    @Query("select count(*) from ModuleDO a where a.name = ?1 ")
    int isExistName(String name);
}
