package com.yihu.jw.wlyy.agreement.dao;

import com.yihu.jw.wlyy.agreement.entity.WlyyAgreementKpiLog;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Administrator on 2017/6/1 0001.
 */
public interface WlyyAgreementKpiLogDao  extends PagingAndSortingRepository<WlyyAgreementKpiLog, Long>, JpaSpecificationExecutor<WlyyAgreementKpiLog> {

    @Query("from WlyyAgreementKpiLog w where w.code =?1")
    WlyyAgreementKpiLog findByCode(String code);
}