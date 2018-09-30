package com.yihu.jw.healthyhouse.controller.facilities;

import com.yihu.jw.exception.business.ManageException;
import com.yihu.jw.healthyhouse.model.facility.Facility;
import com.yihu.jw.healthyhouse.model.facility.FacilityServer;
import com.yihu.jw.healthyhouse.model.facility.FacilityServerRelation;
import com.yihu.jw.healthyhouse.service.facility.FacilityServerRelationService;
import com.yihu.jw.healthyhouse.service.facility.FacilityServerService;
import com.yihu.jw.healthyhouse.service.facility.FacilityService;
import com.yihu.jw.healthyhouse.util.facility.FacilityMsgReader;
import com.yihu.jw.healthyhouse.util.facility.msg.FacilityMsg;
import com.yihu.jw.healthyhouse.util.poi.AExcelReader;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.ObjEnvelop;
import com.yihu.jw.restmodel.web.PageEnvelop;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 设施管理
 * Created by zdm on 2018/9/19.
 */
@RestController
@RequestMapping(HealthyHouseMapping.api_healthyHouse_common)
@Api(value = "Facility", description = "设施管理", tags = {"3设施管理"})
public class FacilitiesController extends EnvelopRestEndpoint {

    @Autowired
    private FacilityService facilityService;
    @Autowired
    private FacilityServerService facilityServerService;
    @Autowired
    private FacilityServerRelationService facilityServerRelationService;

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
        int count = (int) facilityService.getCount(filters);
        return success(facilityList, count, page, size);
    }

    @ApiOperation(value = "创建设施，包含设施与服务的关联关系")
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = HealthyHouseMapping.HealthyHouse.Facilities.CREATE)
    public ObjEnvelop<Facility> createFacilities(
            @ApiParam(name = "facility", value = "设施JSON结构")
            @RequestParam(value = "facility") String facility,
            @ApiParam(name = "facilityServerJson", value = "设施关联的服务字符串用,号隔开")
            @RequestParam(value = "facilityServerJson") String facilityServerJson) throws IOException {
        Facility facility1 = toEntity(facility, Facility.class);
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
        Facility facilityBack = facilityService.save(facility1);
        List<FacilityServerRelation> facilityServerRelationList = createRelationByServerCode(facility1, facilityServerJson);
        facilityBack.setFacilityServerRelation(facilityServerRelationList);
        return success(facilityBack);
    }

    @ApiOperation(value = "更新（id存在）设施，包含设施与服务的关联关系")
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = HealthyHouseMapping.HealthyHouse.Facilities.UPDATE_FACILITIES_AND_RELATION)
    public ObjEnvelop<Facility> updateFacilitieAndRelations(
            @ApiParam(name = "facility", value = "设施JSON结构")
            @RequestParam(value = "facility") String facility,
            @ApiParam(name = "facilityServerJson", value = "设施关联的服务字符串用,号隔开")
            @RequestParam(value = "facilityServerJson") String facilityServerJson) throws Exception {
        Facility facility1 = toEntity(facility, Facility.class);
        List<Facility> facilityList = null;
        List<Facility> faList = facilityService.findByField("id", facility1.getId());
        if (!(faList != null && faList.size() > 0)) {
            return failed("设施不存在！", ObjEnvelop.class);
        }
        if (StringUtils.isNotEmpty(facility1.getId())) {
            //删除设施，设施关联关系，设施服务使用设施数
            deleteFacilityByCode(facility1);
        }
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
        Facility facilityBack = facilityService.save(facility1);
        List<FacilityServerRelation> facilityServerRelationList = createRelationByServerCode(facility1, facilityServerJson);
        facilityBack.setFacilityServerRelation(facilityServerRelationList);
        return success(facilityBack);
    }


    @ApiOperation(value = "获取设施")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.Facilities.GET_FACILITIES_BY_ID)
    public ObjEnvelop<Facility> getFacilitie(
            @ApiParam(name = "id", value = "设施ID", defaultValue = "")
            @RequestParam(value = "id") String id) throws Exception {
        Facility facility = facilityService.findById(id);
        List<FacilityServerRelation> facilityServerRelationList = facilityServerRelationService.findByField("facilitieCode", facility.getCode());
        if (facility == null) {
            return failed("设施不存在！", ObjEnvelop.class);
        }
        facility.setFacilityServerRelation(facilityServerRelationList);
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
        for (Facility facility : facilityList) {
            List<FacilityServerRelation> facilityServerRelationList = facilityServerRelationService.findByField("facilitieCode", facility.getCode());
            facility.setFacilityServerRelation(facilityServerRelationList);
        }
        return success(facilityList);
    }

    @ApiOperation(value = "新增/更新（idy已存在）设施")
    @PostMapping(value = HealthyHouseMapping.HealthyHouse.Facilities.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ObjEnvelop<Facility> updateFacilities(
            @ApiParam(name = "facility", value = "设施JSON结构（不包括设施与服务关联关系）")
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
        Facility facility = facilityService.findById(facilitiesId);
        if (null == facility) {
            return failed("设施不存在！");
        }
        deleteFacilityByCode(facility);
        return success("success");
    }

    @ApiOperation(value = "设施统计：设施总数/今日新增设施")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.Facilities.COUNT_FACILITIES)
    public ObjEnvelop<Long> countFacilities(
            @ApiParam(name = "totalCountFlag", value = "设施总数:true;今日新增设施:false", defaultValue = "true")
            @RequestParam(value = "totalCountFlag") boolean totalCountFlag) throws Exception {
        String filters = "";
        long count;
        //设施总数:true
        if (totalCountFlag) {
            count = facilityService.getCount(filters);
        } else {
            //今日新增设施:false
            String todayStart = DateUtil.getStringDateShort() + " " + "00:00:00";
            String todayEnd = DateUtil.getStringDateShort() + " " + "23:59:59";
            filters = "createTime>=" + todayStart + ";createTime<=" + todayEnd;
            count = facilityService.getCount(filters);
        }
        return success("success", count);
    }

    @ApiOperation(value = "变更设施状态")
    @PostMapping(value = HealthyHouseMapping.HealthyHouse.Facilities.UPDATE_FACILITIE_STATE)
    public ObjEnvelop<Facility> updateFacilitieState(
            @ApiParam(name = "facilitiesId", value = "设施id", required = true)
            @RequestParam(value = "facilitiesId") String facilitiesId,
            @ApiParam(name = "state", value = "状态：0开放，1关闭，2损坏，3维修")
            @RequestParam(value = "state") String state) throws Exception {
        Facility facility = facilityService.findById(facilitiesId);
        facility.setStatus(state);
        facility = facilityService.save(facility);
        return success(facility);
    }

    @ApiOperation(value = "今日使用设施数统计")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.Facilities.COUNT_FACILITIES_BY_TIME)
    public ObjEnvelop<Long> countFacilitiesByTime() throws Exception {
        long count = facilityServerRelationService.countDistinctByFacilitieCodeAndCreateTimeBetween();
        return success("success", count);
    }


    @GetMapping("/exportToExcel")
    @ApiOperation(value = "设施列表导出excel")
    public void exportToExcel(
            HttpServletResponse response,
            @ApiParam(name = "filters", value = "过滤条件", required = false) @RequestParam(required = false, name = "filters") String filters,
            @ApiParam(name = "sorts", value = "排序", required = false) @RequestParam(required = false, name = "sorts") String sorts) throws ManageException {
        //获取设施数据
        List<Facility> facilityList = null;
        try {
            facilityList = facilityService.search(filters, sorts);
        } catch (ParseException e) {
            throw new ManageException("获取设施列表异常", e);
        }
        facilityService.exportFacilityExcel(response, facilityList);
    }


    @PostMapping(value = "/batchImport")
    @ApiOperation(value = "设施列表导入（经纬度重复的不导入）")
    public Envelop importData(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestPart(value = "file") MultipartFile file,
            HttpServletRequest request) throws IOException, ManageException {
        try {
            request.setCharacterEncoding("UTF-8");
            AExcelReader excelReader = new FacilityMsgReader();
            excelReader.read(file);
            List<FacilityMsg> dataList = excelReader.getCorrectLs();
            if (dataList.size() > 0) {
                Map<String, Object> result = facilityService.batchInsertFacility(dataList);
                return success("导入成功!", result);
            }

        } catch (Exception e) {
            throw new ManageException("导入设施列表异常！", e);
        }
        return failed("导入失败");
    }

    @ApiOperation(value = "获取设施列表--不分页(app)", responseContainer = "List")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.Facilities.GET_FACILITIELIST)
    public ListEnvelop<Facility> getFacilitieLists(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器", defaultValue = "")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序", defaultValue = "")
            @RequestParam(value = "sorts", required = false) String sorts,
            @ApiParam(name = "facilityCategory", value = "设施分类：1小屋、2步道、3餐厅", defaultValue = "1")
            @RequestParam(value = "facilityCategory", required = false) String facilityCategory,
            @ApiParam(name = "facilityServerType", value = "非必传参数：设施服务类型：dinner吃饭、measure测量、sports运动", defaultValue = "measure")
            @RequestParam(value = "facilityServerType", required = false) String facilityServerType,
            @ApiParam(name = "facilityServerCodes", value = "非必传参数：设施服务编码，可多个，用逗号隔开", defaultValue = "jkxwServer003,HFHS7C5B5")
            @RequestParam(value = "facilityServerCodes", required = false) String facilityServerCodes) throws Exception {
        List<Facility> facilityList = new ArrayList<>();
        //设施服务编码存在，查找使用该服务的设施
        if (StringUtils.isNotEmpty(facilityServerCodes)) {
            String[] faServerCodes = facilityServerCodes.split(",");
            List<String> facilityCodeList = facilityService.getFacilityCodeByServerCode(faServerCodes);
            facilityList = facilityService.getFacilityByFacilityCode(facilityCodeList);
        } else if (StringUtils.isNotEmpty(facilityServerType)) {
            //设施编码为空，设施服务类型不为空，按设施服务类型获取设施
            List<String> facilityCodeList = facilityService.getFacilityCodeByServerType(facilityServerType);
            facilityList = facilityService.getFacilityByFacilityCode(facilityCodeList);
        } else if (StringUtils.isNotEmpty(facilityCategory)) {
            //设施编码为空，设施服务类型为空，按照设施分类获取按设施服务类型获取设施
            filters = "category=" + facilityCategory + ";status=0;";
            facilityList = facilityService.search(fields, filters, sorts);
        } else {
            facilityList = facilityService.search(fields, filters, sorts);
        }
        return success(facilityList);
    }


    @GetMapping(value = HealthyHouseMapping.HealthyHouse.Facilities.GET_ALL_FACILITIELISTS_COUNT)
    @ApiOperation(value = "设施统计-三个统计值")
    public ObjEnvelop<Map> usedFacilityCount() throws Exception {
        Map<String, Long> map = new HashMap<>();
        //今日使用设施数
        long countUsedFacilitieToday = facilityServerRelationService.countDistinctByFacilitieCodeAndCreateTimeBetween();
        map.put("countUsedFacilitieToday", countUsedFacilitieToday);
        long countAllFacilitie = facilityService.getCount("");
        map.put("countAllFacilitie", countAllFacilitie);
        //今日新增设施:false
        String todayStart = DateUtil.getStringDateShort() + " " + "00:00:00";
        String todayEnd = DateUtil.getStringDateShort() + " " + "23:59:59";
        String filters = "createTime>=" + todayStart + ";createTime<=" + todayEnd;
        long countCreatedFacilitieToday = facilityService.getCount(filters);
        map.put("countCreatedFacilitieToday", countCreatedFacilitieToday);
        return success("获取成功", map);
    }


