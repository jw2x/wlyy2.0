package com.yihu.jw.feign.iot.data_input;

import com.yihu.jw.exception.ApiException;
import com.yihu.jw.feign.fallbackfactory.iot.data_input.DataInputFeignFallbackFactory;
import com.yihu.jw.feign.fallbackfactory.iot.data_input.DataSearchFeignFallbackFactory;
import com.yihu.jw.restmodel.CommonContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.rm.iot.DataRequestMapping;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = CommonContants.svr_iot // name值是eurika的实例名字
        ,fallbackFactory  = DataSearchFeignFallbackFactory.class
)
@RequestMapping(DataRequestMapping.api_iot_common)
public interface DataSearchFeign {

    @PostMapping(value = DataRequestMapping.DataSearch.api_data_search_one, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Envelop getOne(@RequestBody String jsonData);

    @PostMapping(value = DataRequestMapping.DataSearch.api_user_search_list, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Envelop getList( @RequestBody String jsonData,
                            @RequestParam(value = "page", required = true) int page,
                            @RequestParam(value = "size", required = true) int size);

    @PostMapping(value = DataRequestMapping.DataSearch.api_user_search_list_page, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Envelop getListPage( @RequestBody String jsonData,
                                @RequestParam(value = "page", required = true) int page,
                                @RequestParam(value = "size", required = true) int size);

    @PostMapping(value = DataRequestMapping.DataSearch.api_user_search_recent5, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Envelop getRecent5ByTypeAndTime( @RequestBody String jsonData,
                               @RequestParam(value = "page", required = true) int page,
                               @RequestParam(value = "size", required = true) int size);

    @PostMapping(value = DataRequestMapping.DataSearch.api_user_abnormal_times_a_week, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Envelop getAbnormalTimesAWeek(@RequestBody String jsonData,
                                          @RequestParam(value = "page", required = true) int page,
                                          @RequestParam(value = "size", required = true) int size);

    @PostMapping(value = DataRequestMapping.DataSearch.api_user_search_recent1, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Envelop getRecent1ByCodeAndDel(@RequestBody String jsonData,
                                      @RequestParam(value = "page", required = true) int page,
                                      @RequestParam(value = "size", required = true) int size);

    @PostMapping(value = DataRequestMapping.DataSearch.api_user_search_list_code_del, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Envelop getListByCodeAndDel( @RequestBody String jsonData,
                                        @RequestParam(value = "page", required = true)int page,
                                        @RequestParam(value = "size", required = true)int size);

}
