package com.yihu.jw.healthyhouse.dao.user;

import com.yihu.jw.healthyhouse.model.user.FacilityUsedRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户导航记录 dao
 * @author zdm
 * @version 1.0
 * @created 2018.09.21
 */
public interface FacilityUsedRecordDao extends JpaRepository<FacilityUsedRecord, Long> {

    FacilityUsedRecord findById(String id);

    long countByFacilitieCodeAndCreateUser(String facilitieCode,String createUser);
}

