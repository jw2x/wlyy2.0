package com.yihu.jw.healthyhouse.service.facilities;

import com.yihu.jw.healthyhouse.dao.facilities.FacilitiesServerDao;
import com.yihu.jw.healthyhouse.model.facilities.FacilitiesServer;
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
public class FacilitiesServerService extends BaseJpaService<FacilitiesServer, FacilitiesServerDao> {

    @Autowired
    private FacilitiesServerDao facilitiesServerDao;

    public FacilitiesServer findById(String id) {
        return  facilitiesServerDao.findById(id);
    }

}
