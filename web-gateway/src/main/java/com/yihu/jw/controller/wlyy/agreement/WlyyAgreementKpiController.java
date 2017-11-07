package com.yihu.jw.controller.wlyy.agreement;

import com.yihu.jw.commnon.wlyy.AgreementContants;
import com.yihu.jw.feign.wlyy.agreement.WlyyAgreementKpiFeign;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AgreementContants.AgreementKpi.api_common)
@Api(value = "套餐指标相关操作", description = "套餐指标相关操作")
public class WlyyAgreementKpiController extends EnvelopRestController {

    private Logger logger= LoggerFactory.getLogger(WlyyAgreementKpiController.class);

    @Autowired
    private WlyyAgreementKpiFeign wlyyAgreementKpiFegin;

    @Autowired
    private Tracer tracer;

    @PostMapping(value = AgreementContants.AgreementKpi.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建套餐指标", notes = "创建套餐指标")
    public Envelop create(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        return wlyyAgreementKpiFegin.create(jsonData);
    }

    @PutMapping(value = AgreementContants.AgreementKpi.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改套餐指标", notes = "修改套餐指标")
    public Envelop update(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        return wlyyAgreementKpiFegin.update(jsonData);
    }


    @DeleteMapping(value =AgreementContants.AgreementKpi.api_delete)
    @ApiOperation(value = "删除套餐指标", notes = "删除套餐指标")
    public Envelop delete(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code) {
        return wlyyAgreementKpiFegin.delete(code);
    }

    @GetMapping(value =AgreementContants.AgreementKpi.api_getByCode)
    @ApiOperation(value = "根据code查找套餐指标", notes = "根据code查找套餐指标")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code
    ) {
        return wlyyAgreementKpiFegin.findByCode(code);
    }

    @RequestMapping(value =AgreementContants.AgreementKpi.api_queryPage, method = RequestMethod.GET)
    @ApiOperation(value = "分页获取套餐指标")
    public Envelop queryPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,agreementCode,kpiName,type,kpiTimes,status,del,kpiContent,keyword")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+kpiName,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts,
            @ApiParam(name = "size", value = "分页大小", defaultValue = "15")
            @RequestParam(value = "size", required = false) int size,
            @ApiParam(name = "page", value = "页码", defaultValue = "1")
            @RequestParam(value = "page", required = false) int page) throws Exception {
        return wlyyAgreementKpiFegin.queryPage(fields, filters, sorts, size, page);
    }

    @GetMapping(value =AgreementContants.AgreementKpi.api_getList)
    @ApiOperation(value = "获取套餐指标列表(不分页)")
    public Envelop getList(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,agreementCode,kpiName,type,kpiTimes,status,del,kpiContent,keyword")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+kpiName,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        return wlyyAgreementKpiFegin.getList(fields, filters, sorts);
    }
}
