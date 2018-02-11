package com.yihu.jw.feign.fallbackfactory.iot.device;

import com.yihu.jw.feign.iot.device.IotPatientDeviceFeign;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.iot.device.IotDeviceVO;
import com.yihu.jw.restmodel.iot.device.IotPatientDeviceVO;
import feign.hystrix.FallbackFactory;
import io.swagger.annotations.ApiParam;
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
            public Envelop<IotPatientDeviceVO> create(@ApiParam(name = "json_data", value = "", defaultValue = "")
                                                      @RequestParam String jsonData) {
                tracer.getCurrentSpan().logEvent("设备绑定失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                return null;
            }

            @Override
            public Envelop<IotPatientDeviceVO> findByDeviceSnAndUserType(
                    @ApiParam(name = "deviceSn", value = "sn码", defaultValue = "")
                    @RequestParam(value = "deviceSn",required = true) String deviceSn,
                    @ApiParam(name = "userType", value = "按键号", defaultValue = "")
                    @RequestParam(value = "userType",required = true) String userType) {
                tracer.getCurrentSpan().logEvent("按sn码和按键号查找失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("deviceSn:" + deviceSn);
                tracer.getCurrentSpan().logEvent("userType:" + userType);
                return null;
            }

            @Override
            public Envelop<IotPatientDeviceVO> findByPatient(
                    @ApiParam(name = "patient", value = "居民code", defaultValue = "")
                    @RequestParam(value = "patient",required = true) String patient) {
                tracer.getCurrentSpan().logEvent("按居民code查找失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("patient:" + patient);
                return null;
            }

            @Override
            public Envelop<IotPatientDeviceVO> findByPatientAndDeviceSn(
                    @ApiParam(name = "patient", value = "居民code", defaultValue = "")
                    @RequestParam(value = "patient",required = true) String patient,
                    @ApiParam(name = "deviceSn", value = "sn码", defaultValue = "")
                    @RequestParam(value = "deviceSn",required = true) String deviceSn) {
                tracer.getCurrentSpan().logEvent("按居民和sn码查找失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("patient:" + patient);
                tracer.getCurrentSpan().logEvent("deviceSn:" + deviceSn);
                return null;
            }

            @Override
            public Envelop<IotDeviceVO> findListByPatient(
                    @ApiParam(name = "patient", value = "居民code", defaultValue = "")
                    @RequestParam(value = "patient", required = true) String patient,
                    @ApiParam(name = "page", value = "第几页", defaultValue = "")
                    @RequestParam(value = "page", required = true) Integer page,
                    @ApiParam(name = "pagesize", value = "页面大小", defaultValue = "")
                    @RequestParam(value = "pagesize", required = true) Integer pagesize){
                tracer.getCurrentSpan().logEvent("按居民分页查找失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("patient:" + patient);
                tracer.getCurrentSpan().logEvent("page:" + page);
                tracer.getCurrentSpan().logEvent("pagesize:" + pagesize);
                return null;
            }

            @Override
            public Envelop<IotPatientDeviceVO> findByDeviceSnAndCategoryCode(
                    @ApiParam(name = "categoryCode", value = "设备类型", defaultValue = "")
                    @RequestParam(value = "categoryCode",required = true) String categoryCode,
                    @ApiParam(name = "deviceSn", value = "sn码", defaultValue = "")
                    @RequestParam(value = "deviceSn",required = true) String deviceSn) {
                tracer.getCurrentSpan().logEvent("按sn码和设备类型查找失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("categoryCode:" + categoryCode);
                tracer.getCurrentSpan().logEvent("deviceSn:" + deviceSn);
                return null;
            }

            @Override
            public Envelop<IotPatientDeviceVO> findByDeviceSnAndCategoryCodeAndUserType(
                    @ApiParam(name = "deviceSn", value = "sn码", defaultValue = "")
                    @RequestParam(value = "deviceSn",required = true) String deviceSn,
                    @ApiParam(name = "categoryCode", value = "设备类型", defaultValue = "")
                    @RequestParam(value = "categoryCode",required = true) String categoryCode,
                    @ApiParam(name = "userType", value = "按键号", defaultValue = "")
                    @RequestParam(value = "userType",required = true) String userType) {
                tracer.getCurrentSpan().logEvent("按sn码，设备类型及按键号查找失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("categoryCode:" + categoryCode);
                tracer.getCurrentSpan().logEvent("deviceSn:" + deviceSn);
                tracer.getCurrentSpan().logEvent("userType:" + userType);
                return null;
            }

            @Override
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
                tracer.getCurrentSpan().logEvent("更换患者绑定的血糖仪失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("patient:" + patient);
                tracer.getCurrentSpan().logEvent("deviceSN:" + deviceSN);
                tracer.getCurrentSpan().logEvent("newDeviceSN:" + newDeviceSN);
                tracer.getCurrentSpan().logEvent("userType:" + userType);
                tracer.getCurrentSpan().logEvent("sim:" + sim);
                return null;
            }

        };
    }

}
