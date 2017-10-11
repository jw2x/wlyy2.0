package com.yihu.jw.controller.doctorManage;

import com.yihu.jw.restmodel.doctorManage.DoctorContants;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("{version}/"+ DoctorContants.api_common)
@Api(description = "微信配置")
public class DoctorController {

    private Logger logger= LoggerFactory.getLogger(DoctorController.class);

  /*  @Autowired
    private DoctorFegin fegin;

    @Autowired
    private Tracer tracer;

    @ApiVersion(1)
    @RequestMapping(value = DoctorContants.Doctor.api_getList, method = RequestMethod.GET)
    @ApiOperation(value = "获取医生列表(分页)")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop getList(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,name,saasId,appId,appSecret,baseUrl,remark")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts,
            @ApiParam(name = "size", value = "分页大小", defaultValue = "15")
            @RequestParam(value = "size", required = false) int size,
            @ApiParam(name = "page", value = "页码", defaultValue = "1")
            @RequestParam(value = "page", required = false) int page) throws Exception {
        String filterStr = "";
        if(StringUtils.isNotBlank(filters)){
            filters = filters.replaceAll("=", ":");
            JSONObject jsonResult = new JSONObject(filters);
            if(jsonResult.has("name")){
                filterStr+="name?"+jsonResult.get("name")+";";
            }
            if(jsonResult.has("saasId")){
                filterStr += "saasId?" + jsonResult.get("saasId")+";";
            }
        }
        Envelop envelop = fegin.getList(fields,filterStr,sorts,size,page);
        return envelop;
    }

    @ApiVersion(1)
    @GetMapping(value = DoctorContants.Doctor.api_getByCode)
    @ApiOperation(value = "根据code查找", notes = "根据code查找")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @PathVariable(value = "code", required = true) String code
    ) throws JiWeiException {
        return fegin.findByCode(code);
    }*/

}
