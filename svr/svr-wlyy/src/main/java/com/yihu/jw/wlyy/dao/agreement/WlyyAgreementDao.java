package com.yihu.jw.wlyy.dao.agreement;

import com.yihu.jw.wlyy.agreement.WlyyAgreement;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Administrator on 2017/6/1 0001.
 */
public interface WlyyAgreementDao extends PagingAndSortingRepository<WlyyAgreement, String>, JpaSpecificationExecutor<WlyyAgreement> {


    @Query("from WlyyAgreement w where w.id=?1 and w.status !=-1")
    WlyyAgreement findById(String id);

}