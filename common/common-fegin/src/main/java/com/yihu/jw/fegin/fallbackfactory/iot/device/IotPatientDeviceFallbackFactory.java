package com.yihu.jw.fegin.fallbackfactory.iot.device;

import com.yihu.jw.fegin.iot.device.IotPatientDeviceFeign;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.iot.device.IotDeviceVO;
import com.yihu.jw.restmodel.iot.device.IotPatientDeviceVO;
import com.yihu.jw.restmodel.iot.device.LocationDataVO;
import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yeshijie on 2018/2/8.
 */
@Component
public class IotPatientDeviceFallbackFactory implements FallbackFactory<IotPatientDeviceFeign> {

    @Autowired
    private Tracer tracer;

    @Override
    public IotPatientDeviceFeign create(Throwable e) {
        return new IotPatientDeviceFeign() {

            @Override
            public Envelop<IotPatientDeviceVO> create(
                    @RequestParam String jsonData) {
                tracer.getCurrentSpan().logEvent("设备绑定失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                return null;
            }

            @Override
            public Envelop<IotPatientDeviceVO> findByDeviceSnAndUserType(
                    @RequestParam(value = "deviceSn",required = true) String deviceSn,
                    @RequestParam(value = "userType",required = true) String userType) {
                tracer.getCurrentSpan().logEvent("按sn码和按键号查找失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("deviceSn:" + deviceSn);
                tracer.getCurrentSpan().logEvent("userType:" + userType);
                return null;
            }

            @Override
            public Envelop<IotPatientDeviceVO> findByPatient(
                    @RequestParam(value = "patient",required = true) String patient) {
                tracer.getCurrentSpan().logEvent("按居民code查找失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("patient:" + patient);
                return null;
            }

            @Override
            public Envelop<IotPatientDeviceVO> findByPatientAndDeviceSn(
                    @RequestParam(value = "patient",required = true) String patient,
                    @RequestParam(value = "deviceSn",required = true) String deviceSn) {
                tracer.getCurrentSpan().logEvent("按居民和sn码查找失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("patient:" + patient);
                tracer.getCurrentSpan().logEvent("deviceSn:" + deviceSn);
                return null;
            }

            @Override
            public Envelop<IotDeviceVO> findListByPatient(
                    @RequestParam(value = "patient", required = true) String patient,
                    @RequestParam(value = "page", required = true) Integer page,
                    @RequestParam(value = "pagesize", required = true) Integer pagesize){
                tracer.getCurrentSpan().logEvent("按居民分页查找失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("patient:" + patient);
                tracer.getCurrentSpan().logEvent("page:" + page);
                tracer.getCurrentSpan().logEvent("pagesize:" + pagesize);
                return null;
            }

            @Override
            public Envelop<IotPatientDeviceVO> findByDeviceSnAndCategoryCode(
                    @RequestParam(value = "categoryCode",required = true) String categoryCode,
                    @RequestParam(value = "deviceSn",required = true) String deviceSn) {
                tracer.getCurrentSpan().logEvent("按sn码和设备类型查找失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("categoryCode:" + categoryCode);
                tracer.getCurrentSpan().logEvent("deviceSn:" + deviceSn);
                return null;
            }

            @Override
            public Envelop<IotPatientDeviceVO> findByDeviceSnAndCategoryCodeAndUserType(
                    @RequestParam(value = "deviceSn",required = true) String deviceSn,
                    @RequestParam(value = "categoryCode",required = true) String categoryCode,
                    @RequestParam(value = "userType",required = true) String userType) {
                tracer.getCurrentSpan().logEvent("按sn码，设备类型及按键号查找失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("categoryCode:" + categoryCode);
                tracer.getCurrentSpan().logEvent("deviceSn:" + deviceSn);
                tracer.getCurrentSpan().logEvent("userType:" + userType);
                return null;
            }

            @Override
            public Envelop<IotPatientDeviceVO> updatePatientDevice(
                    @RequestParam(value = "patient",required = true) String patient,
                    @RequestParam(value = "deviceSN",required = true) String deviceSN,
                    @RequestParam(value = "newDeviceSN",required = true) String newDeviceSN,
                    @RequestParam(value = "userType",required = true) String userType,
                    @RequestParam(value = "sim",required = true) String sim) {
                tracer.getCurrentSpan().logEvent("更换患者绑定的血糖仪失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("patient:" + patient);
                tracer.getCurrentSpan().logEvent("deviceSN:" + deviceSN);
                tracer.getCurrentSpan().logEvent("newDeviceSN:" + newDeviceSN);
                tracer.getCurrentSpan().logEvent("userType:" + userType);
                tracer.getCurrentSpan().logEvent("sim:" + sim);
                return null;
            }

            @Override
            public Envelop<LocationDataVO> findDeviceLocationsByIdCard(
                    @RequestParam(value = "jsonData",required = true) String jsonData) {
                tracer.getCurrentSpan().logEvent("查询设备地址失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                return null;
            }

        };
    }

}
