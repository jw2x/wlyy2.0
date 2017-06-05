package com.yihu.jw.quota.etl.extract;

import com.yihu.jw.quota.etl.Contant;
import com.yihu.jw.quota.etl.extract.db.DBExtract;
import com.yihu.jw.quota.etl.model.DbConfig;
import com.yihu.jw.quota.model.jpa.source.TjDataSource;
import com.yihu.jw.quota.model.jpa.source.TjQuotaDataSource;
import com.yihu.jw.quota.service.source.TjDataSourceService;
import com.yihu.jw.quota.util.SpringUtil;
import com.yihu.jw.quota.vo.DataModel;
import com.yihu.jw.quota.vo.QuotaVO;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenweida on 2017/6/1.
 */
@Component
@Scope("prototype")
public class ExtractHelper {
    private String startTime;
    private String endTime;
    @Autowired
    private TjDataSourceService dataSourceService;

    private Logger logger = LoggerFactory.getLogger(ExtractHelper.class);

    /**
     * 公共的抽取数据
     *
     * @param quotaVO
     * @return
     * @throws Exception
     */
    public Object extractData(QuotaVO quotaVO, String startTime, String endTime) throws Exception {
        try {
            this.startTime = startTime;
            this.endTime = endTime;

            //得到该指标的数据来源
            TjQuotaDataSource quotaDataSource = dataSourceService.findSourceByQuotaCode(quotaVO.getCode());
            //如果为空说明数据错误
            if (quotaDataSource == null) {
                throw new Exception("QuotaDataSource data error");
            }
            //判断数据源是什么类型,根据类型和数据库相关的配置信息抽取数据
            if (TjDataSource.type_mysql.equals(quotaDataSource.getType())) {
                //获取数据库配置
                DbConfig dbConfig = (DbConfig) JSONObject.toBean(JSONObject.fromObject(quotaDataSource.getConfigJson()), DbConfig.class);
                //通过配置得到数据库链接
                JdbcTemplate jdbcTemplate = initJdbcTemplate(dbConfig);
                //设置时间
                String sql = initSql(dbConfig.getSql());
                String sqlCount = initSql(dbConfig.getSqlCount());
                //抽取数据
                List<DataModel> ds1 = SpringUtil.getBean(DBExtract.class).extractByPage(
                        DataModel.class,
                        sql,
                        sqlCount,
                        true,
                        jdbcTemplate);

                if (StringUtils.isEmpty(dbConfig.getSecondSql())) {
                    return ds1;
                } else {
                    //设置时间
                    String secondSql = initSql(dbConfig.getSecondSql());
                    String secondSqlCount = initSql(dbConfig.getSecondSqlCount());
                    //抽取数据
                    List<DataModel> ds2 = SpringUtil.getBean(DBExtract.class).extractByPage(DataModel.class,
                            secondSql,
                            secondSqlCount,
                            true,
                            jdbcTemplate);
                    Map<String, List<DataModel>> params = new HashMap<String, List<DataModel>>();
                    params.put(Contant.extract.computeKey1, ds1);
                    params.put(Contant.extract.computeKey2, ds2);
                    return params;
                }
            } else if (TjDataSource.type_redis.equals(quotaDataSource.getType())) {

            }

        } catch (Exception e) {
            logger.error("extract error:" + e.getMessage());
            logger.error("quotaVOr:" + quotaVO.toString());

        }
        return null;
    }

    /**
     * 初始化sql
     * @param sql 替换时间
     * @return
     */
    private String initSql(String sql) {
        try {
            if (sql.contains(Contant.endTime)) {
                sql = sql.replace(Contant.endTime, endTime);
            }
            if (sql.contains(Contant.startTime)) {
                sql = sql.replace(Contant.startTime, startTime);
            }
            logger.info("sql init :"+sql);
        }catch (Exception e){

        }
        return sql;
    }

    /**
     * 初始化数据库的链接
     *
     * @param dbConfig
     * @return
     */
    private JdbcTemplate initJdbcTemplate(DbConfig dbConfig) {
        //初始化数据库链接
        DataSource ds = DataSourceBuilder.create().
                url(dbConfig.getUrl()).
                username(dbConfig.getUsername()).
                driverClassName(dbConfig.getDriver()).
                password(dbConfig.getPassword()).build();
        return new JdbcTemplate(ds);
    }
}
