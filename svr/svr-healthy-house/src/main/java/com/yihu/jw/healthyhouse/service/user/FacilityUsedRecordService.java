package com.yihu.jw.healthyhouse.service.user;

import com.yihu.jw.healthyhouse.dao.user.FacilityUsedRecordDao;
import com.yihu.jw.healthyhouse.model.user.FacilityUsedRecord;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        return facilityUsedRecordDao.findById(id);
    }

    public List<FacilityUsedRecord> countDistinctByFacilitieCodeAndUserId(String userId,Integer page,Integer size) throws Exception {
        Integer pageStart = (page - 1) * size;
        Integer pageEnd = page * size;
        String sql = "select fur.*  from facility_used_records  fur WHERE  fur.user_id=? GROUP BY fur.facilitie_code LIMIT "+pageStart+","+pageEnd;
        List<FacilityUsedRecord> facilityUsedRecords = jdbcTemplate.query(sql, new BeanPropertyRowMapper(FacilityUsedRecord.class), userId);
        return facilityUsedRecords;
    }


    public long countByFacilitieCodeAndUserId(String facilitieCode,String userId) {
        return facilityUsedRecordDao.countByFacilitieCodeAndUserId( facilitieCode, userId);
    }
}
