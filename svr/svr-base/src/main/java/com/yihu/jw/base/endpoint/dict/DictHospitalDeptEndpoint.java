package com.yihu.jw.base.endpoint.dict;

import com.yihu.jw.base.service.dict.DictHospitalDeptService;
import com.yihu.jw.restmodel.base.dict.DictHospitalDeptVO;
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

import com.yihu.jw.entity.base.dict.DictHospitalDeptDO;

/**
 * 医院科室字典控制器
 *
 * @version <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年08月31日 	Created
 *
 * </pre>
 * @since 1.
 */
@RestController
@RequestMapping(value = BaseRequestMapping.DictHospitalDept.PREFIX)
@Api(value = "医院科室字典管理", description = "医院科室字典管理服务接口", tags = {"wlyy基础服务 - 医院科室字典管理服务接口"})
public class DictHospitalDeptEndpoint extends EnvelopRestEndpoint {

    @Autowired
    private DictHospitalDeptService dictHospitalDeptService;

    @PostMapping(value = BaseRequestMapping.DictHospitalDept.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建")
    public ObjEnvelop<DictHospitalDeptVO> create(
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        DictHospitalDeptDO dictHospitalDept = toEntity(jsonData, DictHospitalDeptDO.class);
        dictHospitalDept = dictHospitalDeptService.save(dictHospitalDept);
        return success(dictHospitalDept, DictHospitalDeptVO.class);
    }

    @PostMapping(value = BaseRequestMapping.DictHospitalDept.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
            @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
            @RequestParam(value = "ids") String ids) {
        dictHospitalDeptService.delete(ids.split(","));
        return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.DictHospitalDept.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public ObjEnvelop<DictHospitalDeptVO> update(
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        DictHospitalDeptDO dictHospitalDept = toEntity(jsonData, DictHospitalDeptDO.class);
        if (null == dictHospitalDept.getId()) {
            return failed("ID不能为空", ObjEnvelop.class);
        }
        dictHospitalDept = dictHospitalDeptService.save(dictHospitalDept);
        return success(dictHospitalDept, DictHospitalDeptVO.class);
    }

    @GetMapping(value = BaseRequestMapping.DictHospitalDept.PAGE)
    @ApiOperation(value = "获取分页")
    public PageEnvelop<DictHospitalDeptVO> page(
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
        List<DictHospitalDeptDO> dictHospitalDepts = dictHospitalDeptService.search(fields, filters, sorts, page, size);
        int count = (int) dictHospitalDeptService.getCount(filters);
        return success(dictHospitalDepts, count, page, size, DictHospitalDeptVO.class);
    }

    @GetMapping(value = BaseRequestMapping.DictHospitalDept.LIST)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<DictHospitalDeptVO> list(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        List<DictHospitalDeptDO> dictHospitalDepts = dictHospitalDeptService.search(fields, filters, sorts);
        return success(dictHospitalDepts, DictHospitalDeptVO.class);
    }

    @GetMapping(value = BaseRequestMapping.DictHospitalDept.queryDeptByOrg)
    @ApiOperation(value = "根据机构获取科室")
    public ListEnvelop<DictHospitalDeptVO> queryDeptByOrg(
            @ApiParam(name = "orgCode", value = "机构标识")
            @RequestParam(value = "orgCode", required = true) String orgCode) throws Exception {
        List<DictHospitalDeptDO> dictHospitalDepts = dictHospitalDeptService.findDeptByOrgCode(orgCode);
        return success(dictHospitalDepts, DictHospitalDeptVO.class);
    }
}
