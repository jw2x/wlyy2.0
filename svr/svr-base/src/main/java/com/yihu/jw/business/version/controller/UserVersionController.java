package com.yihu.jw.business.version.controller;

import com.yihu.jw.base.sms.BaseSms;
import com.yihu.jw.base.version.BaseUserVersion;
import com.yihu.jw.business.version.service.UserVersionService;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.rm.base.BaseSmsRequestMapping;
import com.yihu.jw.rm.base.BaseVersionRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by chenweida on 2017/11/10.
 */
@RestController
@RequestMapping(BaseVersionRequestMapping.api_common)
@Api(description = "灰度发布,用户版本信息")
public class UserVersionController extends EnvelopRestController {
    @Autowired
    private UserVersionService userVersionService;


    @PostMapping(value = BaseVersionRequestMapping.UserVersion.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "给用户添加灰度发布版本信息", notes = "给用户添加灰度发布版本信息")
    public Envelop createUserVersion(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            BaseUserVersion baseUserVersion = toEntity(jsonData, BaseUserVersion.class);
            return Envelop.getSuccess(BaseVersionRequestMapping.UserVersion.message_success_create, userVersionService.createUserVersion(baseUserVersion));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @DeleteMapping(value = BaseVersionRequestMapping.UserVersion.api_delete, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "删除用户灰度发布版本信息", notes = "删除用户灰度发布版本信息")
    public Envelop deleteUserVersion(
            @ApiParam(name = "id", value = "id")
            @RequestParam(value = "id", required = true) String id) {
        try {
            userVersionService.deleteUserVersion(id);
            return Envelop.getSuccess(BaseVersionRequestMapping.UserVersion.message_success_delete);
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }
}
