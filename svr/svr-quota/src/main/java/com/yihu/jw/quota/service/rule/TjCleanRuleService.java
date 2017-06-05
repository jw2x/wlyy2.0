package com.yihu.jw.quota.service.rule;

import com.yihu.jw.quota.model.jpa.dimension.TjDimensionSlave;
import com.yihu.jw.quota.model.jpa.rule.TjQuotaRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.JDBCType;
import java.util.List;

/**
 * Created by chenweida on 2017/6/1.
 */
@Service
public class TjCleanRuleService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<TjQuotaRule> findByQuotaCode(String code) {
        String sql="SELECT " +
                "  qr.*, " +
                "cr.type " +
                "FROM " +
                "  tj_clean_rule cr, " +
                "  tj_quota_rule qr " +
                "WHERE " +
                "  cr.`code`=qr.rule_code " +
                "AND qr.quota_code = ?";
        List<TjQuotaRule> quotaDataSources = jdbcTemplate.query(sql, new BeanPropertyRowMapper(TjQuotaRule.class), code);
        return quotaDataSources;
    }
}
