package com.yihu.jw.service;/**
 * Created by nature of king on 2018/5/10.
 */

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.jw.dao.AccountDao;
import com.yihu.jw.entity.health.bank.AccountDO;
import com.yihu.jw.restmodel.common.Envelop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author wangzhinan
 * @create 2018-05-10 11:26
 * @desc account service
 **/
@Service
@Transactional
public class AccountService extends BaseJpaService<AccountDO,AccountDao> {

    @Autowired
    private AccountDao accountDao;

    public Envelop<Boolean> insert(AccountDO accountDO){
        accountDao.save(accountDO);
        Envelop<Boolean> envelop = new Envelop<>();
        envelop.setObj(true);
        return envelop;
    }
}
