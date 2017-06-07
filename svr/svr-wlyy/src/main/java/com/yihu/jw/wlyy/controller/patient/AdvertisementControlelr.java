package com.yihu.jw.wlyy.controller.patient;

import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.restmodel.wlyy.patient.WlyyPatientContants;
import com.yihu.jw.wlyy.entity.patient.WlyyAdvertisement;
import com.yihu.jw.wlyy.service.patient.AdvertisementService;
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
 * Created by Administrator on 2017/6/6 0006.
 */

@RestController
@RequestMapping(WlyyPatientContants.Advertisement.api_common)
@Api(value = "广告相关操作", description = "广告相关操作")
public class AdvertisementControlelr extends EnvelopRestController {

    @Autowired
    private AdvertisementService advertisementService;

    @PostMapping(value = WlyyPatientContants.Advertisement.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建广告", notes = "创建广告")
    public Envelop create(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            WlyyAdvertisement advertisement = toEntity(jsonData, WlyyAdvertisement.class);
            return Envelop.getSuccess(WlyyPatientContants.Advertisement.message_success_create, advertisementService.create(advertisement));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PutMapping(value = WlyyPatientContants.Advertisement.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改广告", notes = "修改广告")
    public Envelop update(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            WlyyAdvertisement advertisement = toEntity(jsonData, WlyyAdvertisement.class);
            return Envelop.getSuccess(WlyyPatientContants.Advertisement.message_success_update, advertisementService.update(advertisement));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @DeleteMapping(value = WlyyPatientContants.Advertisement.api_delete)
    @ApiOperation(value = "删除广告", notes = "删除广告")
    public Envelop delete(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code) {
        try {
            advertisementService.delete(code);
            return Envelop.getSuccess(WlyyPatientContants.Advertisement.message_success_delete );
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @GetMapping(value = WlyyPatientContants.Advertisement.api_getByCode)
    @ApiOperation(value = "根据code查找协议", notes = "根据code查找协议")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code
    ) {
        try {
            return Envelop.getSuccess(WlyyPatientContants.Advertisement.message_success_find, advertisementService.findByCode(code));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @RequestMapping(value = WlyyPatientContants.Advertisement.api_queryPage, method = RequestMethod.GET)
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
        List<WlyyAdvertisement> list = advertisementService.search(fields, filters, sorts, page, size);
        //获取总数
        long count=advertisementService.getCount(filters);
        //封装头信息
        pagedResponse(request, response, count, page, size);
        //封装返回格式
        List<WlyyAdvertisement> advertisement = convertToModels(list, new ArrayList<>(list.size()), WlyyAdvertisement.class, fields);

        return Envelop.getSuccessListWithPage(WlyyPatientContants.Advertisement.message_success_find_functions, advertisement, page, size, count);
    }


    @GetMapping(value = WlyyPatientContants.Advertisement.api_getList)
    @ApiOperation(value = "获取协议列表(不分页)")
    public Envelop getList(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,parentCode,saasId,name,price,posterPic,remark,type,status")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        //得到list数据
        List<WlyyAdvertisement> list = advertisementService.search(fields,filters,sorts);
        //封装返回格式
        List<WlyyAdvertisement> advertisement = convertToModels(list, new ArrayList<>(list.size()), WlyyAdvertisement.class, fields);
        return Envelop.getSuccessList(WlyyPatientContants.Advertisement.message_success_find_functions,advertisement);
    }

    @GetMapping(value = WlyyPatientContants.Advertisement.api_getListByPatientCode)
    @ApiOperation(value = "根据患者code获取广告")
    public Envelop getListByPatientCode(
            @ApiParam(name="patientCode")
            @RequestParam(value="") String patientCode){
        List<WlyyAdvertisement> advertisements = advertisementService.getListByPatientCode(patientCode);
        return Envelop.getSuccessList(WlyyPatientContants.Advertisement.message_success_find_functions,advertisements);
    }

}
