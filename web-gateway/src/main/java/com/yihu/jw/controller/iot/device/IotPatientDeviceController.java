package com.yihu.jw.controller.iot.device;

import com.yihu.jw.common.iot.IotCommonContants;
import com.yihu.jw.fegin.iot.device.IotPatientDeviceFeign;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.iot.device.IotDeviceVO;
import com.yihu.jw.restmodel.iot.device.IotPatientDeviceVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import iot.device.LocationDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yeshijie on 2018/2/8.
 */
@RestController
@RequestMapping(IotCommonContants.Common.patientDevice)
@Api(tags = "居民设备管理相关操作", description = "居民设备管理相关操作")
public class IotPatientDeviceController extends EnvelopRestController{

    @Autowired
    private IotPatientDeviceFeign iotPatientDeviceFeign;


    @PostMapping(value = IotRequestMapping.PatientDevice.addPatientDevice)
    @ApiOperation(value = "设备绑定", notes = "设备绑定")
    public Envelop<IotPatientDeviceVO> create(@ApiParam(name = "jsonData", value = "", defaultValue = "")
                                       @RequestParam String jsonData) {
        return iotPatientDeviceFeign.create(jsonData);
    }

    @GetMapping(value = IotRequestMapping.PatientDevice.findByDeviceSnAndUserType)
    @ApiOperation(value = "按sn码和按键号查找", notes = "按sn码和按键号查找")
    public Envelop<IotPatientDeviceVO> findByDeviceSnAndUserType(
            @ApiParam(name = "deviceSn", value = "sn码", defaultValue = "")
            @RequestParam(value = "deviceSn",required = true) String deviceSn,
            @ApiParam(name = "userType", value = "按键号", defaultValue = "")
            @RequestParam(value = "userType",required = true) String userType) {
        return iotPatientDeviceFeign.findByDeviceSnAndUserType(deviceSn, userType);
    }

    @GetMapping(value = IotRequestMapping.PatientDevice.findByPatient)
    @ApiOperation(value = "按居民code查找", notes = "按居民code查找")
    public Envelop<IotPatientDeviceVO> findByPatient(
            @ApiParam(name = "patient", value = "居民code", defaultValue = "")
            @RequestParam(value = "patient",required = true) String patient) {
        return iotPatientDeviceFeign.findByPatient(patient);
    }

    @GetMapping(value = IotRequestMapping.PatientDevice.findByPatientAndDeviceSn)
    @ApiOperation(value = "按居民和sn码查找", notes = "按居民和sn码查找")
    public Envelop<IotPatientDeviceVO> findByPatientAndDeviceSn(
            @ApiParam(name = "patient", value = "居民code", defaultValue = "")
            @RequestParam(value = "patient",required = true) String patient,
            @ApiParam(name = "deviceSn", value = "sn码", defaultValue = "")
            @RequestParam(value = "deviceSn",required = true) String deviceSn) {
        return iotPatientDeviceFeign.findByPatientAndDeviceSn(patient, deviceSn);
    }

    @GetMapping(value = IotRequestMapping.PatientDevice.findListByPatient)
    @ApiOperation(value = "按居民分页查找", notes = "按居民分页查找")
    public Envelop<IotDeviceVO> findListByPatient(
            @ApiParam(name = "patient", value = "居民code", defaultValue = "")
            @RequestParam(value = "patient", required = true) String patient,
            @ApiParam(name = "page", value = "第几页", defaultValue = "")
            @RequestParam(value = "page", required = true) Integer page,
            @ApiParam(name = "pagesize", value = "页面大小", defaultValue = "")
            @RequestParam(value = "pagesize", required = true) Integer pagesize){
        return iotPatientDeviceFeign.findListByPatient(patient, page, pagesize);
    }

    @GetMapping(value = IotRequestMapping.PatientDevice.findByDeviceSnAndCategoryCode)
    @ApiOperation(value = "按sn码和设备类型查找", notes = "按sn码和设备类型查找")
    public Envelop<IotPatientDeviceVO> findByDeviceSnAndCategoryCode(
            @ApiParam(name = "categoryCode", value = "设备类型", defaultValue = "")
            @RequestParam(value = "categoryCode",required = true) String categoryCode,
            @ApiParam(name = "deviceSn", value = "sn码", defaultValue = "")
            @RequestParam(value = "deviceSn",required = true) String deviceSn) {
        return iotPatientDeviceFeign.findByDeviceSnAndCategoryCode(categoryCode, deviceSn);
    }

    @GetMapping(value = IotRequestMapping.PatientDevice.findByDeviceSnAndCategoryCodeAndUserType)
    @ApiOperation(value = "按sn码，设备类型及按键号查找", notes = "按sn码，设备类型及按键号查找")
    public Envelop<IotPatientDeviceVO> findByDeviceSnAndCategoryCodeAndUserType(
            @ApiParam(name = "deviceSn", value = "sn码", defaultValue = "")
            @RequestParam(value = "deviceSn",required = true) String deviceSn,
            @ApiParam(name = "categoryCode", value = "设备类型", defaultValue = "")
            @RequestParam(value = "categoryCode",required = true) String categoryCode,
            @ApiParam(name = "userType", value = "按键号", defaultValue = "")
            @RequestParam(value = "userType",required = true) String userType) {
        return iotPatientDeviceFeign.findByDeviceSnAndCategoryCodeAndUserType(deviceSn, categoryCode, userType);
    }


    @PostMapping(value = IotRequestMapping.PatientDevice.updatePatientDevice)
    @ApiOperation(value = "更换患者绑定的血糖仪", notes = "更换患者绑定的血糖仪")
    public Envelop<IotPatientDeviceVO> updatePatientDevice(
            @ApiParam(name = "patient", value = "sn码", defaultValue = "")
            @RequestParam(value = "patient",required = true) String patient,
            @ApiParam(name = "deviceSN", value = "设备类型", defaultValue = "")
            @RequestParam(value = "deviceSN",required = true) String deviceSN,
            @ApiParam(name = "newDeviceSN", value = "设备类型", defaultValue = "")
            @RequestParam(value = "newDeviceSN",required = true) String newDeviceSN,
            @ApiParam(name = "userType", value = "sn码", defaultValue = "")
            @RequestParam(value = "userType",required = true) String userType,
            @ApiParam(name = "sim", value = "按键号", defaultValue = "")
            @RequestParam(value = "sim",required = true) String sim) {
        return iotPatientDeviceFeign.updatePatientDevice(patient, deviceSN, newDeviceSN, userType, sim);
    }

    @GetMapping(value = IotRequestMapping.PatientDevice.findLocationByIdCard)
    @ApiOperation(value = "根据idCard查询设备地址", notes = "根据idCard查询设备地址")
    public Envelop<LocationDataVO> findDeviceLocationsByIdCard(@ApiParam(name = "jsonData", value = "jsonData", defaultValue = "")
                                                                     @RequestParam(value = "jsonData",required = true) String jsonData) {
        return iotPatientDeviceFeign.findDeviceLocationsByIdCard(jsonData);
    }

}
