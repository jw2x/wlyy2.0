package com.yihu.jw.feign.iot.device;

import com.yihu.jw.feign.fallbackfactory.iot.device.IotPatientDeviceFallbackFactory;
import com.yihu.jw.restmodel.CommonContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.iot.device.IotDeviceVO;
import com.yihu.jw.restmodel.iot.device.IotPatientDeviceVO;
import com.yihu.jw.restmodel.iot.device.LocationDataVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author yeshijie on 2018/2/8.
 */
@FeignClient(
        name = CommonContants.svr_iot // name值是eurika的实例名字
        ,fallbackFactory  = IotPatientDeviceFallbackFactory.class
)
@RequestMapping(IotRequestMapping.Common.patientDevice)
public interface IotPatientDeviceFeign{

    @PostMapping(value = IotRequestMapping.PatientDevice.addPatientDevice)
    Envelop<IotPatientDeviceVO> create(@RequestParam(value = "jsonData", required = true) String jsonData);

    @GetMapping(value = IotRequestMapping.PatientDevice.findByDeviceSnAndUserType)
    Envelop<IotPatientDeviceVO> findByDeviceSnAndUserType(
            @RequestParam(value = "deviceSn",required = true) String deviceSn,
            @RequestParam(value = "userType",required = true) String userType);

    @GetMapping(value = IotRequestMapping.PatientDevice.findByPatient)
    Envelop<IotPatientDeviceVO> findByPatient(
            @RequestParam(value = "patient",required = true) String patient);

    @GetMapping(value = IotRequestMapping.PatientDevice.findByPatientAndDeviceSn)
    Envelop<IotPatientDeviceVO> findByPatientAndDeviceSn(
            @RequestParam(value = "patient",required = true) String patient,
            @RequestParam(value = "deviceSn",required = true) String deviceSn);

    @GetMapping(value = IotRequestMapping.PatientDevice.findListByPatient)
    Envelop<IotDeviceVO> findListByPatient(
            @RequestParam(value = "patient", required = true) String patient,
            @RequestParam(value = "page", required = true) Integer page,
            @RequestParam(value = "pagesize", required = true) Integer pagesize);

    @GetMapping(value = IotRequestMapping.PatientDevice.findByDeviceSnAndCategoryCode)
    Envelop<IotPatientDeviceVO> findByDeviceSnAndCategoryCode(
            @RequestParam(value = "categoryCode",required = true) String categoryCode,
            @RequestParam(value = "deviceSn",required = true) String deviceSn);

    @GetMapping(value = IotRequestMapping.PatientDevice.findByDeviceSnAndCategoryCodeAndUserType)
    Envelop<IotPatientDeviceVO> findByDeviceSnAndCategoryCodeAndUserType(
            @RequestParam(value = "deviceSn",required = true) String deviceSn,
            @RequestParam(value = "categoryCode",required = true) String categoryCode,
           @RequestParam(value = "userType",required = true) String userType);

    @PostMapping(value = IotRequestMapping.PatientDevice.updatePatientDevice)
    Envelop<IotPatientDeviceVO> updatePatientDevice(
            @RequestParam(value = "patient",required = true) String patient,
            @RequestParam(value = "deviceSN",required = true) String deviceSN,
            @RequestParam(value = "newDeviceSN",required = true) String newDeviceSN,
            @RequestParam(value = "userType",required = true) String userType,
            @RequestParam(value = "sim",required = true) String sim);

    @GetMapping(value = IotRequestMapping.PatientDevice.findLocationByIdCard)
    public Envelop<List<LocationDataVO>> findDeviceLocationsByIdCard(@RequestParam(value = "jsonData",required = true) String jsonData);
}
