package com.yihu.jw.dao;

import com.yihu.jw.entity.specialist.PatientHospitalRecordDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Trick on 2018/4/25.
 */
public interface PatientHospitalRecordDao extends PagingAndSortingRepository<PatientHospitalRecordDO, String>,
        JpaSpecificationExecutor<PatientHospitalRecordDO> {
}
