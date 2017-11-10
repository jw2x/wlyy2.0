package com.yihu.jw.wlyy.service.doctor;

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.jw.wlyy.dao.doctor.DoctorDao;
import com.yihu.jw.wlyy.doctor.BaseDoctors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/6/7 0007.
 */
@Service
public class DoctorService extends BaseJpaService<BaseDoctors, DoctorDao> {

    @Autowired
    private DoctorDao doctorDao;

    public BaseDoctors findById(String code){
        return doctorDao.findById(code);
    }


}
