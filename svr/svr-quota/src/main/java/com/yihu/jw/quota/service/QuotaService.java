package com.yihu.jw.quota.service;

import com.yihu.jw.quota.dao.es.QuotaResultDao;
import com.yihu.jw.quota.model.QuotaResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chenweida on 2017/5/23.
 */
@Service
public class QuotaService {
    @Autowired
    private QuotaResultDao quotaResultDao;

    public QuotaResult findById(String id) {
        QuotaResult quotaResult=quotaResultDao.findById(id);
        return quotaResult;
    }

    public QuotaResult findBy_Id(String id) {
        QuotaResult quotaResult=quotaResultDao.findOne(id);
        return quotaResult;
    }
}
