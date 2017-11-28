package com.yihu.jw.business.user.contorller;

import com.yihu.jw.business.user.service.EmployeeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenweida on 2017/5/11.
 */
@RestController
@RequestMapping("/employee")
@Api(description = "医生，行政人员等非患者用户")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;


}
