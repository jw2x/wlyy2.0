package com.yihu.jw.quota.service.dimension;

import com.yihu.jw.quota.model.jpa.dimension.TjDimensionSlave;
import com.yihu.jw.quota.model.jpa.dimension.TjQuotaDimensionSlave;
import com.yihu.jw.quota.model.jpa.source.TjQuotaDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenweida on 2017/6/1.
 */
@Service
public class TjDimensionSlaveService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<TjDimensionSlave> getDimensionSlaveByQuotaCode(String code) {
        String sql = "SELECT " +
                "  dm.* " +
                " FROM " +
                "  tj_dimension_slave dm, " +
                "  tj_quota_dimension_slave qdm " +
                " WHERE " +
                "  dm.`code` = qdm.quota_code " +
                " AND qdm.quota_code = ? ";
        List<TjDimensionSlave> quotaDataSources = jdbcTemplate.query(sql, new BeanPropertyRowMapper(TjDimensionSlave.class), code);
        return quotaDataSources;
    }

    public List<TjQuotaDimensionSlave> findTjQuotaDimensionSlaveByQuotaCode(String code) {
        String sql = "SELECT " +
                "  qdm.*,dm.type " +
                " FROM " +
                "  tj_dimension_slave dm, " +
                "  tj_quota_dimension_slave qdm " +
                " WHERE " +
                "  dm.`code` = qdm.quota_code " +
                " AND qdm.quota_code = ? order BY" +
                " qdm.sort asc ";
        List<TjQuotaDimensionSlave> quotaDataSources = jdbcTemplate.query(sql, new BeanPropertyRowMapper(TjQuotaDimensionSlave.class), code);
        return quotaDataSources;
    }
}
