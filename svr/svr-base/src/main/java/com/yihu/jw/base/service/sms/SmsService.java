package com.yihu.jw.base.service.sms;

import com.yihu.jw.base.dao.sms.SmsDao;
import com.yihu.jw.base.model.sms.BaseSms;
import com.yihu.jw.mysql.query.BaseJpaService;
import com.yihu.jw.restmodel.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by chenweida on 2017/5/22.
 */
@Service
public class SmsService  extends BaseJpaService<BaseSms, SmsDao>{
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