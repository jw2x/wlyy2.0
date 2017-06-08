package com.yihu.jw.wlyy.controller.agreement;

import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.restmodel.wlyy.agreement.WlyyAgreementContants;
import com.yihu.jw.wlyy.entity.agreement.WlyyAgreement;
import com.yihu.jw.wlyy.service.agreement.WlyyAgreementService;
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

@RestController
@RequestMapping(WlyyAgreementContants.Agreement.api_common)
@Api(value = "协议相关操作", description = "协议相关操作")
public class WlyyAgreementController extends EnvelopRestController {

    @Autowired
    private WlyyAgreementService wlyyAgreementService;

    @PostMapping(value = WlyyAgreementContants.Agreement.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建协议", notes = "创建协议")
    public Envelop create(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            WlyyAgreement wlyyAgreement = toEntity(jsonData, WlyyAgreement.class);
            return Envelop.getSuccess(WlyyAgreementContants.Agreement.message_success_create, wlyyAgreementService.create(wlyyAgreement));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PutMapping(value = WlyyAgreementContants.Agreement.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改协议", notes = "修改协议")
    public Envelop update(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            WlyyAgreement wlyyAgreement = toEntity(jsonData, WlyyAgreement.class);
            return Envelop.getSuccess(WlyyAgreementContants.Agreement.message_success_update, wlyyAgreementService.update(wlyyAgreement));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }


    @DeleteMapping(value = WlyyAgreementContants.Agreement.api_delete)
    @ApiOperation(value = "删除协议", notes = "删除协议")
    public Envelop delete(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code) {
        try {
            wlyyAgreementService.delete(code);
            return Envelop.getSuccess(WlyyAgreementContants.Agreement.message_success_delete );
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @GetMapping(value = WlyyAgreementContants.Agreement.api_getByCode)
    @ApiOperation(value = "根据code查找协议", notes = "根据code查找协议")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code
    ) {
        try {
            return Envelop.getSuccess(WlyyAgreementContants.Agreement.message_success_find, wlyyAgreementService.findByCode(code));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @RequestMapping(value = WlyyAgreementContants.Agreement.api_queryPage, method = RequestMethod.GET)
    @ApiOperation(value = "分页获取协议")
    public Envelop queryPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,parentCode,saasId,name,price,posterPic,remark,type,status")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
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
        List<WlyyAgreement> list = wlyyAgreementService.search(fields, filters, sorts, page, size);
        //获取总数
        long count=wlyyAgreementService.getCount(filters);
        //封装头信息
        pagedResponse(request, response, count, page, size);
        //封装返回格式
        List<WlyyAgreement> wlyyAgreement = convertToModels(list, new ArrayList<>(list.size()), WlyyAgreement.class, fields);

        return Envelop.getSuccessListWithPage(WlyyAgreementContants.Agreement.message_success_find_functions,wlyyAgreement, page, size,count);
    }


    @GetMapping(value = WlyyAgreementContants.Agreement.api_getList)
    @ApiOperation(value = "获取协议列表(不分页)")
    public Envelop getList(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,parentCode,saasId,name,price,posterPic,remark,type,status")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        //得到list数据
        List<WlyyAgreement> list = wlyyAgreementService.search(fields,filters,sorts);
        //封装返回格式
        List<WlyyAgreement> wlyyAgreement = convertToModels(list, new ArrayList<>(list.size()), WlyyAgreement.class, fields);
        return Envelop.getSuccessList(WlyyAgreementContants.Agreement.message_success_find_functions,wlyyAgreement);
    }

}
