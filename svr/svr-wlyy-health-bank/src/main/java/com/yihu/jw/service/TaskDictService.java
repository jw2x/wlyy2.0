package com.yihu.jw.service;/**
 * Created by nature of king on 2018/6/8.
 */

import com.yihu.jw.dao.TaskDictDao;
import com.yihu.jw.entity.health.bank.TaskDictDO;
import com.yihu.jw.restmodel.common.Envelop;
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
 * @create 2018-06-08 15:47
 * @desc 字典 service
 **/
@Service
@Transactional
public class TaskDictService extends BaseJpaService<TaskDictDO,TaskDictDao> {

    @Autowired
    private TaskDictDao taskDictDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查看任务字典
     *
     * @param taskDictDO 任务字典对象
     *
     * @param page 页码
     * @param size 分页大小
     * @return
     */
    public Envelop<TaskDictDO> selectByCondition(TaskDictDO taskDictDO, Integer page, Integer size){
        String sql = new ISqlUtils().getSql(taskDictDO,page,size,"*");
        List<TaskDictDO> taskDictDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(TaskDictDO.class));
        String sqlcount = new ISqlUtils().getSql(taskDictDO,0,0,"count");
        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sqlcount);
        Long count = 0L;
        if(rstotal!=null&&rstotal.size()>0){
            count = (Long) rstotal.get(0).get("total");
        }
        return Envelop.getSuccessListWithPage(HealthBankMapping.api_success, taskDictDOS,page,size,count);
    }

    /**
     * 添加任务字典
     *
     *
     * @param taskDictDO 任务字典对象
     * @return
     */
    public Envelop<Boolean> insert(TaskDictDO taskDictDO){
        taskDictDO.setCreateTime(new Date());
        taskDictDO.setUpdateTime(new Date());
        Envelop<Boolean> envelop = new Envelop<>();
        taskDictDao.save(taskDictDO);
        envelop.setObj(true);
        return envelop;
    }


    /**
     * 更新任务字典
     *
     * @param taskDictDO 任务字典对象
     * @return
     */
    public Envelop<Boolean> update(TaskDictDO taskDictDO){
        Envelop<Boolean> envelop = new Envelop<>();
        String sql =  ISqlUtils.getUpdateSql(taskDictDO);
        jdbcTemplate.update(sql);
        envelop.setObj(true);
        return envelop;
    }
}
