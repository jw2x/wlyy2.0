package com.yihu.jw.base.endpoint.doctor;

import com.yihu.jw.base.service.doctor.BaseDoctorService;
import com.yihu.jw.restmodel.base.doctor.BaseDoctorVO;
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
import java.util.Map;

import com.yihu.jw.entity.base.doctor.BaseDoctorDO;

/**
 * 医生基础信息控制器
 *
 * @version <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年08月31日 	Created
 *
 * </pre>
 * @since 1.
 */
@RestController
@RequestMapping(value = BaseRequestMapping.BaseDoctor.PREFIX)
@Api(value = "医生基础信息管理", description = "医生基础信息管理服务接口", tags = {"wlyy基础服务 - 医生基础信息管理服务接口"})
public class BaseDoctorEndpoint extends EnvelopRestEndpoint {

    @Autowired
    private BaseDoctorService baseDoctorService;

    @PostMapping(value = BaseRequestMapping.BaseDoctor.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建")
    public ObjEnvelop<BaseDoctorVO> create(
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        BaseDoctorDO baseDoctor = toEntity(jsonData, BaseDoctorDO.class);
        baseDoctor = baseDoctorService.save(baseDoctor);
        return success(baseDoctor, BaseDoctorVO.class);
    }

    @PostMapping(value = BaseRequestMapping.BaseDoctor.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
            @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
            @RequestParam(value = "ids") String ids) {
        baseDoctorService.delete(ids.split(","));
        return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.BaseDoctor.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public ObjEnvelop<BaseDoctorVO> update(
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        BaseDoctorDO baseDoctor = toEntity(jsonData, BaseDoctorDO.class);
        if (null == baseDoctor.getId()) {
            return failed("ID不能为空", ObjEnvelop.class);
        }
        baseDoctor = baseDoctorService.save(baseDoctor);
        return success(baseDoctor, BaseDoctorVO.class);
    }

    @GetMapping(value = BaseRequestMapping.BaseDoctor.PAGE)
    @ApiOperation(value = "获取分页")
    public PageEnvelop<BaseDoctorVO> page(
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
        List<BaseDoctorDO> baseDoctors = baseDoctorService.search(fields, filters, sorts, page, size);
        int count = (int) baseDoctorService.getCount(filters);
        return success(baseDoctors, count, page, size, BaseDoctorVO.class);
    }

    @GetMapping(value = BaseRequestMapping.BaseDoctor.LIST)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<BaseDoctorVO> list(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        List<BaseDoctorDO> baseDoctors = baseDoctorService.search(fields, filters, sorts);
        return success(baseDoctors, BaseDoctorVO.class);
    }

    /**
     * 单个医生信息（基本信息 + 医院执业信息）
     * @param orgId
     * @param doctorId
     * @return
     * @throws Exception
     */
    @PostMapping(value = BaseRequestMapping.BaseDoctor.DOCINFO)
    @ApiOperation(value = "获取单个医生执业信息列表")
    public Envelop doctorHosplist(
            @ApiParam(name = "orgId", value = "医院id")
            @RequestParam(value = "orgId", required = true) String orgId,
            @ApiParam(name = "doctorId", value = "医生id")
            @RequestParam(value = "doctorId", required = true) String doctorId) throws Exception {
        Map<String,Object> map = baseDoctorService.getDoctorInfo(orgId, doctorId);
        return success(map.toString());
    }

 /**
     * 医生信息（基本信息 + 医院执业信息）列表
     * @param name
     * @param idcard
     * @param orgCode
     * @param doctorStatus
     * @return
     * @throws Exception
     */
    @PostMapping(value = BaseRequestMapping.BaseDoctor.DOCINFO)
    @ApiOperation(value = "获取列表")
    public Envelop getDoctorFullInfolist(
            @ApiParam(name = "name", value = "医生姓名")
            @RequestParam(value = "orgId", required = true) String name,
            @ApiParam(name = "idcard", value = "医生身份证号")
            @RequestParam(value = "orgId", required = true) String idcard,
            @ApiParam(name = "orgCode", value = "医院代码")
            @RequestParam(value = "orgCode", required = true) String orgCode,
            @ApiParam(name = "doctorStatus", value = "医生是否生效")
            @RequestParam(value = "doctorId", required = true) String doctorStatus,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts,
            @ApiParam(name = "page", value = "分页大小", required = true, defaultValue = "1")
            @RequestParam(value = "page") int page,
            @ApiParam(name = "size", value = "页码", required = true, defaultValue = "15")
            @RequestParam(value = "size") int size) throws Exception {
        List<Map<String,Object>> map = baseDoctorService.getDoctorFullInfo(name, idcard,orgCode,doctorStatus);
        return success(map.toString());
    }


    /**
     * 获取医生 所属机构/部门/职称/职务 树形结构数据
     * @return
     * @throws Exception
     */
    @PostMapping(value = BaseRequestMapping.BaseDoctor.docOrgTreeInfo)
    @ApiOperation(value = "获取医生所属机构树形结构数据")
    public Envelop getDoctorOrgTree() throws Exception {
        String data = baseDoctorService.getOrgTree();
        return success(data);
    }


}
