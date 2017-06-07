package com.yihu.jw.wlyy.service.agreement;

import com.yihu.jw.mysql.query.BaseJpaService;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.restmodel.wlyy.agreement.WlyyAgreementContants;
import com.yihu.jw.wlyy.dao.agreement.WlyyAgreementDao;
import com.yihu.jw.wlyy.entity.agreement.WlyyAgreement;
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

    @Transient
    public WlyyAgreement create(WlyyAgreement wlyyAgreement) {
        if (StringUtils.isEmpty(wlyyAgreement.getCode())) {
            throw new ApiException(WlyyAgreementContants.Agreement.message_fail_code_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreement.getName())) {
            throw new ApiException(WlyyAgreementContants.Agreement.message_fail_name_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreement.getPrice())) {
            throw new ApiException(WlyyAgreementContants.Agreement.message_fail_price_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreement.getStatus())) {
            throw new ApiException(WlyyAgreementContants.Agreement.message_fail_status_is_null, CommonContants.common_error_params_code);
        }
        //设置创建时间和修改时间
        Date date = new Date();
        wlyyAgreement.setCreateTime(date);
        wlyyAgreement.setUpdateTime(date);
        return wlyyAgreementDao.save(wlyyAgreement);
    }

    @Transient
    public WlyyAgreement update(WlyyAgreement wlyyAgreement) {
        String code = wlyyAgreement.getCode();
        if (StringUtils.isEmpty(code)) {
            throw new ApiException(WlyyAgreementContants.Agreement.message_fail_code_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreement.getName())) {
            throw new ApiException(WlyyAgreementContants.Agreement.message_fail_name_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreement.getPrice())) {
            throw new ApiException(WlyyAgreementContants.Agreement.message_fail_price_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreement.getStatus())) {
            throw new ApiException(WlyyAgreementContants.Agreement.message_fail_status_is_null, CommonContants.common_error_params_code);
        }
        Long id = wlyyAgreement.getId();
        if (StringUtils.isEmpty(id)) {
            throw new ApiException(WlyyAgreementContants.Agreement.message_fail_id_is_null, CommonContants.common_error_params_code);
        }
        WlyyAgreement wlyyAgreement1 = wlyyAgreementDao.findById(id);
        if(wlyyAgreement1==null){
            throw new ApiException(WlyyAgreementContants.Agreement.message_fail_wlyyAgreement_is_no_exist, CommonContants.common_error_params_code);
        }
        //设置创建时间和修改时间
        Date date = new Date();
        wlyyAgreement.setCreateTime(wlyyAgreement1.getCreateTime());
        wlyyAgreement.setUpdateTime(date);

        wlyyAgreement1 = wlyyAgreementDao.findCodeExcludeId(code,id);
        if(wlyyAgreement1 !=null){
            throw new ApiException(WlyyAgreementContants.Agreement.message_fail_code_exist, CommonContants.common_error_params_code);
        }
        return wlyyAgreementDao.save(wlyyAgreement);
    }

    public WlyyAgreement findByCode(String code) {
        WlyyAgreement wlyyAgreement = wlyyAgreementDao.findByCode(code);
        return wlyyAgreement;
    }

    @Transient
    public void delete(String code) {
        WlyyAgreement wlyyAgreement = findByCode(code);
        if(wlyyAgreement==null){
            throw new ApiException(WlyyAgreementContants.Agreement.message_fail_wlyyAgreement_is_no_exist, CommonContants.common_error_params_code);
        }
        wlyyAgreement.setStatus(-1);
        wlyyAgreementDao.save(wlyyAgreement);
    }
}
