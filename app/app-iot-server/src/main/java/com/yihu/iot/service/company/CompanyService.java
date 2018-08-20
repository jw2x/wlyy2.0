package com.yihu.iot.service.company;

import com.yihu.ehr.constants.ErrorCode;
import com.yihu.iot.constant.ServiceApi;
import com.yihu.iot.model.ObjectResult;
import com.yihu.iot.model.Result;
import com.yihu.iot.model.user.UserModel;
import com.yihu.iot.service.common.BaseService;
import com.yihu.iot.util.http.HttpHelper;
import com.yihu.iot.util.http.HttpResponse;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.iot.company.IotCompanyCertificateVO;
import com.yihu.jw.restmodel.iot.company.IotCompanyVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yeshijie on 2018/1/22.
 */
@Service
public class CompanyService extends BaseService {

    @Value("${ehr.user.roleId}")
    private String roleId;//物联网默认用户角色

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
    public MixEnvelop<IotCompanyVO, IotCompanyVO> findCompanyPage(String name, String status, String type, Integer page, Integer size) throws IOException{
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("status", status);
        params.put("type", type);
        params.put("page", page);
        params.put("size", size);
        HttpResponse response = HttpHelper.get(iotUrl + ServiceApi.Company.FindCompanyPage, params);
        MixEnvelop<IotCompanyVO, IotCompanyVO> envelop = objectMapper.readValue(response.getBody(),MixEnvelop.class);

        return envelop;
    }

    /**
     * 创建企业
     * @param jsonData
     * @return
     * @throws IOException
     */
    public MixEnvelop<IotCompanyVO, IotCompanyVO> addCompany(String jsonData) throws IOException {
        MixEnvelop<IotCompanyVO, IotCompanyVO> envelop = new MixEnvelop<>();
        //新增ehr用户
        IotCompanyVO iotCompany = toModel(jsonData, IotCompanyVO.class);

        envelop = userVerification(iotCompany,envelop);
        if(envelop.getStatus()!=null&&envelop.getStatus()==-1){
            return envelop;
        }
        //验证账户
        Result login_code = existence("login_code",iotCompany.getAccount());
        if(login_code.isSuccessFlg()){
            envelop.setStatus(-1);
            envelop.setMessage("该账号已存在");
            return envelop;
        }
        //验证身份证
        Result id_card_no = existence("id_card_no",iotCompany.getContactsIdcard());
        if(id_card_no.isSuccessFlg()){
            envelop.setStatus(-1);
            envelop.setMessage("该身份证号已被注册，请确认。");
            return envelop;
        }
        //验证邮件
        Result email = existence("email",iotCompany.getContactsEmail());
        if(email.isSuccessFlg()){
            envelop.setStatus(-1);
            envelop.setMessage("该邮箱已存在");
            return envelop;
        }
        //验证手机号
        Result telephone = existence("telephone",iotCompany.getContactsMobile());
        if(telephone.isSuccessFlg()){
            envelop.setStatus(-1);
            envelop.setMessage("该手机号码已存在");
            return envelop;
        }
        MixEnvelop<UserModel, UserModel> userModelEnvelop = updateUser(iotCompany);
        if(userModelEnvelop.getStatus()!=200){
            envelop.setStatus(-1);
            envelop.setMessage(userModelEnvelop.getMessage());
            return envelop;
        }

        Map<String, Object> params = new HashMap<>();
        params.put("jsonData", toJson(iotCompany));
        HttpResponse response = HttpHelper.post(iotUrl + ServiceApi.Company.AddCompany, params);
        envelop = objectMapper.readValue(response.getBody(),MixEnvelop.class);
        return envelop;
    }

