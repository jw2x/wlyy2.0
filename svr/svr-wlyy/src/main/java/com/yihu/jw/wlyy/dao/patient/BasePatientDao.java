package com.yihu.jw.wlyy.dao.patient;

import com.yihu.jw.wlyy.patient.BasePatientDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Administrator on 2017/6/6 0006.
 */
public interface BasePatientDao extends PagingAndSortingRepository<BasePatientDO, String>, JpaSpecificationExecutor<BasePatientDO> {

    @Query("from BasePatientDO w where w.code =?1")
    BasePatientDO findByCode(String code);

    @Query("from BasePatientDO w where w.id=?1 ")
    BasePatientDO findById(Long id);

}