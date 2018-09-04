package com.yihu.wlyy.statistics.job.business;

import com.yihu.jw.entity.base.statistics.DimensionQuotaDO;
import com.yihu.jw.entity.base.statistics.JobConfigDO;
import com.yihu.jw.entity.base.statistics.JobLogDO;
import com.yihu.wlyy.statistics.dao.QuartzJobConfigDao;
import com.yihu.wlyy.statistics.dao.QuartzJobLogDao;
import com.yihu.wlyy.statistics.dao.WlyyDimensionQuotaDao;
import com.yihu.wlyy.statistics.etl.cache.Cache;
import com.yihu.wlyy.statistics.etl.compute.ComputeHelper;
import com.yihu.wlyy.statistics.etl.convert.ConvertHelper;
import com.yihu.wlyy.statistics.etl.extract.ExtractHelper;
import com.yihu.wlyy.statistics.etl.filter.FilterHelper;
import com.yihu.wlyy.statistics.etl.save.SaveHelper;
import com.yihu.wlyy.statistics.etl.save.es.ElasticFactory;
import com.yihu.wlyy.statistics.util.DateUtil;
import com.yihu.wlyy.statistics.util.SpringUtil;
import com.yihu.wlyy.statistics.vo.DataModel;
import com.yihu.wlyy.statistics.vo.FilterModel;
import com.yihu.wlyy.statistics.vo.SaveModel;
import io.searchbox.client.JestClient;
import io.searchbox.core.*;
import net.sf.json.JSONArray;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by chenweida on 2017/7/10.
 */
@Component
@Scope("prototype")
@DisallowConcurrentExecution//防止到了执行时间点前一任务还在执行中，但是这时有空闲的线程，那么马上又会执行，这样一来就会存在同一job被并行执行
public class MysqlToEsQuotaJob implements Job {

    private Logger logger = LoggerFactory.getLogger(MysqlToEsQuotaJob.class);

    private String wlyyJobCongId;//指标对象
    private JobConfigDO quartzJobConfig;//指标对象
    private String endTime;//结束时间
    private String startTime;//开始时间
    private String year;//要统计的年份
    private Date quotaDate;//统计的时间
    private String timeLevel;//1 日 2年
    private String incrementInterval;//增量的时间间隔（天）

    @Autowired
    private QuartzJobLogDao quartzJobLogDao;//执行日志Dao
    @Autowired
    private WlyyDimensionQuotaDao dimensionQuotaDao;
    @Autowired
    private QuartzJobConfigDao quartzJobConfigDao;

    @Autowired
    private ElasticFactory elasticFactory;
    @Value("${es.type}")
    private String esType;
    @Value("${es.index}")
    private String esIndex;

    @Override
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
    private void initParams(JobExecutionContext context) throws Exception {
        JobDataMap map = context.getJobDetail().getJobDataMap();

        this.timeLevel = map.getString("timeLevel");
        this.endTime = map.getString("endTime");
        this.startTime = map.getString("startTime");
        this.incrementInterval = map.get("incrementInterval")!=null?String.valueOf(map.get("incrementInterval")):"1";
        //为空默认是统计昨天的数据  统计昨天的数据是从 前天的下午17:00:00 到昨天的下午17:00:00
        //初始化结束时间
        if (StringUtils.isEmpty(endTime)) {
            endTime = new LocalDate(new DateTime().minusDays(1)).toString("yyyy-MM-dd") + " 17:00:00"; //2017-06-01 17:00:00
        } else {
            endTime = endTime + " 17:00:00";
        }
        //初始化统计年份
        this.year = getNowYearByDate(endTime);
        //初始化开始时间
        if ("2".equals(timeLevel)) {
            //按年度到达量
            startTime = this.year + "-06-30 17:00:00";
        } else {
            //增量
            if (StringUtils.isEmpty(startTime)) {
//                startTime = new LocalDate(new DateTime().minusDays(2)).toString("yyyy-MM-dd") + " 17:00:00"; //2017-06-01 17:00:00
                getStartTime();
            } else {
                startTime = startTime + " 17:00:00";
            }
        }


        this.quotaDate = DateUtil.strToDate(endTime, "yyyy-MM-dd");
        this.wlyyJobCongId = map.getString("jobConfig");
        this.quartzJobConfig = quartzJobConfigDao.findById(wlyyJobCongId);
    }

