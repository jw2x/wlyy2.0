package com.yihu.jw.quota.service.compute;

import com.yihu.jw.quota.dao.jpa.compute.TjComputeDao;
import com.yihu.jw.quota.model.jpa.compute.TjCompute;
import com.yihu.jw.quota.model.jpa.rule.TjQuotaRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenweida on 2017/6/1.
 */
@Service
public class TjComputeService {
    @Autowired
    private TjComputeDao computeDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public TjCompute findByQuotaCode(String code) {
        String sql=" SELECT " +
                "  tc.* " +
                " FROM " +
                "  tj_compute tc, " +
                "  tj_quota_compute tqc " +
                " WHERE " +
                "  tc.`code` = tqc.compute_code " +
                " AND tqc.quota_code = ? ";
        List<TjCompute> quotaDataSources = jdbcTemplate.query(sql, new BeanPropertyRowMapper(TjCompute.class), code);
        return quotaDataSources.get(0);
    }
}
