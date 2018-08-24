package com.yihu.jw.wlyy.service.doctor;

import com.yihu.jw.wlyy.dao.doctor.HospitalDao;
import com.yihu.jw.entity.wlyy.hospital.BaseOrgHospitalDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/6/7 0007.
 */
@Service
public class HospitalService extends BaseJpaService<BaseOrgHospitalDO, HospitalDao> {

    @Autowired
    private HospitalDao hospitalDao;

    public BaseOrgHospitalDO findByCode(String code){
        return hospitalDao.findByCode(code);
    }

}
