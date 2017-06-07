package com.yihu.jw.wlyy.dao.doctor;

import com.yihu.jw.wlyy.entity.doctor.BaseOrgHospital;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Administrator on 2017/6/7 0007.
 */
public interface HospitalDao extends PagingAndSortingRepository<BaseOrgHospital, Long>, JpaSpecificationExecutor<BaseOrgHospital> {

    @Query("from BaseOrgHospital w where w.code = ?1")
    BaseOrgHospital findByCode(String code);
}
