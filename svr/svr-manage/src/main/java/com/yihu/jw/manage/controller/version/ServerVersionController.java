package com.yihu.jw.manage.controller.version;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yihu.jw.manage.model.version.BaseServerVersion;
import com.yihu.jw.manage.service.version.ServerVersionService;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.rm.base.BaseVersionRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenweida on 2017/6/20.
 */
@RestController
@RequestMapping(BaseVersionRequestMapping.api_common)
@Api(description = "服务端版本管理")
public class ServerVersionController {
    @Autowired
    private ServerVersionService serverVersionService;

    @GetMapping(BaseVersionRequestMapping.BaseServerVersion.api_getList)
    @ApiOperation(value = "分页获取服务端版本列表")
    public Map<String,Object> list(
            @ApiParam(name = "name", value = "版本名称", required = false) @RequestParam(required = false, name = "name") String name,
            @ApiParam(name = "saasId", required = false) @RequestParam(required = false, name = "saasId") String saasId,
            @ApiParam(name = "sorts", value = "排序", required = false) @RequestParam(required = false, name = "sorts") String sorts,
            @ApiParam(name = "start", value = "当前页", required = false) @RequestParam(required = false, name = "start", defaultValue = "1") Integer start,
            @ApiParam(name = "length", value = "每页显示条数", required = false) @RequestParam(required = false, name = "length", defaultValue = "10") Integer length
    ) {
        Map<String, Object> req = new HashMap<String, Object>();
        try {
            Map<String, String> map = new HashMap<>();
            map.put("name",name);
            map.put("sorts",sorts);
            map.put("saasId",saasId);
            Envelop envelop = serverVersionService.list(length, start,map);

            List list = envelop.getDetailModelList();

            //数据返回
            req.put("rows",list);
            req.put("total",envelop.getTotalCount());
            return req;
        } catch (Exception e) {
            req.put("获取信息失败:" + e.getMessage(), -1);
            return req;
        }
    }


    @DeleteMapping(value = "/serverVersion/{codes}")
    @ApiOperation(value = "通过codes删除,多个code用,分割", notes = "通过codes删除")
    public Envelop deleteByCodes(
            @ApiParam(name = "codes", value = "codes")
            @PathVariable String codes,
            @ApiParam(name = "userCode", value = "userCode")
            @RequestParam String userCode
    ) {
        Envelop envelop = serverVersionService.deleteByCode(codes,userCode);
        return envelop;
    }

    @GetMapping(value = "/serverVersion/{code}")
    @ApiOperation(value = "根据code查找服务端版本", notes = "根据code查找服务端版本")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @PathVariable String code
    ) {
        Envelop envelop = serverVersionService.findByCode(code);
        return envelop;
    }

    @PostMapping(BaseVersionRequestMapping.BaseServerVersion.api_create)
    @ApiOperation(value = "保存/更新服务端版本", notes = "保存/更新服务端版本")
    public Envelop saveOrUpdate(@ModelAttribute @Valid BaseServerVersion baseServerVersion,String userCode) throws JsonProcessingException {
        return serverVersionService.saveOrUpdate(baseServerVersion,userCode);
    }

    @GetMapping(BaseVersionRequestMapping.BaseServerVersion.api_getListNoPage)
    @ApiOperation(value = "获取服务端版本列表")
    public List getListNoPage(@ApiParam(name = "saasId", value = "saasId", required = false) @RequestParam(required = false, name = "saasId") String saasId) {
        Map<String, Object> req = new HashMap<String, Object>();
        Map<String, String> map = new HashMap<>();
        map.put("saasId",saasId);
        Envelop envelop = serverVersionService.getListNoPage(map);

        List<Map<String,Object>> list = envelop.getDetailModelList();
        return list;
    }

}
