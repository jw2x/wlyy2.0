package com.yihu.wlyy.statistics.util;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLQueryExpr;
import com.alibaba.druid.sql.parser.SQLExprParser;
import com.yihu.wlyy.statistics.etl.save.es.ElasticFactory;
import com.yihu.wlyy.statistics.vo.DataModel;
import com.yihu.wlyy.statistics.vo.SaveModel;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.nlpcn.es4sql.domain.Select;
import org.nlpcn.es4sql.jdbc.ObjectResult;
import org.nlpcn.es4sql.jdbc.ObjectResultsExtractor;
import org.nlpcn.es4sql.parse.ElasticSqlExprParser;
import org.nlpcn.es4sql.parse.SqlParser;
import org.nlpcn.es4sql.query.AggregationQueryAction;
import org.nlpcn.es4sql.query.DefaultQueryAction;
import org.nlpcn.es4sql.query.SqlElasticSearchRequestBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chenweida on 2017/7/17.
 * SELECT town,townName,sum(result1) result1 FROM wlyy_quota_test
 * where quotaCode='1'
 * group by town,townName ， date_histogram(field='quotaDate','interval'='week')
 */
@Component
public class ElasticsearchUtil {
    private Logger logger= LoggerFactory.getLogger(ElasticsearchUtil.class);
    @Autowired
    private ElasticFactory elasticFactory;
    @Value("${es.type}")
    private String esType;
    @Value("${es.index}")
    private String esIndex;

    /**
     * 折线图
     *
     * @param quotaCode 指标quotacode
     * @param code      机构code，或者区code或者团队code或者城市code
     * @param startDate 开始日期 yyyy-MM-dd
     * @param endDate   结束日期 yyyy-MM-dd
     * @param timeLevel 1增量 2到达量
     * @param areaLevel 1 省 2 市 3 区县 4 机构 5团队
     * @param interval  1日 2周 3月
     * @return
     */
    public List<SaveModel> findQuotaLines(String quotaCode,
                                          String code,
                                          String startDate,
                                          String endDate,
                                          String timeLevel,
                                          String areaLevel,
                                          String interval) {

        //时间格式转换  yyyy-MM-dd转成 2017-07-17T00:00:00+0800
        startDate = changeDate(startDate);
        //时间格式转换  yyyy-MM-dd转成 2017-07-17T00:00:00+0800
        endDate = changeDate(endDate);
        StringBuffer sql = new StringBuffer();
        StringBuffer groupBy = new StringBuffer();
        if (SaveModel.teamLevel.equals(areaLevel)) {
            sql.append("select team,teamName,result1,result2 from "+esType+" where team='" + code + "'");
        } else if (SaveModel.OrgLevel.equals(areaLevel)) {
            sql.append("select hospital,hospitalName,sum(result1) result1,sum(result2) result2 from "+esType+" where hospital='" + code + "'");
            groupBy.append(" group by hospital,hospitalName");
        } else if (SaveModel.townLevel.equals(areaLevel)) {
            sql.append("select town,townName,sum(result1) result1,sum(result2) result2 from "+esType+" where town='" + code + "'");
            groupBy.append(" group by town,townName");
        } else if (SaveModel.cityLevel.equals(areaLevel)) {
            sql.append("select city,cityName,sum(result1) result1,sum(result2) result2 from "+esType+" where city='" + code + "'");
            groupBy.append(" group by city,cityName");
        }

        sql.append(" and quotaCode='" + quotaCode + "'  ");
        sql.append(" and timeLevel='" + timeLevel + "'  ");
        sql.append(" and areaLevel='5'");
        sql.append(" and quotaDate >='" + startDate + "'  ");
        sql.append(" and quotaDate <='" + endDate + "'  ");

        //根据时间维度分组
        if (SaveModel.interval_month.equals(interval)) {
            groupBy.append(" ,date_histogram(field='quotaDate','interval'='month') ");
        } else if (SaveModel.interval_week.equals(interval)) {
            groupBy.append(" ,date_histogram(field='quotaDate','interval'='week') ");
        } else if (SaveModel.interval_day.equals(interval)) {
            groupBy.append(" ,date_histogram(field='quotaDate','interval'='1d') ");
        }

        sql.append(groupBy);
        return excute(sql.toString());
    }

