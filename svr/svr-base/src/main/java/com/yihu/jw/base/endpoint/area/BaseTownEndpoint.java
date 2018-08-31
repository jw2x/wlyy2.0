package com.yihu.jw.base.endpoint.area;

import com.yihu.jw.base.service.area.BaseTownService;
import com.yihu.jw.restmodel.base.dict.BaseTownVO;
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

import com.yihu.jw.entity.base.area.BaseTownDO;

/**
*
* 区县字典控制器
*
* @version
* <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年08月31日 	Created
 *
 * </pre>
* @since 1.
*/
@RestController
@RequestMapping(value = BaseRequestMapping.BaseTown.PREFIX)
@Api(value = "区县字典管理", description = "区县字典管理服务接口", tags = {"wlyy基础服务 - 区县字典管理服务接口"})
public class BaseTownEndpoint extends EnvelopRestEndpoint {

@Autowired
private BaseTownService baseTownService;

@PostMapping(value = BaseRequestMapping.BaseTown.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
@ApiOperation(value = "创建")
public ObjEnvelop<BaseTownVO> create (
    @ApiParam(name = "json_data", value = "Json数据", required = true)
    @RequestBody String jsonData) throws Exception {
    BaseTownDO baseTown = toEntity(jsonData, BaseTownDO.class);
    baseTown = baseTownService.save(baseTown);
    return success(baseTown, BaseTownVO.class);
    }

    @PostMapping(value = BaseRequestMapping.BaseTown.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
    @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
    @RequestParam(value = "ids") String ids) {
    baseTownService.delete(ids.split(","));
    return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.BaseTown.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public ObjEnvelop<BaseTownVO> update (
        @ApiParam(name = "json_data", value = "Json数据", required = true)
        @RequestBody String jsonData) throws Exception {
        BaseTownDO baseTown = toEntity(jsonData, BaseTownDO.class);
        if (null == baseTown.getId()) {
        return failed("ID不能为空", ObjEnvelop.class);
        }
        baseTown = baseTownService.save(baseTown);
        return success(baseTown, BaseTownVO.class);
        }

        @GetMapping(value = BaseRequestMapping.BaseTown.PAGE)
        @ApiOperation(value = "获取分页")
        public PageEnvelop<BaseTownVO> page (
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
            List<BaseTownDO> baseTowns = baseTownService.search(fields, filters, sorts, page, size);
                int count = (int)baseTownService.getCount(filters);
                return success(baseTowns, count, page, size, BaseTownVO.class);
         }

         @GetMapping(value = BaseRequestMapping.BaseTown.LIST)
         @ApiOperation(value = "获取列表")
         public ListEnvelop<BaseTownVO> list (
             @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
             @RequestParam(value = "fields", required = false) String fields,
             @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
             @RequestParam(value = "filters", required = false) String filters,
             @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
             @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
             List<BaseTownDO> baseTowns = baseTownService.search(fields, filters, sorts);
                  return success(baseTowns, BaseTownVO.class);
         }

 }
