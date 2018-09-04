package com.yihu.wlyy.statistics.etl.convert;


import com.yihu.jw.entity.base.statistics.DimensionQuotaDO;
import com.yihu.wlyy.statistics.vo.DataModel;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by chenweida on 2017/6/5.
 */
public interface Convert {
    /**
     *
     * @param jdbcTemplate jdbc工具
     * @param oneList 需要赋值的数据
     * @param slaveLevel 维度 1 2 3
     * @param temp 指标类
     * @return
     */
    public List<DataModel> convert(JdbcTemplate jdbcTemplate,List<DataModel> oneList, String slaveLevel, DimensionQuotaDO temp );
}
