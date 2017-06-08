package com.yihu.jw.wlyy.service.patient;

import com.yihu.jw.mysql.query.BaseJpaService;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.restmodel.wlyy.patient.WlyyPatientContants;
import com.yihu.jw.util.AddressUtils;
import com.yihu.jw.util.CusAccessObjectUtil;
import com.yihu.jw.wlyy.dao.patient.AdvertisementDao;
import com.yihu.jw.wlyy.entity.BaseSaas;
import com.yihu.jw.wlyy.entity.agreement.WlyySignFamily;
import com.yihu.jw.wlyy.entity.patient.WlyyAdvertisement;
import com.yihu.jw.wlyy.service.BaseSaasService;
import com.yihu.jw.wlyy.service.agreement.WlyySignFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/6 0006.
 */
@Service
public class AdvertisementService extends BaseJpaService<WlyyAdvertisement, AdvertisementDao> {

    @Autowired
    private AdvertisementDao advertisementDao;

    @Autowired
    private PatientService patientService;

    @Autowired
    private WlyySignFamilyService signFamilyService;

    @Autowired
    private BaseSaasService saasService;

    @Transient
     public WlyyAdvertisement create(WlyyAdvertisement advertisement) {
        if (StringUtils.isEmpty(advertisement.getCode())) {
            throw new ApiException(WlyyPatientContants.Advertisement.message_fail_code_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(advertisement.getSaasId())) {
            throw new ApiException(WlyyPatientContants.Advertisement.message_fail_saasid_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(advertisement.getName())) {
            throw new ApiException(WlyyPatientContants.Advertisement.message_fail_name_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(advertisement.getPicture())) {
            throw new ApiException(WlyyPatientContants.Advertisement.message_fail_picture_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(advertisement.getStatus())) {
            throw new ApiException(WlyyPatientContants.Advertisement.message_fail_status_is_null, CommonContants.common_error_params_code);
        }
        //设置创建时间和修改时间
        Date date = new Date();
        advertisement.setCreateTime(date);
        advertisement.setUpdateTime(date);
        return advertisementDao.save(advertisement);
    }

    @Transient
    public WlyyAdvertisement update(WlyyAdvertisement advertisement) {
        String code = advertisement.getCode();
        if (StringUtils.isEmpty(code)) {
            throw new ApiException(WlyyPatientContants.Advertisement.message_fail_code_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(advertisement.getSaasId())) {
            throw new ApiException(WlyyPatientContants.Advertisement.message_fail_saasid_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(advertisement.getName())) {
            throw new ApiException(WlyyPatientContants.Advertisement.message_fail_name_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(advertisement.getPicture())) {
            throw new ApiException(WlyyPatientContants.Advertisement.message_fail_picture_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(advertisement.getStatus())) {
            throw new ApiException(WlyyPatientContants.Advertisement.message_fail_status_is_null, CommonContants.common_error_params_code);
        }
        Long id = advertisement.getId();
        if (StringUtils.isEmpty(id)) {
            throw new ApiException(WlyyPatientContants.Advertisement.message_fail_picture_is_null, CommonContants.common_error_params_code);
        }
        //根据id获取修改前的记录
        WlyyAdvertisement advertisement1 = findById(id);
        if(null == advertisement1){
            throw new ApiException(WlyyPatientContants.Advertisement.message_fail_wlyyAdvertisement_is_not_exist, CommonContants.common_error_params_code);
        }
        //设置创建时间和修改时间
        Date date = new Date();
        advertisement.setCreateTime(advertisement1.getCreateTime());
        advertisement.setUpdateTime(date);
        return advertisementDao.save(advertisement);
    }

    public WlyyAdvertisement findById(Long id) {
        return advertisementDao.findById(id);
    }

    public WlyyAdvertisement findByCode(String code) {
        return advertisementDao.findByCode(code);
    }

    @Transient
    public void delete(String code) {
        WlyyAdvertisement advertisement = findByCode(code);
        if(advertisement==null){
            throw new ApiException(WlyyPatientContants.Advertisement.message_fail_wlyyAdvertisement_is_not_exist, CommonContants.common_error_params_code);
        }
        advertisement.setStatus(-1);
        advertisementDao.save(advertisement);
    }

    /**
     * 根据患者code查找广告
     * @param patientCode
     * @param request
     * @return
     */
    public List<WlyyAdvertisement> getListByPatientCode(String patientCode, HttpServletRequest request) {
        List<WlyyAdvertisement> advertisements = null;
        //查找已签约的,根据签约的saasId查找地区,获得广告
        List<WlyySignFamily> signs =  signFamilyService.findByPatientCode(patientCode,1);
        if(signs!=null){
            for(WlyySignFamily sign:signs){
                String saasCode = sign.getSaasId();
                if(!StringUtils.isEmpty(sign.getSaasId())){
                    advertisements = getListBySaasCode(saasCode);
                    if(advertisements!=null){
                        return advertisements;
                    }
                }
            }
        }
        //如果未签约或者通过签约未获取到广告,则根据http获取广告
        advertisements = getByHttp(request);
        return advertisements;
    }

    /**
     * 通过saasCode查找广告
     * @param saasCode
     * @return
     */
    public List<WlyyAdvertisement> getListBySaasCode(String saasCode){
        return advertisementDao.getListBySaasCode(saasCode);
    }

    /**
     * 查找默认广告
     * @return
     */
    private List<WlyyAdvertisement> getDefaultList(){
        return advertisementDao.getDefaultList();
    }

    /**
     * 通过用户的HttpServletRequest,判断显示的广告
     * @param request
     * @return
     */
    public  List<WlyyAdvertisement> getByHttp(HttpServletRequest request){
        String ipAddress = CusAccessObjectUtil.getIpAddress(request);
        AddressUtils addressUtils = new AddressUtils();
        try {
            String address = addressUtils.getAddresses(ipAddress);//"中国-西南-四川省-成都市- -电信"  (没有值,中间用空格隔开  country-area-region-city-county-isp)或者返回0
            String[] addresses = address.split("-");
            if(addresses.length<6){
                return  getDefaultList();
            }else{
                String cityName = addresses[3];
                BaseSaas saas = saasService.findByName(cityName);//成都市
                if(saas ==null){
                    cityName = cityName.substring(0,cityName.length()-1);//成都
                    saas = saasService.findByName(cityName);
                }
                if(saas==null){//如果还是为空,则展示默认广告
                    return  getDefaultList();
                }
                String saasCode = saas.getCode();
                return getListBySaasCode(saasCode);
            }

        } catch (UnsupportedEncodingException e) {//解析ip失败,展示默认广告
            return  getDefaultList();
        }

    }

    /**
     * 通过ip定位地址,展示广告的广告(供网关调用)
     * @param ipaddress
     * @return
     */
    public  List<WlyyAdvertisement> getListByIp(String ipaddress){
        try {
            AddressUtils addressUtils = new AddressUtils();
            String address = addressUtils.getAddresses(ipaddress);
            String[] addresses = address.split("-");
            if(addresses.length<6){
                return  getDefaultList();
            }else{
                String cityName = addresses[3];
                BaseSaas saas = saasService.findByName(cityName);//成都市
                if(saas ==null){
                    cityName = cityName.substring(0,cityName.length()-1);//成都
                    saas = saasService.findByName(cityName);
                }
                if(saas==null){//如果还是为空,则展示默认广告
                    return  getDefaultList();
                }
                String saasCode = saas.getCode();
                return getListBySaasCode(saasCode);
            }

        } catch (UnsupportedEncodingException e) {//解析ip失败,展示默认广告
            return  getDefaultList();
        }

    }
}
