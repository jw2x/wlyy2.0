package com.yihu.jw.healthyhouse.controller.facilities;

import com.yihu.jw.healthyhouse.model.facilities.Facilities;
import com.yihu.jw.healthyhouse.service.facilities.FacilitiesService;
import com.yihu.jw.restmodel.web.*;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.health.house.HealthyHouseMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.ObjEnvelop;
import com.yihu.jw.restmodel.web.PageEnvelop;


/**
 * 设施管理
 * Created by zdm on 2018/9/19.
 */
@RestController
@RequestMapping(HealthyHouseMapping.api_healthyHouse_common)
@Api(value = "Facilities", description = "设施管理", tags = {"设施管理"})
public class FacilitiesController extends EnvelopRestEndpoint {

    @Autowired
    private FacilitiesService facilitiesService;

    @ApiOperation(value = "获取设施列表", responseContainer = "List")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.Facilities.PAGE)
    public PageEnvelop<Facilities> getDictionaries(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器", defaultValue = "")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序", defaultValue = "")
            @RequestParam(value = "sorts", required = false) String sorts,
            @ApiParam(name = "size", value = "分页大小", defaultValue = "15")
            @RequestParam(value = "size", required = false) Integer size,
            @ApiParam(name = "page", value = "页码", defaultValue = "1")
            @RequestParam(value = "page", required = false) Integer page) throws Exception {
        List<Facilities> FacilitiesList = facilitiesService.search(fields, filters, sorts, page, size);
        return success(FacilitiesList, FacilitiesList.size(), page, size);
    }

    @ApiOperation(value = "创建设施")
    @PostMapping(value = HealthyHouseMapping.HealthyHouse.Facilities.CREATE)
    public ObjEnvelop<Facilities> createDictionary(
            @ApiParam(name = "facilities", value = "设施JSON结构")
            @RequestBody Facilities facilities) throws IOException {
        List<Facilities> facilitiesList = null;
        if (StringUtils.isEmpty(facilities.getCode())) {
            return failed("设施编码不能为空！", ObjEnvelop.class);
        } else {
            facilitiesList = facilitiesService.findByField("code", facilities.getCode());
            if (null != facilitiesList && facilitiesList.size() > 0) {
                return failed("设施编码已存在！", ObjEnvelop.class);
            }
        }
        if (StringUtils.isEmpty(facilities.getName())) {
            return failed("设施名称不能为空！", ObjEnvelop.class);
        } else {
            facilitiesList = facilitiesService.findByField("name", facilities.getName());
            if (null != facilitiesList && facilitiesList.size() > 0) {
                return failed("设施名称已存在！", ObjEnvelop.class);
            }
        }
        if (!(facilities.getLongitude() > 0)) {
            return failed("设施经度不能为空！", ObjEnvelop.class);
        }
        if (!(facilities.getLatitude() > 0)) {
            return failed("设施纬度不能为空！", ObjEnvelop.class);
        }
        if (StringUtils.isEmpty(facilities.getCategory().toString())) {
            return failed("设施类别不正确，请参考系统字典：设施类别！", ObjEnvelop.class);
        }
        Facilities Facilities = facilitiesService.save(facilities);
        return success(Facilities);
    }

    @ApiOperation(value = "获取设施")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.Facilities.GET_FACILITIES_BY_ID)
    public ObjEnvelop<Facilities> getDictionary(
            @ApiParam(name = "id", value = "设施ID", defaultValue = "")
            @RequestParam(value = "id") String id) throws Exception {
        Facilities facilities = facilitiesService.findById(id);
        if (facilities == null) {
            return failed("设施不存在！", ObjEnvelop.class);
        }
        return success(facilities);
    }

    @ApiOperation(value = "获取设施")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.Facilities.GET_FACILITIES_BY_FIELD)
    public ListEnvelop<Facilities> getDictionaryByPhoneticCode(
            @ApiParam(name = "field", value = "查找字段名", required = true)
            @RequestParam(value = "field") String field,
            @ApiParam(name = "value", value = "检索值")
            @RequestParam(value = "value") String value) throws Exception {
        List<Facilities> facilitiesList = facilitiesService.findByField(field, value);
        return success(facilitiesList);
    }

    @ApiOperation(value = "更新设施")
    @PutMapping(value = HealthyHouseMapping.HealthyHouse.Facilities.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ObjEnvelop<Facilities> updateDictionary(
            @ApiParam(name = "facilities", value = "设施JSON结构")
            @RequestBody Facilities facilities) throws Exception {
        if (StringUtils.isEmpty(facilities.getCode())) {
            return failed("设施编码不能为空！", ObjEnvelop.class);
        }
        if (StringUtils.isEmpty(facilities.getName())) {
            return failed("设施名称不能为空！", ObjEnvelop.class);
        }
        if (!(facilities.getLongitude() > 0)) {
            return failed("设施经度不能为空！", ObjEnvelop.class);
        }
        if (!(facilities.getLatitude() > 0)) {
            return failed("设施纬度不能为空！", ObjEnvelop.class);
        }
        if (StringUtils.isEmpty(facilities.getCategory().toString())) {
            return failed("设施类别不正确，请参考系统字典：设施类别！", ObjEnvelop.class);
        }
        facilities = facilitiesService.save(facilities);
        return success(facilities);
    }

    @ApiOperation(value = "删除设施")
    @DeleteMapping(value = HealthyHouseMapping.HealthyHouse.Facilities.DELETE)
    public Envelop deleteDictionary(
            @ApiParam(name = "facilitiesId", value = "设施ID")
            @RequestParam(value = "facilitiesId") String facilitiesId) throws Exception {
        Facilities facilities = new Facilities();
        facilities.setId(facilitiesId);
        facilitiesService.delete(facilities);
        return success("success");
    }


}
