package com.yihu.jw.wlyy.dao.agreement;

import com.yihu.jw.wlyy.agreement.WlyySignFamilyDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/6/2 0002.
 */
public interface WlyySignFamilyDao  extends PagingAndSortingRepository<WlyySignFamilyDO, String>, JpaSpecificationExecutor<WlyySignFamilyDO> {

    @Query("from WlyySignFamilyDO w where w.id = ?1")
    WlyySignFamilyDO findById(String id);

    @Query("from WlyySignFamilyDO w where w.patient = ?1 and w.status = ?2")
    List<WlyySignFamilyDO> findByPatientId(String patientId, Integer status);
}