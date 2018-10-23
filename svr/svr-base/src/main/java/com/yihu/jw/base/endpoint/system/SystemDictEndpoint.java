package com.yihu.jw.base.endpoint.system;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yihu.jw.base.service.system.SystemDictService;
import com.yihu.jw.base.util.ConstantUtils;
import com.yihu.jw.entity.base.system.SystemDictDO;
import com.yihu.jw.restmodel.base.system.SystemDictVO;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.ListEnvelop;
import com.yihu.jw.restmodel.web.PageEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.base.BaseRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoint - 系统字典
 * Created by progr1mmer on 2018/8/16.
 */
@RestController
@RequestMapping(value = BaseRequestMapping.SystemDict.PREFIX)
@Api(value = "系统字典", description = "系统字典服务接口", tags = {"wlyy基础服务 - 系统字典服务接口"})
public class SystemDictEndpoint extends EnvelopRestEndpoint {

    @Autowired
    private SystemDictService systemDictService;

    @PostMapping(value = BaseRequestMapping.SystemDict.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
            @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
            @RequestParam(value = "ids") String ids) {
        systemDictService.delete(ids.split(","));
        return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.SystemDict.UPDATE)
    @ApiOperation(value = "更新")
    public Envelop update(
            @ApiParam(name = "json", value = "Json数据", required = true)
            @RequestParam String jsonData) throws Exception {
        SystemDictDO systemDictDO = toEntity(jsonData, SystemDictDO.class);
        if (null == systemDictDO.getCode()) {
            return failed("ID不能为空", Envelop.class);
        }
        systemDictDO = systemDictService.save(systemDictDO);
        return success(systemDictDO, SystemDictVO.class);
    }

    @GetMapping(value = BaseRequestMapping.SystemDict.PAGE)
    @ApiOperation(value = "获取分页")
    public Envelop page(
            @ApiParam(name = "dictType", value = "字典类型")
            @RequestParam(value = "dictType", required = true) String dictType,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts,
            @ApiParam(name = "page", value = "分页大小", required = true, defaultValue = "1")
            @RequestParam(value = "page") int page,
            @ApiParam(name = "size", value = "页码", required = true, defaultValue = "15")
            @RequestParam(value = "size") int size) throws Exception {

       JSONObject result =  systemDictService.queryDictPageByType("1",dictType,filters,sorts,page,size);
       if(StringUtils.equalsIgnoreCase(ConstantUtils.FAIL,result.getString("response"))){
           return failed(result.getString("msg"));
       }
        return success(result.getJSONArray("msg"), result.getInteger("count"), page, size);
    }

    @GetMapping(value = BaseRequestMapping.SystemDict.LIST)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<SystemDictVO> list(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        List<SystemDictDO> systemDictDOS = systemDictService.search(fields, filters, sorts);
        return success(systemDictDOS, SystemDictVO.class);
    }

    @PostMapping(value = BaseRequestMapping.SystemDict.CREATE)
    @ApiOperation(value = "新增系统字典")
    public Envelop createSystemDict(
            @ApiParam(name = "jsonData", value = "json数据，系统字典及其值")
            @RequestParam(value = "jsonData", required = true) String jsonData) throws Exception {
        String message = systemDictService.createSystemDict(jsonData);
        if (StringUtils.equalsIgnoreCase(message, ConstantUtils.SUCCESS)) {
            return success(message);
        }
        return failed(message);
    }

    @GetMapping(value = BaseRequestMapping.SystemDict.QUERY_BY_SAASID)
    @ApiOperation(value = "根据字典类型获取字典")
    public ListEnvelop queryDictBySaasId(
            @ApiParam(name = "saasId", value = "saasId")
            @RequestParam(value = "saasId", required = true) String saasId,
            @ApiParam(name = "name", value = "字典名称")
            @RequestParam(value = "name", required = false) String name,
            @ApiParam(name = "type", value = "字典类型")
            @RequestParam(value = "type", required = true) String type,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts,
            @ApiParam(name = "page", value = "分页大小", required = true, defaultValue = "1")
            @RequestParam(value = "page") int page,
            @ApiParam(name = "size", value = "页码", required = true, defaultValue = "15")
            @RequestParam(value = "size") int size
    ) throws Exception {
        JSONArray list = systemDictService.getDistListBySaasId(type,saasId,name,sorts,page,size);
        return success(list);
    }

    @PostMapping(value = BaseRequestMapping.SystemDict.QUERY_BY_TYPE)
    @ApiOperation(value = "根据字典类型获取字典")
    public ListEnvelop queryDictByType(
            @ApiParam(name = "userId", value = "用户id")
            @RequestParam(value = "userId", required = true) String userId,
            @ApiParam(name = "type", value = "字典类型")
            @RequestParam(value = "type", required = true) String type,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts,
            @ApiParam(name = "page", value = "分页大小", required = true, defaultValue = "1")
            @RequestParam(value = "page") int page,
            @ApiParam(name = "size", value = "页码", required = true, defaultValue = "15")
            @RequestParam(value = "size") int size
    ) throws Exception {
        JSONArray list = systemDictService.getDistListByType(type, userId, sorts, page, size);
        return success(list);
    }
}
