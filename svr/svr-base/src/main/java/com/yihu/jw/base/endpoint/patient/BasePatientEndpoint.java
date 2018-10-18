package com.yihu.jw.base.endpoint.patient;

import com.yihu.jw.base.service.patient.BasePatientService;
import com.yihu.jw.base.util.ConstantUtils;
import com.yihu.jw.restmodel.base.patient.BasePatientVO;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.ListEnvelop;
import com.yihu.jw.restmodel.web.ObjEnvelop;
import com.yihu.jw.restmodel.web.PageEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.base.BaseRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import com.yihu.jw.entity.base.patient.BasePatientDO;

/**
 * 居民信息控制器
 *
 * @version <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年08月31日 	Created
 *
 * </pre>
 * @since 1.
 */
@RestController
@RequestMapping(value = BaseRequestMapping.BasePatient.PREFIX)
@Api(value = "居民信息管理", description = "居民信息管理服务接口", tags = {"wlyy基础服务 - 居民信息管理服务接口"})
public class BasePatientEndpoint extends EnvelopRestEndpoint {

    @Autowired
    private BasePatientService basePatientService;

    @PostMapping(value = BaseRequestMapping.BasePatient.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建")
    public Envelop create(
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestParam String jsonData) throws Exception {
       String msg = basePatientService.createPatient(jsonData);
       if(!StringUtils.equalsIgnoreCase(ConstantUtils.SUCCESS,msg)){
           return success(msg);
       }
        return failed(msg);
    }

    @PostMapping(value = BaseRequestMapping.BasePatient.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
            @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
            @RequestParam(value = "ids") String ids) {
        basePatientService.delete(ids.split(","));
        return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.BasePatient.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public ObjEnvelop<BasePatientVO> update(
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestParam String jsonData) throws Exception {
        BasePatientDO basePatient = toEntity(jsonData, BasePatientDO.class);
        if (null == basePatient.getId()) {
            return failed("ID不能为空", ObjEnvelop.class);
        }
        basePatient = basePatientService.save(basePatient);
        return success(basePatient, BasePatientVO.class);
    }

    @PostMapping(value = BaseRequestMapping.BasePatient.getPatientById)
    @ApiOperation(value = "获取列表")
    public Envelop getPatientById(
            @ApiParam(name = "id", value = "居民id")
            @RequestParam(value = "id", required = true) String id) throws Exception {
        Map<String,Object> basePatients = basePatientService.getPatientInfo(id);
        return success(basePatients.toString());
    }

    @GetMapping(value = BaseRequestMapping.BasePatient.PAGE)
    @ApiOperation(value = "获取分页")
    public PageEnvelop<BasePatientVO> page(
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
        List<BasePatientDO> basePatients = basePatientService.search(fields, filters, sorts, page, size);
        int count = (int) basePatientService.getCount(filters);
        return success(basePatients, count, page, size, BasePatientVO.class);
    }

    @GetMapping(value = BaseRequestMapping.BasePatient.LIST)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<BasePatientVO> list(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        List<BasePatientDO> basePatients = basePatientService.search(fields, filters, sorts);
        return success(basePatients, BasePatientVO.class);
    }

    @PostMapping(value = BaseRequestMapping.BasePatient.getBaseInfo)
    @ApiOperation(value = "获取居民基础信息列表")
    public ListEnvelop queryPatientBaseInfo(
            @ApiParam(name = "idcard", value = "居民身份证")
            @RequestParam(value = "idcard", required = false) String idcard,
            @ApiParam(name = "name", value = "居民姓名")
            @RequestParam(value = "name", required = false) String name,
            @ApiParam(name = "page", value = "分页大小", required = true, defaultValue = "1")
            @RequestParam(value = "page") int page,
            @ApiParam(name = "size", value = "页码", required = true, defaultValue = "15")
            @RequestParam(value = "size") int size,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {

        List<Map<String,Object>> basePatients = basePatientService.queryPatientBaseInfo(idcard, name, page,size,sorts);
        return success(basePatients);
    }
}
