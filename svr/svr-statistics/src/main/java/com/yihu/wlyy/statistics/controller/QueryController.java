package com.yihu.wlyy.statistics.controller;

import com.yihu.wlyy.statistics.etl.cache.Cache;
import com.yihu.wlyy.statistics.etl.save.es.ElasticFactory;
import com.yihu.wlyy.statistics.util.DateUtil;
import com.yihu.wlyy.statistics.util.ElasticsearchUtil;
import com.yihu.wlyy.statistics.vo.SaveModel;
import io.searchbox.client.JestClient;
import io.searchbox.core.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by chenweida on 2017/7/1.
 */
@RestController
@RequestMapping("/quotaQuery")
@Api(description = "指标查询")
public class QueryController {
    @Autowired
    private ElasticsearchUtil elasticsearchUtil;

    @Autowired
    private ElasticFactory elasticFactory;
    @Value("${es.type}")
    private String esType;
    @Value("${es.index}")
    private String esIndex;

    @ApiOperation(value = "执行sql")
    @RequestMapping(value = "/excuteSQL", method = RequestMethod.GET)
    public List<SaveModel> excuteSQL(
            @ApiParam(name = "sql", value = "执行的", required = true) @RequestParam(value = "sql", required = true) String sql) {
        List<SaveModel> saveModels = elasticsearchUtil.excute(sql);
        return saveModels;
    }


    @ApiOperation(value = "查询某几个指标某个层级的列表")
    @RequestMapping(value = "/getQuotasList", method = RequestMethod.GET)
    public Map<String, List<SaveModel>> getQuotasList(
            @ApiParam(name = "ids", value = "指标id,多个逗号分割", required = true) @RequestParam(value = "ids", required = true) String ids,
            @ApiParam(name = "code", value = "指标的code", required = true) @RequestParam(value = "code", required = true) String code,
            @ApiParam(name = "arealevel", value = "指标的level(1 省 2 市 3 区县 4 机构 5团队)", required = true) @RequestParam(value = "arealevel", required = true) String arealevel,
            @ApiParam(name = "childrenArealevel", value = "指标的level(1 省 2 市 3 区县 4 机构 5团队)", required = true) @RequestParam(value = "childrenArealevel", required = true) String childrenArealevel,
            @ApiParam(name = "timeLevel", value = " 1增量的指标  2到达量的指标", required = true) @RequestParam(value = "timeLevel", required = true) String timeLevel,
            @ApiParam(name = "date", value = "时间(yyyy-MM-dd),不传默认查今天", required = false) @RequestParam(value = "date", required = false) String date) {
        //如果日期是空初始化是今天
        if (StringUtils.isEmpty(date)) {
            date = DateUtil.dateToStrShort(new Date());
        }
        Map<String, List<SaveModel>> jo = new HashMap<>();
        for (String id : ids.split(",")) {
            jo.put("index_" + id, elasticsearchUtil.findOneDateQuotaByChllevel(id, code, date, timeLevel, arealevel, childrenArealevel));
        }

        return jo;
    }

    @ApiOperation(value = "查询某个1级维度指标")
    @RequestMapping(value = "/getQuotaLevel1", method = RequestMethod.GET)
    public List<SaveModel> getQuotaLevel1(
            @ApiParam(name = "id", value = "指标id", required = true) @RequestParam(value = "id", required = true) String id,
            @ApiParam(name = "code", value = "指标的code", required = true) @RequestParam(value = "code", required = true) String code,
            @ApiParam(name = "arealevel", value = "指标的level(1 省 2 市 3 区县 4 机构 5团队)", required = true) @RequestParam(value = "arealevel", required = true) String arealevel,
            @ApiParam(name = "timeLevel", value = " 1增量的指标  2到达量的指标", required = true) @RequestParam(value = "timeLevel", required = true) String timeLevel,
            @ApiParam(name = "date", value = "时间(yyyy-MM-dd),不传默认查今天", required = false) @RequestParam(value = "date", required = false) String date) {
        //如果日期是空初始化是今天
        if (StringUtils.isEmpty(date)) {
            date = DateUtil.dateToStrShort(new Date());
        }
        List<SaveModel> saveModels = elasticsearchUtil.findOneDateQuotaLevel1(id, code, date, timeLevel, arealevel);

        return saveModels;
    }

