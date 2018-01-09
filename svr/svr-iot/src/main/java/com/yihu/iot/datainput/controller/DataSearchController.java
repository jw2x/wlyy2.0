package com.yihu.iot.datainput.controller;

import com.alibaba.fastjson.JSONObject;
import com.yihu.iot.datainput.service.DataSearchService;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.rm.iot.DataRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(DataRequestMapping.api_iot_common)
@Api(value = "数据查询操作", description = "数据查询操作")
public class DataSearchController {

    @Autowired
    private DataSearchService dataSearchService;

    @PostMapping(value = DataRequestMapping.DataSearch.api_data_search_one, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询单条数据", notes = "根据id查询单条数据")
    public Envelop getOne(@ApiParam(name = "id", value = "", defaultValue = "") @RequestBody String id){
        try{
            String jsonData = "{\"id\":" + id + "}";
            return Envelop.getSuccess(DataRequestMapping.DataInput.message_success,dataSearchService.getData(jsonData));
        } catch (ApiException e){
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PostMapping(value = DataRequestMapping.DataSearch.api_user_search_list, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询数据", notes = "根据条件查询数据")
    public Envelop getList(@ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData){
        try{
            return Envelop.getSuccess(DataRequestMapping.DataInput.message_success,dataSearchService.getData(jsonData));
        } catch (ApiException e){
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PostMapping(value = DataRequestMapping.DataSearch.api_user_search_list_page, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询数据,分页", notes = "根据条件查询数据,分页")
    public Envelop getListPage(@ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData){
        try{
            return Envelop.getSuccess(DataRequestMapping.DataInput.message_success,dataSearchService.getData(jsonData));
        } catch (ApiException e){
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PostMapping(value = DataRequestMapping.DataSearch.api_user_search_recent5, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "获取最近5条数据", notes = "根据居民的体征类型，测量时间获取")
    public Envelop getRecent5(@ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData){
        try{
            return Envelop.getSuccess(DataRequestMapping.DataInput.message_success,dataSearchService.getData(jsonData));
        } catch (ApiException e){
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PostMapping(value = DataRequestMapping.DataSearch.api_user_abnormal_times_a_week, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "获取居民一周内体征数据异常次数", notes = "血糖或血压体征数据")
    public Envelop getAbnormalTimesAWeek(@ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData){
        try{
            return Envelop.getSuccess(DataRequestMapping.DataInput.message_success,dataSearchService.getData(jsonData));
        } catch (ApiException e){
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PostMapping(value = DataRequestMapping.DataSearch.api_user_abnormal_times_a_week, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询体征数据", notes = "根据居民code和删除标识获取最近一次体征数据")
    public Envelop getOneByCodeAndDel(@ApiParam(name = "userCode", value = "", defaultValue = "") @RequestBody String userCode,
                                      @ApiParam(name = "del", value = "", defaultValue = "") @RequestBody String del) {
        try {
            String jsonData = "{\"userCode\":" + userCode + ",\"del\":" + del + "}";
            return Envelop.getSuccess(DataRequestMapping.DataInput.message_success, dataSearchService.getData(jsonData));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PostMapping(value = DataRequestMapping.DataSearch.api_user_abnormal_times_a_week, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询体征数据", notes = "根据居民code和删除标识获取所有体征数据，时间倒序")
    public Envelop getListByCodeAndDel(
            @ApiParam(name = "userCode", value = "", defaultValue = "") @RequestBody String userCode,
            @ApiParam(name = "del", value = "", defaultValue = "") @RequestBody String del){
        try{
            String jsonData = "{\"userCode\":"+userCode+",\"del\":"+ del +"}";
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.matchQuery("user", "kimchy"));
//            searchSourceBuilder.query(JSONObject.parseObject(jsonData).);
            return Envelop.getSuccess(DataRequestMapping.DataInput.message_success,dataSearchService.getData(jsonData));
        } catch (ApiException e){
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }
}
