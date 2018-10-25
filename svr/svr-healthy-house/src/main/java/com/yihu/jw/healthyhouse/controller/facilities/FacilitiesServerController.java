package com.yihu.jw.healthyhouse.controller.facilities;

import com.yihu.jw.healthyhouse.constant.SystemDictConstant;
import com.yihu.jw.healthyhouse.model.dict.SystemDictEntry;
import com.yihu.jw.healthyhouse.model.facility.FacilityServer;
import com.yihu.jw.healthyhouse.service.dict.SystemDictEntryService;
import com.yihu.jw.healthyhouse.service.facility.FacilityServerService;
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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 设施服务管理
 * Created by zdm on 2018/9/19.
 */
@RestController
@RequestMapping(HealthyHouseMapping.api_healthyHouse_common)
@Api(value = "FacilityServer", description = "设施服务管理", tags = {"4设施服务管理"})
public class FacilitiesServerController extends EnvelopRestEndpoint {

    @Autowired
    private FacilityServerService facilityServerService;
    @Autowired
    private SystemDictEntryService systemDictEntryService;

    @ApiOperation(value = "获取设施服务列表", responseContainer = "List")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.FacilitiesServer.PAGE)
    public PageEnvelop<FacilityServer> getFacilitiesServer(
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
        List<FacilityServer> facilityServerList = facilityServerService.search(fields, filters, sorts, page, size);
        int count = (int) facilityServerService.getCount(filters);
        return success(facilityServerList, count, page, size);
    }

    @ApiOperation(value = "创建设施服务")
    @PostMapping(value = HealthyHouseMapping.HealthyHouse.FacilitiesServer.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ObjEnvelop<FacilityServer> createFacilitiesServer(
            @ApiParam(name = "FacilityServer", value = "设施服务JSON结构")
            @RequestBody FacilityServer facilityServer) throws IOException {
        List<FacilityServer> facilityServerList = null;
        if (StringUtils.isEmpty(facilityServer.getCode())) {
            return failed("设施服务编码不能为空！", ObjEnvelop.class);
        } else {
            facilityServerList = facilityServerService.findByField("code", facilityServer.getCode());
            if (null != facilityServerList && facilityServerList.size() > 0) {
                return failed("设施服务编码已存在！", ObjEnvelop.class);
            }
        }
        if (StringUtils.isEmpty(facilityServer.getName())) {
            return failed("设施服务名称不能为空！", ObjEnvelop.class);
        } else {
            facilityServerList = facilityServerService.findByField("name", facilityServer.getName());
            if (null != facilityServerList && facilityServerList.size() > 0) {
                return failed("设施服务名称已存在！", ObjEnvelop.class);
            }
        }

        if (StringUtils.isEmpty(facilityServer.getType())) {
            return failed("设施服务类别不正确，请参考系统字典：设施服务类别！", ObjEnvelop.class);
        }
        facilityServer = facilityServerService.save(facilityServer);
        return success(facilityServer);
    }

    @ApiOperation(value = "获取设施服务")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.FacilitiesServer.GET_FACILITIESERVERS_BY_ID)
    public ObjEnvelop<FacilityServer> getFacilitiesServer(
            @ApiParam(name = "id", value = "设施服务ID", defaultValue = "")
            @RequestParam(value = "id") String id) throws Exception {
        FacilityServer facilityServer = facilityServerService.findById(id);
        if (facilityServer == null) {
            return failed("设施服务不存在！", ObjEnvelop.class);
        }
        return success(facilityServer);
    }

    @ApiOperation(value = "获取设施服务")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.FacilitiesServer.GET_FACILITIESERVERS_BY_FIELD)
    public ListEnvelop<FacilityServer> getFacilitiesServerByField(
            @ApiParam(name = "field", value = "查找字段名", required = true)
            @RequestParam(value = "field") String field,
            @ApiParam(name = "value", value = "检索值")
            @RequestParam(value = "value") String value) throws Exception {
        List<FacilityServer> facilityServerList = facilityServerService.findByField(field, value);
        return success(facilityServerList);
    }

    @ApiOperation(value = "更新设施服务")
    @PutMapping(value = HealthyHouseMapping.HealthyHouse.FacilitiesServer.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ObjEnvelop<FacilityServer> updateFacilitiesServer(
            @ApiParam(name = "FacilityServer", value = "设施服务JSON结构")
            @RequestBody FacilityServer facilityServer) throws Exception {
        if (StringUtils.isEmpty(facilityServer.getCode())) {
            return failed("设施服务编码不能为空！", ObjEnvelop.class);
        }
        if (StringUtils.isEmpty(facilityServer.getName())) {
            return failed("设施服务名称不能为空！", ObjEnvelop.class);
        }

        if (StringUtils.isEmpty(facilityServer.getType())) {
            return failed("设施服务类别不正确，请参考系统字典：设施服务类别！", ObjEnvelop.class);
        }
        facilityServer = facilityServerService.save(facilityServer);
        return success(facilityServer);
    }

    @ApiOperation(value = "删除设施服务")
    @DeleteMapping(value = HealthyHouseMapping.HealthyHouse.FacilitiesServer.DELETE)
    public Envelop deleteFacilitiesServer(
            @ApiParam(name = "facilitiesServerId", value = "设施服务ID")
            @RequestParam(value = "facilitiesServerId") String facilitiesServerId) throws Exception {
        FacilityServer facilityServer = new FacilityServer();
        facilityServer.setId(facilitiesServerId);
        facilityServerService.delete(facilityServer);
        return success("success");
    }

    @ApiOperation(value = "获取设施服务列表", responseContainer = "List")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.FacilitiesServer.LIST_FACILITIESERVERS)
    public ListEnvelop<FacilityServer> getFacilitiesServer(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器", defaultValue = "")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序", defaultValue = "")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        List<FacilityServer> facilityServerList = facilityServerService.search(fields, filters, sorts);
        return success(facilityServerList);
    }

    @ApiOperation(value = "app按照分类获取-设施服务列表", responseContainer = "List")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.FacilitiesServer.LIST_FACILITIESERVERS_BY_TYPE)
    public ListEnvelop<Map> getFacilitiesServerByType() throws Exception {
        List<Map> mapList=new ArrayList<>();
        Map<String, Object> map ;
        List<FacilityServer> facilityServerList;
        //获取系统字典-设施服务类型
        List<SystemDictEntry> systemDictEntryList = systemDictEntryService.getDictEntryCodeAndValueByDictIdAndPcode(SystemDictConstant.FACILITIE_SERVERS_TYPE_DICT_ID);
        for (Object object : systemDictEntryList) {
            Object[] obj=(Object[])object;
            if(null!=obj[0]&&StringUtils.isNotEmpty(obj[0].toString()) ){
                map = new HashMap<>();
                String filters = "type=" + obj[0].toString();
                facilityServerList = facilityServerService.search("", filters, "");
                map.put("name",null==obj[1]?"":obj[1].toString());
                map.put(obj[0].toString(), facilityServerList);
                mapList.add(map);
            }

        }
        return success("success",mapList);
    }


}
