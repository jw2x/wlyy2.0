package com.yihu.jw.service;/**
 * Created by nature of king on 2018/6/8.
 */

import com.yihu.jw.dao.TaskRangDao;
import com.yihu.jw.entity.health.bank.TaskRangDO;
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
 * @create 2018-06-08 15:41
 * @desc 任务范围
 **/
@Service
@Transactional
public class TaskRangService extends BaseJpaService<TaskRangDO,TaskRangDao> {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private TaskRangDao taskRangDao;

    /**
     * 查看任务范围
     *
     * @param taskRangDO 任务范围对象
     * @param page 页码
     * @param size 分页大小
     * @return
     */
    public MixEnvelop<TaskRangDO, TaskRangDO> selectByCondition(TaskRangDO taskRangDO, Integer page, Integer size){
        String sql = new ISqlUtils().getSql(taskRangDO,page,size,"*");
        List<TaskRangDO> taskRangDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(TaskRangDO.class));
        String sqlcount = new ISqlUtils().getSql(taskRangDO,0,0,"count");
        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sqlcount);
        Long count = 0L;
        if(rstotal!=null&&rstotal.size()>0){
            count = (Long) rstotal.get(0).get("total");
        }
        return MixEnvelop.getSuccessListWithPage(HealthBankMapping.api_success, taskRangDOS,page,size,count);
    }

    /**
     * 添加任务范围
     *
     * @param taskRangDO 任务范围对象
     * @return
     */
    public MixEnvelop<Boolean, Boolean> insert(TaskRangDO taskRangDO){
        MixEnvelop<Boolean, Boolean> envelop = new MixEnvelop<>();
        taskRangDO.setCreateTime(new Date());
        taskRangDO.setUpdateTime(new Date());
        taskRangDao.save(taskRangDO);
        envelop.setObj(true);
        return envelop;
    }


    /**
     * 更新任务范围
     *
     * @param taskRangDO 任务范围对象
     * @return
     */
    public MixEnvelop<Boolean, Boolean> update(TaskRangDO taskRangDO){
        MixEnvelop<Boolean, Boolean> envelop = new MixEnvelop<>();
        String sql =  ISqlUtils.getUpdateSql(taskRangDO);
        jdbcTemplate.update(sql);
        envelop.setObj(true);
        return envelop;
    }

}
