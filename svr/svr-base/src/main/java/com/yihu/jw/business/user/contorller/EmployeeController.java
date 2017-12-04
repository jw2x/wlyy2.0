package com.yihu.jw.business.user.contorller;

import com.yihu.jw.business.user.service.EmployeeService;
import com.yihu.jw.rm.base.BaseRequestMapping;
import com.yihu.jw.rm.base.BaseSmsRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenweida on 2017/5/11.
 */
@RestController
@RequestMapping(BaseSmsRequestMapping.api_common)
@Api(description = "医生，行政人员等非患者用户")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "根据行政人员账号查找用户")
    @GetMapping(value = BaseRequestMapping.Employee.api_getEmployeeByAccount)
    public String getEmployeeByAccount(
            @ApiParam(name = "userAccount", value = "用户账号", required = true) @RequestParam(value = "userAccount", required = true) String userAccount) {
        

        return "调用根据code查找患者";
    }
}