    /**
     * 查询某个指标某一天的量
     *
     * @param quotaCode 指标quotacode
     * @param quotaDate 时间 yyyy-MM-dd
     * @param timeLevel 1增量 2到达量
     * @param areaLevel 1 省 2 市 3 区县 4 机构 5团队
     * @return
     */
    public List<SaveModel> findOneDateQuota(String quotaCode,
                                            String code,
                                            String quotaDate,
                                            String timeLevel,
                                            String areaLevel) {
        //时间格式转换  yyyy-MM-dd转成 2017-07-17T00:00:00+0800
        quotaDate = changeDate(quotaDate);

        StringBuffer sql = new StringBuffer();
        StringBuffer groupBy = new StringBuffer();
        if (SaveModel.teamLevel.equals(areaLevel)) {
            sql.append("select team,teamName,result1,result2 from "+esType+" where team='" + code + "'");
        } else if (SaveModel.OrgLevel.equals(areaLevel)) {
            sql.append("select hospital,hospitalName,sum(result1) result1,sum(result2) result2 from "+esType+" where hospital='" + code + "'");
            groupBy.append(" group by hospital,hospitalName");
        } else if (SaveModel.townLevel.equals(areaLevel)) {
            sql.append("select town,townName,sum(result1) result1,sum(result2) result2 from "+esType+" where town='" + code + "'");
            groupBy.append(" group by town,townName");
        } else if (SaveModel.cityLevel.equals(areaLevel)) {
            sql.append("select city,cityName,sum(result1) result1,sum(result2) result2 from "+esType+" where city='" + code + "'");
            groupBy.append(" group by city,cityName");
        }

        sql.append(" and quotaCode='" + quotaCode + "'  ");
        sql.append(" and timeLevel='" + timeLevel + "'  ");
        sql.append(" and areaLevel='5'");
        sql.append(" and quotaDate='" + quotaDate + "'");


        sql.append(groupBy);
        return excute(sql.toString());
    }
    /**
     * 查询某个2级维度指标某一天的数据
     *
     * @param quotaCode 指标quotacode
     * @param quotaDate 时间 yyyy-MM-dd
     * @param code      机构code或者团队code或者town code或者city code
     * @param timeLevel 1增量 2到达量
     * @param areaLevel 1 省 2 市 3 区县 4 机构 5团队
     * @return
     */
    public List<SaveModel> findOneDateQuotaLevel2(String quotaCode,
                                                  String code,
                                                  String quotaDate,
                                                  String timeLevel,
                                                  String areaLevel) {

        //时间格式转换  yyyy-MM-dd转成 2017-07-17T00:00:00+0800
        quotaDate = changeDate(quotaDate);

        StringBuffer sql = new StringBuffer();
        StringBuffer groupBy = new StringBuffer();
        if (SaveModel.teamLevel.equals(areaLevel)) {
            sql.append("select team,teamName,slaveKey1,slaveKey1Name,slaveKey2,slaveKey2Name,result1,result2 from "+esType+" where team='" + code + "'");
            groupBy.append("  group by slaveKey1,slaveKey1Name,slaveKey2,slaveKey2Name");
        } else if (SaveModel.OrgLevel.equals(areaLevel)) {
            sql.append("select hospital,hospitalName,slaveKey1,slaveKey1Name,slaveKey2,slaveKey2Name,sum(result1) result1,sum(result2) result2 from "+esType+" where hospital='" + code + "'");
            groupBy.append("  group by slaveKey1,slaveKey1Name,slaveKey2,slaveKey2Name");
        } else if (SaveModel.townLevel.equals(areaLevel)) {
            sql.append("select town,townName,slaveKey1,slaveKey1Name,slaveKey2,slaveKey2Name,sum(result1) result1,sum(result2) result2 from "+esType+" where town='" + code + "'");
            groupBy.append("  group by slaveKey1,slaveKey1Name,slaveKey2,slaveKey2Name");
        } else if (SaveModel.cityLevel.equals(areaLevel)) {
            sql.append("select city,cityName,slaveKey1,slaveKey1Name,slaveKey2,slaveKey2Name,sum(result1) result1,sum(result2) result2 from "+esType+" where city='" + code + "'");
            groupBy.append("  group by slaveKey1,slaveKey1Name,slaveKey2,slaveKey2Name");
        }

        sql.append(" and quotaCode='" + quotaCode + "'  ");
        sql.append(" and timeLevel='" + timeLevel + "'  ");
        sql.append(" and areaLevel='5'");
        sql.append(" and quotaDate='" + quotaDate + "'");



        sql.append(groupBy);
        return excute(sql.toString());
    }
    /**
     * 查询某个一级维度指标某一天的数据
     *
     * @param quotaCode 指标quotacode
     * @param quotaDate 时间 yyyy-MM-dd
     * @param code      机构code或者团队code或者town code或者city code
     * @param timeLevel 1增量 2到达量
     * @param areaLevel 1 省 2 市 3 区县 4 机构 5团队
     * @return
     */
    public List<SaveModel> findOneDateQuotaLevel1(String quotaCode,
                                                  String code,
                                                  String quotaDate,
                                                  String timeLevel,
                                                  String areaLevel) {

        //时间格式转换  yyyy-MM-dd转成 2017-07-17T00:00:00+0800
        quotaDate = changeDate(quotaDate);

        StringBuffer sql = new StringBuffer();
        StringBuffer groupBy = new StringBuffer();
        if (SaveModel.teamLevel.equals(areaLevel)) {
            sql.append("select team,teamName,slaveKey1,slaveKey1Name,result1,result2 from "+esType+" where team='" + code + "'");
            groupBy.append("  group by team,teamName,slaveKey1,slaveKey1Name");
        } else if (SaveModel.OrgLevel.equals(areaLevel)) {
            sql.append("select hospital,hospitalName,slaveKey1,slaveKey1Name,sum(result1) result1,sum(result2) result2 from "+esType+" where hospital='" + code + "'");
            groupBy.append("  group by hospital,hospitalName,slaveKey1,slaveKey1Name");
        } else if (SaveModel.townLevel.equals(areaLevel)) {
            sql.append("select town,townName,slaveKey1,slaveKey1Name,sum(result1) result1,sum(result2) result2 from "+esType+" where town='" + code + "'");
            groupBy.append("  group by town,townName,slaveKey1,slaveKey1Name");
        } else if (SaveModel.cityLevel.equals(areaLevel)) {
            sql.append("select city,cityName,slaveKey1,slaveKey1Name,sum(result1) result1,sum(result2) result2 from "+esType+" where city='" + code + "'");
            groupBy.append("  group by city,cityName,slaveKey1,slaveKey1Name");
        }

        sql.append(" and quotaCode='" + quotaCode + "'  ");
        sql.append(" and timeLevel='" + timeLevel + "'  ");
        sql.append(" and areaLevel='5'");
        sql.append(" and quotaDate='" + quotaDate + "'");



        sql.append(groupBy);
        return excute(sql.toString());
    }


