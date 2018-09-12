package com.yihu.wlyy.figure.label.service;

import com.yihu.figure_label.entity.FlJobConfig;
import com.yihu.wlyy.figure.label.constant.BusinessConstant;
import com.yihu.wlyy.figure.label.dao.FlJobConfigDao;
import com.yihu.wlyy.figure.label.constant.ConstantUtil;
import com.yihu.wlyy.figure.label.util.QuartzHelpers;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

import static com.yihu.wlyy.figure.label.constant.BusinessConstant.gt;

/**
 *@author lith on 2018.03.23
 */
@Service
public class JobService {
    @Value("${JobService.sleepTime}")
    private Integer sleepTime;

    @Autowired
    private FlJobConfigDao flJobConfigDao;

    @Autowired
    private QuartzHelpers quartzHelpers;

    @Transactional(rollbackFor = Exception.class)
    public void stopById(String id) throws Exception {
        String[] idarr = id.split(",");
        for(String oneId : idarr){
        FlJobConfig flJobConfig = flJobConfigDao.findByIdAndStatus(Long.valueOf(oneId), "1");
        if (flJobConfig != null) {
            quartzHelpers.removeJob(flJobConfig.getId().toString());
            flJobConfigDao.updateStatus(flJobConfig.getId(), "0");
            ;
        } else {
            throw new Exception("任务已经停止");
        }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void startById(String id) throws Exception {
        String[] idarr = id.split(",");
        for(String oneId : idarr){
            FlJobConfig flJobConfig = flJobConfigDao.findByIdAndStatus(Long.valueOf(oneId), "0");
            if (flJobConfig != null) {
                startOneJob(flJobConfig);
            } else {
                throw new Exception("任务已经启动");
            }
        }

    }


    @Transactional(rollbackFor = Exception.class)
    public void stopAll() throws Exception {
        List<FlJobConfig> FlJobConfigs = flJobConfigDao.findByAll("1");
        if (FlJobConfigs != null && FlJobConfigs.size() > 0) {
            for (FlJobConfig FlJobConfig : FlJobConfigs) {
                quartzHelpers.removeJob(FlJobConfig.getId().toString());
                flJobConfigDao.updateStatus(FlJobConfig.getId(), "0");
            }
        } else {
            throw new Exception("任务已经全部停止");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void startAll() throws Exception {
        List<FlJobConfig> FlJobConfigs = flJobConfigDao.findByAll("0");
        if (FlJobConfigs != null && FlJobConfigs.size() > 0) {
            for (FlJobConfig FlJobConfig : FlJobConfigs) {
                startOneJob(FlJobConfig);
            }
        } else {
            throw new Exception("任务已经全部启动");
        }
    }

    /**
     * 启动单个任务
     *
     * @param FlJobConfig
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void startOneJob(FlJobConfig FlJobConfig) throws Exception {

        FlJobConfig flJobConfigVO = new FlJobConfig();
        BeanUtils.copyProperties(FlJobConfig, flJobConfigVO);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("jobConfig", flJobConfigVO.getId());
        params.put(BusinessConstant.sourceType, flJobConfigVO.getSourceType());
        params.put(BusinessConstant.sourceType, flJobConfigVO.getSource());
        //表里设置的增量查询类型和增量查询初始值，如果值没有配置，以当前时间为准
        if(!StringUtils.isEmpty(flJobConfigVO.getSqlFieldValue())){
            params.put(BusinessConstant.sqlFieldValue,flJobConfigVO.getSqlFieldValue());
        }else{
            params.put(BusinessConstant.sqlFieldValue, DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
        }
        params.put(BusinessConstant.SQLFIELDCONDITION, BusinessConstant.gt);
        if (!StringUtils.isEmpty(FlJobConfig.getJobClass())) {
            //往quartz框架添加任务
            quartzHelpers.addJob(getRightClass(FlJobConfig), FlJobConfig.getQuartzCron(), FlJobConfig.getId().toString(), params);
            flJobConfigDao.updateStatus(FlJobConfig.getId(), "1");//设置任务状态是启动 }
        }
    }

    public void startNowById(String id) throws Exception {
        FlJobConfig FlJobConfig = flJobConfigDao.findOne(Long.parseLong(id));

        FlJobConfig flJobConfigVO = new FlJobConfig();
        BeanUtils.copyProperties(FlJobConfig, flJobConfigVO);

        Map<String, Object> params = new HashMap<>();
        params.put("jobConfig", flJobConfigVO.getId());
        params.put(BusinessConstant.sourceType, flJobConfigVO.getSourceType());
        params.put(BusinessConstant.source, flJobConfigVO.getSource());
        if(!StringUtils.isEmpty(flJobConfigVO.getSqlFieldValue())){
            params.put(flJobConfigVO.getSqlField(),flJobConfigVO.getSqlFieldValue());
        }else{
            params.put(flJobConfigVO.getSqlField(), DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
        }
        params.put(BusinessConstant.SQLFIELDCONDITION, BusinessConstant.gt);
        //往quartz框架添加任务
        if (!StringUtils.isEmpty(FlJobConfig.getJobClass())) {
            quartzHelpers.startNow(getRightClass(FlJobConfig), FlJobConfig.getId() + UUID.randomUUID().toString().replace("-", ""), params);
            Thread.sleep(sleepTime);
        }
    }


    /**
     * @param FlJobConfig
     * @return
     * @throws ClassNotFoundException
     */
    private Class getRightClass(FlJobConfig FlJobConfig) throws ClassNotFoundException {
        return Class.forName(FlJobConfig.getJobClass());
    }


        /**
         * 增量查询数据成功后，将增量时间改为当前时间
         * @param id
         * @param lastDataId
         * @param bool
         */
    @Transactional(rollbackFor = Exception.class)
    public void updateFieldValuetoCurrentTimeOrId(Long id,Long lastDataId,boolean bool) {
        if (!bool) {
            return;
        }
        if(null != lastDataId && lastDataId != 0){
            this.flJobConfigDao.updateSqlFildeValue(id,String.valueOf(lastDataId));
            return;
        }
        this.flJobConfigDao.updateSqlFildeValue(id, DateFormatUtils.format(new Date(), ConstantUtil.date_format));
    }
}
