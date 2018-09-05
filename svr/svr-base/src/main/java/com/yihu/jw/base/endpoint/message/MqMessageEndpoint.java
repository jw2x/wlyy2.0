package com.yihu.jw.base.endpoint.message;

import com.yihu.jw.base.service.message.MqMessageService;
import com.yihu.jw.entity.base.message.MqMessageDO;
import com.yihu.jw.restmodel.base.message.MqMessageVO;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.ListEnvelop;
import com.yihu.jw.restmodel.web.ObjEnvelop;
import com.yihu.jw.restmodel.web.PageEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.base.BaseRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoint - 基于MQ的消息推送
 * Created by progr1mmer on 2018/9/3.
 */
@RestController
@RequestMapping(value = BaseRequestMapping.MqMessage.PREFIX)
@Api(value = "基于MQ的消息推送", description = "基于MQ的消息推送服务接口", tags = {"wlyy基础服务 - 基于MQ的消息推送服务接口"})
public class MqMessageEndpoint extends EnvelopRestEndpoint {

    @Autowired
    private MqMessageService mqMessageService;

    @PostMapping(value = BaseRequestMapping.Function.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建")
    public ObjEnvelop<MqMessageVO> create (
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        MqMessageDO mqMessageDO = toEntity(jsonData, MqMessageDO.class);
        mqMessageDO = mqMessageService.save(mqMessageDO);
        return success(mqMessageDO, MqMessageVO.class);
    }

    @PostMapping(value = BaseRequestMapping.Function.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
            @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
            @RequestParam(value = "ids") String ids) {
        mqMessageService.delete(ids);
        return success("success");
    }

    @PostMapping(value = BaseRequestMapping.Function.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public ObjEnvelop<MqMessageVO> update (
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        MqMessageDO mqMessageDO = toEntity(jsonData, MqMessageDO.class);
        if (null == mqMessageDO.getId()) {
            return failed("ID不能为空", ObjEnvelop.class);
        }
        mqMessageDO = mqMessageService.save(mqMessageDO);
        return success(mqMessageDO, MqMessageVO.class);
    }

    @GetMapping(value = BaseRequestMapping.Function.PAGE)
    @ApiOperation(value = "获取分页")
    public PageEnvelop<MqMessageVO> page (
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts,
            @ApiParam(name = "page", value = "分页大小", required = true, defaultValue = "1")
            @RequestParam(value = "page") int page,
            @ApiParam(name = "size", value = "页码", required = true, defaultValue = "15")
            @RequestParam(value = "size") int size) throws Exception {
        List<MqMessageDO> mqMessageDOS = mqMessageService.search(fields, filters, sorts, page, size);
        int count = (int)mqMessageService.getCount(filters);
        return success(mqMessageDOS, count, page, size, MqMessageVO.class);
    }

    @GetMapping(value = BaseRequestMapping.Function.LIST)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<MqMessageVO> list (
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        List<MqMessageDO> mqMessageDOS = mqMessageService.search(fields, filters, sorts);
        return success(mqMessageDOS, MqMessageVO.class);
    }

}
