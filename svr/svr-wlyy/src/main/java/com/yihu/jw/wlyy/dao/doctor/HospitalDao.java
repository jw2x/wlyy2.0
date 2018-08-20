package com.yihu.jw.wlyy.dao.doctor;

import com.yihu.jw.entity.wlyy.hospital.BaseOrgHospitalDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Administrator on 2017/6/7 0007.
 */
public interface HospitalDao extends PagingAndSortingRepository<BaseOrgHospitalDO, String>, JpaSpecificationExecutor<BaseOrgHospitalDO> {

    @Query("from BaseOrgHospitalDO w where w.code = ?1")
    BaseOrgHospitalDO findByCode(String code);
}
