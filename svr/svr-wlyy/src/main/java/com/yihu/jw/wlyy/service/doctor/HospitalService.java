package com.yihu.jw.wlyy.service.doctor;

import com.yihu.jw.mysql.query.BaseJpaService;
import com.yihu.jw.wlyy.dao.doctor.HospitalDao;
import com.yihu.jw.wlyy.entity.doctor.BaseOrgHospital;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/6/7 0007.
 */
public class HospitalService extends BaseJpaService<BaseOrgHospital, HospitalDao> {

    @Autowired
    private HospitalDao hospitalDao;

    public BaseOrgHospital findByCode(String code){
        return hospitalDao.findByCode(code);
    }

}
