package com.yihu.jw.healthyhouse.service.facilities;

import com.yihu.jw.healthyhouse.dao.facilities.FacilitiesDao;
import com.yihu.jw.healthyhouse.model.facilities.Facilities;
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
public class FacilitiesService extends BaseJpaService<Facilities, FacilitiesDao> {

    @Autowired
    private FacilitiesDao facilitiesDao;

    public Facilities findById(String id) {
        return  facilitiesDao.findById(id);
    }

}
