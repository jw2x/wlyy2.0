package com.yihu.jw.healthyhouse.controller.user;


import com.yihu.jw.healthyhouse.model.user.FeedBack;
import com.yihu.jw.healthyhouse.service.user.FeedBackService;
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
 * 意见反馈
 * Created by zdm on 2018/9/21.
 */
@RestController
@RequestMapping(HealthyHouseMapping.api_healthyHouse_common)
@Api(value = "FeedBack", description = "意见反馈管理", tags = {"意见反馈管理"})
public class FeedBackController extends EnvelopRestEndpoint {

    @Autowired
    private FeedBackService feedBackService;

    @ApiOperation(value = "获取意见反馈列表", responseContainer = "List")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.FeedBack.PAGE)
    public PageEnvelop<FeedBack> getFeedBacks(
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
        List<FeedBack> feedBackList = feedBackService.search(fields, filters, sorts, page, size);
        return success(feedBackList, (null == feedBackList) ? 0 : feedBackList.size(), page, size);
    }

    @ApiOperation(value = "创建/更新（id存在）意见反馈")
    @PostMapping(value = HealthyHouseMapping.HealthyHouse.FeedBack.CREATE)
    public ObjEnvelop<FeedBack> createFeedBack(
            @ApiParam(name = "feedBack", value = "意见反馈JSON结构")
            @RequestBody FeedBack feedBack) throws IOException {
        feedBack = feedBackService.save(feedBack);
        return success(feedBack);
    }

    @ApiOperation(value = "获取意见反馈")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.FeedBack.GET_FEEDBACK_BY_ID)
    public ObjEnvelop<FeedBack> getFeedBack(
            @ApiParam(name = "id", value = "意见反馈ID", defaultValue = "")
            @RequestParam(value = "id") String id) throws Exception {
        FeedBack feedBack = feedBackService.findById(id);
        if (feedBack == null) {
            return failed("意见反馈不存在！", ObjEnvelop.class);
        }
        return success(feedBack);
    }

    @ApiOperation(value = "管理员根据id获取/或回复意见反馈,需改变意见反馈回复状态")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.FeedBack.UPDATE_FEEDBACKS_BY_ID)
    public ObjEnvelop<FeedBack> getFeedBackAndUpdate(
            @ApiParam(name = "id", value = "意见反馈ID(必要)", defaultValue = "")
            @RequestParam(value = "id", required = true) String id,
            @ApiParam(name = "feedBackJson", value = "意见反馈Json（非必要，有回复内容，需提供。）", defaultValue = "")
            @RequestParam(value = "feedBackJson", required = false) String feedBackJson) throws Exception {
        FeedBack feedBackOld = feedBackService.findById(id);
        if (feedBackOld == null) {
            return failed("意见反馈不存在！", ObjEnvelop.class);
        }
        if (StringUtils.isNotEmpty(feedBackJson)) {
            FeedBack feedBack = toEntity(feedBackJson, FeedBack.class);
            feedBackOld.setFlag(2);
            feedBackOld.setReplyContent(feedBack.getReplyContent());
            feedBackOld.setUpdateUser(feedBack.getUpdateUser());
        } else {
            //根据id获取意见反馈，打开待反馈信息
            feedBackOld.setFlag(1);
        }
        feedBackOld = feedBackService.save(feedBackOld);
        return success(feedBackOld);
    }

    @ApiOperation(value = "获取意见反馈:根据日期，根据反馈人等")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.FeedBack.GET_FEEDBACKS_BY_FIELD)
    public ListEnvelop<FeedBack> getFeedBackByField(
            @ApiParam(name = "field", value = "查找字段名", required = true)
            @RequestParam(value = "field") String field,
            @ApiParam(name = "value", value = "检索值")
            @RequestParam(value = "value") String value) throws Exception {
        List<FeedBack> feedBackList = feedBackService.findByField(field, value);
        return success(feedBackList);
    }

    @ApiOperation(value = "删除意见反馈")
    @DeleteMapping(value = HealthyHouseMapping.HealthyHouse.FeedBack.DELETE)
    public Envelop deleteFeedBack(
            @ApiParam(name = "facilitiesServerId", value = "意见反馈ID")
            @RequestParam(value = "facilitiesServerId") String facilitiesServerId) throws Exception {
        FeedBack feedBack = feedBackService.findById(facilitiesServerId);
        feedBackService.delete(feedBack);
        return success("success");
    }

}
