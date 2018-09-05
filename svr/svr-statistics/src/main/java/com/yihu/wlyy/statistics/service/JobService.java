package com.yihu.wlyy.statistics.service;

import com.yihu.jw.entity.base.statistics.JobConfigDO;
import com.yihu.wlyy.statistics.dao.QuartzJobConfigDao;
import com.yihu.wlyy.statistics.etl.cache.Cache;
import com.yihu.wlyy.statistics.job.business.CacheCleanJob;
import com.yihu.wlyy.statistics.util.DateUtil;
import com.yihu.wlyy.statistics.util.QuartzHelper;
import com.yihu.wlyy.statistics.vo.WlyyJobConfigVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author chenweida
 */
@Service
public class JobService {
    @Value("${JobService.sleepTime}")
    private Integer sleepTime;

    @Autowired
    private QuartzHelper quartzHelper;

    @Autowired
    private QuartzJobConfigDao wlyyJobConfigDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Transactional
    public void stopById(String id) throws Exception {
        JobConfigDO quartzJobConfig = wlyyJobConfigDao.findById(id, "1");
        if (quartzJobConfig != null) {
            for (int j = 1; j <= 2; j++) {
                if (breakPoint(quartzJobConfig, j)) continue;
                quartzHelper.removeJob(quartzJobConfig.getId() + "-" + j);
                wlyyJobConfigDao.updateStatus(quartzJobConfig.getId(), "0");
            }
        } else {
            throw new Exception("任务已经停止");
        }
    }

    @Transactional
    public void startById(String id) throws Exception {
        JobConfigDO quartzJobConfig = wlyyJobConfigDao.findById(id, "0");
        if (quartzJobConfig != null) {
            startOneJob(quartzJobConfig);
        } else {
            throw new Exception("任务已经启动");
        }
    }


    @Transactional
    public void stopAll() throws Exception {
        List<JobConfigDO> quartzJobConfigs = wlyyJobConfigDao.findByAll("1");
        if (quartzJobConfigs != null && quartzJobConfigs.size() > 0) {
            for (JobConfigDO quartzJobConfig : quartzJobConfigs) {
                for (int j = 1; j <= 2; j++) {
                    if (breakPoint(quartzJobConfig, j)) continue;
                    quartzHelper.removeJob(quartzJobConfig.getId() + "-" + j);

                    wlyyJobConfigDao.updateStatus(quartzJobConfig.getId(), "0");
                    ;
                }
            }
        } else {
            throw new Exception("任务已经全部停止");
        }
    }

    @Transactional
    public void startAll() throws Exception {
        List<JobConfigDO> quartzJobConfigs = wlyyJobConfigDao.findByAll("0");
        if (quartzJobConfigs != null && quartzJobConfigs.size() > 0) {
            for (JobConfigDO quartzJobConfig : quartzJobConfigs) {
                startOneJob(quartzJobConfig);
            }
        } else {
            throw new Exception("任务已经全部启动");
        }
    }

    /**
     * 启动单个任务
     *
     * @param quartzJobConfig
     * @throws Exception
     */
    @Transactional
    private void startOneJob(JobConfigDO quartzJobConfig) throws Exception {

        WlyyJobConfigVO wlyyJobConfigVO = new WlyyJobConfigVO();
        BeanUtils.copyProperties(quartzJobConfig, wlyyJobConfigVO);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("jobConfig", wlyyJobConfigVO.getId());
        params.put("incrementInterval", wlyyJobConfigVO.getIncrementInterval()!=null?String.valueOf(wlyyJobConfigVO.getIncrementInterval()):"1");
        if (!StringUtils.isEmpty(quartzJobConfig.getJobClass())) {
            for (int j = 1; j <= 2; j++) {
                if (breakPoint(wlyyJobConfigVO, j)) continue;
                params.put("timeLevel", j + "");
                //往quartz框架添加任务
                quartzHelper.addJob(getRightClass(quartzJobConfig), quartzJobConfig.getQuartzCron(), quartzJobConfig.getId() + "-" + j, params);
                wlyyJobConfigDao.updateStatus(quartzJobConfig.getId(), "1");//设置任务状态是启动 }
            }
        }
    }

