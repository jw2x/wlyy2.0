package com.yihu.jw.feign.fallbackfactory.base.version;

import com.yihu.jw.feign.base.version.UserVersionFeign;
import feign.hystrix.FallbackFactory;

/**
 * Created by chenweida on 2017/11/13.
 */
public class UserVersionFeignFallbackFactory implements FallbackFactory<UserVersionFeign> {
    @Override
    public UserVersionFeign create(Throwable cause) {
        return null;
    }
}
