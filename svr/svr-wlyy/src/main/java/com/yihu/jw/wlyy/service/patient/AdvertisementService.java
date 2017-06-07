package com.yihu.jw.wlyy.service.patient;

import com.yihu.jw.base.model.Saas;
import com.yihu.jw.base.service.SaasService;
import com.yihu.jw.mysql.query.BaseJpaService;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.restmodel.wlyy.patient.WlyyPatientContants;
import com.yihu.jw.wlyy.dao.patient.AdvertisementDao;
import com.yihu.jw.wlyy.entity.patient.BasePatient;
import com.yihu.jw.wlyy.entity.patient.WlyyAdvertisement;
import com.yihu.jw.wlyy.service.agreement.WlyySignFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/6 0006.
 */
public class AdvertisementService extends BaseJpaService<WlyyAdvertisement, AdvertisementDao> {

    @Autowired
    private AdvertisementDao advertisementDao;

    @Autowired
    private PatientService patientService;

    @Autowired
    private WlyySignFamilyService signFamilyService;

    @Autowired
    private SaasService saasService;

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

    public List<WlyyAdvertisement> getListByPatientCode(String patientCode) {
        List<WlyyAdvertisement> advertisements = null;
        //查询患者的地址,根据患者的地址显示广告
        BasePatient patient = patientService.findByCode(patientCode);
        if(patient!=null){//patient为空时,查找默认广告
            String cityName = patient.getCityName();
            //根据cityName查找saas
            if(!StringUtils.isEmpty(cityName)){
                Saas saas = saasService.findByName(cityName);
                if(saas!=null){
                    String saasCode = saas.getCode();
                    advertisements = getListBySaasCode(saasCode);
                }
            }
        }
        //如果查询出的广告为空,则查询默认广告
        if(advertisements==null){
            advertisements = getDefaultList();
        }
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
}
