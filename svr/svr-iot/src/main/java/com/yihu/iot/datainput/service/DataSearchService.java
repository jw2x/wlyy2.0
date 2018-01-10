package com.yihu.iot.datainput.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yihu.base.es.config.ElastricSearchHelper;
import com.yihu.base.hbase.HBaseHelper;
import com.yihu.iot.datainput.util.ConstantUtils;
import io.searchbox.core.Search;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
            if(DataStandardConvertService.dataMap.get("body_sign_params").contains(key)){
                subQuery.put("data."+key,jsonObject.get(key)); //data数据里内嵌的字段，真正的数据值内容
            }else{
                subQuery.put(key,jsonObject.get(key));
            }
            matchQuery.put("match",subQuery);
            jsonArray.add(matchQuery);
        }
        //默认为must
        if(null != jsonObject.get("should") && StringUtils.equals("should",jsonObject.getString("should"))){
            mustOrShouldQuery.put("should",jsonArray);
        }else{
            mustOrShouldQuery.put("must",jsonArray);
        }
        boolQuery.put("bool",mustOrShouldQuery);
        query.put("query",boolQuery);

        return query.toJSONString();
    }

    public String getData(String jsonData){
        String query = getQueryString(jsonData);
        String result = elastricSearchHelper.search(ConstantUtils.esIndex,ConstantUtils.esType,query);
        return result;
    }

    public static void main(String args[]){
        String str = "{\n" +
                "\t\"systolic\":\"59\",\n" +
                "\t\"extcode\":\"1\",\n" +
                "\t\"from\":1,\n" +
                "\t\"size\":5\n" +
                "}";
        System.out.println(getQueryString(str));
    }
}
