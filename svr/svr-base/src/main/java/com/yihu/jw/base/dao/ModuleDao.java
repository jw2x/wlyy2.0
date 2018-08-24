package com.yihu.jw.base.dao;

import com.yihu.jw.entity.base.module.ModuleDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Dao - 模块
 * Created by chenweida on 2017/5/19.
 */
public interface ModuleDao extends PagingAndSortingRepository<ModuleDO, String>, JpaSpecificationExecutor<ModuleDO> {

}
