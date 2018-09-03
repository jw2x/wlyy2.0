package com.yihu.jw.base.endpoint.im;

import com.yihu.jw.base.service.ImGetuiConfigService;
import com.yihu.jw.entity.base.im.ImGetuiConfigDO;
import com.yihu.jw.restmodel.base.im.ImGetuiConfigVO;
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
 * @author yeshijie on 2018/8/29.
 */
@RestController
@RequestMapping(value = BaseRequestMapping.ImGetuiConfig.PREFIX, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "im管理", description = "im管理服务接口", tags = {"wlyy基础服务 - im管理服务接口"})
public class ImGetuiConfigEndpiont extends EnvelopRestEndpoint {

    @Autowired
    private ImGetuiConfigService imGetuiConfigService;

    @PostMapping(value = BaseRequestMapping.ImGetuiConfig.CREATE)
    @ApiOperation(value = "创建")
    public ObjEnvelop<ImGetuiConfigVO> create (
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        ImGetuiConfigDO imGetuiConfigDO = toEntity(jsonData, ImGetuiConfigDO.class);
        imGetuiConfigDO = imGetuiConfigService.save(imGetuiConfigDO);
        return success(convertToModel(imGetuiConfigDO, ImGetuiConfigVO.class));
    }

    @PostMapping(value = BaseRequestMapping.ImGetuiConfig.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
            @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
            @RequestParam(value = "ids") String ids) {
        imGetuiConfigService.delete(ids);
        return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.ImGetuiConfig.UPDATE)
    @ApiOperation(value = "更新")
    public ObjEnvelop<ImGetuiConfigVO> update (
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        ImGetuiConfigDO imGetuiConfigDO = toEntity(jsonData, ImGetuiConfigDO.class);
        if (null == imGetuiConfigDO.getId()) {
            return failed("ID不能为空", ObjEnvelop.class);
        }
        imGetuiConfigDO = imGetuiConfigService.save(imGetuiConfigDO);
        return success(convertToModel(imGetuiConfigDO, ImGetuiConfigVO.class));
    }

    @GetMapping(value = BaseRequestMapping.ImGetuiConfig.PAGE)
    @ApiOperation(value = "获取分页")
    public PageEnvelop<ImGetuiConfigVO> page (
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
        List<ImGetuiConfigDO> list = imGetuiConfigService.search(fields, filters, sorts, page, size);
        int count = (int)imGetuiConfigService.getCount(filters);
        return success(list, count, page, size, ImGetuiConfigVO.class);
    }

    @GetMapping(value = BaseRequestMapping.ImGetuiConfig.LIST)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<ImGetuiConfigVO> list (
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        List<ImGetuiConfigDO> list = imGetuiConfigService.search(fields, filters, sorts);
        return success(list, ImGetuiConfigVO.class);
    }
}
