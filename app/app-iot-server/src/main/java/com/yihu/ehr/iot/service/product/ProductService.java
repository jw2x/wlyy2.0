package com.yihu.ehr.iot.service.product;

import com.yihu.ehr.iot.constant.ServiceApi;
import com.yihu.ehr.iot.service.common.BaseService;
import com.yihu.ehr.iot.util.http.HttpHelper;
import com.yihu.ehr.iot.util.http.HttpResponse;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.iot.product.IotProductBaseInfoVO;
import com.yihu.jw.restmodel.iot.product.IotProductVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import io.swagger.annotations.ApiOperation;
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


    /**
     * 分页查找产品
     * @param name
     * @param classify
     * @param page
     * @param size
     * @return
     */
    public Envelop<IotProductBaseInfoVO> findCompanyPage(String name,String classify,Integer page,Integer size) throws IOException{
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("classify", classify);
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
