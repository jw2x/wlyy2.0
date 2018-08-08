package com.yihu.health.controller.dictionary;

import com.yihu.health.controller.common.BaseController;
import com.yihu.health.service.dictionary.SystemDictService;
import com.yihu.jw.restmodel.archives.dict.SystemDictVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yeshijie on 2018/1/16.
 */
@RestController
@RequestMapping(IotRequestMapping.Common.system_dict)
@Api(tags = "系统字典相关操作", description = "系统字典相关操作")
public class SystemDictController extends BaseController{

    @Autowired
    private SystemDictService systemDictService;

    @GetMapping(value = "dictionariesWithEntry")
    @ApiOperation(value = "获取字典列表(不分页)")
    public List<SystemDictVO> getList() throws Exception {
        return systemDictService.getList("145,146,147,148,149,150,154,152,153,154,155,156,157");
    }

}
