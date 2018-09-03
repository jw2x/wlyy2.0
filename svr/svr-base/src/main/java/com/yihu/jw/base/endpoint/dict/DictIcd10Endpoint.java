package com.yihu.jw.base.endpoint.dict;

import com.yihu.jw.base.service.dict.DictIcd10Service;
import com.yihu.jw.restmodel.base.dict.DictIcd10VO;
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

import com.yihu.jw.entity.base.dict.DictIcd10DO;

/**
 * ICD10字典控制器
 *
 * @version <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年08月31日 	Created
 *
 * </pre>
 * @since 1.
 */
@RestController
@RequestMapping(value = BaseRequestMapping.DictIcd10.PREFIX)
@Api(value = "ICD10字典管理", description = "ICD10字典管理服务接口", tags = {"wlyy基础服务 - ICD10字典管理服务接口"})
public class DictIcd10Endpoint extends EnvelopRestEndpoint {

    @Autowired
    private DictIcd10Service dictIcd10Service;

    @PostMapping(value = BaseRequestMapping.DictIcd10.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建")
    public ObjEnvelop<DictIcd10VO> create(
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        DictIcd10DO dictIcd10 = toEntity(jsonData, DictIcd10DO.class);
        dictIcd10 = dictIcd10Service.save(dictIcd10);
        return success(dictIcd10, DictIcd10VO.class);
    }

    @PostMapping(value = BaseRequestMapping.DictIcd10.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
            @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
            @RequestParam(value = "ids") String ids) {
        dictIcd10Service.delete(ids.split(","));
        return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.DictIcd10.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public ObjEnvelop<DictIcd10VO> update(
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        DictIcd10DO dictIcd10 = toEntity(jsonData, DictIcd10DO.class);
        if (null == dictIcd10.getId()) {
            return failed("ID不能为空", ObjEnvelop.class);
        }
        dictIcd10 = dictIcd10Service.save(dictIcd10);
        return success(dictIcd10, DictIcd10VO.class);
    }

    @GetMapping(value = BaseRequestMapping.DictIcd10.PAGE)
    @ApiOperation(value = "获取分页")
    public PageEnvelop<DictIcd10VO> page(
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
        List<DictIcd10DO> dictIcd10s = dictIcd10Service.search(fields, filters, sorts, page, size);
        int count = (int) dictIcd10Service.getCount(filters);
        return success(dictIcd10s, count, page, size, DictIcd10VO.class);
    }

    @GetMapping(value = BaseRequestMapping.DictIcd10.LIST)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<DictIcd10VO> list(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        List<DictIcd10DO> dictIcd10s = dictIcd10Service.search(fields, filters, sorts);
        return success(dictIcd10s, DictIcd10VO.class);
    }

}
