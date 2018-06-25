package com.yihu.jw.service;/**
 * Created by nature of king on 2018/5/10.
 */

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.jw.dao.AccountDao;
import com.yihu.jw.dao.ActiveRecordDao;
import com.yihu.jw.dao.ActivityDao;
import com.yihu.jw.dao.TaskDao;
import com.yihu.jw.entity.health.bank.AccountDO;
import com.yihu.jw.entity.health.bank.ActiveRecordDO;
import com.yihu.jw.entity.health.bank.ActivityDO;
import com.yihu.jw.entity.health.bank.TaskDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.rm.health.bank.HealthBankMapping;
import com.yihu.jw.util.ISqlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
public class ActiveRecordService extends BaseJpaService<ActiveRecordDO,ActiveRecordDO> {

    @Autowired
    private TaskDao taskDao;
    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private ActiveRecordDao activeRecordDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 添加健康银行活动活跃记录
     *
     * @return
     */
    public ActiveRecordDO insert(String saasId,String taskId,String activityId,Integer originalStatus,Integer currentStatus,
    String patientId) throws Exception{
        ActiveRecordDO activeRecordDO = new ActiveRecordDO();
        activeRecordDO.setSaasId(saasId);
        if(!StringUtils.isEmpty(taskId)){
            activeRecordDO.setTaskId(taskId);
            TaskDO taskDO = taskDao.findOne(taskId);
            activeRecordDO.setTaskTitle(taskDO!=null?taskDO.getTitle():null);
            if(StringUtils.isEmpty(activityId)&&"ACTIVITY_TASK".equals(taskDO.getType())){
                activityId = taskDO.getTransactionId();
            }
        }
        if(!StringUtils.isEmpty(activityId)){
            activeRecordDO.setActivityId(activityId);
            ActivityDO activityDO = activityDao.findOne(activityId);
            activeRecordDO.setActivityTitle(activityDO!=null?activityDO.getTitle():null);
        }
        activeRecordDO.setOriginalStatus(originalStatus);
        activeRecordDO.setCurrentStatus(currentStatus);
        String sql = " select * from wlyy.wlyy_patient where code='"+patientId+"' and status=1 ";
        Map<String,Object> result = jdbcTemplate.queryForMap(sql);
        activeRecordDO.setPatientId(patientId);
        if(result!=null){
            activeRecordDO.setPatientName(result.get("name")+"");
        }
        String sql2 = "select f.* from wlyy.wlyy_sign_family f where f.patient='"+patientId+"' and f.status=1 and f.expenses_status=1";
//        Map<String,Object> result2 = jdbcTemplate.queryForMap(sql2);
        List<Map<String, Object>> result2 = jdbcTemplate.queryForList(sql2);
        if(result2!=null&&result2.size()>0){
            activeRecordDO.setTeamId(result2.get(0).get("admin_team_code")!=null?(Integer)result2.get(0).get("admin_team_code"):null);;
        }
        return activeRecordDao.save(activeRecordDO);
    }


}
