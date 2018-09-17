package com.yihu.jw.controller;/**
 * Created by nature of king on 2018/8/29.
 */

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yihu.jw.entity.specialist.HospitalServiceItemDO;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.specialist.SpecialistMapping;
import com.yihu.jw.service.SpecialistHospitalServiceItemService;
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
 * @create 2018-08-29 0:56
 * @desc
 **/
@RestController
@RequestMapping(SpecialistMapping.api_specialist_common)
@Api(tags = "机构服务项目相关操作", description = "机构服务项目相关操作")
public class SpecialistHospitalServiceItemController extends EnvelopRestEndpoint {

    @Autowired
    private Tracer tracer;
    @Autowired
    private SpecialistHospitalServiceItemService specialistHospitalServiceItemService;


    /**
     * 添加机构服务项目
     * @param hospitalServiceItem
     * @return
     */
    @PostMapping(value = SpecialistMapping.serviceItem.createHospitalServiceItem)
    @ApiOperation(value = "添加机构服务项目")
    public MixEnvelop<HospitalServiceItemDO,HospitalServiceItemDO> create(@ApiParam(name = "hospitalServiceItem", value = "机构服务项目")
                                                                        @RequestParam(value = "hospitalServiceItem")String hospitalServiceItem){
        try {
            JSONArray array = JSONArray.parseArray(hospitalServiceItem);
            List<HospitalServiceItemDO> hospitalServiceItemDOS = new ArrayList<>();
            for (int i =0 ; i<array.size();i++){
                JSONObject object = array.getJSONObject(i);
                object.replace("expense", DataUtils.doubleToInt(object.getDouble("expense")));
                HospitalServiceItemDO hospitalServiceItemDO = toEntity(object.toJSONString(),HospitalServiceItemDO.class);
                hospitalServiceItemDOS.add(hospitalServiceItemDO);
            }
            return specialistHospitalServiceItemService.insert(hospitalServiceItemDOS);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

    /**
     * 根据医院Code查找机构服务项目
     *
     * @param hospitals
     * @return
     */
    @PostMapping(value = SpecialistMapping.serviceItem.selectByHospital)
    @ApiOperation(value = "根据医院Code查找机构服务项目")
    public MixEnvelop<HospitalServiceItemDO,HospitalServiceItemDO> selectByHospital(@ApiParam(name = "hospitals", value = "医院code集合")
                                                                          @RequestParam(value = "hospitals",required = false)String hospitals){
        try {
            JSONArray array = JSONArray.parseArray(hospitals);
            List<String> hospitalList = new ArrayList<>();
            if (array != null || array.size()!=0){
                for (int i =0 ;i<array.size();i++){
                    hospitalList.add(array.getString(i));
                }
            }
            MixEnvelop envelop = specialistHospitalServiceItemService.selectByHospital(hospitalList);
            List<HospitalServiceItemDO> hospitalServiceItemDOList = envelop.getDetailModelList();
            JSONArray array1 = new JSONArray();
            if (hospitalServiceItemDOList != null && hospitalServiceItemDOList.size()!=0){
                for (HospitalServiceItemDO hospitalServiceItemDO:hospitalServiceItemDOList){
                    JSONObject object = (JSONObject) JSONObject.toJSON(hospitalServiceItemDO);
                    object.replace("expense", DataUtils.integerTransferDouble(hospitalServiceItemDO.getExpense()));
                    JSONObject jsonObject = object.getJSONObject("specialistServiceItemDO");
                    jsonObject.replace("threeHospitals", DataUtils.integerTransferDouble(hospitalServiceItemDO.getSpecialistServiceItemDO().getThreeHospitals()));
                    jsonObject.replace("twoHospitals",DataUtils.integerTransferDouble(hospitalServiceItemDO.getSpecialistServiceItemDO().getTwoHospitals()));
                    jsonObject.replace("oneHospitals",DataUtils.integerTransferDouble(hospitalServiceItemDO.getSpecialistServiceItemDO().getOneHospitals()));
                    array1.add(object);
                }
            }
            envelop.setDetailModelList(array1);
            return envelop;
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }


    /**
     * 根据id查找机构服务项目
     *
     * @param ids
     * @return
     */
    @PostMapping(value = SpecialistMapping.serviceItem.selectById)
    @ApiOperation(value = "根据id查找机构服务项目")
    public MixEnvelop<HospitalServiceItemDO,HospitalServiceItemDO> selectById(@ApiParam(name = "ids", value = "机构服务项目集合")
                                                                                    @RequestParam(value = "ids")String ids){
        try {
            JSONArray array = JSONArray.parseArray(ids);
            List<String> idList = new ArrayList<>();
            for (int i =0 ;i<array.size();i++){
                idList.add(array.getString(i));
            }
            MixEnvelop envelop = specialistHospitalServiceItemService.selectById(idList);
            List<HospitalServiceItemDO> hospitalServiceItemDOList = envelop.getDetailModelList();
            JSONArray array1 = new JSONArray();
            if (hospitalServiceItemDOList != null && hospitalServiceItemDOList.size()!=0){
                for (HospitalServiceItemDO hospitalServiceItemDO:hospitalServiceItemDOList){
                    JSONObject object = (JSONObject) JSONObject.toJSON(hospitalServiceItemDO);
                    object.replace("expense", DataUtils.integerTransferDouble(hospitalServiceItemDO.getExpense()));
                    JSONObject jsonObject = object.getJSONObject("specialistServiceItemDO");
                    jsonObject.replace("threeHospitals", DataUtils.integerTransferDouble(hospitalServiceItemDO.getSpecialistServiceItemDO().getThreeHospitals()));
                    jsonObject.replace("twoHospitals",DataUtils.integerTransferDouble(hospitalServiceItemDO.getSpecialistServiceItemDO().getTwoHospitals()));
                    jsonObject.replace("oneHospitals",DataUtils.integerTransferDouble(hospitalServiceItemDO.getSpecialistServiceItemDO().getOneHospitals()));
                    array1.add(object);
                }
            }
            envelop.setDetailModelList(array1);
            return envelop;
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }


    /**
     *
     * @param serviceItemName
     * @param hospitals
     * @return
     */
    @PostMapping(value = SpecialistMapping.serviceItem.selectByCondition)
    @ApiOperation(value = "根据条件查找机构服务项目")
    public MixEnvelop<JSONArray,JSONArray> selectByCondition(@ApiParam(name = "serviceItemName", value = "服务项目名称")
                                                                                    @RequestParam(value = "serviceItemName",required = false)String serviceItemName,
                                                                                     @ApiParam(name = "hospitals",value = "医院集合")
                                                                                     @RequestParam(value = "hospitals",required = false)String hospitals){
        try {
            JSONArray array = JSONArray.parseArray(hospitals);
            List<String> hospitalList = new ArrayList<>();
            for (int i =0 ;i<array.size();i++){
                hospitalList.add(array.getString(i));
            }
            return specialistHospitalServiceItemService.selectByCondition(serviceItemName,hospitalList);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }


    /**
     *
     * @param hospital
     * @param serviceItem
     * @return
     */
    @PostMapping(value = SpecialistMapping.serviceItem.deleteHospitalItem)
    @ApiOperation(value = "删除机构服务项目")
    public MixEnvelop<Boolean,Boolean> delete(@ApiParam(name = "hospital", value = "医院code")
                                              @RequestParam(name = "hospital",required = false)String hospital,
                                              @ApiParam(name = "serviceItem", value = "服务项目code")
                                              @RequestParam(name = "serviceItem",required = false)String serviceItem,
                                              @ApiParam(name = "id", value = "机构服务项目id")
                                                  @RequestParam(name = "id",required = false)String id){
        try {
            return specialistHospitalServiceItemService.delete(hospital,serviceItem,id);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }


    /**
     *
     * @param hospital
     * @param docHospital
     * @param serviceItemName
     * @return
     */
    @PostMapping(value = SpecialistMapping.serviceItem.selectByHospital1)
    @ApiOperation(value = "查询服务项目")
    public MixEnvelop<JSONArray,JSONArray> selectByHospital1(@ApiParam(name = "hospital", value ="社区code")
                                              @RequestParam(name = "hospital",required = false)String hospital,
                                              @ApiParam(name = "docHospital", value = "医院code")
                                              @RequestParam(name = "docHospital")String docHospital,
                                              @ApiParam(name = "serviceItemName", value = "服务项目名称")
                                              @RequestParam(name = "serviceItemName",required = false)String serviceItemName){
        try {
            return specialistHospitalServiceItemService.selectByHospital1(hospital,docHospital,serviceItemName);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

    /**
     * 机构服务项目导数据
     *
     * @param hospitalItems
     * @return
     */
    @RequestMapping(value = SpecialistMapping.serviceItem.importData2)
    @ResponseBody
    public MixEnvelop<Boolean,Boolean> importData(@ApiParam(name = "hospitalItems", value = "机构服务项目集合")
                                                  @RequestParam(value = "hospitalItems")String hospitalItems) {
        try {
            JSONArray array = JSONArray.parseArray(hospitalItems);
            List<HospitalServiceItemDO> hospitalServiceItemDOList = new ArrayList<>();
            for (int i = 0;i<array.size();i++){
                JSONObject object = array.getJSONObject(i);
                object.replace("expense", DataUtils.doubleToInt(object.getDouble("expense")));
                HospitalServiceItemDO hospitalServiceItemDO = toEntity(object.toJSONString(),HospitalServiceItemDO.class);
                hospitalServiceItemDOList.add(hospitalServiceItemDO);
            }
            return specialistHospitalServiceItemService.importData(hospitalServiceItemDOList);
        } catch (Exception e) {
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }
}