    /**
     * 计算指标
     */
    private void computequota() {
        try {
            logger.info("========================quotaCode:" + wlyyJobCongId + "," + DateUtil.dateToStr(quotaDate, "yyyy-MM-dd") + ",timeLevel:" + timeLevel + " start========================");
            JobLogDO tjQuotaLog = new JobLogDO();
            tjQuotaLog.setJobId(wlyyJobCongId);
            tjQuotaLog.setJobStartTime(new Date());

            // 0 删除这天的数据
            deleteData(quotaDate, wlyyJobCongId, timeLevel);
            //1..抽取数据 如果是累加就是 List<DataModel>  如果是相除 Map<String,List<DataModel>>
            List<DataModel> dataModels = extract();
            //2..根据规则过滤数据
            FilterModel filterModel = filter(dataModels);
            //得到该指标的维度
            List<DimensionQuotaDO> dimensionQuotas = dimensionQuotaDao.findDimensionQuotasByQuotaCode(wlyyJobCongId);
            //2.1.从维度的key转换
            if (dimensionQuotas != null && dimensionQuotas.size() > 0) {
                filterModel = convert(filterModel, dimensionQuotas);
            }
            //3.统计数据
            List<SaveModel> sms = compute(filterModel.getData(), dimensionQuotas, timeLevel);
            //4.保存数据
            Boolean success = saveDate(sms);

            tjQuotaLog.setJobType(success ? "1" : "0");
            tjQuotaLog.setJobEndTime(new Date());
            tjQuotaLog.setJobContent(JSONArray.fromObject(filterModel.getErrorModels()).toString());
            saveLog(tjQuotaLog);
            logger.info("========================quotaCode:" + wlyyJobCongId + "," + DateUtil.dateToStr(quotaDate, "yyyy-MM-dd") + " end========================");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除 某个指标某一天的某个timelevel的数据
     *
     * @param quotaDate
     * @param quotaCode
     * @param timeLevel
     */
    private void deleteData(Date quotaDate, String quotaCode, String timeLevel) {
        JestClient jestClient = null;
        try {
            jestClient = elasticFactory.getJestClient();
            //先根据条件查找出来
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(
                    new BoolQueryBuilder()
                            .must(QueryBuilders.matchQuery("quotaCode", quotaCode))
                            .must(QueryBuilders.matchQuery("timeLevel", timeLevel))
                            .must(QueryBuilders.matchQuery("quotaDate", quotaDate))
            ).size(500000);//一次取10000条

            Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex(esIndex).addType(esType)
                    .build();
            SearchResult result = jestClient.execute(search);
            List<SaveModel> saveModels = result.getSourceAsObjectList(SaveModel.class);

            //根据id批量删除
            Bulk.Builder bulk = new Bulk.Builder().defaultIndex(esIndex).defaultType(esType);
            for (SaveModel obj : saveModels) {
                Delete index = new Delete.Builder(obj.getId()).build();
                bulk.addAction(index);
            }
            BulkResult br = jestClient.execute(bulk.build());

            logger.info("delete data count:" + saveModels.size());
            logger.info("delete flag:" + br.isSucceeded());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jestClient != null) {
                jestClient.shutdownClient();
            }
        }
    }

    @Transactional
    private void saveLog(JobLogDO tjQuotaLog) {
        quartzJobLogDao.save(tjQuotaLog);
    }

    private FilterModel convert(FilterModel dataModels, List<DimensionQuotaDO> dimensionQuotas) {
        try {
            return SpringUtil.getBean(ConvertHelper.class).convert(dataModels, dimensionQuotas);
        } catch (Exception e) {
            logger.error("convert error:" + e.getMessage());
        }
        return null;
    }

    /**
     * 保存数据
     *
     * @param sms
     */
    private Boolean saveDate(List<SaveModel> sms) {
        try {
            return SpringUtil.getBean(SaveHelper.class).save(sms);
        } catch (Exception e) {
            logger.error("save error:" + e.getMessage());
        }
        return false;
    }

    /**
     * 根据计算规则统计数据
     *
     * @param dataModels
     */
    private List<SaveModel> compute(List<DataModel> dataModels, List<DimensionQuotaDO> dimensionQuotas, String timeLevel) {
        try {
            return SpringUtil.getBean(ComputeHelper.class).compute(dataModels, dimensionQuotas, wlyyJobCongId, endTime, timeLevel);
        } catch (Exception e) {
            logger.error("compute error:" + e.getMessage());
        }
        return null;
    }

    /**
     * 过滤数据
     *
     * @param dataModel
     * @return
     */
    private FilterModel filter(List<DataModel> dataModel) {
        try {
            FilterModel filterModel = SpringUtil.getBean(FilterHelper.class).filter(dataModel);
            logger.info("FilterModel:  success sizs:" + filterModel.getData().size() + ",error size:" + filterModel.getErrorModels().size());
            return filterModel;
        } catch (Exception e) {
            logger.error("filter error:" + e.getMessage());
        }
        return null;
    }

    /**
     * 抽取数据
     *
     * @return
     */
    private List<DataModel> extract() {
        try {
            List<DataModel> dataModels = null;
            //先判断指标是否支持缓存
            if (StringUtils.isEmpty(quartzJobConfig.getCacheKey())) {
                //不支持直接去数据库拿
                dataModels = SpringUtil.getBean(ExtractHelper.class).extractData(quartzJobConfig, startTime, endTime, year, timeLevel);
            } else {
                //缓存的key 是 时间+timelevel+key
                StringBuffer bu = new StringBuffer(DateUtil.dateToStr(quotaDate, "yyyy-MM-dd") + "-" + timeLevel + "-" + quartzJobConfig.getCacheKey());
                //支持的话判断缓存有没有值
                dataModels = Cache.getCache(bu.toString());
                if (dataModels == null) {
                    //如果缓存是空的那么直接数据库拿 在放入缓存
                    dataModels = SpringUtil.getBean(ExtractHelper.class).extractData(quartzJobConfig, startTime, endTime, year, timeLevel);
                    Cache.addCache(bu.toString(), dataModels);
                }
            }
            logger.info("quotaCode:" + wlyyJobCongId + ",size:" + dataModels.size());
            return dataModels;
        } catch (Exception e) {
            logger.error("extract error:" + e.getMessage());
        }
        return null;
    }

    /**
     * 获取现在时间属于那个年度
     *
     * @param date
     * @return
     */
    public static String getNowYearByDate(String date) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date today = simpleDateFormat.parse(date);
        String todayString = simpleDateFormat.format(today);


        String startDateString = (1900 + today.getYear()) + "-06-30";
        Date startDate = simpleDateFormat.parse(startDateString);
        if (simpleDateFormat.parse(todayString).after(startDate)) {
            return (1900 + today.getYear()) + "";
        } else {
            return (1900 + today.getYear() - 1) + "";
        }
    }

    public void getStartTime() throws Exception{
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        if("1".equals(this.incrementInterval)){//日
            startTime = new LocalDate(new DateTime().minusDays(2)).toString("yyyy-MM-dd") + " 17:00:00";
        }else if("2".equals(this.incrementInterval)){//周
            Date monday = DateUtil.getMondayOfThisDayToDate(sf.parse(endTime));
//            startTime =sf.format(monday)+ " 17:00:00";
            startTime =DateUtil.getNextDay(monday,-1)+ " 17:00:00";
        }else if("3".equals(this.incrementInterval)){//月
            Date fristDay = DateUtil.getFristDayOfMonthToDate(sf.parse(endTime));
//            startTime = sf.format(fristDay)+ " 17:00:00";
            startTime =DateUtil.getNextDay(fristDay,-1)+ " 17:00:00";
        }
    }
}
