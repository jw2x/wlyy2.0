package com.yihu.jw.base.service.patient;

import com.yihu.jw.base.dao.patient.BasePatientDao;
import com.yihu.jw.base.util.JavaBeanUtils;
import com.yihu.mysql.query.BaseJpaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.yihu.jw.entity.base.patient.BasePatientDO;
import org.springframework.util.CollectionUtils;

import java.util.*;

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

    @Autowired
    private BasePatientDao basePatientDao;

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

    /**
     * 获取用户基础信息，参数为空查全部
     * @param idcard
     * @param name
     * @param page
     * @param size
     * @param sorts
     * @return
     */
    public List<Map<String,Object>> queryPatientBaseInfo(String idcard,String name,int page,int size,String sorts)throws Exception{
        List<Map<String,Object>> result = new ArrayList<>();
        if(!StringUtils.isEmpty(idcard)){
            result = basePatientDao.findByIdcard("%"+idcard+"%",creatPage(page,size,sorts));
            return result;
        }
        if(!StringUtils.isEmpty(name)){
            result = basePatientDao.findByName(idcard,creatPage(page,size,sorts));
            return result;
        }
        result = basePatientDao.findBaseInfo(creatPage(page,size,sorts));
        return result;
    }
}
