package com.yihu.jw.quota.service.job;

import com.yihu.jw.quota.dao.jpa.TjQuotaDao;
import com.yihu.jw.quota.model.jpa.TjQuota;
import com.yihu.jw.quota.util.QuartzHelper;
import com.yihu.jw.quota.vo.QuotaVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author chenweida
 */
@Service
public class JobService {
    @Autowired
    private QuartzHelper quartzHelper;
    @Autowired
    private TjQuotaDao quotaDao;

    public void startNowById(String id) throws Exception {
        TjQuota tjQuota= quotaDao.findByCode(id);
        QuotaVo quotaVO =new QuotaVo();
        BeanUtils.copyProperties(tjQuota,quotaVO);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("quota", quotaVO);
        params.put("saasid", "testId");

        //往quartz框架添加任务
        if (!StringUtils.isEmpty(tjQuota.getJobClazz())) {
            quartzHelper.startNow(Class.forName(quotaVO.getJobClazz()),  UUID.randomUUID().toString().replace("-", ""), params);
        }
    }
}
