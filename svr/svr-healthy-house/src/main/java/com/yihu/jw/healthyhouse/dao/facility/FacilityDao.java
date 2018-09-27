package com.yihu.jw.healthyhouse.dao.facility;

import com.yihu.jw.healthyhouse.model.facility.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 设施dao
 * @author zdm
 * @version 1.0
 * @created 2018.09.19
 */
public interface FacilityDao extends JpaRepository<Facility, String> {

    Facility findById(String id);
    Facility findByLongitudeAndLatitude(double longitude,double latitude);
    Facility findByCode(String code);
}

