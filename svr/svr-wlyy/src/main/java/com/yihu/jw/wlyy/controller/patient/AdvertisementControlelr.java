package com.yihu.jw.wlyy.controller.patient;

import com.yihu.jw.exception.ApiException;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.wlyy.WlyyRequestMapping;
import com.yihu.jw.entity.wlyy.patient.WlyyAdvertisementDO;
import com.yihu.jw.wlyy.service.patient.AdvertisementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/6 0006.
 */

@RestController
@RequestMapping(value = WlyyRequestMapping.api_wlyy_common)
@Api(value = "广告相关操作", description = "广告相关操作")
public class AdvertisementControlelr extends EnvelopRestEndpoint {

    @Autowired
    private AdvertisementService advertisementService;

    @PostMapping(value = WlyyRequestMapping.Advertisement.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建广告", notes = "创建广告")
    public Envelop create(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) throws IOException {
        try {
            WlyyAdvertisementDO advertisement = toEntity(jsonData, WlyyAdvertisementDO.class);
            return success(WlyyRequestMapping.Advertisement.message_success_create, advertisementService.create(advertisement));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PutMapping(value = WlyyRequestMapping.Advertisement.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改广告", notes = "修改广告")
    public Envelop update(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) throws IOException {
        try {
            WlyyAdvertisementDO advertisement = toEntity(jsonData, WlyyAdvertisementDO.class);
            return success(WlyyRequestMapping.Advertisement.message_success_update, advertisementService.update(advertisement));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @DeleteMapping(value = WlyyRequestMapping.Advertisement.api_delete)
    @ApiOperation(value = "删除广告", notes = "删除广告")
    public Envelop delete(
            @ApiParam(name = "id", value = "id")
            @RequestParam(value = "id", required = true) String id) {
        try {
            advertisementService.delete(id);
            return Envelop.getSuccess(WlyyRequestMapping.Advertisement.message_success_delete);
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @GetMapping(value = WlyyRequestMapping.Advertisement.api_getById)
    @ApiOperation(value = "根据id查找广告", notes = "根据id查找广告")
    public Envelop findByCode(
            @ApiParam(name = "id", value = "id")
            @RequestParam(value = "id", required = true) String id
    ) {
        try {
            return success(WlyyRequestMapping.Advertisement.message_success_find, advertisementService.findById(id));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @RequestMapping(value = WlyyRequestMapping.Advertisement.api_queryPage, method = RequestMethod.GET)
    @ApiOperation(value = "分页获取广告")
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
        List<WlyyAdvertisementDO> list = advertisementService.search(fields, filters, sorts, page, size);
        //获取总数
        long count = advertisementService.getCount(filters);
        //封装头信息
        pagedResponse(request, response, count, page, size);
        //封装返回格式
        List<WlyyAdvertisementDO> advertisement = convertToModels(list, new ArrayList<>(list.size()), WlyyAdvertisementDO.class, fields);

        return success(WlyyRequestMapping.Advertisement.message_success_find_functions, advertisement, page, size, (int)count);
    }


    @GetMapping(value = WlyyRequestMapping.Advertisement.api_getList)
    @ApiOperation(value = "获取广告列表(不分页)")
    public Envelop getList(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,parentCode,saasId,name,price,posterPic,remark,type,status")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        //得到list数据
        List<WlyyAdvertisementDO> list = advertisementService.search(fields, filters, sorts);
        //封装返回格式
        List<WlyyAdvertisementDO> advertisement = convertToModels(list, new ArrayList<>(list.size()), WlyyAdvertisementDO.class, fields);
        return success(WlyyRequestMapping.Advertisement.message_success_find_functions, advertisement);
    }

    @GetMapping(value = WlyyRequestMapping.Advertisement.api_getListByPatientId)
    @ApiOperation(value = "根据患者code获取广告")
    public Envelop getListByPatientCode(
            @ApiParam(name = "patientId")
            @RequestParam(value = "patientId") String patientId,
            HttpServletRequest request
    ) {
        List<WlyyAdvertisementDO> advertisements = advertisementService.getListByPatientId(patientId, request);
        return success(WlyyRequestMapping.Advertisement.message_success_find_functions, advertisements);
    }

    @GetMapping(value = WlyyRequestMapping.Advertisement.api_getListByHttp)
    @ApiOperation(value = "根据患者code地区(通过http判断)获取广告")
    public Envelop getListByHttp(HttpServletRequest request) {
        List<WlyyAdvertisementDO> advertisements = advertisementService.getByHttp(request);
        return success(WlyyRequestMapping.Advertisement.message_success_find_functions, advertisements);
    }

    /**
     * 供网关调用
     *
     * @param ipAddress
     * @return
     */
    @GetMapping(value = WlyyRequestMapping.Advertisement.api_getListByIp)
    @ApiOperation(value = "根据患者ip地址(供网关调用)")
    public Envelop getListByIp(@RequestParam(value = "ipAddress") String ipAddress) {
        List<WlyyAdvertisementDO> advertisements = advertisementService.getListByIp(ipAddress);
        return success(WlyyRequestMapping.Advertisement.message_success_find_functions, advertisements);
    }

}
