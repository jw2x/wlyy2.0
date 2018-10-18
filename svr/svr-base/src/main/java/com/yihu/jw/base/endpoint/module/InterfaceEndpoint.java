package com.yihu.jw.base.endpoint.module;

import com.yihu.jw.base.service.module.InterfaceService;
import com.yihu.jw.base.util.ErrorCodeUtil;
import com.yihu.jw.entity.base.module.InterfaceDO;
import com.yihu.jw.exception.code.BaseErrorCode;
import com.yihu.jw.restmodel.base.module.InterfaceVO;
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
 * 接口
 * @author yeshijie on 2018/9/29.
 */
@RestController
@RequestMapping(value = BaseRequestMapping.Interface.PREFIX)
@Api(value = "接口管理", description = "接口管理服务接口", tags = {"基础服务 - 接口管理服务接口"})
public class InterfaceEndpoint extends EnvelopRestEndpoint {

    @Autowired
    private InterfaceService interfaceService;
    @Autowired
    private ErrorCodeUtil errorCodeUtil;

    @PostMapping(value = BaseRequestMapping.Interface.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建")
    public ObjEnvelop<InterfaceVO> create (
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestParam String jsonData) throws Exception {
        InterfaceDO interfaceDO = toEntity(jsonData, InterfaceDO.class);

        interfaceDO = interfaceService.addInterface(interfaceDO);
        return success(interfaceDO, InterfaceVO.class);
    }

    @GetMapping(value = BaseRequestMapping.Interface.FINDBYID)
    @ApiOperation(value = "按id查找")
    public ObjEnvelop<InterfaceVO> findById (
            @ApiParam(name = "id", value = "id", required = true)
            @RequestParam(value = "id") String id) throws Exception {
        InterfaceDO interfaceDO = interfaceService.findById(id);
        return success(interfaceDO, InterfaceVO.class);
    }

    @PostMapping(value = BaseRequestMapping.Interface.STATUS)
    @ApiOperation(value = "生效/失效")
    public Envelop status(
            @ApiParam(name = "id", value = "id", required = true)
            @RequestParam(value = "id") String id,
            @ApiParam(name = "status", value = "1生效，0失效", required = true)
            @RequestParam(value = "status") Integer status) {
        interfaceService.updateStatus(id, status);
        return success("修改成功");
    }


    @PostMapping(value = BaseRequestMapping.Interface.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
            @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
            @RequestParam(value = "ids") String ids) {
        interfaceService.delete(ids.split(","));
        return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.Interface.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public ObjEnvelop<InterfaceVO> update (
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestParam String jsonData) throws Exception {
        InterfaceDO interfaceDO = toEntity(jsonData, InterfaceDO.class);
        if (null == interfaceDO.getId()) {
            return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.Common.ID_IS_NULL), ObjEnvelop.class);
        }
        interfaceDO = interfaceService.addInterface(interfaceDO);
        return success(interfaceDO, InterfaceVO.class);
    }

    @GetMapping(value = BaseRequestMapping.Interface.PAGE)
    @ApiOperation(value = "获取分页")
    public PageEnvelop<InterfaceVO> page (
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
        List<InterfaceDO> modules = interfaceService.search(fields, filters, sorts, page, size);
        int count = (int)interfaceService.getCount(filters);
        return success(modules, count, page, size, InterfaceVO.class);
    }

    @GetMapping(value = BaseRequestMapping.Interface.LIST)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<InterfaceVO> list (
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        List<InterfaceDO> interfaceDOs = interfaceService.search(fields, filters, sorts);
        return success(interfaceDOs, InterfaceVO.class);
    }
}
