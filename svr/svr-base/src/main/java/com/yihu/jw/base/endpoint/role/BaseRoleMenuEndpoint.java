package com.yihu.jw.base.endpoint.role;

import com.yihu.jw.base.service.role.BaseRoleMenuService;
import com.yihu.jw.entity.base.role.BaseRoleMenuDO;
import com.yihu.jw.restmodel.base.role.BaseRoleMenuVO;
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
* 角色菜单表控制器
*
* @version
* <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年10月23日 	Created
 *
 * </pre>
* @since 1.
*/
@RestController
@RequestMapping(value = BaseRequestMapping.BaseRoleMenu.PREFIX)
@Api(value = "角色菜单表管理", description = "角色菜单表管理服务接口", tags = {"wlyy基础服务 - 角色菜单表管理服务接口"})
public class BaseRoleMenuEndpoint extends EnvelopRestEndpoint {

@Autowired
private BaseRoleMenuService baseRoleMenuService;

@PostMapping(value = BaseRequestMapping.BaseRoleMenu.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
@ApiOperation(value = "创建")
public ObjEnvelop<BaseRoleMenuVO> create (
    @ApiParam(name = "json_data", value = "Json数据", required = true)
    @RequestBody String jsonData) throws Exception {
    BaseRoleMenuDO baseRoleMenu = toEntity(jsonData, BaseRoleMenuDO.class);
    baseRoleMenu = baseRoleMenuService.save(baseRoleMenu);
    return success(baseRoleMenu, BaseRoleMenuVO.class);
    }

    @PostMapping(value = BaseRequestMapping.BaseRoleMenu.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
    @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
    @RequestParam(value = "ids") String ids) {
    baseRoleMenuService.delete(ids.split(","));
    return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.BaseRoleMenu.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public ObjEnvelop<BaseRoleMenuVO> update (
        @ApiParam(name = "json_data", value = "Json数据", required = true)
        @RequestBody String jsonData) throws Exception {
        BaseRoleMenuDO baseRoleMenu = toEntity(jsonData, BaseRoleMenuDO.class);
        if (null == baseRoleMenu.getId()) {
        return failed("ID不能为空", ObjEnvelop.class);
        }
        baseRoleMenu = baseRoleMenuService.save(baseRoleMenu);
        return success(baseRoleMenu, BaseRoleMenuVO.class);
        }

        @GetMapping(value = BaseRequestMapping.BaseRoleMenu.PAGE)
        @ApiOperation(value = "获取分页")
        public PageEnvelop<BaseRoleMenuVO> page (
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
            List<BaseRoleMenuDO> baseRoleMenus = baseRoleMenuService.search(fields, filters, sorts, page, size);
                int count = (int)baseRoleMenuService.getCount(filters);
                return success(baseRoleMenus, count, page, size, BaseRoleMenuVO.class);
         }

         @GetMapping(value = BaseRequestMapping.BaseRoleMenu.LIST)
         @ApiOperation(value = "获取列表")
         public ListEnvelop<BaseRoleMenuVO> list (
             @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
             @RequestParam(value = "fields", required = false) String fields,
             @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
             @RequestParam(value = "filters", required = false) String filters,
             @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
             @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
             List<BaseRoleMenuDO> baseRoleMenus = baseRoleMenuService.search(fields, filters, sorts);
                  return success(baseRoleMenus, BaseRoleMenuVO.class);
         }

 }
