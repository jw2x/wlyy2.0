package com.yihu.iot.datainput.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yihu.base.es.config.ElastricSearchHelper;
import com.yihu.base.hbase.HBaseHelper;
import com.yihu.iot.datainput.enums.DataTypeEnum;
import com.yihu.iot.datainput.util.ConstantUtils;
import com.yihu.iot.datainput.util.RowKeyUtils;
import com.yihu.jw.iot.datainput.Data;
import com.yihu.jw.iot.datainput.DataBodySignsDO;
import com.yihu.jw.iot.datainput.DataStandardDO;
import com.yihu.jw.restmodel.iot.datainput.DataBodySignsVO;
import com.yihu.jw.util.date.DateUtil;
import io.searchbox.core.SearchResult;
import io.searchbox.core.Update;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.CollectionUtils;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQueryBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.util.*;

@Component
public class DataSearchService {

    private Logger logger = LoggerFactory.getLogger(DataSearchService.class);

    @Autowired
    private ElastricSearchHelper elastricSearchHelper;

    @Autowired
    private HBaseHelper hBaseHelper;

    private Set<String> fieldsSet = new HashSet<>();

    @PostConstruct
    public void init(){
        PropertyDescriptor[] properties = BeanUtils.getPropertyDescriptors(Data.class);
        for(PropertyDescriptor field:properties){
            fieldsSet.add(field.getName());
        }
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

    public String getData(String jsonData){
        logger.info("load data from elasticsearch start:" + org.apache.http.client.utils.DateUtils.formatDate(new Date(), DateUtil.yyyy_MM_dd_HH_mm_ss));
        JSONObject resultJsonObj = new JSONObject();
        JSONArray  resultArray = new JSONArray();
       /* List list = new ArrayList();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            list = objectMapper.readValue(jsonData,(List.class));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        SearchSourceBuilder query = getQueryBuilder(jsonData);
        SearchResult esResult = elastricSearchHelper.search(ConstantUtils.esIndex,ConstantUtils.esType,query.toString());
        if(esResult.getTotal() == 0){
            return "";
        }
        List<String> rowkeys = new ArrayList<>();
        for(Object object:esResult.getSourceAsStringList()){
            JSONObject jsonObject = (JSONObject)JSONObject.parse(String.valueOf(object));
            JSONArray datas = (JSONArray)jsonObject.get("data");
            for(Object data:datas){
                JSONObject dataJson = (JSONObject)data;
                if(null != dataJson.getString("rid")){
                    rowkeys.add(dataJson.getString("rid"));
                }
            }
        }

        resultArray.addAll(esResult.getSourceAsStringList());
        resultJsonObj.put("data", resultArray);
        resultJsonObj.put("count", esResult.getTotal());
        logger.info("load data from elasticsearch end:" + org.apache.http.client.utils.DateUtils.formatDate(new Date(), DateUtil.yyyy_MM_dd_HH_mm_ss));
        return resultJsonObj.toJSONString();

//        if (!CollectionUtils.isEmpty(rowkeys)) {
//         return getDataFromHbase(rowkeys,esResult);
//        }
    }

    /**
     * 构造es查询参数
     * @param jsonData
     *
             {
             "filter":[{"andOr":"and|or","condition":">|=|<|>=|<=|?","field":"<filed>","value":"<value>"},<{...}>], - 参数说明：andOr跟数据库的中的AND和OR相似；condition指条件匹配程度，?相当于数据库中的like；filed指检索的字段；value为检索的值
            "page":1,		- 参数格式：页码，默认1，int类型 不需要分页，传""
            "size":10,		- 参数格式：条数，默认10，int类型 不需要分页，传""
            "sort":[
                     {"key":{"order":"asc|desc"}}, - 参数格式：排序， key要排序的字段，order固定，取值asc或desc，不需要排序，传""
                     {"key":{"order":"asc|desc"}}
                  ]
            }
     * @return
     */
    private SearchSourceBuilder getQueryBuilder(String jsonData) {
        JSONObject json = JSONObject.parseObject(jsonData);
        List<Map<String, Object>> filter = (List)json.getJSONArray("filter");
        int page = json.getIntValue("page") == 0 ? 1:json.getIntValue("page"); //从第一页开始
        int size = json.getIntValue("size") == 0 ? 1:json.getIntValue("size"); //默认值为1，最少获取一条记录
        JSONArray sort = json.getJSONArray("sort");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        for(Map<String, Object> param : filter) {
            String andOr = String.valueOf(param.get("andOr"));
            String condition = String.valueOf(param.get("condition"));
            String field = String.valueOf(param.get("field"));
            Object value = param.get("value");
            if(!CollectionUtils.isEmpty(fieldsSet) && fieldsSet.contains(field)){
                field = "data." + field;
            }
            if(condition.equals("=")) {
                MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchPhraseQuery(field, value);
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
                RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(field);;
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
        searchSourceBuilder.from((page -1)*size);
        searchSourceBuilder.size(size);
        if(CollectionUtils.notEmpty(sort)){
            for(Object obj:sort){
                JSONObject object = JSONObject.parseObject(obj.toString());
                FieldSortBuilder fieldSortBuilder = null;
                for(String key:object.keySet()){
                    if(fieldsSet.contains(key)){
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

        NestedQueryBuilder nestedQueryBuilder = new NestedQueryBuilder("data",boolQueryBuilder);
        searchSourceBuilder.query(nestedQueryBuilder);

        return searchSourceBuilder;
    }


    /**
     * 修改字段
     * {"rid":"", 要修改的文档data id
     *  "key":"value", key:要修改的字段，value:要修改的值
     *  <{...}>}
     * @param json
     * @return
     */
    public String updateData(String json) {
        JSONObject params = JSONObject.parseObject(json);
        //拿到rid
        String rid = params.getString("rid");
        JSONObject query = new JSONObject();
        query.put("rid",rid);
        String queryString = getQueryString(query.toJSONString());
        //将该rid的文档取出来
        SearchResult searchResult = elastricSearchHelper.search(ConstantUtils.esIndex, ConstantUtils.esType, queryString);
        params.remove("rid");
        String _id = getEsId(searchResult.getJsonString());
        String resultSource = searchResult.getSourceAsString();
        //inner，nested object在es中修改只支持替换整个的object
        JSONObject resultObject = (JSONObject) JSONObject.parse(String.valueOf(resultSource));
        //文档里的data数组对象数据
        JSONArray datas = (JSONArray) resultObject.get("data");
        for (Object data : datas) {
            JSONObject dataJson = (JSONObject) data;
            if (StringUtils.equalsIgnoreCase(rid, dataJson.getString("rid"))) {
                for (String key : params.keySet()) {
                    dataJson.put(key, params.get(key));//改的是data里面的数据
                }
            }
        }
        JSONObject updateObj = new JSONObject();
        updateObj.put("data",datas);
//        boolean bool = elastricSearchHelper.update(ConstantUtils.esIndex, ConstantUtils.esType, _id, updateObj.toJSONString());
//        Update update = new Update();
//        update.
        boolean bool = elastricSearchHelper.update(ConstantUtils.esIndex, ConstantUtils.esType, _id, resultObject);
        JSONObject updateResult = new JSONObject();
        updateResult.put("result", bool);
        return updateResult.toJSONString();
    }

    private String getEsId(String str){
        String _id = "";
        JSONObject jsonObject = JSONObject.parseObject(str);
        JSONObject hist1 = jsonObject.getJSONObject("hits");
        JSONArray array = (JSONArray)hist1.get("hits");
        for(Object obj:array){
            _id  = ((JSONObject)obj).getString("_id");
        }
        return _id;
    }

    /**
     * 根据从es中搜索出来的结果去hbase中查询数据内容
     * @param rowkeys
     * @param esResult
     * @return
     */
    public String getDataFromHbase(List<String> rowkeys,SearchResult esResult){
        long time = System.currentTimeMillis();
        logger.info("load data from hbase start:" + org.apache.http.client.utils.DateUtils.formatDate(new Date(), DateUtil.yyyy_MM_dd_HH_mm_ss));
        JSONObject resultJsonObj = new JSONObject();
        JSONArray  resultArray = new JSONArray();
        List<Map<String, Object>> resultList = new ArrayList<>();
        try {
            //拿到rowkey后，去hbase读取数据内容
            Result[] hbaseData = hBaseHelper.getResultList(ConstantUtils.tableName,rowkeys);
            for(Result res:hbaseData){
                List<Cell> ceList = res.listCells();
                if(null == ceList){
                    continue;
                }
                Map<String, Object> map = new HashMap<String, Object>();
                String rowkey = Bytes.toString(res.getRow());
                //rowkey是根据一些头部数据加密而来，解密即可还原
                String tag = RowKeyUtils.getMessageFromRowKey(rowkey);
                String[] tags = tag.split(",");
                map.put("access_token", tags[0]);
                map.put("sn", tags[1]);
                map.put("ext_code", tags[2]);
                if (ceList != null && ceList.size() > 0) {
                    for (Cell cell : ceList) {
                        map.put(Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength()),
                                Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
                    }
                }
                resultList.add(map);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("get data from hbase fail.",e.getMessage());
            return esResult.getSourceAsString();
        }
        resultArray.addAll(resultList);
        resultJsonObj.put("data",resultArray);
        resultJsonObj.put("count",esResult.getTotal());
        long count = System.currentTimeMillis() - time;
        logger.info("load data from hbase end:" + org.apache.http.client.utils.DateUtils.formatDate(new Date(), DateUtil.yyyy_MM_dd_HH_mm_ss) + ",count: " + count);
        return resultJsonObj.toJSONString();
    }

    public List<DataBodySignsVO> getDataToBean(String jsonData){
        List<DataBodySignsVO> result = new ArrayList<>();
        logger.info("load data from elasticsearch start:" + org.apache.http.client.utils.DateUtils.formatDate(new Date(), DateUtil.yyyy_MM_dd_HH_mm_ss));
        SearchSourceBuilder query = getQueryBuilder(jsonData);
        SearchResult esResult = elastricSearchHelper.search(ConstantUtils.esIndex,ConstantUtils.esType,query.toString());
        if(esResult.getTotal() == 0){
            return result;
        }
        for(String str :esResult.getSourceAsStringList()){
            DataBodySignsVO dataBodySignsVO = JSONObject.parseObject(str,DataBodySignsVO.class);
            result.add(dataBodySignsVO);
        }

        return result;
    }

    public static void main(String args[]) {
    }

}
