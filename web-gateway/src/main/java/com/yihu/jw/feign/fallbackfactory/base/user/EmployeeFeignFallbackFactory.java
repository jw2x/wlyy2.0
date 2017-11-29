package com.yihu.jw.feign.fallbackfactory.base.user;

import com.yihu.jw.feign.base.user.EmployeeFeign;
import feign.hystrix.FallbackFactory;

/**
 * Created by chenweida on 2017/11/29.
 */
public class EmployeeFeignFallbackFactory  implements FallbackFactory<EmployeeFeign> {
    @Override
    public EmployeeFeign create(Throwable cause) {
        return null;
    }
}
