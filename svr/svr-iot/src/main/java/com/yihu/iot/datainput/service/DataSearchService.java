package com.yihu.iot.datainput.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yihu.base.es.config.ElastricSearchHelper;
import com.yihu.base.hbase.HBaseHelper;
import com.yihu.iot.datainput.enums.DataTypeEnum;
import com.yihu.iot.datainput.util.ConstantUtils;
import com.yihu.iot.datainput.util.RowKeyUtils;
import io.searchbox.core.Search;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DataSearchService {

    private Logger logger = LoggerFactory.getLogger(DataSearchService.class);

    @Autowired
    private ElastricSearchHelper elastricSearchHelper;

    @Autowired
    private HBaseHelper hBaseHelper;


    /**
     * 拼接es搜索json string
     * @param json
     * @return
     */
    public static String getQueryString(String json){
        JSONObject jsonObject = (JSONObject)JSONObject.parse(json);
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
            if(null != DataStandardConvertService.dataMap.get(baseName) && DataStandardConvertService.dataMap.get(baseName).contains(key)){
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
        String query = getQueryString(jsonData);
        String esResult = elastricSearchHelper.search(ConstantUtils.esIndex,ConstantUtils.esType,query);
        JSONArray jsonArray = (JSONArray)JSONArray.parse(esResult);
        List<String> rowkeys = new ArrayList<>();
        for(Object object:jsonArray){
            JSONObject jsonObject = (JSONObject)object;
            JSONArray datas = (JSONArray)jsonObject.get("data");
            for(Object data:datas){
                JSONObject dataJson = (JSONObject)data;
                rowkeys.add(dataJson.getString("rid"));
            }
        }

        List<Map<String, Object>> resultList = new ArrayList<>();
        try {
            //拿到rowkey后，去hbase读取数据内容
            Result[] hbaseData = hBaseHelper.getResultList(ConstantUtils.tableName,rowkeys);
            for(int i = 0;i < hbaseData.length; i++){
                List<Cell> ceList = hbaseData[i].listCells();
                Map<String, Object> map = new HashMap<String, Object>();
                String rowkey = Bytes.toString(hbaseData[i].getRow());
                //rowkey是根据一些头部数据加密而来，解密即可还原
                String tag = RowKeyUtils.getMessageFromRowKey(rowkey);
                String[] tags = tag.split(",");
                map.put("accessToken", tags[0]);
                map.put("deviceSn", tags[1]);
                map.put("extCode", tags[2]);
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
            return esResult;
        }
        JSONArray resultArray = new JSONArray();
        resultArray.addAll(resultList);
        return resultArray.toJSONString();
    }

    public static void main(String args[]){
        String str = "{\n" +
                "\t\"or\":\"\",\n" +
                "\t\"extCode\":\"1\",\n" +
                "\t\"idcard\":\"test\",\n" +
                "\t\"from\":1,\n" +
                "\t\"size\":5,\n" +
                "\t\"sort\":{\"measure_time\":\"desc\"}\n" +
                "}";
        System.out.println(getQueryString(str));
    }
}
