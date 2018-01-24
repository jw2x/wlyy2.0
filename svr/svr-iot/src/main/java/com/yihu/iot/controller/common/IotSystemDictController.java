package com.yihu.iot.controller.common;

import com.yihu.iot.dao.dict.IotSystemDictDao;
import com.yihu.iot.service.dict.IotSystemDictService;
import com.yihu.jw.iot.dict.IotSystemDictDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.iot.dict.IotSystemDictVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yeshijie on 2018/1/16.
 */
@RestController
@RequestMapping(IotRequestMapping.Common.system_dict)
@Api(tags = "系统字典相关操作", description = "系统字典相关操作")
public class IotSystemDictController extends EnvelopRestController {

    @Autowired
    private IotSystemDictService iotSystemDictService;
    @Autowired
    private IotSystemDictDao iotSystemDictDao;

    @GetMapping(value = IotRequestMapping.System.findDictByCode)
    @ApiOperation(value = "获取字典列表(不分页)")
    public Envelop<IotSystemDictVO> getList(
            @ApiParam(name = "dictName", value = "字典名称", defaultValue = "COMPANY_TYPE")
            @RequestParam(value = "dictName", required = true) String dictName) throws Exception {
        try {
            List<IotSystemDictDO> doList = iotSystemDictDao.findByDictName(dictName);
            List<IotSystemDictVO> voList = convertToModels(doList,new ArrayList<IotSystemDictVO>(doList.size()),IotSystemDictVO.class);
            return Envelop.getSuccessList(IotRequestMapping.Company.message_success_find_functions,voList);
        }catch (Exception e){
            e.printStackTrace();
            return Envelop.getError("查询失败");
        }
    }

}
