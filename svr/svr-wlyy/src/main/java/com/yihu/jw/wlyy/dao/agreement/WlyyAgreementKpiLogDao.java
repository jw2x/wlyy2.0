package com.yihu.jw.wlyy.dao.agreement;

import com.yihu.jw.wlyy.agreement.WlyyAgreementKpiLog;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Administrator on 2017/6/1 0001.
 */
public interface WlyyAgreementKpiLogDao  extends PagingAndSortingRepository<WlyyAgreementKpiLog, String>, JpaSpecificationExecutor<WlyyAgreementKpiLog> {

    @Query("from WlyyAgreementKpiLog w where w.id =?1")
    WlyyAgreementKpiLog findById(String code);
}