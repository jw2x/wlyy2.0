package com.yihu.iot.datainput.controller;

import com.alibaba.fastjson.JSONObject;
import com.yihu.iot.datainput.service.DataSearchService;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.iot.datainput.DataBodySignsVO;
import com.yihu.jw.rm.iot.DataRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(DataRequestMapping.api_iot_common)
@Api(tags = "数据查询操作", description = "数据查询操作")
public class DataSearchController {

    @Autowired
    private DataSearchService dataSearchService;

    @PostMapping(value = DataRequestMapping.DataSearch.api_data_search_one, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询单条数据", notes = "根据id查询单条数据")
    public MixEnvelop<DataBodySignsVO, DataBodySignsVO> getOne(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) throws IOException {
        try{
            return MixEnvelop.getSuccess(DataRequestMapping.DataSearch.search_success,dataSearchService.getDataToBean(jsonData));
        } catch (ApiException e){
            return MixEnvelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PostMapping(value = DataRequestMapping.DataSearch.api_user_search_list, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询数据", notes = "根据条件查询数据")
    @ApiModelProperty()
    public MixEnvelop<DataBodySignsVO, DataBodySignsVO> getList(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) throws IOException {
        try{
            return MixEnvelop.getSuccess(DataRequestMapping.DataSearch.search_success,dataSearchService.getDataToBean(jsonData));
        } catch (ApiException e){
            return MixEnvelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PostMapping(value = DataRequestMapping.DataSearch.api_user_search_list_page, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询数据,分页", notes = "根据条件查询数据,分页")
    public MixEnvelop getListPage(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) throws IOException {
        try{
            return MixEnvelop.getSuccess(DataRequestMapping.DataSearch.search_success, dataSearchService.getDataToBean(jsonData));
        } catch (ApiException e){
            return MixEnvelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PostMapping(value = DataRequestMapping.DataSearch.api_user_search_recent5, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "获取最近5条数据", notes = "根据居民的体征类型，测量时间获取")
    public MixEnvelop<DataBodySignsVO, DataBodySignsVO> getRecent5ByTypeAndTime(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData ) throws IOException {
        try{
            JSONObject jsonObject = JSONObject.parseObject(jsonData);
            jsonObject.put("size", 5);
            return MixEnvelop.getSuccess(DataRequestMapping.DataSearch.search_success, dataSearchService.getDataToBean(jsonObject.toJSONString()));
        } catch (ApiException e){
            return MixEnvelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PostMapping(value = DataRequestMapping.DataSearch.api_user_abnormal_times_a_week, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "获取居民一周内体征数据异常次数", notes = "血糖或血压体征数据")
    public MixEnvelop getAbnormalTimesAWeek(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) throws IOException {
        try{
            return MixEnvelop.getSuccess(DataRequestMapping.DataSearch.search_success,dataSearchService.getDataToBean(jsonData));
        } catch (ApiException e){
            return MixEnvelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PostMapping(value = DataRequestMapping.DataSearch.api_user_search_recent1, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询体征数据", notes = "根据居民code和删除标识获取最近一次体征数据")
    public MixEnvelop getRecent1ByCodeAndDel(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) throws IOException {
        try {
            JSONObject jsonObject = JSONObject.parseObject(jsonData);
            jsonObject.put("size",1);
            return MixEnvelop.getSuccess(DataRequestMapping.DataSearch.search_success, dataSearchService.getDataToBean(jsonData));
        } catch (ApiException e) {
            return MixEnvelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PostMapping(value = DataRequestMapping.DataSearch.api_user_search_list_code_del, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询体征数据", notes = "根据居民code和删除标识获取所有体征数据，时间倒序")
    public MixEnvelop getListByCodeAndDel(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) throws IOException {
        try{
            return MixEnvelop.getSuccess(DataRequestMapping.DataSearch.search_success,dataSearchService.getDataToBean(jsonData));
        } catch (ApiException e){
            return MixEnvelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PostMapping(value = DataRequestMapping.DataSearch.api_user_delete, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "体征数据删除", notes = "根据id删除标志，支持伪删除")
    public MixEnvelop delete(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) throws IOException {
        try{
            String str = dataSearchService.updateData(jsonData);
            if(!StringUtils.equalsIgnoreCase("true",str)){
                return MixEnvelop.getSuccess(DataRequestMapping.DataSearch.message_fail,str);
            }
            return MixEnvelop.getSuccess(DataRequestMapping.DataSearch.delete_success,str);
        } catch (ApiException e){
            return MixEnvelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PostMapping(value = DataRequestMapping.DataSearch.api_user_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新体征记录", notes = "根据id更新体征记录（包括体征值、上传时间等）")
    public MixEnvelop update(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) throws IOException {
        try{
            String str = dataSearchService.updateData(jsonData);
            if(!StringUtils.equalsIgnoreCase("true",str)){
                return MixEnvelop.getSuccess(DataRequestMapping.DataSearch.message_fail,str);
            }
            return MixEnvelop.getSuccess(DataRequestMapping.DataSearch.update_success,str);
        } catch (ApiException e){
            return MixEnvelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PostMapping(value = DataRequestMapping.DataSearch.api_user_search_werun_datas, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询微信运动数据", notes = "根据Id获取用户微信运动数据")
    public MixEnvelop getWeRunDataListById(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) throws IOException {
        try{
            return MixEnvelop.getSuccess(DataRequestMapping.DataSearch.search_success,dataSearchService.getWeRunDataList(jsonData));
        } catch (ApiException e){
            return MixEnvelop.getError(e.getMessage(), e.getErrorCode());
        }
    }
}
