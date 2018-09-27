package com.yihu.jw.healthyhouse.dao.facility;

import com.yihu.jw.healthyhouse.model.facility.FacilityServerRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

/**
 * 设施与服务关系 dao
 * @author zdm
 * @version 1.0
 * @created 2018.09.20
 */
public interface FacilityServerRelationDao extends JpaRepository<FacilityServerRelation, Long> {

    FacilityServerRelation findById(String id);

    List<FacilityServerRelation> findByFacilitieCode(String facilityCode);

    @Modifying
    void deleteByFacilitieCode(String facilitieCode);
}