    @ApiOperation(value = "查询某个2级维度指标")
    @RequestMapping(value = "/getQuotaLevel2", method = RequestMethod.GET)
    public List<SaveModel> getQuotaLevel2(
            @ApiParam(name = "id", value = "指标id", required = true) @RequestParam(value = "id", required = true) String id,
            @ApiParam(name = "code", value = "指标的code", required = true) @RequestParam(value = "code", required = true) String code,
            @ApiParam(name = "arealevel", value = "指标的level(1 省 2 市 3 区县 4 机构 5团队)", required = true) @RequestParam(value = "arealevel", required = true) String arealevel,
            @ApiParam(name = "timeLevel", value = " 1增量的指标  2到达量的指标", required = true) @RequestParam(value = "timeLevel", required = true) String timeLevel,
            @ApiParam(name = "date", value = "时间(yyyy-MM-dd),不传默认查今天", required = false) @RequestParam(value = "date", required = false) String date) {
        //如果日期是空初始化是今天
        if (StringUtils.isEmpty(date)) {
            date = DateUtil.dateToStrShort(new Date());
        }
        List<SaveModel> saveModels = elasticsearchUtil.findOneDateQuotaLevel2(id, code, date, timeLevel, arealevel);

        return saveModels;
    }

    @ApiOperation(value = "查询某个指标某个层级的折线图")
    @RequestMapping(value = "/getQuotasLine", method = RequestMethod.GET)
    public List<SaveModel> getQuotasLine(
            @ApiParam(name = "id", value = "指标id", required = true) @RequestParam(value = "id", required = true) String id,
            @ApiParam(name = "code", value = "指标的code", required = true) @RequestParam(value = "code", required = true) String code,
            @ApiParam(name = "arealevel", value = "指标的level(1 省 2 市 3 区县 4 机构 5团队)", required = true) @RequestParam(value = "arealevel", required = true) String arealevel,
            @ApiParam(name = "timeLevel", value = " 1增量的指标  2到达量的指标", required = true) @RequestParam(value = "timeLevel", required = true) String timeLevel,
            @ApiParam(name = "startDate", value = "时间(yyyy-MM-dd)", required = false) @RequestParam(value = "startDate", required = true) String startDate,
            @ApiParam(name = "endDate", value = "时间(yyyy-MM-dd),不传默认查今天", required = false) @RequestParam(value = "endDate", required = false) String endDate,
            @ApiParam(name = "interval", value = "1日 2周 3月", required = false) @RequestParam(value = "interval", required = false) String interval
    ) {
        //如果日期是空初始化是今天
        if (StringUtils.isEmpty(endDate)) {
            endDate = DateUtil.dateToStrShort(new Date());
        }
        List<SaveModel> saveModels = elasticsearchUtil.findQuotaLines(id, code, startDate, endDate, timeLevel, arealevel, interval);
        return saveModels;
    }

    @ApiOperation(value = "查询某几个指标的到达量")
    @RequestMapping(value = "/getQuotas", method = RequestMethod.GET)
    public Map<String, List<SaveModel>> getQuotas(
            @ApiParam(name = "ids", value = "指标id,多个逗号分割", required = true) @RequestParam(value = "ids", required = true) String ids,
            @ApiParam(name = "code", value = "指标的code", required = true) @RequestParam(value = "code", required = true) String code,
            @ApiParam(name = "arealevel", value = "指标的level(1 省 2 市 3 区县 4 机构 5团队)", required = true) @RequestParam(value = "arealevel", required = true) String arealevel,
            @ApiParam(name = "date", value = "时间(yyyy-MM-dd),不传默认查今天", required = false) @RequestParam(value = "date", required = false) String date) {
        Map<String, List<SaveModel>> returnMap = new HashMap<>();
        for (String id : ids.split(",")) {
            returnMap.put("index_" + id, elasticsearchUtil.findOneDateQuota(id, code, date, "2", arealevel));
        }
        return returnMap;
    }


