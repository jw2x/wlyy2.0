package com.yihu.jw.wlyy.dao.patient;

import com.yihu.jw.wlyy.entity.patient.BasePatient;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Administrator on 2017/6/6 0006.
 */
public interface BasePatientDao extends PagingAndSortingRepository<BasePatient, Long>, JpaSpecificationExecutor<BasePatient> {

    @Query("from BasePatient w where w.code =?1")
    BasePatient findByCode(String code);

    @Query("from BasePatient w where w.id=?1 ")
    BasePatient findById(Long id);

}