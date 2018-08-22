package com.yihu.iot.service.product;

import com.yihu.ehr.constants.ErrorCode;
import com.yihu.iot.constant.ServiceApi;
import com.yihu.iot.model.ObjectResult;
import com.yihu.iot.model.ehr.MStdDataSet;
import com.yihu.iot.service.common.BaseService;
import com.yihu.iot.util.http.HttpHelper;
import com.yihu.iot.util.http.HttpResponse;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.iot.product.IotMaintenanceUnitVO;
import com.yihu.jw.restmodel.iot.product.IotProductBaseInfoVO;
import com.yihu.jw.restmodel.iot.product.IotProductVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yeshijie on 2018/1/17.
 */
@Service
public class ProductService extends BaseService {

    @Value("${ehr.metadata.version}")
    private String version;//版本 59083976eebd
    @Value("${ehr.metadata.reference}")
    private String reference;//标准来源 000000065a965615966b3a40c86ceba7

    /**
     * 测量数据（ehr标准规范管理-平台标准-标准数据集）
     * @return
     */
    public MixEnvelop<MStdDataSet, MStdDataSet> data_sets(Integer page, Integer size, String name){
        String url = "/std/data_sets";
        MixEnvelop<MStdDataSet, MStdDataSet> envelop = new MixEnvelop<>();
        Map<String, Object> params = new HashMap<>();
        String filters = "reference="+reference+";";
        if(StringUtils.isNotBlank(name)){
            filters+="name?"+name+";";
        }
        params.put("filters", filters);
        params.put("page", page);
        params.put("size", size);
        params.put("version", version);
        try {
            HttpResponse response = HttpHelper.get(profileInnerUrl + url, params);
            ObjectResult result =  objectMapper.readValue(response.getBody(),ObjectResult.class);
            if(result.isSuccessFlg()){
                envelop = objectMapper.readValue(response.getBody(),MixEnvelop.class);
                envelop.setStatus(200);
            }else {
                envelop.setStatus(-1);
                envelop.setMessage(result.getErrorMsg());
            }
            return envelop;
        } catch (Exception e) {
            e.printStackTrace();
            envelop.setStatus(-1);
            envelop.setMessage(ErrorCode.SystemError.toString());
            return envelop;
        }
    }


    /**
     * 分页查找产品
     * @param name
     * @param classify
     * @param page
     * @param size
     * @return
     */
    public MixEnvelop<IotProductBaseInfoVO, IotProductBaseInfoVO> findCompanyPage(String name, String classify, String companyId, Integer page, Integer size) throws IOException{
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("classify", classify);
        params.put("companyId", companyId);
        params.put("page", page);
        params.put("size", size);
        HttpResponse response = HttpHelper.get(iotUrl + ServiceApi.Product.FindProductPage, params);
        MixEnvelop<IotProductBaseInfoVO, IotProductBaseInfoVO> envelop = objectMapper.readValue(response.getBody(),MixEnvelop.class);
        return envelop;
    }

    /**
     * 分页查找产品
     * @param name
     * @param companyId
     * @param page
     * @param size
     * @return
     * @throws IOException
     */
    public MixEnvelop<IotProductBaseInfoVO, IotProductBaseInfoVO> findProductPageByCompanyId(String name, String companyId, Integer page, Integer size) throws IOException{
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("page", page);
        params.put("companyId", companyId);
        params.put("size", size);
        HttpResponse response = HttpHelper.get(iotUrl + ServiceApi.Product.FindProductPageByCompanyId, params);
        MixEnvelop<IotProductBaseInfoVO, IotProductBaseInfoVO> envelop = objectMapper.readValue(response.getBody(),MixEnvelop.class);
        return envelop;
    }

    /**
     * 创建产品
     * @param jsonData
     * @return
     * @throws IOException
     */
    public MixEnvelop<IotProductVO, IotProductVO> addProduct(String jsonData) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("jsonData", jsonData);
        HttpResponse response = HttpHelper.post(iotUrl + ServiceApi.Product.AddProduct, params);
        MixEnvelop<IotProductVO, IotProductVO> envelop = objectMapper.readValue(response.getBody(),MixEnvelop.class);
        return envelop;
    }

    /**
     * 根据id查找产品
     * @param id
     * @return
     * @throws IOException
     */
    public MixEnvelop<IotProductVO, IotProductVO> findByCode(String id) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        HttpResponse response = HttpHelper.get(iotUrl + ServiceApi.Product.FindProductById, params);
        MixEnvelop<IotProductVO, IotProductVO> envelop = objectMapper.readValue(response.getBody(),MixEnvelop.class);
        return envelop;
    }

    /**
     * 根据产品id查找维护单位
     * @param productId
     * @return
     * @throws IOException
     */
    public MixEnvelop<IotMaintenanceUnitVO, IotMaintenanceUnitVO> maintenanceUnitById(String productId) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("productId", productId);
        HttpResponse response = HttpHelper.get(iotUrl + ServiceApi.Product.MaintenanceUnitById, params);
        MixEnvelop<IotMaintenanceUnitVO, IotMaintenanceUnitVO> envelop = objectMapper.readValue(response.getBody(),MixEnvelop.class);
        return envelop;
    }

    /**
     * 删除产品
     * @param id
     * @return
     * @throws IOException
     */
    @PostMapping(value = IotRequestMapping.Product.delProduct)
    @ApiOperation(value = "删除产品", notes = "删除产品")
    public MixEnvelop<IotProductVO, IotProductVO> delCompany(String id) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        HttpResponse response = HttpHelper.post(iotUrl + ServiceApi.Product.DelProduct, params);
        MixEnvelop<IotProductVO, IotProductVO> envelop = objectMapper.readValue(response.getBody(),MixEnvelop.class);
        return envelop;
    }

    /**
     * 修改产品信息
     * @param jsonData
     * @return
     * @throws IOException
     */
    public MixEnvelop<IotProductVO, IotProductVO> updCompany(String jsonData) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("jsonData", jsonData);
        HttpResponse response = HttpHelper.post(iotUrl + ServiceApi.Product.UpdProduct, params);
        MixEnvelop<IotProductVO, IotProductVO> envelop = objectMapper.readValue(response.getBody(),MixEnvelop.class);
        return envelop;
    }

}
