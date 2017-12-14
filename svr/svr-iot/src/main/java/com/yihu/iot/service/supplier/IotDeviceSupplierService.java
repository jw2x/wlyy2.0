package com.yihu.iot.service.supplier;

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.iot.dao.supplier.IotDeviceSupplierDao;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.exception.code.ExceptionCode;
import com.yihu.jw.iot.supplier.IotDeviceSupplierDO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author yeshijie on 2017/12/5.
 */
@Service
public class IotDeviceSupplierService extends BaseJpaService<IotDeviceSupplierDO,IotDeviceSupplierDao>{

    @Autowired
    private IotDeviceSupplierDao iotDeviceSupplierDao;


    /**
     * 新增
     * @param iotDeviceSupplier
     * @return
     */
    public IotDeviceSupplierDO create(IotDeviceSupplierDO iotDeviceSupplier) {

        if (StringUtils.isEmpty(iotDeviceSupplier.getSupplierName())) {
            throw new ApiException(IotRequestMapping.DeviceSupplier.message_fail_supplierName_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(iotDeviceSupplier.getOrganizationCode())) {
            throw new ApiException(IotRequestMapping.DeviceSupplier.message_fail_organizationCode_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(iotDeviceSupplier.getContactsName())) {
            throw new ApiException(IotRequestMapping.DeviceSupplier.message_fail_contactsName_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(iotDeviceSupplier.getContactsMobile())) {
            throw new ApiException(IotRequestMapping.DeviceSupplier.message_fail_contactsMobile_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(iotDeviceSupplier.getContactsPhone())) {
            throw new ApiException(IotRequestMapping.DeviceSupplier.message_fail_contactsPhone_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(iotDeviceSupplier.getContactsIdcard())) {
            throw new ApiException(IotRequestMapping.DeviceSupplier.message_fail_contactsIdcard_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(iotDeviceSupplier.getType())) {
            throw new ApiException(IotRequestMapping.DeviceSupplier.message_fail_type_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(iotDeviceSupplier.getOrganizationCodeImg())) {
            throw new ApiException(IotRequestMapping.DeviceSupplier.message_fail_organizationCodeImg_is_null, ExceptionCode.common_error_params_code);
        }
        if(StringUtils.isEmpty(iotDeviceSupplier.getContactsIdcardImg())){
            throw new ApiException(IotRequestMapping.DeviceSupplier.message_fail_contactsIdcardImg_is_null,ExceptionCode.common_error_params_code);
        }
        iotDeviceSupplier.setSaasId(getCode());
        iotDeviceSupplier.setDel(1);
        return iotDeviceSupplierDao.save(iotDeviceSupplier);
    }

    /**
     * 修改
     * @param iotDeviceSupplier
     * @return
     */
    public IotDeviceSupplierDO update(IotDeviceSupplierDO iotDeviceSupplier){
        if(StringUtils.isEmpty(iotDeviceSupplier.getId())){
            throw new ApiException(IotRequestMapping.DeviceSupplier.message_fail_id_is_null,ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(iotDeviceSupplier.getSupplierName())) {
            throw new ApiException(IotRequestMapping.DeviceSupplier.message_fail_supplierName_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(iotDeviceSupplier.getOrganizationCode())) {
            throw new ApiException(IotRequestMapping.DeviceSupplier.message_fail_organizationCode_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(iotDeviceSupplier.getContactsName())) {
            throw new ApiException(IotRequestMapping.DeviceSupplier.message_fail_contactsName_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(iotDeviceSupplier.getContactsMobile())) {
            throw new ApiException(IotRequestMapping.DeviceSupplier.message_fail_contactsMobile_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(iotDeviceSupplier.getContactsPhone())) {
            throw new ApiException(IotRequestMapping.DeviceSupplier.message_fail_contactsPhone_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(iotDeviceSupplier.getContactsIdcard())) {
            throw new ApiException(IotRequestMapping.DeviceSupplier.message_fail_contactsIdcard_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(iotDeviceSupplier.getType())) {
            throw new ApiException(IotRequestMapping.DeviceSupplier.message_fail_type_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(iotDeviceSupplier.getOrganizationCodeImg())) {
            throw new ApiException(IotRequestMapping.DeviceSupplier.message_fail_organizationCodeImg_is_null, ExceptionCode.common_error_params_code);
        }
        if(StringUtils.isEmpty(iotDeviceSupplier.getContactsIdcardImg())){
            throw new ApiException(IotRequestMapping.DeviceSupplier.message_fail_contactsIdcardImg_is_null,ExceptionCode.common_error_params_code);
        }

        IotDeviceSupplierDO deviceSupplier = findById(iotDeviceSupplier.getId());
        if(deviceSupplier == null){
            throw new ApiException(IotRequestMapping.DeviceSupplier.message_fail_iotDeviceSupplier_is_no_exist,ExceptionCode.common_error_params_code);
        }

        deviceSupplier.setSupplierName(iotDeviceSupplier.getSupplierName());
        deviceSupplier.setOrganizationCode(iotDeviceSupplier.getOrganizationCode());
        deviceSupplier.setJuridicalPersonName(iotDeviceSupplier.getJuridicalPersonName());
        deviceSupplier.setOrganizationAddress(iotDeviceSupplier.getOrganizationAddress());
        deviceSupplier.setOfficePhone(iotDeviceSupplier.getOfficePhone());
        deviceSupplier.setContactsName(iotDeviceSupplier.getContactsName());
        deviceSupplier.setContactsMobile(iotDeviceSupplier.getContactsMobile());
        deviceSupplier.setContactsPhone(iotDeviceSupplier.getContactsPhone());
        deviceSupplier.setContactsIdcard(iotDeviceSupplier.getContactsIdcard());
        deviceSupplier.setType(iotDeviceSupplier.getType());
        deviceSupplier.setOrganizationCodeImg(iotDeviceSupplier.getOrganizationCodeImg());
        deviceSupplier.setContactsIdcardImg(iotDeviceSupplier.getContactsIdcardImg());

        return iotDeviceSupplierDao.save(deviceSupplier);
    }

    public IotDeviceSupplierDO findById(String id) {
        return iotDeviceSupplierDao.findById(id);
    }

    /**
     * 删除厂商，伪删除
     * @param id
     */
    public void deleteById(String id){
        if(StringUtils.isEmpty(id)){
            throw new ApiException(IotRequestMapping.DeviceSupplier.message_fail_id_is_null,ExceptionCode.common_error_params_code);
        }

        IotDeviceSupplierDO deviceSupplier = findById(id);
        if(deviceSupplier == null){
            throw new ApiException(IotRequestMapping.DeviceSupplier.message_fail_iotDeviceSupplier_is_no_exist,ExceptionCode.common_error_params_code);
        }
        deviceSupplier.setDel(0);
        iotDeviceSupplierDao.save(deviceSupplier);
    }
}
