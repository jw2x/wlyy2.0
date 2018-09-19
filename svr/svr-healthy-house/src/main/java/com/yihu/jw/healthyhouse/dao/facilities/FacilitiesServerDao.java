package com.yihu.jw.healthyhouse.dao.facilities;

import com.yihu.jw.healthyhouse.model.facilities.FacilitiesServer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 设施服务dao
 * @author zdm
 * @version 1.0
 * @created 2018.09.19
 */
public interface FacilitiesServerDao extends JpaRepository<FacilitiesServer, Long> {

    FacilitiesServer findById(String id);
}

