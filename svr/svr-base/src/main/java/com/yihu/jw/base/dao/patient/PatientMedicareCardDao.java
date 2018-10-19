package com.yihu.jw.base.dao.patient;

import com.yihu.jw.entity.base.patient.PatientMedicareCardDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Trick on 2018/8/31.
 */
public interface PatientMedicareCardDao extends PagingAndSortingRepository<PatientMedicareCardDO, String>, JpaSpecificationExecutor<PatientMedicareCardDO> {

    List<PatientMedicareCardDO> findByPatientCode(String patientCode);

    @Query("select id from PatientMedicareCardDO where patientCode = ?1")
    List<Object> findIdListByPatientCode(String patientCode);
}
