package com.yihu.jw.base.endpoint.role;

import com.yihu.jw.base.service.role.MenuService;
import com.yihu.jw.base.util.ErrorCodeUtil;
import com.yihu.jw.entity.base.role.MenuDO;
import com.yihu.jw.exception.code.BaseErrorCode;
import com.yihu.jw.restmodel.base.role.MenuVO;
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
 * @author yeshijie on 2018/9/26.
 */
@RestController
@RequestMapping(value = BaseRequestMapping.Menu.PREFIX)
@Api(value = "菜单管理", description = "菜单管理服务接口", tags = {"基础服务 - 菜单管理服务接口"})
public class MenuEndpoint extends EnvelopRestEndpoint {


    @Autowired
    private MenuService menuService;
    @Autowired
    private ErrorCodeUtil errorCodeUtil;

    @PostMapping(value = BaseRequestMapping.Menu.CREATE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建")
    public ObjEnvelop<MenuVO> create (
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        MenuDO menuDO = toEntity(jsonData, MenuDO.class);
        menuDO = menuService.save(menuDO);
        return success(menuDO, MenuVO.class);
    }

    @PostMapping(value = BaseRequestMapping.Menu.STATUS)
    @ApiOperation(value = "生效/失效")
    public Envelop delete(
            @ApiParam(name = "id", value = "id", required = true)
            @RequestParam(value = "id") String id,
            @ApiParam(name = "status", value = "1生效，0失效", required = true)
            @RequestParam(value = "status") Integer status) {
        menuService.updateStatus(id, status);
        return success("修改成功");
    }

    @PostMapping(value = BaseRequestMapping.Menu.UPDATE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public Envelop update (
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        MenuDO menuDO = toEntity(jsonData, MenuDO.class);
        if (null == menuDO.getId()) {
            return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.Common.id_is_null), Envelop.class);
        }
        menuDO = menuService.save(menuDO);
        return success(menuDO);
    }

    @GetMapping(value = BaseRequestMapping.Menu.PAGE)
    @ApiOperation(value = "获取分页")
    public PageEnvelop<MenuVO> page (
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
        List<MenuDO> menuDOS = menuService.search(fields, filters, sorts, page, size);
        int count = (int)menuService.getCount(filters);
        return success(menuDOS, count, page, size, MenuVO.class);
    }

    @GetMapping(value = BaseRequestMapping.Menu.LIST)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<MenuVO> list (
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        List<MenuDO> menuDOS = menuService.search(fields, filters, sorts);
        return success(menuDOS, MenuVO.class);
    }


}
