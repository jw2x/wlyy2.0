package com.yihu.ehr.iot.service.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yihu.ehr.iot.util.CurrentRequest;
import com.yihu.ehr.iot.util.encode.AES;
import com.yihu.ehr.iot.util.encode.Base64;
import com.yihu.ehr.iot.util.http.HttpHelper;
import com.yihu.ehr.iot.util.http.HttpResponse;
import com.yihu.ehr.iot.util.operator.StringUtil;
import com.yihu.ehr.iot.model.AccessToken;
import com.yihu.ehr.iot.model.ListResult;
import com.yihu.ehr.iot.model.ObjectResult;
import com.yihu.ehr.iot.model.Result;
import com.yihu.ehr.util.rest.Envelop;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

/**
 * Serveice - 基类
 * Author Progr1mmer
 */
public class BaseService {

    @Autowired
    protected ObjectMapper objectMapper;
    @Value("${permissions.info}")
    protected String permissionsInfo;
    @Value("${app.clientId}")
    protected String clientId;
//    @Value("${app.baseClientId}")
//    protected String baseClientId;
    @Value("${service-gateway.profileInnerUrl}")
    protected String profileInnerUrl;
    @Value("${service-gateway.profileOuterUrl}")
    protected String profileOuterUrl;
    @Value("${service-gateway.portalInnerUrl}")
    protected String portalInnerUrl;
    @Value("${service-gateway.portalOuterUrl}")
    protected String portalOuterUrl;
    @Value("${service-gateway.iotUrl}")
    protected String iotUrl;
    @Value("${app.oauth2InnerUrl}")
    protected String oauth2InnerUrl;
    @Value("${app.oauth2OuterUrl}")
    protected String oauth2OuterUrl;
    @Autowired
    private CurrentRequest currentRequest;

    public String readFile(String filePath, String charSet) {

        try {
            return readString(new FileInputStream(new File(filePath)), charSet);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String readString(InputStream is, String charSet) {
        try {
            return new String(readByte(is), charSet);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] readByte(InputStream is) {
        try {
            int temp;
            byte[] tempBuffer = new byte[1024];
            byte[] buffer = new byte[0];
            while ((temp = is.read(tempBuffer)) != -1) {
                buffer = ArrayUtils.addAll(buffer, ArrayUtils.subarray(tempBuffer, 0, temp));
            }
            return buffer;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> T toModel(String json,Class<T> targetCls){
        try {
            T model = objectMapper.readValue(json, targetCls);
            return model;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * json转为指定对象
     *
     * @param json
     * @param t
     * @param <T>
     * @return
     */
    public <T> T toObj(String json, Class<T> t) {
        try {
            return objectMapper.readValue(json, t);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 对象转json
     * @param obj
     * @return
     */
    public String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将envelop中的DetailList串转化为模板对象集合
     * @param modelList
     * @param targets
     * @param targetCls
     * @param <T>
     * @return
     */
    public <T> Collection<T> getEnvelopList(List modelList, Collection<T> targets, Class<T> targetCls) {
        try {
            for (Object aModelList : modelList) {
                String objJsonData = objectMapper.writeValueAsString(aModelList);
                T model = objectMapper.readValue(objJsonData, targetCls);
                targets.add(model);
            }
            return targets;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Map<String, Object> getDecryptionParms(Map<String, Object> params) throws Exception {
        if(!StringUtil.isEmpty(params.get("userId"))){
            String userId = new String(Base64.decode(params.get("userId").toString()), "utf-8");
            params.put("userId", userId);
            String key = AES.genKey(userId);
            String iv = AES.genIV(userId);
            for (String paramKey : params.keySet()) {
                if (!paramKey.equals("userId") && !StringUtil.isEmpty(params.get(paramKey))) {
                    params.put(paramKey, AES.decrypt(params.get(paramKey).toString(), key, iv));
                }
            }
        }
        return params;
    }

    /**
     * 获取省列表
     * @param level
     * @return
     */
    public Result getProvinces(Integer level) {
        try {
            Map<String, Object> request = new HashMap<>();
            Map<String, Object> header = new HashMap<>();
            HttpResponse response = HttpHelper.get(profileInnerUrl + ("/geography_entries/level/" +level), request, header);
            if (response!=null && response.getStatusCode() == 200) {
                return toModel(response.getBody(),ListResult.class);
            }
            else {
                return Result.error(response.getStatusCode(),response.getBody());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    /**
     * 获取市列表
     * @param pid
     * @return
     */
    public Result getCitys(Integer pid) {
        try {
            Map<String, Object> request = new HashMap<>();
            Map<String, Object> header = new HashMap<>();
            HttpResponse response = HttpHelper.get(profileInnerUrl + ("/geography_entries/pid/" +pid), request, header);
            if (response != null && response.getStatusCode() == 200) {
                return toModel(response.getBody(),ListResult.class);
            }
            else {
                return Result.error(response.getStatusCode(),response.getBody());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    public Result getDictNameById(Integer id) {
        try {
            Map<String, Object> request = new HashMap<>();
            Map<String, Object> header = new HashMap<>();
            HttpResponse response = HttpHelper.get(profileInnerUrl + ("/geography_entries/" +id), request, header);
            if (response!=null && response.getStatusCode() == 200) {
                return toModel(response.getBody(),ObjectResult.class);
            }
            else {
                return Result.error(response.getStatusCode(),response.getBody());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    /**
     * 获取存储在缓存中的token信息及clientId信息
     */
    public Map<String, Object> getHeader() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, Object> header = new HashMap<>();
        AccessToken accessToken = (AccessToken)request.getSession().getAttribute("token");
        header.put("token",accessToken.getAccessToken());
        header.put("clientId",clientId);
        return header;
    }

    public Envelop failed(String errMsg) {
        Envelop envelop = new Envelop();
        envelop.setSuccessFlg(false);
        envelop.setErrorMsg(errMsg);
        return envelop;
    }

    public Envelop success(Object object) {
        Envelop envelop = new Envelop();
        envelop.setSuccessFlg(true);
        envelop.setObj(object);
        return envelop;
    }

    public Envelop success(List<Object> objectList) {
        Envelop envelop = new Envelop();
        envelop.setSuccessFlg(true);
        envelop.setObj(objectList);
        return envelop;
    }

}
