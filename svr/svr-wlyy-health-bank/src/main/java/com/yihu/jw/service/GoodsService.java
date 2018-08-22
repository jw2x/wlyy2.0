package com.yihu.jw.service;/**
 * Created by nature of king on 2018/4/27.
 */

import com.yihu.jw.dao.GoodsDao;
import com.yihu.jw.entity.health.bank.GoodsDO;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.rm.health.bank.HealthBankMapping;
import com.yihu.jw.util.ISqlUtils;
import com.yihu.mysql.query.BaseJpaService;
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
 * @create 2018-04-27 16:56
 * @desc goods Service
 **/
@Service
@Transactional
public class GoodsService extends BaseJpaService<GoodsDO,GoodsDao> {
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /**
     * insert health task
     *
     * @param goodsDO
     * @return
     */
    public MixEnvelop<Boolean, Boolean> insert(GoodsDO goodsDO){
        goodsDao.save(goodsDO);
        MixEnvelop<Boolean, Boolean> envelop = new MixEnvelop<>();
        envelop.setObj(true);
        return envelop;
    }

    /**
     * find goods
     * @param goodsDO
     * @param page
     * @param size
     * @return
     * @throws ParseException
     */
    public MixEnvelop<GoodsDO, GoodsDO> findByCondition(GoodsDO goodsDO, Integer page, Integer size) throws ParseException {
        String sql = new ISqlUtils().getSql(goodsDO,page,size,"*");
        List<GoodsDO> goodsDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(GoodsDO.class));

        String sqlcount = new ISqlUtils().getSql(goodsDO,0,0,"count");
        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sqlcount);
        Long count = 0L;
        if(rstotal!=null&&rstotal.size()>0){
            count = (Long) rstotal.get(0).get("total");
        }
        return MixEnvelop.getSuccessListWithPage(HealthBankMapping.api_success,goodsDOS,page,size,count);
    }
    /**
     * update goods
     *
     * @param goodsDO
     * @return
     */
    public MixEnvelop<Boolean, Boolean> update(GoodsDO goodsDO){
        goodsDao.save(goodsDO);
        MixEnvelop<Boolean, Boolean> envelop = new MixEnvelop<>();
        envelop.setObj(true);
        return envelop;
    }
}
