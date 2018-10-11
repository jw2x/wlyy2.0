package com.yihu.jw.healthyhouse.controller.user;


import com.google.common.base.Joiner;
import com.yihu.jw.healthyhouse.model.facility.Facility;
import com.yihu.jw.exception.business.ManageException;
import com.yihu.jw.healthyhouse.model.facility.FacilityServerRelation;
import com.yihu.jw.healthyhouse.model.user.FacilityUsedRecord;
import com.yihu.jw.healthyhouse.model.user.NavigationServiceEvaluation;
import com.yihu.jw.healthyhouse.service.facility.FacilityServerRelationService;
import com.yihu.jw.healthyhouse.service.facility.FacilityService;
import com.yihu.jw.healthyhouse.service.user.FacilityUsedRecordService;
import com.yihu.jw.healthyhouse.service.user.NavigationServiceEvaluationService;
import com.yihu.jw.healthyhouse.service.user.UserService;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 用户使用导航记录
 * Created by zdm on 2018/9/21.
 */
@RestController
@RequestMapping(HealthyHouseMapping.api_healthyHouse_common)
@Api(value = "FacilityUsedRecord", description = "用户使用导航记录管理", tags = {"5用户使用导航记录管理"})
public class FacilityUsedRecordController extends EnvelopRestEndpoint {

