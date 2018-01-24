package com.yihu.ehr.iot.service.company;

import com.yihu.ehr.iot.constant.ServiceApi;
import com.yihu.ehr.iot.service.common.BaseService;
import com.yihu.ehr.iot.util.http.HttpHelper;
import com.yihu.ehr.iot.util.http.HttpResponse;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.iot.company.IotCompanyCertificateVO;
import com.yihu.jw.restmodel.iot.company.IotCompanyVO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yeshijie on 2018/1/22.
 */
@Service
public class CompanyService extends BaseService {

    /**
     * 分页查找企业
     * @param name
     * @param status
     * @param type
     * @param page
     * @param size
     * @return
     * @throws IOException
     */
    public Envelop<IotCompanyVO> findCompanyPage(String name,String status,String type,Integer page,Integer size) throws IOException{
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("status", status);
        params.put("type", type);
        params.put("page", page);
        params.put("size", size);
        HttpResponse response = HttpHelper.get(iotUrl + ServiceApi.Company.FindCompanyPage, params);
        Envelop<IotCompanyVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);

        return envelop;
    }

    /**
     * 创建企业
     * @param jsonData
     * @return
     * @throws IOException
     */
    public Envelop<IotCompanyVO> addCompany(String jsonData) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("jsonData", jsonData);
        HttpResponse response = HttpHelper.post(iotUrl + ServiceApi.Company.AddCompany, params);
        Envelop<IotCompanyVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }

    /**
     * 根据id查找企业
     * @param id
     * @return
     * @throws IOException
     */
    public Envelop<IotCompanyVO> findByCode(String id) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        HttpResponse response = HttpHelper.get(iotUrl + ServiceApi.Company.FindCompanyById, params);
        Envelop<IotCompanyVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }

    /**
     * 根据营业执照号查找企业
     * @param businessLicense
     * @return
     * @throws IOException
     */
    public Envelop<IotCompanyVO> findByBusinessLicense(String businessLicense) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("businessLicense", businessLicense);
        HttpResponse response = HttpHelper.get(iotUrl + ServiceApi.Company.FindByBusinessLicense, params);
        Envelop<IotCompanyVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }

    /**
     * 删除企业
     * @param id
     * @return
     * @throws IOException
     */
    public Envelop<IotCompanyVO> delCompany(String id) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        HttpResponse response = HttpHelper.post(iotUrl + ServiceApi.Company.DelCompany, params);
        Envelop<IotCompanyVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }

    /**
     * 修改企业信息
     * @param jsonData
     * @return
     * @throws IOException
     */
    public Envelop<IotCompanyVO> updCompany(String jsonData) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("jsonData", jsonData);
        HttpResponse response = HttpHelper.post(iotUrl + ServiceApi.Company.UpdCompany, params);
        Envelop<IotCompanyVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }

    /**
     * 分页获取企业证书
     * @param name
     * @param page
     * @param size
     * @return
     * @throws IOException
     */
    public Envelop<IotCompanyCertificateVO> findCompanyCertPage(String name,Integer page,Integer size) throws IOException{
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("page", page);
        params.put("size", size);
        HttpResponse response = HttpHelper.get(iotUrl + ServiceApi.Company.FindCompanyCertPage, params);
        Envelop<IotCompanyCertificateVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }

    /**
     * 根据id查找企业证书
     * @param id
     * @return
     * @throws IOException
     */
    public Envelop<IotCompanyCertificateVO> findCompanyCertById(String id) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        HttpResponse response = HttpHelper.get(iotUrl + ServiceApi.Company.FindCompanyCertById, params);
        Envelop<IotCompanyCertificateVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }

    /**
     * 根据企业id查找企业证书
     * @param companyId
     * @return
     * @throws IOException
     */
    public Envelop<IotCompanyCertificateVO> findCompanyCertByCompanyId(String companyId) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("companyId", companyId);
        HttpResponse response = HttpHelper.get(iotUrl + ServiceApi.Company.FindCompanyCertByCompanyId, params);
        Envelop<IotCompanyCertificateVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }

    /**
     * 创建企业证书
     * @param jsonData
     * @return
     * @throws IOException
     */
    public Envelop<IotCompanyCertificateVO> addCompanyCert(String jsonData) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("jsonData", jsonData);
        HttpResponse response = HttpHelper.post(iotUrl + ServiceApi.Company.AddCompanyCert, params);
        Envelop<IotCompanyCertificateVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }
}
