package com.yihu.jw.business.sms.service;

import com.yihu.jw.business.sms.dao.SmsDao;
import com.yihu.jw.business.sms.model.BaseSms;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by chenweida on 2017/5/22.
 */
@Service
public class SmsService  extends BaseJpaService<BaseSms, SmsDao> {
    @Autowired
    private SmsDao smsDao;

    @Transactional
    public BaseSms createSms(BaseSms sms) throws ApiException {
        return smsDao.save(sms);
    }

    @Transactional
    public BaseSms updateSms(BaseSms sms) {
        return smsDao.save(sms);
    }

}