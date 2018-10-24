package com.yihu.jw.base.dao.doctor;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.yihu.jw.entity.base.doctor.BaseDoctorDO;

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

   // 开始----查询医生基本信息，包含结构
    @Query(value = "select tb.id as id,tb.name as name,tb.idcard as idcard,tb.sex as sex,tb.mobile as mobile,GROUP_CONCAT(tb.org SEPARATOR ',') as orgInfo,tb.status as status from (select doc.id, doc.name, doc.idcard, case doc.sex when 1 then '男' when 2 then '女' else '未知' end as sex, doc.mobile, concat(hos.org_name,'/',dept.name,'/',hos.doctor_duty_name,'/',hos.job_title_name) as org,case doc.del when 0 then '无效' when 1 then '有效' end as status from base_doctor doc, base_doctor_hospital hos, dict_hospital_dept dept where doc.id = hos.doctor_code and hos.dept_code = dept.code) tb GROUP BY tb.id",nativeQuery = true)
    List<Map<String,Object>> queryDoctorFullInfo();

    @Query(value = "select tb.id as id,tb.name as name,tb.idcard as idcard,tb.sex as sex,tb.mobile as mobile,GROUP_CONCAT(tb.org SEPARATOR ',') as orgInfo,tb.status as status from (select doc.id, doc.name, doc.idcard, case doc.sex when 1 then '男' when 2 then '女' else '未知' end as sex, doc.mobile, concat(hos.org_name,'/',dept.name,'/',hos.doctor_duty_name,'/',hos.job_title_name) as org,case doc.del when 0 then '无效' when 1 then '有效' end as status from base_doctor doc, base_doctor_hospital hos, dict_hospital_dept dept where doc.id = hos.doctor_code and hos.dept_code = dept.code and doc.idcard like ?1) tb GROUP BY tb.id",nativeQuery = true)
    List<Map<String,Object>> queryDoctorFullInfoByIdcard(String idcard);

    @Query(value = "select tb.id as id,tb.name as name,tb.idcard as idcard,tb.sex as sex,tb.mobile as mobile,GROUP_CONCAT(tb.org SEPARATOR ',') as orgInfo,tb.status as status from (select doc.id, doc.name, doc.idcard, case doc.sex when 1 then '男' when 2 then '女' else '未知' end as sex, doc.mobile, concat(hos.org_name,'/',dept.name,'/',hos.doctor_duty_name,'/',hos.job_title_name) as org,case doc.del when 0 then '无效' when 1 then '有效' end as status from base_doctor doc, base_doctor_hospital hos, dict_hospital_dept dept where doc.id = hos.doctor_code and hos.dept_code = dept.code and doc.idcard like ?1 and hos.org_code =  ?2) tb GROUP BY tb.id",nativeQuery = true)
    List<Map<String,Object>> queryDoctorFullInfoByIdcardAndOrg(String idcard,String orgCode);

    @Query(value = "select tb.id as id,tb.name as name,tb.idcard as idcard,tb.sex as sex,tb.mobile as mobile,GROUP_CONCAT(tb.org SEPARATOR ',') as orgInfo,tb.status as status from (select doc.id, doc.name, doc.idcard, case doc.sex when 1 then '男' when 2 then '女' else '未知' end as sex, doc.mobile, concat(hos.org_name,'/',dept.name,'/',hos.doctor_duty_name,'/',hos.job_title_name) as org,case doc.del when 0 then '无效' when 1 then '有效' end as status from base_doctor doc, base_doctor_hospital hos, dict_hospital_dept dept where doc.id = hos.doctor_code and hos.dept_code = dept.code and doc.idcard like ?1 and doc.del = ?2) tb GROUP BY tb.id",nativeQuery = true)
    List<Map<String,Object>> queryDoctorFullInfoByIdcardAndDocDel(String idcard,String del);

    @Query(value = "select tb.id as id,tb.name as name,tb.idcard as idcard,tb.sex as sex,tb.mobile as mobile,GROUP_CONCAT(tb.org SEPARATOR ',') as orgInfo,tb.status as status from (select doc.id, doc.name, doc.idcard, case doc.sex when 1 then '男' when 2 then '女' else '未知' end as sex, doc.mobile, concat(hos.org_name,'/',dept.name,'/',hos.doctor_duty_name,'/',hos.job_title_name) as org,case doc.del when 0 then '无效' when 1 then '有效' end as status from base_doctor doc, base_doctor_hospital hos, dict_hospital_dept dept where doc.id = hos.doctor_code and hos.dept_code = dept.code and doc.idcard like ?1 and hos.org_code = ?2 and doc.del = ?3) tb GROUP BY tb.id",nativeQuery = true)
    List<Map<String,Object>> queryDoctorFullInfoByIdcardAndOrgAndDocDel(String idcard,String orgCode,String del);

    @Query(value = "select tb.id as id,tb.name as name,tb.idcard as idcard,tb.sex as sex,tb.mobile as mobile,GROUP_CONCAT(tb.org SEPARATOR ',') as orgInfo,tb.status as status from (select doc.id, doc.name, doc.idcard, case doc.sex when 1 then '男' when 2 then '女' else '未知' end as sex, doc.mobile, concat(hos.org_name,'/',dept.name,'/',hos.doctor_duty_name,'/',hos.job_title_name) as org,case doc.del when 0 then '无效' when 1 then '有效' end as status from base_doctor doc, base_doctor_hospital hos, dict_hospital_dept dept where doc.id = hos.doctor_code and hos.dept_code = dept.code and doc.name like ?1) tb GROUP BY tb.id",nativeQuery = true)
    List<Map<String,Object>> queryDoctorFullInfoByName(String name);

    @Query(value = "select tb.id as id,tb.name as name,tb.idcard as idcard,tb.sex as sex,tb.mobile as mobile,GROUP_CONCAT(tb.org SEPARATOR ',') as orgInfo,tb.status as status from (select doc.id, doc.name, doc.idcard, case doc.sex when 1 then '男' when 2 then '女' else '未知' end as sex, doc.mobile, concat(hos.org_name,'/',dept.name,'/',hos.doctor_duty_name,'/',hos.job_title_name) as org,case doc.del when 0 then '无效' when 1 then '有效' end as status from base_doctor doc, base_doctor_hospital hos, dict_hospital_dept dept where doc.id = hos.doctor_code and hos.dept_code = dept.code and doc.name like ?1 and hos.org_code = ?2) tb GROUP BY tb.id",nativeQuery = true)
    List<Map<String,Object>> queryDoctorFullInfoByNameAndOrg(String name,String orgCode);

    @Query(value = "select tb.id as id,tb.name as name,tb.idcard as idcard,tb.sex as sex,tb.mobile as mobile,GROUP_CONCAT(tb.org SEPARATOR ',') as orgInfo,tb.status as status from (select doc.id, doc.name, doc.idcard, case doc.sex when 1 then '男' when 2 then '女' else '未知' end as sex, doc.mobile, concat(hos.org_name,'/',dept.name,'/',hos.doctor_duty_name,'/',hos.job_title_name) as org,case doc.del when 0 then '无效' when 1 then '有效' end as status from base_doctor doc, base_doctor_hospital hos, dict_hospital_dept dept where doc.id = hos.doctor_code and hos.dept_code = dept.code and doc.name like ?1 and doc.del = ?2) tb GROUP BY tb.id",nativeQuery = true)
    List<Map<String,Object>> queryDoctorFullInfoByNameAndDocDel(String name,String del);

    @Query(value = "select tb.id as id,tb.name as name,tb.idcard as idcard,tb.sex as sex,tb.mobile as mobile,GROUP_CONCAT(tb.org SEPARATOR ',') as orgInfo,tb.status as status from (select doc.id, doc.name, doc.idcard, case doc.sex when 1 then '男' when 2 then '女' else '未知' end as sex, doc.mobile, concat(hos.org_name,'/',dept.name,'/',hos.doctor_duty_name,'/',hos.job_title_name) as org,case doc.del when 0 then '无效' when 1 then '有效' end as status from base_doctor doc, base_doctor_hospital hos, dict_hospital_dept dept where doc.id = hos.doctor_code and hos.dept_code = dept.code and doc.name like ?1 and hos.org_code = ?2 and doc.del = ?3) tb GROUP BY tb.id",nativeQuery = true)
    List<Map<String,Object>> queryDoctorFullInfoByNameAndOrgAndDocDel(String name,String orgCode,String del);

    @Query(value = "select tb.id as id,tb.name as name,tb.idcard as idcard,tb.sex as sex,tb.mobile as mobile,GROUP_CONCAT(tb.org SEPARATOR ',') as orgInfo,tb.status as status from (select doc.id, doc.name, doc.idcard, case doc.sex when 1 then '男' when 2 then '女' else '未知' end as sex, doc.mobile, concat(hos.org_name,'/',dept.name,'/',hos.doctor_duty_name,'/',hos.job_title_name) as org,case doc.del when 0 then '无效' when 1 then '有效' end as status from base_doctor doc, base_doctor_hospital hos, dict_hospital_dept dept where doc.id = hos.doctor_code and hos.dept_code = dept.code and hos.org_code = ?1) tb GROUP BY tb.id",nativeQuery = true)
    List<Map<String,Object>> queryDoctorFullInfoByOrg(String orgCode);

    @Query(value = "select tb.id as id,tb.name as name,tb.idcard as idcard,tb.sex as sex,tb.mobile as mobile,GROUP_CONCAT(tb.org SEPARATOR ',') as orgInfo,tb.status as status from (select doc.id, doc.name, doc.idcard, case doc.sex when 1 then '男' when 2 then '女' else '未知' end as sex, doc.mobile, concat(hos.org_name,'/',dept.name,'/',hos.doctor_duty_name,'/',hos.job_title_name) as org,case doc.del when 0 then '无效' when 1 then '有效' end as status from base_doctor doc, base_doctor_hospital hos, dict_hospital_dept dept where doc.id = hos.doctor_code and hos.dept_code = dept.code and hos.org_code = ?1 and doc.del = ?2) tb GROUP BY tb.id",nativeQuery = true)
    List<Map<String,Object>> queryDoctorFullInfoByOrgAndDocDel(String orgCode,String del);

    @Query(value = "select tb.id as id,tb.name as name,tb.idcard as idcard,tb.sex as sex,tb.mobile as mobile,GROUP_CONCAT(tb.org SEPARATOR ',') as orgInfo,tb.status as status from (select doc.id, doc.name, doc.idcard, case doc.sex when 1 then '男' when 2 then '女' else '未知' end as sex, doc.mobile, concat(hos.org_name,'/',dept.name,'/',hos.doctor_duty_name,'/',hos.job_title_name) as org,case doc.del when 0 then '无效' when 1 then '有效' end as status from base_doctor doc, base_doctor_hospital hos, dict_hospital_dept dept where doc.id = hos.doctor_code and hos.dept_code = dept.code and doc.del = ?1) tb GROUP BY tb.id",nativeQuery = true)
    List<Map<String,Object>> queryDoctorFullInfoByDocDel(String del);
    // 结束----查询医生基本信息，包含结构






}