package com.yihu.ehr.health.service.archives;

import com.yihu.ehr.health.constant.ServiceApi;
import com.yihu.ehr.health.service.common.BaseService;
import com.yihu.ehr.health.util.http.HttpHelper;
import com.yihu.ehr.health.util.http.HttpResponse;
import com.yihu.jw.restmodel.archives.PatientArchivesInfoVO;
import com.yihu.jw.restmodel.archives.PatientArchivesVO;
import com.yihu.jw.restmodel.archives.dict.SystemDictVO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.iot.device.IotDeviceVO;
import com.yihu.jw.rm.archives.PatientArchivesMapping;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

/**
 * Created by Trick on 2018/2/22.
 */
@Service
public class ArchivesService extends BaseService{

    /**
     * 分页查找健康档案
     * @param page
     * @param size
     * @param status
     * @param name
     * @return
     * @throws ParseException
     */
    public Envelop<PatientArchivesVO> queryPatientArchivesPage(Integer page, Integer size, String status, String cancelReseanType , String name) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("size", size);
        params.put("status", status);
        params.put("cancelReseanType", cancelReseanType);
        params.put("name", name);
        HttpResponse response = HttpHelper.get(archivesInnerUrl + ServiceApi.Archives.findPatientArchives, params);
        Envelop<PatientArchivesVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }

    /**
     * 获取档案详情
     * @return
     * @throws ParseException
     */
    public Envelop<PatientArchivesInfoVO> queryPatientArchivesInfoPage(String code) throws Exception {

        Map<String, Object> params = new HashMap<>();
        params.put("page", code);
        HttpResponse response = HttpHelper.get(archivesInnerUrl + ServiceApi.Archives.findPatientArchivesInfos, params);
        Envelop<PatientArchivesInfoVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }

    public Envelop<Boolean> createPatientArchives(String patientArchives,String list)throws Exception {

        Map<String, Object> params = new HashMap<>();
        params.put("patientArchives", patientArchives);
        params.put("list", list);
        HttpResponse response = HttpHelper.get(archivesInnerUrl + ServiceApi.Archives.createPatientArchives, params);
        Envelop<Boolean> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }

    public  Envelop<Boolean> updatePatientArchives(String patientArchives,String list)throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("patientArchives", patientArchives);
        params.put("list", list);
        HttpResponse response = HttpHelper.get(archivesInnerUrl + ServiceApi.Archives.createPatientArchives, params);
        Envelop<Boolean> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }

}
