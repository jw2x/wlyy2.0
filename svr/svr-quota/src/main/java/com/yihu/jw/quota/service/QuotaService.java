package com.yihu.jw.quota.service;

import com.yihu.jw.quota.dao.es.QuotaResultDao;
import com.yihu.jw.quota.model.es.QuotaResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    public String save() {
        QuotaResult wlyyQuotaResult=new QuotaResult();
        wlyyQuotaResult.setDel("1");
        wlyyQuotaResult.setOrgCode("orgtest");
        wlyyQuotaResult.setOrgName("机构测试");
        wlyyQuotaResult.setCreateTime(new Date());

        quotaResultDao.save(wlyyQuotaResult);
        return null;
    }
}