    /**
     * 查询某一天父level下的子level 例如 查询市下面的团队，或者区下面的团队
     *
     * @param quotaCode      指标code
     * @param code           机构code或者团队code或者town code或者city code
     * @param quotaDate      指标code
     * @param timeLevel      1增量 2到达量
     * @param areaLevel      父arealevel
     * @param childAreaLevel 子arealevel
     * @return
     */
    public List<SaveModel> findOneDateQuotaByChllevel(String quotaCode,
                                                      String code,
                                                      String quotaDate,
                                                      String timeLevel,
                                                      String areaLevel,
                                                      String childAreaLevel) {

        //时间格式转换  yyyy-MM-dd转成 2017-07-17T00:00:00+0800
        quotaDate = changeDate(quotaDate);

        StringBuffer sql = new StringBuffer();
        StringBuffer groupBy = new StringBuffer();
        //根据 childAreaLevel   group by
        if (SaveModel.teamLevel.equals(childAreaLevel)) {
            sql.append("select team,teamName,result1,result2 from "+esType+" where  ");
        } else if (SaveModel.OrgLevel.equals(childAreaLevel)) {
            sql.append("select hospital,hospitalName,sum(result1) result1,sum(result2) result2 from "+esType+" where  ");
            groupBy.append(" group by hospital,hospitalName");
        } else if (SaveModel.townLevel.equals(childAreaLevel)) {
            sql.append("select town,townName,sum(result1) result1,sum(result2) result2 from "+esType+" where ");
            groupBy.append(" group by town,townName");
        } else if (SaveModel.cityLevel.equals(childAreaLevel)) {
            sql.append("select city,cityName,sum(result1) result1,sum(result2) result2 from "+esType+" where  ");
            groupBy.append(" group by city,cityName");
        }

        sql.append("  quotaCode='" + quotaCode + "'  ");
        sql.append(" and timeLevel='" + timeLevel + "'  ");
        sql.append(" and areaLevel='5'");
        sql.append(" and quotaDate='" + quotaDate + "'");
        //查询code
        if (SaveModel.teamLevel.equals(areaLevel)) {
            sql.append(" and team='" + code + "'");
        } else if (SaveModel.OrgLevel.equals(areaLevel)) {
            sql.append(" and hospital='" + code + "'");
        } else if (SaveModel.townLevel.equals(areaLevel)) {
            sql.append(" and town='" + code + "'");
        } else if (SaveModel.cityLevel.equals(areaLevel)) {
            sql.append(" and city='" + code + "'");
        }
        sql.append(groupBy);
        return excute(sql.toString());
    }

