package com.yihu.jw.wlyy.dao.doctor;

import com.yihu.jw.wlyy.doctor.BaseDoctorsDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Administrator on 2017/6/7 0007.
 */
public interface DoctorDao extends PagingAndSortingRepository<BaseDoctorsDO, String>, JpaSpecificationExecutor<BaseDoctorsDO> {

    @Query("from BaseDoctorsDO d where d.id = ?1 and d.status !=-1")
    BaseDoctorsDO findById(String code);
}
