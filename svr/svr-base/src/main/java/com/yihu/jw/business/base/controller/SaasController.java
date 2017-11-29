package com.yihu.jw.business.base.controller;

import com.yihu.jw.base.base.SaasDO;
import com.yihu.jw.business.base.service.SaasService;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.rm.base.BaseRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenweida on 2017/5/19.
 */
@RestController
@RequestMapping(BaseRequestMapping.api_base_common)
@Api(value = "Saas配置模块", description = "Saas配置模块接口管理")
public class SaasController extends EnvelopRestController {
    @Autowired
    private SaasService saasService;

    @PostMapping(value = BaseRequestMapping.Saas.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建Saas配置", notes = "创建单个Saas配置")
    public Envelop createSaas(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            SaasDO saas = toEntity(jsonData, SaasDO.class);
            return Envelop.getSuccess(BaseRequestMapping.Saas.message_success_create, saasService.createSaas(saas));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PutMapping(value = BaseRequestMapping.Saas.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改Saas配置", notes = "修改Saas配置")
    public Envelop updateSaas(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            SaasDO saas = toEntity(jsonData, SaasDO.class);
            return Envelop.getSuccess(BaseRequestMapping.Saas.message_success_update, saasService.updateSaas(saas));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @DeleteMapping(value = BaseRequestMapping.Saas.api_delete, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "删除Saas配置", notes = "删除Saas配置")
    public Envelop deleteSaas(
            @ApiParam(name = "id", value = "id")
            @RequestParam(value = "id", required = true) String id) {
        try {
            saasService.deleteSaas(id);
            return Envelop.getSuccess(BaseRequestMapping.Saas.message_success_delete);
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @GetMapping(value = BaseRequestMapping.Saas.api_getById, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "根据code查找Saas配置", notes = "根据code查找Saas配置")
    public Envelop findByCode(
            @ApiParam(name = "id", value = "id")
            @RequestParam(value = "id", required = true) String id
    ) {
        try {
            return Envelop.getSuccess(BaseRequestMapping.Saas.message_success_find, saasService.findById(id));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }


    @RequestMapping(value = BaseRequestMapping.Saas.api_getSaass, method = RequestMethod.GET)
    @ApiOperation(value = "获取Saas配置列表(分页)")
    public Envelop getSaass(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "code,name,saasId,parentCode,remark")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            //code like 1,name大于aa ,code 等于1 , defaultValue = "code?1;name>aa;code=1"
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts,
            @ApiParam(name = "size", value = "分页大小", defaultValue = "15")
            @RequestParam(value = "size", required = false) int size,
            @ApiParam(name = "page", value = "页码", defaultValue = "1")
            @RequestParam(value = "page", required = false) int page,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        //得到list数据
        List<SaasDO> list = saasService.search(fields, filters, sorts, page, size);
        //获取总数
        long count = saasService.getCount(filters);
        //封装头信息
        pagedResponse(request, response, count, page, size);
        //封装返回格式
        List<SaasDO> mSaass = convertToModels(list, new ArrayList<>(list.size()), SaasDO.class, fields);

        return Envelop.getSuccessListWithPage(BaseRequestMapping.Saas.message_success_find_saass, mSaass, page, size, count);
    }


    @GetMapping(value = BaseRequestMapping.Saas.api_getSaassNoPage)
    @ApiOperation(value = "获取Saas配置列表，不分页")
    public Envelop getAppsNoPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "code,name,id,remark")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        //得到list数据
        List<SaasDO> list = saasService.search(fields, filters, sorts);
        //封装返回格式
        List<SaasDO> mSaass = convertToModels(list, new ArrayList<>(list.size()), SaasDO.class, fields);
        return Envelop.getSuccessList(BaseRequestMapping.Saas.message_success_find_saass, mSaass);
    }
}
