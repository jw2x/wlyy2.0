package com.yihu.jw.healthyhouse.controller.facilities;

import com.yihu.jw.healthyhouse.model.facility.Facility;
import com.yihu.jw.healthyhouse.service.facility.FacilityService;
import com.yihu.jw.restmodel.web.*;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.health.house.HealthyHouseMapping;
import com.yihu.jw.util.date.DateUtil;
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
@Api(value = "Facility", description = "设施管理", tags = {"设施管理"})
public class FacilitiesController extends EnvelopRestEndpoint {

    @Autowired
    private FacilityService facilityService;

    @ApiOperation(value = "获取设施列表", responseContainer = "List")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.Facilities.PAGE)
    public PageEnvelop<Facility> getFacilities(
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
         List<Facility> facilityList = facilityService.search(fields, filters, sorts, page, size);
        return success(facilityList, (null==facilityList)?0:facilityList.size(), page, size);
    }

    @ApiOperation(value = "创建设施")
    @PostMapping(value = HealthyHouseMapping.HealthyHouse.Facilities.CREATE)
    public ObjEnvelop<Facility> createFacilities(
            @ApiParam(name = "facility", value = "设施JSON结构")
            @RequestParam(value = "facility")String facility,
            @ApiParam(name = "facilityServerStr", value = "服务字符串，使用‘,’隔开")
            @RequestParam(value = "facilityServerStr")String facilityServerStr) throws IOException {
        Facility facility1=toEntity(facility,Facility.class);
        String[] server=facilityServerStr.split(",");

        List<Facility> facilityList = null;
        if (StringUtils.isEmpty(facility1.getCode())) {
            return failed("设施编码不能为空！", ObjEnvelop.class);
        } else {
            facilityList = facilityService.findByField("code", facility1.getCode());
            if (null != facilityList && facilityList.size() > 0) {
                return failed("设施编码已存在！", ObjEnvelop.class);
            }
        }
        if (StringUtils.isEmpty(facility1.getName())) {
            return failed("设施名称不能为空！", ObjEnvelop.class);
        } else {
            facilityList = facilityService.findByField("name", facility1.getName());
            if (null != facilityList && facilityList.size() > 0) {
                return failed("设施名称已存在！", ObjEnvelop.class);
            }
        }
        if (!(facility1.getLongitude() > 0)) {
            return failed("设施经度不能为空！", ObjEnvelop.class);
        }
        if (!(facility1.getLatitude() > 0)) {
            return failed("设施纬度不能为空！", ObjEnvelop.class);
        }
        if (StringUtils.isEmpty(facility1.getCategory().toString())) {
            return failed("设施类别不正确，请参考系统字典：设施类别！", ObjEnvelop.class);
        }
        Facility Facility = facilityService.save(facility1);
        return success(Facility);
    }

    @ApiOperation(value = "获取设施")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.Facilities.GET_FACILITIES_BY_ID)
    public ObjEnvelop<Facility> getFacilitie(
            @ApiParam(name = "id", value = "设施ID", defaultValue = "")
            @RequestParam(value = "id") String id) throws Exception {
        Facility facility = facilityService.findById(id);
        if (facility == null) {
            return failed("设施不存在！", ObjEnvelop.class);
        }
        return success(facility);
    }

    @ApiOperation(value = "获取设施")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.Facilities.GET_FACILITIES_BY_FIELD)
    public ListEnvelop<Facility> getFacilitiesByfield(
            @ApiParam(name = "field", value = "查找字段名", required = true)
            @RequestParam(value = "field") String field,
            @ApiParam(name = "value", value = "检索值")
            @RequestParam(value = "value") String value) throws Exception {
        List<Facility> facilityList = facilityService.findByField(field, value);
        return success(facilityList);
    }

    @ApiOperation(value = "更新设施")
    @PutMapping(value = HealthyHouseMapping.HealthyHouse.Facilities.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ObjEnvelop<Facility> updateFacilities(
            @ApiParam(name = "facility", value = "设施JSON结构")
            @RequestBody Facility facility) throws Exception {
        if (StringUtils.isEmpty(facility.getCode())) {
            return failed("设施编码不能为空！", ObjEnvelop.class);
        }
        if (StringUtils.isEmpty(facility.getName())) {
            return failed("设施名称不能为空！", ObjEnvelop.class);
        }
        if (!(facility.getLongitude() > 0)) {
            return failed("设施经度不能为空！", ObjEnvelop.class);
        }
        if (!(facility.getLatitude() > 0)) {
            return failed("设施纬度不能为空！", ObjEnvelop.class);
        }
        if (StringUtils.isEmpty(facility.getCategory().toString())) {
            return failed("设施类别不正确，请参考系统字典：设施类别！", ObjEnvelop.class);
        }
        facility = facilityService.save(facility);
        return success(facility);
    }

    @ApiOperation(value = "删除设施")
    @DeleteMapping(value = HealthyHouseMapping.HealthyHouse.Facilities.DELETE)
    public Envelop deleteFacilities(
            @ApiParam(name = "facilitiesId", value = "设施ID")
            @RequestParam(value = "facilitiesId") String facilitiesId) throws Exception {
        Facility facility = new Facility();
        facility.setId(facilitiesId);
        facilityService.delete(facility);
        return success("success");
    }

    @ApiOperation(value = "设施统计：设施总数/今日新增设施")
    @DeleteMapping(value = HealthyHouseMapping.HealthyHouse.Facilities.COUNT_FACILITIES)
    public ObjEnvelop<Long> countFacilities(
            @ApiParam(name = "totalCountFlag", value = "设施总数:true;今日新增设施:false",defaultValue = "true")
            @RequestParam(value = "totalCountFlag") boolean totalCountFlag) throws Exception {
        String filters="";
        long count;
        //设施总数:true
        if (totalCountFlag) {
            count= facilityService.getCount(filters);
        } else {
            //今日新增设施:false
            String todayStart = DateUtil.getStringDateShort()+" "+"00:00:00";
            String todayEnd = DateUtil.getStringDateShort()+" "+"23:59:59";
            filters= "createTime>="+todayStart+";createTime<="+todayEnd;
            count= facilityService.getCount(filters);
        }
        return success("success", count);
    }


}
