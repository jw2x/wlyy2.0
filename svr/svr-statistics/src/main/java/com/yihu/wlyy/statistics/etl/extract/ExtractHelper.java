package com.yihu.wlyy.statistics.etl.extract;

import com.yihu.jw.entity.base.statistics.JobConfigDO;
import com.yihu.wlyy.statistics.etl.extract.db.DBExtract;
import com.yihu.wlyy.statistics.etl.extract.db.ESExtract;
import com.yihu.wlyy.statistics.util.SpringUtil;
import com.yihu.wlyy.statistics.vo.DataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by chenweida on 2017/6/1.
 */
@Component
@Scope("prototype")
public class ExtractHelper {
    private String startTime;
    private String endTime;

    private Logger logger = LoggerFactory.getLogger(ExtractHelper.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Value("${wlyy.im.databaseName}")
    private String imDatabaseName;


    /**
     * 公共的抽取数据
     *
     * @param wlyyJobConfigVO
     * @return
     * @throws Exception
     */
    public List<DataModel> extractData(JobConfigDO wlyyJobConfigVO, String startTime, String endTime, String year, String timeLevel) throws Exception {
        try {
            this.startTime = startTime;
            this.endTime = endTime;
            String timeSql = "";
            if ("1".equals(timeLevel)) {
                timeSql = wlyyJobConfigVO.getSqlDay();
            } else if ("2".equals(timeLevel)) {
                timeSql = wlyyJobConfigVO.getSqlYear();
            } else {
                timeSql = wlyyJobConfigVO.getSqlDay();
            }
            //如果为null 初始化
            if(StringUtils.isEmpty(timeSql)){
                timeSql="";
            }
            //设置时间
            String sql = initSql(wlyyJobConfigVO.getSql() + "  " + timeSql, startTime, endTime, year);
            String sqlCount = initSql(wlyyJobConfigVO.getSqlCount() + "  " + timeSql, startTime, endTime, year);
            logger.info(" sql: " + sql);
            logger.info(" sqlCount: " + sqlCount);

            //如果是数据库从数据库抽取
            if (StringUtils.isEmpty(wlyyJobConfigVO.getExtractType())||"1".equals(wlyyJobConfigVO.getExtractType())){
                //抽取数据库
                return SpringUtil.getBean(DBExtract.class).extractByPage(
                        DataModel.class,
                        sql,
                        sqlCount,
                        true,
                        jdbcTemplate);
            }else if("2".equals(wlyyJobConfigVO.getExtractType())){
                //抽取ES
               return SpringUtil.getBean(ESExtract.class).extract(wlyyJobConfigVO);
            }
        } catch (Exception e) {
            logger.error("extract error:" + e.getMessage());
            logger.error("quotaVO str:" + wlyyJobConfigVO.toString());

        }
        return null;
    }


    public String initSql(String sql, String startTime, String endTime, String year) {
        return sql.replace("[startTime]", startTime).replace("[endTime]", endTime).replace("[year]", year).replace("[im]",imDatabaseName);
    }

}
