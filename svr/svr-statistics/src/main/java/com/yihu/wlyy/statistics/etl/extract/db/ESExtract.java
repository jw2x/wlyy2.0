package com.yihu.wlyy.statistics.etl.extract.db;

import com.yihu.jw.entity.base.statistics.DimensionQuotaDO;
import com.yihu.jw.entity.base.statistics.JobConfigDO;
import com.yihu.wlyy.statistics.dao.WlyyDimensionQuotaDao;
import com.yihu.wlyy.statistics.util.ElasticsearchUtil;
import com.yihu.wlyy.statistics.vo.DataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2016.10.16.
 * ES抽取
 */
@Component
@Scope("prototype")
public class ESExtract<T> {
    @Autowired
    private ElasticsearchUtil elasticsearchUtil;
    @Value("${es.type}")
    private String esType;
    @Value("${es.index}")
    private String esIndex;
    @Autowired
    private WlyyDimensionQuotaDao dimensionQuotaDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * ES抽取
     *
     * @param wlyyJobConfigVO
     * @return
     */
    public List<DataModel> extract(JobConfigDO wlyyJobConfigVO) {


        //扩展维度

        String sql= initSql(wlyyJobConfigVO.getSql(),wlyyJobConfigVO.getStartTime(),wlyyJobConfigVO.getEndTime());
        String re = "";
        //得到该指标的维度
        List<DimensionQuotaDO> dimensionQuotas = dimensionQuotaDao.findDimensionQuotasByQuotaCode(wlyyJobConfigVO.getQuotaId());
        if (dimensionQuotas != null && dimensionQuotas.size() > 0) {
            for(int i=1;i<=dimensionQuotas.size();i++){
                sql+=",slaveKey"+i;
                re+=","+dimensionQuotas.get(i-1).getKey()+" AS slaveKey"+i;
//                dictSql = one.getDictSql();
//                List<Map<String, Object>> temp = jdbcTemplate.queryForList(dictSql);

            }
        }
        sql = sql.replace("[re]", re);
//        sql="SELECT adminTeamCode AS team ,COUNT(*) AS result1  ,isRead AS slaveKey1   FROM health_edu_article_patient_test3 where userType=2 AND createTime < '2018-01-04T17:00:00+0800'  AND createTime >= '2018-01-03T17:00:00+0800' group by team,slaveKey1";
        List<DataModel> dataModels = elasticsearchUtil.excute(sql,DataModel.class);

        return dataModels;
    }
    //初始化时间
    public String initSql(String sql, String startTime, String endTime) {

        return sql.replace("[startTime]", startTime).replace("[endTime]", endTime);
    }
}
