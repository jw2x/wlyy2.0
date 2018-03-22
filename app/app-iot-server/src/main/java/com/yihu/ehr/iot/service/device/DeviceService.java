package com.yihu.ehr.iot.service.device;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yihu.base.fastdfs.FastDFSHelper;
import com.yihu.ehr.iot.constant.ServiceApi;
import com.yihu.ehr.iot.service.common.BaseService;
import com.yihu.ehr.iot.service.common.FileUploadService;
import com.yihu.ehr.iot.util.excel.AExcelReader;
import com.yihu.ehr.iot.util.excel.reader.IotDeviceImportVOReader;
import com.yihu.ehr.iot.util.http.HttpHelper;
import com.yihu.ehr.iot.util.http.HttpResponse;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.base.BaseEnvelop;
import com.yihu.jw.restmodel.iot.common.ExistVO;
import com.yihu.jw.restmodel.iot.common.UploadVO;
import com.yihu.jw.restmodel.iot.device.IotDeviceImportRecordVO;
import com.yihu.jw.restmodel.iot.device.IotDeviceImportVO;
import com.yihu.jw.restmodel.iot.device.IotDeviceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
    @Value("${neiwang.enable}")
    private Boolean isneiwang;  //如果不是内网项目要转到到内网在上传
    @Autowired
    private FileUploadService fileUploadService;

    /**
     * 创建设备
     * @param jsonData
     * @return
     * @throws IOException
     */
    public Envelop<IotDeviceVO> create(String jsonData) throws IOException {
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
        params.put("id", id);
        HttpResponse response = HttpHelper.post(iotUrl + ServiceApi.Device.UpdSim, params);
        BaseEnvelop envelop = objectMapper.readValue(response.getBody(),BaseEnvelop.class);
        return envelop;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public Envelop<IotDeviceVO> delDevice(String id) throws IOException{
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        HttpResponse response = HttpHelper.post(iotUrl + ServiceApi.Device.DelDevice, params);
        Envelop<IotDeviceVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }

    /**
     * 修改设备
     * @param jsonData
     * @return
     */
    public BaseEnvelop updDevice(String jsonData) throws IOException{
        Map<String, Object> params = new HashMap<>();
        params.put("jsonData", jsonData);
        HttpResponse response = HttpHelper.post(iotUrl + ServiceApi.Device.UpdDevice, params);
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
                String purcharseId,Integer isBinding,Integer page,Integer size) throws IOException{
        Map<String, Object> params = new HashMap<>();
        params.put("sn", sn);
        params.put("hospital", hospital);
        params.put("orderId", orderId);
        params.put("purcharseId", purcharseId);
        params.put("isBinding", isBinding);
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
    public Envelop<IotDeviceImportRecordVO> uploadStream(MultipartFile file,String purcharseId,HttpServletRequest request) throws Exception {
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
        ObjectNode objectNode = null;
        Map<String, Object> params = params = new HashMap<>();;

        if(isneiwang){
            objectNode = fastDFSHelper.upload(file.getInputStream(), fileType, "");

            params.put("url", objectNode.get("fid").toString().replaceAll("\"", ""));
        }else {
            UploadVO uploadVO = fileUploadService.request(request,file.getInputStream(),fullName);
            if(uploadVO==null){
                return Envelop.getError("文件上传失败");
            }
            params.put("url", uploadVO.getFullUri());
        }

        //解析excel封装对象
        AExcelReader excelReader = new IotDeviceImportVOReader();
        excelReader.read(file.getInputStream());
        List<IotDeviceImportVO> correctLs = excelReader.getCorrectLs();
        params.put("purcharseId", purcharseId);
        params.put("jsonData", JSONObject.toJSONString(correctLs));
        params.put("fileName", fileName);

        String ret = HttpHelper.postBody(iotUrl + ServiceApi.Device.ImportDevice,JSONObject.toJSONString(params));
        Envelop<IotDeviceImportRecordVO> envelop = objectMapper.readValue(ret,Envelop.class);
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
