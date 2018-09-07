package com.yihu.jw.base.service.sms;

import com.yihu.jw.base.dao.sms.SmsDao;
import com.yihu.jw.entity.base.sms.SmsDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.stereotype.Service;

/**
 * Service - 短信记录
 * Created by progr1mmer on 2018/9/6.
 */
@Service
public class SmsService extends BaseJpaService<SmsDO, SmsDao>{

}
