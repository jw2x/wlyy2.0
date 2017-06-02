package com.yihu.jw.quota.job;

import com.yihu.jw.quota.dao.jpa.TjQuotaLogDao;
import com.yihu.jw.quota.etl.Contant;
import com.yihu.jw.quota.etl.compute.ComputeHelper;
import com.yihu.jw.quota.etl.extract.ExtractHelper;
import com.yihu.jw.quota.etl.filter.FilterHelper;
import com.yihu.jw.quota.etl.save.SaveHelper;
import com.yihu.jw.quota.model.jpa.TjQuotaLog;
import com.yihu.jw.quota.model.jpa.compute.TjCompute;
import com.yihu.jw.quota.service.compute.TjComputeService;
import com.yihu.jw.quota.service.rule.TjCleanRuleService;
import com.yihu.jw.quota.service.source.TjDataSourceService;
import com.yihu.jw.quota.util.SpringUtil;
import com.yihu.jw.quota.vo.*;
import net.sf.json.JSONObject;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016.10.10.
 * 每天的签约到达量统计
 */
@Component
@Scope("prototype")
@DisallowConcurrentExecution//防止到了执行时间点前一任务还在执行中，但是这时有空闲的线程，那么马上又会执行，这样一来就会存在同一job被并行执行
public class QuotaJob implements Job {

    private Logger logger = LoggerFactory.getLogger(QuotaJob.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private TjComputeService computeService;
    @Autowired
    private TjQuotaLogDao tjQuotaLogDao;

    private String saasid;//saasid
    private QuotaVO quotaVO;//指标对象
    private String endTime;//结束时间
    private String startTime;//开始时间
    private String timeLevel;//时间


    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        try {
            //springz注入
            SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
            //初始化参数
            initParams(context);
            //统计指标
            computequota();
        } catch (Exception e) {
            //如果出錯立即重新執行
            JobExecutionException e2 = new JobExecutionException(e);
            e2.setRefireImmediately(true);
            e.printStackTrace();
        }
    }


    /**
     * 初始化参数
     *
     * @param context
     */
    private void initParams(JobExecutionContext context) {
        JobDataMap map = context.getJobDetail().getJobDataMap();
        this.saasid = map.getString("saasid");

        this.endTime = map.getString("endTime");
        if (StringUtils.isEmpty(endTime)) {
            endTime = LocalDate.now().toString("yyyy-MM-dd"); //2017-06-01 默认今天
        }

        this.startTime = map.getString("startTime");
        if (StringUtils.isEmpty(startTime)) {
            startTime = new LocalDate(new DateTime().minusDays(1)).toString("yyyy-MM-dd"); //默认昨天
        }

        this.quotaVO = (QuotaVO) map.get("quota");
        if (quotaVO != null) {
            //得到算法
            TjCompute compute = computeService.findByQuotaCode(quotaVO.getCode());
            quotaVO.setComputeType(compute.getType());
        }else{
            logger.error("quotaVO is null");
            return;
        }
        this.timeLevel= (String) map.get("timeLevel");
        if(StringUtils.isEmpty(this.timeLevel)){
            this.timeLevel=Contant.main_dimension_timeLevel.day;
        }
    }

    /**
     * 计算指标
     */
    private void computequota() {
        try {
            TjQuotaLog tjQuotaLog=new TjQuotaLog();
            tjQuotaLog.setQuotaCode(quotaVO.getCode());
            tjQuotaLog.setSaasId(saasid);
            tjQuotaLog.setStartTime(new Date());
            JobLogModel jobLogModel=new JobLogModel();
            //抽取数据 如果是累加就是 List<DataModel>  如果是相除 Map<String,List<DataModel>>
            Object dataModels = extract();
            //根据规则过滤数据
            FilterModel filterModel = filter(dataModels);
            jobLogModel.setErrorModels(filterModel.getErrorModels());
            //统计数据
            List<SaveModel> sms = compute(filterModel.getData());
            //保存数据
            saveDate(sms);
            tjQuotaLog.setEndTime(new Date());
            tjQuotaLog.setContent(JSONObject.fromObject(jobLogModel).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存数据
     * @param sms
     */
    private void saveDate(List<SaveModel> sms) {
        try {
           SpringUtil.getBean(SaveHelper.class).save(sms, quotaVO);
        } catch (Exception e) {
            logger.error("save error:"+e.getMessage());
        }
    }

    /**
     * 根据计算规则统计数据
     *
     * @param dataModels
     */
    private List<SaveModel> compute(Object dataModels) {
        try {
            return SpringUtil.getBean(ComputeHelper.class).compute(dataModels, quotaVO,timeLevel,saasid,startTime);
        } catch (Exception e) {
            logger.error("compute error:"+e.getMessage());
        }
        return null;
    }

    /**
     * 过滤数据
     *
     * @param dataModels
     * @return
     */
    private FilterModel filter(Object dataModels) {
        try {
            return SpringUtil.getBean(FilterHelper.class).filter(dataModels, quotaVO);
        } catch (Exception e) {
            logger.error("filter error:"+e.getMessage());
        }
        return null;
    }

    /**
     * 抽取数据
     *
     * @return
     */
    private Object extract() {
        try {
            return SpringUtil.getBean(ExtractHelper.class).extractData(quotaVO, startTime, endTime);
        } catch (Exception e) {
            logger.error("extract error:"+e.getMessage());
        }
        return null;
    }

}
