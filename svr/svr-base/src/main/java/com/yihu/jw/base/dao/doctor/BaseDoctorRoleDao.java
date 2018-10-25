package com.yihu.jw.base.dao.doctor;

import com.yihu.jw.entity.base.doctor.BaseDoctorRoleDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Set;


/**
 * 
 * 医生与业务模块角色关联信息 数据库访问层
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年10月25日 	Created
 *
 * </pre>
 * @since 1.
 */
public interface BaseDoctorRoleDao extends PagingAndSortingRepository<BaseDoctorRoleDO, Integer>, JpaSpecificationExecutor<BaseDoctorRoleDO>  {

    @Query("select id from BaseDoctorRoleDO where doctorCode = ?1")
    Set<Object> findIdListByDoctorCode(String doctorCode);

    List<BaseDoctorRoleDO> findByDoctorCode(String doctorCode);
}
