package com.yihu.jw.healthyhouse.controller.dict;


import com.yihu.jw.healthyhouse.model.dict.DictEntryKey;
import com.yihu.jw.healthyhouse.model.dict.SystemDict;
import com.yihu.jw.healthyhouse.model.dict.SystemDictEntry;
import com.yihu.jw.healthyhouse.service.dict.SystemDictEntryService;
import com.yihu.jw.healthyhouse.service.dict.SystemDictService;
import com.yihu.jw.restmodel.web.*;
import com.yihu.jw.rm.health.house.HealthyHouseMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import java.io.IOException;
import java.util.*;

/**
 * @author zdm
 * @version 1.0
 * @created 2018.09.18
 */
@RestController
@RequestMapping(HealthyHouseMapping.api_healthyHouse_common)
@Api(value = "DictionaryEntry", description = "系统全局字典项管理", tags = {"系统字典-系统全局字典项管理"})
public class SystemDictEntryController extends EnvelopRestEndpoint {

    @Autowired
    private SystemDictService dictService;
    @Autowired
    private SystemDictEntryService systemDictEntryService;

    @ApiOperation(value = "获取字典项列表")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.SystemDictEntry.PAGE)
    public PageEnvelop getSystemDictEntrys(
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
        List<SystemDictEntry> systemDictEntryList = systemDictEntryService.search(fields,filters,sorts,page,size);
        return success(systemDictEntryList,(null==systemDictEntryList)?0:systemDictEntryList.size(),page, size);
    }

    @ApiOperation(value = "创建字典项")
    @PostMapping(value = HealthyHouseMapping.HealthyHouse.SystemDictEntry.CREATE)
    public ObjEnvelop<SystemDictEntry> createDictEntry (
            @ApiParam(name = "entryJson", value = "字典项JSON结构")
            @RequestParam(value = "entryJson") String entryJson) throws IOException{
        SystemDictEntry entry = toEntity(entryJson, SystemDictEntry.class);
        SystemDict systemDict = dictService.retrieve(entry.getDictId());
        if (systemDict == null) {
            return  failed("所属字典不存在!",ObjEnvelop.class);
        }
        int nextSort = systemDictEntryService.getNextSN(entry.getDictId());
        entry.setSort(nextSort);
        entry.setCreateTime(new Date());
        systemDictEntryService.save(entry);
        return success(entry);
    }

    @ApiOperation(value = "获取字典项")
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.SystemDictEntry.GETDICTENTRYBYDICTIDANDCODE)
    public ObjEnvelop getDictEntry(
            @ApiParam(name = "dictId", value = "字典ID", required = true)
            @RequestParam(value = "dictId") String dictId,
            @ApiParam(name = "code", value = "字典项代码", required = true)
            @RequestParam(value = "code") String code) throws Exception{
        SystemDictEntry systemDictEntry = systemDictEntryService.getDictEntry(dictId, code);
        return success(systemDictEntry);
    }

    @ApiOperation(value = "删除字典项")
    @DeleteMapping(HealthyHouseMapping.HealthyHouse.SystemDictEntry.DELETEBYDICTIDANDCODE)
    public Envelop deleteDictEntry(
            @ApiParam(name = "dictId", value = "字典ID")
            @RequestParam(value = "dictId") String dictId,
            @ApiParam(name = "code", value = "字典项编码")
            @RequestParam(value = "code") String code) throws Exception{
        systemDictEntryService.deleteDictEntry(dictId, code);
        return success("success");
    }

    @ApiOperation(value = "修改字典项")
    @PutMapping(value =HealthyHouseMapping.HealthyHouse.SystemDictEntry.UPDATE)
    public ObjEnvelop<SystemDictEntry> updateDictEntry(
            @ApiParam(name = "entryJson", value = "字典项JSON结构")
            @RequestParam(value = "entryJson") String entryJson) throws IOException {
        SystemDictEntry entry = toEntity(entryJson, SystemDictEntry.class);
        SystemDictEntry temp = systemDictEntryService.retrieve(new DictEntryKey(entry.getCode(), entry.getDictId()));
        if (null == temp) {
            failed("字典项不存在!",ObjEnvelop.class);
        }
        entry.setUpdateTime(new Date());
        systemDictEntryService.saveDictEntry(entry);
        return success(entry);
    }

    @GetMapping(value =HealthyHouseMapping.HealthyHouse.SystemDictEntry.ISEXISTSDICTENTRYBYDICTIDANDCODE)
    @ApiOperation(value = "根据dictId和code判断提交的字典项是否已经存在")
    public boolean isDictEntryCodeExists(
            @ApiParam(name = "dictId", value = "字典id", defaultValue = "")
            @RequestParam(value = "dictId",required = true) String dictId,
            @ApiParam(name = "code", value = "字典项编码", defaultValue = "")
            @RequestParam(value = "code") String code){
        return systemDictEntryService.isDictContainEntry(dictId, code);
    }

    @GetMapping(value =HealthyHouseMapping.HealthyHouse.SystemDictEntry.GETDICTENTRYBYDICTIDANDNAME)
    @ApiOperation(value = "根据dictId和name判断提交的字典项是否已经存在")
    public boolean isDictEntryNameExists(
            @ApiParam(name = "dictId", value = "字典id", defaultValue = "")
            @RequestParam(value = "dictId",required = true) String dictId,
            @ApiParam(name = "name", value = "字典项名称")
            @RequestParam(value = "name") String name){
        List<SystemDictEntry> systemDictEntryPage= systemDictEntryService.findByDictIdAndValueLike(dictId, name);
        if(null!=systemDictEntryPage&&systemDictEntryPage.size()>0){
            return  true;
        }
        return false;
    }

    @GetMapping(HealthyHouseMapping.HealthyHouse.SystemDictEntry.LIST)
    @ApiOperation(value = "根据dictId获取所有字典项")
    public ListEnvelop GetSystemDictEntryListByDictId(
            @ApiParam(name = "dictId", value = "字典id")
            @RequestParam(value = "dictId") String dictId) throws Exception{
        List<SystemDictEntry> cardList = systemDictEntryService.getDictEntryCodeAndValueByDictId(dictId);
        return success(cardList);
    }

}
