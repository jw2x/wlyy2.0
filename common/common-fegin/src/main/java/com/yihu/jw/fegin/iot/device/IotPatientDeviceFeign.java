package com.yihu.jw.fegin.iot.device;

import com.yihu.jw.fegin.fallbackfactory.iot.device.IotPatientDeviceFallbackFactory;
import com.yihu.jw.common.CommonContants;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.iot.device.IotDeviceVO;
import com.yihu.jw.restmodel.iot.device.IotPatientDeviceVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import iot.device.LocationDataVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    MixEnvelop<IotPatientDeviceVO, IotPatientDeviceVO> create(@RequestParam(value = "jsonData", required = true) String jsonData);

    @GetMapping(value = IotRequestMapping.PatientDevice.findByDeviceSnAndUserType)
    MixEnvelop<IotPatientDeviceVO, IotPatientDeviceVO> findByDeviceSnAndUserType(
            @RequestParam(value = "deviceSn",required = true) String deviceSn,
            @RequestParam(value = "userType",required = true) String userType);

    @GetMapping(value = IotRequestMapping.PatientDevice.findByPatient)
    MixEnvelop<IotPatientDeviceVO, IotPatientDeviceVO> findByPatient(
            @RequestParam(value = "patient",required = true) String patient);

    @GetMapping(value = IotRequestMapping.PatientDevice.findByPatientAndDeviceSn)
    MixEnvelop<IotPatientDeviceVO, IotPatientDeviceVO> findByPatientAndDeviceSn(
            @RequestParam(value = "patient",required = true) String patient,
            @RequestParam(value = "deviceSn",required = true) String deviceSn);

    @GetMapping(value = IotRequestMapping.PatientDevice.findListByPatient)
    MixEnvelop<IotDeviceVO, IotDeviceVO> findListByPatient(
            @RequestParam(value = "patient", required = true) String patient,
            @RequestParam(value = "page", required = true) Integer page,
            @RequestParam(value = "pagesize", required = true) Integer pagesize);

    @GetMapping(value = IotRequestMapping.PatientDevice.findByDeviceSnAndCategoryCode)
    MixEnvelop<IotPatientDeviceVO, IotPatientDeviceVO> findByDeviceSnAndCategoryCode(
            @RequestParam(value = "categoryCode",required = true) String categoryCode,
            @RequestParam(value = "deviceSn",required = true) String deviceSn);

    @GetMapping(value = IotRequestMapping.PatientDevice.findByDeviceSnAndCategoryCodeAndUserType)
    MixEnvelop<IotPatientDeviceVO, IotPatientDeviceVO> findByDeviceSnAndCategoryCodeAndUserType(
            @RequestParam(value = "deviceSn",required = true) String deviceSn,
            @RequestParam(value = "categoryCode",required = true) String categoryCode,
           @RequestParam(value = "userType",required = true) String userType);

    @PostMapping(value = IotRequestMapping.PatientDevice.updatePatientDevice)
    MixEnvelop<IotPatientDeviceVO, IotPatientDeviceVO> updatePatientDevice(
            @RequestParam(value = "patient",required = true) String patient,
            @RequestParam(value = "deviceSN",required = true) String deviceSN,
            @RequestParam(value = "newDeviceSN",required = true) String newDeviceSN,
            @RequestParam(value = "userType",required = true) String userType,
            @RequestParam(value = "sim",required = true) String sim);

    @GetMapping(value = IotRequestMapping.PatientDevice.findLocationByIdCard)
    public MixEnvelop<LocationDataVO, LocationDataVO> findDeviceLocationsByIdCard(@RequestParam(value = "jsonData",required = true) String jsonData);
}
