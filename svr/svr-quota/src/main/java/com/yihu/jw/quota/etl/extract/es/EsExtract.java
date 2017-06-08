package com.yihu.jw.quota.etl.extract.es;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLQueryExpr;
import com.alibaba.druid.sql.parser.ParserException;
import com.alibaba.druid.sql.parser.SQLExprParser;
import com.alibaba.druid.sql.parser.Token;
import com.yihu.jw.quota.etl.Contant;
import com.yihu.jw.quota.etl.model.EsConfig;
import com.yihu.jw.quota.etl.save.es.ElasticFactory;
import com.yihu.jw.quota.model.jpa.dimension.TjQuotaDimensionMain;
import com.yihu.jw.quota.model.jpa.dimension.TjQuotaDimensionSlave;
import com.yihu.jw.quota.vo.DictModel;
import com.yihu.jw.quota.vo.QuotaVO;
import com.yihu.jw.quota.vo.SaveModel;
import net.sf.json.JSONObject;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.collect.HppcMaps;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.InternalAggregations;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.aggregations.metrics.valuecount.InternalValueCount;
import org.nlpcn.es4sql.domain.Select;
import org.nlpcn.es4sql.parse.ElasticSqlExprParser;
import org.nlpcn.es4sql.parse.SqlParser;
import org.nlpcn.es4sql.query.AggregationQueryAction;
import org.nlpcn.es4sql.query.DefaultQueryAction;
import org.nlpcn.es4sql.query.SqlElasticSearchRequestBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by chenweida on 2017/6/6.
 */
@Component
@Scope("prototype")
public class EsExtract {
    private Logger logger = LoggerFactory.getLogger(EsExtract.class);
    @Autowired
    private ElasticFactory elasticFactory;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String startTime;
    private String endTime;
    private String timeLevel;
    private String saasid;
    private QuotaVO quotaVO;
    private EsConfig esConfig;


    public List<SaveModel> extract(List<TjQuotaDimensionMain> qdm,//主维度
                                   List<TjQuotaDimensionSlave> qds,//细维度
                                   String startTime,//开始时间
                                   String endTime, //结束时间
                                   String timeLevel, //时间维度  1日,2 周, 3 月,4 年
                                   String saasid,//saasid
                                   QuotaVO quotaVO,//指标code
                                   EsConfig esConfig //es配置
    ) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.timeLevel = timeLevel;
        this.saasid = saasid;
        this.quotaVO = quotaVO;
        this.esConfig = esConfig;

