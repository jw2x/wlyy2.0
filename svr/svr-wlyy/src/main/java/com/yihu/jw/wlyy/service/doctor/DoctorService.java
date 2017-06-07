package com.yihu.jw.wlyy.service.doctor;

import com.yihu.jw.mysql.query.BaseJpaService;
import com.yihu.jw.wlyy.dao.doctor.DoctorDao;
import com.yihu.jw.wlyy.entity.doctor.Doctors;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/6/7 0007.
 */
public class DoctorService extends BaseJpaService<Doctors, DoctorDao> {

    @Autowired
    private DoctorDao doctorDao;

    public Doctors findByCode(String code){
        return doctorDao.findByCode(code);
    }


}
