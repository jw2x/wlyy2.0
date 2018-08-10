package com.yihu.jw.business.demo;

import com.yihu.jw.business.user.dao.BaseRoleDao;
import com.yihu.jw.restmodel.common.Envelop;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;


/**
 * Created by chenweida on 2017/5/10.
 */
@RestController
@RequestMapping("/demo")
@Api(description = "demo例子")
@RefreshScope
public class DemoController {
    @Autowired
    private BaseRoleDao baseRoleDao;

    @ApiOperation(value = "根据code查找患者")
    @GetMapping(value = "findByCode")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "患者code", required = false) @RequestParam(value = "code", required = false) String code) {
        baseRoleDao.findAllByName("管理员");
        return  Envelop.getSuccess("成功");
    }

}
