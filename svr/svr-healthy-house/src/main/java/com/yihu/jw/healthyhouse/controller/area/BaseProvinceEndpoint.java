package com.yihu.jw.healthyhouse.controller.area;


import com.yihu.jw.healthyhouse.model.area.BaseProvinceDO;
import com.yihu.jw.healthyhouse.service.area.BaseProvinceService;
import com.yihu.jw.restmodel.base.area.BaseProvinceVO;
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
* 省字典控制器
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
@RequestMapping(value = BaseRequestMapping.BaseProvince.PREFIX)
@Api(value = "省字典管理", description = "省字典管理服务接口", tags = {"省字典管理服务接口"})
public class BaseProvinceEndpoint extends EnvelopRestEndpoint {

@Autowired
private BaseProvinceService baseProvinceService;

@PostMapping(value = BaseRequestMapping.BaseProvince.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
@ApiOperation(value = "创建")
public ObjEnvelop<BaseProvinceVO> create (
    @ApiParam(name = "json_data", value = "Json数据", required = true)
    @RequestBody String jsonData) throws Exception {
    BaseProvinceDO baseProvince = toEntity(jsonData, BaseProvinceDO.class);
    baseProvince = baseProvinceService.save(baseProvince);
    return success(baseProvince, BaseProvinceVO.class);
    }

    @PostMapping(value = BaseRequestMapping.BaseProvince.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
    @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
    @RequestParam(value = "ids") String ids) {
    baseProvinceService.delete(ids.split(","));
    return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.BaseProvince.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public ObjEnvelop<BaseProvinceVO> update (
        @ApiParam(name = "json_data", value = "Json数据", required = true)
        @RequestBody String jsonData) throws Exception {
        BaseProvinceDO baseProvince = toEntity(jsonData, BaseProvinceDO.class);
        if (null == baseProvince.getId()) {
        return failed("ID不能为空", ObjEnvelop.class);
        }
        baseProvince = baseProvinceService.save(baseProvince);
        return success(baseProvince, BaseProvinceVO.class);
        }

        @GetMapping(value = BaseRequestMapping.BaseProvince.PAGE)
        @ApiOperation(value = "获取分页")
        public PageEnvelop<BaseProvinceVO> page (
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
            List<BaseProvinceDO> baseProvinces = baseProvinceService.search(fields, filters, sorts, page, size);
                int count = (int)baseProvinceService.getCount(filters);
                return success(baseProvinces, count, page, size, BaseProvinceVO.class);
         }

         @GetMapping(value = BaseRequestMapping.BaseProvince.LIST)
         @ApiOperation(value = "获取列表")
         public ListEnvelop<BaseProvinceVO> list (
             @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
             @RequestParam(value = "fields", required = false) String fields,
             @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
             @RequestParam(value = "filters", required = false) String filters,
             @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
             @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
             List<BaseProvinceDO> baseProvinces = baseProvinceService.search(fields, filters, sorts);
                  return success(baseProvinces, BaseProvinceVO.class);
         }

 }
