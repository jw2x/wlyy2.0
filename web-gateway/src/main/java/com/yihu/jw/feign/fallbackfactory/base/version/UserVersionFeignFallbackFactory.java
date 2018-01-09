package com.yihu.jw.feign.fallbackfactory.base.version;

import com.yihu.jw.feign.base.version.UserVersionFeign;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * Created by chenweida on 2017/11/13.
 */
@Component
public class UserVersionFeignFallbackFactory implements FallbackFactory<UserVersionFeign> {
    @Override
    public UserVersionFeign create(Throwable cause) {
        return null;
    }
}
