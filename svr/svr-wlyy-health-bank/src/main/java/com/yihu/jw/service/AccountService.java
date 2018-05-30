package com.yihu.jw.service;/**
 * Created by nature of king on 2018/5/10.
 */

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.jw.dao.AccountDao;
import com.yihu.jw.entity.health.bank.AccountDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.rm.health.bank.HealthBankMapping;
import com.yihu.jw.util.ISqlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 添加银行账户信息
     *
     * @param accountDO 银行账户对象
     * @return
     */
    public Envelop<Boolean> insert(AccountDO accountDO){
        accountDao.save(accountDO);
        Envelop<Boolean> envelop = new Envelop<>();
        envelop.setObj(true);
        return envelop;
    }


    /**
     * 获取银行账户信息
     *
     * @param accountDO 银行账户对象
     * @param page 页码
     * @param size 每页大小
     * @return
     * @throws ParseException
     */
    public Envelop<AccountDO> findByCondition(AccountDO accountDO, Integer page, Integer size) throws ParseException {
        String sql = new ISqlUtils().getSql(accountDO,page,size,"*");
        List<AccountDO> accountDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(AccountDO.class));

        String sqlcount = new ISqlUtils().getSql(accountDO,0,0,"count");
        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sqlcount);
        Long count = 0L;
        if(rstotal!=null&&rstotal.size()>0){
            count = (Long) rstotal.get(0).get("total");
        }
        return Envelop.getSuccessListWithPage(HealthBankMapping.api_success,accountDOS,page,size,count);
    }
}
