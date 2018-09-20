package com.yihu.jw.healthyhouse.service.facility;

import com.yihu.jw.healthyhouse.dao.facility.FacilityServerDao;
import com.yihu.jw.healthyhouse.model.facility.FacilityServer;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 设施服务管理器.
 *
 * @author zdm
 * @version 1.0
 * @created 2018.09.19
 */
@Service
@Transactional
public class FacilityServerService extends BaseJpaService<FacilityServer, FacilityServerDao> {

    @Autowired
    private FacilityServerDao facilityServerDao;

    public FacilityServer findById(String id) {
        return  facilityServerDao.findById(id);
    }

}
