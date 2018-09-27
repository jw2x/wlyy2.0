package com.yihu.jw.healthyhouse.service.user;

import com.yihu.jw.exception.business.ManageException;
import com.yihu.jw.healthyhouse.dao.facility.FacilityDao;
import com.yihu.jw.healthyhouse.dao.facility.FacilityServerRelationDao;
import com.yihu.jw.healthyhouse.dao.user.FacilityUsedRecordDao;
import com.yihu.jw.healthyhouse.dao.user.NavigationServiceEvaluationDao;
import com.yihu.jw.healthyhouse.model.facility.Facility;
import com.yihu.jw.healthyhouse.model.facility.FacilityServerRelation;
import com.yihu.jw.healthyhouse.model.user.FacilityUsedRecord;
import com.yihu.jw.healthyhouse.model.user.NavigationServiceEvaluation;
import com.yihu.jw.healthyhouse.service.facility.FacilityService;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Autowired
    private NavigationServiceEvaluationDao navigationServiceEvaluationDao;
    @Autowired
    private FacilityServerRelationDao facilityServerRelationDao;
    @Autowired
    private FacilityDao facilityDao;

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

    /**
     * 获取 用户使用记录详情
     * @param id
     * @return
     */
    public Map<String,Object> getUsedRecordDetail(String id) throws ManageException {
        Map<String,Object> result = new HashMap<>();
        FacilityUsedRecord record = findById(id);
        if (record == null ){
            throw new ManageException("使用记录不存在！");
        }

        String userId = record.getCreateUser();
        String facilityCode = record.getFacilitieCode();
        NavigationServiceEvaluation comment = navigationServiceEvaluationDao.findByUseRecordId(record.getId());
        long historyNum = countByFacilitieCodeAndUserId(facilityCode, userId);
        Facility facility = facilityDao.findByCode(facilityCode);
        List<FacilityServerRelation> facilityServerRelations = facilityServerRelationDao.findByFacilitieCode(facilityCode);
        List<String> services = facilityServerRelations.stream().map(FacilityServerRelation::getServiceName).collect(Collectors.toList());
        if (comment ==null) {
            result.put("Commented",false); //未评价
        }else {
            result.put("Commented",true);//已评价
            result.put("Star",comment.getScore());//评分
        }
        result.put("usedCount",historyNum);//前往设施次数
        result.put("serviceList",services);//设施包含服务
        result.put("facilityIcon",facility.getImgPath());//设施图片
        return result;
    }

}
