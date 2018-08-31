package com.yihu.jw.base.endpoint.doctor;

import com.yihu.jw.base.service.doctor.BaseDoctorHospitalService;
import com.yihu.jw.restmodel.base.dict.BaseDoctorHospitalVO;
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

import com.yihu.jw.entity.base.doctor.BaseDoctorHospitalDO;

/**
 * 医生职业信息控制器
 *
 * @version <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年08月31日 	Created
 *
 * </pre>
 * @since 1.
 */
@RestController
@RequestMapping(value = BaseRequestMapping.BaseDoctorHospital.PREFIX)
@Api(value = "医生职业信息管理", description = "医生职业信息管理服务接口", tags = {"wlyy基础服务 - 医生职业信息管理服务接口"})
public class BaseDoctorHospitalEndpoint extends EnvelopRestEndpoint {

    @Autowired
    private BaseDoctorHospitalService baseDoctorHospitalService;

    @PostMapping(value = BaseRequestMapping.BaseDoctorHospital.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建")
    public ObjEnvelop<BaseDoctorHospitalVO> create(
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        BaseDoctorHospitalDO baseDoctorHospital = toEntity(jsonData, BaseDoctorHospitalDO.class);
        baseDoctorHospital = baseDoctorHospitalService.save(baseDoctorHospital);
        return success(baseDoctorHospital, BaseDoctorHospitalVO.class);
    }

    @PostMapping(value = BaseRequestMapping.BaseDoctorHospital.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
            @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
            @RequestParam(value = "ids") String ids) {
        baseDoctorHospitalService.delete(ids.split(","));
        return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.BaseDoctorHospital.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public ObjEnvelop<BaseDoctorHospitalVO> update(
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        BaseDoctorHospitalDO baseDoctorHospital = toEntity(jsonData, BaseDoctorHospitalDO.class);
        if (null == baseDoctorHospital.getId()) {
            return failed("ID不能为空", ObjEnvelop.class);
        }
        baseDoctorHospital = baseDoctorHospitalService.save(baseDoctorHospital);
        return success(baseDoctorHospital, BaseDoctorHospitalVO.class);
    }

    @GetMapping(value = BaseRequestMapping.BaseDoctorHospital.PAGE)
    @ApiOperation(value = "获取分页")
    public PageEnvelop<BaseDoctorHospitalVO> page(
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
        List<BaseDoctorHospitalDO> baseDoctorHospitals = baseDoctorHospitalService.search(fields, filters, sorts, page, size);
        int count = (int) baseDoctorHospitalService.getCount(filters);
        return success(baseDoctorHospitals, count, page, size, BaseDoctorHospitalVO.class);
    }

    @GetMapping(value = BaseRequestMapping.BaseDoctorHospital.LIST)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<BaseDoctorHospitalVO> list(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        List<BaseDoctorHospitalDO> baseDoctorHospitals = baseDoctorHospitalService.search(fields, filters, sorts);
        return success(baseDoctorHospitals, BaseDoctorHospitalVO.class);
    }

}
