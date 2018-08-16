package com.yihu.jw.fegin.iot.data_input;

import com.yihu.jw.fegin.fallbackfactory.iot.data_input.DataSearchFeignFallbackFactory;
import com.yihu.jw.restmodel.CommonContants;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.rm.iot.DataRequestMapping;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(
        name = CommonContants.svr_iot // name值是eurika的实例名字
        ,fallbackFactory  = DataSearchFeignFallbackFactory.class
)
@RequestMapping(DataRequestMapping.api_iot_common)
public interface DataSearchFeign {

    @PostMapping(value = DataRequestMapping.DataSearch.api_data_search_one, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public MixEnvelop getOne(@RequestBody String jsonData);

    @PostMapping(value = DataRequestMapping.DataSearch.api_user_search_list, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public MixEnvelop getList(@RequestBody String jsonData);

    @PostMapping(value = DataRequestMapping.DataSearch.api_user_search_list_page, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public MixEnvelop getListPage(@RequestBody String jsonData);

    @PostMapping(value = DataRequestMapping.DataSearch.api_user_search_recent5, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public MixEnvelop getRecent5ByTypeAndTime(@RequestBody String jsonData);

    @PostMapping(value = DataRequestMapping.DataSearch.api_user_abnormal_times_a_week, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public MixEnvelop getAbnormalTimesAWeek(@RequestBody String jsonData);

    @PostMapping(value = DataRequestMapping.DataSearch.api_user_search_recent1, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public MixEnvelop getRecent1ByCodeAndDel(@RequestBody String jsonData);

    @PostMapping(value = DataRequestMapping.DataSearch.api_user_search_list_code_del, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public MixEnvelop getListByCodeAndDel(@RequestBody String jsonData);

    @PostMapping(value = DataRequestMapping.DataSearch.api_user_delete, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public MixEnvelop delete(@RequestBody String jsonData);

    @PostMapping(value = DataRequestMapping.DataSearch.api_user_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public MixEnvelop update(@RequestBody String jsonData);

    @PostMapping(value = DataRequestMapping.DataSearch.api_user_search_werun_datas, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public MixEnvelop getWeRunDataListById(@RequestBody String jsonData);

}
