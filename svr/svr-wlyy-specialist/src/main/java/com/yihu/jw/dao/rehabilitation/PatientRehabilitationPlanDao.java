package com.yihu.jw.dao.rehabilitation;

import com.yihu.jw.entity.specialist.PatientHospitalRecordDO;
import com.yihu.jw.entity.specialist.rehabilitation.PatientRehabilitationPlanDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
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

    @Query("update PatientRehabilitationPlanDO a set a.status=?1 where a.id =?2 ")
    int updateStatusById(Integer status,String id);
}
