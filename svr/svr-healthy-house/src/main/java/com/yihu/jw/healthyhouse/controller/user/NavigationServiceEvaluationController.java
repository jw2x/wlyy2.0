package com.yihu.jw.healthyhouse.controller.user;


import com.yihu.jw.healthyhouse.model.user.NavigationServiceEvaluation;
import com.yihu.jw.healthyhouse.service.user.NavigationServiceEvaluationService;
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
 * 服务评价
 * Created by zdm on 2018/9/21.
 */
@RestController
@RequestMapping(HealthyHouseMapping.api_healthyHouse_common)
@Api(value = "NavigationServiceEvaluation", description = "服务评价管理", tags = {"服务评价管理"})
public class NavigationServiceEvaluationController extends EnvelopRestEndpoint {

    @Autowired
    private NavigationServiceEvaluationService navigationServiceEvaluationService;

    @ApiOperation(value = "获取服务评价列表", responseContainer = "List")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.NavigationServiceEvaluation.PAGE)
    public PageEnvelop<NavigationServiceEvaluation> getNavigationServiceEvaluations(
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
        List<NavigationServiceEvaluation> navigationServiceEvaluationList = navigationServiceEvaluationService.search(fields, filters, sorts, page, size);
        return success(navigationServiceEvaluationList, (null == navigationServiceEvaluationList) ? 0 : navigationServiceEvaluationList.size(), page, size);
    }

    @ApiOperation(value = "创建/更新（id存在）服务评价")
    @PostMapping(value = HealthyHouseMapping.HealthyHouse.NavigationServiceEvaluation.CREATE)
    public ObjEnvelop<NavigationServiceEvaluation> createNavigationServiceEvaluation(
            @ApiParam(name = "NavigationServiceEvaluation", value = "服务评价JSON结构")
            @RequestBody NavigationServiceEvaluation navigationServiceEvaluation) throws IOException {
        navigationServiceEvaluation = navigationServiceEvaluationService.save(navigationServiceEvaluation);
        return success(navigationServiceEvaluation);
    }

    @ApiOperation(value = "获取服务评价")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.NavigationServiceEvaluation.GET_NAVIGATION_SERVICE_EVALUATION_BY_ID)
    public ObjEnvelop<NavigationServiceEvaluation> getNavigationServiceEvaluation(
            @ApiParam(name = "id", value = "服务评价ID", defaultValue = "")
            @RequestParam(value = "id") String id) throws Exception {
        NavigationServiceEvaluation navigationServiceEvaluation = navigationServiceEvaluationService.findById(id);
        if (navigationServiceEvaluation == null) {
            return failed("服务评价不存在！", ObjEnvelop.class);
        }
        return success(navigationServiceEvaluation);
    }

    @ApiOperation(value = "获取服务评价:根据日期，根据评价人等")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.NavigationServiceEvaluation.GET_NAVIGATION_SERVICE_EVALUATION_BY_FIELD)
    public ListEnvelop<NavigationServiceEvaluation> getNavigationServiceEvaluationByField(
            @ApiParam(name = "field", value = "查找字段名", required = true)
            @RequestParam(value = "field") String field,
            @ApiParam(name = "value", value = "检索值")
            @RequestParam(value = "value") String value) throws Exception {
        List<NavigationServiceEvaluation> navigationServiceEvaluationList = navigationServiceEvaluationService.findByField(field, value);
        return success(navigationServiceEvaluationList);
    }

    @ApiOperation(value = "删除服务评价")
    @DeleteMapping(value = HealthyHouseMapping.HealthyHouse.NavigationServiceEvaluation.DELETE)
    public Envelop deleteNavigationServiceEvaluation(
            @ApiParam(name = "facilitiesServerId", value = "服务评价ID")
            @RequestParam(value = "facilitiesServerId") String facilitiesServerId) throws Exception {
        NavigationServiceEvaluation navigationServiceEvaluation = navigationServiceEvaluationService.findById(facilitiesServerId);
        navigationServiceEvaluationService.delete(navigationServiceEvaluation);
        return success("success");
    }

}
