package com.yihu.jw.base.dao.doctor;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.yihu.jw.entity.base.doctor.BaseDoctorRoleDictDO;

import java.util.Set;

/**
 * 
 * 医生角色字典 数据库访问层
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年08月31日 	Created
 *
 * </pre>
 * @since 1.
 */
public interface BaseDoctorRoleDictDao extends PagingAndSortingRepository<BaseDoctorRoleDictDO, Integer>, JpaSpecificationExecutor<BaseDoctorRoleDictDO>  {

    @Query("select id from BaseDoctorHospitalDO where doctorCode = ?1")
    Set<Object> findIdListByHospCodeAndDoctorCode(String doctorCode);

}