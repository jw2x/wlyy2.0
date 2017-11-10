package com.yihu.jw.wlyy.service.agreement;

import com.yihu.jw.exception.ApiException;
import com.yihu.jw.exception.code.ExceptionCode;
import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.jw.rm.wlyy.WlyyRequestMapping;
import com.yihu.jw.util.IDCard;
import com.yihu.jw.wlyy.agreement.WlyyAgreement;
import com.yihu.jw.wlyy.agreement.WlyySignFamily;
import com.yihu.jw.wlyy.dao.agreement.WlyySignFamilyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.Transient;
import java.text.ParseException;
import java.util.List;

/**
 * Created by Administrator on 2017/6/2 0002.
 */
@Service
public class WlyySignFamilyService extends BaseJpaService<WlyySignFamily, WlyySignFamilyDao> {

    @Autowired
    private WlyySignFamilyDao wlyySignFamilyDao;

    @Autowired
    private WlyyAgreementService wlyyAgreementService;

    //@Autowired
    //private SaasService saasService;

    @Transient
    public WlyySignFamily create(WlyySignFamily wlyySignFamily) throws ParseException {
        boolean b = canSaveOrUpdate(wlyySignFamily);
        if(b){
            return wlyySignFamilyDao.save(wlyySignFamily);
        }
        return null;
    }

    @Transient
    public WlyySignFamily update(WlyySignFamily wlyySignFamily) throws ParseException {
        boolean b = canSaveOrUpdate(wlyySignFamily);
        if(b){
            return wlyySignFamilyDao.save(wlyySignFamily);
        }
        return null;
    }

    public WlyySignFamily findByCode(String code) {
        return wlyySignFamilyDao.findById(code);
    }

    private boolean canSaveOrUpdate(WlyySignFamily wlyySignFamily) throws ParseException {
        String saasId = wlyySignFamily.getSaasId();
        if (StringUtils.isEmpty(saasId)) {
            throw new ApiException(WlyyRequestMapping.Agreement.message_fail_saasId_is_null, ExceptionCode.common_error_params_code);
        }
        //Saas saas = saasService.findByCode(saasId);
        //if(saas==null){
        //    throw new ApiException(BaseContants.Saas.message_fail_code_no_exist, ExceptionCode.common_error_params_code);
        //}
        if (StringUtils.isEmpty(wlyySignFamily.getType())) {
            throw new ApiException(WlyyRequestMapping.SignFamily.message_fail_type_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyySignFamily.getName())) {
            throw new ApiException(WlyyRequestMapping.SignFamily.message_fail_name_is_null, ExceptionCode.common_error_params_code);
        }
        String idcard = wlyySignFamily.getIdcard();
        if (StringUtils.isEmpty(idcard)) {
            throw new ApiException(WlyyRequestMapping.SignFamily.message_fail_idCard_is_null, ExceptionCode.common_error_params_code);
        }
        String s = new IDCard().IDCardValidate(idcard);//不为空字符串,说明身份证有问题啦
        if(!StringUtils.isEmpty(s)){
            throw new ApiException(s, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyySignFamily.getSsc())) {
            throw new ApiException(WlyyRequestMapping.SignFamily.message_fail_ssc_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyySignFamily.getHospital())) {
            throw new ApiException(WlyyRequestMapping.SignFamily.message_fail_hospital_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyySignFamily.getHospitalName())) {
            throw new ApiException(WlyyRequestMapping.SignFamily.message_fail_hospitalName_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyySignFamily.getStatus())) {
            throw new ApiException(WlyyRequestMapping.SignFamily.message_fail_status_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyySignFamily.getExpenses())) {
            throw new ApiException(WlyyRequestMapping.SignFamily.message_fail_expense_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyySignFamily.getExpensesStatus())) {
            throw new ApiException(WlyyRequestMapping.SignFamily.message_fail_expenseStatus_is_null, ExceptionCode.common_error_params_code);
        }
        String agreementCode = wlyySignFamily.getAgreementCode();
        if (StringUtils.isEmpty(agreementCode)) {
            throw new ApiException(WlyyRequestMapping.SignFamily.message_fail_agreementCode_is_null, ExceptionCode.common_error_params_code);
        }
        //根据agreementCode查找协议是否存在
        WlyyAgreement agreement = wlyyAgreementService.findByCode(agreementCode);
        if(agreement==null){
            throw new ApiException(WlyyRequestMapping.Agreement.message_fail_wlyyAgreement_is_no_exist, ExceptionCode.common_error_params_code);
        }
        return true;
    }

    /**
     * 根据患者code和签约状态查找签约
     * @param patientCode
     * @param status
     * @return
     */
    public List<WlyySignFamily> findByPatientCode(String patientCode,Integer status) {
        return wlyySignFamilyDao.findByPatientCode(patientCode,status);
    }
}
