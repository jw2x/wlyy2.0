package com.yihu.jw.healthyhouse.dao.facilities;

import com.yihu.jw.healthyhouse.model.facilities.Facilities;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 设施dao
 * @author zdm
 * @version 1.0
 * @created 2018.09.19
 */
public interface FacilitiesDao extends JpaRepository<Facilities, Long> {

    Facilities findById(String id);
}

