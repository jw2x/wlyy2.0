package com.yihu.jw.wlyy.dao.doctor;

import com.yihu.jw.wlyy.doctor.Doctors;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Administrator on 2017/6/7 0007.
 */
public interface DoctorDao extends PagingAndSortingRepository<Doctors, Long>, JpaSpecificationExecutor<Doctors> {

    @Query("from Doctors d where d.code = ?1 and d.status !=-1")
    Doctors findByCode(String code);
}
