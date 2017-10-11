package com.yihu.jw.controller.base.version;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yihu.jw.fegin.base.version.UserUrlVersionFegin;
import com.yihu.jw.restmodel.base.version.BaseVersionContants;
import com.yihu.jw.restmodel.common.Envelop;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenweida on 2017/6/20.
 */
@RestController
@RequestMapping("{version}"+BaseVersionContants.api_common)
@Api(value = "后台用户版本模块", description = "后台用户版本模块管理")
public class UserUrlVersionController {

    @Autowired
    private UserUrlVersionFegin userUrlVersionFegin;

    @Autowired
    private Tracer tracer;


    @GetMapping(value = BaseVersionContants.UserUrlVersion.api_getListNoPage)
    @ApiOperation(value = "获取用户版本列表，不分页")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop getListNoPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "code,name,saasId,parentCode,remark")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        String filterStr = "";
        if(StringUtils.isNotBlank(filters)){
            filters = filters.replaceAll("=", ":");
            JSONObject jsonResult = new JSONObject(filters);
            if(jsonResult.has("saasId")){
                filterStr+="saasId="+jsonResult.get("saasId")+";";
            }
            if(jsonResult.has("bsvCode")){
                filterStr+="bsvCode="+jsonResult.get("bsvCode")+";";
            }
        }
        return userUrlVersionFegin.getListNoPage(fields, filterStr, sorts);
    }


    @GetMapping(value = BaseVersionContants.UserUrlVersion.api_changeUserVersion)
    @ApiOperation(value = "更改后台用户版本")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop changeUserVersion(
            @ApiParam(name = "serverCode",value="后台版本code")
            @RequestParam(value = "serverCode") String serverCode,
            @ApiParam(name = "userCodes", value = "更改的用户codes")
            @RequestParam(value = "userCodes") String userCodes,
            @ApiParam(name = "userCode", value = "修改人code")
            @RequestParam(value = "userCode") String userCode,
            @ApiParam(name = "userName", value = "修改人")
            @RequestParam(value = "userName") String userName,
            @ApiParam(name = "saasId")
            @RequestParam(value = "saasId") String saasId
    ) throws Exception {
        return userUrlVersionFegin.changeUserVersion(serverCode, userCodes, userCode,userName,saasId);
    }
}
