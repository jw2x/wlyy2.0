package com.yihu.jw.service;/**
 * Created by nature of king on 2018/6/8.
 */

import com.yihu.jw.dao.TaskGoodsDao;
import com.yihu.jw.entity.health.bank.TaskGoodsDO;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.rm.health.bank.HealthBankMapping;
import com.yihu.jw.util.ISqlUtils;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author wangzhinan
 * @create 2018-06-08 15:46
 * @desc 活动商品 service
 **/
@Service
@Transactional
public class TaskGoodsService extends BaseJpaService<TaskGoodsDO,TaskGoodsDao> {

    @Autowired
    private TaskGoodsDao taskGoodsDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查看商品
     *
     * @param taskGoodsDO 活动商品对象
     * @param page 页码
     * @param size 分页大小
     * @return
     */
    public MixEnvelop<TaskGoodsDO, TaskGoodsDO> selectByCondition(TaskGoodsDO taskGoodsDO, Integer page, Integer size){
        String sql = new ISqlUtils().getSql(taskGoodsDO,page,size,"*");
        List<TaskGoodsDO> taskGoodsDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(TaskGoodsDO.class));
        String sqlcount = new ISqlUtils().getSql(taskGoodsDO,0,0,"count");
        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sqlcount);
        Long count = 0L;
        if(rstotal!=null&&rstotal.size()>0){
            count = (Long) rstotal.get(0).get("total");
        }
        return MixEnvelop.getSuccessListWithPage(HealthBankMapping.api_success, taskGoodsDOS,page,size,count);
    }


    /**
     * 添加活动商品
     *
     * @param taskGoodsDO 活动商品对象
     * @return
     */
    public MixEnvelop<Boolean, Boolean> insert(TaskGoodsDO taskGoodsDO){
        taskGoodsDO.setCreateTime(new Date());
        taskGoodsDO.setUpdateTime(new Date());
        MixEnvelop<Boolean, Boolean> envelop = new MixEnvelop<>();
        taskGoodsDao.save(taskGoodsDO);
        envelop.setObj(true);
        return envelop;
    }


    /**
     * 更新活动商品
     *
     * @param taskGoodsDO
     * @return
     */
    public MixEnvelop<Boolean, Boolean> update(TaskGoodsDO taskGoodsDO){
        MixEnvelop<Boolean, Boolean> envelop = new MixEnvelop<>();
        String sql =  ISqlUtils.getUpdateSql(taskGoodsDO);
        jdbcTemplate.update(sql);
        envelop.setObj(true);
        return envelop;
    }
}
