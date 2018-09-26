package com.yihu.jw.healthyhouse.controller.user;


import com.yihu.jw.healthyhouse.model.user.Appeal;
import com.yihu.jw.healthyhouse.service.user.AppealService;
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
 * 账号申诉
 * Created by zdm on 2018/9/26.
 */
@RestController
@RequestMapping(HealthyHouseMapping.api_healthyHouse_common)
@Api(value = "Appeal", description = "账号申诉管理", tags = {"账号申诉管理"})
public class AppealController extends EnvelopRestEndpoint {

    @Autowired
    private AppealService AppealService;

    @ApiOperation(value = "获取账号申诉列表", responseContainer = "List")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.Appeal.PAGE)
    public PageEnvelop<Appeal> getAppeals(
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
        List<Appeal> appealList = AppealService.search(fields, filters, sorts, page, size);
        return success(appealList, (null == appealList) ? 0 : appealList.size(), page, size);
    }

    @ApiOperation(value = "创建/更新（id存在）账号申诉")
    @PostMapping(value = HealthyHouseMapping.HealthyHouse.Appeal.CREATE)
    public ObjEnvelop<Appeal> createAppeal(
            @ApiParam(name = "Appeal", value = "账号申诉JSON结构")
            @RequestBody Appeal appeal) throws IOException {
        if(StringUtils.isEmpty(appeal.getCreateUser())){
            return failed("账号申诉人（createUser）不能为空！",ObjEnvelop.class);
        }
        appeal = AppealService.save(appeal);
        return success(appeal);
    }

    @ApiOperation(value = "获取账号申诉")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.Appeal.GET_APPEAL_BY_ID)
    public ObjEnvelop<Appeal> getAppeal(
            @ApiParam(name = "id", value = "账号申诉ID", defaultValue = "")
            @RequestParam(value = "id") String id) throws Exception {
        Appeal appeal = AppealService.findById(id);
        if (appeal == null) {
            return failed("账号申诉不存在！", ObjEnvelop.class);
        }
        return success(appeal);
    }

    @ApiOperation(value = "管理员根据id获取/或回复账号申诉,需改变账号申诉回复状态")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.Appeal.UPDATE_APPEAL_BY_ID)
    public ObjEnvelop<Appeal> getAppealAndUpdate(
            @ApiParam(name = "id", value = "账号申诉ID(必要)", defaultValue = "")
            @RequestParam(value = "id", required = true) String id,
            @ApiParam(name = "appealJson", value = "账号申诉Json（非必要，有回复内容，需提供。）", defaultValue = "")
            @RequestParam(value = "appealJson", required = false) String appealJson) throws Exception {
        Appeal appealOld = AppealService.findById(id);
        if (appealOld == null) {
            return failed("账号申诉不存在！", ObjEnvelop.class);
        }
        if (StringUtils.isNotEmpty(appealJson)) {
            Appeal appeal = toEntity(appealJson, Appeal.class);
            appealOld.setFlag(2);
            appealOld.setReplyContent(appeal.getReplyContent());
            appealOld.setUpdateUser(appeal.getUpdateUser());
        } else {
            //根据id获取账号申诉，打开申诉信息
            appealOld.setFlag(1);
        }
        appealOld = AppealService.save(appealOld);
        return success(appealOld);
    }

    @ApiOperation(value = "获取账号申诉:根据日期，根据反馈人等")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.Appeal.GET_APPEALS_BY_FIELD)
    public ListEnvelop<Appeal> getAppealByField(
            @ApiParam(name = "field", value = "查找字段名", required = true)
            @RequestParam(value = "field") String field,
            @ApiParam(name = "value", value = "检索值")
            @RequestParam(value = "value") String value) throws Exception {
        List<Appeal> appealList = AppealService.findByField(field, value);
        return success(appealList);
    }

    @ApiOperation(value = "删除账号申诉")
    @DeleteMapping(value = HealthyHouseMapping.HealthyHouse.Appeal.DELETE)
    public Envelop deleteAppeal(
            @ApiParam(name = "appealId", value = "账号申诉ID")
            @RequestParam(value = "appealId") String appealId) throws Exception {
        Appeal appeal = AppealService.findById(appealId);
        AppealService.delete(appeal);
        return success("success");
    }

}
