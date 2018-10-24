package com.yihu.jw.base.dao.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.yihu.jw.entity.base.doctor.BaseDoctorHospitalDO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * 医生职业信息 数据库访问层
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年08月31日 	Created
 *
 * </pre>
 * @since 1.
 */
public interface BaseDoctorHospitalDao extends PagingAndSortingRepository<BaseDoctorHospitalDO, Integer>, JpaSpecificationExecutor<BaseDoctorHospitalDO>  {

    List<BaseDoctorHospitalDO> findByOrgCodeAndDoctorCode(String orgCode, String doctorCode);

    @Query("select id from BaseDoctorHospitalDO where doctorCode = ?1")
    Set<Object> findIdListByOrgCodeAndDoctorCode(String doctorCode);

    @Query("select new BaseDoctorHospitalDO(orgCode,orgName,doctorDutyCode,doctorDutyName) from BaseDoctorHospitalDO where doctorCode = ?1")
    List<BaseDoctorHospitalDO> getOrgAndDutyByDoctorCode(String doctorCode);
}