package com.yihu.jw.base.dao;

import com.yihu.jw.entity.base.role.RoleModuleFunctionDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Dao - 角色模块功能
 * Created by progr1mmer on 2018/8/17.
 */
public interface RoleModuleFunctionDao extends PagingAndSortingRepository<RoleModuleFunctionDO, String>, JpaSpecificationExecutor<RoleModuleFunctionDO> {

}
