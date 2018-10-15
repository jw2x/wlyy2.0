package com.yihu.jw.base.dao.doctor;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.yihu.jw.entity.base.doctor.BaseDoctorDO;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;

/**
 * 
 * 医生基础信息 数据库访问层
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年08月31日 	Created
 *
 * </pre>
 * @since 1.
 */
public interface BaseDoctorDao extends PagingAndSortingRepository<BaseDoctorDO, String>, JpaSpecificationExecutor<BaseDoctorDO>  {

    @Query("select doc.id,doc.name,doc.idcard,case doc.sex when 1 then '男' when 2 then '女' else '未知' end as sex,doc.del as status,hos.hospName,hos.deptName,hos.roleName,hos.jobTitleName from BaseDoctorDO doc,BaseDoctorHospitalDO hos where doc.id = hos.doctorCode and hos.del = 1 and name like ?1")
    List<Map<String,Object>> queryDoctorFullInfoByName(String name, Pageable pageable);

    @Query("select doc.id,doc.name,doc.idcard,case doc.sex when 1 then '男' when 2 then '女' else '未知' end as sex,doc.del as status,hos.hospName,hos.deptName,hos.roleName,hos.jobTitleName from BaseDoctorDO doc,BaseDoctorHospitalDO hos where doc.id = hos.doctorCode and hos.del = 1 and name like ?1")
    List<Map<String,Object>> queryDoctorFullInfoByNameAndOrgCode(String name, String orgCode,Pageable pageable);

    @Query("select doc.id,doc.name,doc.idcard,case doc.sex when 1 then '男' when 2 then '女' else '未知' end as sex,doc.del as status,hos.hospName,hos.deptName,hos.roleName,hos.jobTitleName from BaseDoctorDO doc,BaseDoctorHospitalDO hos where doc.id = hos.doctorCode and hos.del = 1 and name like ?1")
    List<Map<String,Object>> queryDoctorFullInfoByNameAndDocDel(String name, String docStatus,Pageable pageable);

    @Query("select doc.id,doc.name,doc.idcard,case doc.sex when 1 then '男' when 2 then '女' else '未知' end as sex,doc.del as status,hos.hospName,hos.deptName,hos.roleName,hos.jobTitleName from BaseDoctorDO doc,BaseDoctorHospitalDO hos where doc.id = hos.doctorCode and hos.del = 1 and name like ?1 and orgCode = ?2")
    List<Map<String,Object>> queryDoctorFullInfoByNameAndOrgCodeAndDocDel(String name, String orgCode,String docStatus,Pageable pageable);

    @Query("select doc.id,doc.name,doc.idcard,case doc.sex when 1 then '男' when 2 then '女' else '未知' end as sex,doc.del as status,hos.hospName,hos.deptName,hos.roleName,hos.jobTitleName from BaseDoctorDO doc,BaseDoctorHospitalDO hos where doc.id = hos.doctorCode and hos.del = 1 and idcard like ?1")
    List<Map<String,Object>> queryDoctorFullInfoByIdcard(String idcard, Pageable pageable);

    @Query("select doc.id,doc.name,doc.idcard,case doc.sex when 1 then '男' when 2 then '女' else '未知' end as sex,doc.del as status,hos.hospName,hos.deptName,hos.roleName,hos.jobTitleName from BaseDoctorDO doc,BaseDoctorHospitalDO hos where doc.id = hos.doctorCode and hos.del = 1 and idcard like ?1 and orgCode = ?2")
    List<Map<String,Object>> queryDoctorFullInfoByIdcardAndOrgCode(String idcard,String orgCode,Pageable pageable);


}