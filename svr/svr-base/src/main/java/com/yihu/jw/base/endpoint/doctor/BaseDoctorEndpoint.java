package com.yihu.jw.base.endpoint.doctor;

import com.alibaba.fastjson.JSONObject;
import com.yihu.jw.base.service.doctor.BaseDoctorService;
import com.yihu.jw.base.service.org.OrgTreeService;
import com.yihu.jw.base.util.ConstantUtils;
import com.yihu.jw.base.util.JavaBeanUtils;
import com.yihu.jw.restmodel.base.doctor.BaseDoctorVO;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.ListEnvelop;
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

    @Autowired
    private OrgTreeService orgTreeService;

    @PostMapping(value = BaseRequestMapping.BaseDoctor.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "新增医生")
    public Envelop create(
            @ApiParam(name = "jsonData", value = "Json数据", required = true)
            @RequestParam String jsonData) throws Exception {
        JSONObject jsonObject = null;
        String str = baseDoctorService.createDoctor(jsonData);
        jsonObject = JSONObject.parseObject(str);
        if (jsonObject.getString("response").equalsIgnoreCase(ConstantUtils.FAIL)) {
            return failed(jsonObject.getString("msg"));
        }
        return success(jsonObject.getString("msg"));
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
    @ApiOperation(value = "更新医生")
    public Envelop update(
            @ApiParam(name = "jsonData", value = "Json数据", required = true)
            @RequestParam String jsonData) throws Exception {
        String str = baseDoctorService.updateDoctor(jsonData);
        JSONObject jsonObject = JSONObject.parseObject(str);
        if (jsonObject.getString("response").equalsIgnoreCase(ConstantUtils.FAIL)) {
            return failed(jsonObject.getString("msg"));
        }
        return success(jsonObject.getString("response"));
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
     *
     * @param orgId
     * @param doctorId
     * @return
     * @throws Exception
     */
    @PostMapping(value = BaseRequestMapping.BaseDoctor.DOCINFO)
    @ApiOperation(value = "获取单个医生及其执业信息")
    public Envelop doctorHosplist(
            @ApiParam(name = "orgId", value = "医院id")
            @RequestParam(value = "orgId", required = true) String orgId,
            @ApiParam(name = "doctorId", value = "医生id")
            @RequestParam(value = "doctorId", required = true) String doctorId) throws Exception {
        Map<String, Object> map = baseDoctorService.getOneDoctorInfo(orgId, doctorId);
        return success(map.toString());
    }

    /**
     * 生效或失效单个医生
     *
     * @param doctorCode
     * @param del
     * @return
     * @throws Exception
     */
    @PostMapping(value = BaseRequestMapping.BaseDoctor.enableOrDis)
    @ApiOperation(value = "生效或失效单个医生")
    public Envelop enableOrDisableDoctor(
            @ApiParam(name = "doctorCode", value = "医生标识")
            @RequestParam(value = "doctorCode", required = true) String doctorCode,
            @ApiParam(name = "del", value = "生效或失效标识")
            @RequestParam(value = "del", required = true) String del) throws Exception {
        String str = baseDoctorService.enableOrDisableDoctor(doctorCode, del);
        JSONObject jsonObject = JSONObject.parseObject(str);
        if (jsonObject.getString("response").equalsIgnoreCase(ConstantUtils.FAIL)) {
            return failed(jsonObject.getString("msg"));
        }
        return success(jsonObject.getString("response"));
    }

    /*
     * 医生信息（基本信息 + 医院执业信息）列表
     * @param name
     * @param idcard
     * @param orgCode
     * @param doctorStatus
     * @return
     * @throws Exception
     */
    @PostMapping(value = BaseRequestMapping.BaseDoctor.docFullInfo)
    @ApiOperation(value = "获取医生基础信息列表")
    public PageEnvelop getDoctorFullInfolist(
            @ApiParam(name = "nameOrIdcard", value = "医生姓名或医生身份证号")
            @RequestParam(value = "nameOrIdcard", required = false) String nameOrIdcard,
            @RequestParam(value = "orgCode", required = false) String orgCode,
            @ApiParam(name = "doctorStatus", value = "医生是否生效")
            @RequestParam(value = "doctorStatus", required = false) String doctorStatus,
            @ApiParam(name = "page", value = "分页大小", required = true, defaultValue = "1")
            @RequestParam(value = "page") int page,
            @ApiParam(name = "size", value = "页码", required = true, defaultValue = "15")
            @RequestParam(value = "size") int size) throws Exception {
        JSONObject result = baseDoctorService.queryDoctorListFullInfo(nameOrIdcard,orgCode, doctorStatus,page,size);
        return success(result.getJSONArray("msg"),result.getInteger("count"),page,size);
    }


    /**
     * 获取医生新增时选择的机构列表
     *
     * @param townCode
     * @return
     * @throws Exception
     */
    @GetMapping(value = BaseRequestMapping.BaseDoctor.getOrgListByTown)
    @ApiOperation(value = "根据区域获取机构列表")
    public Envelop enableOrDisableDoctor(
            @ApiParam(name = "townCode", value = "townCode")
            @RequestParam(value = "townCode", required = true) String townCode) throws Exception {
        List<Map<String, Object>> result = orgTreeService.findOrgListByParentCode(townCode);
        return success(JavaBeanUtils.getInstance().mapListJson(result));
    }

    /**
     * 获取医生 机构/职务 树形结构数据
     *
     * @param doctorCode
     * @return
     * @throws Exception
     */
    @GetMapping(value = BaseRequestMapping.BaseDoctor.docOrgDutyTreeInfo)
    @ApiOperation(value = "获取医生所属机构树形结构数据")
    public Envelop getOrgDutyTree(
            @ApiParam(name = "doctorCode", value = "doctorCode")
            @RequestParam(value = "doctorCode", required = true) String doctorCode) throws Exception {
        return success(baseDoctorService.getDoctorDutyTree(doctorCode));
    }

    /**
     * 获取医生 机构/科室 树形结构数据
     *
     * @param doctorCode
     * @return
     * @throws Exception
     */
    @GetMapping(value = BaseRequestMapping.BaseDoctor.docOrgDeptTreeInfo)
    @ApiOperation(value = "获取医生所属机构树形结构数据")
    public Envelop getOrgDeptTree(
            @ApiParam(name = "doctorCode", value = "doctorCode")
            @RequestParam(value = "doctorCode", required = true) String doctorCode) throws Exception {
        return success(baseDoctorService.getDoctorDeptTree(doctorCode));
    }

    @GetMapping(value = BaseRequestMapping.BaseDoctor.getDoctorListByDept)
    @ApiOperation(value = "获取某一科室下的医生列表")
    public Envelop getDoctorListByDept(
            @ApiParam(name = "deptCode", value = "科室代码", required = true)
            @RequestParam String deptCode) throws Exception {
        JSONObject jsonObject = baseDoctorService.getDoctorListByDept(deptCode);
        if (jsonObject.getString("response").equalsIgnoreCase(ConstantUtils.FAIL)) {
            return failed(jsonObject.getString("msg"));
        }
        return success(jsonObject.getString("msg"));
    }

}