    @ApiOperation(value = "删除某一天的某一个指标的数据")
    @RequestMapping(value = "/deleteQuotaWithId", method = RequestMethod.DELETE)
    public String deleteQuota(
            @ApiParam(name = "id", value = "指标id", required = true) @RequestParam(value = "id", required = true) String id,
            @ApiParam(name = "date", value = "时间(yyyy-MM-dd)", required = true) @RequestParam(value = "date", required = true) String date) {

        return deleteData(DateUtil.strToDate(date, "yyyy-MM-dd"), id).toString();
    }

    @ApiOperation(value = "删除某一天的数据")
    @RequestMapping(value = "/deleteQuota", method = RequestMethod.DELETE)
    public String deleteQuota(
            @ApiParam(name = "date", value = "时间(yyyy-MM-dd)", required = true) @RequestParam(value = "date", required = true) String date) {


        return deleteData(DateUtil.strToDate(date, "yyyy-MM-dd"), null).toString();
    }

    @ApiOperation(value = "删除某一天到某一天的数据")
    @RequestMapping(value = "/deleteQuotaDateToDate", method = RequestMethod.DELETE)
    public String deleteQuotaDateToDate(
            @ApiParam(name = "start", value = "时间(yyyy-MM-dd)", required = true) @RequestParam(value = "start", required = true) String start,
            @ApiParam(name = "end", value = "时间(yyyy-MM-dd)", required = true) @RequestParam(value = "end", required = true) String end) {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = sdf.parse(start);
            Date endDate = endDate = sdf.parse(end);

            int day = daysBetween(startDate, endDate);
            for (int i = 0; i < day; i++) {
                Cache.cleanCache();//清空缓存
                deleteData(DateUtil.strToDate(getYesterday(i, startDate), "yyyy-MM-dd"), null).toString();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "删除成功";
    }

    @ApiOperation(value = "删除某一天到某一天的数据")
    @RequestMapping(value = "/deleteQuotaDateToDateWithId", method = RequestMethod.DELETE)
    public String deleteQuotaDateToDateWithId(
            @ApiParam(name = "id", value = "指标id", required = true) @RequestParam(value = "id", required = true) String id,
            @ApiParam(name = "start", value = "时间(yyyy-MM-dd)", required = true) @RequestParam(value = "start", required = true) String start,
            @ApiParam(name = "end", value = "时间(yyyy-MM-dd)", required = true) @RequestParam(value = "end", required = true) String end) {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = sdf.parse(start);
            Date endDate = endDate = sdf.parse(end);

            int day = daysBetween(startDate, endDate);
            for (int i = 0; i < day; i++) {
                Cache.cleanCache();//清空缓存
                deleteData(DateUtil.strToDate(getYesterday(i, startDate), "yyyy-MM-dd"), id).toString();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "删除成功";
    }

    public static String getYesterday(Integer day, Date startDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.DAY_OF_MONTH, day);
        String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        return yesterday;
    }
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 删除 某个指标某一天的某个timelevel的数据
     *
     * @param quotaDate
     * @param quotaCode
     */
    private net.sf.json.JSONObject deleteData(Date quotaDate, String quotaCode) {
        net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();

        JestClient jestClient = null;
        try {
            jestClient = elasticFactory.getJestClient();
            //先根据条件查找出来
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

            BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
            if (quotaDate != null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("quotaDate", quotaDate));
            }
            if (!StringUtils.isEmpty(quotaCode)) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("quotaCode", quotaCode));
            }

            searchSourceBuilder.query(boolQueryBuilder
            ).size(500000);//一次取10000条

            Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex(esIndex).addType(esType)
                    .build();
            SearchResult result = jestClient.execute(search);
            List<SaveModel> saveModels = result.getSourceAsObjectList(SaveModel.class);

            //根据id批量删除
            Bulk.Builder bulk = new Bulk.Builder().defaultIndex(esIndex).defaultType(esType);
            for (SaveModel obj : saveModels) {
                Delete index = new Delete.Builder(obj.getId()).build();
                bulk.addAction(index);
            }
            BulkResult br = jestClient.execute(bulk.build());
            jsonObject.put("flag", br.isSucceeded());
            jsonObject.put("count", saveModels.size());
            return jsonObject;
        } catch (Exception e) {
            jsonObject.put("flag", false);
            jsonObject.put("message", e.getMessage());
            return jsonObject;
        } finally {
            if (jestClient != null) {
                jestClient.shutdownClient();
            }
        }
    }
}
