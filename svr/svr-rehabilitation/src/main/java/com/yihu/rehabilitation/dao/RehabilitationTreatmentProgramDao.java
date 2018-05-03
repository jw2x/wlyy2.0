package com.yihu.rehabilitation.dao;

import com.yihu.jw.rehabilitation.RehabilitationTreatmentProgramDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RehabilitationTreatmentProgramDao extends PagingAndSortingRepository<RehabilitationTreatmentProgramDO, String>,
        JpaSpecificationExecutor<RehabilitationTreatmentProgramDO> {

    @Query("from RehabilitationTreatmentProgramDO w where w.id = ?1")
    RehabilitationTreatmentProgramDO findById(String id);
}
