package com.yihu.iot.controller.device;

import com.yihu.iot.service.device.IotPatientDeviceService;
import com.yihu.jw.iot.device.IotPatientDeviceDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.iot.device.IotDeviceVO;
import com.yihu.jw.restmodel.iot.device.IotPatientDeviceVO;
import com.yihu.jw.restmodel.iot.device.LocationDataVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yeshijie on 2018/2/8.
 */
@RestController
@RequestMapping(IotRequestMapping.Common.patientDevice)
@Api(tags = "居民设备管理相关操作", description = "居民设备管理相关操作")
public class IotPatientDeviceController extends EnvelopRestController{

    @Autowired
    private IotPatientDeviceService iotPatientDeviceService;


    @PostMapping(value = IotRequestMapping.PatientDevice.addPatientDevice)
    @ApiOperation(value = "设备绑定", notes = "设备绑定")
    public Envelop<IotPatientDeviceVO> create(@ApiParam(name = "jsonData", value = "", defaultValue = "")
                                       @RequestParam String jsonData) {
        try {
            //设备绑定
            IotPatientDeviceVO deviceVO = toEntity(jsonData, IotPatientDeviceVO.class);
            IotPatientDeviceDO patientDevice = convertToModel(deviceVO, IotPatientDeviceDO.class);
            iotPatientDeviceService.create(patientDevice);
            //地址信息存入es
            iotPatientDeviceService.deviceData2Es(deviceVO);
            return Envelop.getSuccess(IotRequestMapping.Device.message_success_create);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = IotRequestMapping.PatientDevice.findByDeviceSnAndUserType)
    @ApiOperation(value = "按sn码和按键号查找", notes = "按sn码和按键号查找")
    public Envelop<IotPatientDeviceVO> findByDeviceSnAndUserType(
            @ApiParam(name = "deviceSn", value = "sn码", defaultValue = "")
            @RequestParam(value = "deviceSn",required = true) String deviceSn,
            @ApiParam(name = "userType", value = "按键号", defaultValue = "")
            @RequestParam(value = "userType",required = true) String userType) {
        try {
            IotPatientDeviceDO patientDevice = iotPatientDeviceService.findByDeviceSnAndUserType(deviceSn, userType);
            IotPatientDeviceVO patientDeviceVO = convertToModel(patientDevice,IotPatientDeviceVO.class);
            return Envelop.getSuccess(IotRequestMapping.Device.message_success_create,patientDeviceVO);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = IotRequestMapping.PatientDevice.findByPatient)
    @ApiOperation(value = "按居民code查找", notes = "按居民code查找")
    public Envelop<IotPatientDeviceVO> findByPatient(
            @ApiParam(name = "patient", value = "居民code", defaultValue = "")
            @RequestParam(value = "patient",required = true) String patient) {
        try {
            List<IotPatientDeviceDO> list = iotPatientDeviceService.findByPatient(patient);
            //DO转VO
            List<IotPatientDeviceVO> iotPatientDeviceVOList = convertToModels(list,new ArrayList<>(list.size()),IotPatientDeviceVO.class);
            return Envelop.getSuccess(IotRequestMapping.Device.message_success_create,iotPatientDeviceVOList);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = IotRequestMapping.PatientDevice.findByPatientAndDeviceSn)
    @ApiOperation(value = "按居民和sn码查找", notes = "按居民和sn码查找")
    public Envelop<IotPatientDeviceVO> findByPatientAndDeviceSn(
            @ApiParam(name = "patient", value = "居民code", defaultValue = "")
            @RequestParam(value = "patient",required = true) String patient,
            @ApiParam(name = "deviceSn", value = "sn码", defaultValue = "")
            @RequestParam(value = "deviceSn",required = true) String deviceSn) {
        try {
            List<IotPatientDeviceDO> list = iotPatientDeviceService.findByPatientAndDeviceSn(patient,deviceSn);
            //DO转VO
            List<IotPatientDeviceVO> iotPatientDeviceVOList = convertToModels(list,new ArrayList<>(list.size()),IotPatientDeviceVO.class);
            return Envelop.getSuccess(IotRequestMapping.Device.message_success_create,iotPatientDeviceVOList);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
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
        try {
            PageRequest pageRequest = new PageRequest(page - 1, pagesize);
            List<IotPatientDeviceDO> list = iotPatientDeviceService.findByPatient(patient,pageRequest);
            //DO转VO
            List<IotPatientDeviceVO> iotPatientDeviceVOList = convertToModels(list,new ArrayList<>(list.size()),IotPatientDeviceVO.class);
            return Envelop.getSuccess(IotRequestMapping.Company.message_success_find_functions,iotPatientDeviceVOList);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = IotRequestMapping.PatientDevice.findByDeviceSnAndCategoryCode)
    @ApiOperation(value = "按sn码和设备类型查找", notes = "按sn码和设备类型查找")
    public Envelop<IotPatientDeviceVO> findByDeviceSnAndCategoryCode(
            @ApiParam(name = "categoryCode", value = "设备类型", defaultValue = "")
            @RequestParam(value = "categoryCode",required = true) String categoryCode,
            @ApiParam(name = "deviceSn", value = "sn码", defaultValue = "")
            @RequestParam(value = "deviceSn",required = true) String deviceSn) {
        try {
            List<IotPatientDeviceDO> list = iotPatientDeviceService.findByDeviceSnAndCategoryCode(deviceSn,categoryCode);
            //DO转VO
            List<IotPatientDeviceVO> iotPatientDeviceVOList = convertToModels(list,new ArrayList<>(list.size()),IotPatientDeviceVO.class);
            return Envelop.getSuccess(IotRequestMapping.Device.message_success_create,iotPatientDeviceVOList);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = IotRequestMapping.PatientDevice.findByDeviceSnAndCategoryCodeAndUserType)
    @ApiOperation(value = "按sn码，设备类型及按键号查找", notes = "按sn码，设备类型及按键号查找")
    public Envelop<IotPatientDeviceVO> findByDeviceSnAndCategoryCodeAndUserType(
            @ApiParam(name = "deviceSn", value = "sn码", defaultValue = "")
            @RequestParam(value = "deviceSn",required = true) String deviceSn,
            @ApiParam(name = "categoryCode", value = "设备类型", defaultValue = "")
            @RequestParam(value = "categoryCode",required = true) String categoryCode
            ,
            @ApiParam(name = "userType", value = "按键号", defaultValue = "")
            @RequestParam(value = "userType",required = true) String userType) {
        try {
            IotPatientDeviceDO patientDevice = iotPatientDeviceService.findByDeviceSnAndCategoryCodeAndUserType(deviceSn,categoryCode, userType);
            IotPatientDeviceVO patientDeviceVO = convertToModel(patientDevice,IotPatientDeviceVO.class);
            return Envelop.getSuccess(IotRequestMapping.Device.message_success_create,patientDeviceVO);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
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
        try {
            iotPatientDeviceService.updatePatientDevice(patient, deviceSN, newDeviceSN, userType, sim);
            return Envelop.getSuccess(IotRequestMapping.Device.message_success_create);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }


    @GetMapping(value = IotRequestMapping.PatientDevice.findAllLocations)
    @ApiOperation(value = "查询所有设备地址", notes = "查询所有设备地址")
    public Envelop<List<LocationDataVO>> findAllDeviceLocations() {
        try {
            List<LocationDataVO> list = iotPatientDeviceService.findAllDeviceLocations();
            return Envelop.getSuccess(IotRequestMapping.Device.message_success_create,list);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = IotRequestMapping.PatientDevice.findLocationByIdCard)
    @ApiOperation(value = "根据idCard查询设备地址", notes = "根据idCard查询设备地址")
    public Envelop<List<LocationDataVO>> findDeviceLocationsByIdCard(@ApiParam(name = "jsonData", value = "jsonData", defaultValue = "")
                                                                     @RequestParam(value = "jsonData",required = true) String jsonData) {
        try {
            List<LocationDataVO> list = iotPatientDeviceService.findDeviceLocationsByIdCard(jsonData);
            return Envelop.getSuccess(IotRequestMapping.Device.message_success_create,list);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = IotRequestMapping.PatientDevice.findLocationBySn)
    @ApiOperation(value = "根据sn码查询设备地址", notes = "根据sn码查询设备地址")
    public Envelop<List<LocationDataVO>> findDeviceLocationsBySn(@ApiParam(name = "jsonData", value = "jsonData", defaultValue = "")
                                                                     @RequestParam(value = "jsonData",required = true) String jsonData) {
        try {
            List<LocationDataVO> list = iotPatientDeviceService.findDeviceLocationsBySn(jsonData);
            return Envelop.getSuccess(IotRequestMapping.Device.message_success_create,list);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = IotRequestMapping.PatientDevice.deleteLocation)
    @ApiOperation(value = "解绑设备删除地址", notes = "解绑设备删除地址")
    public Envelop deleteLocations(@ApiParam(name = "jsonData", value = "jsonData", defaultValue = "")
                                                                 @RequestParam(value = "jsonData",required = true) String jsonData) {
        try {
            boolean bool = iotPatientDeviceService.deleteLocationsByIdcardOrSn(jsonData);
            if(bool){
                return Envelop.getSuccess(IotRequestMapping.Device.message_success_create,"device delete success");
            }
            return Envelop.getError("delete fail,not exist");

        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = IotRequestMapping.PatientDevice.updateLocation)
    @ApiOperation(value = "设备地址修改", notes = "设备地址修改")
    public Envelop updateLocations(@ApiParam(name = "jsonData", value = "jsonData", defaultValue = "")
                                   @RequestParam(value = "jsonData",required = true) String jsonData) {
        try {
            boolean bool = iotPatientDeviceService.updateLocationsByIdcardOrSn(jsonData);
            if(bool){
                return Envelop.getSuccess(IotRequestMapping.Device.message_success_create,"device update success");
            }
            return Envelop.getError("delete fail,not exist");

        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }
}
