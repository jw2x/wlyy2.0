package com.yihu.jw.fegin.iot.common;

import com.yihu.jw.fegin.fallbackfactory.iot.common.IotSystemDictFallbackFactory;
import com.yihu.jw.restmodel.CommonContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.iot.dict.IotSystemDictVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yeshijie on 2018/1/20.
 */
@FeignClient(
        name = CommonContants.svr_iot // name值是eurika的实例名字
        ,fallbackFactory  = IotSystemDictFallbackFactory.class
)
@RequestMapping(IotRequestMapping.Common.system_dict)
public interface IotSystemDictFeign {


    @GetMapping(value = IotRequestMapping.System.findDictByCode)
    public Envelop<IotSystemDictVO> getList(@RequestParam(value = "dictName", required = true) String dictName) throws Exception ;

}
