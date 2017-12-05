package com.yihu.jw.business.login.service;

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.jw.base.login.BaseLoginAccountDO;
import com.yihu.jw.business.login.dao.BaseLoginAccountDao;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.exception.code.ExceptionCode;
import com.yihu.jw.rm.base.BaseUserRequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Created by LiTaohong on 2017/12/5.
 * 用户账户功能
 */
@Service
public class BaseLoginAccountService extends BaseJpaService<BaseLoginAccountDO,BaseLoginAccountDao> {

    @Autowired
    private BaseLoginAccountDao baseLoginAccountDao;

    /**
     * 创建账户
     * @param baseLoginAccountDO
     * @return
     */
    @Transactional
    public BaseLoginAccountDO createBaseLoginAccount(BaseLoginAccountDO baseLoginAccountDO){
        if (StringUtils.isEmpty(baseLoginAccountDO.getId())) {
            throw new ApiException(BaseUserRequestMapping.BaseMenu.message_fail_id_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(baseLoginAccountDO.getSaasId())) {
            throw new ApiException(BaseUserRequestMapping.BaseMenu.message_fail_saasId_is_null, ExceptionCode.common_error_params_code);
        }
        return this.baseLoginAccountDao.save(baseLoginAccountDO);
    }


    /**
     * 修改账户
     * @param baseLoginAccountDO
     * @return
     */
    @Transactional
    public BaseLoginAccountDO updateBaseLoginAccount(BaseLoginAccountDO baseLoginAccountDO){
        return this.baseLoginAccountDao.save(baseLoginAccountDO);
    }


    /**
     * 锁定账户
     * @param id
     * @return
     */
    @Transactional
    public void lockBaseLoginAccount(String id){
         this.baseLoginAccountDao.updateAccount(id);
    }

    /**
     * 删除账户
     * @param id
     * @return
     */
    @Transactional
    public void deleteBaseLoginAccount(String id){
         this.baseLoginAccountDao.deleteAccount(id);
    }




}
