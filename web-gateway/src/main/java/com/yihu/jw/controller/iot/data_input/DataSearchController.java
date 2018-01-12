package com.yihu.jw.controller.iot.data_input;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yihu.jw.commnon.iot.DataConstants;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.feign.iot.data_input.DataInputFeign;
import com.yihu.jw.feign.iot.data_input.DataSearchFeign;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.rm.iot.DataRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(DataConstants.DataSearch.api_common)
@Api(value = "数据查询", description = "数据查询")
public class DataSearchController {

    @Autowired
    private DataSearchFeign dataSearchFeign;

    @PostMapping(value = DataConstants.DataSearch.api_data_search_one, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询单条数据", notes = "根据id查询单条数据")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop getOne(
            @ApiParam(name = "json_data", value = ""  ) @RequestBody String jsonData) {
        return dataSearchFeign.getOne(jsonData);
    }

    @PostMapping(value = DataConstants.DataSearch.api_user_search_list, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询数据", notes = "根据条件查询数据")
    public Envelop getList(@ApiParam(name = "json_data", value = "json_data"  ) @RequestBody String jsonData,
                           @ApiParam(name = "page", value = "page"  ) @RequestParam(value = "page", required = true)  int page,
                           @ApiParam(name = "size", value = "size"  ) @RequestParam(value = "size", required = true)  int size){
        try{
            return dataSearchFeign.getList(jsonData,page,size);
        } catch (ApiException e){
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PostMapping(value = DataConstants.DataSearch.api_user_search_list_page, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询数据,分页", notes = "根据条件查询数据,分页")
    public Envelop getListPage(@ApiParam(name = "json_data", value = ""  ) @RequestBody String jsonData,
                               @ApiParam(name = "page", value = "page"  ) @RequestParam(value = "page", required = true)  int page,
                               @ApiParam(name = "size", value = "size"  ) @RequestParam(value = "size", required = true)  int size){

            return dataSearchFeign.getListPage(jsonData,page,size);
    }

    @PostMapping(value = DataConstants.DataSearch.api_user_search_recent5, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "获取最近5条数据", notes = "根据居民的体征类型，测量时间获取")
    public Envelop getRecent5(@ApiParam(name = "json_data", value = ""  ) @RequestBody String jsonData,
                              @ApiParam(name = "page", value = "page"  ) @RequestParam(value = "page", required = true)  int page,
                              @ApiParam(name = "size", value = "size"  ) @RequestParam(value = "size", required = true)  int size){
            return dataSearchFeign.getRecent5ByTypeAndTime(jsonData,page,size);
    }

    @PostMapping(value = DataConstants.DataSearch.api_user_abnormal_times_a_week, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "获取居民一周内体征数据异常次数", notes = "血糖或血压体征数据")
    public Envelop getAbnormalTimesAWeek(@ApiParam(name = "json_data", value = ""  ) @RequestBody String jsonData,
                                         @ApiParam(name = "page", value = "page"  ) @RequestParam(value = "page", required = true)  int page,
                                         @ApiParam(name = "size", value = "size"  ) @RequestParam(value = "size", required = true)  int size){
            return dataSearchFeign.getAbnormalTimesAWeek(jsonData,page,size);
    }

    @PostMapping(value = DataConstants.DataSearch.api_user_search_recent1, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询体征数据", notes = "根据居民code和删除标识获取最近一次体征数据")
    public Envelop getOneByCodeAndDel(@ApiParam(name = "json_data", value = ""  ) @RequestBody String jsonData,
                                      @ApiParam(name = "page", value = "page"  ) @RequestParam(value = "page", required = true)  int page,
                                      @ApiParam(name = "size", value = "size"  ) @RequestParam(value = "size", required = true)  int size) {
            return dataSearchFeign.getRecent1ByCodeAndDel(jsonData,page,size);
    }

    @PostMapping(value = DataConstants.DataSearch.api_user_search_list_code_del, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询体征数据", notes = "根据居民code和删除标识获取所有体征数据，时间倒序")
    public Envelop getListByCodeAndDel(@ApiParam(name = "json_data", value = ""  ) @RequestBody String jsonData,
                                       @ApiParam(name = "page", value = "page"  )@RequestParam(value = "page", required = true)  int page,
                                       @ApiParam(name = "size", value = "size"  )@RequestParam(value = "size", required = true)  int size){
            return dataSearchFeign.getListByCodeAndDel(jsonData,page,size);
    }
}
