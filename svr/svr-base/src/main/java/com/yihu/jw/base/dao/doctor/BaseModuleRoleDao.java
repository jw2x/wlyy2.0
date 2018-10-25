package com.yihu.jw.base.dao.doctor;

import com.yihu.jw.entity.base.doctor.BaseModuleRoleDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * 
 * 业务模块与业务模块角色关联信息 数据库访问层
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年10月25日 	Created
 *
 * </pre>
 * @since 1.
 */
public interface BaseModuleRoleDao extends PagingAndSortingRepository<BaseModuleRoleDO, Integer>, JpaSpecificationExecutor<BaseModuleRoleDO>  {
}