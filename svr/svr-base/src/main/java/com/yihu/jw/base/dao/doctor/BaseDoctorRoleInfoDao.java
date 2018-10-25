package com.yihu.jw.base.dao.doctor;

import com.yihu.jw.entity.base.doctor.BaseDoctorRoleInfoDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * 
 * 业务模块角色字典（给医生用的） 数据库访问层
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年10月25日 	Created
 *
 * </pre>
 * @since 1.
 */
public interface BaseDoctorRoleInfoDao extends PagingAndSortingRepository<BaseDoctorRoleInfoDO, Integer>, JpaSpecificationExecutor<BaseDoctorRoleInfoDO>  {
}