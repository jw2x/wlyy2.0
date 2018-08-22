package com.yihu.jw.fegin.fallbackfactory.iot.data_input;

import com.yihu.jw.fegin.iot.data_input.DataSearchFeign;
import com.yihu.jw.restmodel.web.MixEnvelop;
import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;

@Component
public class DataSearchFeignFallbackFactory implements FallbackFactory<DataSearchFeign> {

    @Autowired
    private Tracer tracer;

    @Override
    public DataSearchFeign create(Throwable throwable) {

        return new DataSearchFeign() {
            @Override
            public MixEnvelop getOne(String jsonData) {
                tracer.getCurrentSpan().logEvent("根据id获取单条数据失败:原因:" + throwable.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                return null;
            }

            @Override
            public MixEnvelop getList(String jsonData ) {
                tracer.getCurrentSpan().logEvent("获取多条数据失败:原因:" + throwable.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                return null;
            }

            @Override
            public MixEnvelop getListPage(String jsonData ) {
                tracer.getCurrentSpan().logEvent("获取数据分页列表失败:原因:" + throwable.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                return null;
            }

            @Override
            public MixEnvelop getRecent5ByTypeAndTime(String jsonData ) {
                tracer.getCurrentSpan().logEvent("获取最近5条记录数据失败:原因:" + throwable.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                return null;
            }

            @Override
            public MixEnvelop getAbnormalTimesAWeek(String jsonData ) {
                tracer.getCurrentSpan().logEvent("获取最近体征数据异常次数失败:原因:" + throwable.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                return null;
            }

            @Override
            public MixEnvelop getRecent1ByCodeAndDel(String jsonData ) {
                tracer.getCurrentSpan().logEvent("根据居民code和删除标识获取最近一次体征数据:" + throwable.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                return null;
            }

            @Override
            public MixEnvelop getListByCodeAndDel(String jsonData ) {
                tracer.getCurrentSpan().logEvent("根据居民code和删除标识获取所有体征数据失败:原因:" + throwable.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                return null;
            }

            @Override
            public MixEnvelop delete(String jsonData) {
                tracer.getCurrentSpan().logEvent("根据居民rid删除用户体征数据失败:原因:" + throwable.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                return null;
            }

            @Override
            public MixEnvelop update(String jsonData) {
                tracer.getCurrentSpan().logEvent("根据rid修改用户体征数据失败:原因:" + throwable.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                return null;
            }

            @Override
            public MixEnvelop getWeRunDataListById(String jsonData) {
                tracer.getCurrentSpan().logEvent("根据用户code用户微信运动数据列表数据失败:原因:" + throwable.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                return null;
            }
        };
    }
}
