package com.yihu.jw.wlyy.agreement.controller;

import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.restmodel.wlyy.WlyyContants;
import com.yihu.jw.wlyy.agreement.entity.WlyySignFamily;
import com.yihu.jw.wlyy.agreement.service.WlyySignFamilyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(WlyyContants.SignFamily.api_common)
@Api(value = "签约相关操作", description = "签约相关操作")
public class WlyySignFamilyController extends EnvelopRestController {

    @Autowired
    private WlyySignFamilyService wlyySignFamilyService;

    @PostMapping(value = WlyyContants.SignFamily.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建签约", notes = "创建签约")
    public Envelop create(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) throws ParseException {
        try {
            WlyySignFamily wlyySignFamily = toEntity(jsonData, WlyySignFamily.class);
            return Envelop.getSuccess(WlyyContants.SignFamily.message_success_create, wlyySignFamilyService.create(wlyySignFamily));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PutMapping(value = WlyyContants.SignFamily.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改签约", notes = "修改签约")
    public Envelop update(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) throws ParseException {
        try {
            WlyySignFamily wlyySignFamily = toEntity(jsonData, WlyySignFamily.class);
            return Envelop.getSuccess(WlyyContants.SignFamily.message_success_update, wlyySignFamilyService.update(wlyySignFamily));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @GetMapping(value =WlyyContants.SignFamily.api_getByCode)
    @ApiOperation(value = "根据code查找协议", notes = "根据code查找协议")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code
    ) {
        try {
            return Envelop.getSuccess(WlyyContants.SignFamily.message_success_find, wlyySignFamilyService.findByCode(code));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @RequestMapping(value =WlyyContants.SignFamily.api_queryPage, method = RequestMethod.GET)
    @ApiOperation(value = "分页获取协议")
    public Envelop queryPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name")
            @RequestParam(value = "sorts", required = false) String sorts,
            @ApiParam(name = "size", value = "分页大小", defaultValue = "15")
            @RequestParam(value = "size", required = false) int size,
            @ApiParam(name = "page", value = "页码", defaultValue = "1")
            @RequestParam(value = "page", required = false) int page,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        //得到list数据
        List<WlyySignFamily> list = wlyySignFamilyService.search(fields, filters, sorts, page, size);
        //获取总数
        long count=wlyySignFamilyService.getCount(filters);
        //封装头信息
        pagedResponse(request, response, count, page, size);
        //封装返回格式
        List<WlyySignFamily> wlyySignFamily = convertToModels(list, new ArrayList<>(list.size()), WlyySignFamily.class, fields);

        return Envelop.getSuccessListWithPage(WlyyContants.SignFamily.message_success_find_functions,wlyySignFamily, page, size,count);
    }


    @GetMapping(value =WlyyContants.SignFamily.api_getList)
    @ApiOperation(value = "获取协议列表(不分页)")
    public Envelop getList(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        //得到list数据
        List<WlyySignFamily> list = wlyySignFamilyService.search(fields,filters,sorts);
        //封装返回格式
        List<WlyySignFamily> wlyySignFamily = convertToModels(list, new ArrayList<>(list.size()), WlyySignFamily.class, fields);
        return Envelop.getSuccessList(WlyyContants.SignFamily.message_success_find_functions,wlyySignFamily);
    }

}
