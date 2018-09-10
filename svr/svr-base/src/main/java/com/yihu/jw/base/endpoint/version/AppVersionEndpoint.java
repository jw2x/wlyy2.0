package com.yihu.jw.base.endpoint.version;

import com.yihu.jw.base.service.version.AppVersionService;
import com.yihu.jw.entity.base.version.AppVersionDO;
import com.yihu.jw.restmodel.base.version.AppVersionVO;
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
* app版本号表控制器
*
* @version
* <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年09月07日 	Created
 *
 * </pre>
* @since 1.
*/
@RestController
@RequestMapping(value = BaseRequestMapping.AppVersion.PREFIX)
@Api(value = "app版本号表管理", description = "app版本号表管理服务接口", tags = {"wlyy基础服务 - app版本号表管理服务接口"})
public class AppVersionEndpoint extends EnvelopRestEndpoint {

@Autowired
private AppVersionService appVersionService;

@PostMapping(value = BaseRequestMapping.AppVersion.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
@ApiOperation(value = "创建")
public ObjEnvelop<AppVersionVO> create (
    @ApiParam(name = "json_data", value = "Json数据", required = true)
    @RequestBody String jsonData) throws Exception {
    AppVersionDO appVersion = toEntity(jsonData, AppVersionDO.class);
    appVersion = appVersionService.save(appVersion);
    return success(appVersion, AppVersionVO.class);
    }

    @PostMapping(value = BaseRequestMapping.AppVersion.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
    @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
    @RequestParam(value = "ids") String ids) {
    appVersionService.delete(ids.split(","));
    return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.AppVersion.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public ObjEnvelop<AppVersionVO> update (
        @ApiParam(name = "json_data", value = "Json数据", required = true)
        @RequestBody String jsonData) throws Exception {
        AppVersionDO appVersion = toEntity(jsonData, AppVersionDO.class);
        if (null == appVersion.getId()) {
        return failed("ID不能为空", ObjEnvelop.class);
        }
        appVersion = appVersionService.save(appVersion);
        return success(appVersion, AppVersionVO.class);
        }

        @GetMapping(value = BaseRequestMapping.AppVersion.PAGE)
        @ApiOperation(value = "获取分页")
        public PageEnvelop<AppVersionVO> page (
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
            List<AppVersionDO> appVersions = appVersionService.search(fields, filters, sorts, page, size);
                int count = (int)appVersionService.getCount(filters);
                return success(appVersions, count, page, size, AppVersionVO.class);
         }

         @GetMapping(value = BaseRequestMapping.AppVersion.LIST)
         @ApiOperation(value = "获取列表")
         public ListEnvelop<AppVersionVO> list (
             @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
             @RequestParam(value = "fields", required = false) String fields,
             @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
             @RequestParam(value = "filters", required = false) String filters,
             @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
             @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
             List<AppVersionDO> appVersions = appVersionService.search(fields, filters, sorts);
                  return success(appVersions, AppVersionVO.class);
         }

 }
