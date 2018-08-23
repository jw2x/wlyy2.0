package com.yihu.jw.controller;/**
 * Created by nature of king on 2018/8/17.
 */

import com.alibaba.fastjson.JSONArray;
import com.yihu.jw.entity.specialist.SpecialistServiceItemDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.rm.specialist.SpecialistMapping;
import com.yihu.jw.service.SpecialistServiceItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzhinan
 * @create 2018-08-17 9:33
 * @desc 服务项目
 **/
@RestController
@RequestMapping(SpecialistMapping.api_specialist_common)
@Api(tags = "服务项目相关操作", description = "服务项目相关操作")
public class SpecialistServiceItemController extends EnvelopRestController {

    @Autowired
    private Tracer tracer;
    @Autowired
    private SpecialistServiceItemService specialistServiceItemService;

    /**
     * 服务项目列表查询
     *
     * @param serviceItem
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping(value = SpecialistMapping.serviceItem.getServiceItem)
    @ApiOperation(value = "服务项目列表查询")
    public Envelop<SpecialistServiceItemDO> select(@ApiParam(name = "serviceItem", value = "服务项目JSON")
                                                       @RequestParam(value = "serviceItem")String serviceItem,
                                                   @ApiParam(value = "当前页",name = "page")
                                                   @RequestParam(value = "page",defaultValue = "1") Integer page,
                                                   @ApiParam(value = "显示记录数",name = "pageSize",defaultValue = "10")
                                                       @RequestParam(value = "pageSize") Integer pageSize){
        try {
            SpecialistServiceItemDO serviceItemDO = toEntity(serviceItem,SpecialistServiceItemDO.class);
            return specialistServiceItemService.select(serviceItemDO,page,pageSize);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }


    /**
     * 服务项目添加
     *
     * @param serviceItem
     * @return
     */
    @GetMapping(value = SpecialistMapping.serviceItem.createServiceItem)
    @ApiOperation(value = "服务项目添加")
    public Envelop<Boolean> insert(@ApiParam(name = "serviceItem", value = "服务项目JSON")
                                                       @RequestParam(value = "serviceItem")String serviceItem){
        try {
            SpecialistServiceItemDO serviceItemDO = toEntity(serviceItem,SpecialistServiceItemDO.class);
            return specialistServiceItemService.insert(serviceItemDO);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }


    /**
     * 批量删除服务项目
     * @param ids
     * @return
     */
    @PostMapping(value = SpecialistMapping.serviceItem.batchDelete)
    @ApiOperation(value = "批量删除服务项目")
    public Envelop<Boolean> batchDelete(@ApiParam(name="ids",value = "id集合")
                                        @RequestParam(value = "ids",required = false)String ids){
        try{
            Envelop<Boolean> envelop = new Envelop<>();
            JSONArray array = JSONArray.parseArray(ids);
            List<String> itemIds = new ArrayList<>();
            for (int i = 0;i<array.size();i++){
                itemIds.add(array.getString(i));
            }
            specialistServiceItemService.batchDelete(itemIds);
            envelop.setObj(true);
            return envelop;
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }


    /**
     * 更新服务项目
     *
     * @param serviceItem
     * @return
     */
    @GetMapping(value = SpecialistMapping.serviceItem.updateServiceItem)
    @ApiOperation(value = "服务项目更新")
    public Envelop<Boolean> udpate(@ApiParam(name = "serviceItem", value = "服务项目JSON")
                                   @RequestParam(value = "serviceItem")String serviceItem){
        try {
            SpecialistServiceItemDO serviceItemDO = toEntity(serviceItem,SpecialistServiceItemDO.class);
            return specialistServiceItemService.update(serviceItemDO);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }
}
