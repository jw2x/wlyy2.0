package com.yihu.jw.healthyhouse.controller.facilities;

import com.yihu.jw.healthyhouse.model.facility.FacilityServer;
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
import java.util.List;


/**
 * 设施服务管理
 * Created by zdm on 2018/9/19.
 */
@RestController
@RequestMapping(HealthyHouseMapping.api_healthyHouse_common)
@Api(value = "FacilityServer", description = "设施服务管理", tags = {"设施服务管理"})
public class FacilitiesServerController extends EnvelopRestEndpoint {

    @Autowired
    private FacilityServerService facilityServerService;

    @ApiOperation(value = "获取设施服务列表", responseContainer = "List")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.FacilitiesServer.PAGE)
    public PageEnvelop<FacilityServer> getDictionaries(
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
        return success(facilityServerList, facilityServerList.size(), page, size);
    }

    @ApiOperation(value = "创建设施服务")
    @PostMapping(value = HealthyHouseMapping.HealthyHouse.FacilitiesServer.CREATE)
    public ObjEnvelop<FacilityServer> createDictionary(
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
    public ObjEnvelop<FacilityServer> getDictionary(
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
    public ListEnvelop<FacilityServer> getDictionaryByPhoneticCode(
            @ApiParam(name = "field", value = "查找字段名", required = true)
            @RequestParam(value = "field") String field,
            @ApiParam(name = "value", value = "检索值")
            @RequestParam(value = "value") String value) throws Exception {
        List<FacilityServer> facilityServerList = facilityServerService.findByField(field, value);
        return success(facilityServerList);
    }

    @ApiOperation(value = "更新设施服务")
    @PutMapping(value = HealthyHouseMapping.HealthyHouse.FacilitiesServer.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ObjEnvelop<FacilityServer> updateDictionary(
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
    public Envelop deleteDictionary(
            @ApiParam(name = "facilitiesServerId", value = "设施服务ID")
            @RequestParam(value = "facilitiesServerId") String facilitiesServerId) throws Exception {
        FacilityServer facilityServer = new FacilityServer();
        facilityServer.setId(facilitiesServerId);
        facilityServerService.delete(facilityServer);
        return success("success");
    }


}
