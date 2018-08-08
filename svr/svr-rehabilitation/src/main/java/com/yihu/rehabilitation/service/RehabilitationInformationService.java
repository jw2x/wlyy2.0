package com.yihu.rehabilitation.service;

import com.yihu.jw.rehabilitation.RehabilitationInformationDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.rehabilitation.RehabilitationInformationVO;
import com.yihu.jw.rm.rehabilitation.RehabilitationRequestMapping;
import com.yihu.mysql.query.BaseJpaService;
import com.yihu.rehabilitation.dao.RehabilitationInformationDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author humingfen on 2018/4/25.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RehabilitationInformationService extends BaseJpaService<RehabilitationInformationDO, RehabilitationInformationDao> {

    @Autowired
    private RehabilitationInformationDao rehabilitationInformationDao;
    @Autowired
    private JdbcTemplate jdbcTempalte;

    /**
     * 按类型分页查找
     * @param page
     * @param size
     * @param patientId
     * @param hospital
     * @return
     */
    public Envelop<RehabilitationInformationVO> queryPage(Integer page, Integer size, String patientId, String hospital){
        StringBuffer sql = new StringBuffer("SELECT DISTINCT w.* from rehabilitation_information w where 1 = 1 ");
        StringBuffer sqlCount = new StringBuffer("SELECT COUNT(DISTINCT w.id) count from rehabilitation_information w where 1 = 1 ");
        List<Object> args = new ArrayList<>();
        if(StringUtils.isNotBlank(patientId)){
            sql.append(" and w.patient_id = ? ");
            sqlCount.append(" and w.patient_id = '").append(patientId).append("' ");
            args.add(patientId);
        }
        if(StringUtils.isNotBlank(hospital)){
            sql.append(" and w.hospital like ? ");
            sqlCount.append(" and w.hospital like '%").append(hospital).append("%' ");
            args.add('%'+ hospital + '%');
        }
        sql.append("order by w.update_time desc limit ").append((page-1)*size).append(",").append(size);

        List<RehabilitationInformationDO> list = jdbcTempalte.query(sql.toString(),args.toArray(),new BeanPropertyRowMapper(RehabilitationInformationDO.class));
        List<Map<String,Object>> countList = jdbcTempalte.queryForList(sqlCount.toString());
        long count = Long.valueOf(countList.get(0).get("count").toString());

        //DO转VO
        List<RehabilitationInformationVO> informationVOList = convertToModels(list,new ArrayList<>(list.size()), RehabilitationInformationVO.class);

        return Envelop.getSuccessListWithPage(RehabilitationRequestMapping.Common.message_success_find_functions,informationVOList, page, size,count);
    }

    /**
     * 新增
     * @param informationDO
     * @return
     */
    public RehabilitationInformationDO create(RehabilitationInformationDO informationDO) {
        informationDO.setSaasId(getCode());
        informationDO = rehabilitationInformationDao.save(informationDO);
        return informationDO;
    }

    /**
     * 按id查找
     * @param id
     * @return
     */
    public RehabilitationInformationDO findById(String id) {
        RehabilitationInformationDO informationDO = rehabilitationInformationDao.findById(id);
        return informationDO;
    }

    /**
     * 按patientId查找
     * @param patientId
     * @return
     */
    public List<RehabilitationInformationDO> findByPatientId(String patientId) {
        List<RehabilitationInformationDO> informationDO = rehabilitationInformationDao.findByPatientId(patientId);
        return informationDO;
    }

    /**
     * 修改
     * @param informationDO
     */
    public void update(RehabilitationInformationDO informationDO){
        RehabilitationInformationDO oldInformationDO = rehabilitationInformationDao.findById(informationDO.getId());
        oldInformationDO.setHospital(informationDO.getHospital());
        oldInformationDO.setDepartmen(informationDO.getDepartmen());
        oldInformationDO.setSummary(informationDO.getSummary());
        oldInformationDO.setAdvice(informationDO.getAdvice());
        oldInformationDO.setDisease(informationDO.getDisease());
        oldInformationDO.setDischargeTime(informationDO.getDischargeTime());
        rehabilitationInformationDao.save(oldInformationDO);
    }

}