    public void startNowById(String id) throws Exception {
        JobConfigDO quartzJobConfig = wlyyJobConfigDao.findOne(id);

        WlyyJobConfigVO wlyyJobConfigVO = new WlyyJobConfigVO();
        BeanUtils.copyProperties(quartzJobConfig, wlyyJobConfigVO);

        Map<String, String> params = new HashMap<String, String>();
        params.put("jobConfig", wlyyJobConfigVO.getId());
        params.put("incrementInterval", wlyyJobConfigVO.getIncrementInterval()!=null?String.valueOf(wlyyJobConfigVO.getIncrementInterval()):"1");
        for (int i = 1; i <= 2; i++) {
            if (breakPoint(wlyyJobConfigVO, i)) continue;
            params.put("timeLevel", i + "");
            //往quartz框架添加任务
            if (!StringUtils.isEmpty(quartzJobConfig.getJobClass())) {
                quartzHelper.startNow(getRightClass(quartzJobConfig), quartzJobConfig.getId() + UUID.randomUUID().toString().replace("-", ""), params);
                Thread.sleep(sleepTime);
            }
        }
    }

    private boolean breakPoint(WlyyJobConfigVO wlyyJobConfigVO, int i) {
        //该方法使用于每日统计
        if(wlyyJobConfigVO.getIncrementInterval()!=1){
            return true;
        }
        //如果为空或者等3说明纪要生成到达量也要生成增量
        if (StringUtils.isEmpty(wlyyJobConfigVO.getTimeLevel()) || Integer.valueOf(wlyyJobConfigVO.getTimeLevel()) == 3) {
            return false;
        }
        //如果不为空 并且是1或者2 说明只要增量或者只要到达量
        if (!(StringUtils.isEmpty(wlyyJobConfigVO.getTimeLevel())) && Integer.valueOf(wlyyJobConfigVO.getTimeLevel()) == i) {
            return false;
        }
        return true;
    }

    private boolean breakPoint(JobConfigDO wlyyJobConfigVO, int i) {
        //该方法使用于每日统计
        if(wlyyJobConfigVO.getIncrementInterval()!=1){
            return true;
        }
        //如果为空或者等3说明纪要生成到达量也要生成增量
        if (StringUtils.isEmpty(wlyyJobConfigVO.getTimeLevel()) || Integer.valueOf(wlyyJobConfigVO.getTimeLevel()) == 3) {
            return false;
        }
        //如果不为空 并且是1或者2 说明只要增量或者只要到达量
        if (!(StringUtils.isEmpty(wlyyJobConfigVO.getTimeLevel())) && Integer.valueOf(wlyyJobConfigVO.getTimeLevel()) == i) {
            return false;
        }
        return true;
    }

    public void productDataByDay(Integer day) throws Exception {
        List<JobConfigDO> quartzJobConfigs = wlyyJobConfigDao.findByIds();
        for (JobConfigDO quartzJobConfig : quartzJobConfigs) {

            WlyyJobConfigVO wlyyJobConfigVO = new WlyyJobConfigVO();
            BeanUtils.copyProperties(quartzJobConfig, wlyyJobConfigVO);

            Map<String, String> params = new HashMap<String, String>();
            params.put("jobConfig", wlyyJobConfigVO.getId());
            params.put("incrementInterval", wlyyJobConfigVO.getIncrementInterval()!=null?String.valueOf(wlyyJobConfigVO.getIncrementInterval()):"1");
            for (int i = 1; i <= day; i++) {
                for (int j = 1; j <= 2; j++) {
                    if (breakPoint(wlyyJobConfigVO, j)) continue;
                    params.put("timeLevel", j + "");
                    //往quartz框架添加任务
                    params.put("startTime", getYesterday(0 - i - 1));
                    params.put("endTime", getYesterday(0 - i));
                    if (!StringUtils.isEmpty(quartzJobConfig.getJobClass())) {
                        quartzHelper.startNow(getRightClass(quartzJobConfig), quartzJobConfig.getId() + UUID.randomUUID().toString().replace("-", ""), params);
                        Thread.sleep(sleepTime);
                    }
                }
            }
        }
    }

