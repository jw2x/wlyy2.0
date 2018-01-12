package com.yihu.jw.feign.fallbackfactory.iot.data_input;

import com.yihu.jw.feign.iot.data_input.DataInputFeign;
import com.yihu.jw.feign.iot.data_input.DataSearchFeign;
import com.yihu.jw.restmodel.common.Envelop;
import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class DataSearchFeignFallbackFactory implements FallbackFactory<DataSearchFeign> {

    @Autowired
    private Tracer tracer;

    @Override
    public DataSearchFeign create(Throwable throwable) {

        return new DataSearchFeign() {
            @Override
            public Envelop getOne(String jsonData) {
                tracer.getCurrentSpan().logEvent("根据id获取单条数据失败:原因:" + throwable.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                return null;
            }

            @Override
            public Envelop getList(String jsonData, int page, int size) {
                tracer.getCurrentSpan().logEvent("获取多条数据失败:原因:" + throwable.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                return null;
            }

            @Override
            public Envelop getListPage(String jsonData, int page, int size) {
                tracer.getCurrentSpan().logEvent("获取数据分页列表失败:原因:" + throwable.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                return null;
            }

            @Override
            public Envelop getRecent5ByTypeAndTime(String jsonData, int page, int size) {
                tracer.getCurrentSpan().logEvent("获取最近"+size+"条记录数据:原因:" + throwable.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                return null;
            }

            @Override
            public Envelop getAbnormalTimesAWeek(String jsonData, int page, int size) {
                tracer.getCurrentSpan().logEvent("获取最近体征数据异常次数失败:原因:" + throwable.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                return null;
            }

            @Override
            public Envelop getRecent1ByCodeAndDel(String jsonData, int page, int size) {
                tracer.getCurrentSpan().logEvent("根据居民code和删除标识获取最近一次体征数据:" + throwable.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                return null;
            }

            @Override
            public Envelop getListByCodeAndDel(String jsonData, int page, int size) {
                tracer.getCurrentSpan().logEvent("根据居民code和删除标识获取所有体征数据失败:原因:" + throwable.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                return null;
            }
        };
    }
}
