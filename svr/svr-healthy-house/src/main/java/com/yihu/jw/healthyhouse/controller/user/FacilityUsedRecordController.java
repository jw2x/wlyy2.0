package com.yihu.jw.healthyhouse.controller.user;


import com.yihu.jw.healthyhouse.model.user.FacilityUsedRecord;
import com.yihu.jw.healthyhouse.service.user.FacilityUsedRecordService;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.ListEnvelop;
import com.yihu.jw.restmodel.web.ObjEnvelop;
import com.yihu.jw.restmodel.web.PageEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.health.house.HealthyHouseMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


/**
 * 用户使用导航记录
 * Created by zdm on 2018/9/21.
 */
@RestController
@RequestMapping(HealthyHouseMapping.api_healthyHouse_common)
@Api(value = "FacilityUsedRecord", description = "用户使用导航记录管理", tags = {"用户使用导航记录管理"})
public class FacilityUsedRecordController extends EnvelopRestEndpoint {

    @Autowired
    private FacilityUsedRecordService facilityUsedRecordService;

    @ApiOperation(value = "获取用户使用导航记录列表--分页（web）", responseContainer = "List")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.FacilityUsedRecord.PAGE)
    public PageEnvelop<FacilityUsedRecord> getFacilityUsedRecords(
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
        List<FacilityUsedRecord> facilityUsedRecordList = facilityUsedRecordService.search(fields, filters, sorts, page, size);
        return success(facilityUsedRecordList, (null == facilityUsedRecordList) ? 0 : facilityUsedRecordList.size(), page, size);
    }

    @ApiOperation(value = "创建/更新（id存在）用户使用导航记录")
    @PostMapping(value = HealthyHouseMapping.HealthyHouse.FacilityUsedRecord.CREATE)
    public ObjEnvelop<FacilityUsedRecord> createFacilityUsedRecord(
            @ApiParam(name = "facilityUsedRecord", value = "用户使用导航记录JSON结构")
            @RequestBody FacilityUsedRecord facilityUsedRecord) throws IOException {
        facilityUsedRecord = facilityUsedRecordService.save(facilityUsedRecord);
        return success(facilityUsedRecord);
    }

    @ApiOperation(value = "获取记录id查找导航记录")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.FacilityUsedRecord.GET_FACILITY_USED_RECORD_BY_ID)
    public ObjEnvelop<FacilityUsedRecord> getFacilityUsedRecord(
            @ApiParam(name = "id", value = "用户使用导航记录ID", defaultValue = "")
            @RequestParam(value = "id") String id) throws Exception {
        FacilityUsedRecord facilityUsedRecord = facilityUsedRecordService.findById(id);
        if (facilityUsedRecord == null) {
            return failed("用户使用导航记录不存在！", ObjEnvelop.class);
        }
        return success(facilityUsedRecord);
    }

    @ApiOperation(value = "获取用户使用导航记录:根据日期，根据反馈人等")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.FacilityUsedRecord.GET_FACILITY_USED_RECORD_BY_FIELD)
    public ListEnvelop<FacilityUsedRecord> getFacilityUsedRecordByField(
            @ApiParam(name = "field", value = "查找字段名", required = true)
            @RequestParam(value = "field") String field,
            @ApiParam(name = "value", value = "检索值")
            @RequestParam(value = "value") String value) throws Exception {
        List<FacilityUsedRecord> facilityUsedRecordList = facilityUsedRecordService.findByField(field, value);
        return success(facilityUsedRecordList);
    }

    @ApiOperation(value = "删除用户使用导航记录")
    @DeleteMapping(value = HealthyHouseMapping.HealthyHouse.FacilityUsedRecord.DELETE)
    public Envelop deleteFacilityUsedRecord(
            @ApiParam(name = "facilityUsedRecordId", value = "用户使用导航记录ID")
            @RequestParam(value = "facilityUsedRecordId") String facilityUsedRecordId) throws Exception {
        FacilityUsedRecord facilityUsedRecord = facilityUsedRecordService.findById(facilityUsedRecordId);
        facilityUsedRecordService.delete(facilityUsedRecord);
        return success("success");
    }

    @ApiOperation(value = "获取用户查找导航记录，包含设施使用次数统计")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.FacilityUsedRecord.GET_FACILITY_USED_RECORD_AND_COUNT_BY_ID)
    public PageEnvelop<FacilityUsedRecord> getFacilityUsedRecordAndCountById(
            @ApiParam(name = "userId", value = "用户ID", defaultValue = "")
            @RequestParam(value = "userId") String userId,
            @ApiParam(name = "size", value = "分页大小", defaultValue = "15")
            @RequestParam(value = "size", required = false) Integer size,
            @ApiParam(name = "page", value = "页码", defaultValue = "1")
            @RequestParam(value = "page", required = false) Integer page) throws Exception {
        //根据用户id
        List<FacilityUsedRecord> facilityUsedRecordList = facilityUsedRecordService.countDistinctByFacilitieCodeAndUserId(userId,page,size);
        for(FacilityUsedRecord facilityUsedRecord1:facilityUsedRecordList){
         long count=   facilityUsedRecordService.countByFacilitieCodeAndUserId(facilityUsedRecord1.getFacilitieCode(),userId);
            facilityUsedRecord1.setNum((int)count);
        }
        return success(facilityUsedRecordList, (null == facilityUsedRecordList) ? 0 : facilityUsedRecordList.size(), page, size);
    }


}
