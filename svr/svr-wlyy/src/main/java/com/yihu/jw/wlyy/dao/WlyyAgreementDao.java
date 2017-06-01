package com.yihu.jw.wlyy.dao;

import com.yihu.jw.wlyy.entity.WlyyAgreement;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Administrator on 2017/6/1 0001.
 */
public interface WlyyAgreementDao extends PagingAndSortingRepository<WlyyAgreement, Long>, JpaSpecificationExecutor<WlyyAgreement> {

    @Query("from WlyyAgreement w where w.code =?1 and w.status!=-1")
    WlyyAgreement findByCode(String code);

    @Query("from WlyyAgreement w where w.id=?1 and w.status !=-1")
    WlyyAgreement findById(Long id);

    @Query("from WlyyAgreement w where w.id!=?2 and w.code = ?1 and w.status !=-1")
    WlyyAgreement findCodeExcludeId(String code, Long id);
}