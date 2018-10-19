package com.yihu.jw.base.service.patient;

import com.yihu.jw.base.dao.patient.PatientMedicareCardDao;
import com.yihu.jw.entity.base.patient.PatientMedicareCardDO;
import com.yihu.mysql.query.BaseJpaService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<PatientMedicareCardDO> findPatientCardByCode(String patientCode){
        List<PatientMedicareCardDO> result = new ArrayList<>();
        if(StringUtils.isEmpty(patientCode)){
            return result;
        }
        return patientMedicareCardDao.findByPatientCode(patientCode);
    }

}
