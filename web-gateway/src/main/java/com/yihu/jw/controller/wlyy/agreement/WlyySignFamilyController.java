package com.yihu.jw.controller.wlyy.agreement;

import com.yihu.jw.fegin.wlyy.agreement.WlyySignFamilyFegin;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.wlyy.WlyyContants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(WlyyContants.WlyySignFamily.api_common)
@Api(value = "签约相关操作", description = "签约相关操作")
public class WlyySignFamilyController extends EnvelopRestController {

    private Logger logger= LoggerFactory.getLogger(WlyySignFamilyController.class);

    @Autowired
    private WlyySignFamilyFegin wlyySignFamilyFegin;

    @Autowired
    private Tracer tracer;

    @PostMapping(value = WlyyContants.WlyySignFamily.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建签约", notes = "创建签约")
    public Envelop create(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
       return wlyySignFamilyFegin.create(jsonData);
    }

    @PutMapping(value = WlyyContants.WlyySignFamily.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改签约", notes = "修改签约")
    public Envelop update(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        return wlyySignFamilyFegin.update(jsonData);
    }

    @GetMapping(value =WlyyContants.WlyySignFamily.api_getByCode)
    @ApiOperation(value = "根据code查找协议", notes = "根据code查找协议")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code
    ) {
       return wlyySignFamilyFegin.findByCode(code);
    }

    @RequestMapping(value =WlyyContants.WlyySignFamily.api_queryPage, method = RequestMethod.GET)
    @ApiOperation(value = "分页获取协议")
    public Envelop queryPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,parentCode,saasId,name,price,posterPic,remark,type,status")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts,
            @ApiParam(name = "size", value = "分页大小", defaultValue = "15")
            @RequestParam(value = "size", required = false) int size,
            @ApiParam(name = "page", value = "页码", defaultValue = "1")
            @RequestParam(value = "page", required = false) int page,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
       return wlyySignFamilyFegin.queryPage(fields, filters, sorts, size, page, request, response);
    }


    @GetMapping(value =WlyyContants.WlyySignFamily.api_getList)
    @ApiOperation(value = "获取协议列表(不分页)")
    public Envelop getList(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,parentCode,saasId,name,price,posterPic,remark,type,status")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        return wlyySignFamilyFegin.getList(fields,filters,sorts);
    }

}
