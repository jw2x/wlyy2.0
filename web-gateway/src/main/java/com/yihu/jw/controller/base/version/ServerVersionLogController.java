package com.yihu.jw.controller.base.version;

import com.yihu.jw.commnon.base.base.BaseVersionContants;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenweida on 2017/5/19.
 */
@RestController
@RequestMapping("{version}/"+ BaseVersionContants.api_common)
@Api(value = "服务器版本日志模块", description = "服务器版本日志模块接口管理")
public class ServerVersionLogController extends EnvelopRestController {
    /*@Autowired
    private ServerVersionLogService serverVersionLogService;*/

    /*@PostMapping(value = BaseVersionContants.BaseServerVersionLog.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建服务器版本日志", notes = "创建单个服务器版本日志")
    public Envelop createBaseServerVersionLog(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            BaseServerVersionLog baseServerVersionLog = toEntity(jsonData, BaseServerVersionLog.class);
            return Envelop.getSuccess(BaseVersionContants.BaseServerVersionLog.message_success_create, serverVersionLogService.createBaseServerVersionLog(baseServerVersionLog));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @DeleteMapping(value = BaseVersionContants.BaseServerVersionLog.api_delete, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "删除服务器版本日志", notes = "删除服务器版本日志")
    public Envelop deleteBaseServerVersionLog(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code) {
        try {
            serverVersionLogService.deleteBaseServerVersionLog(code);
            return Envelop.getSuccess(BaseVersionContants.BaseServerVersionLog.message_success_delete );
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @GetMapping(value = BaseVersionContants.BaseServerVersionLog.api_getByCode, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "根据code查找服务器版本日志", notes = "根据code查找服务器版本日志")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code
    ) {
        try {
            return Envelop.getSuccess(BaseVersionContants.BaseServerVersionLog.message_success_find, serverVersionLogService.findByCode(code));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }*/


   /* @RequestMapping(value = BaseVersionContants.BaseServerVersionLog.api_getBaseServerVersionLog, method = RequestMethod.GET)
    @ApiOperation(value = "获取服务器版本日志列表(分页)")
    public Envelop getBaseServerVersionLogs(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "code,name,saasId,parentCode,remark")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            //code like 1,name大于aa ,code 等于1 , defaultValue = "code?1;name>aa;code=1"
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts,
            @ApiParam(name = "size", value = "分页大小", defaultValue = "15")
            @RequestParam(value = "size", required = false) int size,
            @ApiParam(name = "page", value = "页码", defaultValue = "1")
            @RequestParam(value = "page", required = false) int page,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        //得到list数据
        List<BaseServerVersionLog> list = serverVersionLogService.search(fields, filters, sorts, page, size);
        //获取总数
        long count=serverVersionLogService.getCount(filters);
        //封装头信息
        pagedResponse(request, response, count, page, size);
        //封装返回格式
        List<MBaseServerVersionLog> mBaseServerVersionLogs = convertToModels(list, new ArrayList<>(list.size()), MBaseServerVersionLog.class, fields);

        return Envelop.getSuccessListWithPage(BaseVersionContants.BaseServerVersionLog.message_success_find_BaseServerVersionLog,mBaseServerVersionLogs, page, size,count);
    }


    @GetMapping(value = BaseVersionContants.BaseServerVersionLog.api_getBaseServerVersionLogNoPage)
    @ApiOperation(value = "获取服务器版本日志列表，不分页")
    public Envelop getAppsNoPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "code,name,saasId,parentCode,remark")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        //得到list数据
        List<BaseServerVersionLog> list = serverVersionLogService.search(fields,filters,sorts);
        //封装返回格式
        List<MBaseServerVersionLog> mBaseServerVersionLogs = convertToModels(list, new ArrayList<>(list.size()), MBaseServerVersionLog.class, fields);
        return Envelop.getSuccessList(BaseVersionContants.BaseServerVersionLog.message_success_find_BaseServerVersionLog,mBaseServerVersionLogs);
    }
*/

}
