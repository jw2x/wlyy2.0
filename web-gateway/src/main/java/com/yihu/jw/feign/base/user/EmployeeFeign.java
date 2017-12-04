package com.yihu.jw.feign.base.user;

import com.yihu.jw.feign.fallbackfactory.base.user.EmployeeFeignFallbackFactory;
import com.yihu.jw.restmodel.CommonContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.rm.base.BaseRequestMapping;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by chenweida on 2017/11/29.
 */
@FeignClient(
        name = CommonContants.svr_base // name值是eurika的实例名字
        ,fallbackFactory  = EmployeeFeignFallbackFactory.class
)
@RequestMapping(value = BaseRequestMapping.api_base_common)
public interface EmployeeFeign {


    @RequestMapping(value = BaseRequestMapping.Employee.api_getEmployeeByAccount, method = RequestMethod.GET)
    Envelop getEmployeeByAccount(@RequestParam(value = "userAccount",required = true)String userAccount);
}
