package com.yihu.iot.service.common;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yihu.iot.datainput.enums.DataTypeEnum;
import com.yihu.iot.datainput.service.DataStandardConvertService;
import com.yihu.jw.datainput.Data;
import com.yihu.jw.datainput.StepInfoDO;
import org.apache.commons.lang.StringUtils;
//import org.apache.hadoop.hbase.util.CollectionUtils;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.beans.PropertyDescriptor;
import java.util.*;

@Component
public class ElasticSearchQueryGenerator {

    private static Map<String,Set<String>> fieldsMap = new HashMap<>();

    @PostConstruct
    public static void init(){
        Set<String> fieldsSet = new HashSet<>();
        PropertyDescriptor[] properties = BeanUtils.getPropertyDescriptors(Data.class);
        for(PropertyDescriptor field:properties){
            fieldsSet.add(field.getName());
        }
        fieldsMap.put("data",fieldsSet);
        Set<String> fieldsSet2 = new HashSet<>();
        PropertyDescriptor[] properties2 = BeanUtils.getPropertyDescriptors(StepInfoDO.class);
        for(PropertyDescriptor field:properties2){
            fieldsSet2.add(field.getName());
        }
        fieldsMap.put("stepInfoList",fieldsSet2);
    }

    /**
     * 构造es查询参数
     * @param jsonData
     *
    {
    "filter":[{"andOr":"and|or","condition":">|=|<|>=|<=|?","field":"<filed>","value":"<value>"},<{...}>],
    - 参数说明：andOr跟数据库的中的AND和OR相似；condition指条件匹配程度，?相当于数据库中的like；filed指检索的字段；value为检索的值
    "page":1,  - 参数格式：页码，默认1，int类型 不需要分页，传""
    "size":10, - 参数格式：条数，默认10，int类型 不需要分页，传""
    "sort":[
    {"key":{"order":"asc|desc"}}, - 参数格式：排序， key要排序的字段，order固定，取值asc或desc，不需要排序，传""
    {"key":{"order":"asc|desc"}}
    ]
    }
     * @return
     */
    public SearchSourceBuilder getQueryBuilder(String nestedPath, String jsonData) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //什么都不传的情况下，构造查询所有的语句
        if(StringUtils.isEmpty(jsonData)){
            MatchAllQueryBuilder allQueryBuilder = QueryBuilders.matchAllQuery();
            return searchSourceBuilder.query(allQueryBuilder);
        }

        JSONObject json = JSONObject.parseObject(jsonData);
        List<Map<String, Object>> filter = (List)json.getJSONArray("filter");
        int page = json.getIntValue("page") == 0 ? 1:json.getIntValue("page"); //从第一页开始
        int size = json.getIntValue("size") == 0 ? 1:json.getIntValue("size"); //默认值为1，最少获取一条记录
        JSONArray sort = json.getJSONArray("sort");
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        boolQueryBuilder = getBoolQueryBuilder(nestedPath,jsonData,false);//非嵌套的数据查询不需要nested

        searchSourceBuilder.from((page -1)*size);
        searchSourceBuilder.size(size);
        //排序
        if(!CollectionUtils.isEmpty(sort)){
            for(Object obj:sort){
                JSONObject object = JSONObject.parseObject(obj.toString());
                FieldSortBuilder fieldSortBuilder = null;
                for(String key:object.keySet()){
                    if(!CollectionUtils.isEmpty(fieldsMap.get(nestedPath)) && fieldsMap.get(nestedPath).contains(key)){
                        fieldSortBuilder = new FieldSortBuilder("data." + key);
                    }else{
                        fieldSortBuilder = new FieldSortBuilder(key);
                    }
                    JSONObject sortValue = object.getJSONObject(key);
                    if(StringUtils.equalsIgnoreCase(SortOrder.ASC.toString(),sortValue.getString("order"))){
                        fieldSortBuilder.order(SortOrder.ASC);
                    }else if(StringUtils.equalsIgnoreCase(SortOrder.DESC.toString(),sortValue.getString("order"))){
                        fieldSortBuilder.order(SortOrder.DESC);
                    }
                    fieldSortBuilder.setNestedPath("data");
                    searchSourceBuilder.sort(fieldSortBuilder);
                }
            }
        }

        if(StringUtils.isNotEmpty(nestedPath)){
            NestedQueryBuilder nestedQueryBuilder = getNestedBuilder(nestedPath,jsonData);//嵌套的数据查询
            QueryFilterBuilder filterBuilder = QueryBuilders.queryFilter(nestedQueryBuilder);
            QueryBuilder filteredQueryBuilder = QueryBuilders.filteredQuery(boolQueryBuilder,filterBuilder);
            searchSourceBuilder.query(filteredQueryBuilder);
        }else{
            searchSourceBuilder.query(boolQueryBuilder);
        }

