package com.yihu.rehabilitation.dao;

import com.yihu.jw.entity.rehabilitation.RehabilitationInformationDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RehabilitationInformationDao extends PagingAndSortingRepository<RehabilitationInformationDO, String>,
        JpaSpecificationExecutor<RehabilitationInformationDO> {

    @Query("from RehabilitationInformationDO w where w.id =?1 ")
    RehabilitationInformationDO findById(String id);

    @Query("from RehabilitationInformationDO w where w.patientId =?1 order by dischargeTime desc")
    List<RehabilitationInformationDO> findByPatientId(String patientId);
}
