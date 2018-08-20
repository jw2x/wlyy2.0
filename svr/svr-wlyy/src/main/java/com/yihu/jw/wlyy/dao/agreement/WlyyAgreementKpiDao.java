package com.yihu.jw.wlyy.dao.agreement;

import com.yihu.jw.entity.wlyy.agreement.WlyyAgreementKpiDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Administrator on 2017/6/1 0001.
 */
public interface WlyyAgreementKpiDao extends PagingAndSortingRepository<WlyyAgreementKpiDO, String>, JpaSpecificationExecutor<WlyyAgreementKpiDO> {


    @Query("from WlyyAgreementKpiDO w where w.id=?1 and w.status !=-1")
    WlyyAgreementKpiDO findById(String id);

}