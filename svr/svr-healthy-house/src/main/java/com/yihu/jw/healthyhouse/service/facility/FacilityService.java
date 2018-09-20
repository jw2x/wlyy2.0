package com.yihu.jw.healthyhouse.service.facility;

import com.yihu.jw.healthyhouse.dao.facility.FacilityDao;
import com.yihu.jw.healthyhouse.model.facility.Facility;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 设施管理器.
 *
 * @author zdm
 * @version 1.0
 * @created 2018.09.19
 */
@Service
@Transactional
public class FacilityService extends BaseJpaService<Facility, FacilityDao> {

    @Autowired
    private FacilityDao facilityDao;

    public Facility findById(String id) {
        return  facilityDao.findById(id);
    }

}