    /**
     * 时间格式转换  yyyy-MM-dd转成 2017-07-17T00:00:00+0800
     *
     * @param quotaDate
     */
    private String changeDate(String quotaDate) {
        return quotaDate + "T00:00:00+0800";
    }

    /**
     * 执行sql查询es
     *
     * @param sql
     * @return
     */
    public List<SaveModel> excute(String sql) {
        List<SaveModel> saveModels = new ArrayList<>();
        try {
            SQLExprParser parser = new ElasticSqlExprParser(sql);
            SQLExpr expr = parser.expr();
            SQLQueryExpr queryExpr = (SQLQueryExpr) expr;
//            if (parser.getLexer().token() != Token.EOF) {
//                throw new ParserException("illegal sql expr : " + sql);
//            }
            Select select = null;
            select = new SqlParser().parseSelect(queryExpr);

            //通过抽象语法树，封装成自定义的Select，包含了select、from、where group、limit等
            AggregationQueryAction action = null;
            DefaultQueryAction queryAction = null;
            SqlElasticSearchRequestBuilder requestBuilder = null;
            if (select.isAgg) {
                //包含计算的的排序分组的
                action = new AggregationQueryAction(elasticFactory.getTransportClient(), select);
                requestBuilder = action.explain();
            } else {
                //封装成自己的Select对象
                Client client = elasticFactory.getTransportClient();
                queryAction = new DefaultQueryAction(client, select);
                requestBuilder = queryAction.explain();
            }
            SearchResponse response = (SearchResponse) requestBuilder.get();
            Object queryResult = null;
            if(sql.toUpperCase().indexOf("GROUP")!=-1||sql.toUpperCase().indexOf("SUM")!=-1){
                queryResult = response.getAggregations();
            }else{
                queryResult = response.getHits();
            }
            ObjectResult temp = new ObjectResultsExtractor(true, true, true).extractResults(queryResult, true);
            List<String> heads = temp.getHeaders();
            temp.getLines().stream().forEach(one -> {
                try {
                    SaveModel saveModel = new SaveModel();
                    for (int i = 0; i < one.size(); i++) {
                        String key = null;
                        Object value = one.get(i);
                        if(heads.get(i).startsWith("_")){
                            continue;
                        }
                        if (heads.get(i).contains("date_histogram")) {
                            key = "setQuotaDate";
                            value=DateUtil.strToDate(String.valueOf(value),"yyyy-MM-dd HH:mm:ss");
                        } else {
                            key = "set" + UpFirstStr(heads.get(i));
                        }

                        if (value instanceof String) {
                            SaveModel.class.getMethod(key, String.class).invoke(saveModel, value);
                        } else if (value instanceof Integer) {
                            SaveModel.class.getMethod(key, Integer.class).invoke(saveModel, value);
                        } else if (value instanceof Double) {
                            SaveModel.class.getMethod(key, Integer.class).invoke(saveModel, ((Double) value).intValue());
                        } else if (value instanceof java.util.Date) {
                            SaveModel.class.getMethod(key, java.util.Date.class).invoke(saveModel, value);
                        }
                    }
                    saveModels.add(saveModel);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saveModels;
    }

    public<T> List<T> excute(String sql, Class<T> clazz) {
        List saveModels = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXX");
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            //解决 group by之后默认是200的问题
            if (sql.toLowerCase().contains("group by")) {
                sql = sql + " limit 0,2000";
            }

            SQLExprParser parser = new ElasticSqlExprParser(sql);
            SQLExpr expr = parser.expr();
            SQLQueryExpr queryExpr = (SQLQueryExpr) expr;

            Select select = null;
            select = new SqlParser().parseSelect(queryExpr);

            //通过抽象语法树，封装成自定义的Select，包含了select、from、where group、limit等
            AggregationQueryAction action = null;
            DefaultQueryAction queryAction = null;
            SqlElasticSearchRequestBuilder requestBuilder = null;
            if (select.isAgg) {
                //包含计算的的排序分组的
                action = new AggregationQueryAction(elasticFactory.getTransportClient(), select);
                requestBuilder = action.explain();
            } else {
                //封装成自己的Select对象
                queryAction = new DefaultQueryAction(elasticFactory.getTransportClient(), select);
                requestBuilder = queryAction.explain();
            }
            SearchResponse response = (SearchResponse) requestBuilder.get();
            Object queryResult = null;
            if (sql.toUpperCase().indexOf("GROUP") != -1 || sql.toUpperCase().indexOf("SUM") != -1 || select.isAgg) {
                queryResult = response.getAggregations();
            } else {
                queryResult = response.getHits();
            }
            ObjectResult temp = new ObjectResultsExtractor(true, true, true).extractResults(queryResult, true);
            List<String> heads = temp.getHeaders();
            temp.getLines().forEach(one -> {
                Object saveModel = null;
                try {

                    saveModel = clazz.newInstance();
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
                for (int i = 0; i < one.size(); i++) {
                    try {
                        String key = null;
                        Object value = one.get(i);
                        if (heads.get(i).startsWith("_")) {
                            continue;
                        }
                        key = "set" + UpFirstStr(heads.get(i));
                        if (heads.get(i).contains("quotaDate") || heads.get(i).contains("createTime") || heads.get(i).contains("date_histogram")) {
                            if (heads.get(i).contains("date_histogram")) {
                                key = "setQuotaDate";
                            }

                            try {
                                //yyyy-MM-dd'T'HH:mm:ssXX
                                value = dateFormat.parse(String.valueOf(one.get(i)));
                            } catch (Exception e) {
                                //yyyy-MM-dd HH:mm:ss
                                try {
                                    value = dateFormat1.parse(String.valueOf(one.get(i)));
                                }catch (Exception e1){
                                    Timestamp ts = new Timestamp(Long.parseLong(String.valueOf(one.get(i))));
                                    try {
                                        Date date = new Date();
                                        date = ts;
                                        value =date;

                                    } catch (Exception e2) {
                                        value = String.valueOf(one.get(i));
                                    }
                                }

                            }
//                            value = DateUtil.strToDate(String.valueOf(value).replace("T00:00:00+0800", " 00:00:00"), "yyyy-MM-dd HH:mm:ss");
                        }

                        if (value instanceof String) {
                            clazz.getMethod(key, String.class).invoke(saveModel, value);
                        } else if (value instanceof Integer) {
                            clazz.getMethod(key, Integer.class).invoke(saveModel, value);
                        } else if (value instanceof Double) {
                            clazz.getMethod(key, Double.class).invoke(saveModel, value);
                        } else if (value instanceof java.util.Date) {
                            clazz.getMethod(key, java.util.Date.class).invoke(saveModel, value);
                        } else if (value instanceof java.util.List) {
                            clazz.getMethod(key, java.util.List.class).invoke(saveModel, value);
                        }
                    } catch (Exception e) {
                        logger.warn(e.getMessage());
                    }
                }
                saveModels.add(saveModel);
            });
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return saveModels;
    }

    /**
     * 执行sql查询es
     *
     * @param sql
     * @return
     */
    public List<DataModel> excuteDataModel(String sql) {
        List<DataModel> saveModels = new ArrayList<>();
        try {
            SQLExprParser parser = new ElasticSqlExprParser(sql);
            SQLExpr expr = parser.expr();
            SQLQueryExpr queryExpr = (SQLQueryExpr) expr;
//            if (parser.getLexer().token() != Token.EOF) {
//                throw new ParserException("illegal sql expr : " + sql);
//            }
            Select select = null;
            select = new SqlParser().parseSelect(queryExpr);

            //通过抽象语法树，封装成自定义的Select，包含了select、from、where group、limit等
            AggregationQueryAction action = null;
            DefaultQueryAction queryAction = null;
            SqlElasticSearchRequestBuilder requestBuilder = null;
            if (select.isAgg) {
                //包含计算的的排序分组的
                action = new AggregationQueryAction(elasticFactory.getTransportClient(), select);
                requestBuilder = action.explain();
            } else {
                //封装成自己的Select对象
                Client client = elasticFactory.getTransportClient();
                queryAction = new DefaultQueryAction(client, select);
                requestBuilder = queryAction.explain();
            }
            SearchResponse response = (SearchResponse) requestBuilder.get();
            Object queryResult = null;
            if(sql.toUpperCase().indexOf("GROUP")!=-1||sql.toUpperCase().indexOf("SUM")!=-1){
                queryResult = response.getAggregations();
            }else{
                queryResult = response.getHits();
            }
            ObjectResult temp = new ObjectResultsExtractor(true, true, true).extractResults(queryResult, true);
            List<String> heads = temp.getHeaders();
            for(List<Object> one:temp.getLines()){
//            temp.getLines().stream().forEach(one -> {
                try {
                    DataModel dataModel = new DataModel();
                    for (int i = 0; i < one.size(); i++) {
                        String key = null;
                        Object value = one.get(i);
                        if(heads.get(i).startsWith("_")){
                            continue;
                        }
                        if (heads.get(i).contains("date_histogram")) {
                            key = "setQuotaDate";
                            value=DateUtil.strToDate(String.valueOf(value),"yyyy-MM-dd HH:mm:ss");
                        } else {
                            key = "set" + UpFirstStr(heads.get(i));
                        }
                        try {
                            if (value instanceof String) {
                                 DataModel.class.getMethod(key, String.class).invoke(dataModel, value);
                            } else if (value instanceof Integer) {
                                DataModel.class.getMethod(key, Integer.class).invoke(dataModel, value);
                            } else if (value instanceof Double) {
                                DataModel.class.getMethod(key, Double.class).invoke(dataModel, value);
                            } else if (value instanceof java.util.Date) {
                                DataModel.class.getMethod(key, java.util.Date.class).invoke(dataModel, value);
                            }
                        }catch (Exception e){
                            logger.error(e.getMessage());
                        }
                    }
                    saveModels.add(dataModel);
                } catch (Exception e) {
                    e.printStackTrace();
                }}
//            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saveModels;
    }

    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    private String UpFirstStr(String str) {
        return str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toUpperCase());
    }
}
