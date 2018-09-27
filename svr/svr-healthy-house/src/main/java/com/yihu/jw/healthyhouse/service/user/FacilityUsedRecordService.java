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

    //根据用户id获取用户历史记录，按设施编码分组
    public List<FacilityUsedRecord> countDistinctByFacilitieCodeAndUserId(String userId) throws Exception {
        String sql = "select fur.*  from facility_used_records  fur WHERE  fur.create_user=? GROUP BY fur.facilitie_code ";
        List<FacilityUsedRecord> facilityUsedRecords = jdbcTemplate.query(sql, new BeanPropertyRowMapper(FacilityUsedRecord.class), userId);
        return facilityUsedRecords;
    }

    public Long countByUserId(String userId) {
        return facilityUsedRecordDao.countByUserId(userId);
    }

    public Long countAll() {
        return facilityUsedRecordDao.countAllByUserIdIsNotNull();
    }

    //根据用户id及设施编码统计历史导航记录总数
    public long countByFacilitieCodeAndUserId(String facilitieCode, String userId) {
        return facilityUsedRecordDao.countByFacilitieCodeAndCreateUser(facilitieCode, userId);
    }
}
