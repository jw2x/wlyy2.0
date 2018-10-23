package com.yihu.jw.base.service.patient;

import com.yihu.jw.base.dao.patient.PatientMedicareCardDao;
import com.yihu.jw.entity.base.patient.PatientMedicareCardDO;
import com.yihu.mysql.query.BaseJpaService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * 居民信息服务service
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong    1.0  2018年08月31日 Created
 *
 * </pre>
 * @since 1.
 */
@Service
public class PatientMedicardCardService extends BaseJpaService<PatientMedicareCardDO, PatientMedicareCardDao> {

    @Autowired
    private PatientMedicareCardDao patientMedicareCardDao;

    /**
     * 根据居民标识获取居民医疗相关卡列表
     * @param patientCode
     * @return
     */
    public List<PatientMedicareCardDO> findPatientCardByCode(String patientCode){
        List<PatientMedicareCardDO> result = new ArrayList<>();
        if(StringUtils.isEmpty(patientCode)){
            return result;
        }
        return patientMedicareCardDao.findByPatientCode(patientCode);
    }

    /**
     * 根据居民标识获取关联卡id列表
     * @param patientCode
     * @return
     */
    public Set<Object> findIdListByPatientCode(String patientCode){
        Set<Object> result = new HashSet<>();
        if(StringUtils.isEmpty(patientCode)){
            return result;
        }
        return patientMedicareCardDao.findIdListByPatientCode(patientCode);
    }

}
