package com.yihu.jw.user.contorller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenweida on 2017/5/11.
 */
@RestController
@RequestMapping("/employee")
@Api(description = "医生，行政人员等非患者用户")
public class EmployeeController {
}
