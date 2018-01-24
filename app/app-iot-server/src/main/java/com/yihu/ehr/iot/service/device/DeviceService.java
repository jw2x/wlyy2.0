package com.yihu.ehr.iot.service.device;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yihu.base.fastdfs.FastDFSHelper;
import com.yihu.ehr.iot.constant.ServiceApi;
import com.yihu.ehr.iot.service.common.BaseService;
import com.yihu.ehr.iot.util.excel.AExcelReader;
import com.yihu.ehr.iot.util.excel.reader.IotDeviceImportVOReader;
import com.yihu.ehr.iot.util.http.HttpHelper;
import com.yihu.ehr.iot.util.http.HttpResponse;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.base.BaseEnvelop;
import com.yihu.jw.restmodel.iot.common.ExistVO;
import com.yihu.jw.restmodel.iot.device.IotDeviceImportRecordVO;
import com.yihu.jw.restmodel.iot.device.IotDeviceImportVO;
import com.yihu.jw.restmodel.iot.device.IotDeviceVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yeshijie on 2017/12/8.
 */
@Service
public class DeviceService extends BaseService{

    @Autowired
    private FastDFSHelper fastDFSHelper;

    /**
     * 创建设备
     * @param jsonData
     * @return
     * @throws IOException
     */
    public Envelop<IotDeviceVO> create(@ApiParam(name = "json_data", value = "", defaultValue = "")
                          @RequestBody String jsonData) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("jsonData", jsonData);
        HttpResponse response = HttpHelper.post(iotUrl + ServiceApi.Device.CreateDevice, params);
        Envelop<IotDeviceVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }

    /**
     * 根据code查找设备
     * @param id
     * @return
     * @throws IOException
     */
    @GetMapping(value = IotRequestMapping.Device.api_getById)
    @ApiOperation(value = "根据code查找设备", notes = "根据code查找设备")
    public Envelop<IotDeviceVO> findByCode(String id)throws IOException{
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        HttpResponse response = HttpHelper.get(iotUrl + ServiceApi.Device.GetDeviceById, params);
        Envelop<IotDeviceVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }

    /**
     * sn码是否存在
     * @param sn
     * @return
     * @throws IOException
     */
    public Envelop<ExistVO> isSnExist(String sn) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("sn", sn);
        HttpResponse response = HttpHelper.get(iotUrl + ServiceApi.Device.IsSnExist, params);
        Envelop<ExistVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }

    /**
     * sim卡号是否存在
     * @param sim
     * @return
     * @throws IOException
     */
    public Envelop<ExistVO> isSimExist(String sim) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("sim", sim);
        HttpResponse response = HttpHelper.get(iotUrl + ServiceApi.Device.IsSimExist, params);
        Envelop<ExistVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }

    /**
     * 修改sim卡号
     * @param sim
     * @param id
     * @return
     */
    public BaseEnvelop updSim(String sim,String id) throws IOException{
        Map<String, Object> params = new HashMap<>();
        params.put("sim", sim);
        HttpResponse response = HttpHelper.get(iotUrl + ServiceApi.Device.UpdSim, params);
        BaseEnvelop envelop = objectMapper.readValue(response.getBody(),BaseEnvelop.class);
        return envelop;
    }

    /**
     * 分页查找设备
     * @param sn
     * @param hospital
     * @param orderId
     * @param purcharseId
     * @param page
     * @param size
     * @return
     * @throws IOException
     */
    public Envelop<IotDeviceVO> findProductPageByCompanyId(String sn,String hospital,String orderId,
                String purcharseId,Integer page,Integer size) throws IOException{
        Map<String, Object> params = new HashMap<>();
        params.put("sn", sn);
        params.put("hospital", hospital);
        params.put("orderId", orderId);
        params.put("purcharseId", purcharseId);
        params.put("page", page);
        params.put("size", size);
        HttpResponse response = HttpHelper.get(iotUrl + ServiceApi.Device.QueryDevicePage, params);
        Envelop<IotDeviceVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }

    /**
     * 是否正在导入设备数据
     * @param purcharseId
     * @return
     */
    public Envelop<ExistVO> isImportDevice(String purcharseId) throws IOException {
        Envelop<ExistVO> envelop = objectMapper.readValue(isImporting(purcharseId),Envelop.class);
        return envelop;
    }

    /**
     * 是否正在导入设备数据
     * @param purcharseId
     * @return
     */
    public String isImporting(String purcharseId){
        Map<String, Object> params = new HashMap<>();
        params.put("purcharseId", purcharseId);
        HttpResponse response = HttpHelper.get(iotUrl + ServiceApi.Device.IsImportDevice, params);
        return response.getBody();
    }

    /**
     * 设备导入
     * @param file
     * @param purcharseId
     * @return
     * @throws IOException
     */
    public Envelop<IotDeviceImportRecordVO> uploadStream(MultipartFile file,String purcharseId) throws Exception {
        String res = isImporting(purcharseId);
        JSONObject json = JSON.parseObject(res);
        if(json.getInteger("status")==200){
            if(json.getJSONObject("obj").getInteger("isExist")==1){
                return Envelop.getError("已存在导入中的数据，请等待上次数据处理完后在导入");
            }
        }else {
            return Envelop.getError("查询是否在导入中失败");
        }

        //文件上传
        // 得到文件的完整名称  xxx.txt
        String fullName = file.getOriginalFilename();
        //得到文件类型
        String fileType = fullName.substring(fullName.lastIndexOf(".") + 1).toLowerCase();
        if(!"xls".equals(fileType)){
            return Envelop.getError("文件格式不正确");
        }
        String fileName = fullName.substring(0, fullName.lastIndexOf("."));

        //上传到fastdfs
        ObjectNode objectNode = fastDFSHelper.upload(file.getInputStream(), fileType, "");

        //解析excel封装对象
        AExcelReader excelReader = new IotDeviceImportVOReader();
        excelReader.read(file.getInputStream());
        List<IotDeviceImportVO> correctLs = excelReader.getCorrectLs();

        Map<String, Object> params = new HashMap<>();
        params.put("purcharseId", purcharseId);
        params.put("jsonData", JSONObject.toJSONString(correctLs));
        params.put("fileName", fileName);
        params.put("url", objectNode.get("fid").toString().replaceAll("\"", ""));
        String ret = HttpHelper.postBody(iotUrl + ServiceApi.Device.ImportDevice,JSONObject.toJSONString(params));
        Envelop<IotDeviceImportRecordVO> envelop = objectMapper.readValue(ret,Envelop.class);
//        HttpResponse response = HttpHelper.post(iotUrl + ServiceApi.Device.ImportDevice, params);
//        Envelop<IotDeviceImportRecordVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }

    /**
     * 分页查找导入记录
     * @param purcharseId
     * @param page
     * @param size
     * @return
     * @throws IOException
     */
    public Envelop<IotDeviceImportRecordVO> queryImportRecordPage(String purcharseId,Integer page,Integer size) throws IOException{
        Map<String, Object> params = new HashMap<>();
        params.put("purcharseId", purcharseId);
        params.put("page", page);
        params.put("size", size);
        HttpResponse response = HttpHelper.get(iotUrl + ServiceApi.Device.QueryImportRecordPage, params);
        Envelop<IotDeviceImportRecordVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }
}
