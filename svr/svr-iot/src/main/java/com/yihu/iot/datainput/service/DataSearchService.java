package com.yihu.iot.datainput.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
//import com.yihu.base.hbase.HBaseHelper;
import com.yihu.elasticsearch.ElasticSearchHelper;
import com.yihu.iot.datainput.util.ConstantUtils;
import com.yihu.iot.datainput.util.RowKeyUtils;
import com.yihu.iot.service.common.ElasticSearchQueryGenerator;
import com.yihu.jw.restmodel.iot.datainput.DataBodySignsVO;
import com.yihu.jw.restmodel.iot.datainput.WeRunDataVO;
import com.yihu.jw.util.date.DateUtil;
import io.searchbox.core.SearchResult;
import org.apache.commons.lang.StringUtils;
//import org.apache.hadoop.hbase.Cell;
//import org.apache.hadoop.hbase.client.Result;
//import org.apache.hadoop.hbase.util.Bytes;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class DataSearchService {

    private Logger logger = LoggerFactory.getLogger(DataSearchService.class);

    @Autowired
    private ElasticSearchHelper elasticSearchHelper;

    @Autowired
    private ElasticSearchQueryGenerator elasticSearchQueryGenerator;

//    @Autowired
//    private HBaseHelper hBaseHelper;

    public String getData(String jsonData) throws IOException {
        logger.info("load data from elasticsearch start:" + org.apache.http.client.utils.DateUtils.formatDate(new Date(), DateUtil.yyyy_MM_dd_HH_mm_ss));
        JSONObject resultJsonObj = new JSONObject();
        JSONArray  resultArray = new JSONArray();
        SearchSourceBuilder query = elasticSearchQueryGenerator.getQueryBuilder("data",jsonData);
        SearchResult esResult = elasticSearchHelper.search(ConstantUtils.esIndex,ConstantUtils.esType,query.toString());
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
     * 修改字段
     * {"rid":"", 要修改的文档data id
     *  "key":"value", key:要修改的字段，value:要修改的值
     *  <{...}>}
     * @param json
     * @return
     */
    public String updateData(String json) throws IOException {
        JSONObject params = JSONObject.parseObject(json);
        //拿到rid，构造通用查询结构，查询对应es Id
        String rid = params.getString("rid");
        JSONObject queryField = new JSONObject();
        queryField.put("field","rid");
        queryField.put("value",rid);
        JSONArray filter = new JSONArray();
        filter.add(queryField);
        JSONObject query = new JSONObject();
        query.put("filter",filter);
        SearchSourceBuilder queryString = elasticSearchQueryGenerator.getQueryBuilder("data",query.toJSONString());
        JSONArray datas = new JSONArray();
        JSONObject resultObject = new JSONObject();
        String _id = "";
        try {
            //将该rid的文档取出来
            SearchResult searchResult = elasticSearchHelper.search(ConstantUtils.esIndex, ConstantUtils.esType, queryString.toString());
            params.remove("rid");
            _id = getEsId(searchResult.getJsonString());
            String resultSource = searchResult.getSourceAsString();
            //inner，nested object在es中修改只支持替换整个的object
            resultObject = (JSONObject) JSONObject.parse(String.valueOf(resultSource));
            //文档里的data数组对象数据
            datas = (JSONArray) resultObject.get("data");
            for (Object data : datas) {
                JSONObject dataJson = (JSONObject) data;
                if (StringUtils.equalsIgnoreCase(rid, dataJson.getString("rid"))) {
                    for (String key : params.keySet()) {
                        dataJson.put(key, params.get(key));//改的是data里面的数据
                    }
                }
            }
        }catch (Exception e){
            logger.error("get data failed from elasticsearch",e.getMessage());
            return "no data for rid:"+ rid +",update failed";
        }

        JSONObject updateObj = new JSONObject();
        updateObj.put("data",datas);
//        boolean bool = elastricSearchHelper.update(ConstantUtils.esIndex, ConstantUtils.esType, _id, updateObj.toJSONString());
//        Update update = new Update();
//        update.
        boolean bool = elasticSearchHelper.update(ConstantUtils.esIndex, ConstantUtils.esType, _id, resultObject);
        return String.valueOf(bool);
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
   /* public String getDataFromHbase(List<String> rowkeys,SearchResult esResult){
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
            logger.error("get data from hbase failed.",e.getMessage());
            return esResult.getSourceAsString();
        }
        resultArray.addAll(resultList);
        resultJsonObj.put("data",resultArray);
        resultJsonObj.put("count",esResult.getTotal());
        long count = System.currentTimeMillis() - time;
        logger.info("load data from hbase end:" + org.apache.http.client.utils.DateUtils.formatDate(new Date(), DateUtil.yyyy_MM_dd_HH_mm_ss) + ",count: " + count);
        return resultJsonObj.toJSONString();
    }*/

    /**
     * 体征数据查询，数据转为视图展示VO类
     * @param jsonData
     * @return
     */
    public List<DataBodySignsVO> getDataToBean(String jsonData) throws IOException {
        List<DataBodySignsVO> result = new ArrayList<>();
        if(StringUtils.isEmpty(jsonData)){
            return result;
        }
        logger.info("load data from elasticsearch start:" + org.apache.http.client.utils.DateUtils.formatDate(new Date(), DateUtil.yyyy_MM_dd_HH_mm_ss));
        SearchSourceBuilder query = elasticSearchQueryGenerator.getQueryBuilder("data",jsonData);
        SearchResult esResult = elasticSearchHelper.search(ConstantUtils.esIndex,ConstantUtils.esType,query.toString());
        if(null== esResult || esResult.getTotal()==null || esResult.getTotal() == 0){
            return result;
        }
        for(String str :esResult.getSourceAsStringList()){
            DataBodySignsVO dataBodySignsVO = JSONObject.parseObject(str,DataBodySignsVO.class);
            result.add(dataBodySignsVO);
        }

        return result;
    }

    /**
     * 查询微信运动数据
     * @param json
     * @return
     */
    public List<WeRunDataVO> getWeRunDataList(String json) throws IOException {
        List<WeRunDataVO> result = new ArrayList<>();
        SearchSourceBuilder query = elasticSearchQueryGenerator.getQueryBuilder("stepInfoList",json);
        SearchResult esResult = elasticSearchHelper.search(ConstantUtils.weRunDataIndex,ConstantUtils.weRunDataType,query.toString());
        if (null != esResult && esResult.getTotal() == 0){
            return result;
        }
        for (String str:esResult.getSourceAsStringList()){
            WeRunDataVO weRunDataVO = JSONObject.parseObject(str,WeRunDataVO.class);
            result.add(weRunDataVO);
        }
        return result;
    }

}
