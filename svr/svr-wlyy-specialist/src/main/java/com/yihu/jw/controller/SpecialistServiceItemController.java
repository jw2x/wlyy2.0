package com.yihu.jw.controller;/**
 * Created by nature of king on 2018/8/17.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yihu.jw.entity.specialist.SpecialistEvaluateDO;
import com.yihu.jw.entity.specialist.SpecialistServiceItemDO;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.specialist.SpecialistMapping;
import com.yihu.jw.service.SpecialistServiceItemService;
import com.yihu.jw.util.DataUtils;
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
public class SpecialistServiceItemController extends EnvelopRestEndpoint {

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
    @PostMapping(value = SpecialistMapping.serviceItem.getServiceItem)
    @ApiOperation(value = "服务项目列表查询")
    public MixEnvelop<SpecialistServiceItemDO,SpecialistEvaluateDO> select(@ApiParam(name = "serviceItem", value = "服务项目JSON")
                                                       @RequestParam(value = "serviceItem")String serviceItem,
                                                                           @ApiParam(value = "当前页",name = "page")
                                                   @RequestParam(value = "page",defaultValue = "1") Integer page,
                                                                           @ApiParam(value = "显示记录数",name = "pageSize",defaultValue = "10")
                                                       @RequestParam(value = "pageSize") Integer pageSize){
        try {
            SpecialistServiceItemDO serviceItemDO = toEntity(serviceItem,SpecialistServiceItemDO.class);
            MixEnvelop envelop = specialistServiceItemService.select(serviceItemDO,page,pageSize);
            JSONArray array = new JSONArray();
            List<SpecialistServiceItemDO> specialistServiceItemDOS =  envelop.getDetailModelList();
            if (specialistServiceItemDOS != null && specialistServiceItemDOS.size()!=0){
                for (SpecialistServiceItemDO specialistServiceItemDO:specialistServiceItemDOS){
                    JSONObject jsonObject = (JSONObject)JSONObject.toJSON(specialistServiceItemDO);
                    jsonObject.replace("threeHospitals", DataUtils.integerTransferDouble(specialistServiceItemDO.getThreeHospitals()));
                    jsonObject.replace("twoHospitals",DataUtils.integerTransferDouble(specialistServiceItemDO.getTwoHospitals()));
                    jsonObject.replace("oneHospitals",DataUtils.integerTransferDouble(specialistServiceItemDO.getOneHospitals()));
                    array.add(jsonObject);
                }
            }
            envelop.setDetailModelList(array);
            return envelop;
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }


    /**
     * 服务项目添加
     *
     * @param serviceItem
     * @return
     */
    @PostMapping(value = SpecialistMapping.serviceItem.createServiceItem)
    @ApiOperation(value = "服务项目添加")
    public MixEnvelop<Boolean,Boolean> insert(@ApiParam(name = "serviceItem", value = "服务项目JSON")
                                                       @RequestParam(value = "serviceItem")String serviceItem){
        try {
            JSONObject object = JSON.parseObject(serviceItem);
            if (object.getDouble("threeHospitals")!=null){
                object.replace("threeHospitals", DataUtils.doubleToInt(object.getDouble("threeHospitals")));
            }
            if (object.getDouble("twoHospitals")!=null){
                object.replace("twoHospitals",DataUtils.doubleToInt(object.getDouble("twoHospitals")));
            }
            if (object.getDouble("oneHospitals")!=null){
                object.replace("oneHospitals",DataUtils.doubleToInt(object.getDouble("oneHospitals")));
            }
            SpecialistServiceItemDO serviceItemDO = toEntity(object.toJSONString(),SpecialistServiceItemDO.class);
            return specialistServiceItemService.insert(serviceItemDO);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }


    /**
     * 批量删除服务项目
     * @param ids
     * @return
     */
    @PostMapping(value = SpecialistMapping.serviceItem.batchDelete)
    @ApiOperation(value = "批量删除服务项目")
    public MixEnvelop<Boolean,Boolean> batchDelete(@ApiParam(name="ids",value = "id集合")
                                        @RequestParam(value = "ids",required = false)String ids){
        try{
            MixEnvelop<Boolean,Boolean> envelop = new MixEnvelop<>();
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
            return MixEnvelop.getError(e.getMessage());
        }
    }


    /**
     * 更新服务项目
     *
     * @param serviceItem
     * @return
     */
    @PostMapping(value = SpecialistMapping.serviceItem.updateServiceItem)
    @ApiOperation(value = "服务项目更新")
    public MixEnvelop<Boolean,Boolean> udpate(@ApiParam(name = "serviceItem", value = "服务项目JSON")
                                              @RequestParam(value = "serviceItem")String serviceItem){
        try {
            JSONObject object = JSON.parseObject(serviceItem);
            if (object.getDouble("threeHospitals")!=null){
                object.replace("threeHospitals", DataUtils.doubleToInt(object.getDouble("threeHospitals")));
            }
            if (object.getDouble("twoHospitals")!=null){
                object.replace("twoHospitals",DataUtils.doubleToInt(object.getDouble("twoHospitals")));
            }
            if (object.getDouble("oneHospitals")!=null){
                object.replace("oneHospitals",DataUtils.doubleToInt(object.getDouble("oneHospitals")));
            }
            SpecialistServiceItemDO serviceItemDO = toEntity(object.toJSONString(),SpecialistServiceItemDO.class);
            return specialistServiceItemService.update(serviceItemDO);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }


    /**
     * 根据医院code获取服务项目
     *
     * @param hospital
     * @return
     */
    @PostMapping(value = SpecialistMapping.serviceItem.selectItemByHospital)
    @ApiOperation(value = "根据医院code获取服务项目")
    public MixEnvelop selectByHospital(@ApiParam(name = "hospital", value = "医院code")
                                              @RequestParam(value = "hospital")String hospital){
        try {
            MixEnvelop envelop = specialistServiceItemService.selectByHospital(hospital);
            JSONArray array = new JSONArray();
            List<SpecialistServiceItemDO> specialistServiceItemDOS =  envelop.getDetailModelList();
            if (specialistServiceItemDOS != null && specialistServiceItemDOS.size()!=0){
                for (SpecialistServiceItemDO specialistServiceItemDO:specialistServiceItemDOS){
                    JSONObject jsonObject = (JSONObject)JSONObject.toJSON(specialistServiceItemDO);
                    jsonObject.replace("threeHospitals", DataUtils.integerTransferDouble(specialistServiceItemDO.getThreeHospitals()));
                    jsonObject.replace("twoHospitals",DataUtils.integerTransferDouble(specialistServiceItemDO.getTwoHospitals()));
                    jsonObject.replace("oneHospitals",DataUtils.integerTransferDouble(specialistServiceItemDO.getOneHospitals()));
                    array.add(jsonObject);
                }
            }
            envelop.setDetailModelList(array);
            return envelop;
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }


    /**
     * 导数据
     *
     * @return
     */
    @RequestMapping(value = "importData1")
    @ResponseBody
    public MixEnvelop<Boolean,Boolean> importData(@ApiParam(name = "serviceItems", value = "服务项目集合")
                                                      @RequestParam(value = "serviceItems")String serviceItems) {
        try {
            JSONArray array = JSONArray.parseArray(serviceItems);
            List<SpecialistServiceItemDO> specialistServiceItemDOS = new ArrayList<>();
            for (int i = 0;i<array.size();i++){
                JSONObject object = array.getJSONObject(i);
                if (object.getDouble("threeHospitals")!=null){
                    object.replace("threeHospitals", DataUtils.doubleToInt(object.getDouble("threeHospitals")));
                }
                if (object.getDouble("twoHospitals")!=null){
                    object.replace("twoHospitals",DataUtils.doubleToInt(object.getDouble("twoHospitals")));
                }
                if (object.getDouble("oneHospitals")!=null){
                    object.replace("oneHospitals",DataUtils.doubleToInt(object.getDouble("oneHospitals")));
                }
                SpecialistServiceItemDO specialistServiceItemDO = toEntity(object.toJSONString(),SpecialistServiceItemDO.class);
                specialistServiceItemDOS.add(specialistServiceItemDO);
            }
            return specialistServiceItemService.importData(specialistServiceItemDOS);
        } catch (Exception e) {
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

}
