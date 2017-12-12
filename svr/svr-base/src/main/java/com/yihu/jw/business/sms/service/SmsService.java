package com.yihu.jw.business.sms.service;

import com.yihu.jw.base.sms.BaseSmsDO;
import com.yihu.jw.business.sms.dao.SmsDao;
import com.yihu.jw.exception.ApiException;
import com.yihu.base.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by chenweida on 2017/5/22.
 */
@Service
public class SmsService  extends BaseJpaService<BaseSmsDO, SmsDao> {
    @Autowired
    private SmsDao smsDao;

    @Transactional
    public BaseSmsDO createSms(BaseSmsDO sms) throws ApiException {

        return smsDao.save(sms);
    }

    @Transactional
    public BaseSmsDO updateSms(BaseSmsDO sms) {
        return smsDao.save(sms);
    }

}