        return searchSourceBuilder;
    }

    /**
     * 嵌套的查询query
     * @param nestedPath
     * @param queryCondition
     * @return
     */
    private NestedQueryBuilder getNestedBuilder(String nestedPath,String queryCondition){
        if(StringUtils.isEmpty(nestedPath)){
            return null;
        }
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder = getBoolQueryBuilder(nestedPath,queryCondition,true);
        NestedQueryBuilder nestedQueryBuilder = QueryBuilders.nestedQuery(nestedPath,boolQueryBuilder);
        return nestedQueryBuilder;
    }

    /**
     * 构造bool查询（里面有匹配，过滤，范围等）
     * @param nestedPath
     * @param queryCondition
     * @return
     */
    private BoolQueryBuilder getBoolQueryBuilder(String nestedPath,String queryCondition,Boolean isNested){
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        JSONObject jsonCondition = JSONObject.parseObject(queryCondition);
        List<Map<String, Object>> filter = (List)jsonCondition.getJSONArray("filter");
        for(Map<String, Object> param : filter) {
            String andOr = String.valueOf(param.get("andOr"));
            String condition = String.valueOf(param.get("condition"));
            String field = String.valueOf(param.get("field"));
            Object value = param.get("value");

            //如果传过来的字段为空则表示查询全部
            if(StringUtils.isEmpty(field)){
                MatchAllQueryBuilder matchQueryBuilder = QueryBuilders.matchAllQuery();
                boolQueryBuilder.must(matchQueryBuilder);
                return boolQueryBuilder;
            }

            if(!isNested){
                if(!CollectionUtils.isEmpty(fieldsMap.get(nestedPath)) && fieldsMap.get(nestedPath).contains(field)){
                    continue;
                }
            }else{
                if(!CollectionUtils.isEmpty(fieldsMap.get(nestedPath)) && !fieldsMap.get(nestedPath).contains(field)){
                    continue;
                }
                field = nestedPath + "." + field;
            }
            if("null".equals(condition)){
                MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery(field, value);
                if("null".equals(andOr)) {
                    boolQueryBuilder.must(matchQueryBuilder);
                }
            }else if(condition.equals("=")) {
                MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery(field, value);
                if("and".equals(andOr)) {
                    boolQueryBuilder.must(matchQueryBuilder);
                }else if("or".equals(andOr)) {
                    boolQueryBuilder.should(matchQueryBuilder);
                }
            }else if (condition.equals("?")) {
                QueryStringQueryBuilder queryStringQueryBuilder = QueryBuilders.queryStringQuery(field + ":" + value);
                if("and".equals(andOr)) {
                    boolQueryBuilder.must(queryStringQueryBuilder);
                }else if("or".equals(andOr)) {
                    boolQueryBuilder.should(queryStringQueryBuilder);
                }
            }else {
                RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(field);
                if(field.endsWith("Date")) {
                    rangeQueryBuilder.format("yyyy-MM-dd HH:mm:ss");
                }
                if(condition.equals(">")) {
                    rangeQueryBuilder.gt(value);
                }else if(condition.equals(">=")) {
                    rangeQueryBuilder.gte(value);
                }else if(condition.equals("<=")) {
                    rangeQueryBuilder.lte(value);
                }else if(condition.equals("<")) {
                    rangeQueryBuilder.lt(value);
                }
                if("and".equals(andOr)) {
                    boolQueryBuilder.must(rangeQueryBuilder);
                }else if("or".equals(andOr)) {
                    boolQueryBuilder.should(rangeQueryBuilder);
                }
            }
        }
        return boolQueryBuilder;
    }


    /**
     * 构造查询所有的语句
     * @return
     */
    public SearchSourceBuilder getQueryAllBuilder(){
        return getQueryBuilder("","");
    }
    /**
     * 拼接es搜索json string
     * @param json
     * @return
     */
    public static String getQueryString(String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        //第一层query
        JSONObject query = new JSONObject();
        //bool层
        JSONObject boolQuery = new JSONObject();
        // and 还是 or，es中key为and--->must,or--->should
        JSONObject mustOrShouldQuery = new JSONObject();
        //匹配项，字段-值
        JSONArray jsonArray = new JSONArray();
        for(String key : jsonObject.keySet()){

            //默认为must
            if(StringUtils.equalsIgnoreCase("or",key) && StringUtils.equals("or",jsonObject.getString("or"))){
                mustOrShouldQuery.put("should",jsonArray);
            }else{
                mustOrShouldQuery.put("must",jsonArray);
            }

            //分页用，from表示数据从第几条开始取
            if(StringUtils.equalsIgnoreCase("from",key)){
                query.put("from",jsonObject.getInteger("from"));
                continue;
            }
            //分页用，size表示获取几条数据
            if(StringUtils.equalsIgnoreCase("size",key)){
                query.put("size",jsonObject.getInteger("size"));
                continue;
            }
            //排序
            if(StringUtils.equalsIgnoreCase("sort",key)){
                JSONObject sortJsonObj = (JSONObject)jsonObject.get("sort");
                for(String sortKey:sortJsonObj.keySet()){
                    if(!StringUtils.equalsIgnoreCase("asc",sortJsonObj.getString(sortKey)) && !StringUtils.equalsIgnoreCase("desc",sortJsonObj.getString(sortKey) )){
                        JSONObject error = new JSONObject();
                        error.put("error","sort contains bad value !");
                        return error.toJSONString();
                    }
                }
                query.put("sort",sortJsonObj);
                continue;
            }

            JSONObject matchQuery = new JSONObject();
            JSONObject subQuery = new JSONObject();
            String baseName = DataTypeEnum.body_sign_params.name().toString();
            if(null != DataStandardConvertService.dataMap.get(baseName) && DataStandardConvertService.dataMap.get(baseName).contains(key) || StringUtils.equalsIgnoreCase("rid",key)){
                subQuery.put("data."+key,jsonObject.get(key)); //data数据里内嵌的字段，真正的数据值内容
            }else{
                subQuery.put(key,jsonObject.get(key));
            }
            matchQuery.put("match",subQuery);
            jsonArray.add(matchQuery);
        }

        boolQuery.put("bool",mustOrShouldQuery);
        query.put("query",boolQuery);

        return query.toJSONString();
    }



    public static void main(String args[]) {

//        elasticSearchQueryGenerator.init();
       /* String str = "{\n" +
                "\t\"filter\": [{\n" +
                "\t\t\"andOr\": \"and\",\n" +
                "\t\t\"condition\": \"=\",\n" +
                "\t\t\"field\": \"\",\n" +
                "\t\t\"value\": \"\"\n" +
                "\t}]\n" +
                "}";
        getQueryBuilder("",str);*/
    }

}

