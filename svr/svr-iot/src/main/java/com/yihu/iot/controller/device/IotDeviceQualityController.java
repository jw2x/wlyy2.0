package com.yihu.iot.controller.device;

import com.yihu.iot.service.device.IotDeviceQualityInspectionPlanService;
import com.yihu.jw.iot.device.IotDeviceDO;
import com.yihu.jw.iot.device.IotDeviceQualityInspectionPlanDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.iot.device.IotDeviceQualityInspectionPlanVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yeshijie on 2017/12/8.
 */
@RestController
@RequestMapping(IotRequestMapping.Common.quality)
@Api(tags = "设备质检管理相关操作", description = "设备质检管理相关操作")
public class IotDeviceQualityController extends EnvelopRestController{
    @Autowired
    private IotDeviceQualityInspectionPlanService iotDeviceQualityInspectionPlanService;

    @PostMapping(value = IotRequestMapping.DeviceQuality.addQualityPlan)
    @ApiOperation(value = "创建设备质检", notes = "创建设备质检")
    public Envelop<IotDeviceQualityInspectionPlanVO> create(@ApiParam(name = "jsonData", value = "", defaultValue = "")
                          @RequestParam String jsonData) {
        try {
            IotDeviceQualityInspectionPlanDO iotDeviceQualityInspectionPlan = toEntity(jsonData, IotDeviceQualityInspectionPlanDO.class);
            return Envelop.getSuccess(IotRequestMapping.Common.message_success_create, iotDeviceQualityInspectionPlanService.create(iotDeviceQualityInspectionPlan));
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = IotRequestMapping.DeviceQuality.findById)
    @ApiOperation(value = "根据code查找设备质检", notes = "根据code查找设备质检")
    public Envelop<IotDeviceQualityInspectionPlanVO> findByCode(@ApiParam(name = "id", value = "id")
                              @RequestParam(value = "id", required = true) String id
    ) {
        try {
            IotDeviceQualityInspectionPlanDO planDO = iotDeviceQualityInspectionPlanService.findById(id);
            IotDeviceQualityInspectionPlanVO planVO = convertToModel(planDO,IotDeviceQualityInspectionPlanVO.class);
            return Envelop.getSuccess(IotRequestMapping.Common.message_success_find, planVO);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }


    @GetMapping(value = IotRequestMapping.DeviceQuality.queryQualityPlanPage)
    @ApiOperation(value = "分页获取设备质检计划", notes = "分页获取设备质检计划")
    public Envelop<IotDeviceQualityInspectionPlanVO> findProductPageByCompanyId(@ApiParam(name = "purcharseId", value = "采购id", defaultValue = "")
                                                           @RequestParam(value = "purcharseId", required = false) String purcharseId,
                                                           @ApiParam(name = "page", value = "第几页", defaultValue = "")
                                                           @RequestParam(value = "page", required = false) Integer page,
                                                           @ApiParam(name = "size", value = "每页记录数", defaultValue = "")
                                                           @RequestParam(value = "size", required = false) Integer size){
        try {
            if(page == null|| page < 0){
                page = 1;
            }
            if(size == null){
                size = 10;
            }
            String filters = "";
            String semicolon = "";
            if(StringUtils.isNotBlank(purcharseId)){
                filters += semicolon +"purcharseId="+purcharseId;
                semicolon = ";";
            }
            if(StringUtils.isBlank(filters)){
                filters+= semicolon + "del=1";
            }
            String sorts = "-updateTime";
            //得到list数据
            List<IotDeviceDO> list = iotDeviceQualityInspectionPlanService.search(null, filters, sorts, page, size);
            //获取总数
            long count = iotDeviceQualityInspectionPlanService.getCount(filters);

            //DO转VO
            List<IotDeviceQualityInspectionPlanVO> voList = convertToModels(list,new ArrayList<>(list.size()),IotDeviceQualityInspectionPlanVO.class);

            return Envelop.getSuccessListWithPage(IotRequestMapping.Common.message_success_find_functions,voList, page, size,count);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = IotRequestMapping.DeviceQuality.delQualityPlan)
    @ApiOperation(value = "删除质检计划", notes = "删除质检计划")
    public Envelop<IotDeviceQualityInspectionPlanVO> delCompany(@ApiParam(name = "id", value = "id")
                                            @RequestParam(value = "id", required = true) String id) {
        try {
            iotDeviceQualityInspectionPlanService.delPlan(id);
            return Envelop.getSuccess(IotRequestMapping.Common.message_success_find);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = IotRequestMapping.DeviceQuality.completeQualityPlan)
    @ApiOperation(value = "完成质检计划", notes = "完成质检计划")
    public Envelop<IotDeviceQualityInspectionPlanVO> updCompany(@ApiParam(name = "actualTime", value = "完成时间", defaultValue = "")
                                            @RequestParam(value = "actualTime", required = true) String actualTime,
                                            @ApiParam(name = "id", value = "id")
                                            @RequestParam(value = "id", required = true) String id) {
        try {
            iotDeviceQualityInspectionPlanService.completePlan(id,actualTime);
            return Envelop.getSuccess(IotRequestMapping.Common.message_success_find);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

}
