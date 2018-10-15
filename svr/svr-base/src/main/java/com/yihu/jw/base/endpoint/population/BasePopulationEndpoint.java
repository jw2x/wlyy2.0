package com.yihu.jw.base.endpoint.population;

import com.yihu.jw.base.service.population.BasePopulationService;
import com.yihu.jw.entity.base.population.BasePopulationDO;
import com.yihu.jw.restmodel.base.population.BasePopulationVO;
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
* 基础人口基数信息控制器
*
* @version
* <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年09月26日 	update
 *
 * </pre>
* @since 1.
*/
@RestController
@RequestMapping(value = BaseRequestMapping.BasePopulation.PREFIX)
@Api(value = "基础人口基数信息管理", description = "基础人口基数信息管理服务接口", tags = {"wlyy基础服务 - 基础人口基数信息管理服务接口"})
public class BasePopulationEndpoint extends EnvelopRestEndpoint {

@Autowired
private BasePopulationService basePopulationService;

@PostMapping(value = BaseRequestMapping.BasePopulation.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
@ApiOperation(value = "创建")
public ObjEnvelop<BasePopulationVO> create (
    @ApiParam(name = "json_data", value = "Json数据", required = true)
    @RequestBody String jsonData) throws Exception {
    BasePopulationDO basePopulation = toEntity(jsonData, BasePopulationDO.class);
    basePopulation = basePopulationService.save(basePopulation);
    return success(basePopulation, BasePopulationVO.class);
    }

    @PostMapping(value = BaseRequestMapping.BasePopulation.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
    @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
    @RequestParam(value = "ids") String ids) {
    basePopulationService.delete(ids.split(","));
    return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.BasePopulation.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public ObjEnvelop<BasePopulationVO> update (
        @ApiParam(name = "json_data", value = "Json数据", required = true)
        @RequestBody String jsonData) throws Exception {
        BasePopulationDO basePopulation = toEntity(jsonData, BasePopulationDO.class);
        if (null == basePopulation.getId()) {
        return failed("ID不能为空", ObjEnvelop.class);
        }
        basePopulation = basePopulationService.save(basePopulation);
        return success(basePopulation, BasePopulationVO.class);
        }

        @GetMapping(value = BaseRequestMapping.BasePopulation.PAGE)
        @ApiOperation(value = "获取分页")
        public PageEnvelop<BasePopulationVO> page (
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
            List<BasePopulationDO> basePopulations = basePopulationService.search(fields, filters, sorts, page, size);
                int count = (int)basePopulationService.getCount(filters);
                return success(basePopulations, count, page, size, BasePopulationVO.class);
         }

         @GetMapping(value = BaseRequestMapping.BasePopulation.LIST)
         @ApiOperation(value = "获取列表")
         public ListEnvelop<BasePopulationVO> list (
             @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
             @RequestParam(value = "fields", required = false) String fields,
             @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
             @RequestParam(value = "filters", required = false) String filters,
             @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
             @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
             List<BasePopulationDO> basePopulations = basePopulationService.search(fields, filters, sorts);
                  return success(basePopulations, BasePopulationVO.class);
         }

 }
