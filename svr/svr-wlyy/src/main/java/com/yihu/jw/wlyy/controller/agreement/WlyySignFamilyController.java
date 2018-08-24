package com.yihu.jw.wlyy.controller.agreement;

import com.yihu.jw.entity.wlyy.agreement.WlyySignFamilyDO;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.wlyy.WlyyRequestMapping;
import com.yihu.jw.wlyy.service.agreement.WlyySignFamilyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(WlyyRequestMapping.api_wlyy_common)
@Api(value = "签约相关操作", description = "签约相关操作")
public class WlyySignFamilyController extends EnvelopRestEndpoint {

    @Autowired
    private WlyySignFamilyService wlyySignFamilyService;

    @PostMapping(value = WlyyRequestMapping.SignFamily.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建签约", notes = "创建签约")
    public Envelop create(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) throws Exception {
        try {
            WlyySignFamilyDO wlyySignFamily = toEntity(jsonData, WlyySignFamilyDO.class);
            return success(WlyyRequestMapping.SignFamily.message_success_create, wlyySignFamilyService.create(wlyySignFamily));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PutMapping(value = WlyyRequestMapping.SignFamily.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改签约", notes = "修改签约")
    public Envelop update(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) throws Exception {
        try {
            WlyySignFamilyDO wlyySignFamily = toEntity(jsonData, WlyySignFamilyDO.class);
            return success(WlyyRequestMapping.SignFamily.message_success_update, wlyySignFamilyService.update(wlyySignFamily));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @GetMapping(value = WlyyRequestMapping.SignFamily.api_getById)
    @ApiOperation(value = "根据code查找协议", notes = "根据code查找协议")
    public Envelop findByCode(
            @ApiParam(name = "id", value = "id")
            @RequestParam(value = "id", required = true) String id
    ) {
        try {
            return success(WlyyRequestMapping.SignFamily.message_success_find, wlyySignFamilyService.findById(id));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @RequestMapping(value = WlyyRequestMapping.SignFamily.api_queryPage, method = RequestMethod.GET)
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
        List<WlyySignFamilyDO> list = wlyySignFamilyService.search(fields, filters, sorts, page, size);
        //获取总数
        long count=wlyySignFamilyService.getCount(filters);
        //封装头信息
        pagedResponse(request, response, count, page, size);
        //封装返回格式
        List<WlyySignFamilyDO> wlyySignFamily = convertToModels(list, new ArrayList<>(list.size()), WlyySignFamilyDO.class, fields);

        return success(WlyyRequestMapping.SignFamily.message_success_find_functions,wlyySignFamily, page, size, (int)count);
    }


    @GetMapping(value = WlyyRequestMapping.SignFamily.api_getList)
    @ApiOperation(value = "获取协议列表(不分页)")
    public Envelop getList(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        //得到list数据
        List<WlyySignFamilyDO> list = wlyySignFamilyService.search(fields,filters,sorts);
        //封装返回格式
        List<WlyySignFamilyDO> wlyySignFamily = convertToModels(list, new ArrayList<>(list.size()), WlyySignFamilyDO.class, fields);
        return success(WlyyRequestMapping.SignFamily.message_success_find_functions, wlyySignFamily);
    }

}
