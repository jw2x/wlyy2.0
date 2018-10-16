package com.yihu.jw.base.endpoint.saas;

import com.yihu.jw.base.service.saas.BaseEmailTemplateConfigService;
import com.yihu.jw.entity.base.saas.BaseEmailTemplateConfigDO;
import com.yihu.jw.restmodel.base.saas.BaseEmailTemplateConfigVO;
import com.yihu.jw.restmodel.web.*;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Endpoint - BaseEmailTemplateConfig
 * Created by zdm on 2018/10/10.
 */
@RestController
@RequestMapping(value = "/BaseEmailTemplateConfig")
@Api(value = "邮件模板管理", description = "Saas邮件模板管理", tags = {"wlyy基础服务 - Saas邮件模板管理"})
public class BaseEmailTemplateConfigEndpoint extends EnvelopRestEndpoint {

    @Autowired
    private BaseEmailTemplateConfigService baseEmailTemplateConfigService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建")
    public ObjEnvelop<BaseEmailTemplateConfigVO> create(
            @ApiParam(name = "baseEmailTemplateConfigDO", value = "baseEmailTemplateConfigDO")
            @RequestBody BaseEmailTemplateConfigDO baseEmailTemplateConfigDO) throws Exception {
        baseEmailTemplateConfigService.save(baseEmailTemplateConfigDO);
        return success("创建成功", baseEmailTemplateConfigDO, BaseEmailTemplateConfigVO.class);
    }


}