        //拼凑查询的sql
        Map<String, TjQuotaDimensionMain> sqls = getSql(qdm, qds);
        //根据sql查询ES
        return queryEsBySql(sqls, qds);

    }

    private Map<String, SaveModel> setAllSlaveData(Map<String, SaveModel> allData, List<DictModel> dictData) {
        try {
            Map<String, SaveModel> returnAllData = new HashMap<>();
            for (Map.Entry<String, SaveModel> one : allData.entrySet()) {
                for (int i = 0; i < dictData.size(); i++) {
                    DictModel dictOne = dictData.get(i);
                    //设置新key
                    StringBuffer newKey = new StringBuffer(one.getKey() + "-" + dictOne.getCode());
                    //设置新的value
                    SaveModel saveModelTemp = new SaveModel();
                    BeanUtils.copyProperties(one.getValue(), saveModelTemp);

                    StringBuffer keyMethodName = new StringBuffer("setSlaveKey" + (i + 1));
                    StringBuffer nameMethodName = new StringBuffer("setSlaveKey" + (i + 1) + "Name");

                    SaveModel.class.getMethod(keyMethodName.toString(), String.class).invoke(saveModelTemp, dictOne.getCode());
                    SaveModel.class.getMethod(nameMethodName.toString(), String.class).invoke(saveModelTemp, dictOne.getName());
                    returnAllData.put(newKey.toString(), saveModelTemp);
                }
            }
            return returnAllData;
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * @param allData
     * @param dictData
     * @param dictType
     */
    private void setAllData(Map<String, SaveModel> allData, List<SaveModel> dictData, String dictType) {
        switch (dictType) {
            case Contant.main_dimension_areaLevel.area_province: {
                //设置省的全部的值
                dictData.stream().forEach(one -> {
                    //StringBuffer key = new StringBuffer(one.getProvince());
                    setOneData(allData, one.getProvince(), one, Contant.main_dimension_areaLevel.area_province);
                });
                break;
            }
            case Contant.main_dimension_areaLevel.area_city: {
                //设置市的全部的值
                dictData.stream().forEach(one -> {
                    //StringBuffer key = new StringBuffer(one.getProvince() + "-" + one.getCity());
                    setOneData(allData, one.getCity(), one, Contant.main_dimension_areaLevel.area_city);
                });
                break;
            }
            case Contant.main_dimension_areaLevel.area_town: {
                //设置区的全部的值
                dictData.stream().forEach(one -> {
                    //StringBuffer key = new StringBuffer(one.getProvince() + "-" + one.getCity() + "-" + one.getTown());
                    setOneData(allData, one.getTown(), one, Contant.main_dimension_areaLevel.area_town);
                });
                break;
            }
            case Contant.main_dimension_areaLevel.area_org: {
                //设置机构
                dictData.stream().forEach(one -> {
                    // StringBuffer key = new StringBuffer(one.getProvince() + "-" + one.getCity() + "-" + one.getTown() + "-" + one.getHospital());
                    setOneData(allData, one.getHospital(), one, Contant.main_dimension_areaLevel.area_org);
                });
                break;
            }
            case Contant.main_dimension_areaLevel.area_team: {
                //设置团队
                dictData.stream().forEach(one -> {
                    // StringBuffer key = new StringBuffer(one.getProvince() + "-" + one.getCity() + "-" + one.getTown() + "-" + one.getHospital() + "-" + one.getTeam());
                    setOneData(allData, one.getTeam(), one, Contant.main_dimension_areaLevel.area_team);
                });
                break;
            }
        }
    }

    private void setOneData(Map<String, SaveModel> allData, String key, SaveModel one, String areaLevel) {
        one.setAreaLevel(areaLevel);
        one.setResult(0);
        one.setCreateTime(new Date());
        one.setQuotaDate(startTime);
        one.setQuotaCode(quotaVO.getCode());
        one.setTimeLevel(timeLevel);
        one.setSaasId(saasid);
        allData.put(key, one);
    }

    private  List<SaveModel> queryEsBySql(Map<String, TjQuotaDimensionMain> sqls, List<TjQuotaDimensionSlave> tjQuotaDimensionSlaves) {
        List<SaveModel> returnList = new ArrayList<>();
        //初始化es链接
        esConfig = (EsConfig) JSONObject.toBean(JSONObject.fromObject(esConfig), EsConfig.class);
        //初始化链接
        Client client = elasticFactory.getClient(esConfig.getHost(), esConfig.getPort(), null);
        for (Map.Entry<String, TjQuotaDimensionMain> one : sqls.entrySet()) {
            logger.info("excute sql:" + one.getKey());
            try {
                SQLExprParser parser = new ElasticSqlExprParser(one.getKey());
                SQLExpr expr = parser.expr();
                if (parser.getLexer().token() != Token.EOF) {
                    throw new ParserException("illegal sql expr : " + one);
                }
                SQLQueryExpr queryExpr = (SQLQueryExpr) expr;
                //通过抽象语法树，封装成自定义的Select，包含了select、from、where group、limit等
                Select select = null;
                select = new SqlParser().parseSelect(queryExpr);

                AggregationQueryAction action = null;
                DefaultQueryAction queryAction = null;
                SqlElasticSearchRequestBuilder requestBuilder = null;
                if (select.isAgg) {
                    //包含计算的的排序分组的
                    action = new AggregationQueryAction(client, select);
                    requestBuilder = action.explain();
                } else {
                    //封装成自己的Select对象
                    queryAction = new DefaultQueryAction(client, select);
                    requestBuilder = queryAction.explain();
                }
                //之后就是对ES的操作
                SearchResponse response = (SearchResponse) requestBuilder.get();
                StringTerms stringTerms = (StringTerms) response.getAggregations().asList().get(0);
                Iterator<Terms.Bucket> gradeBucketIt = stringTerms.getBuckets().iterator();
                //里面存放的数据 例  350200-5-2-2    主维度  细维度1  细维度2  值
                Map<String,Integer> map = new HashMap<>();
                //递归解析json
                expainJson(gradeBucketIt, map, null);

                Map<String, SaveModel> allData = new HashMap<>();
                //初始化主细维度
                allData= initDimension(tjQuotaDimensionSlaves, one, allData);

                for(Map.Entry<String,Integer> oneMap:map.entrySet()){
                    String key=oneMap.getKey();
                    Integer value=oneMap.getValue();
                    SaveModel saveModel=allData.get(key);
                    if(saveModel!=null){
                        saveModel.setResult(value);
                        returnList.add(saveModel);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return returnList;
    }
    /**
     * 初始化主细维度
     */
    private  Map<String, SaveModel>  initDimension(List<TjQuotaDimensionSlave> tjQuotaDimensionSlaves, Map.Entry<String, TjQuotaDimensionMain> one, Map<String, SaveModel> allData) {
        TjQuotaDimensionMain quotaDimensionMain = one.getValue();
        //查询字典数据
        List<SaveModel> dictData = jdbcTemplate.query(quotaDimensionMain.getDictSql(), new BeanPropertyRowMapper(SaveModel.class));
        //设置到map里面
        setAllData(allData, dictData, quotaDimensionMain.getType());


        for (int i = 0; i < tjQuotaDimensionSlaves.size(); i++) {
           List<DictModel> dictDataSlave = jdbcTemplate.query(tjQuotaDimensionSlaves.get(i).getDictSql(), new BeanPropertyRowMapper(DictModel.class));
            allData = setAllSlaveData(allData, dictDataSlave);
        }
        return allData;
    }

    /**
     * 递归解析json
     *
     * @param gradeBucketIt
     * @param map
     * @param sb
     */
    private void expainJson(Iterator<Terms.Bucket> gradeBucketIt,Map<String,Integer>map, StringBuffer sb) {
        while (gradeBucketIt.hasNext()) {
            StringTerms.Bucket b = (StringTerms.Bucket) gradeBucketIt.next();
            if (b.getAggregations().asList().get(0) instanceof StringTerms) {
                StringTerms stringTermsCh = (StringTerms) b.getAggregations().asList().get(0);
                Iterator<Terms.Bucket> gradeBucketItCh = stringTermsCh.getBuckets().iterator();
                while (gradeBucketItCh.hasNext()) {
                    StringBuffer sbTemp = new StringBuffer((sb == null ? "" : (sb.toString() + "-")) + b.getKey());
                    expainJson(gradeBucketItCh, map, sbTemp);
                }
            } else {
                InternalValueCount count = (InternalValueCount) b.getAggregations().asList().get(0);
                map.put(new StringBuffer(sb.toString() + "-" + b.getKey()).toString() , (int)count.getValue());
            }
        }
    }

    /**
     * @param tjQuotaDimensionMains
     * @param tjQuotaDimensionSlaves
     * @return
     */
    private Map<String, TjQuotaDimensionMain> getSql(List<TjQuotaDimensionMain> tjQuotaDimensionMains, List<TjQuotaDimensionSlave> tjQuotaDimensionSlaves) {
        Map<String, TjQuotaDimensionMain> sqlS = new HashMap<>();
        for (int j = 0; j < tjQuotaDimensionMains.size(); j++) {
            TjQuotaDimensionMain one = tjQuotaDimensionMains.get(j);
            String tableName = esConfig.getIndex();
            if (StringUtils.isEmpty(one.getKey())) {
                continue;
            }
            StringBuffer allField = new StringBuffer(one.getKey() + ",");// 例如区  town,sex,age
            StringBuffer AllGroupBy = new StringBuffer(one.getKey() + ",");// 例如区  town,sex,age
            for (int i = 0; i < tjQuotaDimensionSlaves.size(); i++) {
                allField.append(tjQuotaDimensionSlaves.get(i).getKey());
                AllGroupBy.append(tjQuotaDimensionSlaves.get(i).getKey());
                if (i != (tjQuotaDimensionSlaves.size() - 1)) {
                    allField.append(",");
                    AllGroupBy.append(",");
                }
            }
            //拼凑where语句
            StringBuffer whereSql = new StringBuffer(" saasId= '" + saasid + "' and " + esConfig.getTimekey() + " < '" + endTime + "'");
            //是否是增量
            if (Contant.quota.dataLeval_oneDay.endsWith(quotaVO.getDataLevel())) {
                whereSql.append(" and " + esConfig.getTimekey() + " > '" + startTime + "'");
            }
            StringBuffer sql = new StringBuffer("select " + allField + " ,count(*) result from " + tableName + " where " + whereSql + " group by " + AllGroupBy);

            sqlS.put(sql.toString(), one);
        }
        return sqlS;
    }
}
