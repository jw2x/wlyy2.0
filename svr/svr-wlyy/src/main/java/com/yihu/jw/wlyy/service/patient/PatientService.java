package com.yihu.jw.wlyy.service.patient;

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.jw.wlyy.dao.patient.BasePatientDao;
import com.yihu.jw.wlyy.patient.BasePatientDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/6/6 0006.
 */
@Service
public class PatientService extends BaseJpaService<BasePatientDO, BasePatientDao> {

    @Autowired
    private BasePatientDao basePatientDao;


    public BasePatientDO findByCode(String code){
        return basePatientDao.findByCode(code);
    }
}