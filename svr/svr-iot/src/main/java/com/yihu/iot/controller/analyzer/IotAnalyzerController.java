package com.yihu.iot.controller.analyzer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yihu.iot.datainput.service.DataInputService;
import com.yihu.iot.datainput.util.ConstantUtils;
import com.yihu.iot.service.company.IotCompanyCertificateService;
import com.yihu.iot.service.company.IotCompanyService;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.restmodel.iot.company.IotCompanyVO;
import com.yihu.jw.restmodel.iot.device.IotOrderVO;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.iot.DataRequestMapping;
import com.yihu.jw.rm.iot.IotRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author cws on 2018/1/16.
 */
@RestController
@RequestMapping("svr-iot/analyze" )
@Api(tags = "设备数据解析入库", description = "基于不同厂商的设备的采集数据，进行解析适配，并入库。")
public class IotAnalyzerController extends EnvelopRestEndpoint {

    @Autowired
    private DataInputService dataInputService;

    @Autowired
    ObjectMapper objectMapper;

    /**
     * 基于奕拓小屋上传的体征数据，进行解析入库
     * @param dataJson
     * @return
     */
    @GetMapping(value = "/yitouxiaowu")
    @ApiOperation(value = "基于传入的JSON串采集数据，进行解析并入库", notes = "JSON串采集数据解析入库")
    public String findCompanyPage(
            @ApiParam(name = "dataJson", value = "采集数据JSON串", defaultValue = "")
            @RequestParam(value = "dataJson", required = false) String dataJson){

        Envelop envelop = new Envelop();
        String str = "";
        String strResult = "";

        //1. 基础数据初始化
        String accessToken  = "yitouxiaowu";  // 奕拓小屋

        try {
            //JSON数据解析
            Map dataDetail = objectMapper.readValue(dataJson, HashMap.class);
            // 用户信息
            LinkedHashMap memberMap = (LinkedHashMap)dataDetail.get("Member");
            // 身高体重
            LinkedHashMap heightMap = (LinkedHashMap)dataDetail.get("Height");
            // 血压
            LinkedHashMap bloodPressureMap = (LinkedHashMap)dataDetail.get("BloodPressure");
            // 血氧
            LinkedHashMap boMap = (LinkedHashMap)dataDetail.get("Bo");
            // 单导心电
            LinkedHashMap ecgMap = (LinkedHashMap)dataDetail.get("Ecg");
            // 体温
            LinkedHashMap temperatureMap = (LinkedHashMap)dataDetail.get("Temperature");
            // 腰臀比
            LinkedHashMap whrMap = (LinkedHashMap)dataDetail.get("Whr");
            // 血糖
            LinkedHashMap bloodSugarMap = (LinkedHashMap)dataDetail.get("BloodSugar");
            // 血尿酸
            LinkedHashMap uaMap = (LinkedHashMap)dataDetail.get("Ua");
            // 总胆固醇
            LinkedHashMap cholMap = (LinkedHashMap)dataDetail.get("Chol");
            // 血脂
            LinkedHashMap bloodFatMap = (LinkedHashMap)dataDetail.get("BloodFat");
            // 血红蛋白
            LinkedHashMap hbMap = (LinkedHashMap)dataDetail.get("Hb");
            // 尿液分析
            LinkedHashMap urinalysisMap = (LinkedHashMap)dataDetail.get("Urinalysis");

            String sn = dataDetail.get("MachineId").toString();
            String deviceName = "弈拓健康小站测量仪";
            String deviceModel = dataDetail.get("DeviceType").toString();
            String extCode = "";
            String idCard = memberMap.get("IdCode").toString();
            String username = memberMap.get("Name").toString();

            JSONArray jsonArray = new JSONArray();
            JSONObject params = new JSONObject();
            params.put("access_token", accessToken);
            params.put("sn", sn);
            params.put("ext_code","未知");
            params.put("device_name", deviceName);
            params.put("device_model", deviceModel);
            params.put("idcard", idCard);
            params.put("username", username);

            JSONObject data = new JSONObject();
            data.put("measure_time",dataDetail.get("MeasureTime") == null? "":dataDetail.get("MeasureTime").toString());
            if (bloodPressureMap != null) {
                data.put("diastolic",bloodPressureMap.get("LowPressure") == null? "": bloodPressureMap.get("LowPressure").toString());
                data.put("diastolic_unit","mmHg");
                data.put("pulse",bloodPressureMap.get("Pulse") == null? "": bloodPressureMap.get("Pulse").toString());
                data.put("pulse_unit","次/分");
            }
            if (bloodSugarMap != null) {
                data.put("blood_sugar",bloodSugarMap.get("BloodSugar") == null? "": bloodSugarMap.get("BloodSugar").toString());
                data.put("blood_sugar_unit","mmol/L");
            }
            if (boMap != null) {
                data.put("blood_oxygen",boMap.get("Oxygen") == null? "": boMap.get("Oxygen").toString());
                data.put("blood_oxygen_unit","%");

            }
            if (temperatureMap != null) {
                data.put("bbt",temperatureMap.get("Temperature") == null? "": temperatureMap.get("Temperature").toString());
                data.put("bbt_unit","℃");
            }
            if (urinalysisMap != null) {
                data.put("ket",urinalysisMap.get("KET") == null? "": urinalysisMap.get("KET").toString());
                data.put("ket_unit"," ");
                data.put("uro",urinalysisMap.get("URO") == null? "": urinalysisMap.get("URO").toString());
                data.put("uro_unit"," ");
                data.put("ph",urinalysisMap.get("PH") == null? "": urinalysisMap.get("PH").toString());
                data.put("ph_unit","");
                data.put("nit",urinalysisMap.get("NIT") == null? "": urinalysisMap.get("NIT").toString());
                data.put("nit_unit","");
                data.put("pro",urinalysisMap.get("PRO") == null? "": urinalysisMap.get("PRO").toString());
                data.put("pro_unit","");
                data.put("glu",urinalysisMap.get("GLU") == null? "": urinalysisMap.get("GLU").toString());
                data.put("glu_unit","");
                data.put("bil",urinalysisMap.get("BIL") == null? "": urinalysisMap.get("BIL").toString());
                data.put("bil_unit","");
                data.put("sg",urinalysisMap.get("SG") == null? "": urinalysisMap.get("SG").toString());
                data.put("sg_unit","");
                data.put("wbc",urinalysisMap.get("LEU") == null? "": urinalysisMap.get("LEU").toString());
                data.put("wbc_unit","");
                data.put("vc",urinalysisMap.get("VC") == null? "": urinalysisMap.get("VC").toString());
                data.put("vc_unit","");
                data.put("bld",urinalysisMap.get("BLD") == null? "": urinalysisMap.get("BLD").toString());
                data.put("bld_unit","");
            }
            if (uaMap != null) {
                data.put("uric_acid",uaMap.get("Ua") == null? "": uaMap.get("Ua").toString());
                data.put("uric_acid_unit","mmol/L");
            }
            if (cholMap != null) {
                data.put("t-chol",cholMap.get("Chol") == null? "": cholMap.get("Chol").toString());
                data.put("t-chol_unit","mmol/L");
            }
            if (bloodFatMap != null) {
                data.put("hdl",bloodFatMap.get("HdlChol") == null? "": bloodFatMap.get("HdlChol").toString());
                data.put("hdl_unit","mmol/L");
                data.put("tg",bloodFatMap.get("Trig") == null? "": bloodFatMap.get("Trig").toString());
                data.put("tg_unit","mmol/L");
                data.put("ldl",bloodFatMap.get("CalcLdl") == null? "": bloodFatMap.get("CalcLdl").toString());
                data.put("ldl_unit","mmol/L");
            }
            if (heightMap != null) {
                data.put("height", heightMap.get("Height") == null ? "" : heightMap.get("Height").toString());
                data.put("height_unit", "cm");
                data.put("weight", heightMap.get("Weight") == null ? "" : heightMap.get("Weight").toString());
                data.put("weight_unit", "kg");
                data.put("bmi",heightMap.get("BMI") == null? "": heightMap.get("BMI").toString());
                data.put("bmi_unit"," ");
            }
            if (whrMap != null) {
                data.put("waist",whrMap.get("Waistline") == null? "": whrMap.get("Waistline").toString());
                data.put("waist_unit","cm");
            }
            if (hbMap != null) {
                data.put("hgb",hbMap.get("Hb") == null? "": hbMap.get("Hb").toString());
                data.put("hgb_unit","mmol/L");
            }
            if (ecgMap != null) {
                data.put("ecg",ecgMap.get("Hr") == null? "": ecgMap.get("Hr").toString());
                data.put("ecg_unit","次/分");
            }
            data.put("hbalc","");
            data.put("hbalc_unit","");
            data.put("left_eye","");
            data.put("left_eye_unit","");
            data.put("right_eye","");
            data.put("right_eye_unit","");
            jsonArray.add(data);

            params.put("data", jsonArray);

            str = dataInputService.inputBodySignsData(params.toString());
            JSONObject result = JSONObject.parseObject(str);
            if (StringUtils.endsWithIgnoreCase(ConstantUtils.FAIL,result.getString("response"))) {
                Map res = new HashMap();
                res.put("success", "false");
                res.put("message", result.getString("msg"));
                strResult = objectMapper.writeValueAsString(res);
                return strResult;
            }else{
                Map res = new HashMap();
                res.put("success", "true");
                res.put("message", "体征信息上传成功。");
                strResult = objectMapper.writeValueAsString(res);
                return strResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "体征信息上传失败！";
        }
    }
}
