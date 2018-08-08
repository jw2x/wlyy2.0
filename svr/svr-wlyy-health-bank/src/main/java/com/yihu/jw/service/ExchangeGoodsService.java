package com.yihu.jw.service;/**
 * Created by nature of king on 2018/5/3.
 */

import com.yihu.jw.dao.CredittsLogDetailDao;
import com.yihu.jw.dao.ExchangeGoodsDao;
import com.yihu.jw.dao.GoodsDao;
import com.yihu.jw.entity.health.bank.ExchangeGoodsDO;
import com.yihu.jw.entity.health.bank.GoodsDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.rm.health.bank.HealthBankMapping;
import com.yihu.jw.util.ISqlUtils;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wangzhinan
 * @create 2018-05-03 15:34
 * @desc exchange goods Service
 **/
@Service
@Transactional
public class ExchangeGoodsService extends BaseJpaService<ExchangeGoodsDO,ExchangeGoodsDao> {

    @Autowired
    private ExchangeGoodsDao exchangeGoodsDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CredittsLogDetailDao credittsLogDetailDao;
    @Autowired
    private GoodsDao goodsDao;

  /*  *//**
     * exchange goods
     *
     * @param exchangeGoodsDO
     * @return
     *//*
    public Envelop<Boolean> insert(ExchangeGoodsDO exchangeGoodsDO){
        GoodsDO goodsDO = goodsDao.findOne(exchangeGoodsDO.getGoodsId());
        exchangeGoodsDO.setIntegrate(goodsDO.getRequireIntegrate());
        exchangeGoodsDao.save(exchangeGoodsDO);
        String sql = "select cl.status as status,cl.total as total from health_bank_credits_log cl order by cl.update_time desc";
        List<CreditsDetailDO> creditsLogDetailDOList = jdbcTemplate.query(sql,new BeanPropertyRowMapper(CreditsDetailDO.class));
        CreditsDetailDO creditsLogDetailDO = new CreditsDetailDO();
        if (creditsLogDetailDOList == null || creditsLogDetailDOList.size() ==0){
            creditsLogDetailDO.setTotal(Long.parseLong(goodsDO.getRequireIntegrate()));
        }else {
            creditsLogDetailDO.setTotal(creditsLogDetailDOList.get(0).getTotal()-Long.parseLong(goodsDO.getRequireIntegrate()));
        }
        creditsLogDetailDO.setPatientId(exchangeGoodsDO.getPatientId());
        creditsLogDetailDO.setIntegrate("-"+exchangeGoodsDO.getIntegrate());
        creditsLogDetailDO.setIntegrateType("EXCHANGE_GOODS");
        creditsLogDetailDO.setIntegrateId(exchangeGoodsDO.getGoodsId());
        creditsLogDetailDO.setStatus("used");
        creditsLogDetailDO.setCommunity(goodsDO.getCommunity());
        credittsLogDetailDao.save(creditsLogDetailDO);
        Envelop<Boolean> envelop = new Envelop<>();
        envelop.setObj(true);
        return envelop;
    }*/

    /**
     * find exchange goods info
     *
     * @param exchangeGoodsDO
     * @param page
     * @param size
     * @return
     * @throws ParseException
     */
    public Envelop<ExchangeGoodsDO> findByCondition(ExchangeGoodsDO exchangeGoodsDO, Integer page, Integer size) throws ParseException {
        String sql = new ISqlUtils().getSql(exchangeGoodsDO,page,size,"*");
        List<ExchangeGoodsDO> exchangeGoodsDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(ExchangeGoodsDO.class));
        List<ExchangeGoodsDO> exchangeGoodsDOList = new ArrayList<>();
        for (ExchangeGoodsDO exchangeGoodsDO1:exchangeGoodsDOS){
            GoodsDO goodsDO = goodsDao.findOne(exchangeGoodsDO1.getGoodsId());
            exchangeGoodsDO1.setGoodsDO(goodsDO);
            exchangeGoodsDOList.add(exchangeGoodsDO1);
        }
        String sqlcount = new ISqlUtils().getSql(exchangeGoodsDO,0,0,"count");
        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sqlcount);
        Long count = 0L;
        if(rstotal!=null&&rstotal.size()>0){
            count = (Long) rstotal.get(0).get("total");
        }
        return Envelop.getSuccessListWithPage(HealthBankMapping.api_success,exchangeGoodsDOList,page,size,count);
    }
}
