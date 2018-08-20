package com.yihu.jw.base.endpoint.theme;

import com.yihu.jw.base.service.ThemeService;
import com.yihu.jw.entity.base.theme.ThemeDO;
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
 * Created by progr1mmer on 2018/8/16.
 */
@RestController
@RequestMapping(value = BaseRequestMapping.Theme.PREFIX)
@Api(value = "主题应用", description = "主题应用服务接口", tags = {"wlyy基础服务 - 主题应用服务接口"})
public class ThemeEndpoint extends EnvelopRestEndpoint {

    @Autowired
    private ThemeService themeService;

    @PostMapping(value = BaseRequestMapping.Theme.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建")
    public ObjEnvelop<ThemeDO> create (
            @ApiParam(name = "json", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        ThemeDO theme = toEntity(jsonData, ThemeDO.class);
        theme = themeService.save(theme);
        return success(theme);
    }

    @PostMapping(value = BaseRequestMapping.Theme.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete (
            @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
            @RequestParam(value = "ids") String ids){
        themeService.delete(ids.split(","));
        return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.Theme.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public Envelop update (
            @ApiParam(name = "json", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        ThemeDO theme = toEntity(jsonData, ThemeDO.class);
        if (null == theme.getId()) {
            return failed("ID不能为空");
        }
        theme = themeService.save(theme);
        return success(theme);
    }

    @GetMapping(value = BaseRequestMapping.Theme.PAGE)
    @ApiOperation(value = "获取分页")
    public PageEnvelop<ThemeDO> page (
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
        List<ThemeDO> themes = themeService.search(fields, filters, sorts, page, size);
        int count = (int)themeService.getCount(filters);
        return success(themes, count, page, size);
    }

    @GetMapping(value = BaseRequestMapping.Theme.LIST)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<ThemeDO> list (
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        List<ThemeDO> themes = themeService.search(fields, filters, sorts);
        return success(themes);
    }

    @GetMapping(value = BaseRequestMapping.Theme.CHECK_STYLE)
    @ApiOperation(value = "检查主题风格是否已存在(desc=1代表存在，desc=0代表不存在)")
    public Envelop checkName (
            @ApiParam(name = "style", value = "主题风格", required = true)
            @RequestParam(value = "style", required = false) String style) throws Exception {
        if (themeService.search("style=" + style).size() > 0) {
            return success("1");
        } else {
            return success("0");
        }
    }

}
