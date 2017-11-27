package com.yihu.jw.wlyy.dao.agreement;

import com.yihu.jw.wlyy.agreement.WlyyAgreementKpiLogDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Administrator on 2017/6/1 0001.
 */
public interface WlyyAgreementKpiLogDao  extends PagingAndSortingRepository<WlyyAgreementKpiLogDO, String>, JpaSpecificationExecutor<WlyyAgreementKpiLogDO> {

    @Query("from WlyyAgreementKpiLogDO w where w.id =?1")
    WlyyAgreementKpiLogDO findById(String code);
}