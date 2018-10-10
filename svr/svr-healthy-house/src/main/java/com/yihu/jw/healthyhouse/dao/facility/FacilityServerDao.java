package com.yihu.jw.healthyhouse.dao.facility;

import com.yihu.jw.healthyhouse.model.facility.FacilityServer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 设施服务dao
 * @author zdm
 * @version 1.0
 * @created 2018.09.19
 */
public interface FacilityServerDao extends JpaRepository<FacilityServer, Long> {

    FacilityServer findById(String id);

    FacilityServer findByCode(String code);

    List<FacilityServer> findByNameIn(List<String> name);
}

