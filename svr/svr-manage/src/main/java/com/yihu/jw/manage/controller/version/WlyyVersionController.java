package com.yihu.jw.manage.controller.version;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yihu.jw.manage.model.version.WlyyVersion;
import com.yihu.jw.manage.service.version.WlyyVersionService;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.rm.base.BaseVersionRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenweida on 2017/6/20.
 */
@RestController
@RequestMapping(BaseVersionRequestMapping.api_common)
@Api(description = "app版本管理")
public class WlyyVersionController {
    @Autowired
    private WlyyVersionService wlyyVersionService;

    @GetMapping(BaseVersionRequestMapping.WlyyVersion.api_getList)
    @ApiOperation(value = "分页获取app版本列表")
    public Envelop list(
            @ApiParam(name = "name", value = "版本名称", required = false) @RequestParam(required = false, name = "name") String name,
            @ApiParam(name = "saasId", required = false) @RequestParam(required = false, name = "saasId") String saasId,
            @ApiParam(name = "sorts", value = "排序", required = false) @RequestParam(required = false, name = "sorts") String sorts,
            @ApiParam(name = "start", value = "当前页", required = false) @RequestParam(required = false, name = "start", defaultValue = "1") Integer start,
            @ApiParam(name = "length", value = "每页显示条数", required = false) @RequestParam(required = false, name = "length", defaultValue = "10") Integer length
    ) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("name",name);
            map.put("sorts",sorts);
            map.put("saasId",saasId);
            Envelop envelop = wlyyVersionService.list(length, start,map);
            return envelop;
        } catch (Exception e) {
            return Envelop.getError("获取信息失败:" + e.getMessage(), -1);
        }
    }


    @DeleteMapping(value = "/wlyyVersion/{codes}")
    @ApiOperation(value = "通过codes删除,多个code用,分割", notes = "通过codes删除")
    public Envelop deleteByCodes(
            @ApiParam(name = "codes", value = "codes")
            @PathVariable String codes,
            @ApiParam(name = "userCode", value = "userCode")
            @RequestParam String userCode
    ) {
        Envelop envelop = wlyyVersionService.deleteByCode(codes,userCode);
        return envelop;
    }

    @GetMapping(value = "/wlyyVersion/{code}")
    @ApiOperation(value = "根据code查找app版本", notes = "根据code查找app版本")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @PathVariable String code
    ) {
        Envelop envelop = wlyyVersionService.findByCode(code);
        return envelop;
    }

    @PostMapping(BaseVersionRequestMapping.WlyyVersion.api_create)
    @ApiOperation(value = "保存/更新服务端版本", notes = "保存/更新服务端版本")
    public Envelop saveOrUpdate(@ModelAttribute @Valid WlyyVersion version,String userCode) throws JsonProcessingException {
        return wlyyVersionService.saveOrUpdate(version,userCode);
    }

}
