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

    @Query(value = "select org_code as orgCode,org_name as orgName,doctor_duty_code as doctorDutyCode ,doctor_duty_name as doctorDutyName from base_doctor_hospital where doctor_code = ?1 GROUP BY orgCode ORDER BY orgName DESC",nativeQuery = true)
    List<Map<String,Object>> getOrgAndDutyByDoctorCode(String doctorCode);
}