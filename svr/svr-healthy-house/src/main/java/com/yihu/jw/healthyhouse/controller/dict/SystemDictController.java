package com.yihu.jw.healthyhouse.controller.dict;


import com.yihu.jw.healthyhouse.model.dict.SystemDict;
import com.yihu.jw.healthyhouse.service.dict.SystemDictService;
import com.yihu.jw.healthyhouse.util.PinyinUtil;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.ObjEnvelop;
import com.yihu.jw.restmodel.web.PageEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.health.house.HealthyHouseMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @author zdm
 * @version 1.0
 * @created 2018.09.18
 */
@RestController
@RequestMapping(HealthyHouseMapping.api_healthyHouse_common)
@Api(value = "Dictionary", description = "系统全局字典管理", tags = {"系统字典-系统全局字典管理"})
public class SystemDictController extends EnvelopRestEndpoint {

    @Autowired
    private SystemDictService dictService;

    @ApiOperation(value = "获取字典列表", responseContainer = "List")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.SystemDict.PAGE)
    public PageEnvelop<SystemDict> getDictionaries(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器", defaultValue = "")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序", defaultValue = "")
            @RequestParam(value = "sorts", required = false) String sorts,
            @ApiParam(name = "size", value = "分页大小", defaultValue = "15")
            @RequestParam(value = "size", required = false) Integer size,
            @ApiParam(name = "page", value = "页码", defaultValue = "1")
            @RequestParam(value = "page", required = false) Integer page) throws Exception {
        List<SystemDict> systemDictList = dictService.search(fields, filters, sorts, page, size);
        int count = (int)dictService.getCount(filters);
        return success(systemDictList, count, page, size);
    }

    @ApiOperation(value = "创建字典")
    @PostMapping(value = HealthyHouseMapping.HealthyHouse.SystemDict.CREATE)
    public ObjEnvelop<SystemDict> createDictionary(
            @ApiParam(name = "dictionary", value = "字典JSON结构")
            @RequestBody SystemDict dict) throws IOException {
        if (StringUtils.isEmpty(dict.getName())) {
            return failed("字典名称不能为空！", ObjEnvelop.class);
        }
        if (StringUtils.isEmpty(dict.getPhoneticCode())) {
            dict.setPhoneticCode(PinyinUtil.getPinYinHeadChar(dict.getName(), true));
        }
        if (StringUtils.isEmpty(dict.getCode())) {
            dict.setCode(dict.getPhoneticCode());
        }
        SystemDict systemDict = dictService.save(dict);
        return success(systemDict);
    }

    @ApiOperation(value = "获取字典")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.SystemDict.GETDICTBYID)
    public ObjEnvelop<SystemDict> getDictionary(
            @ApiParam(name = "id", value = "字典ID", defaultValue = "")
            @RequestParam(value = "id") String id) throws Exception {

        SystemDict dict = dictService.findById(id);
        if (dict == null) {
            return failed("字典不存在！", ObjEnvelop.class);
        }
        return success(dict);
    }

    @ApiOperation(value = "获取字典")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.SystemDict.GETDICTBYPHONETICCODE)
    public ObjEnvelop<SystemDict> getDictionaryByPhoneticCode(
            @ApiParam(name = "phoneticCode", value = "拼音编码", required = true)
            @RequestParam(value = "phoneticCode") String phoneticCode) throws Exception {
        SystemDict dict = dictService.findByPhoneticCode(phoneticCode);
        if (dict == null) {
            return failed("字典不存在！", ObjEnvelop.class);
        }
        return success(dict);
    }

    @ApiOperation(value = "更新字典")
    @PutMapping(value = HealthyHouseMapping.HealthyHouse.SystemDict.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ObjEnvelop<SystemDict> updateDictionary(
            @ApiParam(name = "dictionary", value = "字典JSON结构")
            @RequestBody SystemDict dict) throws Exception {
        SystemDict oldDict = dictService.findById(dict.getId());
        if (null == oldDict) {
            return failed("字典不存在", ObjEnvelop.class);
        }
        if (StringUtils.isEmpty(dict.getName())) {
            return failed("字典名称不能为空", ObjEnvelop.class);
        }
        if (!oldDict.getName().equals(dict.getName()) && dictService.findByField("name", dict.getName()).size() > 0) {
            return failed("字典名称在系统中已存在", ObjEnvelop.class);
        }
        if (StringUtils.isEmpty(dict.getPhoneticCode())) {
            dict.setPhoneticCode(PinyinUtil.getPinYinHeadChar(dict.getName(), true));
        }
        if (StringUtils.isEmpty(dict.getCode())) {
            dict.setCode(dict.getPhoneticCode());
        }
        oldDict.setName(dict.getName());
        oldDict.setPhoneticCode(dict.getPhoneticCode());
        oldDict.setCode(dict.getCode());
        dictService.updateDict(oldDict);
        return success(oldDict);
    }

    @ApiOperation(value = "删除字典")
    @DeleteMapping(value = HealthyHouseMapping.HealthyHouse.SystemDict.DELETE)
    public Envelop deleteDictionary(
            @ApiParam(name = "dictId", value = "字典ID")
            @RequestParam(value = "dictId") String dictId) throws Exception {
        dictService.deleteDict(dictId);
        return success("success");
    }

    @GetMapping(value = HealthyHouseMapping.HealthyHouse.SystemDict.ISDICTNAMEEXISTS)
    @ApiOperation(value = "判断提交的字典名称是否已经存在")
    public boolean isDictNameExists(
            @ApiParam(name = "dict_name", value = "字典名称")
            @RequestParam(value = "dict_name") String dictName) throws Exception {
        return dictService.isDictNameExists(dictName);
    }

    @GetMapping(value = HealthyHouseMapping.HealthyHouse.SystemDict.ISDICTCODEEXISTS)
    @ApiOperation(value = "判断提交的字典编码是否已经存在")
    public boolean checkCode(
            @ApiParam(name = "code", value = "编码")
            @RequestParam(value = "code") String code) throws Exception {
        if (dictService.findByField("code", code).size() > 0) {
            return true;
        }
        return false;
    }

}
