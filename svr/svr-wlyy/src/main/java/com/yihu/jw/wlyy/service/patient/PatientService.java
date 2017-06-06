package com.yihu.jw.wlyy.service.patient;

import com.yihu.jw.mysql.query.BaseJpaService;
import com.yihu.jw.wlyy.dao.patient.BasePatientDao;
import com.yihu.jw.wlyy.entity.patient.BasePatient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/6/6 0006.
 */
public class PatientService  extends BaseJpaService<BasePatient, BasePatientDao> {

    @Autowired
    private BasePatientDao basePatientDao;


    public BasePatient findByCode(String code){
        return basePatientDao.findByCode(code);
    }
}