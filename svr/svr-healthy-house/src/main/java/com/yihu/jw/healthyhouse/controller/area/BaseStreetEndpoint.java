package com.yihu.jw.healthyhouse.controller.area;


import com.yihu.jw.healthyhouse.model.area.BaseStreet;
import com.yihu.jw.healthyhouse.service.area.BaseStreetService;
import com.yihu.jw.restmodel.base.area.BaseStreetVO;
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
*
* 街道字典控制器
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
@RequestMapping(value = BaseRequestMapping.BaseStreet.PREFIX)
@Api(value = "街道字典管理", description = "街道字典管理服务接口", tags = {"街道字典管理服务接口"})
public class BaseStreetEndpoint extends EnvelopRestEndpoint {

@Autowired
private BaseStreetService baseStreetService;

@PostMapping(value = BaseRequestMapping.BaseStreet.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
@ApiOperation(value = "创建")
public ObjEnvelop<BaseStreetVO> create (
    @ApiParam(name = "json_data", value = "Json数据", required = true)
    @RequestBody String jsonData) throws Exception {
    BaseStreet baseStreet = toEntity(jsonData, BaseStreet.class);
    baseStreet = baseStreetService.save(baseStreet);
    return success(baseStreet, BaseStreetVO.class);
    }

    @PostMapping(value = BaseRequestMapping.BaseStreet.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
    @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
    @RequestParam(value = "ids") String ids) {
    baseStreetService.delete(ids.split(","));
    return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.BaseStreet.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public ObjEnvelop<BaseStreetVO> update (
        @ApiParam(name = "json_data", value = "Json数据", required = true)
        @RequestBody String jsonData) throws Exception {
        BaseStreet baseStreet = toEntity(jsonData, BaseStreet.class);
        if (null == baseStreet.getId()) {
        return failed("ID不能为空", ObjEnvelop.class);
        }
        baseStreet = baseStreetService.save(baseStreet);
        return success(baseStreet, BaseStreetVO.class);
        }

        @GetMapping(value = BaseRequestMapping.BaseStreet.PAGE)
        @ApiOperation(value = "获取分页")
        public PageEnvelop<BaseStreetVO> page (
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
            List<BaseStreet> baseStreets = baseStreetService.search(fields, filters, sorts, page, size);
                int count = (int)baseStreetService.getCount(filters);
                return success(baseStreets, count, page, size, BaseStreetVO.class);
         }

         @GetMapping(value = BaseRequestMapping.BaseStreet.LIST)
         @ApiOperation(value = "获取列表")
         public ListEnvelop<BaseStreetVO> list (
             @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
             @RequestParam(value = "fields", required = false) String fields,
             @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
             @RequestParam(value = "filters", required = false) String filters,
             @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
             @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
             List<BaseStreet> baseStreets = baseStreetService.search(fields, filters, sorts);
                  return success(baseStreets, BaseStreetVO.class);
         }

 }