    public static String getYesterday(Integer day) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, day);
        String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        return yesterday;
    }

    public void productDataByOneDay(String yesterday) throws Exception {
        String sql = "select * from wlyy_job_config_new a where  a.del='1' and a.id !=11 order by a.id asc";
        SimpleDateFormat dataSimple = new SimpleDateFormat("yyyy-MM-dd");

        Date date = dataSimple.parse(yesterday);
        if (date == null) {
            throw new Exception("时间格式错误");
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, -1);//把日期往后增加一天.整数往后推,负数往前移动
        Date nowDate = calendar.getTime();   //这个时间就是日期往后推一天的结果
        String daybefore = new SimpleDateFormat("yyyy-MM-dd").format(nowDate.getTime());
        List<JobConfigDO> quartzJobConfigs = jdbcTemplate.query(sql, new BeanPropertyRowMapper(JobConfigDO.class));
        for (JobConfigDO quartzJobConfig : quartzJobConfigs) {

            WlyyJobConfigVO wlyyJobConfigVO = new WlyyJobConfigVO();
            BeanUtils.copyProperties(quartzJobConfig, wlyyJobConfigVO);

            Map<String, String> params = new HashMap<String, String>();
            params.put("jobConfig", wlyyJobConfigVO.getId());
            params.put("incrementInterval", wlyyJobConfigVO.getIncrementInterval()!=null?String.valueOf(wlyyJobConfigVO.getIncrementInterval()):"1");
            //往quartz框架添加任务
            params.put("startTime", daybefore);
            params.put("endTime", yesterday);

            for (int j = 1; j <= 2; j++) {
                if (breakPoint(wlyyJobConfigVO, j)) continue;
                params.put("timeLevel", j + "");
                if (!StringUtils.isEmpty(quartzJobConfig.getJobClass())) {
                    quartzHelper.startNow(getRightClass(quartzJobConfig), quartzJobConfig.getId() + UUID.randomUUID().toString().replace("-", ""), params);
                    Thread.sleep(sleepTime);
                }
            }

        }
    }

    /**
     * @param quartzJobConfig
     * @return
     * @throws ClassNotFoundException
     */
    private Class getRightClass(JobConfigDO quartzJobConfig) throws ClassNotFoundException {
        return Class.forName(quartzJobConfig.getJobClass());
    }

    public void productDataByOneDayWithId(String yesterday, String id) throws Exception {
        SimpleDateFormat dataSimple = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dataSimple.parse(yesterday);
        if (date == null) {
            throw new Exception("时间格式错误");
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, -1);//把日期往后增加一天.整数往后推,负数往前移动
        Date nowDate = calendar.getTime();   //这个时间就是日期往后推一天的结果
        String daybefore = new SimpleDateFormat("yyyy-MM-dd").format(nowDate.getTime());

        JobConfigDO quartzJobConfig = wlyyJobConfigDao.findById(id);
        if (quartzJobConfig == null) {
            throw new Exception("id不存在");
        }
        WlyyJobConfigVO wlyyJobConfigVO = new WlyyJobConfigVO();
        BeanUtils.copyProperties(quartzJobConfig, wlyyJobConfigVO);

        Map<String, String> params = new HashMap<String, String>();
        params.put("jobConfig", wlyyJobConfigVO.getId());
        params.put("incrementInterval", wlyyJobConfigVO.getIncrementInterval()!=null?String.valueOf(wlyyJobConfigVO.getIncrementInterval()):"1");
        //往quartz框架添加任务
        params.put("startTime", daybefore);
        params.put("endTime", yesterday);

        for (int j = 1; j <= 2; j++) {
            if (breakPoint(wlyyJobConfigVO, j)) continue;
            params.put("timeLevel", j + "");
            if (!StringUtils.isEmpty(quartzJobConfig.getJobClass())) {
                quartzHelper.startNow(getRightClass(quartzJobConfig), quartzJobConfig.getId() + UUID.randomUUID().toString().replace("-", ""), params);
                Thread.sleep(sleepTime);
            }
        }
    }


    public void productDataByDayAndId(Integer day, String id) throws Exception {
        JobConfigDO quartzJobConfig = wlyyJobConfigDao.findById(id);
        if (quartzJobConfig == null) {
            throw new Exception("id不存在");
        }
        WlyyJobConfigVO wlyyJobConfigVO = new WlyyJobConfigVO();
        BeanUtils.copyProperties(quartzJobConfig, wlyyJobConfigVO);

        Map<String, String> params = new HashMap<String, String>();
        params.put("jobConfig", wlyyJobConfigVO.getId());
        params.put("incrementInterval", wlyyJobConfigVO.getIncrementInterval()!=null?String.valueOf(wlyyJobConfigVO.getIncrementInterval()):"1");
        for (int i = 1; i <= day; i++) {
            //往quartz框架添加任务
            params.put("startTime", getYesterday(0 - i - 1));
            params.put("endTime", getYesterday(0 - i));
            for (int j = 1; j <= 2; j++) {
                if (breakPoint(wlyyJobConfigVO, j)) continue;
                params.put("timeLevel", j + "");
                if (!StringUtils.isEmpty(quartzJobConfig.getJobClass())) {
                    quartzHelper.startNow(getRightClass(quartzJobConfig), quartzJobConfig.getId() + UUID.randomUUID().toString().replace("-", ""), params);
                    Thread.sleep(sleepTime);
                }
            }
        }
    }

    public void productDataByDayToDay(String start, String end) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse(start);
        Date endDate = sdf.parse(end);
        if (startDate.after(endDate)) {
            throw new Exception("日期参数错误");
        }
        int day = daysBetween(startDate, endDate);
        for (int i = 0; i < day; i++) {
            Cache.cleanCache();//清空缓存
            // Thread.sleep(60000L);//休息60秒
            productDataByOneDay(getYesterday(i, startDate));
        }
    }

    public static String getYesterday(Integer day, Date startDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.DAY_OF_MONTH, day);
        String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        return yesterday;
    }

    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }


    public void productDataByDayToDayAndId(String start, String end, String ids) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse(start);
        Date endDate = sdf.parse(end);
        if (startDate.after(endDate)) {
            throw new Exception("日期参数错误");
        }
        int day = daysBetween(startDate, endDate);
        String[] idStr = ids.split(",");
        for (int i = 0; i < day; i++) {
            Cache.cleanCache();//清空缓存
            for (String id : idStr) {
                productDataByOneDayWithId(getYesterday(i, startDate), id);
            }
        }
    }

    //================================================没有休眠时间=============================================================

    public void productDataByDayToDayAndIdNoSleep(String start, String end, String id, Long sleepTime) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse(start);
        Date endDate = sdf.parse(end);
        if (startDate.after(endDate)) {
            throw new Exception("日期参数错误");
        }
        int day = daysBetween(startDate, endDate);
        for (int i = 0; i < day; i++) {
            Cache.cleanCache();//清空缓存
            productDataByOneDayWithIdNoSleep(getYesterday(i, startDate), id, sleepTime);
        }
    }


    public void productDataByOneDayWithIdNoSleep(String yesterday, String id, Long sleepTime) throws Exception {
        SimpleDateFormat dataSimple = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dataSimple.parse(yesterday);
        if (date == null) {
            throw new Exception("时间格式错误");
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, -1);//把日期往后增加一天.整数往后推,负数往前移动
        Date nowDate = calendar.getTime();   //这个时间就是日期往后推一天的结果
        String daybefore = new SimpleDateFormat("yyyy-MM-dd").format(nowDate.getTime());

        JobConfigDO quartzJobConfig = wlyyJobConfigDao.findById(id);
        if (quartzJobConfig == null) {
            throw new Exception("id不存在");
        }
        WlyyJobConfigVO wlyyJobConfigVO = new WlyyJobConfigVO();
        BeanUtils.copyProperties(quartzJobConfig, wlyyJobConfigVO);

        Map<String, String> params = new HashMap<String, String>();
        params.put("jobConfig", wlyyJobConfigVO.getId());
        params.put("incrementInterval", wlyyJobConfigVO.getIncrementInterval()!=null?String.valueOf(wlyyJobConfigVO.getIncrementInterval()):"1");
        //往quartz框架添加任务
        params.put("startTime", daybefore);
        params.put("endTime", yesterday);

        for (int j = 1; j <= 2; j++) {
            params.put("timeLevel", j + "");
            if (!StringUtils.isEmpty(quartzJobConfig.getJobClass())) {
                quartzHelper.startNow(getRightClass(quartzJobConfig), quartzJobConfig.getId() + UUID.randomUUID().toString().replace("-", ""), params);
            }
            Thread.sleep(sleepTime * 1000L);
        }
    }


    public void productDataByOneDayNoSleep(String yesterday, Long sleepTime) throws Exception {
        String sql = "select * from wlyy_job_config_new a where  a.del='1' and a.id !=11 order by a.id asc";
        SimpleDateFormat dataSimple = new SimpleDateFormat("yyyy-MM-dd");

        Date date = dataSimple.parse(yesterday);
        if (date == null) {
            throw new Exception("时间格式错误");
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, -1);//把日期往后增加一天.整数往后推,负数往前移动
        Date nowDate = calendar.getTime();   //这个时间就是日期往后推一天的结果
        String daybefore = new SimpleDateFormat("yyyy-MM-dd").format(nowDate.getTime());
        List<JobConfigDO> quartzJobConfigs = jdbcTemplate.query(sql, new BeanPropertyRowMapper(JobConfigDO.class));
        for (JobConfigDO quartzJobConfig : quartzJobConfigs) {

            WlyyJobConfigVO wlyyJobConfigVO = new WlyyJobConfigVO();
            BeanUtils.copyProperties(quartzJobConfig, wlyyJobConfigVO);

            Map<String, String> params = new HashMap<String, String>();
            params.put("jobConfig", wlyyJobConfigVO.getId());
            params.put("incrementInterval", wlyyJobConfigVO.getIncrementInterval()!=null?String.valueOf(wlyyJobConfigVO.getIncrementInterval()):"1");
            //往quartz框架添加任务
            params.put("startTime", daybefore);
            params.put("endTime", yesterday);

            for (int j = 1; j <= 2; j++) {
                params.put("timeLevel", j + "");
                if (!StringUtils.isEmpty(quartzJobConfig.getJobClass())) {
                    quartzHelper.startNow(getRightClass(quartzJobConfig), quartzJobConfig.getId() + UUID.randomUUID().toString().replace("-", ""), params);
                }
                Thread.sleep(sleepTime * 1000L);
            }

        }
    }


    public void startCleanCacheJob() throws Exception {
        if (!quartzHelper.isExistJob(CacheCleanJob.jobKey)) {
            quartzHelper.addJob(CacheCleanJob.class, CacheCleanJob.cron, CacheCleanJob.jobKey, new HashMap<>());
        } else {
            throw new Exception("已经启动");
        }
    }

    public void stopCleanCacheJob() throws Exception {
        if (quartzHelper.isExistJob(CacheCleanJob.jobKey)) {
            quartzHelper.removeJob(CacheCleanJob.jobKey);
        } else {
            throw new Exception("已经停止");
        }
    }

    /**************************************按周或按月****************************************/
    public void productWeekByDayToDay(String start, String end,String id) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse(start);
        Date endDate = sdf.parse(end);
        if (startDate.after(endDate)) {
            throw new Exception("日期参数错误");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        int a = calendar.get(Calendar.YEAR);
        calendar.clear();
        calendar.setTime(endDate);
        int b = calendar.get(Calendar.YEAR);

        int startWeek = DateUtil.week(startDate);
        if(a!=b){
            Date lastDate = DateUtil.getYearLast(a);
            int lastWeek = DateUtil.week(lastDate);
            for(int i=startWeek;i<=lastWeek;i++){
                start = getDate(startDate ,i,2);
                end = getDate(startDate ,i,7);
                productDataByOneDay2(DateUtil.getNextDay(sdf.parse(start),-1),DateUtil.getNextDay(sdf.parse(end),1),2,id);
            }
            startDate =lastDate;
        }
        startWeek = DateUtil.week(startDate);
        int endWeek = DateUtil.week(endDate);

        for(int i=startWeek;i<=endWeek;i++){
            start = getDate(startDate ,i,2);
            end = getDate(startDate ,i,7);
            productDataByOneDay2(DateUtil.getNextDay(sdf.parse(start),-1),DateUtil.getNextDay(sdf.parse(end),1),2,id);
        }
    }

    public void productMonthByDayToDay(String start, String end,String id) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse(start);
        Date endDate = sdf.parse(end);
        if (startDate.after(endDate)) {
            throw new Exception("日期参数错误");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        int a = calendar.get(Calendar.YEAR);
        int a1 = calendar.get(Calendar.MONTH);
        calendar.clear();
        calendar.setTime(endDate);
        int b = calendar.get(Calendar.YEAR);
        int b1 = calendar.get(Calendar.MONTH);

        if(a!=b){
            for(int i=a1;i<=12;i++){
//            start = getDate(startDate ,i,1)+" 00:00:00";
//            end = getDate(startDate ,i,7)+" 59:59:59";
                start = DateUtil.getFristDayOfMonth(startDate);
                end = DateUtil.getLastDayOfMonth(startDate);
                productDataByOneDay2(start,end,3,id);
            }
            a1=1;
            calendar.clear();
            calendar.set(Calendar.YEAR, b);
            startDate = calendar.getTime();
        }
        for(int i=a1;i<=b1;i++){
            start = DateUtil.getFristDayOfMonth(startDate);
            end = DateUtil.getLastDayOfMonth(startDate);
            productDataByOneDay2(start,end,3,id);
        }
    }

    public void productDataByOneDay2(String start, String end,Integer incrementInterval,String id) throws Exception {
        String condition = "";
        if(!StringUtils.isEmpty(id)){
            condition+=" and a.id in ("+id+") ";
        }
        if(incrementInterval!=null){
            condition +=" and a.increment_interval ="+incrementInterval.intValue();
        }
        String sql = "select * from wlyy_job_config_new a where  a.del='1' and a.id !=11 "+condition+" order by a.id asc";
        List<JobConfigDO> quartzJobConfigs = jdbcTemplate.query(sql, new BeanPropertyRowMapper(JobConfigDO.class));
        for (JobConfigDO quartzJobConfig : quartzJobConfigs) {

            WlyyJobConfigVO wlyyJobConfigVO = new WlyyJobConfigVO();
            BeanUtils.copyProperties(quartzJobConfig, wlyyJobConfigVO);

            Map<String, String> params = new HashMap<String, String>();
            params.put("jobConfig", wlyyJobConfigVO.getId());
            params.put("incrementInterval", wlyyJobConfigVO.getIncrementInterval()!=null?String.valueOf(wlyyJobConfigVO.getIncrementInterval()):"1");
            //往quartz框架添加任务
            params.put("startTime", start);
            params.put("endTime", end);

            for (int j = 1; j <= 2; j++) {
//                if (Integer.valueOf(wlyyJobConfigVO.getId()) > 86 && j == 2) continue;
                params.put("timeLevel", j + "");
                if (!StringUtils.isEmpty(quartzJobConfig.getJobClass())) {
                    quartzHelper.startNow(getRightClass(quartzJobConfig), quartzJobConfig.getId() + UUID.randomUUID().toString().replace("-", ""), params);
                    Thread.sleep(sleepTime);
                }
            }

        }
    }

    public String getDate(Date date ,int weekNum,int day){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.WEEK_OF_YEAR, weekNum); // 设置为2016年的第10周
        cal.set(Calendar.DAY_OF_WEEK, day); // 1表示周日，2表示周一，7表示周六
        return sf.format(cal.getTime());
    }
}
