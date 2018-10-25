package com.yihu.jw.base.endpoint.doctor;

import com.yihu.jw.base.service.doctor.BaseModuleRoleService;
import com.yihu.jw.entity.base.doctor.BaseModuleRoleDO;
import com.yihu.jw.restmodel.base.doctor.BaseModuleRoleVO;
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
* 业务模块与业务模块角色关联信息控制器
*
* @version
* <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年10月25日 	Created
 *
 * </pre>
* @since 1.
*/
@RestController
@RequestMapping(value = BaseRequestMapping.BaseModuleRole.PREFIX)
@Api(value = "业务模块与业务模块角色关联信息管理", description = "业务模块与业务模块角色关联信息管理服务接口", tags = {"wlyy基础服务 - 业务模块与业务模块角色关联信息管理服务接口"})
public class BaseModuleRoleEndpoint extends EnvelopRestEndpoint {

@Autowired
private BaseModuleRoleService baseModuleRoleService;

@PostMapping(value = BaseRequestMapping.BaseModuleRole.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
@ApiOperation(value = "创建")
public ObjEnvelop<BaseModuleRoleVO> create (
    @ApiParam(name = "json_data", value = "Json数据", required = true)
    @RequestBody String jsonData) throws Exception {
    BaseModuleRoleDO baseModuleRole = toEntity(jsonData, BaseModuleRoleDO.class);
    baseModuleRole = baseModuleRoleService.save(baseModuleRole);
    return success(baseModuleRole, BaseModuleRoleVO.class);
    }

    @PostMapping(value = BaseRequestMapping.BaseModuleRole.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
    @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
    @RequestParam(value = "ids") String ids) {
    baseModuleRoleService.delete(ids.split(","));
    return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.BaseModuleRole.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public ObjEnvelop<BaseModuleRoleVO> update (
        @ApiParam(name = "json_data", value = "Json数据", required = true)
        @RequestBody String jsonData) throws Exception {
        BaseModuleRoleDO baseModuleRole = toEntity(jsonData, BaseModuleRoleDO.class);
        if (null == baseModuleRole.getId()) {
        return failed("ID不能为空", ObjEnvelop.class);
        }
        baseModuleRole = baseModuleRoleService.save(baseModuleRole);
        return success(baseModuleRole, BaseModuleRoleVO.class);
        }

        @GetMapping(value = BaseRequestMapping.BaseModuleRole.PAGE)
        @ApiOperation(value = "获取分页")
        public PageEnvelop<BaseModuleRoleVO> page (
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
            List<BaseModuleRoleDO> baseModuleRoles = baseModuleRoleService.search(fields, filters, sorts, page, size);
                int count = (int)baseModuleRoleService.getCount(filters);
                return success(baseModuleRoles, count, page, size, BaseModuleRoleVO.class);
         }

         @GetMapping(value = BaseRequestMapping.BaseModuleRole.LIST)
         @ApiOperation(value = "获取列表")
         public ListEnvelop<BaseModuleRoleVO> list (
             @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
             @RequestParam(value = "fields", required = false) String fields,
             @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
             @RequestParam(value = "filters", required = false) String filters,
             @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
             @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
             List<BaseModuleRoleDO> baseModuleRoles = baseModuleRoleService.search(fields, filters, sorts);
                  return success(baseModuleRoles, BaseModuleRoleVO.class);
         }

 }