    /**
     * 校验用户信息
     * @param iotCompany
     * @param envelop
     * @return
     */
    private MixEnvelop<IotCompanyVO, IotCompanyVO> userVerification(IotCompanyVO iotCompany, MixEnvelop<IotCompanyVO, IotCompanyVO> envelop){
        if(StringUtils.isEmpty(iotCompany.getAccount())){
            envelop.setStatus(-1);
            envelop.setMessage("账号不能为空");
            return envelop;
        }
        if(StringUtils.isEmpty(iotCompany.getContactsIdcard())){
            envelop.setStatus(-1);
            envelop.setMessage("身份证号不能为空");
            return envelop;
        }
        if(StringUtils.isEmpty(iotCompany.getContactsEmail())){
            envelop.setStatus(-1);
            envelop.setMessage("邮箱不能为空");
            return envelop;
        }
        if(StringUtils.isEmpty(iotCompany.getContactsMobile())){
            envelop.setStatus(-1);
            envelop.setMessage("手机号码账号不能为空");
            return envelop;
        }

        return envelop;
    }

    /**
     * 验证用户信息
     */
    private Result existence(String existenceType, String existenceNm){
        String getUserUrl = "/users/existence";
        Result result = null;
        Map<String, Object> params = new HashMap<>();
        params.put("existenceType",existenceType);
        params.put("existenceNm",existenceNm);
//        params.put("username",null);
//        params.put("password",null);
        try {
            HttpResponse response = HttpHelper.get(profileInnerUrl + getUserUrl, params);
            result = objectMapper.readValue(response.getBody(),Result.class);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result = new Result();
            result.setSuccessFlg(false);
            result.setMessage(ErrorCode.SystemError.toString());
            return result;
        }
    }

