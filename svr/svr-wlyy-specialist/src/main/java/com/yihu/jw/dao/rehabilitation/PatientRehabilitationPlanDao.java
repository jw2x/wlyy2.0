package com.yihu.jw.dao.rehabilitation;

import com.yihu.jw.entity.specialist.PatientHospitalRecordDO;
import com.yihu.jw.entity.specialist.rehabilitation.PatientRehabilitationPlanDO;
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

    List<PatientRehabilitationPlanDO> findByPatientAndCreateUser(String patient, String doctor);

    @Query(" select p from PatientRehabilitationPlanDO p where p.patient=?1 order by p.createTime desc ")
    List<PatientRehabilitationPlanDO> findbyPatients(String patientCode);

    //插入服务包id
    @Modifying
    @Query("update PatientRehabilitationPlanDO p set p.servicePackageId = ?2 where p.id = ?1")
    void updateServicePackageId(String planId, String servicePackageId);
}
