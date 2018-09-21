package com.yihu.jw.healthyhouse.service.user;

import com.yihu.jw.healthyhouse.dao.user.FacilityUsedRecordDao;
import com.yihu.jw.healthyhouse.model.user.FacilityUsedRecord;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户导航记录.
 *
 * @author zdm
 * @version 1.0
 * @created 2018.09.21
 */
@Service
@Transactional
public class FacilityUsedRecordService extends BaseJpaService<FacilityUsedRecord, FacilityUsedRecordDao> {

    @Autowired
    private FacilityUsedRecordDao facilityUsedRecordDao;

    public FacilityUsedRecord findById(String id) {
        return  facilityUsedRecordDao.findById(id);
    }

}
