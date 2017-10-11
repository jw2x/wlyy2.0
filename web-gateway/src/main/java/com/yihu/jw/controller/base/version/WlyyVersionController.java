package com.yihu.jw.controller.base.version;

import com.yihu.jw.fegin.base.version.WlyyVersionFegin;
import com.yihu.jw.restmodel.base.version.BaseVersionContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.exception.business.JiWeiException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by chenweida on 2017/6/16.
 */

@RestController
@RequestMapping("{version}/"+BaseVersionContants.api_common)
@Api(value = "i健康APP版本模块", description = "i健康APP版本模块接口管理")
public class WlyyVersionController  extends EnvelopRestController {

    @Autowired
    private WlyyVersionFegin wlyyVersionFegin;
    @Autowired
    private Tracer tracer;

    @PostMapping(value = BaseVersionContants.WlyyVersion.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建i健康APP版本", notes = "创建单个i健康APP版本")
    public Envelop createWlyyVersion(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        return wlyyVersionFegin.create(jsonData);
    }

    @PutMapping(value = BaseVersionContants.WlyyVersion.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改i健康APP版本", notes = "修改i健康APP版本")
    public Envelop updateWlyyVersion(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        return wlyyVersionFegin.update(jsonData);
    }
    @DeleteMapping(value = BaseVersionContants.WlyyVersion.api_delete)
    @ApiOperation(value = "删除i健康APP版本", notes = "删除i健康APP版本")
    public Envelop deleteWlyyVersion(
            @ApiParam(name = "codes", value = "codes")
            @PathVariable(value = "codes", required = true) String codes,
            @ApiParam(name = "userCode", value = "userCode")
            @RequestParam(value = "userCode", required = true) String userCode,
            @ApiParam(name = "userName", value = "userName")
            @RequestParam(value = "userName", required = true) String userName) throws JiWeiException {
        return wlyyVersionFegin.delete(codes,userCode,userName);
    }

    @GetMapping(value = BaseVersionContants.WlyyVersion.api_getByCode)
    @ApiOperation(value = "根据code查找i健康APP版本", notes = "根据code查找i健康APP版本")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @PathVariable(value = "code", required = true) String code
    ) {
        return wlyyVersionFegin.findByCode(code);
    }


    @RequestMapping(value = BaseVersionContants.WlyyVersion.api_getList, method = RequestMethod.GET)
    @ApiOperation(value = "获取i健康APP版本列表(分页)")
    public Envelop getWlyyVersions(
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
            @RequestParam(value = "page", required = false) int page) throws Exception {
        String filterStr = "";
        if(StringUtils.isNotBlank(filters)){
            JSONObject jsonResult = new JSONObject(filters);
            if(jsonResult.has("name")){
                filterStr+="name?"+jsonResult.get("name")+";";
            }
            if(jsonResult.has("saasId")){
                filterStr+="saasId="+jsonResult.get("saasId")+";";
            }
        }
        return wlyyVersionFegin.getList(fields, filterStr, sorts, size, page);
    }


    @GetMapping(value = BaseVersionContants.WlyyVersion.api_getListNoPage)
    @ApiOperation(value = "获取i健康APP版本列表，不分页")
    public Envelop getAppsNoPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "code,name,saasId,parentCode,remark")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        return wlyyVersionFegin.getListNoPage(fields, filters, sorts);
    }

}