    @Autowired
    private FacilityUsedRecordService facilityUsedRecordService;
    @Autowired
    private UserService userService;
    @Autowired
    private FacilityService facilityService;
    @Autowired
    private NavigationServiceEvaluationService navigationServiceEvaluationService;
    @Autowired
    private FacilityServerRelationService facilityServerRelationService;

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
        int count = (int) facilityUsedRecordService.getCount(filters);
        return success(facilityUsedRecordList, count, page, size);
    }

    @ApiOperation(value = "创建/更新（id存在）用户使用导航记录")
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = HealthyHouseMapping.HealthyHouse.FacilityUsedRecord.CREATE)
    public ObjEnvelop<FacilityUsedRecord> createFacilityUsedRecord(
            @ApiParam(name = "facilityUsedRecord", value = "用户使用导航记录JSON结构")
            @RequestBody FacilityUsedRecord facilityUsedRecord) throws IOException, ManageException {
        facilityUsedRecord.setUserId(facilityUsedRecord.getCreateUser());
        facilityUsedRecord = facilityUsedRecordService.save(facilityUsedRecord);
        userService.updateFacilityUse(facilityUsedRecord.getCreateUser());
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

    @ApiOperation(value = "获取用户查找历史导航记录，及所有设施包含设施使用次数统计")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.FacilityUsedRecord.GET_FACILITY_USED_RECORD_AND_COUNT_BY_ID)
    public ListEnvelop<FacilityUsedRecord> getFacilityUsedRecordAndCountById(
            @ApiParam(name = "userId", value = "用户ID", defaultValue = "")
            @RequestParam(value = "userId", required = false) String userId,
            @ApiParam(name = "filters", value = "检索字段", defaultValue = "")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "nearbyFlag", value = "是否为“附近”的功能", defaultValue = "false")
            @RequestParam(value = "nearbyFlag") boolean nearbyFlag) throws Exception {
        List<FacilityUsedRecord> facilityUsedRecordList = new ArrayList<>();
        FacilityUsedRecord facilityUsedRecord;
        if (StringUtils.isNotEmpty(userId)) {
            if (nearbyFlag) {
                if (StringUtils.isNotEmpty(filters)) {
                    filters = "deleteFlag=0;status=0;" + "name?" + filters + " g1;cityName?" + filters + " g1;countyName?" + filters + " g1;street?" + filters + " g1;address?" + filters + " g1;";
                } else {
                    filters = "deleteFlag=0;status=0;";
                }
                //获取所有设施，并根据设施编码及用户id查找使用次数
                List<Facility> facilityList = facilityService.search(filters);
                for (Facility facility : facilityList) {
                    facilityUsedRecord = new FacilityUsedRecord();
                    facilityUsedRecord.setFacilitieCode(facility.getCode());
                    facilityUsedRecord.setFacilitieName(facility.getName());
                    facilityUsedRecord.setFacilitieLongitude(facility.getLongitude());
                    facilityUsedRecord.setFacilitieLatitudes(facility.getLatitude());
                    facilityUsedRecord.setFacilitieAddr(facility.getAddress());
                    facilityUsedRecord.setCreateUser(userId);
                    facilityUsedRecord.setUserId(userId);
                    facilityUsedRecord.setFacilitieId(facility.getId());
                    facilityUsedRecord.setFacilitieStatus(facility.getStatus());
                    long count = facilityUsedRecordService.countByFacilitieCodeAndUserId(facility.getCode(), userId);
                    facilityUsedRecord.setNum((int) count);
                    facilityUsedRecordList.add(facilityUsedRecord);
                }
            } else {
                //根据用户id,获取我的历史记录
                facilityUsedRecordList = facilityUsedRecordService.countDistinctByFacilitieCodeAndUserId(userId);
                for (FacilityUsedRecord facilityUsedRecord1 : facilityUsedRecordList) {
                    long count = facilityUsedRecordService.countByFacilitieCodeAndUserId(facilityUsedRecord1.getFacilitieCode(), userId);
                    facilityUsedRecord1.setNum((int) count);
                    //获取设施状态
                    Facility facility = facilityService.findByCode(facilityUsedRecord1.getFacilitieCode());
                    facilityUsedRecord1.setFacilitieStatus(facility.getStatus());
                }
            }
        }

        return success(facilityUsedRecordList);
    }

    @ApiOperation(value = "app-用户使用设施次数", responseContainer = "List")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.FacilityUsedRecord.COUNT_FACILITY_USED_RECORD_BY_USERID)
    public ObjEnvelop<Long> getFacilityUsedRecords(
            @ApiParam(name = "userId", value = "登录用户id", defaultValue = "")
            @RequestParam(value = "userId", required = false) String userId,
            @ApiParam(name = "facilitieCode", value = "设施id", defaultValue = "")
            @RequestParam(value = "facilitieCode", required = false) String facilitieCode) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        long count=0;
        if (StringUtils.isNotEmpty(facilitieCode)) {
            stringBuffer.append("facilitieCode=" + facilitieCode);
        }
        if (StringUtils.isNotEmpty(userId)) {
            stringBuffer.append("createUser=" + userId + ";");
            String filters = stringBuffer.toString();
            count = facilityUsedRecordService.getCount(filters);
        }
        return success(count);
    }


    @ApiOperation(value = "app-设施使用记录-详情页", responseContainer = "List")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.FacilityUsedRecord.GET_FACILITY_USED_RECORD_DETAIL)
    public ObjEnvelop facilityUsedRecordDetail(
            @ApiParam(name = "id", value = "使用记录ID", defaultValue = "")
            @RequestParam(value = "id") String id) throws Exception {
        if (id == null) {
            throw new ManageException("使用记录ID为空！");
        }
        Map<String, Object> usedRecordDetail = facilityUsedRecordService.getUsedRecordDetail(id);
        return success(usedRecordDetail);
    }

    @ApiOperation(value = "我的行程-获取用户使用导航记录列表--分页（app）", responseContainer = "List")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.FacilityUsedRecord.PAGE_FACILITY_USED_RECORD_BY_USERID)
    public PageEnvelop<FacilityUsedRecord> getFacilityUsedRecordsByUserId(
            @ApiParam(name = "userId", value = "必输参数：登录用户id", defaultValue = "")
            @RequestParam(value = "userId") String userId,
            @ApiParam(name = "sorts", value = "排序", defaultValue = "-createTime")
            @RequestParam(value = "sorts", required = false) String sorts,
            @ApiParam(name = "size", value = "分页大小", defaultValue = "15")
            @RequestParam(value = "size", required = false) Integer size,
            @ApiParam(name = "page", value = "页码", defaultValue = "1")
            @RequestParam(value = "page", required = false) Integer page) throws Exception {
        String filters = "createUser=" + userId;
        sorts = "-createTime";
        List<FacilityUsedRecord> facilityUsedRecordList = facilityUsedRecordService.search("", filters, sorts, page, size);
        for (FacilityUsedRecord record : facilityUsedRecordList) {
            //根据设施编码获取关联服务的名称
            String facilityCode = record.getFacilitieCode();
            List<FacilityServerRelation> facilityServerRelations = facilityServerRelationService.findByField("facilitieCode", facilityCode);
            String servicesValue = facilityServerRelations.stream().map(FacilityServerRelation::getServiceName).collect(Collectors.joining("、"));
            record.setFacilityRelationServiceName(servicesValue);
            //根据记录获取评价记录
            NavigationServiceEvaluation comment = navigationServiceEvaluationService.findByUseRecordId(record.getId());
            if (comment == null) {
                record.setNavigationServiceEvaluationFlag("未评价");
            } else {
                record.setNavigationServiceEvaluationFlag("已评价");
            }
            //根据设施编码获取 设施状态
            Facility facility = facilityService.findByCode(facilityCode);
            if (facility!=null) {
                record.setFacilitieStatus(facility.getStatus());
            }
        }
        int count = (int) facilityUsedRecordService.getCount(filters);
        return success(facilityUsedRecordList, count, page, size);
    }

}
