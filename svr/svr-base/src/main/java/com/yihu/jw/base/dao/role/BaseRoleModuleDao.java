package com.yihu.jw.base.dao.role;

import com.yihu.jw.entity.base.role.BaseRoleModuleDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * 
 * 业务模块角色 数据库访问层
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年10月23日 	Created
 *
 * </pre>
 * @since 1.
 */
public interface BaseRoleModuleDao extends PagingAndSortingRepository<BaseRoleModuleDO, Integer>, JpaSpecificationExecutor<BaseRoleModuleDO>  {
}