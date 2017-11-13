package com.yihu.jw.wlyy.service.agreement;

import com.yihu.jw.exception.ApiException;
import com.yihu.jw.exception.code.ExceptionCode;
import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.jw.rm.wlyy.WlyyRequestMapping;
import com.yihu.jw.wlyy.agreement.WlyyAgreement;
import com.yihu.jw.wlyy.dao.agreement.WlyyAgreementDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.Transient;
import java.util.Date;

/**
 * Created by Administrator on 2017/6/1 0001.
 */
@Service
public class WlyyAgreementService extends BaseJpaService<WlyyAgreement, WlyyAgreementDao> {

    @Autowired
    private WlyyAgreementDao wlyyAgreementDao;

    //@Autowired
    //private SaasService saasService;

    @Transient
    public WlyyAgreement create(WlyyAgreement wlyyAgreement) {
        String saasId = wlyyAgreement.getSaasId();
        if (StringUtils.isEmpty(saasId)) {
            throw new ApiException(WlyyRequestMapping.Agreement.message_fail_saasId_is_null, ExceptionCode.common_error_params_code);
        }
        //Saas saas = saasService.findByCode(saasId);
        //if(saas==null){
        //    throw new ApiException(BaseContants.Saas.message_fail_code_no_exist, ExceptionCode.common_error_params_code);
        //}
        if (StringUtils.isEmpty(wlyyAgreement.getName())) {
            throw new ApiException(WlyyRequestMapping.Agreement.message_fail_name_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreement.getPrice())) {
            throw new ApiException(WlyyRequestMapping.Agreement.message_fail_price_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreement.getStatus())) {
            throw new ApiException(WlyyRequestMapping.Agreement.message_fail_status_is_null, ExceptionCode.common_error_params_code);
        }
        //设置创建时间和修改时间
        Date date = new Date();
        wlyyAgreement.setCreateTime(date);
        wlyyAgreement.setUpdateTime(date);
        return wlyyAgreementDao.save(wlyyAgreement);
    }

    @Transient
    public WlyyAgreement update(WlyyAgreement wlyyAgreement) {
        String saasId = wlyyAgreement.getSaasId();
        if (StringUtils.isEmpty(saasId)) {
            throw new ApiException(WlyyRequestMapping.Agreement.message_fail_saasId_is_null, ExceptionCode.common_error_params_code);
        }
        //Saas saas = saasService.findByCode(saasId);
        //if(saas==null){
        //    throw new ApiException(BaseContants.Saas.message_fail_code_no_exist, ExceptionCode.common_error_params_code);
        //}
        if (StringUtils.isEmpty(wlyyAgreement.getName())) {
            throw new ApiException(WlyyRequestMapping.Agreement.message_fail_name_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreement.getPrice())) {
            throw new ApiException(WlyyRequestMapping.Agreement.message_fail_price_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreement.getStatus())) {
            throw new ApiException(WlyyRequestMapping.Agreement.message_fail_status_is_null, ExceptionCode.common_error_params_code);
        }
        String id = wlyyAgreement.getId();
        if (StringUtils.isEmpty(id)) {
            throw new ApiException(WlyyRequestMapping.Agreement.message_fail_id_is_null, ExceptionCode.common_error_params_code);
        }
        WlyyAgreement wlyyAgreement1 = wlyyAgreementDao.findById(id);
        if(wlyyAgreement1==null){
            throw new ApiException(WlyyRequestMapping.Agreement.message_fail_wlyyAgreement_is_no_exist, ExceptionCode.common_error_params_code);
        }
        //设置创建时间和修改时间
        Date date = new Date();
        wlyyAgreement.setCreateTime(wlyyAgreement1.getCreateTime());
        wlyyAgreement.setUpdateTime(date);

        return wlyyAgreementDao.save(wlyyAgreement);
    }

    public WlyyAgreement findById(String id) {
        WlyyAgreement wlyyAgreement = wlyyAgreementDao.findById(id);
        return wlyyAgreement;
    }

    @Transient
    public void delete(String id) {
        WlyyAgreement wlyyAgreement = findById(id);
        if(wlyyAgreement==null){
            throw new ApiException(WlyyRequestMapping.Agreement.message_fail_wlyyAgreement_is_no_exist, ExceptionCode.common_error_params_code);
        }
        wlyyAgreement.setStatus(-1);
        wlyyAgreementDao.save(wlyyAgreement);
    }
}
