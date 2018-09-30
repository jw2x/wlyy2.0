package com.yihu.jw.healthyhouse.dao.facility;

import com.yihu.jw.healthyhouse.model.facility.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 设施dao
 *
 * @author zdm
 * @version 1.0
 * @created 2018.09.19
 */
public interface FacilityDao extends JpaRepository<Facility, String> {

    Facility findById(String id);

    List<Facility> findByLongitudeAndLatitude(double longitude, double latitude);

    Facility findByCode(String code);

    @Query("select f from Facility f where f.code in (:code) ")
    List<Facility> findByCode(@Param("code") List<String> code);
}

