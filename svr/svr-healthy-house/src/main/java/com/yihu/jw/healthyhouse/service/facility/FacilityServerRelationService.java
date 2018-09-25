package com.yihu.jw.healthyhouse.service.facility;

import com.yihu.jw.healthyhouse.dao.facility.FacilityServerRelationDao;
import com.yihu.jw.healthyhouse.model.facility.FacilityServerRelation;
import com.yihu.jw.util.date.DateUtil;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 设施与服务关系 管理器.
 *
 * @author zdm
 * @version 1.0
 * @created 2018.09.20
 */
@Service
@Transactional
public class FacilityServerRelationService extends BaseJpaService<FacilityServerRelation, FacilityServerRelationDao> {

    @Autowired
    private FacilityServerRelationDao facilityServerRelationDao;

    public FacilityServerRelation findById(String id) {
        return facilityServerRelationDao.findById(id);
    }

    public void deleteByFacilitieCode(String facilitieCode) {
        facilityServerRelationDao.deleteByFacilitieCode(facilitieCode);
    }

    public long countDistinctByFacilitieCodeAndCreateTimeBetween() throws Exception {
        String date=DateUtil.getStringDateShort();
        String sql = "select COUNT(DISTINCT facilitie_code ) from facility_used_records  WHERE LEFT(create_time,10)=?";
        String count = jdbcTemplate.queryForObject(sql, String.class, date);
        return Long.parseLong(count);
    }


}
