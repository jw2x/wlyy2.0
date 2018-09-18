package com.yihu.jw.dao.rehabilitation;

import com.yihu.jw.entity.specialist.PatientHospitalRecordDO;
import com.yihu.jw.entity.specialist.rehabilitation.PatientRehabilitationPlanDO;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by 刘文彬 on 2018/8/16.
 */
public interface PatientRehabilitationPlanDao extends PagingAndSortingRepository<PatientRehabilitationPlanDO, Long>,JpaSpecificationExecutor<PatientRehabilitationPlanDO> {

    PatientRehabilitationPlanDO findById(String id);

    @Query(" select p from PatientRehabilitationPlanDO p where p.patient=?1 and p.createUser=?2 order by p.createTime desc ")
    List<PatientRehabilitationPlanDO> findByPatientAndCreateUser(String patient, String doctor);

    @Query(" select p from PatientRehabilitationPlanDO p where p.patient=?1 order by p.createTime desc ")
    List<PatientRehabilitationPlanDO> findByPatients(String patientCode);

    @Query("update PatientRehabilitationPlanDO a set a.status=?1 where a.id =?2 ")
    @Modifying
    int updateStatusById(Integer status,String id);

    //插入服务包id
    @Modifying
    @Query("update PatientRehabilitationPlanDO p set p.servicePackageId = ?2 where p.id = ?1")
    void updateServicePackageId(String planId, String servicePackageId);

    @Query("select count(distinct p.patient) from PatientRehabilitationPlanDO p where p.createUser=?1  and p.status>0 ")
    Integer patientCount(String doctorCode);

    @Query("select count(distinct p.patient) from PatientRehabilitationPlanDO p where p.createUser=?1  and p.status=1 ")
    Integer patientCountByUnfinish(String doctorCode);
}
