package com.yihu.jw.manage.controller.doctorManage;

import com.yihu.jw.restmodel.doctorManage.DoctorContants;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/6/13 0013.
 */
@RestController
@RequestMapping(DoctorContants.api_common)
@Api(description = "医生管理")
public class DoctorController {

/*    @Autowired
    private DoctorService doctorService;*/

   /* @GetMapping(DoctorContants.Doctor.api_getList)
    @ApiOperation(value = "分页获取医生列表")
    public Envelop list(
            @ApiParam(name = "name", value = "医生姓名", required = false) @RequestParam(required = false, name = "name") String name,
            @ApiParam(name = "saasId", required = false) @RequestParam(required = false, name = "saasId") String saasId,
            @ApiParam(name = "sorts", value = "排序", required = false) @RequestParam(required = false, name = "sorts") String sorts,
            @ApiParam(name = "start", value = "当前页", required = false) @RequestParam(required = false, name = "start", defaultValue = "1") Integer start,
            @ApiParam(name = "length", value = "每页显示条数", required = false) @RequestParam(required = false, name = "length", defaultValue = "10") Integer length
    ) {
        try {
            start=start/length+1;
            Map<String,String> map = new HashMap<String,String>();
            map.put("saasId",saasId);
            map.put("sorts",sorts);
            Envelop envelop = doctorService.list(length, start,map);
            return envelop;
        } catch (Exception e) {
            return Envelop.getError("获取信息失败:" + e.getMessage(), -1);
        }
    }*/



   /* @GetMapping(value = "/wechatConfig/{code}")
    @ApiOperation(value = "根据code查找微信配置", notes = "根据code查找微信配置")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
           @PathVariable String code
    ) {
        Envelop envelop = doctorService.findByCode(code);
        return envelop;
    }

    @GetMapping("wechatConfig/listNoPage")
    @ApiOperation(value = "获取微信配置列表")
    public Envelop listNoPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,name,saasId,appId,appSecret,baseUrl,remark")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts){
        return doctorService.getListNoPage(fields,null,sorts);
    }*/
}
