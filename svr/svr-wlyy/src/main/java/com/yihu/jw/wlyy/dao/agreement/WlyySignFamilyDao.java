package com.yihu.jw.wlyy.dao.agreement;

import com.yihu.jw.wlyy.agreement.WlyySignFamily;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/6/2 0002.
 */
public interface WlyySignFamilyDao  extends PagingAndSortingRepository<WlyySignFamily, String>, JpaSpecificationExecutor<WlyySignFamily> {

    @Query("from WlyySignFamily w where w.id = ?1")
    WlyySignFamily findById(String code);

    @Query("from WlyySignFamily w where w.patient = ?1 and w.status = ?2")
    List<WlyySignFamily> findByPatientCode(String patientCode, Integer status);
}