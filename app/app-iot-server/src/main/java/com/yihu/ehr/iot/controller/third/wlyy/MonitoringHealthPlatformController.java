package com.yihu.ehr.iot.controller.third.wlyy;

import com.yihu.ehr.iot.controller.common.BaseController;
import com.yihu.ehr.iot.service.third.wlyy.MonitoringHealthService;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.iot.device.LocationDataVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 远程监测健康平台-访问wlyy
 * @author yeshijie on 2018/2/11.
 */
@RestController
@RequestMapping(IotRequestMapping.Common.wlyy)
@Api(tags = "远程监测健康平台", description = "远程监测健康平台")
public class MonitoringHealthPlatformController extends BaseController{

    @Autowired
    private MonitoringHealthService monitoringHealthService;

    @RequestMapping(value = "/equipmentDistribution",method = RequestMethod.GET)
    @ApiOperation("设备发放情况")
    public String equipmentDistribution(){
        try {
            return monitoringHealthService.equipmentDistribution();
        }catch (Exception e){
            e.printStackTrace();
            return error(-1,"查询失败");
        }
    }

    @GetMapping(value = IotRequestMapping.PatientDevice.findLocationByIdCard)
    @ApiOperation(value = "根据idCard查询设备地址", notes = "根据idCard查询设备地址")
    public Envelop<List<LocationDataVO>> findDeviceLocationsByIdCard(
            @ApiParam(name = "diseaseCondition", value = "病情：0绿标，1黄标，2红标,-1没有标注的居民", defaultValue = "")
            @RequestParam(value = "diseaseCondition",required = false) Integer diseaseCondition,
            @ApiParam(name = "type", value = "1高血压 2糖尿病", defaultValue = "")
            @RequestParam(value = "type",required = false) String type,
            @ApiParam(name="page",value="第几页(默认第一页)",defaultValue = "1")
            @RequestParam(value="page",required = false) Integer page,
            @ApiParam(name="pageSize",value="每页几行(默认10条记录)",defaultValue = "10")
            @RequestParam(value="pageSize",required = false) Integer pageSize) {
        try {
            if(page==null){
                page = 1;
            }
            if(pageSize==null){
                pageSize = 10;
            }
            return monitoringHealthService.findDeviceLocations(diseaseCondition,page,pageSize,type);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @RequestMapping(value = "/chronicDiseaseCount",method = RequestMethod.GET)
    @ApiOperation("慢病患者情况-统计")
    public String chronicDiseaseCount(
            @ApiParam(name="type",value="类型(2糖尿病，1高血压)",defaultValue = "")
            @RequestParam(value="type",required = false) String type){
        try {

            return monitoringHealthService.chronicDiseaseCount(type);
        }catch (Exception e){
            e.printStackTrace();
            return error(-1,"查询失败");
        }
    }

    @RequestMapping(value = "/searchPatient",method = RequestMethod.GET)
    @ApiOperation("居民 医生搜索")
    public String searchPatient(
            @ApiParam(name="name",value="姓名")
            @RequestParam(value="name",required = false) String name,
            @ApiParam(name="page",value="第几页(默认第一页)",defaultValue = "1")
            @RequestParam(value="page",required = false) Integer page,
            @ApiParam(name="pageSize",value="每页几行(默认10条记录)",defaultValue = "10")
            @RequestParam(value="pageSize",required = false) Integer pageSize){
        try {
            if(page==null){
                page = 1;
            }
            if(pageSize==null){
                pageSize = 10;
            }
            return monitoringHealthService.searchPatient(name,page,pageSize);
        }catch (Exception e){
            e.printStackTrace();
            return error(-1,"查询失败");
        }
    }


    @RequestMapping(value = "/warningInformationAlarm",method = RequestMethod.GET)
    @ApiOperation("预警信息警报")
    public String warningInformationAlarm(
            @ApiParam(name="page",value="第几页(默认第一页)",defaultValue = "1")
            @RequestParam(value="page",required = false) Integer page,
            @ApiParam(name="pageSize",value="每页几行(默认10条记录)",defaultValue = "10")
            @RequestParam(value="pageSize",required = false) Integer pageSize){
        try {
            if(page==null){
                page = 1;
            }
            if(pageSize==null){
                pageSize = 10;
            }
            return monitoringHealthService.warningInformationAlarm(page,pageSize);
        }catch (Exception e){
            e.printStackTrace();
            return error(-1,"查询失败");
        }
    }


    @RequestMapping(value = "/deviceBinding",method = RequestMethod.GET)
    @ApiOperation("设备绑定情况")
    public String deviceBinding(
            @ApiParam(name="type",value="设备类型(1血糖仪，2血压计)",defaultValue = "")
            @RequestParam(value="type",required = false) String type){
        try {

            return monitoringHealthService.deviceBinding(type);
        }catch (Exception e){
            e.printStackTrace();
            return error(-1,"查询失败");
        }
    }


    @RequestMapping(value = "/persionalInfo",method = RequestMethod.GET)
    @ApiOperation("个人信息")
    public String persionalInfo(@ApiParam(name="patient",value="居民code",defaultValue = "")
                                @RequestParam(value="patient",required = true) String patient){
        try {
            return monitoringHealthService.persionalInfo(patient);
        }catch (Exception e){
            e.printStackTrace();
            return error(-1,"查询失败");
        }
    }

    @RequestMapping(value = "/familyMember",method = RequestMethod.GET)
    @ApiOperation("家人信息")
    public String familyMember(@ApiParam(name="patient",value="居民code",defaultValue = "")
                               @RequestParam(value="patient",required = true) String patient){
        try {
            return monitoringHealthService.familyMember(patient);
        }catch (Exception e){
            e.printStackTrace();
            return error(-1,"查询失败");
        }
    }

    @RequestMapping(value = "/healthDevice",method = RequestMethod.GET)
    @ApiOperation("健康设备")
    public String healthDevice(@ApiParam(name="patient",value="居民code",defaultValue = "")
                               @RequestParam(value="patient",required = true) String patient){
        try {
            return monitoringHealthService.healthDevice(patient);
        }catch (Exception e){
            e.printStackTrace();
            return error(-1,"查询失败");
        }
    }

    @RequestMapping(value = "chart", method = RequestMethod.GET)
    @ApiOperation("根据患者标志获取健康指标(图表)")
    public String getHealthIndexChartByPatient(@ApiParam(name="patient",value="居民code",defaultValue = "")
                                               @RequestParam(value="patient",required = true) String patient,
                                               @ApiParam(name = "type", value = "指标类型（1血糖，2血压，3体重，4腰围）", defaultValue = "1")
                                               @RequestParam(value = "type", required = true) Integer type,
                                               @ApiParam(name = "gi_type", value = "就餐类型0全部", defaultValue = "1")
                                               @RequestParam(value = "gi_type", required = false) Integer gi_type,
                                               @ApiParam(name="time",value="时间（一周，一月，半年）",defaultValue = "")
                                               @RequestParam(value="time",required = true) String time,
                                               @ApiParam(name = "begin", value = "开始时间", defaultValue = "2017-05-22")
                                               @RequestParam(value = "begin", required = true) String begin,
                                               @ApiParam(name = "end", value = "结束时间", defaultValue = "2018-06-02")
                                               @RequestParam(value = "end", required = true) String end) {
        try {
            return monitoringHealthService.getHealthIndexChartByPatient(patient,type,gi_type,begin,end,time);
        } catch (Exception e) {
            e.printStackTrace();
            return error(-1,"查询失败");
        }
    }

    @ApiOperation("获取门诊记录/住院记录(基卫+APP)")
    @RequestMapping(value = "/event", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public String getAllEvent(@ApiParam(name = "patient", value = "患者代码", defaultValue = "")
                              @RequestParam(value = "patient", required = true) String patient,
                              @ApiParam(name = "type", value = "类型(1血糖，2血压)", defaultValue = "")
                              @RequestParam(value = "type", required = false) String type,
                              @ApiParam(name = "page", value = "第几页", defaultValue = "1")
                              @RequestParam(value = "page", required = true) String page,
                              @ApiParam(name = "pageSize", value = "每页几行", defaultValue = "10")
                              @RequestParam(value = "pageSize", required = true) String pageSize) {
        try {
            return monitoringHealthService.getAllEvent(patient, type, page, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            return error(-1,"查询失败");
        }
    }
}