    /**
     * 新增用户
     * @param iotCompany
     * @return
     */
    private MixEnvelop<UserModel, UserModel> updateUser(IotCompanyVO iotCompany){
        String url = "/user/";
        UserModel userModel = new UserModel();
        userModel.setEmail(iotCompany.getContactsEmail());
        userModel.setIdCardNo(iotCompany.getContactsIdcard());
        userModel.setLoginCode(iotCompany.getAccount());
        userModel.setTelephone(iotCompany.getContactsMobile());
        userModel.setRealName(iotCompany.getContactsName());
        userModel.setRole(roleId);
        MixEnvelop envelop  = new MixEnvelop();
        Map<String, Object> params = new HashMap<>();
        params.put("user_json_data",toJson(userModel));
        try {
            Map<String, Object> head = new HashMap<>();
            head.put("Content-Type","application/json; charset=UTF-8");
            HttpResponse response = HttpHelper.post(profileInnerUrl + url, params,head);
            ObjectResult result = toModel(response.getBody(),ObjectResult.class);
            if(result.isSuccessFlg()){
                UserModel addUserModel = toModel(toJson(result.getObj()),UserModel.class);
                iotCompany.setEhrUserId(addUserModel.getId());
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
     * 修改密码
     * @param userId
     * @param passWord
     * @return
     */
    public MixEnvelop changePassWord(String userId, String passWord){
        MixEnvelop envelop = new MixEnvelop();
        String url = "/users/changePassWord";
        Map<String, Object> params = new HashMap<>();
        params.put("user_id",userId);
        params.put("password",passWord);
        try {
            HttpResponse response = HttpHelper.put(profileInnerUrl + url, params);
            ObjectResult result = toModel(response.getBody(),ObjectResult.class);
            if(result.isSuccessFlg()){
                envelop.setStatus(200);
                envelop.setMessage("修改成功");
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
     * 根据id查找企业
     * @param id
     * @return
     * @throws IOException
     */
    public MixEnvelop<IotCompanyVO, IotCompanyVO> findByCode(String id) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        HttpResponse response = HttpHelper.get(iotUrl + ServiceApi.Company.FindCompanyById, params);
        MixEnvelop<IotCompanyVO, IotCompanyVO> envelop = objectMapper.readValue(response.getBody(),MixEnvelop.class);
        return envelop;
    }

    /**
     * 删除企业证书
     * @param id
     * @return
     */
    public MixEnvelop<IotCompanyCertificateVO, IotCompanyCertificateVO> delCompanyCert(String id) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        HttpResponse response = HttpHelper.post(iotUrl + ServiceApi.Company.DelCompanyCert, params);
        MixEnvelop<IotCompanyCertificateVO, IotCompanyCertificateVO> envelop = objectMapper.readValue(response.getBody(),MixEnvelop.class);
        return envelop;
    }

    /**
     * 根据营业执照号查找企业
     * @param businessLicense
     * @return
     * @throws IOException
     */
    public MixEnvelop<IotCompanyVO, IotCompanyVO> findByBusinessLicense(String businessLicense) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("businessLicense", businessLicense);
        HttpResponse response = HttpHelper.get(iotUrl + ServiceApi.Company.FindByBusinessLicense, params);
        MixEnvelop<IotCompanyVO, IotCompanyVO> envelop = objectMapper.readValue(response.getBody(),MixEnvelop.class);
        return envelop;
    }

    /**
     * 删除企业
     * @param id
     * @return
     * @throws IOException
     */
    public MixEnvelop<IotCompanyVO, IotCompanyVO> delCompany(String id) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        HttpResponse response = HttpHelper.post(iotUrl + ServiceApi.Company.DelCompany, params);
        MixEnvelop<IotCompanyVO, IotCompanyVO> envelop = objectMapper.readValue(response.getBody(),MixEnvelop.class);
        return envelop;
    }

    /**
     * 修改企业信息
     * @param jsonData
     * @return
     * @throws IOException
     */
    public MixEnvelop<IotCompanyVO, IotCompanyVO> updCompany(String jsonData) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("jsonData", jsonData);
        HttpResponse response = HttpHelper.post(iotUrl + ServiceApi.Company.UpdCompany, params);
        MixEnvelop<IotCompanyVO, IotCompanyVO> envelop = objectMapper.readValue(response.getBody(),MixEnvelop.class);
        return envelop;
    }

    /**
     * 分页获取企业证书
     * @param name
     * @param companyId
     * @param page
     * @param size
     * @return
     * @throws IOException
     */
    public MixEnvelop<IotCompanyCertificateVO, IotCompanyCertificateVO> findCompanyCertPage(String name, Integer page, Integer size, String companyId) throws IOException{
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("companyId", companyId);
        params.put("page", page);
        params.put("size", size);
        HttpResponse response = HttpHelper.get(iotUrl + ServiceApi.Company.FindCompanyCertPage, params);
        MixEnvelop<IotCompanyCertificateVO, IotCompanyCertificateVO> envelop = objectMapper.readValue(response.getBody(),MixEnvelop.class);
        return envelop;
    }

    /**
     * 根据id查找企业证书
     * @param id
     * @return
     * @throws IOException
     */
    public MixEnvelop<IotCompanyCertificateVO, IotCompanyCertificateVO> findCompanyCertById(String id) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        HttpResponse response = HttpHelper.get(iotUrl + ServiceApi.Company.FindCompanyCertById, params);
        MixEnvelop<IotCompanyCertificateVO, IotCompanyCertificateVO> envelop = objectMapper.readValue(response.getBody(),MixEnvelop.class);
        return envelop;
    }

    /**
     * 根据企业id查找企业证书
     * @param companyId
     * @return
     * @throws IOException
     */
    public MixEnvelop<IotCompanyCertificateVO, IotCompanyCertificateVO> findCompanyCertByCompanyId(String companyId) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("companyId", companyId);
        HttpResponse response = HttpHelper.get(iotUrl + ServiceApi.Company.FindCompanyCertByCompanyId, params);
        MixEnvelop<IotCompanyCertificateVO, IotCompanyCertificateVO> envelop = objectMapper.readValue(response.getBody(),MixEnvelop.class);
        return envelop;
    }

    /**
     * 创建企业证书
     * @param jsonData
     * @return
     * @throws IOException
     */
    public MixEnvelop<IotCompanyCertificateVO, IotCompanyCertificateVO> addCompanyCert(String jsonData) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("jsonData", jsonData);
        HttpResponse response = HttpHelper.post(iotUrl + ServiceApi.Company.AddCompanyCert, params);
        MixEnvelop<IotCompanyCertificateVO, IotCompanyCertificateVO> envelop = objectMapper.readValue(response.getBody(),MixEnvelop.class);
        return envelop;
    }
}
