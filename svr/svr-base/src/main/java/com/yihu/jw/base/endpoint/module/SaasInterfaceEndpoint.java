package com.yihu.jw.base.endpoint.module;

import com.yihu.jw.base.service.module.SaasInterfaceService;
import com.yihu.jw.base.util.ErrorCodeUtil;
import com.yihu.jw.entity.base.module.SaasInterfaceDO;
import com.yihu.jw.exception.code.BaseErrorCode;
import com.yihu.jw.restmodel.base.module.SaasInterfaceVO;
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
 * 租户接口
 * @author yeshijie on 2018/10/11.
 */
@RestController
@RequestMapping(value = BaseRequestMapping.SaasInterface.PREFIX)
@Api(value = "租户接口管理", description = "租户接口管理服务接口", tags = {"基础服务 - 租户接口管理服务接口"})
public class SaasInterfaceEndpoint extends EnvelopRestEndpoint {

    @Autowired
    private SaasInterfaceService interfaceService;
    @Autowired
    private ErrorCodeUtil errorCodeUtil;

    @PostMapping(value = BaseRequestMapping.SaasInterface.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建")
    public ObjEnvelop<SaasInterfaceVO> create (
            @ApiParam(name = "jsonData", value = "Json数据", required = true)
            @RequestParam String jsonData) throws Exception {
        SaasInterfaceDO interfaceDO = toEntity(jsonData, SaasInterfaceDO.class);

        interfaceDO = interfaceService.addInterface(interfaceDO);
        return success(interfaceDO, SaasInterfaceVO.class);
    }

    @GetMapping(value = BaseRequestMapping.SaasInterface.FINDBYID)
    @ApiOperation(value = "按id查找")
    public ObjEnvelop<SaasInterfaceVO> findById (
            @ApiParam(name = "id", value = "id", required = true)
            @RequestParam(value = "id") String id) throws Exception {
        SaasInterfaceDO interfaceDO = interfaceService.findById(id);
        return success(interfaceDO, SaasInterfaceVO.class);
    }

    @PostMapping(value = BaseRequestMapping.SaasInterface.STATUS)
    @ApiOperation(value = "启用/暂停")
    public Envelop status(
            @ApiParam(name = "id", value = "id", required = true)
            @RequestParam(value = "id") String id,
            @ApiParam(name = "status", value = "1生效，0失效", required = true)
            @RequestParam(value = "status") Integer status) {
        interfaceService.updateStatus(id, status);
        return success("修改成功");
    }


    @PostMapping(value = BaseRequestMapping.SaasInterface.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
            @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
            @RequestParam(value = "ids") String ids) {
        interfaceService.delete(ids.split(","));
        return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.SaasInterface.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public ObjEnvelop<SaasInterfaceVO> update (
            @ApiParam(name = "jsonData", value = "Json数据", required = true)
            @RequestParam String jsonData) throws Exception {
        SaasInterfaceDO interfaceDO = toEntity(jsonData, SaasInterfaceDO.class);
        if (null == interfaceDO.getId()) {
            return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.Common.ID_IS_NULL), ObjEnvelop.class);
        }
        interfaceDO = interfaceService.addInterface(interfaceDO);
        return success(interfaceDO, SaasInterfaceVO.class);
    }

    @GetMapping(value = BaseRequestMapping.SaasInterface.PAGE)
    @ApiOperation(value = "获取分页")
    public PageEnvelop<SaasInterfaceVO> page (
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
        List<SaasInterfaceDO> modules = interfaceService.search(fields, filters, sorts, page, size);
        int count = (int)interfaceService.getCount(filters);
        return success(modules, count, page, size, SaasInterfaceVO.class);
    }

    @GetMapping(value = BaseRequestMapping.SaasInterface.LIST)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<SaasInterfaceVO> list (
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        List<SaasInterfaceDO> interfaceDOs = interfaceService.search(fields, filters, sorts);
        return success(interfaceDOs, SaasInterfaceVO.class);
    }
}
