package com.yihu.jw.feign.base.version;

import com.yihu.jw.feign.fallbackfactory.base.base.SaasFeignFallbackFactory;
import com.yihu.jw.feign.fallbackfactory.base.version.UserVersionFeignFallbackFactory;
import com.yihu.jw.restmodel.CommonContants;
import com.yihu.jw.rm.base.BaseRequestMapping;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by chenweida on 2017/11/13.
 */
@FeignClient(
        name = CommonContants.svr_base // name值是eurika的实例名字
        ,fallbackFactory  = UserVersionFeignFallbackFactory.class
)
@RequestMapping(value = BaseRequestMapping.api_base_common)
public interface UserVersionFeign {

}
