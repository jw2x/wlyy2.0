package com.yihu.jw.wlyy.dao;

import com.yihu.jw.wlyy.entity.WlyyAgreement;
import com.yihu.jw.wlyy.entity.WlyyAgreementKpi;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Administrator on 2017/6/1 0001.
 */
public interface WlyyAgreementKpiDao extends PagingAndSortingRepository<WlyyAgreementKpi, Long>, JpaSpecificationExecutor<WlyyAgreementKpi> {

    @Query("from WlyyAgreementKpi w where w.code =?1 and w.status!=-1")
    WlyyAgreementKpi findByCode(String code);

    @Query("from WlyyAgreementKpi w where w.id=?1 and w.status !=-1")
    WlyyAgreementKpi findById(Long id);

    @Query("from WlyyAgreementKpi w where w.id!=?2 and w.code = ?1 and w.status !=-1")
    WlyyAgreementKpi findCodeExcludeId(String code, Long id);
}