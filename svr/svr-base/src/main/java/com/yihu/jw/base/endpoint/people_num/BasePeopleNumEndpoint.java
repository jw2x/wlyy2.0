package com.yihu.jw.base.endpoint.people_num;

import com.yihu.jw.base.service.people_num.BasePeopleNumService;
import com.yihu.jw.entity.base.peopel_num.BasePeopleNumDO;
import com.yihu.jw.restmodel.base.people_num.BasePeopleNumVO;
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
 * Administrator 	1.0  		2018年09月05日 	Created
 *
 * </pre>
* @since 1.
*/
@RestController
@RequestMapping(value = BaseRequestMapping.BasePeopleNum.PREFIX)
@Api(value = "基础人口基数信息管理", description = "基础人口基数信息管理服务接口", tags = {"wlyy基础服务 - 基础人口基数信息管理服务接口"})
public class BasePeopleNumEndpoint extends EnvelopRestEndpoint {

@Autowired
private BasePeopleNumService basePeopleNumService;

@PostMapping(value = BaseRequestMapping.BasePeopleNum.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
@ApiOperation(value = "创建")
public ObjEnvelop<BasePeopleNumVO> create (
    @ApiParam(name = "json_data", value = "Json数据", required = true)
    @RequestBody String jsonData) throws Exception {
    BasePeopleNumDO basePeopleNum = toEntity(jsonData, BasePeopleNumDO.class);
    basePeopleNum = basePeopleNumService.save(basePeopleNum);
    return success(basePeopleNum, BasePeopleNumVO.class);
    }

    @PostMapping(value = BaseRequestMapping.BasePeopleNum.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
    @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
    @RequestParam(value = "ids") String ids) {
    basePeopleNumService.delete(ids.split(","));
    return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.BasePeopleNum.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public ObjEnvelop<BasePeopleNumVO> update (
        @ApiParam(name = "json_data", value = "Json数据", required = true)
        @RequestBody String jsonData) throws Exception {
        BasePeopleNumDO basePeopleNum = toEntity(jsonData, BasePeopleNumDO.class);
        if (null == basePeopleNum.getId()) {
        return failed("ID不能为空", ObjEnvelop.class);
        }
        basePeopleNum = basePeopleNumService.save(basePeopleNum);
        return success(basePeopleNum, BasePeopleNumVO.class);
        }

        @GetMapping(value = BaseRequestMapping.BasePeopleNum.PAGE)
        @ApiOperation(value = "获取分页")
        public PageEnvelop<BasePeopleNumVO> page (
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
            List<BasePeopleNumDO> basePeopleNums = basePeopleNumService.search(fields, filters, sorts, page, size);
                int count = (int)basePeopleNumService.getCount(filters);
                return success(basePeopleNums, count, page, size, BasePeopleNumVO.class);
         }

         @GetMapping(value = BaseRequestMapping.BasePeopleNum.LIST)
         @ApiOperation(value = "获取列表")
         public ListEnvelop<BasePeopleNumVO> list (
             @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
             @RequestParam(value = "fields", required = false) String fields,
             @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
             @RequestParam(value = "filters", required = false) String filters,
             @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
             @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
             List<BasePeopleNumDO> basePeopleNums = basePeopleNumService.search(fields, filters, sorts);
                  return success(basePeopleNums, BasePeopleNumVO.class);
         }

 }
