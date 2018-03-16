package com.yihu.ehr.iot.service.product;

import com.yihu.ehr.constants.ErrorCode;
import com.yihu.ehr.iot.constant.ServiceApi;
import com.yihu.ehr.iot.model.ObjectResult;
import com.yihu.ehr.iot.model.ehr.MStdDataSet;
import com.yihu.ehr.iot.service.common.BaseService;
import com.yihu.ehr.iot.util.http.HttpHelper;
import com.yihu.ehr.iot.util.http.HttpResponse;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.iot.product.IotMaintenanceUnitVO;
import com.yihu.jw.restmodel.iot.product.IotProductBaseInfoVO;
import com.yihu.jw.restmodel.iot.product.IotProductVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
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

//    @Value("ehr.metadata.domain")
    private String version = "59083976eebd";//版本
    private String reference = "000000065a965615966b3a40c86ceba7";//标准来源

    /**
     * 测量数据（ehr标准规范管理-平台标准-标准数据集）
     * @return
     */
    public Envelop<MStdDataSet> data_sets(Integer page, Integer size, String name){
        String url = "/std/data_sets";
        Envelop<MStdDataSet> envelop = new Envelop<MStdDataSet>();
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
                envelop = objectMapper.readValue(response.getBody(),Envelop.class);
                envelop.setStatus(200);
            }else {
                envelop.setStatus(-1);
                envelop.setErrorMsg(result.getErrorMsg());
            }
            return envelop;
        } catch (Exception e) {
            e.printStackTrace();
            envelop.setStatus(-1);
            envelop.setErrorMsg(ErrorCode.SystemError.toString());
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
    public Envelop<IotProductBaseInfoVO> findCompanyPage(String name,String classify,String companyId,Integer page,Integer size) throws IOException{
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("classify", classify);
        params.put("companyId", companyId);
        params.put("page", page);
        params.put("size", size);
        HttpResponse response = HttpHelper.get(iotUrl + ServiceApi.Product.FindProductPage, params);
        Envelop<IotProductBaseInfoVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
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
    public Envelop<IotProductBaseInfoVO> findProductPageByCompanyId(String name,String companyId,Integer page, Integer size) throws IOException{
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("page", page);
        params.put("companyId", companyId);
        params.put("size", size);
        HttpResponse response = HttpHelper.get(iotUrl + ServiceApi.Product.FindProductPageByCompanyId, params);
        Envelop<IotProductBaseInfoVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }

    /**
     * 创建产品
     * @param jsonData
     * @return
     * @throws IOException
     */
    public Envelop<IotProductVO> addProduct(String jsonData) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("jsonData", jsonData);
        HttpResponse response = HttpHelper.post(iotUrl + ServiceApi.Product.AddProduct, params);
        Envelop<IotProductVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }

    /**
     * 根据id查找产品
     * @param id
     * @return
     * @throws IOException
     */
    public Envelop<IotProductVO> findByCode(String id) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        HttpResponse response = HttpHelper.get(iotUrl + ServiceApi.Product.FindProductById, params);
        Envelop<IotProductVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }

    /**
     * 根据产品id查找维护单位
     * @param productId
     * @return
     * @throws IOException
     */
    public Envelop<IotMaintenanceUnitVO> maintenanceUnitById(String productId) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("productId", productId);
        HttpResponse response = HttpHelper.get(iotUrl + ServiceApi.Product.MaintenanceUnitById, params);
        Envelop<IotMaintenanceUnitVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
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
    public Envelop<IotProductVO> delCompany(String id) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        HttpResponse response = HttpHelper.post(iotUrl + ServiceApi.Product.DelProduct, params);
        Envelop<IotProductVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }

    /**
     * 修改产品信息
     * @param jsonData
     * @return
     * @throws IOException
     */
    public Envelop<IotProductVO> updCompany(String jsonData) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("jsonData", jsonData);
        HttpResponse response = HttpHelper.post(iotUrl + ServiceApi.Product.UpdProduct, params);
        Envelop<IotProductVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }

}