//    @ApiOperation(value = "搜索附近的小屋", responseContainer = "List")
//    @GetMapping(value = HealthyHouseMapping.HealthyHouse.Facilities.NEARBY_FACILITY)
//    public PageEnvelop<Facility> nearbyFacility(
//            @ApiParam(name = "lng", value = "当前经度", defaultValue = "")
//            @RequestParam(value = "lng", required = false) String lng,
//            @ApiParam(name = "lat", value = "当前纬度", defaultValue = "")
//            @RequestParam(value = "lat", required = false) String lat,
//            @ApiParam(name = "page", value = "页码", defaultValue = "1")
//            @RequestParam(value = "page", required = false) Integer page,
//            @ApiParam(name = "size", value = "分页大小", defaultValue = "15")
//            @RequestParam(value = "size", required = false) Integer size) throws Exception {
//        List<Facility> facilityList = facilityService.search(fields, filters, sorts, page, size);
//        return success(facilityList, (null == facilityList) ? 0 : facilityList.size(), page, size);
//    }


    /**
     * 根据设施，删除设施关联服务，变更设施服务关联设施数，删除设施
     *
     * @param facility 设施
     * @throws Exception
     */
    private void deleteFacilityByCode(Facility facility) throws Exception {
        List<FacilityServerRelation> facilityServerRelationList = facilityServerRelationService.findByField("facilitieCode", facility.getCode());
        //通过设施与服务关系，变更设施服务使用数量
        for (FacilityServerRelation facilityServerRelation : facilityServerRelationList) {
            List<FacilityServer> facilityServiceList = facilityServerService.findByField("code", facilityServerRelation.getServiceCode());
            for (FacilityServer facilityServer : facilityServiceList) {
                Integer num = (null == facilityServer.getNum() ? 0 : (Integer.valueOf(facilityServer.getNum()) - 1));
                facilityServer.setNum(num.toString());
                facilityServerService.save(facilityServer);
            }
        }
        facilityServerRelationService.deleteByFacilitieCode(facility.getCode());
        facilityService.delete(facility);
    }

    /**
     * 根据设施及服务编码管理关联关系
     *
     * @param facility1          设施
     * @param facilityServerJson 设施服务编码
     * @return
     */
    public List<FacilityServerRelation> createRelationByServerCode(Facility facility1, String facilityServerJson) {
        List<FacilityServerRelation> list = new ArrayList<>();
        if (StringUtils.isNotEmpty(facilityServerJson)) {
            //设施编码
            String[] fs = facilityServerJson.split(",");
            FacilityServerRelation facilityServerRelation;
            for (String code : fs) {
                if (StringUtils.isNotEmpty(code)) {
                    List<FacilityServer> facilityServerList = facilityServerService.findByField("code", code);
                    for (FacilityServer facilityServer : facilityServerList) {
                        facilityServerRelation = new FacilityServerRelation();
                        facilityServerRelation.setFacilitieCode(facility1.getCode());
                        facilityServerRelation.setFacilitieName(facility1.getName());
                        facilityServerRelation.setServiceCode(facilityServer.getCode());
                        facilityServerRelation.setServiceName(facilityServer.getName());
                        facilityServerRelation.setCreateUser(facility1.getCreateUser());
                        facilityServerRelation.setCreateUserName(facility1.getCreateUserName());
                        //追加设施与服务的关联关系
                        facilityServerRelationService.save(facilityServerRelation);
                        //添加设施的时候，追加改服务的使用设施数量。
                        Integer num = (null == facilityServer.getNum() ? 0 + 1 : (Integer.valueOf(facilityServer.getNum()) + 1));
                        facilityServer.setNum(num.toString());
                        facilityServerService.save(facilityServer);
                        list.add(facilityServerRelation);
                    }
                }
            }
        }
        return list;
    }


}
