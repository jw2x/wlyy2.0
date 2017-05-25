package com.yihu.jw.quota.controller;

import com.yihu.jw.quota.model.QuotaResult;
import com.yihu.jw.quota.service.QuotaService;
import com.yihu.jw.restmodel.base.BaseContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.exception.ApiException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by chenweida on 2017/5/23.
 */
@RestController
@RequestMapping("quota")
public class QuotaController {
    @Autowired
    private QuotaService quotaService;

    @GetMapping(value = "findBy_Id")
    public Envelop findBy_Id(
            @ApiParam(name = "id", value = "", defaultValue = "")
            @RequestParam(value = "id", required = true) String id) {
        try {

            return Envelop.getSuccess(BaseContants.Function.message_success_create, quotaService.findBy_Id(id));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }
    @GetMapping(value = "findById")
    public Envelop findById(
            @ApiParam(name = "id", value = "", defaultValue = "")
            @RequestParam(value = "id", required = true) String id) {
        try {

            return Envelop.getSuccess(BaseContants.Function.message_success_create, quotaService.findById(id));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }
}
