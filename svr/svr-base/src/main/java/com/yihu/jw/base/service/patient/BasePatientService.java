package com.yihu.jw.base.service.patient;

import com.yihu.jw.base.dao.patient.BasePatientDao;
import com.yihu.jw.base.util.JavaBeanUtils;
import com.yihu.mysql.query.BaseJpaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yihu.jw.entity.base.patient.BasePatientDO;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class BasePatientService extends BaseJpaService<BasePatientDO, BasePatientDao> {

    /**
     * 居民id
     * @param patientId
     * @return
     */
    public Map<String,Object> getPatientInfo(String patientId) throws Exception{
        Map<String,Object> resultMap = new HashMap<>();
        if(StringUtils.isEmpty(patientId)){
            return resultMap;
        }
        List<BasePatientDO> patientDOList = this.findByField("id",patientId);
        if(CollectionUtils.isEmpty(patientDOList)){
            return resultMap;
        }
        resultMap = JavaBeanUtils.bean2Map(patientDOList.get(0));
        return resultMap;
    }
}
