package com.yihu.jw.wlyy.agreement.dao;

import com.yihu.jw.wlyy.agreement.entity.WlyySignFamily;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Administrator on 2017/6/2 0002.
 */
public interface WlyySignFamilyDao  extends PagingAndSortingRepository<WlyySignFamily, Long>, JpaSpecificationExecutor<WlyySignFamily> {

    @Query("from WlyySignFamily w where w.code = ?1")
    WlyySignFamily findByCode(String code);
}